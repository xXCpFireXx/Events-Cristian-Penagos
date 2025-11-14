package com.lovelace.eventsUserStories.repository.impl;

import com.lovelace.eventsUserStories.domain.Event;
import com.lovelace.eventsUserStories.repository.interfaces.IEventRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventRepositoryImpl implements IEventRepository {

    private final List<Event> listEvents = new ArrayList<>();

    @Override
    public Event create(Event event) {
        Event newEvent = new Event(
                (long) (listEvents.size()+1),
                event.getNameEvent()
        );

        listEvents.add(newEvent);
        return newEvent;
    }

    @Override
    public Optional<Event> findById(Long id) {

        for (Event event : listEvents){
            if (event.getId().equals(id)){
                return Optional.of(event);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Event> findAll() {
        return List.of();
    }

    @Override
    public void delete(Long id) {

    }
}
