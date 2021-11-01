package com.identity.auth;

import com.identity.utils.JsonWebToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {
    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";

    private boolean validateToken(HttpServletRequest request) {
        String authenticationHeader = request.getHeader(HEADER);
        if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX))
            return false;

        String token = authenticationHeader.substring(PREFIX.length());

        return JsonWebToken.validateJwt(token);
    }

    private boolean validateRole(HttpServletRequest request) {
        return true;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!this.validateToken(request) || !this.validateRole(request)) {
            response.setStatus(401);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
