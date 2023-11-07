package com.warmstone.micro.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


/**
 * @author warmstone
 * @date 2023/11/7 21:12
 * @description
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
               .apiInfo(apiInfo())
               .select()
               .apis(RequestHandlerSelectors.basePackage("com.warmstone.micro.*.controller"))
               .paths(PathSelectors.any())
               .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
               .title(applicationName + "接口文档")
               .description(applicationName + "接口文档")
               .version("1.0")
               .build();
    }
}
