package com.glowfox.app.exception;
public class CapacityExceededException extends RuntimeException {

    public CapacityExceededException(String message) {
        super(message);
    }
}
