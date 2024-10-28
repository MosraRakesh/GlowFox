package com.glowfox.app.controller;

import com.glowfox.app.controller.ClassController;
import com.glowfox.app.model.dto.ClassDTO;
import com.glowfox.app.services.ClassService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ClassControllerTest {

    @InjectMocks
    private ClassController classController;

    @Mock
    private ClassService classService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createClass_shouldReturnCreatedClass() {
        // Arrange
        ClassDTO classDTO = new ClassDTO();
        classDTO.setClassName("Yoga");
        classDTO.setStartDate(LocalDate.now());
        classDTO.setEndDate(LocalDate.now().plusDays(5));
        classDTO.setCapacity(20);
        classDTO.setClassId(1L);

        when(classService.createClass(any(ClassDTO.class))).thenReturn(classDTO);

        // Act
        ResponseEntity<ClassDTO> response = classController.createClass(classDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(classDTO, response.getBody());
        verify(classService, times(1)).createClass(any(ClassDTO.class));
    }

    @Test
   public  void getAllClasses_shouldReturnListOfClasses() {
        // Arrange
        List<ClassDTO> classDTOList = new ArrayList<>();
        classDTOList.add(new ClassDTO());
        when(classService.getAllClasses()).thenReturn(classDTOList);

        // Act
        ResponseEntity<List<ClassDTO>> response = classController.getAllClasses();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(classDTOList, response.getBody());
        verify(classService, times(1)).getAllClasses();
    }
}
