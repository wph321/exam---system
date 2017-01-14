package com.exam.action;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.exam.biz.SignBiz;
import com.exam.biz.StudentBiz;
import com.exam.entity.Sign;
import com.exam.entity.Student;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SignAction extends ActionSupport {
	private SignBiz signBiz;
	private StudentBiz studentBiz;
	public String add(){
		try{
		System.out.println("���뵽��ǩ��������");
		HttpServletRequest req= ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		req.setCharacterEncoding("utf-8");
		Map session = ActionContext.getContext().getSession();
		Student temp_student = (Student) session.get("user");
		
		//�����û���������һ���ж��Ƿ��Ѿ�ǩ��
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date  = new Date();
		String str = sdf.format(date);
		DateFormat  fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date temp_date  = fmt.parse(str);
		
		
		System.out.println(str);
		Sign temp_sign2 = signBiz.findByDateAndStudent(temp_student, str);
		//����ֵ������
		if(temp_sign2!=null){
			String info = "�Ѿ�ǩ��";
			resp.setContentType("text/html;charset=UTF-8"); 
			PrintWriter out=resp.getWriter();
			out.print(info);
	    	out.flush();
	    	out.close();
	    	return "sign_error";
		}else{
			Sign temp_sign = new Sign();
			temp_sign.setStudent(temp_student);
			temp_sign.setTime(temp_date);
			
			
			if(temp_student.getScore()==null){
				temp_student.setScore(10);
			}else{
				temp_student.setScore(temp_student.getScore()+10);
			}
			signBiz.addSign(temp_sign);
			studentBiz.updateStudent(temp_student);
			
			String info  = "ǩ���ɹ�";
			resp.setContentType("text/html;charset=UTF-8"); 
			PrintWriter out=resp.getWriter();
			out.print(info);
	    	out.flush();
	    	out.close();
	    	return "sign_error";
		}
		
		}catch(Exception ex){
			ex.printStackTrace();
			return "exception";
		}
		
	}
	public SignBiz getSignBiz() {
		return signBiz;
	}
	public void setSignBiz(SignBiz signBiz) {
		this.signBiz = signBiz;
	}
	public StudentBiz getStudentBiz() {
		return studentBiz;
	}
	public void setStudentBiz(StudentBiz studentBiz) {
		this.studentBiz = studentBiz;
	}

	
}
