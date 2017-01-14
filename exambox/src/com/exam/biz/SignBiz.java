package com.exam.biz;

import java.util.Date;

import com.exam.entity.Sign;
import com.exam.entity.Student;

public interface SignBiz {
	public void addSign(Sign sign) throws Exception;
	public Sign findByDateAndStudent(Student stu,String datestr) throws Exception;
}
