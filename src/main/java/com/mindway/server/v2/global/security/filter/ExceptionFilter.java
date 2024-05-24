package com.mindway.server.v2.global.security.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindway.server.v2.global.exception.ErrorCode;
import com.mindway.server.v2.global.exception.ErrorResponse;
import com.mindway.server.v2.global.exception.MindWayException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class ExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    public ExceptionFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(request, response);
        } catch (MindWayException e) {
            sendError(response, e.getErrorCode());
        } catch (Exception e) {
            sendError(response, ErrorCode.INTERNAL_SERVER_ERROR);
            throw e;
        }
    }

    private void sendError(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getStatus(), errorCode.getMessage());
        String responseString = objectMapper.writeValueAsString(errorResponse);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(errorCode.getStatus());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(responseString);
    }
}
