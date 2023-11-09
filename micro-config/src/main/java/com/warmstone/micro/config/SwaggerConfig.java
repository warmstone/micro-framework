package com.warmstone.micro.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;


/**
 * @author warmstone
 * @date 2023/11/7 21:12
 * @description
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {

    @Value("${spring.application.name:micro-service}")
    private String applicationName;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
               .apiInfo(apiInfo())
               .select()
               .apis(RequestHandlerSelectors.any())
               .paths(PathSelectors.any())
               .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
               .title(applicationName + "接口文档")
               .description(applicationName + "接口文档")
               .version("1.0")
               .build();
    }

    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> schemeList = new ArrayList<>();
        ApiKey securityScheme = new ApiKey("Authorization", "Authorization", "header");
        schemeList.add(securityScheme);
        return schemeList;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> contextList = new ArrayList<>();
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", scopes()));
        contextList.add(SecurityContext
                .builder()
                .securityReferences(securityReferences)
                .build());
        return contextList;
    }

    private AuthorizationScope[] scopes() {
        return new AuthorizationScope[]{new AuthorizationScope("global", "accessAnything")};
    }
}
