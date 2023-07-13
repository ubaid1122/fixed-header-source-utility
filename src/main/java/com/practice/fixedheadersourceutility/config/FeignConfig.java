package com.practice.fixedheadersourceutility.config;

import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ubaid khanzada
 * @since 1.0
 */
@Configuration
@EnableFeignClients(basePackages = "com.practice.fixedheadersourceutility")
public class FeignConfig {

    private static final String AUTHORIZATION_HEADER = "Authorization";


    @Value("${jwt.token}")
    private String JWT_TOKEN;

    /**
     * This method provides bean to decode feign errors
     *
     * @return errorDecoder
     */
    @Bean
    public ErrorDecoder errorDecoder() {
        return new ErrorDecoder.Default();
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header(AUTHORIZATION_HEADER, "Bearer "+JWT_TOKEN);
        };
    }

}
