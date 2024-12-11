package com.ym.order_service.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Value("${api.key}")
    private String apiKey;
    @Value("${api.secret}")
    private String apiSecret;

    @Bean
    public RequestInterceptor requestInterceptor(){
        return requestTemplate -> {
            requestTemplate.header("X-API-KEY", apiKey);
            requestTemplate.header("X-API-SECRET", apiSecret);
        };
    }
}
