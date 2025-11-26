package com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.mapper;

import com.lovelace.eventsUserStories.domain.model.Event;
import com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.entity.EventEntity;
import com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.entity.VenueEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-26T07:57:38-0500",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.9 (Amazon.com Inc.)"
)
@Component
public class EventDboMapperImpl implements EventDboMapper {

    @Override
    public EventEntity toEntity(Event domain) {
        if ( domain == null ) {
            return null;
        }

        EventEntity eventEntity = new EventEntity();

        eventEntity.setVenue( eventToVenueEntity( domain ) );
        eventEntity.setId( domain.getId() );
        eventEntity.setNameEvent( domain.getNameEvent() );

        return eventEntity;
    }

    @Override
    public Event toDomain(EventEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Event event = new Event();

        event.setIdVenueEvent( entityVenueId( entity ) );
        event.setId( entity.getId() );
        event.setNameEvent( entity.getNameEvent() );

        return event;
    }

    protected VenueEntity eventToVenueEntity(Event event) {
        if ( event == null ) {
            return null;
        }

        VenueEntity venueEntity = new VenueEntity();

        venueEntity.setId( event.getIdVenueEvent() );

        return venueEntity;
    }

    private Long entityVenueId(EventEntity eventEntity) {
        VenueEntity venue = eventEntity.getVenue();
        if ( venue == null ) {
            return null;
        }
        return venue.getId();
    }
}
