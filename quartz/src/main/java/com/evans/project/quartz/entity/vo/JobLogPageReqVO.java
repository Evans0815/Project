package com.evans.project.quartz.entity.vo;

import com.evans.project.common.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.evans.project.common.enums.Constants.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;


/**
 * @author Evans
 * @date 2026/3/17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class JobLogPageReqVO extends PageParam {

    private Long jobId;

    private String handlerName;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime beginTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime endTime;

    private Integer status;

}
