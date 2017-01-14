package com.exam.action;

import java.util.List;
import java.util.Map;

import com.exam.biz.ExamdateBiz;
import com.exam.biz.NewBiz;
import com.exam.biz.PointBiz;
import com.exam.biz.SubjectBiz;
import com.exam.biz.VedioBiz;
import com.exam.entity.Examdate;
import com.exam.entity.New;
import com.exam.entity.Point;
import com.exam.entity.Subject;
import com.exam.entity.Vedio;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport {
  private SubjectBiz subjectBiz;
  private PointBiz pointBiz;
  private ExamdateBiz examdateBiz;
  private NewBiz newbiz;
  private VedioBiz videobiz;
  public String init()throws Exception{
	  try{
		  Map<String,Object> session=ActionContext.getContext().getSession();
		  List<Subject> subjectList=subjectBiz.findSubject();
		  List<Point> pointList=pointBiz.findPoint();
		  List<Examdate> examdateList=examdateBiz.findDate();
		  List<New> allnew  = newbiz.findAllNew();
		  List<Vedio> vlist=videobiz.findVedioByPage(1);
		  session.put("subjectList", subjectList);
		  session.put("subjectList", subjectList);
		  session.put("examdateList", examdateList);
		  session.put("allnew", allnew);
		  session.put("vlist", vlist);
		  
		  return "init_success";
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
public PointBiz getPointBiz() {
	return pointBiz;
}
public void setPointBiz(PointBiz pointBiz) {
	this.pointBiz = pointBiz;
}


public ExamdateBiz getExamdateBiz() {
	return examdateBiz;
}


public void setExamdateBiz(ExamdateBiz examdateBiz) {
	this.examdateBiz = examdateBiz;
}


public NewBiz getNewbiz() {
	return newbiz;
}


public void setNewbiz(NewBiz newbiz) {
	this.newbiz = newbiz;
}


public VedioBiz getVideobiz() {
	return videobiz;
}


public void setVideobiz(VedioBiz videobiz) {
	this.videobiz = videobiz;
}
  
}
