package com.github.yoma.config.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * (业务实现)
 *
 * @author 马世豪
 * @date 2020/1/26 21:28.
 */
@Data
@Slf4j
@Component
@ConfigurationProperties(prefix = "api")
public class OpenAPIConfig {

    private Ys7Config ys7;

}
