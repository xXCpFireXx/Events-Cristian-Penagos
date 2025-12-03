package com.lovelace.eventsUserStories.infrastructure.adapters.in.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovelace.eventsUserStories.infrastructure.adapters.in.web.dto.VenueRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers // 1. Activa la magia de Testcontainers
class VenueControllerTestcontainersTest {

    // 2. Definimos el contenedor. "static" para que se levante UNA vez para todos los tests de esta clase.
    @Container
    static MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // 3. Sobreescribimos la configuración de Spring para que se conecte al contenedor, NO a H2
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mysqlContainer::getUsername);
        registry.add("spring.datasource.password", mysqlContainer::getPassword);
        registry.add("spring.datasource.driver-class-name", () -> "com.mysql.cj.jdbc.Driver");

        // Importante: Decirle a Hibernate que estamos usando MySQL
        registry.add("spring.jpa.database-platform", () -> "org.hibernate.dialect.MySQL8Dialect");

        // Le decimos a Hibernate que cree las tablas en este MySQL nuevo
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
    }

    @Test
    @DisplayName("Debe crear un Venue usando MySQL Real (Testcontainers)")
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void createVenue_WithRealDB_Success() throws Exception {
        // --- GIVEN ---
        VenueRequestDTO request = new VenueRequestDTO();
        request.setNameVenue("Estadio Testcontainers");
        request.setAddress("Docker Avenue 777");
        request.setCapacity(50000);

        String jsonRequest = objectMapper.writeValueAsString(request);

        // --- WHEN & THEN ---
        mockMvc.perform(post("/venues")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nameVenue").value("Estadio Testcontainers"))
                .andExpect(jsonPath("$.id").exists());
    }
}