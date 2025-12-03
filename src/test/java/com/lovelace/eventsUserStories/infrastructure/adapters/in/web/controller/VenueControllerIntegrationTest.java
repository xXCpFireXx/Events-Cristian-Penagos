package com.lovelace.eventsUserStories.infrastructure.adapters.in.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovelace.eventsUserStories.infrastructure.adapters.in.web.dto.VenueRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 1. @SpringBootTest: Levanta TODA la aplicación (contexto completo)
@SpringBootTest
// 2. @AutoConfigureMockMvc: Configura automáticamente la herramienta para hacer peticiones falsas
@AutoConfigureMockMvc
class VenueControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc; // Tu "Postman" virtual

    @Autowired
    private ObjectMapper objectMapper; // Para convertir objetos Java a JSON

    @Test
    @DisplayName("Debe crear un Venue exitosamente (Integración completa)")
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void createVenue_Success() throws Exception {
        // --- GIVEN ---
        // Preparamos el DTO que enviaría el usuario (asegúrate que tu DTO tenga estos campos)
        VenueRequestDTO request = new VenueRequestDTO();
        request.setNameVenue("Gran Salón de Pruebas");
        request.setAddress("Avenida Test 123");
        request.setCapacity(500);

        // Convertimos el objeto a JSON String
        String jsonRequest = objectMapper.writeValueAsString(request);

        // --- WHEN & THEN ---
        // Realizamos la petición POST a /venues (ajusta la ruta si tu controlador usa otra, ej: /api/venues)
        mockMvc.perform(post("/venues")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                // Verificamos que responda 201 Created (o 200 OK, según tu controlador)
                .andExpect(status().isCreated())
                // Verificamos que el JSON de respuesta tenga el nombre correcto
                .andExpect(jsonPath("$.nameVenue").value("Gran Salón de Pruebas"))
                .andExpect(jsonPath("$.id").exists()); // Debe haber generado un ID
    }
}