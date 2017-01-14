package com.exam.dao;

import java.util.List;


import com.exam.entity.Student;

public interface StudentDao {
	public void addStudent(Student student) throws Exception;
	public Student login(String loginName,String loginPwd) throws Exception;	
	public Student findStudentBySid(Integer sid)throws Exception;
	public Student findStudentByUName(String UName) throws Exception;
	public Student findStudentByLoginName(String loginName) throws Exception;
	public void updateStudent(Student stu) throws Exception;
	
	public void deleteStudent(Student stu) throws Exception;
	public List<Student> findStudents() throws Exception;
	
	//分页
	public int findPage()throws Exception;
	public List<Student> findStudentByPage(Integer nowpage)throws Exception;
	//分页查学生的
	public int findPageByStu(Student stu)throws Exception;
	public List<Student> findStudentByPageAndStu(Integer nowpage,Student stu)throws Exception;
}
