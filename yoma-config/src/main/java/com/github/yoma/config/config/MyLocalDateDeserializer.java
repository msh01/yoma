package com.github.yoma.config.config;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class MyLocalDateDeserializer extends JsonDeserializer<LocalDate> {
    private static final String dateFormat = "yyyy-MM-dd";
    @Override
    public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException  {
        String source = jp.getText().trim();
        LocalDate date;
        try {

            if (StringUtils.isNumeric(source)) {
                Instant instant = Instant.ofEpochMilli(Long.parseLong(source));
                ZoneId zone = ZoneId.systemDefault();
                date = LocalDateTime.ofInstant(instant, zone).toLocalDate();
            } else {
                DateTimeFormatter df = DateTimeFormatter.ofPattern(dateFormat);
                date = LocalDate.parse(source, df);
            }
            return date;
        } catch (NumberFormatException e) {
            log.warn("Unable to deserialize LocalDate :" + source, e);
            return null;
        }
    }
    @Override
    public Class<?> handledType() {
        // 关键
        return LocalDate.class;
    }
}
