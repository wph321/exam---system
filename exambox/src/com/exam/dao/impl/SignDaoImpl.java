package com.exam.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.exam.dao.SignDao;
import com.exam.entity.Sign;
import com.exam.entity.Student;

public class SignDaoImpl extends HibernateDaoSupport implements SignDao {

	@Override
	public void addSign(Sign sign) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(sign);
	}

	@Override
	public Sign findByDateAndStudent(Student stu, String datestr) throws Exception {
		// TODO Auto-generated method stub
		String hqlStr = "from Sign where student.id="+stu.getId()+"and time = to_date('"+datestr+"','yyyy-MM-dd') ";
		List<Sign> signList= (List<Sign>) this.getHibernateTemplate().find(hqlStr);
		if(signList.size()!=0){
			return signList.get(0);
		}
		return null;
	}

}
