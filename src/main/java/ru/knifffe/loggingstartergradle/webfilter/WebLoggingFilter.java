package ru.knifffe.loggingstartergradle.webfilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;
import ru.knifffe.loggingstartergradle.service.LoggingService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class WebLoggingFilter extends HttpFilter {


    @Autowired
    private LoggingService loggingService;

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        loggingService.logRequest(request);

        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        try {
            super.doFilter(request, responseWrapper, chain);
            String responseBody = "body=" + new String(responseWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);
            loggingService.logResponse(request, response, responseBody);
        } finally {
            responseWrapper.copyBodyToResponse();
        }
    }

}
