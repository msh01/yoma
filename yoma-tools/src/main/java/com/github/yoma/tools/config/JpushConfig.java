package com.github.yoma.tools.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author msh01
 * @version 1.0.0
 * @createTime 2020年05月18日 00:18:00
 */
@Component("jpushConfig")
@ConfigurationProperties(prefix = "jpush")
@Data
public class JpushConfig {

    /**
     * 极光推送的用户名
     */
    private String appkey;
    /**
     * 极光推送的密码
     */
    private String masterSecret;
    /**
     * 极光推送设置过期时间
     */
    private String liveTime;
}
