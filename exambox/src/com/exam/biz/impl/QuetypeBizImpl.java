package com.exam.biz.impl;

import com.exam.biz.QuetypeBiz;
import com.exam.dao.QuetypeDao;
import com.exam.entity.Quetype;

public class QuetypeBizImpl implements QuetypeBiz {
	private QuetypeDao quetypeDao;
	@Override
	public Quetype findByid(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return quetypeDao.findByid(id);
	}
	public QuetypeDao getQuetypeDao() {
		return quetypeDao;
	}
	public void setQuetypeDao(QuetypeDao quetypeDao) {
		this.quetypeDao = quetypeDao;
	}

}
