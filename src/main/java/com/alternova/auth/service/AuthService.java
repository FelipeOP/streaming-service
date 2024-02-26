package com.alternova.auth.service;

import com.alternova.auth.dto.AuthResponse;
import com.alternova.auth.dto.LoginRequest;
import com.alternova.auth.dto.RegisterRequest;

public interface AuthService {

    AuthResponse login(LoginRequest loginRequest);

    AuthResponse register(RegisterRequest registerRequest);
}
