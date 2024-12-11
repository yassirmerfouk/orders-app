package com.ym.payment_service.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.List;

public class ApiKeyAuthenticationToken extends AbstractAuthenticationToken {

    private final String apiKey;
    private final String apiSecret;

    public ApiKeyAuthenticationToken(String apiKey, String apiSecret){
        super(List.of());
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return apiSecret;
    }

    @Override
    public Object getPrincipal() {
        return apiKey;
    }
}
