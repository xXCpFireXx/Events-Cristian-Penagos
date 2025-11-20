package com.lovelace.eventsUserStories.service.impl;

import com.lovelace.eventsUserStories.exception.DuplicateResourceException;
import com.lovelace.eventsUserStories.model.Event;
import com.lovelace.eventsUserStories.dto.EventRequestDTO;
import com.lovelace.eventsUserStories.dto.EventResponseDTO;
import com.lovelace.eventsUserStories.dto.VenueResponseDTO;
import com.lovelace.eventsUserStories.exception.ResourceNotFoundException;
import com.lovelace.eventsUserStories.mapper.EventMapper;
import com.lovelace.eventsUserStories.repository.interfaces.IEventRepository;
import com.lovelace.eventsUserStories.service.interfaces.IEventService;
import com.lovelace.eventsUserStories.service.interfaces.IVenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements IEventService {

    private final IEventRepository eventRepository;
    private final EventMapper eventMapper;
    private final IVenueService venueService;

    @Override
    public EventResponseDTO createEvent(EventRequestDTO requestDTO) {
        if (eventRepository.existsByNameEvent(requestDTO.getNameEvent())) {
            throw new DuplicateResourceException("Event name already exists: " + requestDTO.getNameEvent());
        }

        VenueResponseDTO venueDTO = venueService.getVenueById(requestDTO.getIdVenue());

        Event event = eventMapper.toEntity(requestDTO);
        Event savedEvent = eventRepository.save(event);

        return eventMapper.toEventResponse(savedEvent, venueDTO);
    }

    @Override
    public Page<EventResponseDTO> getAllEvents(Pageable pageable) {
        return eventRepository.findAll(pageable)
                .map(this::mapEventToResponseDTO);
    }

    @Override
    public EventResponseDTO getEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));
        return mapEventToResponseDTO(event);
    }

    @Override
    public EventResponseDTO updateEvent(Long id, EventRequestDTO requestDTO) {
        if (!eventRepository.existsById(id)) {
            throw new ResourceNotFoundException("Event not found with id: " + id);
        }

        VenueResponseDTO venueDTO = venueService.getVenueById(requestDTO.getIdVenue());

        Event eventToUpdate = eventMapper.toEntity(requestDTO);
        eventToUpdate.setId(id);

        Event updatedEvent = eventRepository.save(eventToUpdate);
        return eventMapper.toEventResponse(updatedEvent, venueDTO);
    }

    @Override
    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new ResourceNotFoundException("Event not found with id: " + id);
        }
        eventRepository.delete(id);
    }

    private EventResponseDTO mapEventToResponseDTO(Event event) {
        VenueResponseDTO venueDTO = venueService.getVenueById(event.getIdVenueEvent());
        return eventMapper.toEventResponse(event, venueDTO);
    }
}
