package com.github.yoma.codegen.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 本地代码生成的个性化配置
 *
 * @author 马世豪
 * @date 2021/3/26 21:28
 */
@Data
@Slf4j
@Component
@ConfigurationProperties(prefix = "generate")
public class LocalGenerateConfig {

    private Boolean camelCaseEnable;
    private Boolean jpaEnable;
    private Boolean swaggerEnable;
    private String generateTargetDir;

}
