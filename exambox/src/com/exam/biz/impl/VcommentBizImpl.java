package com.exam.biz.impl;

import com.exam.biz.VcommentBiz;
import com.exam.dao.VcommentDao;
import com.exam.entity.Vcomment;

public class VcommentBizImpl implements VcommentBiz {
   private VcommentDao vcommentDao;
	@Override
	public int addVcomment(Vcomment comment) throws Exception {
		// TODO Auto-generated method stub
		return vcommentDao.addVcomment(comment);
	}

	@Override
	public int deleteVcomment(Vcomment comment) throws Exception {
		// TODO Auto-generated method stub
		return vcommentDao.deleteVcomment(comment);
	}

	public VcommentDao getVcommentDao() {
		return vcommentDao;
	}

	public void setVcommentDao(VcommentDao vcommentDao) {
		this.vcommentDao = vcommentDao;
	}

}
