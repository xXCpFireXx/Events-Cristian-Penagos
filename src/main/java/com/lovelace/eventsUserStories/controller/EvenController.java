package com.lovelace.eventsUserStories.controller;

import com.lovelace.eventsUserStories.dto.EventRequestDTO;
import com.lovelace.eventsUserStories.dto.EventResponseDTO;
import com.lovelace.eventsUserStories.service.interfaces.IEventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EvenController {
    private final IEventService eventService;

    @PostMapping
    public ResponseEntity<EventResponseDTO> createEvent(@Valid @RequestBody EventRequestDTO requestDTO) {
        EventResponseDTO createdEvent = eventService.createEvent(requestDTO);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventResponseDTO> updateEvent(@PathVariable Long id, @Valid @RequestBody EventRequestDTO requestDTO) {
        return ResponseEntity.ok(eventService.updateEvent(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
