package com.exam.dao;

import java.util.List;

import com.exam.entity.Error;
import com.exam.entity.Student;
import com.exam.entity.Subject;




public interface ErrorDao {
	public int addError(Error error)throws Exception;
	public Error findErrorById(Integer id)throws Exception;
	
	public List<Error> findAllErrorByStu(Student stu)throws Exception;
	public int deleteErrorById(Error e)throws Exception;
	//判断学生是否有过
	public Error findErrorByQidAndStu(Integer qid,Student stu)throws Exception;
	//分页查询
	public int findPage()throws Exception;
	public List<Error> findErrorByPage(Integer nowpage)throws Exception;
	//分页查学生的
	public int findPageByStu(Student stu,Error error)throws Exception;
	public List<Error> findErrorByPageAndStu(Integer nowpage,Student stu,Error error)throws Exception;
	//根据科目id和学生用户查询错题
	public List<Error> findErrorBySubidAndUser(Integer id,Student stu) throws Exception;
	public List<Integer> findErrorBySub(Integer sid)throws Exception;
}
