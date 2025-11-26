package com.lovelace.eventsUserStories.infrastructure.adapters.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventRequestDTO {

    @Schema(example = "Summer Rock Festival", description = "Name of the event")
    @NotBlank(message = "The event name is required")
    private String nameEvent;

    @Schema(example = "1", description = "ID of the venue where the event will take place")
    @NotNull(message = "Venue ID is required")
    private Long idVenue;
}
