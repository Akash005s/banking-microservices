package com.easybank.app.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Getter
@Setter
@ConfigurationProperties(prefix = "contact")
public class ContactInfoProperties {

    private String name;
    private String email;
    private String address;
    private List<String> support;
}
