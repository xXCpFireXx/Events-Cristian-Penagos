package com.lovelace.eventsUserStories.repository.interfaces;

import com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.entity.VenueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueJpaRepository extends JpaRepository<VenueEntity, Long> {
    boolean existsByNameVenue(String nameVenue);
}
