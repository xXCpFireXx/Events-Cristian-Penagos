package com.lovelace.eventsUserStories.repository.impl;

import com.lovelace.eventsUserStories.domain.Event;
import com.lovelace.eventsUserStories.domain.Venue;
import com.lovelace.eventsUserStories.repository.interfaces.IVenueRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class VenueRepositoryImpl implements IVenueRepository {

    private final List<Venue> listVenues = new ArrayList<>();
    private final AtomicLong sequence = new AtomicLong(1L);

    @Override
    public Venue save(Venue venue) {
        if (venue.getId() == null || venue.getId() == 0L) {
            long newId = sequence.getAndIncrement();
            venue.setId(newId);
        } else {
            for (int i = 0; i < listVenues.size(); i++) {
                if (listVenues.get(i).getId().equals(venue.getId())) {
                    listVenues.set(i, venue);
                    return venue;
                }
            }
        }
        listVenues.add(venue);
        return venue;
    }

    @Override
    public List<Venue> findAll() {
        return new ArrayList<>(listVenues);
    }

    @Override
    public Optional<Venue> findById(Long id) {
        return listVenues.stream()
                .filter(v -> v.getId().equals(id))
                .findFirst();
    }

    @Override
    public void deleteById(Long id) {
        listVenues.removeIf(v -> v.getId().equals(id));
    }

    @Override
    public boolean existsById(Long id) {
        return listVenues.stream()
                .anyMatch(v -> v.getId().equals(id));
    }
}
