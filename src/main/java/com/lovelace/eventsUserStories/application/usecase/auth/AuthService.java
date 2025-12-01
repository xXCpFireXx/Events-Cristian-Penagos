package com.lovelace.eventsUserStories.application.usecase.auth;

import com.lovelace.eventsUserStories.infrastructure.adapters.in.web.dto.AuthRequest;
import com.lovelace.eventsUserStories.infrastructure.adapters.in.web.dto.AuthResponse;
import com.lovelace.eventsUserStories.infrastructure.adapters.in.web.security.JwtService;
import com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.entity.UserEntity;
import com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.repository.ISpringUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ISpringUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(AuthRequest request) {
        var user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // ¡Encriptamos clave!
        user.setRole(request.getRole() != null ? request.getRole() : "USER");

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        // Si llega aquí, es que las credenciales son correctas
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
    }
}