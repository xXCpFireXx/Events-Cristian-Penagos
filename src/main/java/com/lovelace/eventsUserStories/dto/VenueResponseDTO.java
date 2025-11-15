package com.lovelace.eventsUserStories.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // <-- Si usas Lombok
@NoArgsConstructor
@AllArgsConstructor
public class VenueResponseDTO {
    private Long id;
    private String nameVenue;
    private String address;
    private int capacity;
}
