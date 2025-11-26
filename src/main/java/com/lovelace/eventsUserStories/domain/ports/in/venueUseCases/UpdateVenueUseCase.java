package com.lovelace.eventsUserStories.domain.ports.in.venueUseCases;

import com.lovelace.eventsUserStories.domain.model.Venue;

import java.util.Optional;

public interface UpdateVenueUseCase {
    Optional<Venue> updateVenue(Long id, Venue venue);
}
