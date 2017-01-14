package com.exam.action;

import java.util.List;
import java.util.Map;

import com.exam.biz.PointBiz;
import com.exam.entity.Point;
import com.exam.entity.Subject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PointAction extends ActionSupport {
     private PointBiz pointBiz;
     private Point point;
     private Subject subject;
     private Integer pid;
     
    private Integer nowpage;//记录请求的页数
   	private Integer backpage;//上一页
   	private Integer nextpage;//下一页 
   	private Integer pages;//总页数
 	
  public String add()throws Exception{
	  try{
		  int num=pointBiz.addPoint(point);
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
		  int num=pointBiz.deletePoint(pid);
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
		  int num=pointBiz.updatePoint(point);
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
		  List<Point> pointList=pointBiz.findPoint();
		  if(pointList!=null){
			  session.remove(pointList);
			  session.put("pointList", pointList);
		  }
		     session.put("pointList", pointList);
		     return "find_success";
 	  }catch(Exception e){
		  e.printStackTrace();
	  }
     return "exception";
  }
  public String findone()throws Exception{
	  try{
		  Map<String,Object> session=ActionContext.getContext().getSession();
		  Point point=pointBiz.findonePoint(pid);
		  session.put("point", point);
		  return "findone_success";
	  }catch(Exception e){
		  e.printStackTrace();
	  }
         return "exception";
  }
  
 public String  findByPageAndSubject()throws Exception{
	 try{
		 if(nowpage==null || nowpage==0){
				nowpage=1;
			}
		    pages=pointBiz.findPagesBySubject(subject);
		    backpage=nowpage-1;
		    if(backpage==0){
		    	backpage=1;
		    }
		    nextpage=nowpage+1;
		    if(nextpage>pages){
		    	nextpage=pages;
		    }
		    Map<String,Object> session=ActionContext.getContext().getSession();
		    List<Point> pointList=pointBiz.findBypageAndSubject(subject, nowpage);
			if(pointList!=null){
				session.remove(pointList);
				session.put("pointList", pointList);
			}
			    session.put("pointList", pointList);
			    
			    return "findByPageAndSubject_success";
		 
	 }catch(Exception e){
		 e.printStackTrace();
	 }
	 return "exception";
 }
 
 public  String findByPage()throws Exception{
	 try{
		 if(nowpage==null || nowpage==0){
				nowpage=1;
			}
		    pages=pointBiz.findPages();
		    backpage=nowpage-1;
		    if(backpage==0){
		    	backpage=1;
		    }
		    nextpage=nowpage+1;
		    if(nextpage>pages){
		    	nextpage=pages;
		    }
		    Map<String,Object> session=ActionContext.getContext().getSession();
		    List<Point> pointList=pointBiz.findByPage(nowpage);
			if(pointList!=null){
				session.remove(pointList);
				session.put("pointList", pointList);
			}
			    session.put("pointList", pointList);
			    
			    return "findByPage_success";
		 
	 }catch(Exception e){
		 e.printStackTrace();
	 }
	 return "exception";
 }
	public PointBiz getPointBiz() {
		return pointBiz;
	}
	public void setPointBiz(PointBiz pointBiz) {
		this.pointBiz = pointBiz;
	}
	public Point getPoint() {
		return point;
	}
	public void setPoint(Point point) {
		this.point = point;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
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
