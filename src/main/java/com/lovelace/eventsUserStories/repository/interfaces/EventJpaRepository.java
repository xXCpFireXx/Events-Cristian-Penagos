package com.lovelace.eventsUserStories.repository.interfaces;

import com.lovelace.eventsUserStories.entity.EventEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;

public interface EventJpaRepository extends JpaRepository<EventEntity, Long> {
    Page<EventEntity> findAll(Pageable pageable);
    boolean existsByNameEvent(String nameEvent);
}
