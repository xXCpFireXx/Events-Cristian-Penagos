package com.lovelace.eventsUserStories.infrastructure.config;

import com.lovelace.eventsUserStories.application.usecase.event.*;
import com.lovelace.eventsUserStories.application.usecase.venue.*;
import com.lovelace.eventsUserStories.domain.ports.out.EventRepositoryPort;
import com.lovelace.eventsUserStories.domain.ports.out.VenueRepositoryPort;
import com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.repository.ISpringUserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor // Genera el constructor para inyectar userRepository
public class ApplicationConfig {

    // Inyectamos el repositorio para buscar usuarios en la DB
    private final ISpringUserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Eventos ----------------------------------------------------------------
    @Bean
    public CreateEventUseCaseImpl createEventUseCase(EventRepositoryPort eventRepositoryPort, VenueRepositoryPort venueRepositoryPort) {
        return new CreateEventUseCaseImpl(eventRepositoryPort, venueRepositoryPort);
    }

    @Bean
    public GetEventByIdUseCaseImpl getEventByIdUseCase(EventRepositoryPort eventRepositoryPort) {
        return new GetEventByIdUseCaseImpl(eventRepositoryPort);
    }

    @Bean
    public GetAllEventsUseCaseImpl getAllEventsUseCase(EventRepositoryPort eventRepositoryPort) {
        return new GetAllEventsUseCaseImpl(eventRepositoryPort);
    }

    @Bean
    public UpdateEventUseCaseImpl updateEventUseCase(EventRepositoryPort eventRepositoryPort, VenueRepositoryPort venueRepositoryPort) {
        return new UpdateEventUseCaseImpl(eventRepositoryPort, venueRepositoryPort);
    }

    @Bean
    public DeleteEventUseCaseImpl deleteEventUseCase(EventRepositoryPort eventRepositoryPort) {
        return new DeleteEventUseCaseImpl(eventRepositoryPort);
    }

    // Venues ----------------------------------------------------------------
    @Bean
    public CreateVenueUseCaseImpl createVenueUseCase(VenueRepositoryPort venueRepositoryPort) {
        return new CreateVenueUseCaseImpl(venueRepositoryPort);
    }

    @Bean
    public GetVenueByIdUseCaseImpl getVenueByIdUseCase(VenueRepositoryPort venueRepositoryPort) {
        return new GetVenueByIdUseCaseImpl(venueRepositoryPort);
    }

    @Bean
    public GetAllVenuesUseCaseImpl getAllVenuesUseCase(VenueRepositoryPort venueRepositoryPort) {
        return new GetAllVenuesUseCaseImpl(venueRepositoryPort);
    }

    @Bean
    public UpdateVenueUseCaseImpl updateVenueUseCase(VenueRepositoryPort venueRepositoryPort) {
        return new UpdateVenueUseCaseImpl(venueRepositoryPort);
    }

    @Bean
    public DeleteVenueUseCaseImpl deleteVenueUseCase(VenueRepositoryPort venueRepositoryPort) {
        return new DeleteVenueUseCaseImpl(venueRepositoryPort);
    }
}