package com.exam.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;





import com.exam.dao.DoneDao;
import com.exam.entity.Done;
import com.exam.entity.Forum;
import com.exam.entity.Point;
import com.exam.entity.Student;
import com.exam.entity.Subject;



public class DoneDaoImpl extends HibernateDaoSupport implements DoneDao {

	@Override
	public void addDone(Done d) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(d);
	}

	@Override
	public void deleteDone(Done d) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(d);
	}

	@Override
	public List<Done> findDoneByStuAndSub(Student stu, Subject sub)
			throws Exception {
		// TODO Auto-generated method stub
		String hql="from Done d left join fetch d.question q where d.student.id="+stu.getId()+" and q.subject.id="+sub.getId();
		Query query = this.getSession().createQuery(hql);
		List<Done> dlist = query.list();
		return dlist;
	}

	@Override
	public List<Done> findDoneByStuAndPoint(Student stu, Point p)
			throws Exception {
		// TODO Auto-generated method stub
		String hql="from Done d left join fetch d.question q left join fetch q.points p where d.student.id="+stu.getId()+" and p.id="+p.getId();
		Query query = this.getSession().createQuery(hql);
		List<Done> dlist = query.list();
		return dlist;
	}

	@Override
	public Done findDoneByQid(Integer qid) throws Exception {
		// TODO Auto-generated method stub
		String hql="from Done d left join fetch d.question q where q.id="+qid;
		Query query = this.getSession().createQuery(hql);
		List<Done> dlist = query.list();
		if(dlist.size()>0)
			return dlist.get(0);
		return null;
	}

	
}
