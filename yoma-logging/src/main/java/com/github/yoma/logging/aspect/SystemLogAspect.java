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
package com.github.yoma.logging.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.github.yoma.logging.domain.Log;
import com.github.yoma.logging.service.LogService;
import com.github.yoma.common.utils.RequestHolder;
import com.github.yoma.common.utils.SecurityUtils;
import com.github.yoma.common.utils.StringUtils;
import com.github.yoma.common.utils.ThrowableUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Zheng Jie
 * @date 2018-11-24
 */
@Component
@Aspect
@Slf4j
public class SystemLogAspect {

    private final LogService logService;

    ThreadLocal<Long> currentTime = new ThreadLocal<>();

    public SystemLogAspect(LogService logService) {
        this.logService = logService;
    }

    /**
     * 配置切入点
     */
    // @Pointcut("@annotation(com.github.yoma.logging.annotation.Log)")
    @Pointcut("execution(public * com.github.yoma.*.controller.*.*save*(..))||execution(public * com.github.yoma.*.controller.*.*delete*(..))"
        + "||execution(public * com.github.yoma.*.controller.*.*release*(..))||execution(public * com.github.yoma.*.controller.*.*generate*(..))"
        + "||execution(public * com.github.yoma.*.controller.*.*trigger*(..))||execution(public * com.github.yoma.*.security.rest.AuthenticationController.updatePassword(..))"
        + "||execution(public * com.github.yoma.*.controller.*.*create*(..))")
    public void logPointcut() {
        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
    }

    /**
     * 配置环绕通知,使用在方法logPointcut()上注册的切入点
     *
     * @param joinPoint
     *            join point for advice
     */
    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        currentTime.set(System.currentTimeMillis());
        result = joinPoint.proceed();
        Log log = new Log("INFO", System.currentTimeMillis() - currentTime.get());
        log.setResult(result);
        currentTime.remove();
        HttpServletRequest request = RequestHolder.getHttpServletRequest();
        Long userId = getUserId();
        log.setCreateBy(userId);
        logService.save(getAccountName(), StringUtils.getBrowser(request), StringUtils.getIp(request), joinPoint, log);
        return result;
    }

    /**
     * 配置异常通知
     *
     * @param joinPoint
     *            join point for advice
     * @param e
     *            exception
     */
    @AfterThrowing(pointcut = "logPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        long l = System.currentTimeMillis();
        Long aLong = currentTime.get();
        long time = 0L;
        if (aLong != null) {
            time = l - aLong;
        }
        Log log = new Log("ERROR", time);
        currentTime.remove();
        log.setExceptionDetail(ThrowableUtil.getStackTrace(e).getBytes());
        HttpServletRequest request = RequestHolder.getHttpServletRequest();
        Long userId = getUserId();
        log.setCreateBy(userId);
        logService.save(getAccountName(), StringUtils.getBrowser(request), StringUtils.getIp(request),
            (ProceedingJoinPoint)joinPoint, log);
    }

    public String getUsername() {
        try {
            return SecurityUtils.getUsername();
        } catch (Exception e) {
            return "";
        }
    }

    public String getAccountName() {
        try {
            return SecurityUtils.getAccountName();
        } catch (Exception e) {
            return "";
        }
    }

    public Long getUserId() {
        try {
            return SecurityUtils.getUserId();
        } catch (Exception e) {
            return -1L;
        }
    }
}
