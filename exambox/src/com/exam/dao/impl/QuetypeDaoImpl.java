package com.exam.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.exam.dao.QuetypeDao;
import com.exam.entity.Quetype;

public class QuetypeDaoImpl extends HibernateDaoSupport implements QuetypeDao {

	@Override
	public Quetype findByid(Integer id) throws Exception {
		// TODO Auto-generated method stub
		String hqlStr = "from Quetype where id = "+id;
		List<Quetype> quetypeList  =(List<Quetype>) this.getHibernateTemplate().find(hqlStr);
		if(quetypeList.size()!=0){
			return  quetypeList.get(0);
		}
		return null;
	}

}
