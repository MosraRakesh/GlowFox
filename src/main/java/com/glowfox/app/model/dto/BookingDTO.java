package com.glowfox.app.model.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;

@Data
public class BookingDTO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6715571510898332624L;
	private String memberName;
    private LocalDate bookingDate;
    private Long classId;
    private String className;
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
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
    
}
