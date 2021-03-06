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
package com.github.yoma.logging.service.impl;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.yoma.logging.domain.Log;
import com.github.yoma.logging.repository.LogRepository;
import com.github.yoma.logging.service.LogService;
import com.github.yoma.logging.service.dto.LogQueryCriteria;
import com.github.yoma.logging.service.mapstruct.LogErrorMapper;
import com.github.yoma.logging.service.mapstruct.LogSmallMapper;
import com.github.yoma.common.utils.*;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * @author Zheng Jie
 * @date 2018-11-24
 */
@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;
    private final LogErrorMapper logErrorMapper;
    private final LogSmallMapper logSmallMapper;

    @Override
    public Object queryAll(LogQueryCriteria criteria, Pageable pageable) {
        Page<Log> page =
            logRepository.findAll(((root, criteriaQuery, cb) -> QueryHelp.getPredicate(root, criteria, cb)), pageable);
        String status = "ERROR";
        if (status.equals(criteria.getLogType())) {
            return PageUtil.toPage(page.map(logErrorMapper::toDto));
        }
        return page;
    }

    @Override
    public List<Log> queryAll(LogQueryCriteria criteria) {
        return logRepository.findAll(((root, criteriaQuery, cb) -> QueryHelp.getPredicate(root, criteria, cb)));
    }

    @Override
    public Object queryAllByUser(LogQueryCriteria criteria, Pageable pageable) {
        Page<Log> page =
            logRepository.findAll(((root, criteriaQuery, cb) -> QueryHelp.getPredicate(root, criteria, cb)), pageable);
        return PageUtil.toPage(page.map(logSmallMapper::toDto));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(String accountName, String browser, String ip, ProceedingJoinPoint joinPoint, Log log) {

        if (joinPoint != null) {
        }
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        // com.github.yoma.logging.annotation.Log aopLog =
        // method.getAnnotation(com.github.yoma.logging.annotation.Log.class);
        // 方法路径
        String methodName = joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()";
        log.setMethod(methodName);
        // added by msh 2020101 想从swagger里面拉取当前的方法说明，当前类的说明，免得用log全部注释一遍
        Class<?> declaringClass = method.getDeclaringClass();
        Api api = declaringClass.getAnnotation(Api.class);
        String target = api.tags()[0];
        log.setTarget(target);
        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
        String operation = apiOperation.value();
        log.setOperation(operation);
        // 描述
        if (log != null) {
            // log.setDescription(aopLog.value());
            log.setDescription(target + ":" + operation);
        }

        StringBuilder params = new StringBuilder("{");
        // 参数值
        List<Object> argValues = new ArrayList<>(Arrays.asList(joinPoint.getArgs()));
        // 参数名称
        for (Object argValue : argValues) {
            params.append(argValue).append(" ");
        }
        log.setParams(params.toString() + " }");


        assert log != null;
        log.setRequestIp(ip);

        String loginPath = "login";
        Long userId = -1L;
        if (loginPath.equals(signature.getName())) {
        } else {
        }
        log.setAddress(StringUtils.getCityInfo(log.getRequestIp()));

        log.setAccountName(accountName);
        log.setBrowser(browser);
        logRepository.save(log);
    }

    @Override
    public Object findByErrDetail(Long id) {
        Log log = logRepository.findById(id).orElseGet(Log::new);
        ValidationUtil.isNull(log.getId(), "Log", "id", id);
        byte[] details = log.getExceptionDetail();
        return Dict.create().set("exception", new String(ObjectUtil.isNotNull(details) ? details : "".getBytes()));
    }

    @Override
    public void download(List<Log> logs, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (Log log : logs) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("用户名", log.getAccountName());
            map.put("IP", log.getRequestIp());
            map.put("IP来源", log.getAddress());
            map.put("描述", log.getDescription());
            map.put("浏览器", log.getBrowser());
            map.put("请求耗时/毫秒", log.getTime());
            map.put("异常详情",
                new String(ObjectUtil.isNotNull(log.getExceptionDetail()) ? log.getExceptionDetail() : "".getBytes()));
            map.put("创建日期", log.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delAllByError() {
        logRepository.deleteByLogType("ERROR");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delAllByInfo() {
        logRepository.deleteByLogType("INFO");
    }
}
