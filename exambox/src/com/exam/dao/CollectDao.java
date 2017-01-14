package com.exam.dao;

import java.util.List;

import com.exam.entity.Collect;
import com.exam.entity.Student;




public interface CollectDao {
	public int addCollect(Collect c)throws Exception;
	public Collect findCollectById(Integer id)throws Exception;
	
	public List<Collect> findAllCollectByStu(Student stu)throws Exception;
	public int deleteCollectById(Collect c)throws Exception;
	//��ҳ��ѯ
	public int findPage()throws Exception;
	public List<Collect> findCollectByPage(Integer nowpage)throws Exception;
	//��ҳ��ѧ����
	public int findPageByStu(Student stu,Collect collect)throws Exception;
	public List<Collect> findCollectByPageAndStu(Integer nowpage,Student stu,Collect collect)throws Exception;
	//��ѧ���ղص����
	public List<Integer> findCollectQnoByStu(Student stu)throws Exception;
}
