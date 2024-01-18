package com.thd.cartoon.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thd.cartoon.dto.response.BaseResponse;
import com.thd.cartoon.dto.response.ErrorResponse;
import com.thd.cartoon.util.SystemMessage;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;



/**
 * @author DatNuclear 6/6/2023
 * @project NuclearShop
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint, AccessDeniedHandler {
    @Resource
    private MessageSource messageSource;
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        if(httpServletResponse.getStatus()==200){
            httpServletResponse.setContentType("application/json");
            httpServletResponse.setStatus(401);
            BaseResponse errorResponse = BaseResponse.builder()
                    .status(HttpStatus.UNAUTHORIZED.name())
                    .code(HttpStatus.UNAUTHORIZED.value())
                    .message(HttpStatus.UNAUTHORIZED.name())
                    .build();
            OutputStream outputStream = httpServletResponse.getOutputStream();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(outputStream, errorResponse);
            outputStream.flush();
        }
    }

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setStatus(403);
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.FORBIDDEN)
                .timestamp(LocalDateTime.now())
//                .message(messageSource.getMessage(MESSAGE_FORBIDDEN,null, LocaleContextHolder.getLocale()))
                .build();
        OutputStream outputStream = httpServletResponse.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(outputStream, errorResponse);
        outputStream.flush();
    }

//    @Override
//    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//        response.setContentType("application/json");
//        response.setStatus(401);
//        ErrorResponse errorResponse = ErrorResponse.builder()
//                .status(HttpStatus.UNAUTHORIZED)
////                .timestamp(LocalDateTime.now())
////                .message(messageSource.getMessage(MESSAGE_UNAUTHORIZED,null, LocaleContextHolder.getLocale()))
//                .build();
//        OutputStream outputStream = response.getOutputStream();
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.writeValue(outputStream, errorResponse);
//        outputStream.flush();
//    }
}
