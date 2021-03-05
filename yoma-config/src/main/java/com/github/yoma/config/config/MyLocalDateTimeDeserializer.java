package com.github.yoma.config.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.github.yoma.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author: msh01
 * @create: 2020/7/1
 **/
@Slf4j
public class MyLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    private static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
    @Override
    public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException  {
        String source = jp.getText().trim();
        LocalDateTime date = null;
        try {
            if (StringUtils.isNotBlank(source)) {
                if (StringUtils.isNumeric(source)) {
                    Instant instant = Instant.ofEpochMilli(Long.parseLong(source));
                    ZoneId zone = ZoneId.systemDefault();
                    date = LocalDateTime.ofInstant(instant, zone);
                } else {
                    DateTimeFormatter df = DateTimeFormatter.ofPattern(dateTimeFormat);
                    date = LocalDateTime.parse(source, df);
                }
            }
            return date;
        } catch (NumberFormatException e) {
            log.warn("Unable to deserialize LocalDateTime:" + source, e);
            return null;
        }
    }
    @Override
    public Class<?> handledType() {
        // 关键
        return LocalDateTime.class;
    }
}
