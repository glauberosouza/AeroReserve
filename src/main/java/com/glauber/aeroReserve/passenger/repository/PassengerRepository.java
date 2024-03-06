package com.glauber.aeroReserve.passenger.repository;

import com.glauber.aeroReserve.passenger.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
