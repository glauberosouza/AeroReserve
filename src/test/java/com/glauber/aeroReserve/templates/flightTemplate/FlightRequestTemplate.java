package com.glauber.aeroReserve.templates.flightTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;

public class FlightRequestTemplate {

    public static FlightRequest creation() {
        LocalDateTime departureDateTime = LocalDateTime.now().plusDays(2);
        LocalDateTime arrivalDateTime = LocalDateTime.of(
                departureDateTime.getYear(),
                departureDateTime.getMonth(),
                departureDateTime.getDayOfMonth(),
                21, 0).plusDays(4);
        return new FlightRequest(
                "1A",
                "São Paulo",
                "Rio Grande do Sul",
                departureDateTime,
                arrivalDateTime,
                64,
                BigDecimal.valueOf(120.0)
        );
    }
}
