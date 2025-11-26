package com.lovelace.eventsUserStories.infrastructure.adapters.in.web.mapper;
import com.lovelace.eventsUserStories.domain.model.Venue;
import com.lovelace.eventsUserStories.infrastructure.adapters.in.web.dto.VenueRequestDTO;
import com.lovelace.eventsUserStories.infrastructure.adapters.in.web.dto.VenueResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface VenueWebMapper {

    @Mapping(target = "id", ignore = true)
    Venue toDomain(VenueRequestDTO requestDTO);

    VenueResponseDTO toResponse(Venue domain);
}
