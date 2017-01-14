package com.exam.entity;

import java.util.Date;

/**
 * Examdate entity. @author MyEclipse Persistence Tools
 */

public class Examdate implements java.io.Serializable {

	// Fields

	private Integer id;
	private Subject subject;
	private Date edate;

	// Constructors

	/** default constructor */
	public Examdate() {
	}

	/** full constructor */
	public Examdate(Subject subject, Date edate) {
		this.subject = subject;
		this.edate = edate;
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

	public Date getEdate() {
		return this.edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

}