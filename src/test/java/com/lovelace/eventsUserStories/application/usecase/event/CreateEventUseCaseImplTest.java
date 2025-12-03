package com.lovelace.eventsUserStories.application.usecase.event;

import com.lovelace.eventsUserStories.domain.exception.DuplicateResourceException;
import com.lovelace.eventsUserStories.domain.model.Event;
import com.lovelace.eventsUserStories.domain.ports.out.EventRepositoryPort;
import com.lovelace.eventsUserStories.domain.ports.out.VenueRepositoryPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateEventUseCaseImplTest {

    // Mockeamos el repositorio de eventos
    @Mock
    private EventRepositoryPort eventRepositoryPort;

    // Mockeamos TAMBIÉN el repositorio de venues (porque el UseCase lo necesita para validar)
    @Mock
    private VenueRepositoryPort venueRepositoryPort;

    // Inyectamos ambos mocks en nuestro UseCase
    @InjectMocks
    private CreateEventUseCaseImpl createEventUseCase;

    @Test
    @DisplayName("Debe crear un Evento exitosamente cuando el nombre no existe y el Venue sí existe")
    void createEvent_Success() {
        // --- GIVEN ---
        // Un evento ficticio para probar
        Event eventToCreate = new Event(null, "Concierto Rock", 1L, LocalDateTime.now(), LocalDateTime.now().plusHours(2));
        Event savedEvent = new Event(10L, "Concierto Rock", 1L, LocalDateTime.now(), LocalDateTime.now().plusHours(2));

        // Simulamos: El evento NO existe previamente
        when(eventRepositoryPort.existsByNameEvent("Concierto Rock")).thenReturn(false);
        // Simulamos: El Venue asociado (ID 1) SÍ existe
        when(venueRepositoryPort.existsById(1L)).thenReturn(true);
        // Simulamos: El guardado retorna el evento con ID
        when(eventRepositoryPort.save(eventToCreate)).thenReturn(savedEvent);

        // --- WHEN ---
        Event result = createEventUseCase.createEvent(eventToCreate);

        // --- THEN ---
        assertNotNull(result);
        assertEquals(10L, result.getId());

        // Verificamos que se llamó a guardar
        verify(eventRepositoryPort).save(eventToCreate);
    }

    @Test
    @DisplayName("Debe lanzar excepción si el Venue no existe")
    void createEvent_ThrowsException_WhenVenueNotFound() {
        // --- GIVEN ---
        Event eventToCreate = new Event(null, "Fiesta Privada", 99L, LocalDateTime.now(), LocalDateTime.now().plusHours(5));

        // Simulamos: El nombre NO está duplicado (pasa la primera validación)
        when(eventRepositoryPort.existsByNameEvent("Fiesta Privada")).thenReturn(false);
        // Simulamos: Pero el Venue (ID 99) NO existe (falla la segunda validación)
        when(venueRepositoryPort.existsById(99L)).thenReturn(false);

        // --- WHEN & THEN ---
        // Esperamos RuntimeException (según tu código actual)
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            createEventUseCase.createEvent(eventToCreate);
        });

        assertEquals("Venue not found with id: 99", exception.getMessage());

        // Verificamos que NUNCA se guardó nada
        verify(eventRepositoryPort, never()).save(any());
    }

    @Test
    @DisplayName("Debe lanzar excepción si el nombre del Evento ya existe")
    void createEvent_ThrowsException_WhenNameExists() {
        // --- GIVEN ---
        Event eventToCreate = new Event(null, "Evento Duplicado", 1L, LocalDateTime.now(), LocalDateTime.now());

        // Simulamos: El nombre YA existe
        when(eventRepositoryPort.existsByNameEvent("Evento Duplicado")).thenReturn(true);

        // --- WHEN & THEN ---
        assertThrows(DuplicateResourceException.class, () -> {
            createEventUseCase.createEvent(eventToCreate);
        });

        // Nota: Como falló en la primera línea del método, ni siquiera debería haber consultado por el Venue
        verify(venueRepositoryPort, never()).existsById(any());
        verify(eventRepositoryPort, never()).save(any());
    }
}