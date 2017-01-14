package com.exam.action;

import java.util.List;
import java.util.Map;

import com.exam.biz.ErrorBiz;
import com.exam.biz.StudentBiz;
import com.exam.entity.Error;
import com.exam.entity.Question;
import com.exam.entity.Student;
import com.exam.entity.Subject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ErrorAction extends ActionSupport {
	private ErrorBiz errorBiz;
	private StudentBiz studentBiz;
	private Student student;
	private Subject subject;
	private Integer nowpage;
	private Integer pages;
	private Integer nextpage;
	private Integer backpage;
	
	public String find(){
		try{
		System.out.println("进入到了查询所有错题的方法中");
		Student stu  = studentBiz.findStudentBySid(student.getId());
		Map session = ActionContext.getContext().getSession();
		
		Error temp_error  = new Error();
		Question question = new Question();
		Subject temp_subject = new Subject();
		
		temp_error.setQuestion(question);
		
		
		nowpage =1;
		pages = errorBiz.findPageByStu(stu,temp_error);
		List<Error> errorList = errorBiz.findErrorByPageAndStu(nowpage, stu,temp_error);
		
		nextpage = nowpage+1;
		if(nextpage>pages){
			nextpage= pages;
		}
		backpage = nowpage-1;
		if(backpage==0){
			backpage=1;
		}
		if(session.get("errorList")!=null){
			session.remove("errorList");
			session.put("errorList",errorList);
		}else{
			session.put("errorList",errorList);
		}
		return "finderror_success";
		}catch(Exception ex){
			ex.printStackTrace();
			return "exception";
		}
	}
	
	
	public String findByPage(){
		try{
			System.out.println("进入到了按页查找的方法");
		
			if(nowpage==null||nowpage==0){
				nowpage=1;
			}
				Error temp_error  = new Error();
				Question question = new Question();
				Subject temp_subject = new Subject();
				
				temp_error.setQuestion(question);
				Map session = ActionContext.getContext().getSession();
				Student  stu = (Student) session.get("user");
				temp_subject.setId(subject.getId());
		
			
				session.put("subid",subject.getId());
				List<Error> errorList ;
				if(temp_subject.getId()!=-1){
					temp_error.getQuestion().setSubject(temp_subject);
				
					 errorList  = errorBiz.findErrorByPageAndStu(nowpage, stu,temp_error);
					 pages = errorBiz.findPageByStu(stu,temp_error);
				}
				else{
					Error temp_error1  = new Error();
					Question question1 = new Question();
					Subject temp_subject1 = new Subject();
					temp_error1.setQuestion(question1);
					 errorList = errorBiz.findErrorByPageAndStu(nowpage,stu,temp_error1);
					 pages = errorBiz.findPageByStu(stu,temp_error1);
				}
			
			//上一页下一页的问题
				nextpage = nowpage+1;
				if(nextpage>pages){
					nextpage= pages;
				}
				backpage = nowpage-1;
				if(backpage==0){
					backpage=1;
				}
			
				if(session.get("errorList")!=null){
					session.remove("errorList");
					session.put("errorList",errorList);
				}else{
					session.put("errorList",errorList);
				}
			
		}catch(Exception ex){
			ex.printStackTrace();
			return "exception";
		}
		return  "findByPage_success";
	}
	public ErrorBiz getErrorBiz() {
		return errorBiz;
	}
	public void setErrorBiz(ErrorBiz errorBiz) {
		this.errorBiz = errorBiz;
	}
	public StudentBiz getStudentBiz() {
		return studentBiz;
	}
	public void setStudentBiz(StudentBiz studentBiz) {
		this.studentBiz = studentBiz;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public Integer getNowpage() {
		return nowpage;
	}
	public void setNowpage(Integer nowpage) {
		this.nowpage = nowpage;
	}
	public Integer getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	public Integer getNextpage() {
		return nextpage;
	}
	public void setNextpage(Integer nextpage) {
		this.nextpage = nextpage;
	}
	public Integer getBackpage() {
		return backpage;
	}
	public void setBackpage(Integer backpage) {
		this.backpage = backpage;
	}

}