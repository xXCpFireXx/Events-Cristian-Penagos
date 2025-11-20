package com.lovelace.eventsUserStories.repository.interfaces;

import com.lovelace.eventsUserStories.model.Event;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface IEventRepository {
    Event save(Event event);
    Optional<Event> findById(Long id);
    Page<Event> findAll(Pageable pageable);
    void delete(Long id);
    boolean existsById(Long id);
    boolean existsByNameEvent(String name);
}
