/*
 * Copyright 2019-2020 Zheng Jie
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.github.yoma.base.modules.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.github.yoma.logging.domain.Log;
import com.github.yoma.logging.service.LogService;
import com.github.yoma.base.modules.security.security.AuthInfo;
import com.github.yoma.base.modules.security.security.JwtUser;
import com.github.yoma.common.result.DetailResponse;
import com.github.yoma.common.utils.RequestHolder;
import com.github.yoma.common.utils.SecurityUtils;
import com.github.yoma.common.utils.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Zheng Jie
 * @date 2018-11-24
 */
@Component
@Aspect
@Slf4j
public class LoginLogAspect {

    private final LogService logService;

    ThreadLocal<Long> currentTime = new ThreadLocal<>();

    public LoginLogAspect(LogService logService) {
        this.logService = logService;
    }

    /**
     * 配置切入点
     */
    @Pointcut("execution(public * com.github.yoma.*.security.rest.AuthenticationController.login(..))")
    public void logPointcut() {
        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
    }

    @AfterReturning(returning = "detailResponse", pointcut = "logPointcut()")
    public Object AfterExec(JoinPoint joinPoint, DetailResponse<AuthInfo> detailResponse) {
        // pointcut是对应的注解类 rvt就是方法运行完之后要返回的值
        System.out.println("AfterReturning增强：获取目标方法的返回值：" + detailResponse);
        AuthInfo data = detailResponse.getData();
        JwtUser user = data.getUser();
        currentTime.set(System.currentTimeMillis());
        Log log = new Log("INFO", System.currentTimeMillis() - currentTime.get());
        currentTime.remove();
        HttpServletRequest request = RequestHolder.getHttpServletRequest();
        log.setCreateBy(user.getUser().getId());
        logService.save(user.getUser().getUsername(), StringUtils.getBrowser(request), StringUtils.getIp(request),
            (ProceedingJoinPoint)joinPoint, log);
        // 根据返回值，判断登录是否成功。登录成功则插入一条日志
        return detailResponse;
    }

    public String getUsername() {
        try {
            return SecurityUtils.getUsername();
        } catch (Exception e) {
            return "";
        }
    }
}
