package com.lovelace.eventsUserStories.infrastructure.adapters.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VenueResponseDTO {
    private Long id;
    private String nameVenue;
    private String address;
    private int capacity;
}
