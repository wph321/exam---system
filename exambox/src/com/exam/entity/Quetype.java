package com.exam.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Quetype entity. @author MyEclipse Persistence Tools
 */

public class Quetype implements java.io.Serializable {

	// Fields

	private Integer id;
	private String tname;
	private Set cards = new HashSet(0);
	private Set collects = new HashSet(0);
	private Set notes = new HashSet(0);
	private Set errors = new HashSet(0);
	private Set questions = new HashSet(0);

	// Constructors

	/** default constructor */
	public Quetype() {
	}

	/** full constructor */
	public Quetype(String tname, Set cards, Set collects, Set notes,
			Set errors, Set questions) {
		this.tname = tname;
		this.cards = cards;
		this.collects = collects;
		this.notes = notes;
		this.errors = errors;
		this.questions = questions;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTname() {
		return this.tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public Set getCards() {
		return this.cards;
	}

	public void setCards(Set cards) {
		this.cards = cards;
	}

	public Set getCollects() {
		return this.collects;
	}

	public void setCollects(Set collects) {
		this.collects = collects;
	}

	public Set getNotes() {
		return this.notes;
	}

	public void setNotes(Set notes) {
		this.notes = notes;
	}

	public Set getErrors() {
		return this.errors;
	}

	public void setErrors(Set errors) {
		this.errors = errors;
	}

	public Set getQuestions() {
		return this.questions;
	}

	public void setQuestions(Set questions) {
		this.questions = questions;
	}

}