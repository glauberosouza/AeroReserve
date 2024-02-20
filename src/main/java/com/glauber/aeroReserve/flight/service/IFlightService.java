package com.glauber.aeroReserve.flight.service;

import com.glauber.aeroReserve.flight.model.Flight;

public interface IFlightService {
    void saveFlight(Flight flight);
    void update(Long flightId, Flight flight);
    Flight findFlightById(Long id);
    void deleteFlight(Long id);
}
