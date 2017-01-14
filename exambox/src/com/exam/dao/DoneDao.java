package com.exam.dao;

import java.util.List;

import com.exam.entity.Admin;
import com.exam.entity.Done;
import com.exam.entity.Point;
import com.exam.entity.Student;
import com.exam.entity.Subject;



public interface DoneDao {
	public void addDone(Done d) throws Exception;
	public void deleteDone(Done d) throws Exception;
	public List<Done> findDoneByStuAndSub(Student stu,Subject sub)throws Exception;
	public List<Done> findDoneByStuAndPoint(Student stu,Point p)throws Exception;
	public Done findDoneByQid(Integer qid)throws Exception;
}
