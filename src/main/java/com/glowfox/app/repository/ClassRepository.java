package com.glowfox.app.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.glowfox.app.dao.model.ClassEntity;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, Long> {
	 // Find a class by name and date range to check for duplicates
    Optional<ClassEntity> findByClassNameAndStartDateAndEndDate(String name, LocalDate startDate, LocalDate endDate);
 // Find a class by name only, used when booking a class by name
    Optional<ClassEntity> findByClassName(String name);
}