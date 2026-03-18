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
public class JobLogRespVO {

    private Long id;

    private Long jobId;

    private String handlerName;

    private String handlerParam;

    @JsonFormat(pattern = Constants.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime beginTime;

    @JsonFormat(pattern = Constants.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime endTime;

    private Integer duration;

    private Integer status;

    private String result;

    @JsonFormat(pattern = Constants.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime createTime;

}
