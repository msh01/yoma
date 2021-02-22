package com.github.yoma.base.modules.security.rest;

import javax.servlet.http.HttpServletRequest;

import cn.hutool.core.util.IdUtil;
import com.github.yoma.core.service.BaseUserService;
import com.github.yoma.common.exception.BadRequestException;
import com.github.yoma.base.modules.security.security.ImgResult;
import com.github.yoma.common.utils.StringUtils;
import com.wf.captcha.ArithmeticCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.github.yoma.common.annotation.AnonymousAccess;
import com.github.yoma.core.domain.CoreAccount;
import com.github.yoma.core.service.CoreAccountService;
import com.github.yoma.base.modules.monitor.service.RedisService;
import com.github.yoma.base.modules.security.security.AuthInfo;
import com.github.yoma.base.modules.security.security.AuthUser;
import com.github.yoma.base.modules.security.security.JwtUser;
import com.github.yoma.base.modules.security.service.OnlineUserService;
import com.github.yoma.base.modules.security.utils.JwtTokenUtil;
import com.github.yoma.common.result.CommonResponse;
import com.github.yoma.common.result.DetailResponse;
import com.github.yoma.common.result.ResponseUtil;
import com.github.yoma.common.utils.SecurityUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Zheng Jie
 * @date 2018-11-23 授权、根据token获取用户详细信息
 */
@Slf4j
@RestController
@RequestMapping("/auth")
@Api(tags = "登录授权")
public class AuthenticationController {

    private final PasswordEncoder passwordEncoder;
    @Value("${jwt.codeKey}")
    private String codeKey;
    @Autowired
    private CoreAccountService coreAccountService;
    @Autowired
    private BaseUserService baseUserService;

    private final JwtTokenUtil jwtTokenUtil;

    private final RedisService redisService;

    private final UserDetailsService userDetailsService;

    private final OnlineUserService onlineUserService;

    public AuthenticationController(PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil,
        RedisService redisService, @Qualifier("jwtUserDetailsService") UserDetailsService userDetailsService,
        OnlineUserService onlineUserService) {
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
        this.redisService = redisService;
        this.userDetailsService = userDetailsService;
        this.onlineUserService = onlineUserService;
    }

    // @ApiOperation("登录授权")
    // @AnonymousAccess
    // @PostMapping(value = "/login")
    // public ResponseEntity login(@Validated @RequestBody AuthUser authUser, HttpServletRequest request) {
    //
    // // 查询验证码
    // // String code = redisService.getCodeVal(authUser.getUuid());
    // // 清除验证码
    // // redisService.delete(authUser.getUuid());
    // // if (StringUtils.isBlank(code)) {
    // // throw new BadRequestException("验证码已过期");
    // // }
    // // if (StringUtils.isBlank(authUser.getCode()) || !authUser.getCode().equalsIgnoreCase(code)) {
    // // throw new BadRequestException("验证码错误");
    // // }
    // final JwtUser jwtUser = (JwtUser)userDetailsService.loadUserByUsername(authUser.getUsername());
    //
    // if (!jwtUser.getPassword().equals(EncryptUtils.encryptPassword(authUser.getPassword()))) {
    // throw new AccountExpiredException("密码错误");
    // }
    //
    // if (!jwtUser.isEnabled()) {
    // throw new AccountExpiredException("账号已停用，请联系管理员");
    // }
    // // 生成令牌
    // final String token = jwtTokenUtil.generateToken(jwtUser);
    // // 保存在线信息
    // onlineUserService.save(jwtUser, token, request);
    // // 返回 token
    // return ResponseEntity.ok(new AuthInfo(token, jwtUser));
    // }
    @ApiOperation("登录授权")
    @AnonymousAccess
    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@Validated @RequestBody AuthUser authUser, HttpServletRequest request) {

        // 查询验证码
        String code = redisService.getCodeVal(authUser.getUuid());
        // 清除验证码
        redisService.delete(authUser.getUuid());
        if (StringUtils.isBlank(code)) {
            throw new BadRequestException("验证码已过期");
        }
        if (StringUtils.isBlank(authUser.getCode()) || !authUser.getCode().equalsIgnoreCase(code)) {
            throw new BadRequestException("验证码错误");
        }
        final JwtUser jwtUser = (JwtUser)userDetailsService.loadUserByUsername(authUser.getUsername());
        // String encode = passwordEncoder.encode("123456");if(!passwordEncoder.matches(oldPass, user.getPassword())){
        // throw new BadRequestException("修改失败，旧密码错误");
        // }

        if (!jwtUser.isEnabled()) {
            throw new AccountExpiredException("账号已停用，请联系管理员");
        }
        // 生成令牌
        final String token = jwtTokenUtil.generateToken(jwtUser);
        // todo 保存最后一次登录ip和登录时间
        // 保存在线信息
        onlineUserService.save(jwtUser, token, request);
        // 返回 token
        AuthInfo authInfo = new AuthInfo("Bearer "+token, jwtUser);
        DetailResponse<AuthInfo> detailResponse = ResponseUtil.detailSuccess(authInfo);
        return ResponseEntity.ok(authInfo);
    }

    // @ApiOperation("获取用户信息")
    // @GetMapping(value = "/info")
    // public ResponseEntity getUserInfo() {
    // String username = SecurityUtils.getUsername();
    // JwtUser jwtUser = (JwtUser)userDetailsService.loadUserByUsername(username);
    // return ResponseEntity.ok(jwtUser);
    // }
    // @ApiOperation("获取用户信息")
    // @GetMapping(value = "/info")
    // // @AnonymousAccess
    // public DetailResponse<JwtUser> getUserInfo() {
    // String username = SecurityUtils.getUsername();
    // JwtUser jwtUser = (JwtUser)userDetailsService.loadUserByUsername(username);
    // DetailResponse<JwtUser> detailResponse = ResponseUtil.detailSuccess(jwtUser);
    // return detailResponse;
    // }

    @ApiOperation("获取用户信息")
    @AnonymousAccess
    @GetMapping(value = "/info")
    public ResponseEntity<Object> getUserInfo() {
        return ResponseEntity.ok(SecurityUtils.getCurrentUser());
    }

    /**
     * 注册-获取短信验证码接口
     */
    @ApiOperation(value = "注册-获取短信验证码接口")
    @PostMapping("/register/getVerifyCode")
    @AnonymousAccess
    public CommonResponse<CoreAccount> getVerifyCode(@RequestBody CoreAccount coreAccount) {
        this.coreAccountService.sendVerifyCode(coreAccount);
        CommonResponse<CoreAccount> success = ResponseUtil.detailSuccess(coreAccount);
        return success;
    }

    /**
     * 注册-获取短信验证码接口
     */
    @ApiOperation(value = "修改密码-获取短信验证码接口")
    @PostMapping("/updatePassword/getVerifyCode")
    @AnonymousAccess
    public CommonResponse<CoreAccount> sendVerifyCodeForPassWordChange(@RequestBody CoreAccount coreAccount) {
        this.coreAccountService.sendVerifyCodeForPassWordChange(coreAccount);
        CommonResponse<CoreAccount> success = ResponseUtil.detailSuccess(coreAccount);
        return success;
    }

    @ApiOperation(value = "身份验证-获取短信验证码接口")
    @PostMapping("/identity/getVerifyCode")
    @AnonymousAccess
    public CommonResponse<CoreAccount> sendVerifyCodeForIdentity(@RequestBody CoreAccount coreAccount) {
        this.coreAccountService.sendVerifyCodeForIdentify(coreAccount);
        CommonResponse<CoreAccount> success = ResponseUtil.detailSuccess(coreAccount);
        return success;
    }

    /**
     * 注册
     */
    @ApiOperation(value = "注册")
    @PostMapping("/signUp")
    @AnonymousAccess
    public CommonResponse<CoreAccount> signUp(@RequestBody CoreAccount coreAccount) {
        coreAccountService.signUp(coreAccount);
        CommonResponse<CoreAccount> success = ResponseUtil.detailSuccess(coreAccount);
        return success;
    }

    /**
     * 注册
     */
    @ApiOperation(value = "客户端发起的修改密码，需短信验证")
    @PostMapping("/updatePassword")
    @AnonymousAccess
    public CommonResponse<CoreAccount> updatePassword(@RequestBody CoreAccount coreAccount) {
        coreAccountService.updatePassword(coreAccount);
        CommonResponse<CoreAccount> success = ResponseUtil.detailSuccess(coreAccount);
        return success;
    }

    /**
     * 注册
     */
    @ApiOperation(value = "操作后台修改密码，输入账户id，原密码与新密码 验证原密码后更新密码")
    @PostMapping("/admin/updatePassword")
    @AnonymousAccess
    public CommonResponse<CoreAccount> updatePasswordOnAdmin(@RequestBody CoreAccount coreAccount) {
        coreAccountService.updatePasswordForAdmin(coreAccount);
        CommonResponse<CoreAccount> success = ResponseUtil.detailSuccess(coreAccount);
        return success;
    }

    /**
     * 注册
     */
    @ApiOperation(value = "测试短信发送")
    @PostMapping("/test/sms/alarm")
    @AnonymousAccess
    public CommonResponse<CoreAccount> testsms(@RequestBody CoreAccount coreAccount) {

        coreAccountService.testAlarmSMS();
        CommonResponse<CoreAccount> success = ResponseUtil.detailSuccess(coreAccount);
        return success;
    }

    @ApiOperation("获取验证码")
    @AnonymousAccess
    @GetMapping(value = "/code")
    public ImgResult getCode() {
        // 算术类型 https://gitee.com/whvse/EasyCaptcha
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(111, 36);
        // 几位数运算，默认是两位
        captcha.setLen(2);
        // 获取运算的结果：5
        String result = captcha.text();
        String uuid = codeKey + IdUtil.simpleUUID();
        redisService.saveCode(uuid, result);
        return new ImgResult(captcha.toBase64(), uuid);
    }

    @ApiOperation("退出登录")
    @AnonymousAccess
    @DeleteMapping(value = "/logout")
    public ResponseEntity logout(HttpServletRequest request) {
        onlineUserService.logout(jwtTokenUtil.getToken(request));
        return new ResponseEntity(HttpStatus.OK);
    }
}
