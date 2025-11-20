package com.lovelace.eventsUserStories.mapper;

import com.lovelace.eventsUserStories.dto.VenueResponseDTO;
import com.lovelace.eventsUserStories.entity.EventEntity;
import com.lovelace.eventsUserStories.model.Event;
import com.lovelace.eventsUserStories.dto.EventRequestDTO;
import com.lovelace.eventsUserStories.dto.EventResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {
    @Mapping(source = "idVenue", target = "idVenueEvent")
    Event toEntity(EventRequestDTO dto);

    @Mapping(source = "event.id", target = "id")
    @Mapping(source = "venue", target = "venue")
    EventResponseDTO toEventResponse(Event event, VenueResponseDTO venue);

    @Mapping(target = "venue", ignore = true)
    EventEntity modelToEntity(Event event);

    @Mapping(source = "venue.id", target = "idVenueEvent")
    Event entityToModel(EventEntity entity);
}
