package com.glowfox.app.model.dto;

import java.io.Serializable;
import java.time.LocalDate;




public class ClassDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5350686885032407938L;
	public ClassDTO(String string, LocalDate plusDays, LocalDate plusDays2, int i) {
		 this.className = className;
	        this.startDate = startDate;
	        this.endDate = endDate;
	        this.capacity = capacity;
	}
	public ClassDTO() {
		// TODO Auto-generated constructor stub
	}
	private String className;
	private LocalDate startDate;
	private LocalDate endDate;
	private int capacity;
	private Long classId;
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public Long getClassId() {
		return classId;
	}
	public void setClassId(Long classId) {
		this.classId = classId;
	}
	
}
