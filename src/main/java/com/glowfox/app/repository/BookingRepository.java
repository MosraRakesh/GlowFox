package com.glowfox.app.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.glowfox.app.dao.model.BookingEntity;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
	  // Count the number of bookings for a specific class on a given date
    int countByClassNameAndBookingDate(String className, LocalDate date);
}
