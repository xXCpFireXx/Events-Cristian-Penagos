package com.lovelace.eventsUserStories.domain.ports.in.venueUseCases;

import com.lovelace.eventsUserStories.domain.model.Venue;

public interface CreateVenueUseCase {
    Venue createVenue(Venue venue);
}
