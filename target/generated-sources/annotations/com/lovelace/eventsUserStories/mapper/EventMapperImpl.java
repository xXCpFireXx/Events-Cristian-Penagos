package com.lovelace.eventsUserStories.mapper;

import com.lovelace.eventsUserStories.domain.Event;
import com.lovelace.eventsUserStories.dto.EventRequestDTO;
import com.lovelace.eventsUserStories.dto.EventResponseDTO;
import com.lovelace.eventsUserStories.dto.VenueResponseDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-15T13:54:51-0500",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.9 (Amazon.com Inc.)"
)
@Component
public class EventMapperImpl implements EventMapper {

    @Override
    public Event toEntity(EventRequestDTO request) {
        if ( request == null ) {
            return null;
        }

        Event event = new Event();

        event.setIdVenueEvent( request.getIdVenue() );
        event.setNameEvent( request.getNameEvent() );

        return event;
    }

    @Override
    public EventResponseDTO toEventResponse(Event event, VenueResponseDTO venueResponse) {
        if ( event == null && venueResponse == null ) {
            return null;
        }

        EventResponseDTO eventResponseDTO = new EventResponseDTO();

        if ( event != null ) {
            eventResponseDTO.setId( event.getId() );
            eventResponseDTO.setNameEvent( event.getNameEvent() );
        }
        eventResponseDTO.setVenue( venueResponse );

        return eventResponseDTO;
    }
}
