package com.glowfox.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.glowfox.app.dao.model.ClassEntity;
import com.glowfox.app.exception.ClassScheduleConflictException;
import com.glowfox.app.exception.DuplicateClassException;
import com.glowfox.app.exception.InvalidCapacityException;
import com.glowfox.app.exception.InvalidDateException;
import com.glowfox.app.model.dto.ClassDTO;
import com.glowfox.app.repository.ClassRepository;

@Service
public class ClassService {

	private ModelMapper modelMapper;
	private ClassRepository classRepository;

	public ClassService(ClassRepository classRepository, ModelMapper modelMapper) {
		this.classRepository = classRepository;
		this.modelMapper = modelMapper;
	}

	public ClassDTO createClass(ClassDTO classDTO) {
		List<ClassEntity> existingClasses = classRepository.findAll();
		for (ClassEntity existingClass : existingClasses) {
			if (classDTO.getStartDate().isBefore(existingClass.getEndDate())
					&& classDTO.getEndDate().isAfter(existingClass.getStartDate())) {
				throw new ClassScheduleConflictException("Class schedule conflicts with an existing class.");
			}
		}
		// Check if class dates are valid
		if (classDTO.getStartDate().isAfter(classDTO.getEndDate())) {
			throw new InvalidDateException("Start date cannot be after end date.");
		}

		if (classDTO.getCapacity() < 1) {
			throw new InvalidCapacityException("no Capacity for a class");
		}
		// Duplicate Class Check
		Optional<ClassEntity> existingClass = classRepository.findByClassNameAndStartDateAndEndDate(
				classDTO.getClassName(), classDTO.getStartDate(), classDTO.getEndDate());
		if (existingClass.isPresent()) {
			throw new DuplicateClassException("A class with this name and schedule already exists.");
		}

		// Save class if no conflicts
		ClassEntity classEntity = modelMapper.map(classDTO, ClassEntity.class);
		classRepository.save(classEntity);
		classDTO.setClassId(classEntity.getId());
		return classDTO;
	}

	public List<ClassDTO> getAllClasses() {
		return classRepository.findAll().stream().map(classEntity -> modelMapper.map(classEntity, ClassDTO.class))
				.collect(Collectors.toList());
	}
}
