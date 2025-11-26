package com.lovelace.eventsUserStories.domain.ports.in.eventUseCases;

import com.lovelace.eventsUserStories.domain.model.Event;

import java.util.Optional;

public interface GetEventByIdUseCase {
    Optional<Event> getEventById(Long id);
}
