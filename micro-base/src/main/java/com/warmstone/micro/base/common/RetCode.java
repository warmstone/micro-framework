package com.warmstone.micro.base.common;

/**
 * @author warmstone
 * @date 2022-04-06 21:50
 * @description 定义返回状态码
 */
public enum RetCode {

    SUCCESS(20000, "成功"),
    FAIL(-1, "失败"),
    NOT_FOUND(40400, "访问的资源不存在"),
    UNAUTHORIZED(40100, "未认证"),
    PERMISSION_DENIED(40300, "权限不足"),
    LOGIN_FAILED(40500, "用户名或者密码错误"),
    LOGIN_EXPIRED(40600, "登录失效")
    ;

    private Integer code;

    private String message;

    RetCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
