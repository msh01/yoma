package com.github.yoma.config.config;

/**
 * @author msh01
 * @version 1.0.0
 * @createTime 2020年06月23日 11:02:00
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

import static java.util.concurrent.Executors.*;

/**
 * 多线程执行定时任务
 * @author 王久印
 * 2018年3月1日
 */
@Configuration
//所有的定时任务都放在一个线程池中，定时任务启动时使用不同都线程。
public class ScheduleConfig implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        //设定一个长度10的定时任务线程池
        taskRegistrar.setScheduler(newScheduledThreadPool(10));
    }

}
