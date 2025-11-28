package com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.mapper;

import com.lovelace.eventsUserStories.domain.model.Venue;
import com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.entity.VenueEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-28T07:04:39-0500",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.9 (Amazon.com Inc.)"
)
@Component
public class VenueDboMapperImpl implements VenueDboMapper {

    @Override
    public VenueEntity toEntity(Venue domain) {
        if ( domain == null ) {
            return null;
        }

        VenueEntity venueEntity = new VenueEntity();

        venueEntity.setId( domain.getId() );
        venueEntity.setNameVenue( domain.getNameVenue() );
        venueEntity.setAddress( domain.getAddress() );
        venueEntity.setCapacity( domain.getCapacity() );

        return venueEntity;
    }

    @Override
    public Venue toDomain(VenueEntity entity) {
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
