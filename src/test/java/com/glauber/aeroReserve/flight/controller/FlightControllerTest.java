package com.glauber.aeroReserve.flight.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glauber.aeroReserve.BaseCompTest;
import com.glauber.aeroReserve.templates.flightTemplate.FlightRequestTemplate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FlightControllerTest extends BaseCompTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

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
        mvc.perform(get("/v1/flights" + 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // THEN
    }
}
/*

3. Teste para verificar se o cliente recebe um número de reserva após a compra da passagem.*/