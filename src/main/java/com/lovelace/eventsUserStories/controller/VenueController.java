package com.lovelace.eventsUserStories.controller;

import com.lovelace.eventsUserStories.dto.VenueRequestDTO;
import com.lovelace.eventsUserStories.dto.VenueResponseDTO;
import com.lovelace.eventsUserStories.service.interfaces.IVenueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Operation(summary = "Create a new venue", description = "Registers a new Venue in the database. Validates for duplicate names.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Venue created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VenueResponseDTO.class),
                            examples = @ExampleObject(value = "{\"id\": 1, \"nameVenue\": \"Grand Plaza Hall\", \"address\": \"123 Main St\", \"capacity\": 500}"))),
            @ApiResponse(responseCode = "400", description = "Invalid data (Empty name, negative capacity)", content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflict: Venue name already exists", content = @Content)
    })
    @PostMapping
    public ResponseEntity<VenueResponseDTO> createVenue(@Valid @RequestBody VenueRequestDTO requestDTO) {
        VenueResponseDTO createdVenue = venueService.createVenue(requestDTO);
        return new ResponseEntity<>(createdVenue, HttpStatus.CREATED);
    }

    @Operation(summary = "List all venues", description = "Returns a complete list of all registered venues.")
    @ApiResponse(responseCode = "200", description = "List retrieved successfully")
    @GetMapping
    public ResponseEntity<List<VenueResponseDTO>> getAllVenues() {
        return ResponseEntity.ok(venueService.getAllVenues());
    }

    @Operation(summary = "Get venue by ID", description = "Retrieves details of a specific venue.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venue found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VenueResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Venue not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<VenueResponseDTO> getVenueById(@PathVariable Long id) {
        return ResponseEntity.ok(venueService.getVenueById(id));
    }

    @Operation(summary = "Update a venue", description = "Modifies data of an existing venue.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venue updated successfully"),
            @ApiResponse(responseCode = "404", description = "Venue not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PutMapping("/{id}")
    public ResponseEntity<VenueResponseDTO> updateVenue(
            @PathVariable Long id,
            @Valid @RequestBody VenueRequestDTO requestDTO
    ) {
        return ResponseEntity.ok(venueService.updateVenue(id, requestDTO));
    }

    @Operation(summary = "Delete a venue", description = "Removes a venue from the database by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Venue deleted (No Content)"),
            @ApiResponse(responseCode = "404", description = "Venue not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenue(@PathVariable Long id) {
        venueService.deleteVenue(id);
        return ResponseEntity.noContent().build();
    }
}
