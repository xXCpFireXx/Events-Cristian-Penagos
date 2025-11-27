package com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(
            mappedBy = "venue",        // Indica que el dueño de la relación es el campo 'venue' en EventEntity
            cascade = CascadeType.ALL, // Si eliminas el Venue, se eliminan sus Eventos
            orphanRemoval = true,      // Si sacas un evento de esta lista, se borra de la BD
            fetch = FetchType.LAZY     // Carga perezosa: No trae los eventos a menos que se pidan
    )
    @ToString.Exclude // Importante: Evita bucles infinitos al imprimir logs
    private List<EventEntity> events = new ArrayList<>();
}
