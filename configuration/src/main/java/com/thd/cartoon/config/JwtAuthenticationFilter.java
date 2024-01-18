package com.thd.cartoon.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thd.cartoon.dto.response.BaseResponse;
import com.thd.cartoon.repository.TokenRepository;
import com.thd.cartoon.service.JwtService;
import com.thd.cartoon.util.SystemMessage;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtProvider;
    private final UserDetailsServiceImpl userDetailsService;
    private final TokenRepository tokenRepository;
    private final MessageSource messageSource;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String jwt = getJwtFromRequest(request);
        if(StringUtils.hasText(jwt)){
            try {
                String username = jwtProvider.getUsernameFromToken(jwt);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                    var isTokenValid = tokenRepository.findByToken(jwt)
                            .map(t -> !t.isExpired() && !t.isRevoked())
                            .orElse(false);
                    if (jwtProvider.isValidToken(jwt, userDetails) && isTokenValid) {
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                        authToken.setDetails(
                                new WebAuthenticationDetailsSource().buildDetails(request)
                        );
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
            }catch(Exception e){
                ObjectMapper mapper = new ObjectMapper();
                BaseResponse errorDetail = BaseResponse.builder()
                        .build();
                HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
                log.error(e.getMessage());
                if (e instanceof ExpiredJwtException) {
                    //User is expired
                    errorDetail.setMessage(messageSource.getMessage(SystemMessage.JWT_IS_EXPIRED,null, LocaleContextHolder.getLocale()));
                }
                errorDetail.setCode(httpStatus.value());
                errorDetail.setStatus(httpStatus.name());
                response.setStatus(httpStatus.value());
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(mapper.writeValueAsString(errorDetail));
            }

        }
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest arg0) {
        String token = arg0.getHeader("Authorization");
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }

}
