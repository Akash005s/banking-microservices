package com.easybank.app.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI accountServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("EasyBank - Account Service API")
                        .description("""
                                Account Service handles:
                                - Customer onboarding
                                - Account creation
                                - Fetching account details
                                - Account validations
                                """)
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Akash Maurya")
                                .email("akashmaurya49062@gmail.com")
                                .url("https://github.com/Akash005s"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
}
