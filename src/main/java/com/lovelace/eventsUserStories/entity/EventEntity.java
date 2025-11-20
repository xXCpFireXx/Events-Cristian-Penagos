package com.lovelace.eventsUserStories.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "event")
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, name = "name_event")
    private String nameEvent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_venue", nullable = false)
    private VenueEntity venue;
}
