package com.lovelace.eventsUserStories.service.interfaces;

import com.lovelace.eventsUserStories.dto.VenueRequestDTO;
import com.lovelace.eventsUserStories.dto.VenueResponseDTO;

import java.util.List;

public interface IVenueService {
    VenueResponseDTO createVenue(VenueRequestDTO requestDTO);
    List<VenueResponseDTO> getAllVenues();
    VenueResponseDTO getVenueById(Long id);
    VenueResponseDTO updateVenue(Long id, VenueRequestDTO requestDTO);
    void deleteVenue(Long id);
}
