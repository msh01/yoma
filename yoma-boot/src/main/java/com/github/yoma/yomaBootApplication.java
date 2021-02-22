package com.github.yoma;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.yoma.common.utils.SpringContextHolder;

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
@EnableAsync
@MapperScan({"com.github.yoma.**.dao"})
public class yomaBootApplication {



    public static void main(String[] args) {
        SpringApplication.run(yomaBootApplication.class, args);
    }

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }





}
