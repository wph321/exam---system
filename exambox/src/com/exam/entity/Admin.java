package com.exam.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Admin entity. @author MyEclipse Persistence Tools
 */

public class Admin implements java.io.Serializable {

	// Fields

	private Integer id;
	private String aname;
	private String type;
	private String loginname;
	private String loginpass;
	private String email;
	private String phone;
	private Set forums = new HashSet(0);
	private Set news = new HashSet(0);
	private Set replies = new HashSet(0);
	private Set vedios = new HashSet(0);

	// Constructors

	/** default constructor */
	public Admin() {
	}

	/** full constructor */
	public Admin(String aname, String type, String loginname, String loginpass,
			String email, String phone, Set forums, Set news, Set replies,
			Set vedios) {
		this.aname = aname;
		this.type = type;
		this.loginname = loginname;
		this.loginpass = loginpass;
		this.email = email;
		this.phone = phone;
		this.forums = forums;
		this.news = news;
		this.replies = replies;
		this.vedios = vedios;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAname() {
		return this.aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
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

	public Set getForums() {
		return this.forums;
	}

	public void setForums(Set forums) {
		this.forums = forums;
	}

	public Set getNews() {
		return this.news;
	}

	public void setNews(Set news) {
		this.news = news;
	}

	public Set getReplies() {
		return this.replies;
	}

	public void setReplies(Set replies) {
		this.replies = replies;
	}

	public Set getVedios() {
		return this.vedios;
	}

	public void setVedios(Set vedios) {
		this.vedios = vedios;
	}

}