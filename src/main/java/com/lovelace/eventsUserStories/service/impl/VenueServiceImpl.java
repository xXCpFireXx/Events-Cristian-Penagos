package com.lovelace.eventsUserStories.service.impl;

import com.lovelace.eventsUserStories.model.Venue;
import com.lovelace.eventsUserStories.dto.VenueRequestDTO;
import com.lovelace.eventsUserStories.dto.VenueResponseDTO;
import com.lovelace.eventsUserStories.exception.DuplicateResourceException;
import com.lovelace.eventsUserStories.exception.ResourceNotFoundException;
import com.lovelace.eventsUserStories.mapper.VenueMapper;
import com.lovelace.eventsUserStories.repository.interfaces.IVenueRepository;
import com.lovelace.eventsUserStories.service.interfaces.IVenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VenueServiceImpl implements IVenueService {

    private final IVenueRepository venueRepository;
    private final VenueMapper venueMapper;

    @Override
    public VenueResponseDTO createVenue(VenueRequestDTO requestDTO) {
        if (venueRepository.existsByNameVenue(requestDTO.getNameVenue())) {
            throw new DuplicateResourceException("A venue with the name " + requestDTO.getNameVenue() + "already exists" );
        }

        Venue venue = venueMapper.toEntity(requestDTO);
        Venue savedVenue = venueRepository.save(venue);
        return venueMapper.toResponse(savedVenue);
    }

    @Override
    public List<VenueResponseDTO> getAllVenues() {
        return venueRepository.findAll().stream()
                .map(venueMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public VenueResponseDTO getVenueById(Long id) {
        Venue venue = venueRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Venue not found with id: " + id));
        return venueMapper.toResponse(venue);
    }

    @Override
    public VenueResponseDTO updateVenue(Long id, VenueRequestDTO requestDTO) {
        if (!venueRepository.existsById(id)) {
            throw new ResourceNotFoundException("Venue not found with id: " + id);
        }
        Venue venueToUpdate = venueMapper.toEntity(requestDTO);
        venueToUpdate.setId(id);

        Venue updatedVenue = venueRepository.save(venueToUpdate);
        return venueMapper.toResponse(updatedVenue);
    }

    @Override
    public void deleteVenue(Long id) {
        if (!venueRepository.existsById(id)) {
            throw new ResourceNotFoundException("Venue not found with id: " + id);
        }
        venueRepository.deleteById(id);
    }
}
