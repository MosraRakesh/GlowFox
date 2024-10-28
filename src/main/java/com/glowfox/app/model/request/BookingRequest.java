package com.glowfox.app.model.request;

import java.time.LocalDate;

public class BookingRequest {
    private String memberName;
    private LocalDate bookingDate;
    private Long classId;  // Add classId to identify which class is being booked
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public LocalDate getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}
	public Long getClassId() {
		return classId;
	}
	public void setClassId(Long classId) {
		this.classId = classId;
	}

   
}
