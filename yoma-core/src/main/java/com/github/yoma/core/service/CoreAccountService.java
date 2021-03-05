package com.github.yoma.core.service;

import cn.javaer.aliyun.sms.SmsClient;
import cn.javaer.aliyun.sms.SmsTemplate;
import com.github.pagehelper.PageInfo;
import com.github.yoma.common.exception.EntityNotFoundException;
import com.github.yoma.common.persistence.CrudService;
import com.github.yoma.common.utils.EncryptUtils;
import com.github.yoma.common.utils.SecurityUtils;
import com.github.yoma.common.utils.StringUtils;
import com.github.yoma.common.utils.ValidationUtil;
import com.github.yoma.config.config.DateConverterConf;
import com.github.yoma.config.config.RedisKeyConfig;
import com.github.yoma.core.bussinessenum.RoleTypeEnum;
import com.github.yoma.core.dao.CoreAccountDao;
import com.github.yoma.core.domain.CoreAccount;
import com.github.yoma.core.domain.CoreAccountCompany;
import com.github.yoma.core.domain.CoreAccountProject;
import com.github.yoma.core.domain.CoreAccountRole;
import com.github.yoma.core.dto.CoreAccountQueryDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 账户信息 业务层
 *
 * @author 马世豪
 * @version 2020-04-07
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class CoreAccountService extends CrudService<CoreAccountDao, CoreAccount> {

    @Autowired
    CoreAccountRoleService accountRoleService;
    @Autowired
    CoreAccountCompanyService accountCompanyService;
    @Autowired
    CoreAccountProjectService accountProjectService;
    @Value("${jwt.codeKey}")
    private String codeKey;
    @Autowired
    private SmsClient smsClient;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisKeyConfig redisKeyConfig;

    @Override
    public CoreAccount get(Long id) {
        return super.get(id);
    }

    public List<CoreAccount> findList(CoreAccountQueryDTO queryDTO) {
        return super.findList(queryDTO);
    }

    public PageInfo<CoreAccount> findPage(CoreAccountQueryDTO queryDTO) {
        return super.findPage(queryDTO);
    }

    @Transactional(readOnly = false)
    public int batchDelete(CoreAccountQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
    }

    // @Cacheable(key = "'loadUserByUsername:'+#p0")
    public CoreAccount findByName(String userName) {
        CoreAccountQueryDTO coreAccountQueryDTO = new CoreAccountQueryDTO();
        CoreAccount user = null;
        if (ValidationUtil.isEmail(userName)) {
        } else {
            coreAccountQueryDTO.setAccountLogin(userName);
            List<CoreAccount> list = this.findList(coreAccountQueryDTO);
            if (list.isEmpty()) {
                throw new EntityNotFoundException(CoreAccount.class, "name", userName);
            } else {
                user = list.get(0);
            }
        }

        if (user == null) {
            throw new EntityNotFoundException(CoreAccount.class, "name", userName);
        } else {
            return user;
        }
    }

    @Transactional(readOnly = false)
    @Override
    public void save(CoreAccount coreAccount) {
        // 只有新增逻辑才会走此方法
        String accountPassOrigin = coreAccount.getAccountPass();
        if (StringUtils.isNotEmpty(accountPassOrigin)) {
            String encryptPassword = EncryptUtils.encryptPassword(accountPassOrigin);
            coreAccount.setAccountPass(encryptPassword);
        } else {
        }
        super.save(coreAccount);
        Long accountId = coreAccount.getId();
        Long companyId = coreAccount.getCompanyId();
        Long projectId = coreAccount.getProjectId();
        Integer roleType = coreAccount.getRoleType();
        List<Long> roleIdList = coreAccount.getRoleIdList();
        ArrayList<CoreAccountRole> accountRoleList = new ArrayList<>();
        ArrayList<CoreAccountCompany> accountCompanyList = new ArrayList<>();
        ArrayList<CoreAccountProject> accountProjectList = new ArrayList<>();
        roleIdList.forEach(roleId -> {
            if (RoleTypeEnum.system.code.equals(roleType)) {
                CoreAccountRole coreAccountRole = new CoreAccountRole();
                coreAccountRole.setAccountId(accountId);
                coreAccountRole.setRoleId(roleId);
                accountRoleList.add(coreAccountRole);
            } else if (RoleTypeEnum.company.code.equals(roleType)) {
                CoreAccountCompany coreAccountCompany = new CoreAccountCompany();
                coreAccountCompany.setAccountId(accountId);
                coreAccountCompany.setRoleId(roleId);
                coreAccountCompany.setCompanyId(companyId);
                accountCompanyList.add(coreAccountCompany);
            } else if (RoleTypeEnum.project.code.equals(roleType)) {
                CoreAccountProject coreAccountProject = new CoreAccountProject();
                coreAccountProject.setAccountId(accountId);
                coreAccountProject.setRoleId(roleId);
                coreAccountProject.setProjectId(projectId);
                accountProjectList.add(coreAccountProject);
            }
        });
        // 删除原有数据&保存新数据
        if (RoleTypeEnum.system.code.equals(roleType)) {
            accountRoleService.batchInsert(accountRoleList);
        } else if (RoleTypeEnum.company.code.equals(roleType)) {
            accountCompanyService.batchInsert(accountCompanyList);
        } else if (RoleTypeEnum.project.code.equals(roleType)) {
            accountProjectService.batchInsert(accountProjectList);
        }
    }

    @Transactional(readOnly = false)
    public void updateSwitchInfo(CoreAccount coreAccount) {
        Long userId = SecurityUtils.getUserId();
        coreAccount.setId(userId);
        super.save(coreAccount);
    }

    public void sendVerifyCode(CoreAccount coreAccount) {
        String accountMobile = coreAccount.getAccountMobile();
        int verificationCode = smsClient.sendVerificationCode("register-verify", accountMobile);
        String keyPre = this.redisKeyConfig.getVerifyCode().getRegister();
        redisTemplate.opsForValue().set(keyPre + accountMobile, String.valueOf(verificationCode), 90, TimeUnit.SECONDS);
    }

    public void sendVerifyCodeForPassWordChange(CoreAccount coreAccount) {
        String accountMobile = coreAccount.getAccountMobile();
        CoreAccountQueryDTO coreAccountQueryDTO = new CoreAccountQueryDTO();
        coreAccountQueryDTO.setAccountMobile(accountMobile);
        List<CoreAccount> list = this.findList(coreAccountQueryDTO);
        if (list.isEmpty()) {
            throw new RuntimeException("手机号对应的账号不存在,请检查是否输错！");
        }
        int verificationCode = smsClient.sendVerificationCode("password-find", accountMobile);
        String keyPre = this.redisKeyConfig.getVerifyCode().getPasswordFind();
        redisTemplate.opsForValue().set(keyPre + accountMobile, String.valueOf(verificationCode), 90, TimeUnit.SECONDS);
    }

    public void sendVerifyCodeForIdentify(CoreAccount coreAccount) {
        String accountMobile = coreAccount.getAccountMobile();
        CoreAccountQueryDTO coreAccountQueryDTO = new CoreAccountQueryDTO();
        coreAccountQueryDTO.setAccountMobile(accountMobile);
        List<CoreAccount> list = this.findList(coreAccountQueryDTO);
        if (list.isEmpty()) {
            throw new RuntimeException("手机号对应的账号不存在,请检查是否输错！");
        }
        int verificationCode = smsClient.sendVerificationCode("identify", accountMobile);
        String keyPre = this.redisKeyConfig.getVerifyCode().getPasswordFind();
        redisTemplate.opsForValue().set(keyPre + accountMobile, String.valueOf(verificationCode), 90, TimeUnit.SECONDS);
    }

    @Transactional(readOnly = false)
    public void signUp(CoreAccount coreAccount) {
        // 验证手机号是否重复
        String accountMobile = coreAccount.getAccountMobile();
        CoreAccountQueryDTO coreAccountQueryDTO = new CoreAccountQueryDTO();
        coreAccountQueryDTO.setAccountMobile(accountMobile);
        List<CoreAccount> list = this.findList(coreAccountQueryDTO);
        if (list.isEmpty()) {
            String verificationCodeDT = coreAccount.getVerificationCode();
            String keyPre = this.redisKeyConfig.getVerifyCode().getRegister();
            // 验证短信验证码有效性
            String verificationCode = (String)redisTemplate.opsForValue().get(keyPre + accountMobile);
            if (verificationCodeDT.equals(verificationCode)) {
                String encryptPassword = EncryptUtils.encryptPassword(coreAccount.getAccountPass());
                coreAccount.setAccountPass(encryptPassword);
                super.save(coreAccount);
                CoreAccountProject coreAccountProject = new CoreAccountProject();
                coreAccountProject.setAccountId(coreAccount.getId());
                coreAccountProject.setRoleId(9L);
                coreAccountProject.setRoleName("项目体验员");
                coreAccountProject.setProjectId(1L);
                this.accountProjectService.save(coreAccountProject);
            } else {
                throw new RuntimeException("短信验证码无效或已过期，请重新验证！");
            }
        } else {
            throw new RuntimeException("手机号已被注册过，请找回密码！");
        }

    }

    public void testAlarmSMS() {
        Map<String, String> map = new HashedMap<>();
        LocalDateTime now = LocalDateTime.now();
        map.put("project", "11");
        map.put("time", now.format(DateConverterConf.dateTimeFormatter));
        map.put("alarm", "a");
        map.put("equipment", "b");
        map.put("analog", "c");
        map.put("value", "d");
        List<String> mobiles = new ArrayList<>();
        mobiles.add("18539270558");

        String msg = "e";
        map.put("detail", msg);
        SmsTemplate smsTemplate = SmsTemplate.builder().phoneNumbers(mobiles).templateParam(map).signName("励科易控")
            .templateCode("SMS_189712502").build();
        smsClient.send(smsTemplate);
    }

    @Transactional(readOnly = false)
    public void updatePassword(CoreAccount coreAccount) {
        String accountMobile = coreAccount.getAccountMobile();
        String verificationCodeDT = coreAccount.getVerificationCode();
        String keyPre = this.redisKeyConfig.getVerifyCode().getPasswordFind();
        CoreAccountQueryDTO coreAccountQueryDTO = new CoreAccountQueryDTO();
        coreAccountQueryDTO.setAccountMobile(accountMobile);
        List<CoreAccount> list = this.findList(coreAccountQueryDTO);
        if (list.isEmpty()) {
            throw new RuntimeException("手机号对应的账号不存在！");
        } else {
            // 验证短信验证码有效性
            String verificationCode = (String)redisTemplate.opsForValue().get(keyPre + accountMobile);
            if (verificationCodeDT.equals(verificationCode)) {
                CoreAccount coreAccountTemp = list.get(0);
                String encryptPassword = EncryptUtils.encryptPassword(coreAccount.getAccountPass());
                coreAccount.setAccountPass(encryptPassword);
                coreAccount.setId(coreAccountTemp.getId());
                super.save(coreAccount);
            } else {
                throw new RuntimeException("短信验证码无效或已过期，请重新验证！");
            }
        }

    }

    @Transactional(readOnly = false)
    public void updatePasswordForAdmin(CoreAccount coreAccount) {
        // 输入账户id，原密码与新密码 验证原密码后更新密码
        CoreAccount coreAccountDetail = this.get(coreAccount);
        String oldPassForDetail = coreAccountDetail.getAccountPass();
        String encryptPasswordForOld = EncryptUtils.encryptPassword(coreAccount.getAccountPass());
        String encryptPasswordForNew = EncryptUtils.encryptPassword(coreAccount.getNewAccountPass());
        if (oldPassForDetail.equals(encryptPasswordForOld)) {
            coreAccount.setAccountPass(encryptPasswordForNew);
            super.save(coreAccount);
        }else {
            throw new RuntimeException("原密码不正确，请重新输入！");
        }

    }

    @Transactional(readOnly = false)
    @Override
    public int delete(CoreAccount coreAccount) {
        return super.delete(coreAccount);
    }

}
