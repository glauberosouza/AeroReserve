package com.glauber.aeroReserve.flight.controller;

import com.glauber.aeroReserve.flight.controller.request.FlightRequest;
import com.glauber.aeroReserve.flight.service.IFlightService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/flights")
public class FlightController {
    private final IFlightService flightService;

    public FlightController(IFlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping
    public ResponseEntity<Void> registerAFlight(@RequestBody @Valid FlightRequest flightRequest) {
        var flightEntity = FlightRequest.toFlightEntity(flightRequest);
        flightService.saveFlight(flightEntity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}