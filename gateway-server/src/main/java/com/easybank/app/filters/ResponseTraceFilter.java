package com.easybank.app.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class ResponseTraceFilter {

    private final FilterUtility filterUtility;
    private static final Logger log = LoggerFactory.getLogger(ResponseTraceFilter.class);

    public ResponseTraceFilter(FilterUtility filterUtility) {
        this.filterUtility = filterUtility;
    }

    @Bean
    public GlobalFilter postGlobalFilter() {

        return (exchange, chain) ->
                chain.filter(exchange)
                        .then(Mono.fromRunnable(() -> {

                            String correlationId =
                                    exchange.getAttribute(FilterUtility.CORRELATION_ID);

                            if (correlationId != null) {
                                filterUtility.addCorrelationIdToResponse(exchange, correlationId);
                                log.info("Correlation ID added to response: {}", correlationId);
                            } else {
                                log.warn("Correlation ID missing in exchange attributes");
                            }
                        }));
    }
}

