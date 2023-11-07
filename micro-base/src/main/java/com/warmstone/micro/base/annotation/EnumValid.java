package com.warmstone.micro.base.annotation;

import com.warmstone.micro.base.common.EnumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author warmstone
 * @date 2023/8/15 20:33
 * @description
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumValidator.class)
public @interface EnumValid {

    Class<?> value();

    String message() default "The input parameter value is not in the correct enumeration";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
