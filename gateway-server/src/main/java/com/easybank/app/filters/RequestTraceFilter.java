package com.easybank.app.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
@Order(1)
public class RequestTraceFilter implements GlobalFilter {

    private final FilterUtility filterUtility;
    private static final Logger log = LoggerFactory.getLogger(RequestTraceFilter.class);

    public RequestTraceFilter(FilterUtility filterUtility) {
        this.filterUtility = filterUtility;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {

        String correlationId;

        if (filterUtility.hasCorrelationId(exchange)) {
            correlationId = filterUtility.getCorrelationId(exchange);
            log.info("Incoming request already has Correlation ID: {}", correlationId);
        } else {
            correlationId = filterUtility.generateCorrelationId();
            exchange = filterUtility.addCorrelationIdToRequest(exchange, correlationId);
            log.info("Generated new Correlation ID: {}", correlationId);
        }

        exchange.getAttributes()
                .put(FilterUtility.CORRELATION_ID, correlationId);

        log.debug("Request path={}, method={}, correlationId={}",
                exchange.getRequest().getURI().getPath(),
                exchange.getRequest().getMethod(),
                correlationId);

        return chain.filter(exchange);
    }
}

