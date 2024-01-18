package com.thd.cartoon.exception;

import com.thd.cartoon.dto.response.BaseResponse;
import com.thd.cartoon.util.SystemMessage;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

/**
 * @author DatNuclear 17/01/2024
 * @project cartoon-movie
 */
@RestControllerAdvice
@Slf4j
public class CustomException extends ResponseEntityExceptionHandler {
    @Resource
    private MessageSource messageSource;
    @ExceptionHandler({CredentialsExpiredException.class,BadCredentialsException.class,AccessDeniedException.class,
            AccessDeniedException.class, AuthenticationException.class,
            UsernameNotFoundException.class,SignatureException.class,ExpiredJwtException.class})
    public  ResponseEntity<BaseResponse> handleSecurityException(Exception exception) {
        BaseResponse errorDetail = BaseResponse.builder()
                .timestamp(LocalDateTime.now())
                .build();
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        log.error(exception.getMessage());
        if (exception instanceof CredentialsExpiredException) {
            //User is expired
            errorDetail.setMessage(messageSource.getMessage(SystemMessage.USER_CREDENTIALS_IS_EXPIRED,null, LocaleContextHolder.getLocale()));
        }
        if (exception instanceof LockedException) {
            //User is locked
            errorDetail.setMessage(messageSource.getMessage(SystemMessage.USER_IS_LOCKED,null, LocaleContextHolder.getLocale()));
        }
        if (exception instanceof AccountExpiredException) {
            //User is expired
            errorDetail.setMessage(messageSource.getMessage(SystemMessage.USER_IS_EXPIRED,null, LocaleContextHolder.getLocale()));
        }
        if (exception instanceof DisabledException) {
            //User is disabled
            errorDetail.setMessage(messageSource.getMessage(SystemMessage.USER_IS_DISABLE,null, LocaleContextHolder.getLocale()));
        }
        if (exception instanceof BadCredentialsException) {
            httpStatus = HttpStatus.BAD_REQUEST;
            //User is expired
            errorDetail.setMessage(messageSource.getMessage(SystemMessage.USER_BAD_CREDENTIALS,null, LocaleContextHolder.getLocale()));
        }
        errorDetail.setCode(httpStatus.value());
        errorDetail.setStatus(httpStatus.name());
        return ResponseEntity.status(httpStatus).body(errorDetail);
    }
    @ExceptionHandler({Exception.class})
    public final ResponseEntity<BaseResponse> handleAllExceptions(Exception ex, WebRequest request) {
        log.error(ex.getMessage());
        BaseResponse error = BaseResponse.builder()
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
