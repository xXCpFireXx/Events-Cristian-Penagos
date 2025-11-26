package com.lovelace.eventsUserStories.domain.ports.out;

import com.lovelace.eventsUserStories.domain.model.Venue;

import java.util.List;
import java.util.Optional;

public interface VenueRepositoryPort {
    Venue save(Venue venue);
    List<Venue> findAll(int page, int size);
    Optional<Venue> findById(Long id);
    void delete(Long id);
    boolean existsById(Long id);
    boolean existsByNameVenue(String nameVenue);
}
