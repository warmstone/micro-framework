package com.warmstone.micro.base.common;

import lombok.Data;

/**
 * @author warmstone
 * @date 2022-04-06 21:46
 * @description 统一返回对象
 */
@Data
public class Result<T>{

    /**
     * 返回状态码
     */
    private Integer code;

    /**
     * 返回提示信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    private Result() {
    }

    private Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回
     * @return 成功结果
     */
    public static <T> Result<T> ok() {
        return ok(null, RetCode.SUCCESS.getMessage());
    }

    /**
     * 成功返回
     * @param data 数据
     * @return 成功结果
     */
    public static <T> Result<T> ok(T data) {
        return ok(data, RetCode.SUCCESS.getMessage());
    }


    /**
     * 成功返回数据+提示信息
     * @param data 数据
     * @param message 提示信息
     * @return 成功结果
     */
    public static <T> Result<T> ok(T data, String message) {
        return ok(RetCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 成功返回状态码+数据+提示信息
     * @param code 状态码
     * @param message 提示信息
     * @param data 数据
     * @return 成功结果
     */
    public static <T> Result<T> ok(Integer code, String message, T data) {
        return new Result<>(code, message, data);
    }

    /**
     * 失败返回提示信息
     * @param message 提示信息
     * @return 失败提示
     */
    public static <T> Result<T> fail(String message) {
        return fail(RetCode.FAIL.getCode(), message, null);
    }

    /**
     * 失败返回状态码+提示信息
     * @param code 状态码
     * @param message 提示信息
     * @return 失败结果
     */
    public static <T> Result<T> fail(Integer code, String message) {
        return fail(code, message, null);
    }

    /**
     * 失败返回状态码+数据+提示信息+总数
     * @param code 状态码
     * @param message 提示信息
     * @param data 数据
     * @return 错误返回
     */
    public static <T> Result<T> fail(Integer code, String message, T data) {
        return new Result<>(code, message, data);
    }
}
