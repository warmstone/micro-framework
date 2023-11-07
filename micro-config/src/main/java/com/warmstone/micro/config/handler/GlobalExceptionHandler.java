package com.warmstone.micro.config.handler;

import com.warmstone.micro.base.common.BizException;
import com.warmstone.micro.base.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author warmstone
 * @date 2022-04-06 22:16
 * @description 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 请求参数校验异常处理
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
        log.error("请求参数校验错误",  exception);
        List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
        String errorMsg = errorList.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(","));
        return Result.fail(errorMsg);
    }

    /**
     * 请求参数序列化错误处理
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public Result<String> httpMessageNotReadableException(HttpMessageNotReadableException exception) {
        log.error("请求参数序列化错误", exception);
        return Result.fail(exception.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public Result<String> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        log.error("参数类型错误", exception);
        return Result.fail("参数类型错误，参数名" + exception.getName());
    }

    @ExceptionHandler(value = BindException.class)
    public Result<String> bindExceptionHandler(BindException exception) {
        log.error("参数类型错误", exception);
        return Result.fail("参数类型错误");
    }

    @ExceptionHandler(BizException.class)
    public Result<String> bizExceptionHandler(BizException e) {
        log.error("捕获业务异常", e);
        return Result.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<String> exceptionHandler(Exception e) {
        log.error("捕获未定义异常:", e);
        return Result.fail(e.getMessage());
    }


}
