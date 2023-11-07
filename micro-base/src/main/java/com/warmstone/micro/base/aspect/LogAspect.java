package com.warmstone.micro.base.aspect;

import com.warmstone.micro.base.util.GsonUtil;
import lombok.Data;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author warmstone
 * @date 2022-04-10 17:20
 * @description
 */
@Aspect
@Configuration
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 日志切点
     */
    @Pointcut(value = "execution(public * com.warmstone.micro.*.controller.*.*(..))||@annotation(com.warmstone.micro.base.annotation.Log)")
    public void log() {}

    @Around(value = "log()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            logger.info("获取请求上下文失败，当前执行方法：{}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
        HttpServletRequest request = requestAttributes.getRequest();
        LogEntity logEntity = buildLogEntity(request, joinPoint);
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        logEntity.setTimeConsuming(endTime - logEntity.getStartTime());
        logger.info(GsonUtil.format(logEntity));
        return result;
    }

    private LogEntity buildLogEntity(HttpServletRequest request, ProceedingJoinPoint joinPoint) {
        LogEntity logEntity = new LogEntity();
        logEntity.setUrl(request.getRequestURL().toString());
        logEntity.setHttpMethod(request.getMethod());
        logEntity.setClassMethod(String.format("%s.%s", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName()));
        logEntity.setIp(request.getRemoteAddr());
        logEntity.setRequestParam(joinPoint.getArgs());
        logEntity.setStartTime(System.currentTimeMillis());
        return logEntity;
    }

    @Data
    private class LogEntity {
        private String url;
        private String httpMethod;
        private String classMethod;
        private String ip;
        private Object[] requestParam;
        private String responseResult;
        private Long startTime;
        private Long timeConsuming;
    }
}
