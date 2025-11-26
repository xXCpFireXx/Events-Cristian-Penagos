package com.lovelace.eventsUserStories.domain.ports.in.eventUseCases;

import com.lovelace.eventsUserStories.domain.model.Event;

import java.util.Optional;

public interface UpdateEventUseCase {
    Optional<Event> updateEvent(Long id, Event event);
}
