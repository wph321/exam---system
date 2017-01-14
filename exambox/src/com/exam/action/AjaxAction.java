package com.exam.action;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import com.exam.biz.PointBiz;
import com.exam.entity.Point;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class AjaxAction extends ActionSupport {
	private PointBiz pointBiz;
	private Integer sid;
	private String result;
	
	 public String findPointBySubject() throws Exception{
		 try{
			 System.out.println("����Ajax�ķ���");
			 System.out.println("��������Id�ǣ�"+sid);
			 Map<String,Object> session=ActionContext.getContext().getSession();
			  List<Point> pointList=pointBiz.findPointBySid(sid);
			  //���ù��˵�����
			  JsonConfig jc=new JsonConfig();
		      jc.setExcludes(new String[]{"subject","questions"});
			  result=JSONArray.fromObject(pointList,jc).toString();
			  System.out.println("���صĽ���ǣ�"+result);
			    	
				return SUCCESS;
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
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	

	
}
