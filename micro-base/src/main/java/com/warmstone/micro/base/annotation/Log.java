package com.warmstone.micro.base.annotation;

import java.lang.annotation.*;

/**
 * @author warmstone
 * @date 2023/8/15 19:47
 * @description
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Log {
}
