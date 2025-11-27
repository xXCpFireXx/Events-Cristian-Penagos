package com.lovelace.eventsUserStories.infrastructure.adapters.in.web.mapper;

import com.lovelace.eventsUserStories.domain.model.Event;
import com.lovelace.eventsUserStories.infrastructure.adapters.in.web.dto.EventRequestDTO;
import com.lovelace.eventsUserStories.infrastructure.adapters.in.web.dto.EventResponseDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-27T09:55:33-0500",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.9 (Amazon.com Inc.)"
)
@Component
public class EventWebMapperImpl implements EventWebMapper {

    @Override
    public Event toDomain(EventRequestDTO requestDTO) {
        if ( requestDTO == null ) {
            return null;
        }

        Event event = new Event();

        event.setIdVenueEvent( requestDTO.getIdVenue() );
        event.setNameEvent( requestDTO.getNameEvent() );

        return event;
    }

    @Override
    public EventResponseDTO toResponse(Event domain) {
        if ( domain == null ) {
            return null;
        }

        EventResponseDTO eventResponseDTO = new EventResponseDTO();

        eventResponseDTO.setIdVenue( domain.getIdVenueEvent() );
        eventResponseDTO.setId( domain.getId() );
        eventResponseDTO.setNameEvent( domain.getNameEvent() );

        return eventResponseDTO;
    }
}
