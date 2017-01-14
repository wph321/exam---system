package com.exam.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Point entity. @author MyEclipse Persistence Tools
 */

public class Point implements java.io.Serializable {

	// Fields

	private Integer id;
	private Subject subject;
	private String pname;
	private String pex;
	private Set questions = new HashSet(0);

	// Constructors

	/** default constructor */
	public Point() {
	}

	/** full constructor */
	public Point(Subject subject, String pname, String pex, Set questions) {
		this.subject = subject;
		this.pname = pname;
		this.pex = pex;
		this.questions = questions;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getPname() {
		return this.pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPex() {
		return this.pex;
	}

	public void setPex(String pex) {
		this.pex = pex;
	}

	public Set getQuestions() {
		return this.questions;
	}

	public void setQuestions(Set questions) {
		this.questions = questions;
	}

}