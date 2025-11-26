package com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
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
