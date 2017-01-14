package com.exam.dao;

import java.util.List;

import com.exam.entity.Collect;
import com.exam.entity.Student;




public interface CollectDao {
	public int addCollect(Collect c)throws Exception;
	public Collect findCollectById(Integer id)throws Exception;
	
	public List<Collect> findAllCollectByStu(Student stu)throws Exception;
	public int deleteCollectById(Collect c)throws Exception;
	//分页查询
	public int findPage()throws Exception;
	public List<Collect> findCollectByPage(Integer nowpage)throws Exception;
	//分页查学生的
	public int findPageByStu(Student stu,Collect collect)throws Exception;
	public List<Collect> findCollectByPageAndStu(Integer nowpage,Student stu,Collect collect)throws Exception;
	//查学生收藏的题号
	public List<Integer> findCollectQnoByStu(Student stu)throws Exception;
}
