package com.lovelace.eventsUserStories.mapper;

import com.lovelace.eventsUserStories.domain.Venue;
import com.lovelace.eventsUserStories.dto.VenueRequestDTO;
import com.lovelace.eventsUserStories.dto.VenueResponseDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-15T13:54:51-0500",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.9 (Amazon.com Inc.)"
)
@Component
public class VenueMapperImpl implements VenueMapper {

    @Override
    public Venue toEntity(VenueRequestDTO requestDTO) {
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
    public VenueResponseDTO toResponseDTO(Venue venue) {
        if ( venue == null ) {
            return null;
        }

        VenueResponseDTO venueResponseDTO = new VenueResponseDTO();

        venueResponseDTO.setId( venue.getId() );
        venueResponseDTO.setNameVenue( venue.getNameVenue() );
        venueResponseDTO.setAddress( venue.getAddress() );
        venueResponseDTO.setCapacity( venue.getCapacity() );

        return venueResponseDTO;
    }
}
