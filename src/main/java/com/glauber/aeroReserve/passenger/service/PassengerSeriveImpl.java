package com.glauber.aeroReserve.passenger.service;

import com.glauber.aeroReserve.passenger.model.Passenger;
import com.glauber.aeroReserve.passenger.repository.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class PassengerSeriveImpl implements IPassangerService {
    private final PassengerRepository repository;

    public PassengerSeriveImpl(PassengerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Passenger passenger) {
        repository.save(passenger);
    }

    @Override
    public void update(Long id, Passenger passenger) {
        var passengerById = findById(id);
        passengerById.setFirstName(passenger.getFirstName());
        passengerById.setLastName(passenger.getLastName());
        passengerById.setEmail(passenger.getEmail());
        repository.save(passengerById);
    }

    @Override
    public Passenger findById(Long id) {
        var passengerById = repository.findById(id);
        return passengerById.orElseThrow(
                () -> new NoSuchElementException("Não foi encontrado um passageiro com o id informado!"));
    }

    @Override
    public void delete(Long id) {
        var passangerToDelete = findById(id);
        repository.delete(passangerToDelete);
    }
}
