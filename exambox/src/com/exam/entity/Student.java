package com.exam.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Student entity. @author MyEclipse Persistence Tools
 */

public class Student implements java.io.Serializable {

	// Fields

	private Integer id;
	private String uname;
	private String type;
	private String loginname;
	private String loginpass;
	private String email;
	private String phone;
	private String image;
	private String status;
	private Integer score;
	private Set grades = new HashSet(0);
	private Set vedios = new HashSet(0);
	private Set vcomments = new HashSet(0);
	private Set notes = new HashSet(0);
	private Set errors = new HashSet(0);
	private Set dones = new HashSet(0);
	private Set signs = new HashSet(0);
	private Set replies = new HashSet(0);
	private Set forums = new HashSet(0);
	private Set collects = new HashSet(0);

	// Constructors

	/** default constructor */
	public Student() {
	}

	/** full constructor */
	public Student(String uname, String type, String loginname,
			String loginpass, String email, String phone, String image,
			String status, Integer score, Set grades, Set vedios,
			Set vcomments, Set notes, Set errors, Set dones, Set signs,
			Set replies, Set forums, Set collects) {
		this.uname = uname;
		this.type = type;
		this.loginname = loginname;
		this.loginpass = loginpass;
		this.email = email;
		this.phone = phone;
		this.image = image;
		this.status = status;
		this.score = score;
		this.grades = grades;
		this.vedios = vedios;
		this.vcomments = vcomments;
		this.notes = notes;
		this.errors = errors;
		this.dones = dones;
		this.signs = signs;
		this.replies = replies;
		this.forums = forums;
		this.collects = collects;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUname() {
		return this.uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLoginname() {
		return this.loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getLoginpass() {
		return this.loginpass;
	}

	public void setLoginpass(String loginpass) {
		this.loginpass = loginpass;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Set getGrades() {
		return this.grades;
	}

	public void setGrades(Set grades) {
		this.grades = grades;
	}

	public Set getVedios() {
		return this.vedios;
	}

	public void setVedios(Set vedios) {
		this.vedios = vedios;
	}

	public Set getVcomments() {
		return this.vcomments;
	}

	public void setVcomments(Set vcomments) {
		this.vcomments = vcomments;
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

	public Set getDones() {
		return this.dones;
	}

	public void setDones(Set dones) {
		this.dones = dones;
	}

	public Set getSigns() {
		return this.signs;
	}

	public void setSigns(Set signs) {
		this.signs = signs;
	}

	public Set getReplies() {
		return this.replies;
	}

	public void setReplies(Set replies) {
		this.replies = replies;
	}

	public Set getForums() {
		return this.forums;
	}

	public void setForums(Set forums) {
		this.forums = forums;
	}

	public Set getCollects() {
		return this.collects;
	}

	public void setCollects(Set collects) {
		this.collects = collects;
	}

}