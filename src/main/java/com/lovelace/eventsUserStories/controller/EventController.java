package com.lovelace.eventsUserStories.controller;

import com.lovelace.eventsUserStories.dto.EventRequestDTO;
import com.lovelace.eventsUserStories.dto.EventResponseDTO;
import com.lovelace.eventsUserStories.service.interfaces.IEventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final IEventService eventService;

    @Operation(summary = "Create a new event", description = "Creates an event and associates it with an existing Venue.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Event created successfully",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"id\": 10, \"nameEvent\": \"Rock Concert\", \"dateEvent\": \"2025-12-01\", \"venue\": {\"id\": 1, \"nameVenue\": \"Stadium\"}}"))),
            @ApiResponse(responseCode = "400", description = "Validation error (Past date, empty name)", content = @Content),
            @ApiResponse(responseCode = "404", description = "Specified Venue does not exist", content = @Content),
            @ApiResponse(responseCode = "409", description = "Event name already exists", content = @Content)
    })
    @PostMapping
    public ResponseEntity<EventResponseDTO> createEvent(@Valid @RequestBody EventRequestDTO requestDTO) {
        EventResponseDTO createdEvent = eventService.createEvent(requestDTO);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @Operation(summary = "Get paginated events", description = "Returns a page of events. Allows sorting and page size definition.")
    @ApiResponse(responseCode = "200", description = "Events page retrieved",
            content = @Content(mediaType = "application/json",
                    // SPRING DATA PAGINATION RESPONSE EXAMPLE
                    examples = @ExampleObject(value = """
                {
                  "content": [
                    {
                      "id": 1,
                      "nameEvent": "Flower Festival",
                      "dateEvent": "2025-08-05",
                      "venue": { "id": 2, "nameVenue": "Central Square", "address": "41st Street", "capacity": 5000 }
                    },
                    {
                      "id": 2,
                      "nameEvent": "Tech Summit",
                      "dateEvent": "2025-09-10",
                      "venue": { "id": 1, "nameVenue": "Inter Hotel", "address": "Downtown", "capacity": 300 }
                    }
                  ],
                  "pageable": {
                    "sort": { "sorted": true, "unsorted": false, "empty": false },
                    "pageNumber": 0,
                    "pageSize": 10,
                    "offset": 0,
                    "paged": true
                  },
                  "totalElements": 50,
                  "totalPages": 5,
                  "last": false,
                  "size": 10,
                  "number": 0
                }
                """)))
    @GetMapping
    public ResponseEntity<Page<EventResponseDTO>> getAllEvents(
            @Parameter(description = "Page number (starts at 0)")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Elements per page")
            @RequestParam(defaultValue = "10") int size,

            @Parameter(description = "Sort field")
            @RequestParam(defaultValue = "id") String sortField,

            @Parameter(description = "Sort direction (asc/desc)")
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(eventService.getAllEvents(pageable));
    }

    @Operation(summary = "Get event by ID", description = "Retrieves details of a specific event.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event found"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @Operation(summary = "Update an event", description = "Updates data of an existing event.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event updated successfully"),
            @ApiResponse(responseCode = "404", description = "Event or Venue not found"),
            @ApiResponse(responseCode = "400", description = "Invalid data")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EventResponseDTO> updateEvent(
            @PathVariable Long id,
            @Valid @RequestBody EventRequestDTO requestDTO
    ) {
        return ResponseEntity.ok(eventService.updateEvent(id, requestDTO));
    }

    @Operation(summary = "Delete an event", description = "Permanently removes an event.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Event deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
