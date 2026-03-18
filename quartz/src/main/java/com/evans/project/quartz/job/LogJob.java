package com.evans.project.quartz.job;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.evans.project.common.enums.Constants;
import com.evans.project.quartz.handler.JobHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Evans
 * @date 2026/3/18
 */
@Slf4j
@Component
public class LogJob implements JobHandler {

    @Override
    public String execute(String param) throws Exception {
        log.info("参数:{}, 执行时间:{}]", param, LocalDateTimeUtil.format(LocalDateTime.now(), Constants.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND));
        return "success";
    }

}
