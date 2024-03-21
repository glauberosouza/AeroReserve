package com.glauber.aeroReserve.passenger.controller;

import com.glauber.aeroReserve.passenger.service.IPassangerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/passangers")
public class PassangerController {
    private final IPassangerService passangerService;

    public PassangerController(IPassangerService passangerService) {
        this.passangerService = passangerService;
    }
}
