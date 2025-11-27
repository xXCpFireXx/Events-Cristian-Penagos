package com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.repository;

import com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISpringEventRepository extends JpaRepository<EventEntity, Long>, JpaSpecificationExecutor<EventEntity> {
    boolean existsByNameEvent(String nameEvent);

    @Query("SELECT e FROM EventEntity e JOIN FETCH e.venue")
    List<EventEntity> findAllEventsWithVenue();
}
