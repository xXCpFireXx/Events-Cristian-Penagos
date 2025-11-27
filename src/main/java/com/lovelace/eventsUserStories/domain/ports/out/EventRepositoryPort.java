package com.lovelace.eventsUserStories.domain.ports.out;

import com.lovelace.eventsUserStories.domain.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventRepositoryPort {
    Event save(Event event);
    Optional<Event> findById(Long id);
    //List<Event> findAll(int page, int size);
    void delete(Long id);
    boolean existsById(Long id);
    boolean existsByNameEvent(String name);
    List<Event> findAllWithFilters(Long venueId, String name);
}
