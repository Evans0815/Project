package com.evans.project.quartz.config;

import com.evans.project.quartz.scheduler.SchedulerManager;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Optional;

/**
 * @author Evans
 * @date 2026/3/17
 */
@Slf4j
@AutoConfiguration
@EnableScheduling // 开启 Spring 自带的定时任务
public class QuartzAutoConfiguration {

    @Bean
    public SchedulerManager schedulerManager(Optional<Scheduler> scheduler) {
        if (!scheduler.isPresent()) {
            log.info("[定时任务 - 已禁用]");
            return new SchedulerManager(null);
        }
        return new SchedulerManager(scheduler.get());
    }

}
