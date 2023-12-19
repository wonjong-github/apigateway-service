package com.example.apigatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration
public class FilterConfig {
//    @Bean
//    public RouterFunction<ServerResponse> routeToFirstServce() {
//        return route("first-service").GET("/first-service", http("http://localhost:8081"))
//                .before(addRequestHeader("first-request", "first-request-header"))
//                .after(addResponseHeader("first-response", "first-response-header"))
//                .build();
//    }
//
//    @Bean
//    public RouterFunction<ServerResponse> routeToSecondServce() {
//        return route("second-service").GET("/second-service", http("http://localhost:8082"))
//                .before(addRequestHeader("second-request", "second-request-header"))
//                .after(addResponseHeader("second-response", "second-response-header"))
//                .build();
//    }

//     @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/first-service/**")
                        .filters(f -> f.addRequestHeader("first-request", "first-request-header")
                                .addResponseHeader("first-reponse", "first-response-header"))
                        .uri("http://localhost:8081"))
                .route(r -> r.path("/second-service/**")
                        .filters(f -> f.addRequestHeader("second-request", "second-request-header")
                                .addResponseHeader("second-reponse", "second-response-header"))
                        .uri("http://localhost:8082"))
                .build();
    }
}
