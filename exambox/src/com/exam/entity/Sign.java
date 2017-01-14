package com.exam.entity;

import java.util.Date;

/**
 * Sign entity. @author MyEclipse Persistence Tools
 */

public class Sign implements java.io.Serializable {

	// Fields

	private Integer id;
	private Student student;
	private Date time;

	// Constructors

	/** default constructor */
	public Sign() {
	}

	/** full constructor */
	public Sign(Student student, Date time) {
		this.student = student;
		this.time = time;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}