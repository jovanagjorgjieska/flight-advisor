package com.example.flightadvisor.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AirportNotFoundException extends RuntimeException{
    public AirportNotFoundException(String code) {
        super(String.format("Airport with code: %s is not found", code));
    }

    public AirportNotFoundException() {
        super("Airport not found");
    }
}
