package com.example.sms.interceptor;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

public class ContentCachingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingResponseWrapper wrapperResponse = new ContentCachingResponseWrapper(response);
        ContentCachingRequestWrapper wrapperRequest = new ContentCachingRequestWrapper(request);
        filterChain.doFilter(wrapperRequest,wrapperResponse);
        wrapperResponse.copyBodyToResponse();
    }
}
