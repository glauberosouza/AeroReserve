package com.glauber.aeroReserve.flight.controller;

import com.glauber.aeroReserve.flight.controller.request.FlightRequest;
import com.glauber.aeroReserve.flight.controller.response.FlightResponse;
import com.glauber.aeroReserve.flight.model.Flight;
import com.glauber.aeroReserve.flight.service.IFlightService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{flightId}")
    public ResponseEntity<FlightResponse> findFlighById(@PathVariable Long flightId) {
        var flightById = flightService.findFlightById(flightId);
        var flightResponse = FlightResponse.from(flightById);
        return ResponseEntity.status(HttpStatus.OK).body(flightResponse);
    }

    @PutMapping("/update/{flightId}")
    public ResponseEntity<Void> updateFlight(@PathVariable Long flightId, @RequestBody @Valid FlightRequest flightRequest) {
        var flightEntity = FlightRequest.toFlightEntity(flightRequest);
        flightService.update(flightId, flightEntity);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}