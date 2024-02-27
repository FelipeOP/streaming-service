package com.alternova.auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alternova.auth.dto.AuthResponse;
import com.alternova.auth.dto.LoginRequest;
import com.alternova.auth.dto.RegisterRequest;
import com.alternova.auth.persistence.constants.Role;
import com.alternova.auth.persistence.entity.User;
import com.alternova.auth.persistence.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        User userDetails = userRepository
                .findByEmail(loginRequest.getEmail())
                .orElseThrow();

        String token = jwtService.getToken(userDetails);
        return new AuthResponse(token, userDetails.getId());
    }

    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
        return new AuthResponse(jwtService.getToken(user), user.getId());
    }

}
