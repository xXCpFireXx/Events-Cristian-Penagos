package com.lovelace.eventsUserStories.infrastructure.adapters.in.web.mapper;

import com.lovelace.eventsUserStories.domain.model.Venue;
import com.lovelace.eventsUserStories.infrastructure.adapters.in.web.dto.VenueRequestDTO;
import com.lovelace.eventsUserStories.infrastructure.adapters.in.web.dto.VenueResponseDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-26T07:57:38-0500",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.9 (Amazon.com Inc.)"
)
@Component
public class VenueWebMapperImpl implements VenueWebMapper {

    @Override
    public Venue toDomain(VenueRequestDTO requestDTO) {
        if ( requestDTO == null ) {
            return null;
        }

        Venue venue = new Venue();

        venue.setNameVenue( requestDTO.getNameVenue() );
        venue.setAddress( requestDTO.getAddress() );
        venue.setCapacity( requestDTO.getCapacity() );

        return venue;
    }

    @Override
    public VenueResponseDTO toResponse(Venue domain) {
        if ( domain == null ) {
            return null;
        }

        VenueResponseDTO venueResponseDTO = new VenueResponseDTO();

        venueResponseDTO.setId( domain.getId() );
        venueResponseDTO.setNameVenue( domain.getNameVenue() );
        venueResponseDTO.setAddress( domain.getAddress() );
        venueResponseDTO.setCapacity( domain.getCapacity() );

        return venueResponseDTO;
    }
}
