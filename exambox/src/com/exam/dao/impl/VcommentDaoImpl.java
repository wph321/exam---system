package com.exam.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.exam.dao.VcommentDao;
import com.exam.entity.Vcomment;

public class VcommentDaoImpl extends HibernateDaoSupport implements VcommentDao {

	@Override
	public int addVcomment(Vcomment comment) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(comment);
		return 1;
	}

	@Override
	public int deleteVcomment(Vcomment comment) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(comment);
		return 1;
	}

}
