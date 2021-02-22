package com.github.yoma.common.config;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.github.yoma.common.utils.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: msh01
 * @create: 2020/7/1
 **/
@Slf4j
public class MyLocalTimeDeserializer extends JsonDeserializer<LocalTime> {
    private static final String timeFormat = "HH:mm:ss";
    @Override
    public LocalTime deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException  {
        String source = jp.getText().trim();
        LocalTime time = null;
        try {

            if (StringUtils.isNotBlank(source)) {
                if (StringUtils.isNumeric(source)) {
                    Instant instant = Instant.ofEpochMilli(Long.parseLong(source));
                    ZoneId zone = ZoneId.systemDefault();
                    time = LocalDateTime.ofInstant(instant, zone).toLocalTime();
                } else {
                    DateTimeFormatter df = DateTimeFormatter.ofPattern(timeFormat);
                    time = LocalTime.parse(source, df);
                }
            }
            return time;
        } catch (NumberFormatException e) {
            log.warn("Unable to deserialize LocalTime :" + source, e);
            return null;
        }
    }
    @Override
    public Class<?> handledType() {
        // 关键
        return LocalTime.class;
    }
}
