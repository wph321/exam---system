package com.exam.entity;

import java.util.Date;

/**
 * Note entity. @author MyEclipse Persistence Tools
 */

public class Note implements java.io.Serializable {

	// Fields

	private Integer id;
	private Student student;
	private Question question;
	private Quetype quetype;
	private Date time;
	private String ncontent;

	// Constructors

	/** default constructor */
	public Note() {
	}

	/** full constructor */
	public Note(Student student, Question question, Quetype quetype, Date time,
			String ncontent) {
		this.student = student;
		this.question = question;
		this.quetype = quetype;
		this.time = time;
		this.ncontent = ncontent;
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

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Quetype getQuetype() {
		return this.quetype;
	}

	public void setQuetype(Quetype quetype) {
		this.quetype = quetype;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getNcontent() {
		return this.ncontent;
	}

	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}

}