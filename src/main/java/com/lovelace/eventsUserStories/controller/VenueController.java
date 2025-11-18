package com.lovelace.eventsUserStories.controller;

import com.lovelace.eventsUserStories.dto.VenueRequestDTO;
import com.lovelace.eventsUserStories.dto.VenueResponseDTO;
import com.lovelace.eventsUserStories.service.interfaces.IVenueService;
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
@RequestMapping("/venues")
@RequiredArgsConstructor
public class VenueController {
    private final IVenueService venueService;

    @Operation(summary = "Create a new venue")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Venue created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "id": 1,
                                              "nameVenue": "Metropolitan Stadium",
                                              "address": "45 Main St, City Center",
                                              "capacity": 45000
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Invalid request or malformed JSON", content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflict: Venue name already exists", content = @Content)
    })
    @PostMapping
    public ResponseEntity<VenueResponseDTO> createVenue(@Valid @RequestBody VenueRequestDTO requestDTO) {
        VenueResponseDTO createdVenue = venueService.createVenue(requestDTO);
        return new ResponseEntity<>(createdVenue, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all venues")
    @ApiResponse(
            responseCode = "200",
            description = "List of venues retrieved successfully",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            value = """
                                    [
                                      {
                                        "id": 1,
                                        "nameVenue": "Metropolitan Stadium",
                                        "address": "45 Main St",
                                        "capacity": 45000
                                      },
                                      {
                                        "id": 2,
                                        "nameVenue": "Grand Theater",
                                        "address": "100 Broadway Ave",
                                        "capacity": 1200
                                      }
                                    ]
                                    """
                    )
            )
    )
    @GetMapping
    public ResponseEntity<List<VenueResponseDTO>> getAllVenues() {
        return ResponseEntity.ok(venueService.getAllVenues());
    }

    @Operation(summary = "Get a venue by ID")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Venue found",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "id": 1,
                                              "nameVenue": "Metropolitan Stadium",
                                              "address": "45 Main St",
                                              "capacity": 45000
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Venue not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<VenueResponseDTO> getVenueById(@PathVariable Long id) {
        return ResponseEntity.ok(venueService.getVenueById(id));
    }

    @Operation(summary = "Update an existing venue")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Venue updated successfully",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "id": 1,
                                              "nameVenue": "Metropolitan Stadium Renovated",
                                              "address": "45 Main St",
                                              "capacity": 50000
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Venue not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<VenueResponseDTO> updateVenue(@PathVariable Long id, @Valid @RequestBody VenueRequestDTO requestDTO) {
        return ResponseEntity.ok(venueService.updateVenue(id, requestDTO));
    }

    @Operation(summary = "Delete a venue")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Venue deleted successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Venue not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenue(@PathVariable Long id) {
        venueService.deleteVenue(id);
        return ResponseEntity.noContent().build();
    }
}
