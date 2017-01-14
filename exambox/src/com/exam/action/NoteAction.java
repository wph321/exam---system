package com.exam.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.exam.biz.NoteBiz;
import com.exam.entity.Note;
import com.exam.entity.Student;
import com.exam.entity.Subject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Note entity. @author MyEclipse Persistence Tools
 */

public class NoteAction extends ActionSupport {
	private NoteBiz noteBiz;
	private Note note;
	private Integer nid;
	private Subject subject;
	private Student student;
	private String type;
	
	private Integer nowpage;//记录请求的页数
   	private Integer backpage;//上一页
   	private Integer nextpage;//下一页 
   	private Integer pages;//总页数
	
	public String add()throws Exception{
		try{
			System.out.println("进入添加 的方法");
			note.setTime(new Date());
			int num=noteBiz.addNote(note);
			if(num>0){
			  return "add_success";
			}
			  return "add_error";
		}catch(Exception e){
			e.printStackTrace();
		}
		     return "exception";
	}
	public String findByPageAndStudent()throws Exception{
		try{
			if(nowpage==null || nowpage==0){
				nowpage=1;
			}
		    pages=noteBiz.findPageByStu(student);
		    backpage=nowpage-1;
		    if(backpage==0){
		    	backpage=1;
		    }
		    nextpage=nowpage+1;
		    if(nextpage>pages){
		    	nextpage=pages;
		    }
			Map<String,Object> session=ActionContext.getContext().getSession();
			List<Note> noteList=noteBiz.findNoteByPageAndStu(nowpage, student);
			session.put("noteList", noteList);
			
			return "findByPageAndStudent_success";
			
		}catch(Exception e){
			e.printStackTrace();
		}
		    return "exception";
	}
	public String findByPageAndSubjectAndStudent()throws Exception{
		try{
			if(subject.getId()==-1){
				findByPageAndStudent();
			}
			if(nowpage==null || nowpage==0){
				nowpage=1;
			}
		    pages=noteBiz.findPageByStuAndSub(student, subject);
		    backpage=nowpage-1;
		    if(backpage==0){
		    	backpage=1;
		    }
		    nextpage=nowpage+1;
		    if(nextpage>pages){
		    	nextpage=pages;
		    }
			Map<String,Object> session=ActionContext.getContext().getSession();
			List<Note> noteList=noteBiz.findNoteByPageAndStuAndSub(nowpage, student, subject);
			session.put("noteList", noteList);
			
			return "findByPageAndSubjectAndStudent_success";
			
		}catch(Exception e){
			e.printStackTrace();
		}
		    return "exception";
	}
	
	public String delete()throws Exception{
		try{
			Map<String,Object> session=ActionContext.getContext().getSession();
			student=(Student)session.get("user");
			int num=noteBiz.deleteNoteById(note);
			if(num>0){
			if("delete1".equals(type)){
				return "delete1_success";
			}else{
				return "delete_success";
			}
		
			}
			    return "delete_error";
			    
		}catch(Exception e){
			e.printStackTrace();
		}
		return "exception";
	}
	
	public NoteBiz getNoteBiz() {
		return noteBiz;
	}
	public void setNoteBiz(NoteBiz noteBiz) {
		this.noteBiz = noteBiz;
	}
	public Note getNote() {
		return note;
	}
	public void setNote(Note note) {
		this.note = note;
	}
	public Integer getNid() {
		return nid;
	}
	public void setNid(Integer nid) {
		this.nid = nid;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Integer getNowpage() {
		return nowpage;
	}
	public void setNowpage(Integer nowpage) {
		this.nowpage = nowpage;
	}
	public Integer getBackpage() {
		return backpage;
	}
	public void setBackpage(Integer backpage) {
		this.backpage = backpage;
	}
	public Integer getNextpage() {
		return nextpage;
	}
	public void setNextpage(Integer nextpage) {
		this.nextpage = nextpage;
	}
	public Integer getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
}