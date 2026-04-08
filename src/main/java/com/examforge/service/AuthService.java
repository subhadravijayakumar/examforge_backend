package com.examforge.service;

import com.examforge.dto.*;
import com.examforge.model.User;
import com.examforge.repository.UserRepository;
import com.examforge.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder encoder;

    // =========================
    // 🔥 REGISTER METHOD
    // =========================
    public AuthResponse register(RegisterRequest req) {

        // ✅ Check if email already exists
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        // ✅ Create new user with default values
        User user = User.builder()
                .name(req.getName())
                .email(req.getEmail())
                .password(encoder.encode(req.getPassword()))
                .role(User.Role.valueOf(req.getRole().toUpperCase()))
                .className(req.getClassName())

                // 🔥 IMPORTANT FIX (new user starts fresh)
                .xp(0)
                .level(1)
                .streak(0)
                .examsTaken(0)
                .avgScore(0)
                .badges(0)

                .build();

        // ✅ Save user
        userRepository.save(user);

        // ✅ Generate JWT token
        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getRole().name()
        );

        // ✅ Return response (with userId)
        return new AuthResponse(
                token,
                user.getName(),
                user.getEmail(),
                user.getRole().name(),
                user.getId()
        );
    }

    // =========================
    // 🔥 LOGIN METHOD
    // =========================
    public AuthResponse login(LoginRequest req) {

        // ✅ Find user
        User user = userRepository
                .findByEmail(req.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        // ✅ Validate password
        if (!encoder.matches(
                req.getPassword(),
                user.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        // ✅ Generate JWT token
        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getRole().name()
        );

        // ✅ Return response (with userId)
        return new AuthResponse(
                token,
                user.getName(),
                user.getEmail(),
                user.getRole().name(),
                user.getId()
        );
    }
}