package com.lovelace.eventsUserStories.service.interfaces;

import com.lovelace.eventsUserStories.dto.EventRequestDTO;
import com.lovelace.eventsUserStories.dto.EventResponseDTO;

import java.util.List;

public interface IEventService {
    EventResponseDTO createEvent(EventRequestDTO requestDTO);
    List<EventResponseDTO> getAllEvents();
    EventResponseDTO getEventById(Long id);
    EventResponseDTO updateEvent(Long id, EventRequestDTO requestDTO);
    void deleteEvent(Long id);
}
