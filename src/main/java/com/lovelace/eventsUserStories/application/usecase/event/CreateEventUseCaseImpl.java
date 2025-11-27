package com.lovelace.eventsUserStories.application.usecase.event;

import com.lovelace.eventsUserStories.domain.exception.DuplicateResourceException;
import com.lovelace.eventsUserStories.domain.model.Event;
import com.lovelace.eventsUserStories.domain.ports.in.eventUseCases.CreateEventUseCase;
import com.lovelace.eventsUserStories.domain.ports.out.EventRepositoryPort;
import com.lovelace.eventsUserStories.domain.ports.out.VenueRepositoryPort;
import org.springframework.transaction.annotation.Transactional;


public class CreateEventUseCaseImpl implements CreateEventUseCase {

    private final EventRepositoryPort eventRepositoryPort;
    private final VenueRepositoryPort venueRepositoryPort;

    public CreateEventUseCaseImpl(EventRepositoryPort eventRepositoryPort, VenueRepositoryPort venueRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
        this.venueRepositoryPort = venueRepositoryPort;
    }

    @Override
    @Transactional
    public Event createEvent(Event event) {
        if (eventRepositoryPort.existsByNameEvent(event.getNameEvent())) {
            throw new DuplicateResourceException("Event name " + event.getNameEvent() +" already exists");
        }

        if (!venueRepositoryPort.existsById(event.getIdVenueEvent())) {
            throw new RuntimeException("Venue not found with id: " + event.getIdVenueEvent());
        }

        return eventRepositoryPort.save(event);
    }
}
