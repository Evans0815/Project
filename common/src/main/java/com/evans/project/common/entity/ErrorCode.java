package com.evans.project.common.entity;

import lombok.Data;

/**
 * @author Evans
 * @date 2026/3/17
 */
@Data
public class ErrorCode {

    /**
     * 错误码
     */
    private final Integer code;
    /**
     * 错误提示
     */
    private final String msg;

    public ErrorCode(Integer code, String message) {
        this.code = code;
        this.msg = message;
    }

}
