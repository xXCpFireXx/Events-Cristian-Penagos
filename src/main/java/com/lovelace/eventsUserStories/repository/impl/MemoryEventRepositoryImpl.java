package com.lovelace.eventsUserStories.repository.impl;

import com.lovelace.eventsUserStories.domain.model.Event;
import com.lovelace.eventsUserStories.domain.ports.out.EventRepositoryPort;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
@Repository
@Profile("dev")
public class MemoryEventRepositoryImpl implements EventRepositoryPort {

    private final List<Event> listEvents = new ArrayList<>();
    private final AtomicLong sequence = new AtomicLong(1L);

    @Override
    public Event save(Event event) {
        if (event.getId() == null || event.getId() == 0L) {
            event.setId(sequence.getAndIncrement());
            listEvents.add(event);
        } else {
            delete(event.getId());
            listEvents.add(event);
        }
        return event;
    }

    @Override
    public Optional<Event> findById(Long id) {
        return listEvents.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    @Override
    public Page<Event> findAll(Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), listEvents.size());
        List<Event> pageContent;
        if (start > listEvents.size()) {
            pageContent = new ArrayList<>();
        } else {
            pageContent = listEvents.subList(start, end);
        }
        return new PageImpl<>(pageContent, pageable, listEvents.size());
    }

    @Override
    public void delete(Long id) {
        listEvents.removeIf(e -> e.getId().equals(id));
    }

    @Override
    public boolean existsById(Long id) {
        return listEvents.stream().anyMatch(e -> e.getId().equals(id));
    }

    @Override
    public boolean existsByNameEvent(String name) {
        return listEvents.stream().anyMatch(e -> e.getNameEvent().equalsIgnoreCase(name));
    }
}
