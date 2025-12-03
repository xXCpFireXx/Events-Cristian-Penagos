package com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.mapper;

import com.lovelace.eventsUserStories.domain.model.Event;
import com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.entity.EventEntity;
import com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.entity.VenueEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-02T20:13:46-0500",
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
        eventEntity.setStartDate( domain.getStartDate() );
        eventEntity.setEndDate( domain.getEndDate() );

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
        event.setStartDate( entity.getStartDate() );
        event.setEndDate( entity.getEndDate() );

        return event;
    }

    @Override
    public List<Event> toDomainList(List<EventEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<Event> list = new ArrayList<Event>( entityList.size() );
        for ( EventEntity eventEntity : entityList ) {
            list.add( toDomain( eventEntity ) );
        }

        return list;
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
