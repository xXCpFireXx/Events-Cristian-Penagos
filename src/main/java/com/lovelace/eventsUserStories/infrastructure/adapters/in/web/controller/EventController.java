package com.lovelace.eventsUserStories.infrastructure.adapters.in.web.controller;

import com.lovelace.eventsUserStories.application.usecase.event.*;
import com.lovelace.eventsUserStories.domain.model.Event;
import com.lovelace.eventsUserStories.infrastructure.adapters.in.web.dto.EventRequestDTO;
import com.lovelace.eventsUserStories.infrastructure.adapters.in.web.dto.EventResponseDTO;
import com.lovelace.eventsUserStories.infrastructure.adapters.in.web.mapper.EventWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Tag(name = "Events", description = "Controller for managing events")
public class EventController {

    private final CreateEventUseCaseImpl createEventUseCase;
    private final GetEventByIdUseCaseImpl getEventByIdUseCase;
    private final GetAllEventsUseCaseImpl getAllEventsUseCase;
    private final UpdateEventUseCaseImpl updateEventUseCase;
    private final DeleteEventUseCaseImpl deleteEventUseCase;

    private final EventWebMapper eventWebMapper;

    @Operation(summary = "Create a new event", description = "Registers a new event in the database after validating that the name is unique and the associated venue exists.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Event created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data (e.g., missing name or invalid venue ID)", content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflict: Event name already exists", content = @Content)
    })
    @PostMapping
    public ResponseEntity<EventResponseDTO> create(@Valid @RequestBody EventRequestDTO requestDTO) {
        Event eventInput = eventWebMapper.toDomain(requestDTO);
        Event eventCreated = createEventUseCase.createEvent(eventInput);
        return new ResponseEntity<>(eventWebMapper.toResponse(eventCreated), HttpStatus.CREATED);
    }

    @Operation(summary = "Get all events", description = "Retrieves a paginated list of all registered events.")
    @ApiResponse(responseCode = "200", description = "List of events retrieved successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventResponseDTO.class)))
    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getAll(
            @Parameter(description = "Page number (0-based index)", example = "0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Size of the page", example = "10")
            @RequestParam(defaultValue = "10") int size
    ) {
        List<Event> events = getAllEventsUseCase.getAllEvents(page, size);
        List<EventResponseDTO> response = events.stream()
                .map(eventWebMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get event by ID", description = "Retrieves the details of a specific event based on its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Event not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getById(@Parameter(description = "ID of the event to be retrieved") @PathVariable Long id) {
        return getEventByIdUseCase.getEventById(id)
                .map(event -> ResponseEntity.ok(eventWebMapper.toResponse(event)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update an event", description = "Updates the information of an existing event by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content),
            @ApiResponse(responseCode = "404", description = "Event or Venue not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<EventResponseDTO> update(
            @Parameter(description = "ID of the event to be updated") @PathVariable Long id,
            @Valid @RequestBody EventRequestDTO requestDTO) {

        Event eventInput = eventWebMapper.toDomain(requestDTO);
        return updateEventUseCase.updateEvent(id, eventInput)
                .map(updated -> ResponseEntity.ok(eventWebMapper.toResponse(updated)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete an event", description = "Permanently removes an event from the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Event deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Event not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Parameter(description = "ID of the event to be deleted") @PathVariable Long id) {
        deleteEventUseCase.deleteEvent(id);
        return ResponseEntity.noContent().build();

    }
}
