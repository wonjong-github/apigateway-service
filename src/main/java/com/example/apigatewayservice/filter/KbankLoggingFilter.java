package com.example.apigatewayservice.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class KbankLoggingFilter extends AbstractGatewayFilterFactory<KbankLoggingFilter.Config>{

    public KbankLoggingFilter(){
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
//        return (exchange, chain) -> {
//            ServerHttpRequest request = exchange.getRequest();
//            ServerHttpResponse response = exchange.getResponse();
//
//            log.info("Global filter baseMessage: request id -> {}", config.getBaseMessage());
//
//            if (config.isPreLogger()) {
//                log.info("Global Filter start: request id -> {}", request.getId());
//            }
//
//            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//                if (config.isPostLogger()) {
//                    log.info("Global Filter end: reponse code -> {}", response.getStatusCode());
//                }
//            }));
//        };

        GatewayFilter filter = new OrderedGatewayFilter((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Logging filter baseMessage: request id -> {}", config.getBaseMessage());

            if (config.isPreLogger()) {
                log.info("Logging PRE Filter  : request id -> {}", request.getId());
            }

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                if (config.isPostLogger()) {
                    log.info("Logging POST Filter: reponse code -> {}", response.getStatusCode());
                }
            }));
        }, Ordered.LOWEST_PRECEDENCE);

        return filter;
    }

    @Data
    public static class Config {
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
        
    }   
}
