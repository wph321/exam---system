package com.exam.biz;

import java.util.List;

import com.exam.entity.New;




public interface NewBiz {
	public int addNew(New n)throws Exception;
	public New findNewByNid(New n)throws Exception;
	public New findNewByid(Integer id)throws Exception;
	public List<New> findAllNew()throws Exception;
	public void updateNew(New n) throws Exception;
	public int deleteNewByNid(New n)throws Exception;
	//分页件查(8个一页)
	public int findPage(New n)throws Exception;
	public List<New> findNewByTermAndPage(New n,Integer page);
}	
