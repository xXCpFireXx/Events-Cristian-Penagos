package com.lovelace.eventsUserStories.application.usecase.venue;

import com.lovelace.eventsUserStories.domain.exception.DuplicateResourceException;
import com.lovelace.eventsUserStories.domain.model.Venue;
import com.lovelace.eventsUserStories.domain.ports.in.venueUseCases.CreateVenueUseCase;
import com.lovelace.eventsUserStories.domain.ports.out.VenueRepositoryPort;
import org.springframework.transaction.annotation.Transactional;

public class CreateVenueUseCaseImpl implements CreateVenueUseCase {

    private final VenueRepositoryPort venueRepositoryPort;

    public CreateVenueUseCaseImpl(VenueRepositoryPort venueRepositoryPort) {
        this.venueRepositoryPort = venueRepositoryPort;
    }

    @Override
    @Transactional
    public Venue createVenue(Venue venue) {
        if (venueRepositoryPort.existsByNameVenue(venue.getNameVenue())) {
            throw new DuplicateResourceException("Venue name " + venue.getNameVenue() +" already exists");
        }

        return venueRepositoryPort.save(venue);
    }
}
