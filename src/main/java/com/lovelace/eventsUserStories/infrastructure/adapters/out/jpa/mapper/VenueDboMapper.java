package com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.mapper;
import com.lovelace.eventsUserStories.domain.model.Venue;
import com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.entity.VenueEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface VenueDboMapper {

    VenueEntity toEntity(Venue domain);

    @InheritInverseConfiguration
    Venue toDomain(VenueEntity entity);
}
