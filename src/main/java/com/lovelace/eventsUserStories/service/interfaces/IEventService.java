package com.lovelace.eventsUserStories.service.interfaces;

import com.lovelace.eventsUserStories.dto.EventRequestDTO;
import com.lovelace.eventsUserStories.dto.EventResponseDTO;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

public interface IEventService {
    EventResponseDTO createEvent(EventRequestDTO requestDTO);
    Page<EventResponseDTO> getAllEvents(Pageable pageable); // <--- Cambio clave
    EventResponseDTO getEventById(Long id);
    EventResponseDTO updateEvent(Long id, EventRequestDTO requestDTO);
    void deleteEvent(Long id);
}
