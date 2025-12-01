package com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.repository;

import com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ISpringUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
