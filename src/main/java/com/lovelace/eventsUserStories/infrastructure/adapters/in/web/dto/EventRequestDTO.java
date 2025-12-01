package com.lovelace.eventsUserStories.infrastructure.adapters.in.web.dto;

import com.lovelace.eventsUserStories.infrastructure.adapters.in.web.validation.OnCreate;
import com.lovelace.eventsUserStories.infrastructure.adapters.in.web.validation.OnUpdate;
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
@ValidDateRange(groups = {OnCreate.class, OnUpdate.class})
public class EventRequestDTO {

    @Schema(example = "Summer Rock Festival", description = "Name of the event")
    @NotBlank(message = "The event name is required", groups = {OnCreate.class, OnUpdate.class})
    private String nameEvent;

    @Schema(example = "1", description = "ID of the venue where the event will take place")
    @NotNull(message = "Venue ID is required", groups = {OnCreate.class, OnUpdate.class})
    private Long idVenue;

    @NotNull(message = "The start date is required", groups = {OnCreate.class, OnUpdate.class})
    @Future(message = "The start date must be in the future", groups = {OnCreate.class})
    private LocalDateTime startDate;

    @NotNull(message = "The end date is required", groups = {OnCreate.class, OnUpdate.class})
    private LocalDateTime endDate;
}
