package com.lovelace.eventsUserStories.mapper;

import com.lovelace.eventsUserStories.dto.VenueRequestDTO;
import com.lovelace.eventsUserStories.dto.VenueResponseDTO;
import com.lovelace.eventsUserStories.entity.VenueEntity;
import com.lovelace.eventsUserStories.model.Venue;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-19T22:56:00-0500",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.9 (Amazon.com Inc.)"
)
@Component
public class VenueMapperImpl implements VenueMapper {

    @Override
    public Venue toEntity(VenueRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Venue venue = new Venue();

        venue.setNameVenue( dto.getNameVenue() );
        venue.setAddress( dto.getAddress() );
        venue.setCapacity( dto.getCapacity() );

        return venue;
    }

    @Override
    public VenueResponseDTO toResponse(Venue venue) {
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

    @Override
    public VenueEntity modelToEntity(Venue venue) {
        if ( venue == null ) {
            return null;
        }

        VenueEntity venueEntity = new VenueEntity();

        venueEntity.setId( venue.getId() );
        venueEntity.setNameVenue( venue.getNameVenue() );
        venueEntity.setAddress( venue.getAddress() );
        venueEntity.setCapacity( venue.getCapacity() );

        return venueEntity;
    }

    @Override
    public Venue entityToModel(VenueEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Venue venue = new Venue();

        venue.setId( entity.getId() );
        venue.setNameVenue( entity.getNameVenue() );
        venue.setAddress( entity.getAddress() );
        venue.setCapacity( entity.getCapacity() );

        return venue;
    }
}
