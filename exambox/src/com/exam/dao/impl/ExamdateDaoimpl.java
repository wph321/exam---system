package com.exam.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.exam.dao.ExamdateDao;
import com.exam.entity.Examdate;
import com.exam.entity.Point;
import com.exam.entity.Subject;

public class ExamdateDaoimpl extends HibernateDaoSupport implements ExamdateDao {

	@Override
	public int addDate(Examdate examdate) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(examdate);
		return 1;
	}

	@Override
	public int deleteDate(Examdate examdate) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(examdate);
		return 1;
	}

	@Override
	public int updateDate(Examdate examdate) throws Exception {
		// TODO Auto-generated method stub
		Session session =this.getSession();
		Examdate examd=(Examdate)session.get(Examdate.class, examdate.getId());
		if(examdate.getEdate()!=null&&!"".equals(examdate.getEdate()))
			examd.setEdate(examdate.getEdate()); 
		if(examdate.getSubject()!=null&&!"".equals(examdate.getSubject()))
			examd.setSubject(examdate.getSubject());
		session.update(examd);
		return 1;
	}

	@Override
	public List<Examdate> findDate() throws Exception {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
		Date date =new Date();
		String nowStr=sdf.format(date);
		System.out.println("================"+nowStr);
		String sql="from Examdate e left join fetch e.subject s left join fetch s.grades where e.edate > to_date('"+nowStr+"','yyyy-MM-dd') order by e.edate";
		List<Examdate> examdateList=(List<Examdate>)this.getHibernateTemplate().find(sql);
		return examdateList;
	}

	@Override
	public Examdate findoneDate(Integer did) throws Exception {
		// TODO Auto-generated method stub
		String sql="from Examdate em where em.id="+did;
		List<Examdate> examdateList=(List<Examdate>)this.getHibernateTemplate().find(sql);
		if(examdateList.size()>0){
			return examdateList.get(0);
		}
		return null;
	}

	@Override
	public int findPageBySubject(Subject subject) throws Exception {
		// TODO Auto-generated method stub
		String sql="select count(*) from Examdate em where em.subject.id="+subject.getId();
		Query query=this.getSession().createQuery(sql);
		Object rowsObj=query.uniqueResult();
		Integer rows=Integer.parseInt(rowsObj.toString());
		if(rows%6==0)
			 return rows/6;
		else
			 return rows/6+1;
		
	}

	@Override
	public List<Examdate> findDateByPageAndSubject(Integer nowpage,Subject subject) throws Exception {
		// TODO Auto-generated method stub
		String sql="from Examdate em where em.subject.id="+subject.getId();
		Query query = this.getSession().createQuery(sql);
		query.setFirstResult((nowpage-1)*6);
		query.setMaxResults(6);
		List<Examdate> examdateList = query.list();
		return examdateList;
		
	}

	@Override
	public int findPages() throws Exception {
		// TODO Auto-generated method stub
		String sql="select count(*) from Examdate ";
		Query query=this.getSession().createQuery(sql);
		Object rowsObj=query.uniqueResult();
		Integer rows=Integer.parseInt(rowsObj.toString());
		if(rows%6==0)
			 return rows/6;
		else
			 return rows/6+1;
		
	}

	@Override
	public List<Examdate> findByPage(Integer nowpage) throws Exception {
		// TODO Auto-generated method stub
		String sql="from Examdate em left join fetch em.subject";
		Query query = this.getSession().createQuery(sql);
		query.setFirstResult((nowpage-1)*6);
		query.setMaxResults(6);
		List<Examdate> examdateList = query.list();
		return examdateList;
	}

}
