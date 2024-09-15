package br.com.erudio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("RESTful API  with Java 21 and Spring boot 3.1")
                        .version("v1")
                        .description("Some description abount API")
                        .termsOfService("")
                        .license(new License().name("Apache 2.0").url("")));
    }
}
