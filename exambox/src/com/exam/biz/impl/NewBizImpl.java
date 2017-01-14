package com.exam.biz.impl;

import java.util.List;

import com.exam.biz.NewBiz;
import com.exam.dao.NewDao;
import com.exam.entity.New;


public class NewBizImpl implements NewBiz {
	private NewDao newdao;
	@Override
	public int addNew(New n) throws Exception {
		// TODO Auto-generated method stub
		return newdao.addNew(n);
	}

	@Override
	public New findNewByNid(New n) throws Exception {
		// TODO Auto-generated method stub
		return newdao.findNewByNid(n);
	}

	@Override
	public List<New> findAllNew() throws Exception {
		// TODO Auto-generated method stub
		return newdao.findAllNew();
	}

	@Override
	public void updateNew(New n) throws Exception {
		// TODO Auto-generated method stub
		newdao.updateNew(n);
	}

	@Override
	public int deleteNewByNid(New n) throws Exception {
		// TODO Auto-generated method stub
		return newdao.deleteNewByNid(n);
	}

	public NewDao getNewdao() {
		return newdao;
	}

	public void setNewdao(NewDao newdao) {
		this.newdao = newdao;
	}

	@Override
	public int findPage(New n) throws Exception {
		// TODO Auto-generated method stub
		return newdao.findPage(n);
	}

	@Override
	public List<New> findNewByTermAndPage(New n, Integer page) {
		// TODO Auto-generated method stub
		return newdao.findNewByTermAndPage(n, page);
	}

	@Override
	public New findNewByid(Integer id) throws Exception {
		// TODO Auto-generated method stub
		
		return newdao.findNewById(id);
	}

}
