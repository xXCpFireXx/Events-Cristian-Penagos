package com.lovelace.eventsUserStories.infrastructure.adapters.in.web.mapper;

import com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.entity.VenueEntity;
import com.lovelace.eventsUserStories.domain.model.Venue;
import com.lovelace.eventsUserStories.dto.VenueRequestDTO;
import com.lovelace.eventsUserStories.dto.VenueResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VenueMapper {
    Venue toEntity(VenueRequestDTO dto);

    VenueResponseDTO toResponse(Venue venue);

    VenueEntity modelToEntity(Venue venue);

    Venue entityToModel(VenueEntity entity);
}
