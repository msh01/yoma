package com.github.yoma.config.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author msh01
 * @version 1.0.0
 * @createTime 2020年04月12日 00:59:00 感谢 https://gdpotter.com/2017/05/24/custom-spring-mvc-jackson/
 */
@EnableWebMvc
@Configuration
// public class WebMvcConfig extends WebMvcConfigurerAdapter {
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        ObjectMapper webObjectMapper = objectMapper.copy();
        // webObjectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        converters.add(new MappingJackson2HttpMessageConverter(webObjectMapper));
    }
}
