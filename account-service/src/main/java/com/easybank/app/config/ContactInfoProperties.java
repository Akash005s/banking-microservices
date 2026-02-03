package com.easybank.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "contact")
public record ContactInfoProperties(
        String name,
        String email,
        String address,
        List<String> support
) {
}
