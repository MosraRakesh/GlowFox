package com.glowfox.app.controller;

import com.glowfox.app.exception.ClassesNotFoundException;
import com.glowfox.app.model.dto.BookingDTO;
import com.glowfox.app.services.BookingService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

public class BookingServiceTest {

    @Mock
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBookClass_Success() {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setMemberName("John Doe");
        bookingDTO.setBookingDate(LocalDate.now());
        
        when(bookingService.bookClass(any())).thenReturn(bookingDTO);
        
        BookingDTO result = bookingService.bookClass(bookingDTO);
        assertEquals("John Doe", result.getMemberName());
    }

    @Test
    void testBookClass_ClassNotFound() {
        when(bookingService.bookClass(any())).thenThrow(new ClassesNotFoundException("Class not found"));
        
        assertThrows(ClassesNotFoundException.class, () -> bookingService.bookClass(new BookingDTO()));
    }
}
