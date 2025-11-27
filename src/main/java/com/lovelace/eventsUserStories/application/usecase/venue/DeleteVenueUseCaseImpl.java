package com.lovelace.eventsUserStories.application.usecase.venue;

import com.lovelace.eventsUserStories.domain.exception.ResourceNotFoundException;
import com.lovelace.eventsUserStories.domain.ports.in.venueUseCases.DeleteVenueUseCase;
import com.lovelace.eventsUserStories.domain.ports.out.VenueRepositoryPort;
import org.springframework.transaction.annotation.Transactional;

public class DeleteVenueUseCaseImpl implements DeleteVenueUseCase {

    private final VenueRepositoryPort venueRepositoryPort;

    public DeleteVenueUseCaseImpl(VenueRepositoryPort venueRepositoryPort) {
        this.venueRepositoryPort = venueRepositoryPort;
    }

    @Override
    @Transactional
    public void deleteVenue(Long id) {
        if (!venueRepositoryPort.existsById(id)) {
            throw new ResourceNotFoundException("Venue not found with id: " + id);
        }
        venueRepositoryPort.delete(id);
    }
}
