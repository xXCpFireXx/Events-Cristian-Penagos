package com.lovelace.eventsUserStories.application.usecase.venue;

import com.lovelace.eventsUserStories.domain.model.Venue;
import com.lovelace.eventsUserStories.domain.ports.in.venueUseCases.GetVenueByIdUseCase;
import com.lovelace.eventsUserStories.domain.ports.out.VenueRepositoryPort;

import java.util.Optional;

public class GetVenueByIdUseCaseImpl implements GetVenueByIdUseCase {

    private final VenueRepositoryPort venueRepositoryPort;

    public GetVenueByIdUseCaseImpl(VenueRepositoryPort venueRepositoryPort) {
        this.venueRepositoryPort = venueRepositoryPort;
    }

    @Override
    public Optional<Venue> getVenueById(Long id) {
        return venueRepositoryPort.findById(id);
    }
}
