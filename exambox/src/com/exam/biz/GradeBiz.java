package com.exam.biz;

import java.util.List;

import com.exam.entity.Collect;
import com.exam.entity.Grade;
import com.exam.entity.Student;




public interface GradeBiz {
	public int addGrade(Grade g)throws Exception;
	public Grade findGradeById(Integer id)throws Exception;
	public int updateGrade(Grade g)throws Exception;
	public List<Grade> findAllGradeByStu(Student stu)throws Exception;
	public int deleteGradeById(Grade grade)throws Exception;
	public List<Grade> findGradeBySubId(Integer subid) throws Exception ;
	public List<Grade> findGrades() throws Exception;
	
	public List<Grade> findGradeBySubIdAndStudent(Integer subid, Student stu)
			throws Exception ;
	//分页查询
	public int findPage()throws Exception;
	public List<Grade> findGradeByPage(Integer nowpage)throws Exception;
	//分页查学生的
	public int findPageByStu(Student stu,Grade grade)throws Exception;
	public List<Grade> findGradeByPageAndStu(Integer nowpage,Student stu,Grade grade)throws Exception;
}
