package com.uxelf.sportpulse.ms_auth.service;

import com.uxelf.sportpulse.ms_auth.dto.login.LoginRequest;
import com.uxelf.sportpulse.ms_auth.dto.login.LoginResponse;
import com.uxelf.sportpulse.ms_auth.dto.register.RegisterRequest;
import com.uxelf.sportpulse.ms_auth.dto.register.RegisterResponse;
import com.uxelf.sportpulse.ms_auth.dto.validate.ValidateResponse;
import com.uxelf.sportpulse.ms_auth.entity.User;
import com.uxelf.sportpulse.ms_auth.enums.Role;
import com.uxelf.sportpulse.ms_auth.exception.ConflictException;
import com.uxelf.sportpulse.ms_auth.exception.UnauthorizedException;
import com.uxelf.sportpulse.ms_auth.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Value("${JWT_EXPIRATION}")
    private long expiration;


    public RegisterResponse register(RegisterRequest request){
        if (userRepository.existsByUsername(request.getUsername())){
            throw new ConflictException("USER_ALREADY_EXISTS", "Username already in use");
        }
        if (userRepository.existsByEmail(request.getEmail())){
            throw new ConflictException("EMAIL_ALREADY_EXISTS", "Email already in use");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        User saved = userRepository.save(user);

        RegisterResponse response = new RegisterResponse();
        response.setId(saved.getId());
        response.setEmail(saved.getEmail());
        response.setUsername(saved.getUsername());
        response.setRole(saved.getRole().name());
        response.setCreatedAt(saved.getCreatedAt());

        return response;
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UnauthorizedException("INVALID_CREDENTIALS", "Wrong email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("INVALID_CREDENTIALS", "Wrong email or password");
        }

        String token = jwtService.generateToken(user);
        return new LoginResponse(token, "Bearer", expiration / 1000, user.getId());
    }

    public ValidateResponse validate(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("TOKEN_MISSING", "Token not provided");
        }

        String token = authHeader.substring(7);
        jwtService.validateToken(token);
        Claims claims = jwtService.extractClaims(token);

        return new ValidateResponse(
                true,
                UUID.fromString(claims.getSubject()),
                claims.get("username", String.class),
                claims.get("role", String.class)
        );
    }
}
