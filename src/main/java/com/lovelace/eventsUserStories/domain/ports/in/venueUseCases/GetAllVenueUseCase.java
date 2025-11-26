package com.lovelace.eventsUserStories.domain.ports.in.venueUseCases;

import com.lovelace.eventsUserStories.domain.model.Venue;

import java.util.List;

public interface GetAllVenueUseCase {
    List<Venue> getAllVenues(int page, int size);
}
