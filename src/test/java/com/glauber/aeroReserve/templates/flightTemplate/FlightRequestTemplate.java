package com.glauber.aeroReserve.templates.flightTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    public static void update(FlightRequest creation) {
        creation.departureDateTime().plusDays(3);
        creation.arrivalDateTime().plusDays(5);
    }


    public static FlightRequest invalidFlight() {
        LocalDateTime departureDateTime = LocalDateTime.now().plusDays(2);
        LocalDateTime arrivalDateTime = LocalDateTime.of(
                departureDateTime.getYear(),
                departureDateTime.getMonth(),
                departureDateTime.getDayOfMonth(),
                21, 0).plusDays(4);
        return new FlightRequest(
                "",
                "",
                "",
                departureDateTime,
                arrivalDateTime,
                0,
                BigDecimal.valueOf(120.0)
        );
    }
}
