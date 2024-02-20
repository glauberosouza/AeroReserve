package com.glauber.aeroReserve.flight.controller.request;

import com.glauber.aeroReserve.flight.model.Flight;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FlightRequest(
        @NotBlank(message = "O campo não pode ficar em branco!")
        String flightNumber,
        @NotBlank(message = "O campo não pode ficar em branco!")
        String origin,
        @NotBlank(message = "O campo não pode ficar em branco!")
        String destination,
        @NotNull(message = "A data de partida não pode ser nula!")
        @FutureOrPresent(message = "A data de partida deve ser no futuro!")
        LocalDateTime departureDateTime,
        @NotNull(message = "A data de chegada não pode ser nula!")
        @Future(message = "A data de chegada deve ser no futuro!")
        LocalDateTime arrivalDateTime,
        @Min(value = 40, message = "O número mínimo de passageiros é de 40")
        int passagerCount,
        @NotNull(message = "O preço não pode ser nulo!")
        @Positive(message = "O preço deve ser um número positivo!")
        BigDecimal price
) {
        public static Flight toFlightEntity(FlightRequest flightRequest) {
                var flight = new Flight();
                flight.setFlightNumber(flightRequest.flightNumber());
                flight.setOrigin(flightRequest.origin());
                flight.setDestination(flightRequest.destination());
                flight.setDepartureDateTime(flightRequest.departureDateTime());
                flight.setArrivalDateTime(flightRequest.arrivalDateTime());
                flight.setPassagerCount(flightRequest.passagerCount());
                flight.setPrice(flightRequest.price());
                return flight;
        }
}

