package com.example.BlogSpot.XianBlogSpot.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("XianBlogSpot API")
                        .description("Documentación de la API de XianBlogSpot")
                        .version("v1.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("Proyecto GitHub")
                        .url("https://github.com/xianDT01/XianBlogSpot"));
    }
}
