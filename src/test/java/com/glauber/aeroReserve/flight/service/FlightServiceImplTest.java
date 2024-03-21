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
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


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
    public void shouldSaveFlight() {
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

    @Test
    @DisplayName("Não Deve salvar um voo caso encontre campos vazios ou nulos")
    public void shouldNotSaveFlight() {
        // GIVEN
        var flight = new Flight();
        flight = null;


        // WHEN
        flightService.saveFlight(flight);
        var allFlights = flightRepository.findAll();
        // THEN
        assertNull(flight);
        assertEquals(true, allFlights.isEmpty());
    }

    @Test
    @DisplayName("Deve atualizar um voo com sucesso")
    public void shouldUpdateFlight() {
        // GIVEN
        var flight = new Flight();
        flight.setId(1L);
        flight.setFlightNumber("1A");
        flight.setOrigin("Rio Grande do Sul");
        flight.setDestination("São Paulo");
        flight.setDepartureDateTime(LocalDateTime.now());
        flight.setArrivalDateTime(LocalDateTime.now().plusDays(3));
        flight.setPassagerCount(64);
        flight.setPrice(BigDecimal.valueOf(120.0));

        when(flightRepository.save(any(Flight.class))).thenReturn(flight);
        flightService.saveFlight(flight);

        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));

        var flightToUpdate = new Flight();

        var expectedDepartureDateTime = LocalDateTime.now().plusDays(1).truncatedTo(ChronoUnit.SECONDS);
        var expectedArrivalDateTime = LocalDateTime.now().plusDays(4).truncatedTo(ChronoUnit.SECONDS);

        flightToUpdate.setDepartureDateTime(expectedDepartureDateTime);
        flightToUpdate.setArrivalDateTime(expectedArrivalDateTime);

        // WHEN
        flightService.update(1L, flightToUpdate);
        // THEN

        assertEquals(LocalDateTime.now().plusDays(1).truncatedTo(ChronoUnit.SECONDS), flight.getDepartureDateTime().truncatedTo(ChronoUnit.SECONDS));
    }

    @Test
    @DisplayName("Deve encontrar um voo pelo id")
    public void shouldFindAFlightById() {
        // GIVEN
        var flight = new Flight();
        flight.setId(1L);
        flight.setFlightNumber("1A");
        flight.setOrigin("Rio Grande do Sul");
        flight.setDestination("São Paulo");
        flight.setDepartureDateTime(LocalDateTime.now());
        flight.setArrivalDateTime(LocalDateTime.now().plusDays(3));
        flight.setPassagerCount(64);
        flight.setPrice(BigDecimal.valueOf(120.0));

        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));

        // WHEN
        var flightById = flightService.findFlightById(1L);
        // THEN

        assertEquals(1L, flightById.getId());
        assertEquals("Rio Grande do Sul", flightById.getOrigin());
    }

    @Test
    @DisplayName("Deve deletar um voo pelo id")
    public void shouldDeleteAFlightById() {
        // GIVEN
        var flight = new Flight();
        flight.setId(1L);
        flight.setFlightNumber("1A");
        flight.setOrigin("Rio Grande do Sul");
        flight.setDestination("São Paulo");
        flight.setDepartureDateTime(LocalDateTime.now());
        flight.setArrivalDateTime(LocalDateTime.now().plusDays(3));
        flight.setPassagerCount(64);
        flight.setPrice(BigDecimal.valueOf(120.0));

        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));

        // WHEN
        flightService.deleteFlight(1L);
        var allFlight = flightRepository.findAll();
        // THEN
        assertEquals(0, allFlight.size());
        assertTrue(allFlight.isEmpty());
    }

}