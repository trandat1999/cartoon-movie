package com.thd.cartoon.service.impl;

import com.thd.cartoon.dto.auth.AuthRequest;
import com.thd.cartoon.dto.auth.AuthResponse;
import com.thd.cartoon.dto.response.BaseResponse;
import com.thd.cartoon.entity.Token;
import com.thd.cartoon.entity.User;
import com.thd.cartoon.repository.TokenRepository;
import com.thd.cartoon.repository.UserRepository;
import com.thd.cartoon.service.AuthService;
import com.thd.cartoon.service.JwtService;
import com.thd.cartoon.type.TokenType;
import com.thd.cartoon.util.SystemMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;

/**
 * @author DatNuclear 16/01/2024
 * @project cartoon-movie
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl extends BaseService implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    @Override
    public BaseResponse login(AuthRequest request) {
        HashMap<String, String> validations = validation(request);
        if(!CollectionUtils.isEmpty(validations)){
            return getResponse400(getMessage(SystemMessage.BAD_REQUEST),validations);
        }
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        var token = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllTokenAndRefreshToken(user.getUsername());
        saveToken(user.getUsername(), token);
        saveRefreshToken(user.getUsername(),refreshToken);
        AuthResponse authResponse = AuthResponse.builder()
                .refreshToken(refreshToken)
                .accessToken(token).build();
        return getResponse200(authResponse,getMessage(SystemMessage.SUCCESS));
    }
    private void saveToken(String username, String jwtToken) {
        var token = Token.builder()
                .username(username)
                .token(jwtToken)
                .type(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
    private void saveRefreshToken(String username, String jwtToken) {
        var token = Token.builder()
                .username(username)
                .token(jwtToken)
                .type(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
    private void revokeAllTokenAndRefreshToken(String username) {

    }
}
