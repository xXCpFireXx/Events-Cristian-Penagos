package com.lovelace.eventsUserStories.repository.impl;

import com.lovelace.eventsUserStories.domain.model.Venue;
import com.lovelace.eventsUserStories.domain.ports.out.VenueRepositoryPort;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@Profile("dev")
public class MemoryVenueRepositoryImpl implements VenueRepositoryPort {

    private final List<Venue> listVenues = new ArrayList<>();
    private final AtomicLong sequence = new AtomicLong(1L);

    @Override
    public Venue save(Venue venue) {
        if (venue.getId() == null || venue.getId() == 0L) {
            venue.setId(sequence.getAndIncrement());
            listVenues.add(venue);
        } else {
            deleteById(venue.getId());
            listVenues.add(venue);
        }
        return venue;
    }

    @Override
    public List<Venue> findAll() { return new ArrayList<>(listVenues); }

    @Override
    public Optional<Venue> findById(Long id) {
        return listVenues.stream().filter(v -> v.getId().equals(id)).findFirst();
    }

    @Override
    public void deleteById(Long id) {
        listVenues.removeIf(v -> v.getId().equals(id));
    }

    @Override
    public boolean existsById(Long id) {
        return listVenues.stream().anyMatch(v -> v.getId().equals(id));
    }

    @Override
    public boolean existsByNameVenue(String nameVenue) {
        return listVenues.stream()
                .anyMatch(v -> v.getNameVenue().equalsIgnoreCase(nameVenue));
    }
}
