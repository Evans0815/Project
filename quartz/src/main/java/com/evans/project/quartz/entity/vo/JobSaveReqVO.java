package com.evans.project.quartz.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author Evans
 * @date 2026/3/17
 */
@Data
public class JobSaveReqVO {

    private Long id;

    @NotEmpty(message = "任务名称不能为空")
    private String name;

    @NotEmpty(message = "处理器的名字不能为空")
    private String handlerName;

    private String handlerParam; // 处理器的参数

    @NotEmpty(message = "CRON 表达式不能为空")
    private String cronExpression;

}
