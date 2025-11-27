package com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.specification;
import com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.entity.EventEntity;
import org.springframework.data.jpa.domain.Specification;

public class EventSpecification {

    // Filtro por Venue ID
    public static Specification<EventEntity> hasVenueId(Long venueId) {
        return (root, query, criteriaBuilder) -> {
            if (venueId == null) {
                return null; // Si es nulo, no filtra nada
            }
            // "root.get("venue").get("id")" navega la relación Event -> Venue -> ID
            return criteriaBuilder.equal(root.get("venue").get("id"), venueId);
        };
    }

    // Filtro por Nombre del Evento (Búsqueda parcial)
    public static Specification<EventEntity> hasNameLike(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isEmpty()) {
                return null;
            }
            // Busca nombres parecidos (LIKE %nombre%) ignorando mayúsculas
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("nameEvent")), "%" + name.toLowerCase() + "%");
        };
    }
}
