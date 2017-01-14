package com.exam.action;

import java.util.List;
import java.util.Map;

import com.exam.biz.ExamdateBiz;
import com.exam.entity.Examdate;
import com.exam.entity.Subject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ExamdateAction extends ActionSupport {
  private ExamdateBiz examdateBiz;
  private Examdate examdate;
  private Subject subject;
  private Integer did;
  
  private Integer nowpage;//记录请求的页数
  private Integer backpage;//上一页
  private Integer nextpage;//下一页 
  private Integer pages;//总页数
  
  public String add()throws Exception{
	  try{
		  int num=examdateBiz.addDate(examdate);
		  if(num>0){
			  return "add_success";
		  }
		      return "add_error";
	  }catch(Exception e){
		  e.printStackTrace();
	  }
	  return "exception";
  }
  public String delete()throws Exception{
	  try{
		  int num=examdateBiz.deleteDate(did);
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
		  int num=examdateBiz.updateDate(examdate);
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
		  List<Examdate> examdateList=examdateBiz.findDate();
		  if(examdateList.size()>0){
		    session.remove(examdateList);
		    session.put("examdateList", examdateList);
		  }  
		  session.put("examdateList", examdateList);
		   return "find_success";
	  }catch(Exception e){
		  e.printStackTrace();
	  }
	      return "exception";
  }
  public String findone()throws Exception{
	  try{
		  Map<String,Object> session=ActionContext.getContext().getSession();
		  Examdate examdate=examdateBiz.findoneDate(did);
		  session.put("examdate", examdate);
		  
		  return "findone_success";
	  }catch(Exception e){
		  e.printStackTrace();
	  }
	     return "exception";
  }
  public String findByPage()throws Exception{
	  try{
		  if(nowpage==null || nowpage==0){
				nowpage=1;
			}
		   pages=examdateBiz.findPages();
		    backpage=nowpage-1;
		    if(backpage==0){
		    	backpage=1;
		    }
		    nextpage=nowpage+1;
		    if(nextpage>pages){
		    	nextpage=pages;
		    }
		    Map<String,Object> session=ActionContext.getContext().getSession();
			  List<Examdate> examdateList=examdateBiz.findByPage(nowpage);
			  if(examdateList.size()>0){
			    session.remove(examdateList);
			    session.put("examdateList", examdateList);
			  }  
			   session.put("examdateList", examdateList); 
			  
			   return "findByPage_success";   
		    
	  }catch(Exception e){
		  e.printStackTrace();
	  }
	     return "exception";
  }
  public String findByPageSubject()throws Exception{
	  try{
		  if(subject.getId()==-1){
		    	findByPage();
		    	return "findByPage_success";
		    }
		  if(nowpage==null || nowpage==0){
				nowpage=1;
			}
		   pages=examdateBiz.findPageBySubject(subject);
		    backpage=nowpage-1;
		    if(backpage==0){
		    	backpage=1;
		    }
		    nextpage=nowpage+1;
		    if(nextpage>pages){
		    	nextpage=pages;
		    }
		    Map<String,Object> session=ActionContext.getContext().getSession();
			  List<Examdate> examdateList=examdateBiz.findDateByPageAndSubject(nowpage, subject);
			  if(examdateList.size()>0){
			    session.remove(examdateList);
			    session.put("examdateList", examdateList);
			  }  
			   session.put("examdateList", examdateList); 
			  
			   return "findByPageSubject_success";   
		    
	  }catch(Exception e){
		  e.printStackTrace();
	  }
	     return "exception";
  }
  
public ExamdateBiz getExamdateBiz() {
	return examdateBiz;
}
public void setExamdateBiz(ExamdateBiz examdateBiz) {
	this.examdateBiz = examdateBiz;
}
public Examdate getExamdate() {
	return examdate;
}
public void setExamdate(Examdate examdate) {
	this.examdate = examdate;
}
public Integer getDid() {
	return did;
}
public void setDid(Integer did) {
	this.did = did;
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
public Subject getSubject() {
	return subject;
}
public void setSubject(Subject subject) {
	this.subject = subject;
}
  
  
}
