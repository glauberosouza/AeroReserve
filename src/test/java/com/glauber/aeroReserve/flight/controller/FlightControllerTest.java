package com.glauber.aeroReserve.flight.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glauber.aeroReserve.BaseCompTest;
import com.glauber.aeroReserve.templates.flightTemplate.FlightRequest;
import com.glauber.aeroReserve.templates.flightTemplate.FlightRequestTemplate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

}
/*
1. Teste para garantir que um cliente possa comprar uma passagem de avião com sucesso.
2. Teste para verificar se os detalhes da passagem (preço, destino, data de partida/chegada, etc.)
    estão corretos após a compra.
3. Teste para verificar se o cliente recebe um número de reserva após a compra da passagem.*/