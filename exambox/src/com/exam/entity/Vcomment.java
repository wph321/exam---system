package com.exam.entity;

import java.util.Date;

/**
 * Vcomment entity. @author MyEclipse Persistence Tools
 */

public class Vcomment implements java.io.Serializable {

	// Fields

	private Integer id;
	private Student student;
	private Vedio vedio;
	private String content;
	private Date time;

	// Constructors

	/** default constructor */
	public Vcomment() {
	}

	/** full constructor */
	public Vcomment(Student student, Vedio vedio, String content, Date time) {
		this.student = student;
		this.vedio = vedio;
		this.content = content;
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

	public Vedio getVedio() {
		return this.vedio;
	}

	public void setVedio(Vedio vedio) {
		this.vedio = vedio;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}