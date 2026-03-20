package com.evans.project.common.exception;

import com.evans.project.common.entity.ErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Evans
 * @date 2026/3/17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceException extends RuntimeException {

    /**
     * 业务错误码
     */
    private Integer code;
    /**
     * 错误提示
     */
    private String message;
    /**
     * 空构造方法，避免反序列化问题
     */
    public ServiceException() {}

    public ServiceException(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMsg();
    }

    public ServiceException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
