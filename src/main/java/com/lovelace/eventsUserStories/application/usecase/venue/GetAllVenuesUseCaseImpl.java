package com.lovelace.eventsUserStories.application.usecase.venue;

import com.lovelace.eventsUserStories.domain.model.Event;
import com.lovelace.eventsUserStories.domain.model.Venue;
import com.lovelace.eventsUserStories.domain.ports.in.eventUseCases.GetAllEventsUseCase;
import com.lovelace.eventsUserStories.domain.ports.in.venueUseCases.GetAllVenueUseCase;
import com.lovelace.eventsUserStories.domain.ports.out.VenueRepositoryPort;

import java.util.List;

public class GetAllVenuesUseCaseImpl implements GetAllVenueUseCase {

    private final VenueRepositoryPort venueRepositoryPort;

    public GetAllVenuesUseCaseImpl(VenueRepositoryPort venueRepositoryPort) {
        this.venueRepositoryPort = venueRepositoryPort;
    }

    @Override
    public List<Venue> getAllVenues(int page, int size) {
        return venueRepositoryPort.findAll(page,size);
    }
}
