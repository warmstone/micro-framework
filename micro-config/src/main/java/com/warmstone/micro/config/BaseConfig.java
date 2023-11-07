package com.warmstone.micro.config;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.warmstone.micro.base.constant.AppConstant;
import com.warmstone.micro.config.handler.GlobalExceptionHandler;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

/**
 * @author warmstone
 * @date 2023/3/28 22:42
 * @description
 */
@Configuration
public class BaseConfig {

    /**
     * LocalDate LocalDateTime 全局格式化
     * @return
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            builder.simpleDateFormat(AppConstant.SIMPLE_DATETIME_FORMAT)
                    .serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(AppConstant.SIMPLE_DATE_FORMAT)))
                    .serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(AppConstant.SIMPLE_DATETIME_FORMAT)));
        };
    }

    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }
}
