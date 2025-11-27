package com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.adapter;
import com.lovelace.eventsUserStories.domain.model.Event;
import com.lovelace.eventsUserStories.domain.ports.out.EventRepositoryPort;
import com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.entity.EventEntity;
import com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.mapper.EventDboMapper;
import com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.repository.ISpringEventRepository;
import com.lovelace.eventsUserStories.infrastructure.adapters.out.jpa.specification.EventSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
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

   /* @Override
    public List<Event> findAll(int page, int size) {
        // Conversión de Dominio (int) a Infraestructura (Pageable)
        List<EventEntity> entityList = springRepository.findAllEventsWithVenue();
        return eventDboMapper.toDomainList(entityList);
    }*/

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

    @Override
    public List<Event> findAllWithFilters(Long venueId, String name) {
        // 1. Comenzamos con una especificación vacía (o 'null' segura)
        Specification<EventEntity> spec = Specification.where(null);

        // 2. Si nos enviaron un venueId, agregamos esa regla a la consulta
        if (venueId != null) {
            spec = spec.and(EventSpecification.hasVenueId(venueId));
        }

        // 3. Si nos enviaron un nombre, agregamos esa regla
        if (name != null && !name.isEmpty()) {
            spec = spec.and(EventSpecification.hasNameLike(name));
        }

        // 4. Ejecutamos la consulta usando las especificaciones acumuladas
        // IMPORTANTE: Asegúrate de que tu ISpringEventRepository extienda 'JpaSpecificationExecutor'
        List<EventEntity> entities = springRepository.findAll(spec);

        // 5. Convertimos a Dominio y retornamos
        return eventDboMapper.toDomainList(entities);
    }
}
