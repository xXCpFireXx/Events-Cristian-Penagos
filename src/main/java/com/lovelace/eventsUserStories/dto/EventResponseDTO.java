package com.lovelace.eventsUserStories.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // <-- Si usas Lombok
@NoArgsConstructor
@AllArgsConstructor
public class EventResponseDTO {
    private Long id;
    private String nameEvent;
    private VenueResponseDTO venue;
}
