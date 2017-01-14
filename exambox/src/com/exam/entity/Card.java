package com.exam.entity;

/**
 * Card entity. @author MyEclipse Persistence Tools
 */

public class Card implements java.io.Serializable {

	// Fields

	private Integer id;
	private Question question;
	private Grade grade;
	private Quetype quetype;
	private String uanswer;
	private String result;
	private Integer score;
	private Integer qno;

	// Constructors

	/** default constructor */
	public Card() {
	}

	/** full constructor */
	public Card(Question question, Grade grade, Quetype quetype,
			String uanswer, String result, Integer score, Integer qno) {
		this.question = question;
		this.grade = grade;
		this.quetype = quetype;
		this.uanswer = uanswer;
		this.result = result;
		this.score = score;
		this.qno = qno;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getUanswer() {
		return this.uanswer;
	}

	public void setUanswer(String uanswer) {
		this.uanswer = uanswer;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getQno() {
		return this.qno;
	}

	public void setQno(Integer qno) {
		this.qno = qno;
	}

}