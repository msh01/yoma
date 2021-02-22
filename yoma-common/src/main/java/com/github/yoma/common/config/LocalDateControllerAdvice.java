package com.github.yoma.common.config;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * 应对get请求时候发送的local date类型数据
 *
 * @author msh01
 * @version 1.0.0
 * @createTime 2020年12月04日 14:44:00
 */
@ControllerAdvice
public class LocalDateControllerAdvice {
    private static final String dateFormat = "yyyy-MM-dd";
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);

    @InitBinder
    public void initBinder(WebDataBinder binder) {

        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {

                LocalDate.parse(text, dateTimeFormatter);
            }
        });
    }
}
