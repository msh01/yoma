package com.github.yoma.base.modules.security.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.github.yoma.core.domain.BaseRole;
import com.github.yoma.core.domain.BaseUser;
import com.github.yoma.core.dto.BaseRoleQueryDTO;
import com.github.yoma.core.service.BaseRoleService;
import com.github.yoma.core.service.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.yoma.core.service.CoreAccountService;
import com.github.yoma.core.service.CoreRoleService;
import com.github.yoma.common.exception.BadRequestException;
import com.github.yoma.base.modules.security.security.JwtUser;
import com.github.yoma.base.modules.system.service.UserService;

/**
 * @author Zheng Jie
 * @date 2018-11-22
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    CoreAccountService coreAccountService;
    @Autowired
    BaseUserService baseUserService;
    @Autowired
    BaseRoleService baseRoleService;
    @Autowired
    CoreRoleService coreRoleService;

    private final JwtPermissionService permissionService;

    public JwtUserDetailsService(UserService userService, JwtPermissionService permissionService) {
        this.userService = userService;
        this.permissionService = permissionService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        BaseUser user = baseUserService.findByName(username);
        JwtUser JwtUser = null;
        if (user == null) {
            throw new BadRequestException("账号不存在");
        } else {
            if (!user.getEnabled()) {
                throw new BadRequestException("账号未激活！");
            }
            ArrayList<Long> deptIds = new ArrayList<>();
            // default time zone
            ZoneId defaultZoneId = ZoneId.systemDefault();
            LocalDate lastPasswordResetLocalDate = LocalDate.of(2020, 2, 2);
            Date lastPasswordResetDate = Date.from(lastPasswordResetLocalDate.atStartOfDay(defaultZoneId).toInstant());

            BaseRoleQueryDTO baseRoleQueryDTO = new BaseRoleQueryDTO();
            baseRoleQueryDTO.setUserId(user.getId());
            List<BaseRole> roles = baseRoleService.findList(baseRoleQueryDTO);
            // List<Long> roleIds = roles.stream().map(BaseRole::getId).collect(Collectors.toList());
            // 防止反序列化时异常
            roles.forEach(x -> {
                x.setCreateTime(null);
                x.setUpdateTime(null);
            });
            user.setRoles(roles);
            JwtUser = new JwtUser(user, deptIds, lastPasswordResetDate, baseRoleService.mapToGrantedAuthorities(user));
            return JwtUser;
        }
    }

    // public UserDetails createJwtUser(BaseUser user) {
    // LocalDateTime now = LocalDateTime.now();
    // // LocalDateTime lastPasswordResetLDate = now.minusDays(50);
    // Timestamp timestamp = Timestamp.valueOf(now);
    //
    // // default time zone
    // ZoneId defaultZoneId = ZoneId.systemDefault();
    //
    // List<GrantedAuthority> grantedAuthorities = baseRoleService.mapToGrantedAuthorities(user);
    //
    // Long accountId = user.getId();
    //
    // return new JwtUser(accountId, user.getUsername(), user.getNickName(), user.getPassword(), "avatar",
    // user.getEmail(), user.getPhone(), "部门1", "职位1", grantedAuthorities, true, timestamp, lastPasswordResetDate);
    // }
}
