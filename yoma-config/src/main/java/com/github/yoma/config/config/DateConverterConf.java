package com.github.yoma.common.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

/**
 * 根据传入的字符串类型，动态的转换为LocalDateTime LocalDate LocalTime
 */
// @EnableWebMvc
@Configuration
public class DateConverterConf {

    public static final String dateFormat = "yyyy-MM-dd";
    public static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
    public static final String timeFormat = "HH:mm:ss";
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormat);

    /**
     * 返回json的日期处理
     *
     * @return
     */
    @Bean(name = "objectMapper")
    @Primary
    public ObjectMapper getObjectMapper() {
        ObjectMapper om = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormat);
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(timeFormat);
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(timeFormatter));

        javaTimeModule.addDeserializer(LocalDateTime.class, new MyLocalDateTimeDeserializer());

        om.registerModule(javaTimeModule);
        // 用于解决java.time 模块的时间序列化为json时变成数组的问题
        om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // 空字符会被反序列化为null，而不是""
        om.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        // 用于解决Unrecognized field, not marked as ignorable 异常
        om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return om;
    }

    // -------------以下三种coverter之前起作用，但在本项目中不起作用（具体原因待定位，可能是依赖引入方式或依赖版本），所以干脆换了种方式，
    // 采用extends JsonDeserializer 的方式。感谢 https://my.oschina.net/eqshen/blog/3106716
    // /**
    // * 配置LocalDateTime的参数自动注入
    // *
    // * @author msh01
    // * @param []
    // * @return: org.springframework.core.convert.converter.Converter<java.lang.String, java.time.LocalDateTime>
    // * @date: 2020/4/6
    // **/
    // @Bean
    // public Converter<String, LocalDateTime> LocalDateTimeConvert() {
    // return new Converter<String, LocalDateTime>() {
    // @Override
    // public LocalDateTime convert(String source) {
    //
    // // 根据字符串类型，将其转换为 LocalDateTime
    // LocalDateTime date = null;
    // try {
    // if (StringUtils.isNotBlank(source)) {
    // if (StringUtils.isNumeric(source)) {
    // Instant instant = Instant.ofEpochMilli(Long.parseLong(source));
    // ZoneId zone = ZoneId.systemDefault();
    // date = LocalDateTime.ofInstant(instant, zone);
    // } else {
    // DateTimeFormatter df = DateTimeFormatter.ofPattern(dateTimeFormat);
    // date = LocalDateTime.parse(source, df);
    // }
    // }
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // return date;
    // }
    // };
    // }
    //
    // /**
    // * 配置LocalDate的参数注入
    // *
    // * @author msh01
    // * @param []
    // * @return: org.springframework.core.convert.converter.Converter<java.lang.String, java.time.LocalDate>
    // * @date: 2020/4/6
    // **/
    // @Bean
    // public Converter<String, LocalDate> LocalDateConvert() {
    // return new Converter<String, LocalDate>() {
    // @Override
    // public LocalDate convert(String source) {
    //
    // LocalDate date = null;
    // try {
    // if (StringUtils.isNotBlank(source)) {
    // if (StringUtils.isNumeric(source)) {
    // Instant instant = Instant.ofEpochMilli(Long.parseLong(source));
    // ZoneId zone = ZoneId.systemDefault();
    // date = LocalDateTime.ofInstant(instant, zone).toLocalDate();
    // } else {
    // DateTimeFormatter df = DateTimeFormatter.ofPattern(dateFormat);
    // date = LocalDate.parse(source, df);
    // }
    // }
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // return date;
    // }
    // };
    // }
    //
    // /**
    // * 配置LocalTime的spring mvc参数注入
    // *
    // * @return
    // */
    // @Bean
    // public Converter<String, LocalTime> LocalTimeConvert() {
    // return new Converter<String, LocalTime>() {
    // @Override
    // public LocalTime convert(String source) {
    //
    // LocalTime time = null;
    // try {
    // if (StringUtils.isNotBlank(source)) {
    // if (StringUtils.isNumeric(source)) {
    // Instant instant = Instant.ofEpochMilli(Long.parseLong(source));
    // ZoneId zone = ZoneId.systemDefault();
    // time = LocalDateTime.ofInstant(instant, zone).toLocalTime();
    // } else {
    // DateTimeFormatter df = DateTimeFormatter.ofPattern(timeFormat);
    // time = LocalTime.parse(source, df);
    // }
    // }
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // return time;
    // }
    // };
    // }
}
