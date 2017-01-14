package com.exam.dao;

import java.util.List;

import com.exam.entity.Subject;

public interface SubjectDao {
   //添加考试科目
	public int addSubject(Subject suject)throws Exception;
  //删除考试科目
	public int deleteSubject(Subject subject)throws Exception;
  //修该考试科目
	public int updateSubject(Subject subject) throws Exception;
  //查询所有的考试科目
	public List<Subject> findSubject()throws Exception;
  //查询单个考试科目
	public Subject findone(Integer sid)throws Exception;
  //分页查询所有的科目
	public List<Subject> findByPage(Integer nowpage)throws Exception;
  //查询总页数
	public int findPages()throws Exception;
}
