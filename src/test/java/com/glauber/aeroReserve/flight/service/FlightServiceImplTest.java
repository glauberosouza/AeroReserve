package com.glauber.aeroReserve.flight.service;

import com.glauber.aeroReserve.flight.model.Flight;
import com.glauber.aeroReserve.flight.repository.FlightRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FlightServiceImplTest {

    @Mock
    private FlightRepository flightRepository;
    @InjectMocks
    private FlightServiceImpl flightService;

    @Test
    @DisplayName("Deve salvar um voo com sucesso")
    public void shouldVerifyIfHadMatchBetweenProductPriceAndRangeAlert() {
        // GIVEN
        var flight = new Flight();

        flight.setFlightNumber("1A");
        flight.setOrigin("Rio Grande do Sul");
        flight.setDestination("São Paulo");
        flight.setDepartureDateTime(LocalDateTime.now());
        flight.setArrivalDateTime(LocalDateTime.now().plusDays(3));
        flight.setPassagerCount(64);
        flight.setPrice(BigDecimal.valueOf(120.0));

        // WHEN
        when(flightRepository.save(any(Flight.class))).thenReturn(flight);
        flightService.saveFlight(flight);
        // THEN
        assertEquals("São Paulo", flight.getDestination());
    }
}