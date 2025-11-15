package com.lovelace.eventsUserStories.repository.interfaces;

import com.lovelace.eventsUserStories.domain.Event;

import java.util.List;
import java.util.Optional;

public interface IEventRepository {
    Event save(Event event);
    Optional<Event> findById(Long id);
    List<Event> findAll();
    void delete(Long id);
    boolean existsById(Long id);
}
