package com.glauber.aeroReserve.flight.controller.response;

import com.glauber.aeroReserve.flight.model.Flight;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FlightResponse(
        Long id,
        String flightNumber,
        String origin,
        String destination,
        LocalDateTime departureDateTime,
        LocalDateTime arrivalDateTime,
        int passagerCount,
        BigDecimal price
) {
    public static FlightResponse from(Flight flight) {
        return new FlightResponse(
                flight.getId(),
                flight.getFlightNumber(),
                flight.getOrigin(),
                flight.getDestination(),
                flight.getDepartureDateTime(),
                flight.getArrivalDateTime(),
                flight.getPassagerCount(),
                flight.getPrice()
        );
    }
}
