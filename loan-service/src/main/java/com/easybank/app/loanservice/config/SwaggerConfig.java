package com.easybank.app.loanservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI loanServiceOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Loan Service API")
                        .description("""
                                Loan Service APIs for EasyBank Microservices Platform.
                                
                                This service handles:
                                - Loan creation
                                - Loan retrieval
                                - Loan payment updates
                                - Loan deletion
                                
                                Built using Spring Boot & OpenAPI 3.
                                """)
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Akash Maurya")
                                .email("akashmaurya49062@gmail.com")
                                .url("https://github.com/Akash005s")
                        )
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")
                        )
                )
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8091")
                                .description("Local Environment")
                ));
    }
}
