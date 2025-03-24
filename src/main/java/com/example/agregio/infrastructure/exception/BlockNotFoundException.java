package com.example.agregio.infrastructure.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BlockNotFoundException extends RuntimeException {
    public BlockNotFoundException(String message) {
        super(message);
        log.error(message);
    }

    public BlockNotFoundException() {
        log.error("Block not found");
    }
}
