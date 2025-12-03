package com.lovelace.eventsUserStories.infrastructure.adapters.in.web.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Deshabilitamos CSRF porque usaremos Tokens (Stateless)
                .authorizeHttpRequests(auth -> auth
                        // Rutas PÚBLICAS (Login, Registro, Swagger/OpenAPI si lo usas)
                        .requestMatchers("/auth/**", "/v3/api-docs/**", "/swagger-ui/**", "/h2-console/**", "/actuator/**").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // No guardar sesión en servidor (Stateless)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // Ejecutar filtro JWT antes del filtro estándar

        // Configuración extra necesaria solo para ver la consola H2 (si la usas)
        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
}