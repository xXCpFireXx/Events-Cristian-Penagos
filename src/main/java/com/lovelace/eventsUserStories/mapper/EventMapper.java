package com.lovelace.eventsUserStories.mapper;

import com.lovelace.eventsUserStories.domain.Event;
import com.lovelace.eventsUserStories.dto.EventRequestDTO;
import com.lovelace.eventsUserStories.dto.EventResponseDTO;
import com.lovelace.eventsUserStories.dto.VenueResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "idVenueEvent", source = "idVenue")
    Event toEntity(EventRequestDTO request);

    @Mapping(target = "venue", source = "venueResponse")
    EventResponseDTO toEventResponse(Event event, VenueResponseDTO venueResponse);
}
