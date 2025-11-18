package com.lovelace.eventsUserStories.repository.interfaces;

import com.lovelace.eventsUserStories.domain.Venue;

import java.util.List;
import java.util.Optional;

public interface IVenueRepository {
    Venue save(Venue venue);
    List<Venue> findAll();
    Optional<Venue> findById(Long id);
    void deleteById(Long id);
    boolean existsById(Long id);
    boolean existsByNameVenue(String nameVenue);
}
