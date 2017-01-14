package com.exam.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Vedio entity. @author MyEclipse Persistence Tools
 */

public class Vedio implements java.io.Serializable {

	// Fields

	private Integer id;
	private Student student;
	private Admin admin;
	private Subject subject;
	private String vname;
	private Date time;
	private String vfile;
	private String vex;
	private String image;
	private String status;
	private Set vcomments = new HashSet(0);

	// Constructors

	/** default constructor */
	public Vedio() {
	}

	/** full constructor */
	public Vedio(Student student, Admin admin, Subject subject, String vname,
			Date time, String vfile, String vex, String image, String status,
			Set vcomments) {
		this.student = student;
		this.admin = admin;
		this.subject = subject;
		this.vname = vname;
		this.time = time;
		this.vfile = vfile;
		this.vex = vex;
		this.image = image;
		this.status = status;
		this.vcomments = vcomments;
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

	public Admin getAdmin() {
		return this.admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getVname() {
		return this.vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getVfile() {
		return this.vfile;
	}

	public void setVfile(String vfile) {
		this.vfile = vfile;
	}

	public String getVex() {
		return this.vex;
	}

	public void setVex(String vex) {
		this.vex = vex;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set getVcomments() {
		return this.vcomments;
	}

	public void setVcomments(Set vcomments) {
		this.vcomments = vcomments;
	}

}