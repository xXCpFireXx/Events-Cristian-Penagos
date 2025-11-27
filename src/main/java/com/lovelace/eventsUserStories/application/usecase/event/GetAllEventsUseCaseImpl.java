package com.lovelace.eventsUserStories.application.usecase.event;

import com.lovelace.eventsUserStories.domain.model.Event;
import com.lovelace.eventsUserStories.domain.ports.in.eventUseCases.GetAllEventsUseCase;
import com.lovelace.eventsUserStories.domain.ports.out.EventRepositoryPort;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public class GetAllEventsUseCaseImpl implements GetAllEventsUseCase {

    private final EventRepositoryPort eventRepositoryPort;

    public GetAllEventsUseCaseImpl(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

//    @Override
//    @Transactional(readOnly = true)
//    public List<Event> getAllEvents(int page, int size) {
//        return eventRepositoryPort.findAll(page, size);
//    }

    @Override
    @Transactional(readOnly = true) // Optimización de lectura (parte de la HU4)
    public List<Event> getAllEvents(Long venueId, String name) {
        return eventRepositoryPort.findAllWithFilters(venueId, name);
    }
}
