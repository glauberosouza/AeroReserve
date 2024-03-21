package com.glauber.aeroReserve.passenger.service;

import com.glauber.aeroReserve.passenger.model.Passenger;

public interface IPassangerService {
    void save (Passenger passenger);
    void update (Long id, Passenger passenger);
    Passenger findById(Long id);
    void delete(Long id);
}
