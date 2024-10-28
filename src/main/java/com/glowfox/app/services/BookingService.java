package com.glowfox.app.services;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glowfox.app.dao.model.BookingEntity;
import com.glowfox.app.dao.model.ClassEntity;
import com.glowfox.app.exception.BookingDateConflictException;
import com.glowfox.app.exception.CapacityExceededException;
import com.glowfox.app.exception.ClassesNotFoundException;
import com.glowfox.app.model.dto.BookingDTO;
import com.glowfox.app.repository.BookingRepository;
import com.glowfox.app.repository.ClassRepository;

@Service
public class BookingService {

	private final BookingRepository bookingRepository;
	private final ClassRepository classRepository;
	private final ModelMapper modelMapper;

	public BookingService(BookingRepository bookingRepository, ClassRepository classRepository,
			ModelMapper modelMapper) {
		this.bookingRepository = bookingRepository;
		this.classRepository = classRepository;
		this.modelMapper = modelMapper;
	}

	public BookingDTO bookClass(BookingDTO bookingDTO) {
		Optional<ClassEntity> classOptional = classRepository.findById(bookingDTO.getClassId());
		if (!classOptional.isPresent()) {
			throw new ClassesNotFoundException("The requested class does not exist.");
		}
		ClassEntity classToBook = classOptional.get();

		LocalDate bookingDate = bookingDTO.getBookingDate();
		if (bookingDate.isBefore(classToBook.getStartDate()) || bookingDate.isAfter(classToBook.getEndDate())) {
			throw new BookingDateConflictException("Booking date must be within the class schedule.");
		}

//        int currentBookings = bookingRepository.countByClassNameAndBookingDate(bookingDTO.getClassName(), bookingDTO.getBookingDate());
//        if (currentBookings >= classToBook.getCapacity()) {
//            throw new CapacityExceededException("The class is fully booked. Cannot exceed maximum capacity.");
//        }
		BookingEntity bookingEntity = new BookingEntity();
		bookingEntity.setMemberName(bookingDTO.getMemberName());
		bookingEntity.setBookingDate(bookingDTO.getBookingDate());
		bookingEntity.setClassName(classToBook.getClassName());
		bookingEntity.setClassEntity(classToBook); // Set the class entity

		// Save the booking to the repository
		BookingEntity savedEntity = bookingRepository.save(bookingEntity);

		// Map saved entity back to DTO
		BookingDTO savedBookingDTO = new BookingDTO();
		savedBookingDTO.setMemberName(savedEntity.getMemberName());
		savedBookingDTO.setBookingDate(savedEntity.getBookingDate());
		savedBookingDTO.setClassName(savedEntity.getClassEntity().getClassName());
		savedBookingDTO.setClassId(savedEntity.getClassEntity().getId());

		return savedBookingDTO;
	}

	public List<BookingDTO> getAllBookings() {
		List<BookingEntity> bookingEntities = bookingRepository.findAll();
		List<BookingDTO> bookingDTOs = new ArrayList<>();

		// Manual mapping from BookingEntity to BookingDTO
		for (BookingEntity bookingEntity : bookingEntities) {
			BookingDTO bookingDTO = new BookingDTO();
			bookingDTO.setMemberName(bookingEntity.getMemberName());
			bookingDTO.setBookingDate(bookingEntity.getBookingDate());
			bookingDTO.setClassId(bookingEntity.getClassEntity().getId());
			bookingDTO.setClassName(bookingEntity.getClassEntity().getClassName());
			bookingDTOs.add(bookingDTO);
		}

		return bookingDTOs;
//        return bookingRepository.findAll().stream()
//                .map(booking -> modelMapper.map(booking, BookingDTO.class))
//                .collect(Collectors.toList());
	}
}
