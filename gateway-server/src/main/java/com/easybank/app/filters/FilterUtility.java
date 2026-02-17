package com.easybank.app.filters;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.UUID;

@Component
public class FilterUtility {

    public static final String CORRELATION_ID = "EasyBank-Correlation-Id";

    /** Check if correlation ID exists in request */
    public boolean hasCorrelationId(ServerWebExchange exchange) {
        return exchange.getRequest()
                .getHeaders()
                .containsKey(CORRELATION_ID);
    }

    /** Get correlation ID from request */
    public String getCorrelationId(ServerWebExchange exchange) {
        return exchange.getRequest()
                .getHeaders()
                .getFirst(CORRELATION_ID);
    }

    /** Generate new correlation ID */
    public String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }

    /** Add correlation ID to request headers */
    public ServerWebExchange addCorrelationIdToRequest(
            ServerWebExchange exchange, String correlationId) {

        return exchange.mutate()
                .request(
                        exchange.getRequest()
                                .mutate()
                                .header(CORRELATION_ID, correlationId)
                                .build()
                )
                .build();
    }

    /** Add correlation ID to response headers */
    public void addCorrelationIdToResponse(
            ServerWebExchange exchange, String correlationId) {

        exchange.getResponse()
                .getHeaders()
                .add(CORRELATION_ID, correlationId);
    }
}

