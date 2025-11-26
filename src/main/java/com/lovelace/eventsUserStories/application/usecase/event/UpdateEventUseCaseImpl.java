package com.lovelace.eventsUserStories.application.usecase.event;

import com.lovelace.eventsUserStories.domain.model.Event;
import com.lovelace.eventsUserStories.domain.ports.in.eventUseCases.UpdateEventUseCase;
import com.lovelace.eventsUserStories.domain.ports.out.EventRepositoryPort;
import com.lovelace.eventsUserStories.domain.ports.out.VenueRepositoryPort;

import java.util.Optional;

public class UpdateEventUseCaseImpl implements UpdateEventUseCase {

    private final EventRepositoryPort eventRepositoryPort;
    private final VenueRepositoryPort venueRepositoryPort;

    public UpdateEventUseCaseImpl(EventRepositoryPort eventRepositoryPort, VenueRepositoryPort venueRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
        this.venueRepositoryPort = venueRepositoryPort;
    }

    @Override
    public Optional<Event> updateEvent(Long id, Event event) {
        if (!eventRepositoryPort.existsById(id)) {
            return Optional.empty();
        }

        if (!venueRepositoryPort.existsById(event.getIdVenueEvent())) {
            throw new RuntimeException("Venue not found with id: " + event.getIdVenueEvent());
        }

        event.setId(id);
        return Optional.of(eventRepositoryPort.save(event));
    }
}
