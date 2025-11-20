package com.lovelace.eventsUserStories.entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "venue")
public class VenueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, name = "name_venue")
    private String nameVenue;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private int capacity;
}
