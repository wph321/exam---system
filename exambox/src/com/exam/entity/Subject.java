package com.exam.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Subject entity. @author MyEclipse Persistence Tools
 */

public class Subject implements java.io.Serializable {

	// Fields

	private Integer id;
	private String sname;
	private String sdetail;
	private Set vedios = new HashSet(0);
	private Set questions = new HashSet(0);
	private Set grades = new HashSet(0);
	private Set points = new HashSet(0);
	private Set examdates = new HashSet(0);

	// Constructors

	/** default constructor */
	public Subject() {
	}

	/** full constructor */
	public Subject(String sname, String sdetail, Set vedios, Set questions,
			Set grades, Set points, Set examdates) {
		this.sname = sname;
		this.sdetail = sdetail;
		this.vedios = vedios;
		this.questions = questions;
		this.grades = grades;
		this.points = points;
		this.examdates = examdates;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSname() {
		return this.sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSdetail() {
		return this.sdetail;
	}

	public void setSdetail(String sdetail) {
		this.sdetail = sdetail;
	}

	public Set getVedios() {
		return this.vedios;
	}

	public void setVedios(Set vedios) {
		this.vedios = vedios;
	}

	public Set getQuestions() {
		return this.questions;
	}

	public void setQuestions(Set questions) {
		this.questions = questions;
	}

	public Set getGrades() {
		return this.grades;
	}

	public void setGrades(Set grades) {
		this.grades = grades;
	}

	public Set getPoints() {
		return this.points;
	}

	public void setPoints(Set points) {
		this.points = points;
	}

	public Set getExamdates() {
		return this.examdates;
	}

	public void setExamdates(Set examdates) {
		this.examdates = examdates;
	}

}