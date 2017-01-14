package com.exam.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.exam.biz.ReplyBiz;
import com.exam.entity.Reply;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class ReplyAction extends ActionSupport {
	private Reply reply;
	private ReplyBiz replybiz;
	private Integer nowpage;
	//上传图片
			private File image;
			private String imageContentType;
			private String imageFileName;
	//admin
	private String type;
	public String findByPage() throws Exception {
		try{
			Map<String,Object> session = ActionContext.getContext().getSession();
			
			
			return "findByPage_success";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "exception";
	}
	public String add() throws Exception {
		try{
			//添加图片
			if(image!=null&&!"".equals(image)){
				String path =ServletActionContext.getRequest().getRealPath("/images/"+imageFileName);
				FileInputStream fis=new FileInputStream(image);
				FileOutputStream fos=new FileOutputStream(path);
				byte[] temp=new byte[1024];
				int size=-1;
				do{
					size=fis.read(temp);
					if(size!=-1)
						fos.write(temp, 0, size);
				}while(size!=-1);
				fos.flush();
				fos.close();
				fis.close();
				reply.setImage(imageFileName);
				
			}
			Date now=new Date();
			reply.setRdate(now);
			replybiz.addReply(reply);
			//更新
		
			return "add_success";
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "exception";
	}
	public String findall() throws Exception {
		try{
			
			return "findall_success";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "exception";
	}
	public String findone() throws Exception {
		try{
			
			return "findone_success";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "exception";
	}
	public String delete() throws Exception {
		try{
			replybiz.deleteReplyByrid(reply);
			if("admin".equals(type))
				return "admindelete_success";
			return "delete_success";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "exception";
	}
	public Reply getReply() {
		return reply;
	}
	public void setReply(Reply reply) {
		this.reply = reply;
	}
	public ReplyBiz getReplybiz() {
		return replybiz;
	}
	public void setReplybiz(ReplyBiz replybiz) {
		this.replybiz = replybiz;
	}
	public Integer getNowpage() {
		return nowpage;
	}
	public void setNowpage(Integer nowpage) {
		this.nowpage = nowpage;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}
	public String getImageContentType() {
		return imageContentType;
	}
	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	
}
