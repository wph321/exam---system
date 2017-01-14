package com.exam.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Question entity. @author MyEclipse Persistence Tools
 */

public class Question implements java.io.Serializable {

	// Fields

	private Integer id;
	private Subject subject;
	private Quetype quetype;
	private String qtitle;
	private String optiona;
	private String optionb;
	private String optionc;
	private String optiond;
	private String result;
	private String knowpint;
	private Integer difficulty;
	private String image;
	private String analysis;
	private Set notes = new HashSet(0);
	private Set points = new HashSet(0);
	private Set cards = new HashSet(0);
	private Set errors = new HashSet(0);
	private Set collects = new HashSet(0);

	// Constructors

	/** default constructor */
	public Question() {
	}

	/** full constructor */
	public Question(Subject subject, Quetype quetype, String qtitle,
			String optiona, String optionb, String optionc, String optiond,
			String result, String knowpint, Integer difficulty, String image,
			String analysis, Set notes, Set points, Set cards, Set errors,
			Set collects) {
		this.subject = subject;
		this.quetype = quetype;
		this.qtitle = qtitle;
		this.optiona = optiona;
		this.optionb = optionb;
		this.optionc = optionc;
		this.optiond = optiond;
		this.result = result;
		this.knowpint = knowpint;
		this.difficulty = difficulty;
		this.image = image;
		this.analysis = analysis;
		this.notes = notes;
		this.points = points;
		this.cards = cards;
		this.errors = errors;
		this.collects = collects;
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

	public Quetype getQuetype() {
		return this.quetype;
	}

	public void setQuetype(Quetype quetype) {
		this.quetype = quetype;
	}

	public String getQtitle() {
		return this.qtitle;
	}

	public void setQtitle(String qtitle) {
		this.qtitle = qtitle;
	}

	public String getOptiona() {
		return this.optiona;
	}

	public void setOptiona(String optiona) {
		this.optiona = optiona;
	}

	public String getOptionb() {
		return this.optionb;
	}

	public void setOptionb(String optionb) {
		this.optionb = optionb;
	}

	public String getOptionc() {
		return this.optionc;
	}

	public void setOptionc(String optionc) {
		this.optionc = optionc;
	}

	public String getOptiond() {
		return this.optiond;
	}

	public void setOptiond(String optiond) {
		this.optiond = optiond;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getKnowpint() {
		return this.knowpint;
	}

	public void setKnowpint(String knowpint) {
		this.knowpint = knowpint;
	}

	public Integer getDifficulty() {
		return this.difficulty;
	}

	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAnalysis() {
		return this.analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	public Set getNotes() {
		return this.notes;
	}

	public void setNotes(Set notes) {
		this.notes = notes;
	}

	public Set getPoints() {
		return points;
	}

	public void setPoints(Set points) {
		this.points = points;
	}

	public Set getCards() {
		return this.cards;
	}

	public void setCards(Set cards) {
		this.cards = cards;
	}

	public Set getErrors() {
		return this.errors;
	}

	public void setErrors(Set errors) {
		this.errors = errors;
	}

	public Set getCollects() {
		return this.collects;
	}

	public void setCollects(Set collects) {
		this.collects = collects;
	}

}