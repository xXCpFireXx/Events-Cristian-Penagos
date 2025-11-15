package com.lovelace.eventsUserStories.repository.impl;

import com.lovelace.eventsUserStories.domain.Event;
import com.lovelace.eventsUserStories.repository.interfaces.IEventRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class EventRepositoryImpl implements IEventRepository {

    private final List<Event> listEvents = new ArrayList<>();
    private final AtomicLong sequence = new AtomicLong(1L);

    @Override
    public Event save(Event event) {
        if (event.getId() == null || event.getId() == 0L) {
            long newId = sequence.getAndIncrement();
            event.setId(newId);
        } else {
            for (int i = 0; i < listEvents.size(); i++) {
                if (listEvents.get(i).getId().equals(event.getId())) {
                    listEvents.set(i, event);
                    return event;
                }
            }
        }
        listEvents.add(event);
        return event;
    }

    @Override
    public Optional<Event> findById(Long id) {
        return listEvents.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Event> findAll() {
        return new ArrayList<>(listEvents);
    }

    @Override
    public void delete(Long id) {
        listEvents.removeIf(e -> e.getId().equals(id));
    }

    @Override
    public boolean existsById(Long id) {
        return listEvents.stream()
                .anyMatch(e -> e.getId().equals(id));
    }
}
