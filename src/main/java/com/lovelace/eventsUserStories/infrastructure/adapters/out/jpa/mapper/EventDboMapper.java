package com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.mapper;
import com.lovelace.eventsUserStories.domain.model.Event;
import com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.entity.EventEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EventDboMapper {

    @Mapping(source = "idVenueEvent", target = "venue.id")
    EventEntity toEntity(Event domain);

    @InheritInverseConfiguration
    Event toDomain(EventEntity entity);

    List<Event> toDomainList(List<EventEntity> entityList);
}
