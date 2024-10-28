package com.glowfox.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glowfox.app.model.dto.ClassDTO;
import com.glowfox.app.services.ClassService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api")
public class ClassController {

    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @PostMapping("/classes")
    public ResponseEntity<ClassDTO> createClass(@RequestBody  ClassDTO classDTO) {
        return new ResponseEntity<>(classService.createClass(classDTO), HttpStatus.CREATED);
    }
    
    @GetMapping("/classes")
    @Operation(summary = "Get Example", description = "Returns an example object")
    @ApiResponse(responseCode = "200", description = "Successful response")
    public ResponseEntity<List<ClassDTO>> getAllClasses() {
        return new ResponseEntity<>(classService.getAllClasses(), HttpStatus.OK);
    }
}
