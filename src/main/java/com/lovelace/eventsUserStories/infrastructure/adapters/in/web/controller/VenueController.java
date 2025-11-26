package com.lovelace.eventsUserStories.infrastructure.adapters.in.web.controller;

import com.lovelace.eventsUserStories.application.usecase.venue.*;
import com.lovelace.eventsUserStories.domain.model.Venue;
import com.lovelace.eventsUserStories.infrastructure.adapters.in.web.dto.VenueRequestDTO;
import com.lovelace.eventsUserStories.infrastructure.adapters.in.web.dto.VenueResponseDTO;
import com.lovelace.eventsUserStories.infrastructure.adapters.in.web.mapper.VenueWebMapper;
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
@RequestMapping("/venues")
@RequiredArgsConstructor
@Tag(name = "Venues", description = "Controller for managing event locations")
public class VenueController {

    private final CreateVenueUseCaseImpl createVenueUseCase;
    private final GetVenueByIdUseCaseImpl getVenueByIdUseCase;
    private final GetAllVenuesUseCaseImpl getAllVenuesUseCase;
    private final UpdateVenueUseCaseImpl updateVenueUseCase;
    private final DeleteVenueUseCaseImpl deleteVenueUseCase;

    private final VenueWebMapper venueWebMapper;

    @Operation(summary = "Create a new venue", description = "Registers a new venue (location) in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Venue created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = VenueResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflict: Venue name already exists", content = @Content)
    })
    @PostMapping
    public ResponseEntity<VenueResponseDTO> create(@Valid @RequestBody VenueRequestDTO requestDTO) {
        Venue venueInput = venueWebMapper.toDomain(requestDTO);
        Venue venueCreated = createVenueUseCase.createVenue(venueInput);
        return new ResponseEntity<>(venueWebMapper.toResponse(venueCreated), HttpStatus.CREATED);
    }

    @Operation(summary = "Get all venues", description = "Retrieves a paginated list of all registered venues.")
    @ApiResponse(responseCode = "200", description = "List of venues retrieved successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = VenueResponseDTO.class)))
    @GetMapping
    public ResponseEntity<List<VenueResponseDTO>> getAll(
            @Parameter(description = "Page number (0-based index)", example = "0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Size of the page", example = "10")
            @RequestParam(defaultValue = "10") int size
    ) {
        List<Venue> venues = getAllVenuesUseCase.getAllVenues(page, size);
        List<VenueResponseDTO> response = venues.stream()
                .map(venueWebMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get venue by ID", description = "Retrieves the details of a specific venue by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venue found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = VenueResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Venue not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<VenueResponseDTO> getById(@Parameter(description = "ID of the venue") @PathVariable Long id) {
        return getVenueByIdUseCase.getVenueById(id)
                .map(venue -> ResponseEntity.ok(venueWebMapper.toResponse(venue)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update a venue", description = "Updates the information of an existing venue.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venue updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = VenueResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content),
            @ApiResponse(responseCode = "404", description = "Venue not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<VenueResponseDTO> update(
            @Parameter(description = "ID of the venue to be updated") @PathVariable Long id,
            @Valid @RequestBody VenueRequestDTO requestDTO) {

        Venue venueInput = venueWebMapper.toDomain(requestDTO);
        return updateVenueUseCase.updateVenue(id, venueInput)
                .map(updated -> ResponseEntity.ok(venueWebMapper.toResponse(updated)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a venue", description = "Permanently removes a venue from the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Venue deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Venue not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Parameter(description = "ID of the venue to be deleted") @PathVariable Long id) {
        deleteVenueUseCase.deleteVenue(id);
        return ResponseEntity.noContent().build();
    }
}
