package com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.adapter;
import com.lovelace.eventsUserStories.domain.model.Event;
import com.lovelace.eventsUserStories.domain.ports.out.EventRepositoryPort;
import com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.entity.EventEntity;
import com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.mapper.EventDboMapper;
import com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.repository.ISpringEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EventJpaAdapter implements EventRepositoryPort {

    private final ISpringEventRepository springRepository;
    private final EventDboMapper eventDboMapper;


    @Override
    public Event save(Event event) {
        EventEntity entity = eventDboMapper.toEntity(event);
        EventEntity saved = springRepository.save(entity);
        return eventDboMapper.toDomain(saved);
    }

    @Override
    public Optional<Event> findById(Long id) {
        return springRepository.findById(id)
                .map(eventDboMapper::toDomain);
    }

    @Override
    public List<Event> findAll(int page, int size) {
        // Conversión de Dominio (int) a Infraestructura (Pageable)
        return springRepository.findAll(PageRequest.of(page, size))
                .map(eventDboMapper::toDomain)
                .toList();
    }

    @Override
    public void delete(Long id) {
        springRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return springRepository.existsById(id);
    }

    @Override
    public boolean existsByNameEvent(String name) {
        return springRepository.existsByNameEvent(name);
    }
}
