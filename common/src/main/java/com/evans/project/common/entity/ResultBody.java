package com.evans.project.common.entity;

import com.evans.project.common.enums.StatusCode;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一API响应结果封装
 *
 * @author Evans
 * @date 2026/3/11
 */
@Data
public class ResultBody<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private int code;

    /**
     * 消息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();

    /**
     * 成功标志
     */
    private boolean success = true;

    public ResultBody() {}

    public ResultBody(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.success = code >= 200 && code < 300;
    }

    /**
     * 成功返回
     */
    public static <T> ResultBody<T> ok() {
        return new ResultBody<>(StatusCode.OK.value(), StatusCode.OK.getReasonPhrase(), null);
    }

    public static <T> ResultBody<T> ok(T data) {
        return new ResultBody<>(StatusCode.OK.value(), StatusCode.OK.getReasonPhrase(), data);
    }

    public static <T> ResultBody<T> ok(String message, T data) {
        return new ResultBody<>(StatusCode.OK.value(), message, data);
    }

    /**
     * 成功但无内容
     */
    public static <T> ResultBody<T> noContent() {
        return new ResultBody<>(StatusCode.NO_CONTENT.value(), StatusCode.NO_CONTENT.getReasonPhrase(), null);
    }

    /**
     * 失败返回
     */
    public static <T> ResultBody<T> error(String message) {
        return new ResultBody<>(StatusCode.INTERNAL_SERVER_ERROR.value(), message, null);
    }

    public static <T> ResultBody<T> error(int code, String message) {
        return new ResultBody<>(code, message, null);
    }

    public static <T> ResultBody<T> error(StatusCode status) {
        return new ResultBody<>(status.value(), status.getReasonPhrase(), null);
    }

    public static <T> ResultBody<T> error(StatusCode status, String message) {
        return new ResultBody<>(status.value(), message, null);
    }

    /**
     * 客户端错误
     */
    public static <T> ResultBody<T> badRequest(String message) {
        return new ResultBody<>(StatusCode.BAD_REQUEST.value(), message, null);
    }

    public static <T> ResultBody<T> unauthorized(String message) {
        return new ResultBody<>(StatusCode.UNAUTHORIZED.value(), message, null);
    }

    public static <T> ResultBody<T> forbidden(String message) {
        return new ResultBody<>(StatusCode.FORBIDDEN.value(), message, null);
    }

    public static <T> ResultBody<T> notFound(String message) {
        return new ResultBody<>(StatusCode.NOT_FOUND.value(), message, null);
    }

    /**
     * 快捷方法判断是否成功
     */
    public boolean isSuccess() {
        return success;
    }

}