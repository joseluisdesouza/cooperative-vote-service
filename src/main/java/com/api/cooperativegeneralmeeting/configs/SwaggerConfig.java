package com.api.cooperativegeneralmeeting.configs;

import com.api.cooperativegeneralmeeting.dtos.ScheduleDto;
import com.api.cooperativegeneralmeeting.dtos.VoteDto;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket scheduleApi(TypeResolver typeResolver) {
        return new Docket(DocumentationType.SWAGGER_2)
                .additionalModels(
                        typeResolver.resolve(ScheduleDto.class),
                        typeResolver.resolve(VoteDto.class)
                )
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.api.cooperativegeneralmeeting"))
                .paths(PathSelectors.regex("/schedule.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "Schedule API REST",
                "API REST de votação em pautas.",
                "1.0",
                "Terms of Service",
                new Contact("José Souza", "https://www.linkedin.com/in/joseluisdesouza/",
                        "joseluisdesouzaa@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html", new ArrayList<VendorExtension>()
        );

        return apiInfo;
    }
}
