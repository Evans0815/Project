package com.evans.project.quartz.entity.vo;

import com.evans.project.common.enums.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Evans
 * @date 2026/3/17
 */
@Data
public class JobRespVO {

    private Long id;

    private String name;

    private String status;

    private String handlerName;

    private String handlerParam;

    private String cronExpression;

    @JsonFormat(pattern = Constants.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime createTime;

}
