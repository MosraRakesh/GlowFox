package com.glowfox.app.exception;
public class BookingDateConflictException extends RuntimeException {

    public BookingDateConflictException(String message) {
        super(message);
    }
}
