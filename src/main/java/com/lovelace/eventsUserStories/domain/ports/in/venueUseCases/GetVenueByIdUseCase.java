package com.lovelace.eventsUserStories.domain.ports.in.venueUseCases;

import com.lovelace.eventsUserStories.domain.model.Venue;

import java.util.Optional;

public interface GetVenueByIdUseCase {
    Optional<Venue> getVenueById(Long id);
}
