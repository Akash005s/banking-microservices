package com.easybank.app.cardservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI cardServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Card Service API")
                        .description("Card Service for EasyBank - Handles card creation, update, fetch and deletion")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Akash Maurya")
                                .email("akash@example.com")
                                .url("https://github.com/akash005s"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
}
