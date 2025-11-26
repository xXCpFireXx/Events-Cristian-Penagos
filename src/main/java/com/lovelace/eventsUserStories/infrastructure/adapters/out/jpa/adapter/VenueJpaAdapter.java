package com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.adapter;

import com.lovelace.eventsUserStories.domain.model.Venue;
import com.lovelace.eventsUserStories.domain.ports.out.VenueRepositoryPort;
import com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.entity.VenueEntity;
import com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.mapper.VenueDboMapper;
import com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.repository.ISpringVenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VenueJpaAdapter implements VenueRepositoryPort {

    private final ISpringVenueRepository springVenueRepository;
    private  final VenueDboMapper venueDboMapper;

    @Override
    public Venue save(Venue venue) {
        VenueEntity entity = venueDboMapper.toEntity(venue);
        VenueEntity saved = springVenueRepository.save(entity);
        return venueDboMapper.toDomain(saved);
    }

    @Override
    public List<Venue> findAll(int page, int size) {
        return springVenueRepository.findAll(PageRequest.of(page, size))
                .map(venueDboMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Venue> findById(Long id) {
        return springVenueRepository.findById(id)
                .map(venueDboMapper::toDomain);
    }

    @Override
    public void delete(Long id) {
        springVenueRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return springVenueRepository.existsById(id);
    }

    @Override
    public boolean existsByNameVenue(String nameVenue) {
        return springVenueRepository.existsByNameVenue(nameVenue);
    }
}
