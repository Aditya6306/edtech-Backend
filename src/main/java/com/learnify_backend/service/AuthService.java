package com.learnify_backend.service;

import com.learnify_backend.dto.JwtAuthResponse;
import com.learnify_backend.dto.LoginDto;
import com.learnify_backend.dto.RegisterDto;

public interface AuthService {
    public String register(RegisterDto dto);
    public JwtAuthResponse login(LoginDto dto);
    public void sendOtp(String email);
}
