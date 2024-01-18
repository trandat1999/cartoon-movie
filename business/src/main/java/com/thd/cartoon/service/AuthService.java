package com.thd.cartoon.service;

import com.thd.cartoon.dto.auth.AuthRequest;
import com.thd.cartoon.dto.response.BaseResponse;

/**
 * @author DatNuclear 16/01/2024
 * @project cartoon-movie
 */
public interface AuthService {
    BaseResponse login(AuthRequest request);
}
