package com.lovelace.eventsUserStories.mapper;

import com.lovelace.eventsUserStories.dto.EventRequestDTO;
import com.lovelace.eventsUserStories.dto.EventResponseDTO;
import com.lovelace.eventsUserStories.dto.VenueResponseDTO;
import com.lovelace.eventsUserStories.entity.EventEntity;
import com.lovelace.eventsUserStories.entity.VenueEntity;
import com.lovelace.eventsUserStories.model.Event;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-19T22:56:00-0500",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.9 (Amazon.com Inc.)"
)
@Component
public class EventMapperImpl implements EventMapper {

    @Override
    public Event toEntity(EventRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Event event = new Event();

        event.setIdVenueEvent( dto.getIdVenue() );
        event.setNameEvent( dto.getNameEvent() );

        return event;
    }

    @Override
    public EventResponseDTO toEventResponse(Event event, VenueResponseDTO venue) {
        if ( event == null && venue == null ) {
            return null;
        }

        EventResponseDTO eventResponseDTO = new EventResponseDTO();

        if ( event != null ) {
            eventResponseDTO.setId( event.getId() );
            eventResponseDTO.setNameEvent( event.getNameEvent() );
        }
        eventResponseDTO.setVenue( venue );

        return eventResponseDTO;
    }

    @Override
    public EventEntity modelToEntity(Event event) {
        if ( event == null ) {
            return null;
        }

        EventEntity eventEntity = new EventEntity();

        eventEntity.setId( event.getId() );
        eventEntity.setNameEvent( event.getNameEvent() );

        return eventEntity;
    }

    @Override
    public Event entityToModel(EventEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Event event = new Event();

        event.setIdVenueEvent( entityVenueId( entity ) );
        event.setId( entity.getId() );
        event.setNameEvent( entity.getNameEvent() );

        return event;
    }

    private Long entityVenueId(EventEntity eventEntity) {
        VenueEntity venue = eventEntity.getVenue();
        if ( venue == null ) {
            return null;
        }
        return venue.getId();
    }
}
