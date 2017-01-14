package com.exam.biz.impl;

import java.util.List;

import com.exam.biz.StudentBiz;
import com.exam.dao.StudentDao;
import com.exam.entity.Student;

public class StudentBizImpl implements StudentBiz{
	private StudentDao studentDao;
	@Override
	public void addStudent(Student student) throws Exception {
		// TODO Auto-generated method stub
		studentDao.addStudent(student);
	}

	@Override
	public Student login(String loginName, String loginPwd) throws Exception {
		// TODO Auto-generated method stub
		return	studentDao.login(loginName, loginPwd);
	}
	
	@Override
	public Student findStudentBySid(Integer sid) throws Exception {
		// TODO Auto-generated method stub
		return studentDao.findStudentBySid(sid);
	}

	@Override
	public void updateStudent(Student stu) throws Exception {
		// TODO Auto-generated method stub
		studentDao.updateStudent(stu);
	}

	@Override
	public void deleteStudent(Student stu) throws Exception {
		// TODO Auto-generated method stub
		studentDao.deleteStudent(stu);
	}
	
	public StudentDao getStudentDao() {
		return studentDao;
	}
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	@Override
	public List<Student> findStudents() throws Exception {
		// TODO Auto-generated method stub
		return studentDao.findStudents();
	}

	@Override
	public int findPage() throws Exception {
		// TODO Auto-generated method stub
		return studentDao.findPage();
	}

	@Override
	public List<Student> findStudentByPage(Integer nowpage) throws Exception {
		// TODO Auto-generated method stub
		return studentDao.findStudentByPage(nowpage);
	}

	@Override
	public int findPageByStu(Student stu) throws Exception {
		// TODO Auto-generated method stub
		return studentDao.findPageByStu(stu);
	}

	@Override
	public List<Student> findStudentByPageAndStu(Integer nowpage, Student stu)
			throws Exception {
		// TODO Auto-generated method stub
		return studentDao.findStudentByPageAndStu(nowpage, stu);
	}

	@Override
	public Student findStudentByUName(String UName) throws Exception {
		// TODO Auto-generated method stub
		return studentDao.findStudentByUName(UName);
	}

	@Override
	public Student findStudentByLoginName(String loginName) throws Exception {
		// TODO Auto-generated method stub
		return studentDao.findStudentByLoginName(loginName);
	}
}
