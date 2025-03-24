package com.example.agregio.infrastructure.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParkNotFoundException extends RuntimeException {
    public ParkNotFoundException(String message) {
        super(message);
        log.error(message);
    }

    public ParkNotFoundException() {
        log.error("Park not found");
    }
}
