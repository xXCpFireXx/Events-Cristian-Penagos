package com.lovelace.eventsUserStories.repository.impl;

import com.lovelace.eventsUserStories.entity.EventEntity;
import com.lovelace.eventsUserStories.entity.VenueEntity;
import com.lovelace.eventsUserStories.domain.exception.ResourceNotFoundException;
import com.lovelace.eventsUserStories.mapper.EventMapper;
import com.lovelace.eventsUserStories.domain.model.Event;
import com.lovelace.eventsUserStories.repository.interfaces.EventJpaRepository;
import com.lovelace.eventsUserStories.domain.ports.out.EventRepositoryPort;
import com.lovelace.eventsUserStories.repository.interfaces.VenueJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Repository
@Profile("jpa")
@RequiredArgsConstructor
public class JpaEventRepositoryImpl implements EventRepositoryPort {

    private final EventJpaRepository jpaEventRepository;
    private final VenueJpaRepository venueJpaRepository;
    private final EventMapper eventMapper;

    @Override
    public Event save(Event event) {
        EventEntity entity = eventMapper.modelToEntity(event);

        if (event.getIdVenueEvent() != null) {
            VenueEntity venueEntity = venueJpaRepository.findById(event.getIdVenueEvent())
                    .orElseThrow(() -> new ResourceNotFoundException("Venue not found"));
            entity.setVenue(venueEntity);
        }

        EventEntity savedEntity = jpaEventRepository.save(entity);

        Event savedModel = eventMapper.entityToModel(savedEntity);
        return savedModel;
    }

    @Override
    public Optional<Event> findById(Long id) {
        return jpaEventRepository.findById(id).map(entity -> {
            Event model = eventMapper.entityToModel(entity);
            if(entity.getVenue() != null) {
                model.setIdVenueEvent(entity.getVenue().getId());
            }
            return model;
        });
    }

    @Override
    public Page<Event> findAll(Pageable pageable) {
        return jpaEventRepository.findAll(pageable).map(entity -> {
            Event model = eventMapper.entityToModel(entity);
            if(entity.getVenue() != null) {
                model.setIdVenueEvent(entity.getVenue().getId());
            }
            return model;
        });
    }

    @Override
    public void delete(Long id) {
        jpaEventRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaEventRepository.existsById(id);
    }

    @Override
    public boolean existsByNameEvent(String name) {
        return jpaEventRepository.existsByNameEvent(name);
    }
}
