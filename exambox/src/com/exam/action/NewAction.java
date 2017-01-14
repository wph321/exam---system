package com.exam.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.exam.biz.NewBiz;
import com.exam.entity.New;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class NewAction extends ActionSupport {
	private New n;
	private NewBiz newbiz;
	private Integer nowpage;
	//上传图片
	private File image;
	private String imageContentType;
	private String imageFileName;
	//判断管理员
	private String type;

	public String findall() throws Exception {
		try{
			Map<String,Object> session = ActionContext.getContext().getSession();
			List<New> newList =newbiz.findAllNew();
			session.put("newList", newList);
			return "findall_success";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "exception";
	}
	public String findByPage() throws Exception {
		try{
			Map<String,Object> session = ActionContext.getContext().getSession();
			int allPage =newbiz.findPage(n);
			if(nowpage==null||nowpage<1)
				nowpage=1;
			if(nowpage>allPage)
				nowpage=allPage;
			List<New> newList = newbiz.findNewByTermAndPage(n, nowpage);
			session.put("allPage", allPage);
			session.put("newList", newList);
			if("admin".equals(type))
				return "adminfindByPage_success";
			return "findByPage_success";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "exception";
	}
	public String findone() throws Exception {
		try{
			Map<String,Object> session = ActionContext.getContext().getSession();
			New findonenew =newbiz.findNewByNid(n);
			session.put("findonenew", findonenew);
			//判断管理员
			if("admin".equals(type))
				return "admin_findone";
			return "findone_success";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "exception";
	}
	public String add() throws Exception {
		try{
			System.out.println("ssssssssssssssssssss");
			if(image!=null&&!"".equals(image)){
				//添加图片
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
				n.setNimage(imageFileName);
			}
			//当前时间
			n.setNdate(new Date());
			newbiz.addNew(n);
			//重新查所有
			Map<String,Object> session = ActionContext.getContext().getSession();
			List<New> newList =newbiz.findAllNew();
			session.put("newList", newList);
			return "add_success";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "exception";
	}
	public String update() throws Exception {
		try{
			if(image!=null&&!"".equals(image)){
				//修改图片
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
				n.setNimage(imageFileName);
			}
			newbiz.updateNew(n);
			//重新查所有
			Map<String,Object> session = ActionContext.getContext().getSession();
			List<New> newList =newbiz.findAllNew();
			session.put("newList", newList);
			return "update_success";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "exception";
	}
	public String delete() throws Exception {
		try{
			newbiz.deleteNewByNid(n);
			//重新查所有
			Map<String,Object> session = ActionContext.getContext().getSession();
			List<New> newList =newbiz.findAllNew();
			session.put("newList", newList);
			return "delete_success";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "exception";
	}
	
	
	
	
	public New getN() {
		return n;
	}
	public void setN(New n) {
		this.n = n;
	}
	public NewBiz getNewbiz() {
		return newbiz;
	}
	public void setNewbiz(NewBiz newbiz) {
		this.newbiz = newbiz;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getNowpage() {
		return nowpage;
	}
	public void setNowpage(Integer nowpage) {
		this.nowpage = nowpage;
	}
	
}
