package com.glauber.aeroReserve.templates.flightTemplate;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FlightRequest(String flightNumber,
                            String origin,

                            String destination,

                            LocalDateTime departureDateTime,


                            LocalDateTime arrivalDateTime,

                            int passagerCount,

                            BigDecimal price) {


    public FlightRequest(String flightNumber, String origin, String destination, LocalDateTime departureDateTime, LocalDateTime arrivalDateTime, int passagerCount, BigDecimal price) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.passagerCount = passagerCount;
        this.price = price;
    }
}


