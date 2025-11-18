package com.lovelace.eventsUserStories.controller;

import com.lovelace.eventsUserStories.dto.EventRequestDTO;
import com.lovelace.eventsUserStories.dto.EventResponseDTO;
import com.lovelace.eventsUserStories.service.interfaces.IEventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final IEventService eventService;

    @Operation(summary = "Create a new event")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Event created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "id": 10,
                                              "nameEvent": "Summer Rock Festival",
                                              "venue": {
                                                "id": 1,
                                                "nameVenue": "Metropolitan Stadium",
                                                "address": "45 Main St",
                                                "capacity": 45000
                                              }
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Invalid event data", content = @Content),
            @ApiResponse(responseCode = "404", description = "Associated Venue not found", content = @Content)
    })
    @PostMapping
    public ResponseEntity<EventResponseDTO> createEvent(@Valid @RequestBody EventRequestDTO requestDTO) {
        EventResponseDTO createdEvent = eventService.createEvent(requestDTO);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all events")
    @ApiResponse(
            responseCode = "200",
            description = "List of events retrieved",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            value = """
                                    [
                                      {
                                        "id": 10,
                                        "nameEvent": "Summer Rock Festival",
                                        "venue": {
                                          "id": 1,
                                          "nameVenue": "Metropolitan Stadium",
                                          "address": "45 Main St",
                                          "capacity": 45000
                                        }
                                      },
                                      {
                                        "id": 11,
                                        "nameEvent": "Tech Conference 2025",
                                        "venue": {
                                          "id": 2,
                                          "nameVenue": "Convention Center",
                                          "address": "10 Central Ave",
                                          "capacity": 5000
                                        }
                                      }
                                    ]
                                    """
                    )
            )
    )
    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @Operation(summary = "Get an event by ID")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Event found",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "id": 10,
                                              "nameEvent": "Summer Rock Festival",
                                              "venue": {
                                                "id": 1,
                                                "nameVenue": "Metropolitan Stadium",
                                                "address": "45 Main St",
                                                "capacity": 45000
                                              }
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Event not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @Operation(summary = "Update an existing event")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Event updated successfully",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "id": 10,
                                              "nameEvent": "Winter Jazz Night",
                                              "venue": {
                                                "id": 2,
                                                "nameVenue": "Grand Theater",
                                                "address": "100 Broadway Ave",
                                                "capacity": 1200
                                              }
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Event not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<EventResponseDTO> updateEvent(@PathVariable Long id, @Valid @RequestBody EventRequestDTO requestDTO) {
        return ResponseEntity.ok(eventService.updateEvent(id, requestDTO));
    }

    @Operation(summary = "Delete an event")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Event deleted successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Event not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
