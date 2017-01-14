package com.exam.action;

import java.util.Date;

import com.exam.biz.VcommentBiz;
import com.exam.entity.Vcomment;
import com.opensymphony.xwork2.ActionSupport;

public class VcommentAction extends ActionSupport {
 private Vcomment comment;
 private VcommentBiz vcommentBiz;
 private Integer vid;
 public String add()throws Exception{
	 try{
		 System.out.println("进入添加的地方法");
		 comment.setTime(new Date());
		 int num=vcommentBiz.addVcomment(comment);
		 vid=comment.getVedio().getId();
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
		 System.out.println("进入删除的方法");
		 int num=vcommentBiz.deleteVcomment(comment);
		 if(num>0){
			 return "delete_success";
		 }
		     return "delete_error";
	 }catch(Exception e){
		 e.printStackTrace();
	 }
	 return "exception";
 }
public Vcomment getComment() {
	return comment;
}
public void setComment(Vcomment comment) {
	this.comment = comment;
}
public VcommentBiz getVcommentBiz() {
	return vcommentBiz;
}
public void setVcommentBiz(VcommentBiz vcommentBiz) {
	this.vcommentBiz = vcommentBiz;
}
public Integer getVid() {
	return vid;
}
public void setVid(Integer vid) {
	this.vid = vid;
}
 
}
