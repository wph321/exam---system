package com.exam.action;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.exam.biz.AdminBiz;
import com.exam.biz.StudentBiz;
import com.exam.entity.Admin;
import com.exam.entity.Student;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class AdminAction extends ActionSupport {
	private Admin admin;
	private AdminBiz adminBiz;
	private StudentBiz studentBiz;
	
	private Integer nowpage;
	private Integer pages;
	private Integer nextpage;
	private Integer backpage;
	
	private String adminName;
	
	public String login(){
		try{
			Map session = ActionContext.getContext().getSession();
			System.out.println("进入到了管理员登录的方法中");
			String loginName = admin.getLoginname();
			String loginPwd= admin.getLoginpass();
			if(session.get("erroradmin")!=null){
				session.remove("erroradmin");
			}
			if(session.get("error")!=null){
				session.remove("error");
			}
			if(session.get("djerror")!=null){
				session.remove("djerror");
			}
			Admin admin = adminBiz.login(loginName, loginPwd);
			
			if(admin!=null){
				System.out.println("admin不为空");
				if(session.get("user")!=null){
					session.remove("user");
					session.put("user",admin);
				}else{
					session.put("user",admin);
				}
				session.put("admin", admin);
				//获取所有学生的基本信息，并保存到session中
				List<Student> stuList = studentBiz.findStudents();
				if(session.get("stuList")!=null){
					session.remove("stuList");
					session.put("stuList",stuList);
				}else{
					session.put("stuList",stuList);
				}
				
				
				//获取所有管理员的基本信息，并保存到session中
				List<Admin> adminList  = adminBiz.findAdmins();
				System.out.println(adminList.size());
				if(session.get("adminList")!=null){
					session.remove("adminList");
					session.put("adminList",adminList);
				}else{
					session.put("adminList",adminList);
				}
				return "adminlogin_success";
			}else{
				System.out.println("管理员为空");
				session.put("erroradmin","管理员账号或错误");
				return "no_user";
			}
			
		
		}catch(Exception ex){
			ex.printStackTrace();
			return "exception";
		}
	}
	
	public String add(){
		System.out.println("进入到了Admin的add的方法中");
		try{
			Map session = ActionContext.getContext().getSession();
			String loginname = admin.getLoginname();
			String loginpass = admin.getLoginpass();
			String uname = admin.getAname();
			String phone = admin.getPhone();
			String email = admin.getEmail();
			
			Admin temp_admin = new  Admin();
			temp_admin.setAname(uname);
			temp_admin.setEmail(email);
			temp_admin.setLoginname(loginname);
			temp_admin.setLoginpass(loginpass);
			temp_admin.setPhone(phone);
			adminBiz.addAdmin(temp_admin);
			
			List<Admin> adminList  = adminBiz.findAdmins();
			if(session.get("adminList")!=null){
				session.remove("adminList");
				session.put("adminList",adminList);
			}else{
				session.put("adminList",adminList);
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			return "exception";
		}
		return  "adminadd_success";
	}
	public String findByPage(){
		
		try{
			System.out.println("进入到了分页查找管理员的方法");
			Map session = ActionContext.getContext().getSession();
			if(nowpage==null||nowpage==0){
				nowpage=1;
			}
			List<Admin> adminList;
			if(session.get("backName")!=null){
				session.remove("backName");
				session.put("backName",adminName);
			}else{
				session.put("backName",adminName);
			}
			
			if(adminName!=null){
				Admin temp_admin = new Admin();
				temp_admin.setAname(adminName);
				adminList = adminBiz.findAdminByPageAndAdmin(nowpage,temp_admin);
				pages = adminBiz.findPageByAdmin(temp_admin);
			}
			else{
				adminList  = adminBiz.findAdminByPage(nowpage);
				pages = adminBiz.findPage();
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
			
				if(session.get("adminList")!=null){
					session.remove("adminList");
					session.put("adminList",adminList);
				}else{
					session.put("adminList",adminList);
				}
				return "findByPage_success";
			}catch(Exception ex){
				ex.printStackTrace();
				return "exception";
			}
		
	}
	
	public String addAJAX(){
	
			try{
				System.out.println("进入到了AJAX方法中");
				HttpServletRequest req= ServletActionContext.getRequest();
				HttpServletResponse resp = ServletActionContext.getResponse();
				req.setCharacterEncoding("utf-8");
				String loginname = admin.getLoginname();
				String AName = admin.getAname();
				Admin temp_admin1 = adminBiz.findAdminByAName(AName);
				Admin temp_admin2 = adminBiz.findAminByloginName(loginname);
				
				if(temp_admin1!=null&&temp_admin2!=null){
					String info = "都不存在";
					resp.setCharacterEncoding("utf-8");
					PrintWriter out=resp.getWriter();
					out.print(info);
			    	out.flush();
			    	out.close();
			    	return "error";
				}
				if(temp_admin1!=null){
					String info = "该姓名已经存在，请重新填写";
					resp.setCharacterEncoding("utf-8");
					PrintWriter out=resp.getWriter();
					out.print(info);
			    	out.flush();
			    	out.close();
			    	return "error";
				}
				else if(temp_admin2!=null){
					String info = "用户名重复，请重新填写";
					resp.setCharacterEncoding("utf-8");
					PrintWriter out=resp.getWriter();
					out.print(info);
			    	out.flush();
			    	out.close();
			    	return "error";
				}
				else {
					String info = "成功";
					resp.setCharacterEncoding("utf-8");
					PrintWriter out=resp.getWriter();
					out.print(info);
			    	out.flush();
			    	out.close();
			    	return "error";
				}
			}catch(Exception ex){
				ex.printStackTrace();
				return "exception";
			}
	}
	public String exit(){
		System.out.println("进入到了管理员退出的方法");
		Map session  = ActionContext.getContext().getSession();
		session.remove("user");
		return "exit_success";
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public AdminBiz getAdminBiz() {
		return adminBiz;
	}
	public void setAdminBiz(AdminBiz adminBiz) {
		this.adminBiz = adminBiz;
	}
	public StudentBiz getStudenBiz() {
		return studentBiz;
	}
	public void setStudenBiz(StudentBiz studentBiz) {
		this.studentBiz = studentBiz;
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

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
}