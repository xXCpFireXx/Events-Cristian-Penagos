package com.lovelace.eventsUserStories.application.usecase.event;

import com.lovelace.eventsUserStories.domain.exception.ResourceNotFoundException;
import com.lovelace.eventsUserStories.domain.ports.in.eventUseCases.DeleteEventUseCase;
import com.lovelace.eventsUserStories.domain.ports.out.EventRepositoryPort;

public class DeleteEventUseCaseImpl implements DeleteEventUseCase {

    private final EventRepositoryPort eventRepositoryPort;

    public DeleteEventUseCaseImpl(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    @Override
    public void deleteEvent(Long id) {
        if (!eventRepositoryPort.existsById(id)) {
            throw new ResourceNotFoundException("Event not found with id: " + id);
        }
        eventRepositoryPort.delete(id);
    }
}
