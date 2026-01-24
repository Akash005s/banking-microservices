package com.easybank.app.loanservice;

import com.easybank.app.loanservice.config.AuditorAwareConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class LoanServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanServiceApplication.class, args);
    }

}
