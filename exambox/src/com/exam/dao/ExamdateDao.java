package com.exam.dao;

import java.util.List;

import com.exam.entity.Examdate;
import com.exam.entity.Subject;

public interface ExamdateDao {
   //添加考试时间
	public int addDate(Examdate examdate)throws Exception;
   //删除考试时间
	public int deleteDate(Examdate examdate)throws Exception;
   //修改考试时间
   public int updateDate(Examdate examdate)throws Exception;
	//查询考试时间
   public List<Examdate> findDate()throws Exception;
	//查询单个的考试时间
   public Examdate findoneDate(Integer did)throws Exception;
   //查询页数
   public int findPages()throws Exception;
   public List<Examdate> findByPage(Integer nowpage)throws Exception;
   //根据条件查询页数
   public int findPageBySubject(Subject subject)throws Exception;
   public List<Examdate> findDateByPageAndSubject(Integer nowpage,Subject subject)throws Exception;
}
