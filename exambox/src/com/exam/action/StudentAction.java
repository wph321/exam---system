package com.exam.action;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.exam.biz.CollectBiz;
import com.exam.biz.ErrorBiz;
import com.exam.biz.GradeBiz;
import com.exam.biz.StudentBiz;
import com.exam.entity.Collect;
import com.exam.entity.Grade;
import com.exam.entity.Sign;
import com.exam.entity.Student;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Student entity. @author MyEclipse Persistence Tools
 */

public class StudentAction extends ActionSupport {
	private Student student;
	
	private StudentBiz studentBiz;
	private ErrorBiz errorBiz;
	private CollectBiz collectBiz;
	private GradeBiz gradeBiz;
	private File image;
	private String beforepwd;
	private String imageContentType;
	private String imageFileName;

	private Integer nowpage;
	private Integer pages;
	private Integer nextpage;
	private Integer backpage;
	
	private String searchName;
	private String UName;
	private String loginName;
	private String yzm;
	
	public String login() throws Exception{
		System.out.println("进入到了登录方法中了");
		Map session = ActionContext.getContext().getSession();
		if(session.get("error")!=null){
			session.remove("error");
		}
		if(session.get("djerror")!=null){
			session.remove("djerror");
		}
		String loginName =  student.getLoginname();
		String loginPwd = student.getLoginpass();
		
		Student  stu= studentBiz.login(loginName, loginPwd);
		
		List<Student> stuList = studentBiz.findStudents();
		
		
		if(session.get("stuList")!=null){
			session.remove("stuList");
			session.put("stuList",stuList);
		}else{
			session.put("stuList",stuList);
		}
	
		if(stu!=null){
			System.out.println("有用户");
			System.out.println(stu.getStatus());
				if(!"0".equals(stu.getStatus())){
						
						if(session.get("user")!=null){
							session.remove("user");
							session.put("user",stu);
						}else{
							session.put("user",stu);
						}
						Date date = new Date();
						if(session.get("lastLoginTime")!=null){
							session.remove("lastLoginTime");
							session.put("lastLoginTime",date);
						}else{
							session.put("lastLoginTime",date);
						}
					//读取收藏信息
						List<Collect> collectList =collectBiz.findAllCollectByStu(stu);
						if(session.get("collectList")!=null){
							session.remove("collectList");
							session.put("collectList",collectList);
						}else{
							session.put("collectList",collectList);
						}
						return "login_success";
						
				}else{
						System.out.println("账户被冻结");
						if(session.get("djerror")!=null){
							session.remove("djerror");
							if(session.get("error")!=null){
								session.remove("error");	
							}
							session.put("djerror","账户已被冻结");
						}else{
							session.put("djerror","账户已被冻结");
						}
						return "no_user";
			}
		}else{
			System.out.println("没有用户");
			if(session.get("error")!=null){
				session.remove("error");
				session.put("error","账户或密码错误");
			}else{
				session.put("error","账户或密码错误");
			}
			return "no_user";
		}
	}
	
	
	
	
	public String checkNC(){
		try{
		System.out.println("进入到了检查昵称是否重复的方法");
		HttpServletRequest req= ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		
		req.setCharacterEncoding("utf-8");
		
		Student student  = studentBiz.findStudentByUName(UName);
		resp.setCharacterEncoding("utf-8");
		if(student!=null){
			String info = "用户名重复";
			PrintWriter out=resp.getWriter();
			out.print(info);
	    	out.flush();
	    	out.close();
		}
		
		}catch(Exception ex){
			ex.printStackTrace();
			return "exception";
		}
		return "checkUName_success";
	}
	
	public String checkLoginName(){
		try{
		System.out.println("进入到了检查loginname是否重复的方法");
		HttpServletRequest req= ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		req.setCharacterEncoding("utf-8");
		Student student  = studentBiz.findStudentByLoginName(loginName);
		resp.setCharacterEncoding("utf-8");
		if(student!=null){
			String info = "账号重复";
			PrintWriter out=resp.getWriter();
			out.print(info);
	    	out.flush();
	    	out.close();
		}
		}catch(Exception ex){
			ex.printStackTrace();
			return "exception";
		}
		return "checkUName_success";
	}
	
	public String addChart() {
		try{
			
		System.out.println("进入到了生产图表的方法");
		DefaultCategoryDataset ds = new DefaultCategoryDataset();  
        
		Map session = ActionContext.getContext().getSession();
		
		Integer subid = (Integer) session.get("gradesubid");
		System.out.println("此时获取到的id是");
		//获取长度部分
		
		
		Student student = (Student) session.get("user");
		List<Grade> gradeList = gradeBiz.findGradeBySubIdAndStudent(subid,student);
		
		
		Integer length = gradeBiz.findGradeBySubIdAndStudent(subid,student).size();
		

		
		int flag = 0;
		if(length.equals(1)){
			//length==1,说明考了一次
			 ds.addValue(gradeList.get(0).getScore(), gradeList.get(0).getSubject().getSname(),"第"+1+"次考试"); 
		}else{
			//考了多次
			for(int i=0;i<length;i++){
			 ds.addValue(gradeList.get(i).getScore(), gradeList.get(i).getSubject().getSname(), "第"+(i+1)+"次考试"); 	
			}
		}
			
//		}
//		
//        ds.addValue(100, "IBM", "二季度");  
//        ds.addValue(100, "ORACLE", "二季度");  
//        ds.addValue(100, "JBOSS", "二季度");  
//        ds.addValue(100, "用友", "二季度");  
//          
//        ds.addValue(100, "IBM", "三季度");  
//        ds.addValue(100, "ORACLE", "三季度");  
//        ds.addValue(100, "JBOSS", "三季度");  
//        ds.addValue(100, "用友", "三季度");  
//        ds.addValue(100, "IBM", "四季度");  
//        ds.addValue(100, "ORACLE", "四季度");  
//        ds.addValue(100, "JBOSS", "四季度");  
//        ds.addValue(100, "用友", "四季度"); 
        
        
        String title = "考试成绩图表" ;  
        JFreeChart chart = ChartFactory.createBarChart3D(title, "考试次数", "分数", ds, PlotOrientation.VERTICAL, true, false, false);  
          
        //中文  
        chart.getTitle().setFont(new Font("宋体", Font.BOLD, 25));//大标题  
          
        //提示条  
        chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 15));  
          
        CategoryPlot plot = (CategoryPlot) chart.getPlot();  
        //域轴字体  
        plot.getDomainAxis().setLabelFont(new Font("宋体", Font.BOLD, 18));  
        plot.getDomainAxis().setTickLabelFont(new Font("宋体", Font.PLAIN, 15));//小标签字体  
          
        //range  
        plot.getRangeAxis().setLabelFont(new Font("宋体", Font.BOLD, 15));  
          
        plot.setForegroundAlpha(0.6f);  
        String path = ServletActionContext.getRequest().getRealPath("/images/zk.jpg");
        ChartUtilities.saveChartAsJPEG(new File(path), chart, 800, 500);
        
		}catch(Exception ex){
			ex.printStackTrace();
			return "exception";
		}
		return "addChart_success";
	}
	public String exit(){
		System.out.println("进入到了用户退出的方法");
		Map session  = ActionContext.getContext().getSession();
		session.remove("user");
		return "exit_success";
	}
	public String statusUd(){
		try{
			System.out.println("进入了状态改变的方法");
			//根据id查询学生
			Student temp_stu = studentBiz.findStudentBySid(student.getId());
			//修改学生状态
			if(temp_stu.getStatus().equals("1")){
				temp_stu.setStatus("0");
			}else{
				temp_stu.setStatus("1");
			}
			//执行biz调用修改
			studentBiz.updateStudent(temp_stu);
			//刷新map中的记录
			Map session = ActionContext.getContext().getSession();
			List<Student> stuList = studentBiz.findStudents();
			if(session.get("stuList")!=null){
				session.remove("stuList");
				session.put("stuList",stuList);
			}else{
				session.put("stuList",stuList);
			}
			
			this.fy();
			return "statusUd_success";
		}catch(Exception ex){
			ex.printStackTrace();
			return "exception";
		}
	}
	
	public void fy(){
		try{
			System.out.println("进入到了分页查找学生的方法");
			
			Map session = ActionContext.getContext().getSession();
			
			
			if(nowpage==null||nowpage==0){
				nowpage=1;
			}
			List<Student> stuList ;
			if(session.get("backName")!=null){
				session.remove("backName");
				session.put("backName",searchName);
			}else{
				session.put("backName",searchName);
			}
			
			if(searchName!=null){
				Student temp_student = new Student();
				temp_student.setUname(searchName);
				stuList = studentBiz.findStudentByPageAndStu(nowpage,temp_student);
				pages = studentBiz.findPageByStu(temp_student);
			}
			else{
				stuList  = studentBiz.findStudentByPage(nowpage);
				pages = studentBiz.findPage();
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
			
				if(session.get("stuList")!=null){
					session.remove("stuList");
					session.put("stuList",stuList);
				}else{
					session.put("stuList",stuList);
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
	}
	
	public String delete(){
		try{
			System.out.println("进入到了删除学生的方法中");
			Map session = ActionContext.getContext().getSession();
			
			Student temp_stu = studentBiz.findStudentBySid(student.getId());
		
			studentBiz.deleteStudent(temp_stu);
			
			//更新学生列表
			List<Student> stuList = studentBiz.findStudents();
			if(session.get("stuList")!=null){
				session.remove("stuList");
				session.put("stuList",stuList);
			}else{
				session.put("stuList",stuList);
			}
			
			return "delete_success";
		}catch(Exception ex){
			ex.printStackTrace();
			return "exception";
		}
	}
	
	public String changepwd(){
		System.out.println("进入到了changepwd方法中");
		Map session = ActionContext.getContext().getSession();
		//根据用户id判断原先id是否正确
		try{
		Student stu = studentBiz.findStudentBySid(student.getId());
		if(stu.getLoginpass().equals(beforepwd)){
			stu.setLoginpass(student.getLoginpass());
			studentBiz.updateStudent(stu);
			session.put("ts", "<script>alert('修改密码成功');</script>");
			return "changepwd_success";
		}else{
			session.put("ts", "<script>alert('修改失败，请稍后重试');</script>");
			return "changepwd_error";
		}
		}catch(Exception ex){
			ex.printStackTrace();
			return "exception";
		}
	}
	
	public String update(){
		try{
			System.out.println("进入到了update方法中了");
			Student stu = studentBiz.findStudentBySid(student.getId());
			String email = student.getEmail();
			String phone = student.getPhone();
			
			String path = ServletActionContext.getRequest().getRealPath("/images/"+imageFileName);
			FileInputStream fis = new FileInputStream(image);
			FileOutputStream fos = new FileOutputStream(path);
			byte[]temp = new byte[1024];
			int size=-1;
			do{
				size=fis.read(temp);
				if(size!=-1)
					fos.write(temp,0,size);
			}while(size!=-1);
			fos.flush();
			fos.close();
			fis.close();
			
			stu.setEmail(email);
			stu.setPhone(phone);
			stu.setImage(imageFileName);
			studentBiz.updateStudent(stu);
			
		}catch(Exception ex){
			ex.printStackTrace();
			return "exception";
		}
		return "update_success";
	}
	public String add() {
		try{
			Map session = ActionContext.getContext().getSession();
			
			System.out.println("进入到了add方法中来,曹");
			System.out.println(student.getLoginname());
			String loginname = student.getLoginname();
			String loginpwd = student.getLoginpass();
			String email = student.getEmail();
			String phone = student.getPhone();
			String uname = student.getUname();
			
			//首相判断用户名 和登录名是否重复
			Student temp_student1  = studentBiz.findStudentByLoginName(loginname);
			Student temp_student2 = studentBiz.findStudentByUName(uname);
			String info = "";
			Student temp_student = new Student();
			System.out.println("执行完了创建对象");
			temp_student.setLoginname(loginname);
			temp_student.setLoginpass(loginpwd);
			temp_student.setEmail(email);
			temp_student.setPhone(phone);
			temp_student.setUname(uname);
			
			if(session.get("registerLoginName")!=null){
				session.remove("registerLoginName");
				session.put("registerLoginName", loginname);
			}else{
				session.put("registerLoginName", loginname);
			}
			//目前写死的部分
			temp_student.setStatus("1");
			temp_student.setType("1");
			System.out.println("执行完了设置基本值");
			//文件上传部分
			String path = ServletActionContext.getRequest().getRealPath("/images/"+imageFileName);
			FileInputStream fis = new FileInputStream(image);
			FileOutputStream fos = new FileOutputStream(path);
			byte[]temp = new byte[1024];
			int size=-1;
			do{
				size=fis.read(temp);
				if(size!=-1)
					fos.write(temp,0,size);
			}while(size!=-1);
			fos.flush();
			fos.close();
			fis.close();
			temp_student.setImage(imageFileName);
			
			System.out.println("执行完了文件上传");
			studentBiz.addStudent(temp_student);
			}catch(Exception ex){
				ex.printStackTrace();
				return "exception";
			}
		return "add_success";
	}
	
	public String adminadd(){
		try{
			
			System.out.println("进入到了add方法中来,222");
			System.out.println(student.getLoginname());
			String loginname = student.getLoginname();
			String loginpwd = student.getLoginpass();
			String email = student.getEmail();
			String phone = student.getPhone();
			String uname = student.getUname();
			Student temp_student = new Student();
			System.out.println("执行完了创建对象");
		
			System.out.println(uname);
			
			//判断用户真实姓名是否重复
			
			
				
		    	//执行增加操作
				temp_student.setLoginname(student.getLoginname());
				temp_student.setLoginpass(loginpwd);
				temp_student.setEmail(email);
				temp_student.setPhone(phone);
				temp_student.setUname(uname);
				
				//目前写死的部分
				temp_student.setStatus("1");
				temp_student.setType("1");
				System.out.println("执行完了设置基本值");
				//文件上传部分
				String path = ServletActionContext.getRequest().getRealPath("/images/"+imageFileName);
				FileInputStream fis = new FileInputStream(image);
				FileOutputStream fos = new FileOutputStream(path);
				byte[]temp = new byte[1024];
				int size=-1;
				do{
					size=fis.read(temp);
					if(size!=-1)
						fos.write(temp,0,size);
				}while(size!=-1);
				fos.flush();
				fos.close();
				fis.close();
				temp_student.setImage(imageFileName);
				
				System.out.println("执行完了文件上传");
				studentBiz.addStudent(temp_student);
				
				//更新操作
				Map session =ActionContext.getContext().getSession();
				List<Student> stuList = studentBiz.findStudents();
				if(session.get("stuList")!=null){
					session.remove("stuList");
					session.put("stuList",stuList);
				}else{
					session.put("stuList",stuList);
				}
				return "adminadd_success";
			}catch(Exception ex){
				ex.printStackTrace();
				return "exception";
			}
	}
	
	public String adminaddAJAX(){
		try{
			System.out.println("进入到了AJAX方法中");
			HttpServletRequest req= ServletActionContext.getRequest();
			HttpServletResponse resp = ServletActionContext.getResponse();
			req.setCharacterEncoding("utf-8");
			String loginname = student.getLoginname();
			String uname = student.getUname();
			Student temp_student1  = studentBiz.findStudentByLoginName(loginname);
			Student temp_student2 = studentBiz.findStudentByUName(uname);
			if(temp_student2!=null&&temp_student1!=null){
				String info = "都错";
				resp.setCharacterEncoding("utf-8");
				PrintWriter out=resp.getWriter();
				out.print(info);
		    	out.flush();
		    	out.close();
		    	return "error";
			}
			
			if(temp_student2!=null){
				String info = "该姓名已经存在，请重新填写";
				System.out.println("进入到不为空的方法");
				resp.setCharacterEncoding("utf-8");
				PrintWriter out=resp.getWriter();
				out.print(info);
		    	out.flush();
		    	out.close();
		    	return "error";
			}
			else if(temp_student1!=null){
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
	
	public String addAJAX(){
		try{
			System.out.println("进入到了AJAX方法中");
			HttpServletRequest req= ServletActionContext.getRequest();
			HttpServletResponse resp = ServletActionContext.getResponse();
			req.setCharacterEncoding("utf-8");
			String loginname = student.getLoginname();
			String uname = student.getUname();
			Student temp_student1  = studentBiz.findStudentByLoginName(loginname);
			Student temp_student2 = studentBiz.findStudentByUName(uname);
			if(temp_student2!=null&&temp_student1!=null){
				String info = "都错";
				resp.setContentType("text/html;charset=UTF-8"); 
				PrintWriter out=resp.getWriter();
				out.print(info);
		    	out.flush();
		    	out.close();
		    	return "register_error";
			}
			
			if(temp_student2!=null){
				String info = "该姓名已经存在，请重新填写";
	
				resp.setContentType("text/html;charset=UTF-8"); 
				PrintWriter out=resp.getWriter();
				out.print(info);
		    	out.flush();
		    	out.close();
		    	return "register_error";
			}
			else if(temp_student1!=null){
				String info = "用户名重复，请重新填写";
				resp.setContentType("text/html;charset=UTF-8"); 
				PrintWriter out=resp.getWriter();
				out.print(info);
		    	out.flush();
		    	out.close();
		    	return "register_error";
			}
			else {
				String info = "成功";
				resp.setContentType("text/html;charset=UTF-8"); 
				PrintWriter out=resp.getWriter();
				out.print(info);
		    	out.flush();
		    	out.close();
		    	return "register_error";
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return "exception";
		}
	}
	
	public String findByPage(){
		try{
		System.out.println("进入到了分页查找学生的方法");
		
		Map session = ActionContext.getContext().getSession();
		if(nowpage==null||nowpage==0){
			nowpage=1;
		}
		List<Student> stuList ;
		if(session.get("backName")!=null){
			session.remove("backName");
			session.put("backName",searchName);
		}else{
			session.put("backName",searchName);
		}
		
		if(searchName!=null){
			Student temp_student = new Student();
			temp_student.setUname(searchName);
			stuList = studentBiz.findStudentByPageAndStu(nowpage,temp_student);
			pages = studentBiz.findPageByStu(temp_student);
		}
		else{
			stuList  = studentBiz.findStudentByPage(nowpage);
			pages = studentBiz.findPage();
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
		
			if(session.get("stuList")!=null){
				session.remove("stuList");
				session.put("stuList",stuList);
			}else{
				session.put("stuList",stuList);
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
			return "exception";
		}
		return "findByPage_success";
	}
	
	public String yzm() {
		
		try{
			Map<String,Object> session = ActionContext.getContext().getSession();
			String rand=(String) session.get("random");
			
			HttpServletRequest req= ServletActionContext.getRequest();
			HttpServletResponse resp = ServletActionContext.getResponse();
			req.setCharacterEncoding("utf-8");
			
			if(rand.equals(yzm)){
				String info = "输入正确";
				System.out.println("进入到不为空的方法");
				resp.setContentType("text/html;charset=UTF-8"); 
				PrintWriter out=resp.getWriter();
				out.print(info);
		    	out.flush();
		    	out.close();
		    	return "error";
			}else{
				String info = "输入错误";
				System.out.println("进入到不为空的方法");
				resp.setContentType("text/html;charset=UTF-8"); 
				PrintWriter out=resp.getWriter();
				out.print(info);
		    	out.flush();
		    	out.close();
		    	return "error";
			}
			//刷新数据
		}catch(Exception e){
			e.printStackTrace();
			return "exception";
		}
		
	}
	
	public String yzmRegister(){
		try{
			System.out.println("进入到了注册验证码");
			Map<String,Object> session = ActionContext.getContext().getSession();
			String rand=(String) session.get("random");
			
			HttpServletRequest req= ServletActionContext.getRequest();
			HttpServletResponse resp = ServletActionContext.getResponse();
			req.setCharacterEncoding("utf-8");
			
			if(rand.equals(yzm)){
				String info = "输入正确";
				resp.setContentType("text/html;charset=UTF-8"); 
				PrintWriter out=resp.getWriter();
				out.print(info);
		    	out.flush();
		    	out.close();
		    	return "error";
			}else{
				String info = "输入错误";
				resp.setContentType("text/html;charset=UTF-8"); 
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
	
	public String checkPass(){
		try{
			Map session = ActionContext.getContext().getSession();
			Student temp_student = (Student) session.get("user");
			HttpServletRequest req= ServletActionContext.getRequest();
			HttpServletResponse resp = ServletActionContext.getResponse();
			req.setCharacterEncoding("utf-8");
			String loginpass = student.getLoginpass();
			if(temp_student.getLoginpass().equals(loginpass)){
				String info = "正确";
				resp.setContentType("text/html;charset=UTF-8"); 
				PrintWriter out=resp.getWriter();
				out.print(info);
		    	out.flush();
		    	out.close();
		    	return "error";
			}else{
				String info = "错误";
				resp.setContentType("text/html;charset=UTF-8"); 
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
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public StudentBiz getStudentBiz() {
		return studentBiz;
	}
	public void setStudentBiz(StudentBiz studentBiz) {
		this.studentBiz = studentBiz;
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

	public String getBeforepwd() {
		return beforepwd;
	}

	public void setBeforepwd(String beforepwd) {
		this.beforepwd = beforepwd;
	}
	public ErrorBiz getErrorBiz() {
		return errorBiz;
	}
	public void setErrorBiz(ErrorBiz errorBiz) {
		this.errorBiz = errorBiz;
	}
	public CollectBiz getCollectBiz() {
		return collectBiz;
	}
	public void setCollectBiz(CollectBiz collectBiz) {
		this.collectBiz = collectBiz;
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
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	public String getUName() {
		return UName;
	}
	public void setUName(String uName) {
		UName = uName;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}




	public String getYzm() {
		return yzm;
	}




	public void setYzm(String yzm) {
		this.yzm = yzm;
	}

}