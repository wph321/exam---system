package com.exam.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Grade entity. @author MyEclipse Persistence Tools
 */

public class Grade implements java.io.Serializable {

	// Fields

	private Integer id;
	private Student student;
	private Subject subject;
	private Integer score;
	private Date time;
	private String type;
	private Set errors = new HashSet(0);
	private Set cards = new HashSet(0);

	// Constructors

	/** default constructor */
	public Grade() {
	}

	/** full constructor */
	public Grade(Student student, Subject subject, Integer score, Date time,
			String type, Set errors, Set cards) {
		this.student = student;
		this.subject = subject;
		this.score = score;
		this.time = time;
		this.type = type;
		this.errors = errors;
		this.cards = cards;
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

	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set getErrors() {
		return this.errors;
	}

	public void setErrors(Set errors) {
		this.errors = errors;
	}

	public Set getCards() {
		return this.cards;
	}

	public void setCards(Set cards) {
		this.cards = cards;
	}

}