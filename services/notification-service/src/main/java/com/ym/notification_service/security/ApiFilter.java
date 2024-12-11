package com.ym.notification_service.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ApiFilter extends OncePerRequestFilter {

    private final SecurityService securityService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        try{
            Authentication authentication = securityService.authenticate(request);
            securityService.setAuthenticationInContext(authentication);
            filterChain.doFilter(request, response);
        }catch (AuthenticationServiceException | AuthorizationServiceException exception){
            response.setStatus(exception instanceof AuthenticationServiceException ? 401 : 403);
        }
    }
}
