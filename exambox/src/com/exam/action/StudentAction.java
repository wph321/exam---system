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
		System.out.println("���뵽�˵�¼��������");
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
			System.out.println("���û�");
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
					//��ȡ�ղ���Ϣ
						List<Collect> collectList =collectBiz.findAllCollectByStu(stu);
						if(session.get("collectList")!=null){
							session.remove("collectList");
							session.put("collectList",collectList);
						}else{
							session.put("collectList",collectList);
						}
						return "login_success";
						
				}else{
						System.out.println("�˻�������");
						if(session.get("djerror")!=null){
							session.remove("djerror");
							if(session.get("error")!=null){
								session.remove("error");	
							}
							session.put("djerror","�˻��ѱ�����");
						}else{
							session.put("djerror","�˻��ѱ�����");
						}
						return "no_user";
			}
		}else{
			System.out.println("û���û�");
			if(session.get("error")!=null){
				session.remove("error");
				session.put("error","�˻����������");
			}else{
				session.put("error","�˻����������");
			}
			return "no_user";
		}
	}
	
	
	
	
	public String checkNC(){
		try{
		System.out.println("���뵽�˼���ǳ��Ƿ��ظ��ķ���");
		HttpServletRequest req= ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		
		req.setCharacterEncoding("utf-8");
		
		Student student  = studentBiz.findStudentByUName(UName);
		resp.setCharacterEncoding("utf-8");
		if(student!=null){
			String info = "�û����ظ�";
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
		System.out.println("���뵽�˼��loginname�Ƿ��ظ��ķ���");
		HttpServletRequest req= ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		req.setCharacterEncoding("utf-8");
		Student student  = studentBiz.findStudentByLoginName(loginName);
		resp.setCharacterEncoding("utf-8");
		if(student!=null){
			String info = "�˺��ظ�";
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
			
		System.out.println("���뵽������ͼ��ķ���");
		DefaultCategoryDataset ds = new DefaultCategoryDataset();  
        
		Map session = ActionContext.getContext().getSession();
		
		Integer subid = (Integer) session.get("gradesubid");
		System.out.println("��ʱ��ȡ����id��");
		//��ȡ���Ȳ���
		
		
		Student student = (Student) session.get("user");
		List<Grade> gradeList = gradeBiz.findGradeBySubIdAndStudent(subid,student);
		
		
		Integer length = gradeBiz.findGradeBySubIdAndStudent(subid,student).size();
		

		
		int flag = 0;
		if(length.equals(1)){
			//length==1,˵������һ��
			 ds.addValue(gradeList.get(0).getScore(), gradeList.get(0).getSubject().getSname(),"��"+1+"�ο���"); 
		}else{
			//���˶��
			for(int i=0;i<length;i++){
			 ds.addValue(gradeList.get(i).getScore(), gradeList.get(i).getSubject().getSname(), "��"+(i+1)+"�ο���"); 	
			}
		}
			
//		}
//		
//        ds.addValue(100, "IBM", "������");  
//        ds.addValue(100, "ORACLE", "������");  
//        ds.addValue(100, "JBOSS", "������");  
//        ds.addValue(100, "����", "������");  
//          
//        ds.addValue(100, "IBM", "������");  
//        ds.addValue(100, "ORACLE", "������");  
//        ds.addValue(100, "JBOSS", "������");  
//        ds.addValue(100, "����", "������");  
//        ds.addValue(100, "IBM", "�ļ���");  
//        ds.addValue(100, "ORACLE", "�ļ���");  
//        ds.addValue(100, "JBOSS", "�ļ���");  
//        ds.addValue(100, "����", "�ļ���"); 
        
        
        String title = "���Գɼ�ͼ��" ;  
        JFreeChart chart = ChartFactory.createBarChart3D(title, "���Դ���", "����", ds, PlotOrientation.VERTICAL, true, false, false);  
          
        //����  
        chart.getTitle().setFont(new Font("����", Font.BOLD, 25));//�����  
          
        //��ʾ��  
        chart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));  
          
        CategoryPlot plot = (CategoryPlot) chart.getPlot();  
        //��������  
        plot.getDomainAxis().setLabelFont(new Font("����", Font.BOLD, 18));  
        plot.getDomainAxis().setTickLabelFont(new Font("����", Font.PLAIN, 15));//С��ǩ����  
          
        //range  
        plot.getRangeAxis().setLabelFont(new Font("����", Font.BOLD, 15));  
          
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
		System.out.println("���뵽���û��˳��ķ���");
		Map session  = ActionContext.getContext().getSession();
		session.remove("user");
		return "exit_success";
	}
	public String statusUd(){
		try{
			System.out.println("������״̬�ı�ķ���");
			//����id��ѯѧ��
			Student temp_stu = studentBiz.findStudentBySid(student.getId());
			//�޸�ѧ��״̬
			if(temp_stu.getStatus().equals("1")){
				temp_stu.setStatus("0");
			}else{
				temp_stu.setStatus("1");
			}
			//ִ��biz�����޸�
			studentBiz.updateStudent(temp_stu);
			//ˢ��map�еļ�¼
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
			System.out.println("���뵽�˷�ҳ����ѧ���ķ���");
			
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
			
			//��һҳ��һҳ������
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
			System.out.println("���뵽��ɾ��ѧ���ķ�����");
			Map session = ActionContext.getContext().getSession();
			
			Student temp_stu = studentBiz.findStudentBySid(student.getId());
		
			studentBiz.deleteStudent(temp_stu);
			
			//����ѧ���б�
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
		System.out.println("���뵽��changepwd������");
		Map session = ActionContext.getContext().getSession();
		//�����û�id�ж�ԭ��id�Ƿ���ȷ
		try{
		Student stu = studentBiz.findStudentBySid(student.getId());
		if(stu.getLoginpass().equals(beforepwd)){
			stu.setLoginpass(student.getLoginpass());
			studentBiz.updateStudent(stu);
			session.put("ts", "<script>alert('�޸�����ɹ�');</script>");
			return "changepwd_success";
		}else{
			session.put("ts", "<script>alert('�޸�ʧ�ܣ����Ժ�����');</script>");
			return "changepwd_error";
		}
		}catch(Exception ex){
			ex.printStackTrace();
			return "exception";
		}
	}
	
	public String update(){
		try{
			System.out.println("���뵽��update��������");
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
			
			System.out.println("���뵽��add��������,��");
			System.out.println(student.getLoginname());
			String loginname = student.getLoginname();
			String loginpwd = student.getLoginpass();
			String email = student.getEmail();
			String phone = student.getPhone();
			String uname = student.getUname();
			
			//�����ж��û��� �͵�¼���Ƿ��ظ�
			Student temp_student1  = studentBiz.findStudentByLoginName(loginname);
			Student temp_student2 = studentBiz.findStudentByUName(uname);
			String info = "";
			Student temp_student = new Student();
			System.out.println("ִ�����˴�������");
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
			//Ŀǰд���Ĳ���
			temp_student.setStatus("1");
			temp_student.setType("1");
			System.out.println("ִ���������û���ֵ");
			//�ļ��ϴ�����
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
			
			System.out.println("ִ�������ļ��ϴ�");
			studentBiz.addStudent(temp_student);
			}catch(Exception ex){
				ex.printStackTrace();
				return "exception";
			}
		return "add_success";
	}
	
	public String adminadd(){
		try{
			
			System.out.println("���뵽��add��������,222");
			System.out.println(student.getLoginname());
			String loginname = student.getLoginname();
			String loginpwd = student.getLoginpass();
			String email = student.getEmail();
			String phone = student.getPhone();
			String uname = student.getUname();
			Student temp_student = new Student();
			System.out.println("ִ�����˴�������");
		
			System.out.println(uname);
			
			//�ж��û���ʵ�����Ƿ��ظ�
			
			
				
		    	//ִ�����Ӳ���
				temp_student.setLoginname(student.getLoginname());
				temp_student.setLoginpass(loginpwd);
				temp_student.setEmail(email);
				temp_student.setPhone(phone);
				temp_student.setUname(uname);
				
				//Ŀǰд���Ĳ���
				temp_student.setStatus("1");
				temp_student.setType("1");
				System.out.println("ִ���������û���ֵ");
				//�ļ��ϴ�����
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
				
				System.out.println("ִ�������ļ��ϴ�");
				studentBiz.addStudent(temp_student);
				
				//���²���
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
			System.out.println("���뵽��AJAX������");
			HttpServletRequest req= ServletActionContext.getRequest();
			HttpServletResponse resp = ServletActionContext.getResponse();
			req.setCharacterEncoding("utf-8");
			String loginname = student.getLoginname();
			String uname = student.getUname();
			Student temp_student1  = studentBiz.findStudentByLoginName(loginname);
			Student temp_student2 = studentBiz.findStudentByUName(uname);
			if(temp_student2!=null&&temp_student1!=null){
				String info = "����";
				resp.setCharacterEncoding("utf-8");
				PrintWriter out=resp.getWriter();
				out.print(info);
		    	out.flush();
		    	out.close();
		    	return "error";
			}
			
			if(temp_student2!=null){
				String info = "�������Ѿ����ڣ���������д";
				System.out.println("���뵽��Ϊ�յķ���");
				resp.setCharacterEncoding("utf-8");
				PrintWriter out=resp.getWriter();
				out.print(info);
		    	out.flush();
		    	out.close();
		    	return "error";
			}
			else if(temp_student1!=null){
				String info = "�û����ظ�����������д";
				resp.setCharacterEncoding("utf-8");
				PrintWriter out=resp.getWriter();
				out.print(info);
		    	out.flush();
		    	out.close();
		    	return "error";
			}
			else {
				String info = "�ɹ�";
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
			System.out.println("���뵽��AJAX������");
			HttpServletRequest req= ServletActionContext.getRequest();
			HttpServletResponse resp = ServletActionContext.getResponse();
			req.setCharacterEncoding("utf-8");
			String loginname = student.getLoginname();
			String uname = student.getUname();
			Student temp_student1  = studentBiz.findStudentByLoginName(loginname);
			Student temp_student2 = studentBiz.findStudentByUName(uname);
			if(temp_student2!=null&&temp_student1!=null){
				String info = "����";
				resp.setContentType("text/html;charset=UTF-8"); 
				PrintWriter out=resp.getWriter();
				out.print(info);
		    	out.flush();
		    	out.close();
		    	return "register_error";
			}
			
			if(temp_student2!=null){
				String info = "�������Ѿ����ڣ���������д";
	
				resp.setContentType("text/html;charset=UTF-8"); 
				PrintWriter out=resp.getWriter();
				out.print(info);
		    	out.flush();
		    	out.close();
		    	return "register_error";
			}
			else if(temp_student1!=null){
				String info = "�û����ظ�����������д";
				resp.setContentType("text/html;charset=UTF-8"); 
				PrintWriter out=resp.getWriter();
				out.print(info);
		    	out.flush();
		    	out.close();
		    	return "register_error";
			}
			else {
				String info = "�ɹ�";
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
		System.out.println("���뵽�˷�ҳ����ѧ���ķ���");
		
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
		
		//��һҳ��һҳ������
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
				String info = "������ȷ";
				System.out.println("���뵽��Ϊ�յķ���");
				resp.setContentType("text/html;charset=UTF-8"); 
				PrintWriter out=resp.getWriter();
				out.print(info);
		    	out.flush();
		    	out.close();
		    	return "error";
			}else{
				String info = "�������";
				System.out.println("���뵽��Ϊ�յķ���");
				resp.setContentType("text/html;charset=UTF-8"); 
				PrintWriter out=resp.getWriter();
				out.print(info);
		    	out.flush();
		    	out.close();
		    	return "error";
			}
			//ˢ������
		}catch(Exception e){
			e.printStackTrace();
			return "exception";
		}
		
	}
	
	public String yzmRegister(){
		try{
			System.out.println("���뵽��ע����֤��");
			Map<String,Object> session = ActionContext.getContext().getSession();
			String rand=(String) session.get("random");
			
			HttpServletRequest req= ServletActionContext.getRequest();
			HttpServletResponse resp = ServletActionContext.getResponse();
			req.setCharacterEncoding("utf-8");
			
			if(rand.equals(yzm)){
				String info = "������ȷ";
				resp.setContentType("text/html;charset=UTF-8"); 
				PrintWriter out=resp.getWriter();
				out.print(info);
		    	out.flush();
		    	out.close();
		    	return "error";
			}else{
				String info = "�������";
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
				String info = "��ȷ";
				resp.setContentType("text/html;charset=UTF-8"); 
				PrintWriter out=resp.getWriter();
				out.print(info);
		    	out.flush();
		    	out.close();
		    	return "error";
			}else{
				String info = "����";
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