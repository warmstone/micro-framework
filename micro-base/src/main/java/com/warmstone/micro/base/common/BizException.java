package com.warmstone.micro.base.common;

import com.warmstone.micro.base.common.RetCode;

/**
 * @author warmstone
 * @date 2022-04-06 22:12
 * @description 自定义业务异常
 */
public class BizException extends Exception{

    private Integer code;

    private String message;

    public BizException() {
    }

    public BizException(String message) {
        this.message = message;
    }

    public BizException(RetCode retCode) {
        this.code = retCode.getCode();
        this.message = retCode.getMessage();
    }

    public BizException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
