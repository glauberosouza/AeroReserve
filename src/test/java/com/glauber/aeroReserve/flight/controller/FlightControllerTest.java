package com.glauber.aeroReserve.flight.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glauber.aeroReserve.BaseCompTest;
import com.glauber.aeroReserve.flight.service.IFlightService;
import com.glauber.aeroReserve.templates.flightTemplate.FlightRequestTemplate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FlightControllerTest extends BaseCompTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private IFlightService service;

    @DisplayName("Deve cadastrar um voo com sucesso")
    @Test
    void shouldRegisterAFlight() throws Exception {
        // GIVEN
        var flightRequest = FlightRequestTemplate.creation();

        var body = objectMapper.writeValueAsString(flightRequest);

        // WHEN
        mvc.perform(post("/v1/flights")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated());

        // THEN
    }

    @DisplayName("Não deve cadastrar um voo caso encontre campos vazios")
    @Test
    void shouldNotRegisterAFlight() throws Exception {
        // GIVEN
        var invalidFlight = FlightRequestTemplate.invalidFlight();

        var body = objectMapper.writeValueAsString(invalidFlight);

        // WHEN
        mvc.perform(post("/v1/flights")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().is4xxClientError());

        // THEN
        assertNotNull(invalidFlight);
        assertTrue(invalidFlight.passagerCount() < 1);
        assertTrue(invalidFlight.flightNumber().isEmpty());
        assertTrue(invalidFlight.destination().isEmpty());
        assertTrue(invalidFlight.origin().isEmpty());
    }

    @DisplayName("Deve encontrar um voo pelo id")
    @Sql(value = "/db.sql/insert_into_flight.sql")
    @Test
    void shouldFindAAFlightById() throws Exception {
        // GIVEN

        // WHEN
        mvc.perform(get("/v1/flights/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("origin", is("São Paulo")));

        // THEN
    }
    @Sql(value = {"/db.sql/insert_into_flight.sql", "/db.sql/update_flight.sql"})
    @DisplayName("Deve atualizar um voo")
    @Test
    void shouldUpdateAFlight() throws Exception {
        // GIVEN
        var flightById = service.findFlightById(1L);
        var body = objectMapper.writeValueAsString(flightById);

        // WHEN
        mvc.perform(put("/v1/flights/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());

        // THEN
        assertEquals(LocalDateTime.of(2024, 10, 11, 21, 0),
                flightById.getArrivalDateTime());
    }
    @DisplayName("Deve deletar um voo pelo id")
    @Sql(value = "/db.sql/insert_into_flight.sql")
    @Test
    void shouldDeleteAFlightById() throws Exception {
        // GIVEN

        // WHEN
        mvc.perform(delete("/v1/flights/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isNoContent());
        // THEN
    }
}
/*

3. Teste para verificar se o cliente recebe um número de reserva após a compra da passagem.*/