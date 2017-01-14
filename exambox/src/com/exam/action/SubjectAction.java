package com.exam.action;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.exam.biz.ErrorBiz;
import com.exam.biz.SubjectBiz;
import com.exam.entity.Error;
import com.exam.entity.Subject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Subject entity. @author MyEclipse Persistence Tools
 */

public class SubjectAction extends ActionSupport {
	private SubjectBiz subjectBiz;
	private Subject subject;
	private ErrorBiz errorbiz;
	private Integer sid;
	private String type;
	private Integer nowpage;//记录请求的页数
  	private Integer backpage;//上一页
  	private Integer nextpage;//下一页 
  	private Integer pages;//总页数
	
	public String add()throws Exception{
		try{
			
			int num=subjectBiz.addSubject(subject);
			if(num>0){
				return "add_success";
			  }
			    return "add_error";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "exception";
	}
public String  delete()throws Exception{
	try{
		System.out.println("进入删除的方法");
		int num=subjectBiz.deleteSubject(sid);
		System.out.println("输出num"+num);
		if(num>0){
			return "delete_success";
		  }
		    return "delete_error";
	}catch(Exception e){
		e.printStackTrace();
	}
	return "exception";
}
	public String update()throws Exception{
		try{
			System.out.println("进入修改的方法");
			int num=subjectBiz.updateSubject(subject);
			if(num>0){
				return "update_success";
			  }
			    return "update_error";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "exception";
	}
	public String find()throws Exception{
		try{
		   Map<String,Object> session=ActionContext.getContext().getSession();
			List<Subject> subjectList=subjectBiz.findSubject();
			if(subjectList!=null){
				session.remove(subjectList);
				session.put("subjectList", subjectList);
			}
			    session.put("subjectList", subjectList);
			     
			     return "find_success";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "exception";
	}
	public  String findone()throws Exception{
		try{
			Map<String,Object> session=ActionContext.getContext().getSession();
			Subject subject=subjectBiz.findone(sid);
			//List<Error> elist =errorbiz.findErrorBySub(sid);
			//session.put("elist", elist);
			session.put("subject", subject);
			if("admin".equals(type))
				return "adminfindone_success";
			    return "findone_success";
		}catch(Exception e){
			e.printStackTrace();
		}
		    return "exception";
	}
	public String  findByPage()throws Exception{
		   try{
			   if(nowpage==null || nowpage==0){
					nowpage=1;
				}
			    pages=subjectBiz.findPages();
			    backpage=nowpage-1;
			    if(backpage==0){
			    	backpage=1;
			    }
			    nextpage=nowpage+1;
			    if(nextpage>pages){
			    	nextpage=pages;
			    }
			    Map<String,Object> session=ActionContext.getContext().getSession();
				List<Subject> subjectList=subjectBiz.findByPage(nowpage);
				if(subjectList!=null){
					session.remove(subjectList);
					session.put("subjectList", subjectList);
				}
				    session.put("subjectList", subjectList);
				    
				    return "findByPage_success";
		   }catch(Exception e){
			   e.printStackTrace();
		   }
		      return "exception";
	   }
	
	public SubjectBiz getSubjectBiz() {
		return subjectBiz;
	}
	public void setSubjectBiz(SubjectBiz subjectBiz) {
		this.subjectBiz = subjectBiz;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
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
	public ErrorBiz getErrorbiz() {
		return errorbiz;
	}
	public void setErrorbiz(ErrorBiz errorbiz) {
		this.errorbiz = errorbiz;
	}
	
	
}