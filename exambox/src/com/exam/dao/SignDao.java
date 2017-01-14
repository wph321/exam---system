package com.exam.dao;

import java.util.Date;

import com.exam.entity.Sign;
import com.exam.entity.Student;

public interface SignDao {
	public void addSign (Sign sign) throws Exception; 
	public Sign findByDateAndStudent(Student stu,String datestr) throws Exception;
}
