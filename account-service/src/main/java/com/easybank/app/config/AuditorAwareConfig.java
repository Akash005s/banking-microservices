package com.easybank.app.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Provides current logged-in user for auditing.
 */
@Component("auditorAware")
public class AuditorAwareConfig implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("SYSTEM");
    }
}

