package com.github.yoma.common.config;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: msh01
 * @create: 2020/7/1
 **/
@Slf4j
public class MyLocalDateTimeToTimstampSerializer extends JsonSerializer<LocalDateTime> {
    private static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

    }
}
