package com.lovelace.eventsUserStories.application.usecase.venue;

import com.lovelace.eventsUserStories.domain.model.Venue;
import com.lovelace.eventsUserStories.domain.ports.in.venueUseCases.UpdateVenueUseCase;
import com.lovelace.eventsUserStories.domain.ports.out.VenueRepositoryPort;

import java.util.Optional;

public class UpdateVenueUseCaseImpl implements UpdateVenueUseCase {

    private final VenueRepositoryPort venueRepositoryPort;

    public UpdateVenueUseCaseImpl(VenueRepositoryPort venueRepositoryPort) {
        this.venueRepositoryPort = venueRepositoryPort;
    }

    @Override
    public Optional<Venue> updateVenue(Long id, Venue venue) {
        if (!venueRepositoryPort.existsById(id)) {
            return Optional.empty();
        }

        venue.setId(id);
        return Optional.of(venueRepositoryPort.save(venue));

    }
}
