package com.evans.project.quartz.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Evans
 * @date 2026/3/17
 */
@Getter
@AllArgsConstructor
public enum JobLogStatusEnum {

    RUNNING(0), // 运行中
    SUCCESS(1), // 成功
    FAILURE(2); // 失败

    /**
     * 状态
     */
    private final Integer status;

}
