package com.lovelace.eventsUserStories.infrastructure.config;

import com.lovelace.eventsUserStories.application.usecase.event.*;
import com.lovelace.eventsUserStories.application.usecase.venue.CreateVenueUseCaseImpl;
import com.lovelace.eventsUserStories.application.usecase.venue.GetAllVenuesUseCaseImpl;
import com.lovelace.eventsUserStories.application.usecase.venue.GetVenueByIdUseCaseImpl;
import com.lovelace.eventsUserStories.domain.ports.out.EventRepositoryPort;
import com.lovelace.eventsUserStories.domain.ports.out.VenueRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

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

    // Venue --------------------------------------------------
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
    public CreateVenueUseCaseImpl updateVenueUseCase(VenueRepositoryPort venueRepositoryPort) {
        return new CreateVenueUseCaseImpl(venueRepositoryPort);
    }

    @Bean
    public CreateVenueUseCaseImpl deleteVenueUseCase(VenueRepositoryPort venueRepositoryPort) {
        return new CreateVenueUseCaseImpl(venueRepositoryPort);
    }
}
