package com.glauber.aeroReserve.flight.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String flightNumber;
    @Column
    private String origin;
    @Column
    private String destination;
    @Column(name = "DEPARTURE_DATETIME")
    private LocalDateTime departureDateTime;
    @Column(name = "ARRIVAL_DATETIME")
    private LocalDateTime arrivalDateTime;
    @Column(name = "PASSEGER_COUNT")
    private int passagerCount;
    @Column
    private BigDecimal price;

    public Flight(String flightNumber, String origin, String destination, LocalDateTime departureDateTime, LocalDateTime arrivalDateTime, int passagerCount, BigDecimal price) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.passagerCount = passagerCount;
        this.price = price;
    }

    public Flight() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public int getPassagerCount() {
        return passagerCount;
    }

    public void setPassagerCount(int passagerCount) {
        this.passagerCount = passagerCount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
