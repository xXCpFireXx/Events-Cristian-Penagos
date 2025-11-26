package com.lovelace.eventsUserStories.domain.ports.in.eventUseCases;

import com.lovelace.eventsUserStories.domain.model.Event;

public interface CreateEventUseCase {
    Event createEvent(Event event);
}
