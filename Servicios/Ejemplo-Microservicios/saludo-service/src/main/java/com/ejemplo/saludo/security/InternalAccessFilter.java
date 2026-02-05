package com.ejemplo.saludo.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class InternalAccessFilter extends OncePerRequestFilter {

    // 🔹 Leemos los valores del application.properties
    @Value("${security.internal.header}")
    private String internalHeader;

    @Value("${security.internal.value}")
    private String internalValue;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String headerValue = request.getHeader(internalHeader);

        if (headerValue == null || !headerValue.equals(internalValue)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Acceso denegado: solo peticiones internas permitidas");
            return;
        }

        filterChain.doFilter(request, response);
    }
}