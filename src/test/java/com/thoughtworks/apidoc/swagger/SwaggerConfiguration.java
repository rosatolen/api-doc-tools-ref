package com.thoughtworks.apidoc.swagger;

import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

import static com.google.common.base.Predicates.in;
import static com.google.common.base.Predicates.not;
import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@TestConfiguration
public class SwaggerConfiguration {

    @Bean
    public Docket docket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                //prevent generation of model definitions for types that are serialized as strings
                .directModelSubstitute(OffsetDateTime.class, String.class)
                .directModelSubstitute(UUID.class, String.class)
                .select()
                //ignore actuator endpoints
                .paths(regex("\\/(?!.*actuator|metrics|autoconfig|beans|configprops|dump|env|health|heapdump|info|mappings|trace|error).*"))
                .build();
        return docket;
    }

//    @Bean
//    public Docket unAuthenticatedDocket() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .useDefaultResponseMessages(false)
//                .groupName("unauthenticated-endpoints")
//                .apiInfo(apiInfo())
//                .select()
//                .paths(regex("^/(profiles|publicProfiles/.*)"))
//                .build();
//    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("DPS Swagger Reference Implementation API").description("Exactly what you think it is").build();
    }
}
