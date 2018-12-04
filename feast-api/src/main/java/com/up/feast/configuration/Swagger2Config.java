package com.up.feast.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Description 整合swagger2配置类
 * @author liub
 * @date 2018-11-28 13:47
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    private static final String VERSION = "1.0.0";

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger API")
                .description("仅供小宝贝使用")
                .termsOfServiceUrl("")
                .version(VERSION)
                .contact(new Contact("liub", "", "672304928@qq.com"))
                .build();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.up.feast"))
                .paths(PathSelectors.any())
                .build();
    }
}
