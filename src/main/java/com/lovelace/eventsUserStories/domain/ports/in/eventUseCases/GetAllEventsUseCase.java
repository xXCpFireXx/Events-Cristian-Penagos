package com.lovelace.eventsUserStories.domain.ports.in.eventUseCases;

import com.lovelace.eventsUserStories.domain.model.Event;

import java.util.List;

public interface GetAllEventsUseCase {
    //List<Event> getAllEvents(int page, int size);
    List<Event> getAllEvents(Long venueId, String name);
}
