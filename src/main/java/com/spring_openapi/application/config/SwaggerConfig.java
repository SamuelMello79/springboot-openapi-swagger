package com.spring_openapi.application.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de pessoas")
                        .version("1.5")
                        .description("API para gerenciamento de pessoas")
                        .contact(new Contact()
                                .name("Suporte API")
                                .email("example@corp.com.br")
                                .url("mellodeveloper797@github.com")
                        )
                );
    }
}
