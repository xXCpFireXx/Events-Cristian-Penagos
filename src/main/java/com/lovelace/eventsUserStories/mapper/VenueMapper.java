package com.lovelace.eventsUserStories.mapper;

import com.lovelace.eventsUserStories.model.Venue;
import com.lovelace.eventsUserStories.dto.VenueRequestDTO;
import com.lovelace.eventsUserStories.dto.VenueResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VenueMapper {
    @Mapping(target = "id", ignore = true)
    Venue toEntity(VenueRequestDTO requestDTO);

    VenueResponseDTO toResponseDTO(Venue venue);
}
