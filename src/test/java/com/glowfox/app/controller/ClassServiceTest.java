package com.glowfox.app.controller;

import com.glowfox.app.exception.InvalidCapacityException;
import com.glowfox.app.model.dto.ClassDTO;
import com.glowfox.app.repository.ClassRepository;
import com.glowfox.app.services.ClassService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ClassServiceTest {

    @Mock
    private ClassRepository classRepository;

    @InjectMocks
    private ClassService classService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createClass_invalidCapacity_shouldThrowException() {
        ClassDTO classDTO = new ClassDTO();
        classDTO.setCapacity(-10); // Invalid capacity
        classDTO.setStartDate(LocalDate.now().plusDays(1)); // Set valid start date to avoid NPE
        classDTO.setEndDate(LocalDate.now().plusDays(10)); // Set valid end date

        assertThrows(InvalidCapacityException.class, () -> classService.createClass(classDTO));
    }
}
