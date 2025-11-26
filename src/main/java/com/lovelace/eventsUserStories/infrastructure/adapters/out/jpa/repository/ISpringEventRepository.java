package com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.repository;

import com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISpringEventRepository extends JpaRepository<EventEntity,Long> {
    boolean existsByNameEvent(String nameEvent);
}
