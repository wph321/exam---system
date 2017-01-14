package com.exam.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.exam.dao.SubjectDao;
import com.exam.entity.Collect;
import com.exam.entity.Subject;

public class SubjectDaoimpl extends HibernateDaoSupport implements SubjectDao {

	@Override
	public int addSubject(Subject suject) throws Exception {
		// TODO Auto-generated method stub
		 this.getHibernateTemplate().save(suject);
		 return 1;
	}

	@Override
	public int deleteSubject(Subject subject) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("进入Dao方法");
		this.getHibernateTemplate().delete(subject);
		return 1;
	}

	@Override
	public int updateSubject(Subject subject) throws Exception {
		// TODO Auto-generated method stub
		Subject sub=(Subject)this.getHibernateTemplate().get(Subject.class, subject.getId());
		if(subject.getSname()!=null&&!"".equals(subject.getSname()))
			sub.setSname(subject.getSname());
		if(subject.getSdetail()!=null&&!"".equals(subject.getSdetail()))
			sub.setSdetail(subject.getSdetail());
		this.getHibernateTemplate().update(sub);
		return 1;
	}

	@Override
	public List<Subject> findSubject() throws Exception {
		// TODO Auto-generated method stub
		String sql="from Subject";
		List<Subject> subjectList=(List<Subject>)this.getHibernateTemplate().find(sql);
		return subjectList;
	}

	@Override
	public Subject findone(Integer sid) throws Exception {
		// TODO Auto-generated method stub
		String sql="from Subject s left join fetch s.questions left join fetch s.points where s.id="+sid;
		List<Subject> subjectList=(List<Subject>)this.getHibernateTemplate().find(sql);
		if(subjectList.size()>0){
		return subjectList.get(0);
		}
		return null;
	}

	@Override
	public List<Subject> findByPage(Integer nowpage) throws Exception {
		// TODO Auto-generated method stub
		String sql="from Subject";
		Query query = this.getSession().createQuery(sql);
		query.setFirstResult((nowpage-1)*6);
		query.setMaxResults(6);
		List<Subject> subjectList = query.list();
		return subjectList;
	}

	@Override
	public int findPages() throws Exception {
		// TODO Auto-generated method stub
		String sql="select count(*) from Subject";
		Query query = this.getSession().createQuery(sql);
		Object rowsObj=query.uniqueResult();
		Integer rows=Integer.parseInt(rowsObj.toString());
		if(rows%6==0)
			 return rows/6;
		else
			 return rows/6+1;
		
	}

}
