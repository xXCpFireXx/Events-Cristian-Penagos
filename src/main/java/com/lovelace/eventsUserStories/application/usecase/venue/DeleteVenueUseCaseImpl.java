package com.lovelace.eventsUserStories.application.usecase.venue;

import com.lovelace.eventsUserStories.domain.exception.ResourceNotFoundException;
import com.lovelace.eventsUserStories.domain.ports.in.venueUseCases.DeleteVenueUseCase;
import com.lovelace.eventsUserStories.domain.ports.out.VenueRepositoryPort;

public class DeleteVenueUseCaseImpl implements DeleteVenueUseCase {

    private final VenueRepositoryPort venueRepositoryPort;

    public DeleteVenueUseCaseImpl(VenueRepositoryPort venueRepositoryPort) {
        this.venueRepositoryPort = venueRepositoryPort;
    }

    @Override
    public void deleteVenue(Long id) {
        if (!venueRepositoryPort.existsById(id)) {
            throw new ResourceNotFoundException("Venue not found with id: " + id);
        }
        venueRepositoryPort.delete(id);
    }
}
