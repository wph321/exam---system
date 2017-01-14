package com.exam.entity;

/**
 * Error entity. @author MyEclipse Persistence Tools
 */

public class Error implements java.io.Serializable {

	// Fields

	private Integer id;
	private Student student;
	private Question question;
	private Grade grade;
	private Quetype quetype;

	// Constructors

	/** default constructor */
	public Error() {
	}

	/** full constructor */
	public Error(Student student, Question question, Grade grade,
			Quetype quetype) {
		this.student = student;
		this.question = question;
		this.grade = grade;
		this.quetype = quetype;
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

	public Grade getGrade() {
		return this.grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Quetype getQuetype() {
		return this.quetype;
	}

	public void setQuetype(Quetype quetype) {
		this.quetype = quetype;
	}

}