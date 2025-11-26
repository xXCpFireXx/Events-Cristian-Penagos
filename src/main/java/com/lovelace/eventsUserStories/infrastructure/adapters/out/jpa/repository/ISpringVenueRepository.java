package com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.repository;

import com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.entity.VenueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISpringVenueRepository extends JpaRepository<VenueEntity,Long> {
    boolean existsByNameVenue(String nameVenue);
}
