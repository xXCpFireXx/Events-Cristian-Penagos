package com.lovelace.eventsUserStories.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VenueRequestDTO {

    @NotBlank(message = "The name of the venue cannot be empty")
    private String nameVenue;

    private String address;

    @Min(value = 1, message = "The capacity must be at least 1")
    private int capacity;
}
