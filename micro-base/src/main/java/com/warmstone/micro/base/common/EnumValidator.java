package com.warmstone.micro.base.common;

import com.warmstone.micro.base.annotation.EnumValid;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author warmstone
 * @date 2023/8/15 20:36
 * @description
 */
@Slf4j
public class EnumValidator implements ConstraintValidator<EnumValid, Integer> {

    private final List<Integer> values = new ArrayList<>();

    @Override
    public void initialize(EnumValid enumValid) {
        Class<?> clazz = enumValid.value();
        Object[] enumConstants = clazz.getEnumConstants();
        try {
            Method method = clazz.getMethod("getCode");
            Integer value = null;
            for (Object enumConstant : enumConstants) {
                value = (Integer) method.invoke(enumConstant);
                values.add(value);
            }
        } catch (Exception e) {
            log.warn("{}注解初始化校验失败", clazz.getName());
        }
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null || values.contains(value);
    }
}
