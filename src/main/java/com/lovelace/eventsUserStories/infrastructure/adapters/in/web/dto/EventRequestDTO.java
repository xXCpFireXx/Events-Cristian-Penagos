package com.lovelace.eventsUserStories.infrastructure.adapters.in.web.dto;

import com.lovelace.eventsUserStories.infrastructure.adapters.in.web.validation.ValidDateRange;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ValidDateRange
public class EventRequestDTO {

    @Schema(example = "Summer Rock Festival", description = "Name of the event")
    @NotBlank(message = "The event name is required")
    private String nameEvent;

    @Schema(example = "1", description = "ID of the venue where the event will take place")
    @NotNull(message = "Venue ID is required")
    private Long idVenue;

    @NotNull(message = "The start date is required")
    @Future(message = "The start date must be in the future")
    private LocalDateTime startDate;

    @NotNull(message = "The end date is required")
    private LocalDateTime endDate;
}
