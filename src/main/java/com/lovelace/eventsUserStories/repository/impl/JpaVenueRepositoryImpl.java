package com.lovelace.eventsUserStories.repository.impl;

import com.lovelace.eventsUserStories.entity.VenueEntity;
import com.lovelace.eventsUserStories.mapper.VenueMapper;
import com.lovelace.eventsUserStories.domain.model.Venue;
import com.lovelace.eventsUserStories.domain.ports.out.VenueRepositoryPort;
import com.lovelace.eventsUserStories.repository.interfaces.VenueJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Profile("jpa")
@RequiredArgsConstructor
public class JpaVenueRepositoryImpl implements VenueRepositoryPort {
    private final VenueJpaRepository venueJpaRepository;
    private final VenueMapper venueMapper;

    @Override
    public Venue save(Venue venue) {
        VenueEntity entity = venueMapper.modelToEntity(venue);
        VenueEntity savedEntity = venueJpaRepository.save(entity);
        return venueMapper.entityToModel(savedEntity);
    }

    @Override
    public List<Venue> findAll() {
        return venueJpaRepository.findAll().stream()
                .map(venueMapper::entityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Venue> findById(Long id) {
        return venueJpaRepository.findById(id).map(venueMapper::entityToModel);
    }

    @Override
    public void deleteById(Long id) {
        venueJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return venueJpaRepository.existsById(id);
    }

    @Override
    public boolean existsByNameVenue(String nameVenue) {
        return venueJpaRepository.existsByNameVenue(nameVenue);
    }
}
