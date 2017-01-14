package com.exam.action;

import java.util.List;
import java.util.Map;

import com.exam.biz.GradeBiz;
import com.exam.entity.Error;
import com.exam.entity.Grade;
import com.exam.entity.Student;
import com.exam.entity.Subject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GradeAction extends ActionSupport {
	private GradeBiz gradeBiz;
	private Integer nowpage;
	private Integer pages;
	private Integer nextpage;
	private Integer backpage;
	private Subject subject;
	public String find(){
		try{
			System.out.println("进入到了查grade方法");
			Map session = ActionContext.getContext().getSession();
			Student stu = (Student) session.get("user");
			
			Grade temp_grade = new Grade();
			Subject temp_subject = new Subject();
			temp_subject.setId(-1);
			temp_grade.setSubject(temp_subject);
			nowpage =1;
			pages = gradeBiz.findPageByStu(stu,temp_grade);
			List<Grade> gradeList = gradeBiz.findGradeByPageAndStu(nowpage, stu,temp_grade);
			
			nextpage = nowpage+1;
			if(nextpage>pages){
				nextpage= pages;
			}
			backpage = nowpage-1;
			if(backpage==0){
				backpage=1;
			}
			System.out.println("执行到了这一步");
			if(session.get("gradeList")!=null){
				session.remove("gradeList");
				session.put("gradeList",gradeList);
			}else{
				session.put("gradeList",gradeList);
			}
			return "findgrade_success";
	
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
	
				Grade temp_grade = new Grade();
				Subject temp_subject = new Subject();
				
				Map session = ActionContext.getContext().getSession();
				Student  stu = (Student) session.get("user");
				
				temp_subject.setId(subject.getId());

				if(session.get("gradesubid")!=null){
					session.remove("gradesubid");
					session.put("gradesubid",subject.getId());
				}else{
					session.put("gradesubid",subject.getId());
				}
				List<Grade> gradeList ;
				if(temp_subject.getId()!=-1){
					temp_grade.setSubject(temp_subject);
				
					 gradeList  = gradeBiz.findGradeByPageAndStu(nowpage, stu,temp_grade);
					 pages = gradeBiz.findPageByStu(stu,temp_grade);
				}
				else{
					temp_grade.setSubject(temp_subject);
					 gradeList = gradeBiz.findGradeByPageAndStu(nowpage,stu,temp_grade);
					 pages = gradeBiz.findPageByStu(stu,temp_grade);
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
			
				if(session.get("gradeList")!=null){
					session.remove("gradeList");
					session.put("gradeList",gradeList);
				}else{
					session.put("gradeList",gradeList);
				}
			
		}catch(Exception ex){
			ex.printStackTrace();
			return "exception";
		}
		return  "findByPage_success";
	}
	
	public GradeBiz getGradeBiz() {
		return gradeBiz;
	}
	public void setGradeBiz(GradeBiz gradeBiz) {
		this.gradeBiz = gradeBiz;
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

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	
}