package com.glauber.aeroReserve.flight.service;

import com.glauber.aeroReserve.flight.model.Flight;
import com.glauber.aeroReserve.flight.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class FlightServiceImpl implements IFlightService {
    private final FlightRepository repository;

    public FlightServiceImpl(FlightRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveFlight(Flight flight) {
        repository.save(flight);
    }

    @Override
    public void update(Long flightId, Flight flight) {
        var Flight = findFlightById(flightId);
        Flight.setDepartureDateTime(flight.getDepartureDateTime());
        Flight.setArrivalDateTime(flight.getArrivalDateTime());
    }

    @Override
    public Flight findFlightById(Long flightId) {
        var optionalFlight = repository.findById(flightId);
        return optionalFlight.orElseThrow(
                () -> new NoSuchElementException("Nenhum voo encontrado com o ID fornecido."));
    }

    @Override
    public void deleteFlight(Long flightById) {
        var flightToDelete = findFlightById(flightById);
        repository.delete(flightToDelete);
    }

}
