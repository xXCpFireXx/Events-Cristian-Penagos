package com.lovelace.eventsUserStories.application.usecase.event;

import com.lovelace.eventsUserStories.domain.model.Event;
import com.lovelace.eventsUserStories.domain.ports.in.eventUseCases.GetEventByIdUseCase;
import com.lovelace.eventsUserStories.domain.ports.out.EventRepositoryPort;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

public class GetEventByIdUseCaseImpl implements GetEventByIdUseCase {
    private final EventRepositoryPort eventRepositoryPort;

    public GetEventByIdUseCaseImpl(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Event> getEventById(Long id) {
        return eventRepositoryPort.findById(id);
    }
}
