package com.trannubichthai.product_service.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {

        String authHeader = httpServletRequest.getHeader("Authorization");
        if (!validString(authHeader) || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        logHeaders(httpServletRequest);

        String userId = httpServletRequest.getHeader("userId");

        System.out.println("userId: " + userId);

        String authoritiesStr = httpServletRequest.getHeader("authorities");
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        if (validString(authoritiesStr)) {
            authorities = Arrays.stream(authoritiesStr.split(","))
                    .distinct()
                    .filter(this::validString)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(userId, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void logHeaders(HttpServletRequest httpServletRequest) {
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            httpServletRequest.getHeader(header);
            logger.info(String.format("Header: %s --- Value: %s", header, httpServletRequest.getHeader(header)));
        }
    }

    private boolean validString(String input) {
        return input != null && !input.isEmpty();
    }
}
