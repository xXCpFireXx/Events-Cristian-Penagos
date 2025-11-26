package com.lovelace.eventsUserStories.infrastructure.adapters.in.web.mapper;

import com.lovelace.eventsUserStories.domain.model.Event;
import com.lovelace.eventsUserStories.infrastructure.adapters.in.web.dto.EventRequestDTO;
import com.lovelace.eventsUserStories.infrastructure.adapters.in.web.dto.EventResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EventWebMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "idVenue", target = "idVenueEvent")
    Event toDomain(EventRequestDTO requestDTO);

    @Mapping(source = "idVenueEvent", target = "idVenue")
    EventResponseDTO toResponse(Event domain);
}
