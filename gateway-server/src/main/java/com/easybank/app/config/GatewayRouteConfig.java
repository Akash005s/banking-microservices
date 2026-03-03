package com.easybank.app.config;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * GatewayRouteConfig
 * <p>
 * Centralized routing configuration for EasyBank API Gateway.
 * </p>
 *
 * <p>
 * This configuration adds a common "/easybank" prefix for all public APIs
 * while keeping Spring Cloud Gateway's Discovery Locator enabled.
 * </p>
 *
 * <p>
 * Routing behavior:
 * <ul>
 *   <li>/easybank/accounts/** → Accounts Service</li>
 *   <li>/easybank/loans/**    → Loans Service</li>
 *   <li>/easybank/cards/**    → Cards Service</li>
 * </ul>
 * </p>
 *
 * <p>
 * Internally, the "/easybank" prefix is removed using {@code rewritePath}
 * before forwarding the request to downstream microservices.
 * </p>
 *
 * <p>
 * Route order is set to a higher priority (order = -1) so that these
 * routes are matched before auto-generated Discovery Locator routes.
 * </p>
 *
 * @author Akash
 */
@Configuration
public class GatewayRouteConfig {

    /**
     * Defines custom gateway routes for EasyBank microservices.
     *
     * <p>
     * This {@link RouteLocator} bean configures path-based routing with
     * URL rewriting for Accounts, Loans, and Cards services.
     * </p>
     *
     * @param builder {@link RouteLocatorBuilder} provided by Spring Cloud Gateway
     * @return configured {@link RouteLocator} instance
     */
    @Bean
    public RouteLocator easyBankRoutes(RouteLocatorBuilder builder) {

        return builder.routes()

                /**
                 * Accounts Service Route
                 *
                 * Public URL:
                 *   /easybank/accounts/**
                 *
                 * Forwarded URL:
                 *   /accounts/**
                 */
                .route("easybank-accounts", r -> r
                        .path("/easybank/accounts/**")
                        .filters(f -> f.rewritePath(
                                "/easybank/accounts/(?<segment>.*)",
                                "/${segment}"
                        )
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                                .circuitBreaker(config -> config.setName("accountsCircuitBreaker")
                                        .setFallbackUri("forward:/contact-support"))
                        )
                        .uri("lb://ACCOUNTS")
                )

                /**
                 * Loans Service Route
                 *
                 * Public URL:
                 *   /easybank/loans/**
                 *
                 * Forwarded URL:
                 *   /loans/**
                 */
                .route("easybank-loans", r -> r
                        .path("/easybank/loans/**")
                        .filters(f -> f.rewritePath(
                                "/easybank/loans/(?<segment>.*)",
                                "/${segment}"
                        )
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                                        .retry(retryConfig -> retryConfig.setRetries(3)
                                                .setMethods(HttpMethod.GET)
                                                .setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true)
                                        )
                        )
                        .uri("lb://LOANS")
                )

                /**
                 * Cards Service Route
                 *
                 * Public URL:
                 *   /easybank/cards/**
                 * <p>
                 * Forwarded URL:
                 *   /cards/**
                 */
                .route("easybank-cards", r -> r
                        .path("/easybank/cards/**")
                        .filters(f -> f.rewritePath(
                                "/easybank/cards/(?<segment>.*)",
                                "/${segment}"
                        )
                                        .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                                        .requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter())
                                                .setKeyResolver(userKeyResolver()))
                        )
                        .uri("lb://CARDS")
                )

                .build();
    }

    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id ->
                new Resilience4JConfigBuilder(id)
                        .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                        .timeLimiterConfig(
                                TimeLimiterConfig.custom()
                                        .timeoutDuration(Duration.ofSeconds(4))
                                        .build()
                        )
                        .build()
        );
    }

    @Bean
    public RedisRateLimiter redisRateLimiter() {
        return new RedisRateLimiter(1, 1, 1);
    }

    @Bean
    public KeyResolver userKeyResolver() {
        return exchange -> Mono.justOrEmpty(exchange.getRequest().getQueryParams().getFirst("user"))
                .defaultIfEmpty("anonymous");
    }
}

