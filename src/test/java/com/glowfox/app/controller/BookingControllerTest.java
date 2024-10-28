package com.glowfox.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glowfox.app.model.dto.BookingDTO;
import com.glowfox.app.services.BookingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.*;

@WebMvcTest(BookingController.class)
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testBookClass_Success() throws Exception {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setMemberName("John Doe");
        bookingDTO.setBookingDate(LocalDate.now());

        when(bookingService.bookClass(any())).thenReturn(bookingDTO);

        mockMvc.perform(post("/api/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookingDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.memberName").value("John Doe"));
    }
}
