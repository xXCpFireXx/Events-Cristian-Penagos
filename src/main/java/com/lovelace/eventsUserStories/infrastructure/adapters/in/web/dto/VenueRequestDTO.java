package com.lovelace.eventsUserStories.infrastructure.adapters.in.web.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VenueRequestDTO {

    @Schema(example = "Metropolitan Stadium", description = "Name of the venue")
    @NotBlank(message = "The name of the venue cannot be empty")
    private String nameVenue;

    @Schema(example = "45 Main St, City Center", description = "Physical address of the venue")
    private String address;

    @Schema(example = "45000", description = "Maximum capacity of people")
    @Min(value = 1, message = "The capacity must be at least 1")
    private int capacity;
}
