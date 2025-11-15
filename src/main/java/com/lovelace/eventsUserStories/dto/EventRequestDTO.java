package com.lovelace.eventsUserStories.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventRequestDTO {

    @NotBlank(message = "The event name is required")
    private String nameEvent;

    @NotNull(message = "Venue ID is required")
    Long idVenue;
}
