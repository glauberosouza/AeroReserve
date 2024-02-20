package com.glauber.aeroReserve.flight.repository;

import com.glauber.aeroReserve.flight.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
