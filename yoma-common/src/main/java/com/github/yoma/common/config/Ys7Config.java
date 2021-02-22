package com.github.yoma.common.config;

import java.util.List;
import java.util.Map;

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
public class Ys7Config {

    private String tokenGet;

}
