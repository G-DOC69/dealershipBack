package com.auto.dealership.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AdminSessionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String path = request.getRequestURI();

        if (path.startsWith("/v3/api-docs") || path.startsWith("/swagger-ui") || path.startsWith("/swagger-resources")
                || path.equals("/") || path.equals("/favicon.ico")
                || path.startsWith("/photos") || path.startsWith("/contact")
                || path.startsWith("/cars") || path.startsWith("/events")
                || path.equals("/admin/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (path.startsWith("/admin/")) {
            Boolean isAdmin = (Boolean) request.getSession().getAttribute("isAdmin");
            if (isAdmin == null || !isAdmin) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
