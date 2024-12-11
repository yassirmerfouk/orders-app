package com.ym.notification_service.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    @Value("${api.key}")
    private String apiKey;
    @Value("${api.secret}")
    private String apiSecret;

    public Authentication authenticate(HttpServletRequest request){
        String key = request.getHeader("X-API-KEY");
        String secret = request.getHeader("X-API-SECRET");
        if(key == null || secret == null)
            throw new AuthenticationServiceException("API Key and Secret are missed!");
        else if(!key.equals(apiKey) || !secret.equals(apiSecret))
            throw new AuthorizationServiceException("API Key and Secret are not accepted!");
        return new ApiKeyAuthenticationToken(apiKey, apiSecret);
    }

    public void setAuthenticationInContext(Authentication authentication){
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
