package com.exam.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Forum entity. @author MyEclipse Persistence Tools
 */

public class Forum implements java.io.Serializable {

	// Fields

	private Integer fid;
	private Student student;
	private Admin admin;
	private String ftitle;
	private String fcontent;
	private String image;
	private Date fdate;
	private Set replies = new HashSet(0);

	// Constructors

	/** default constructor */
	public Forum() {
	}

	/** full constructor */
	public Forum(Student student, Admin admin, String ftitle, String fcontent,
			String image, Date fdate, Set replies) {
		this.student = student;
		this.admin = admin;
		this.ftitle = ftitle;
		this.fcontent = fcontent;
		this.image = image;
		this.fdate = fdate;
		this.replies = replies;
	}

	// Property accessors

	public Integer getFid() {
		return this.fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
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

	public String getFtitle() {
		return this.ftitle;
	}

	public void setFtitle(String ftitle) {
		this.ftitle = ftitle;
	}

	public String getFcontent() {
		return this.fcontent;
	}

	public void setFcontent(String fcontent) {
		this.fcontent = fcontent;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getFdate() {
		return this.fdate;
	}

	public void setFdate(Date fdate) {
		this.fdate = fdate;
	}

	public Set getReplies() {
		return this.replies;
	}

	public void setReplies(Set replies) {
		this.replies = replies;
	}

}