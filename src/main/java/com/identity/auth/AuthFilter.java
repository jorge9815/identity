package com.identity.auth;

import com.identity.annotations.Anonymous;
import com.identity.utils.JsonWebToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class AuthFilter extends OncePerRequestFilter {
    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";

    @Autowired
    private List<HandlerMapping> handlerMappings;

    private boolean validateToken(HttpServletRequest request) {
        try {
            var controllerObject = getHandlerMapping(request);
            var method = controllerObject.getMethod();

            if (method.isAnnotationPresent(Anonymous.class)) {
                return true;
            }
            String authenticationHeader = request.getHeader(HEADER);
            if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX))
                return false;

            String token = authenticationHeader.substring(PREFIX.length());

            return JsonWebToken.validateJwt(token);
        } catch (Exception e) {
            log.error("error: {}", e.getMessage());
            return false;
        }
    }

    private boolean validateRole(HttpServletRequest request) {
        return true;
    }

    private HandlerMethod getHandlerMapping(HttpServletRequest request) throws Exception {
        for (HandlerMapping handlerMapping : handlerMappings) {
            HandlerExecutionChain handlerExecutionChain = handlerMapping.getHandler(request);
            if (handlerExecutionChain != null) {
                return (HandlerMethod) handlerExecutionChain.getHandler();
            }
        }
        return null;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        if (!this.validateToken(request) || !this.validateRole(request)) {
            response.setStatus(401);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
