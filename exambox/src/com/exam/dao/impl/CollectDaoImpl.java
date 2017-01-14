package com.exam.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.exam.dao.CollectDao;
import com.exam.entity.Card;
import com.exam.entity.Collect;
import com.exam.entity.Forum;
import com.exam.entity.Student;



public class CollectDaoImpl extends HibernateDaoSupport implements CollectDao {

	@Override
	public int addCollect(Collect c) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(c);
		return 1;
	}

	@Override
	public Collect findCollectById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		String hql="from Collect c where c.id="+id;
		Query query = this.getSession().createQuery(hql);
		List<Collect> collectList = query.list();
		return collectList.get(0);
	}

	@Override
	public List<Collect> findAllCollectByStu(Student stu) throws Exception {
		// TODO Auto-generated method stub
		String hql="from Collect c where c.student.id="+stu.getId();
		Query query = this.getSession().createQuery(hql);
		List<Collect> collectList = query.list();
		//遍历出来
		for(Collect collect:collectList){
			collect.getQuestion().getId();
			collect.getQuetype().getTname();
			collect.getQuestion().getSubject().getSname();
			
		}
		return collectList;
	}

	@Override
	public int deleteCollectById(Collect c) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(c);
		return 1;
	}

	@Override
	public int findPage() throws Exception {
		// TODO Auto-generated method stub
		String sqlStr="select count(*) from Collect";
		
		Query query = this.getSession().createQuery(sqlStr);
		List<Object> object =query.list();
		int ts =Integer.valueOf(object.get(0).toString());
		int num =0;
		if(ts%2==0)
			num = ts/2;
		else
			num=ts/2+1;
		return num;
	}

	@Override
	public List<Collect> findCollectByPage(Integer nowpage) throws Exception {
		// TODO Auto-generated method stub
		String hql="from Collect";
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult((nowpage-1)*2);
		query.setMaxResults(2);
		List<Collect> collectList = query.list();
		return collectList;
	}

	@Override
	public int findPageByStu(Student stu,Collect collect) throws Exception {
		// TODO Auto-generated method stub
		String sqlStr = "";
		if(collect.getQuestion().getSubject().getId().equals(-1)){
		
		sqlStr="select count(*) from Collect c where c.student.id="+stu.getId();
		}else{
			sqlStr="select count(*) from Collect c where c.student.id="+stu.getId()+" and c.question.subject.id="+collect.getQuestion().getSubject().getId();
		}
		Query query = this.getSession().createQuery(sqlStr);
		List<Object> object =query.list();
		int ts =Integer.valueOf(object.get(0).toString());
		int num =0;
		if(ts%2==0)
			num = ts/2;
		else
			num=ts/2+1;
		return num;
	}

	@Override
	public List<Collect> findCollectByPageAndStu(Integer nowpage, Student stu,Collect collect)
			throws Exception {
		// TODO Auto-generated method stub
		String hql = "";
		if(collect.getQuestion().getSubject().getId().equals(-1)){
			 hql="from Collect c where c.student.id="+stu.getId();
		}else{
			 hql="from Collect c where c.student.id="+stu.getId()+" and c.question.subject.id="+collect.getQuestion().getSubject().getId();
		}
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult((nowpage-1)*2);
		query.setMaxResults(2);
		List<Collect> collectList = query.list();
		return collectList;
	}

	@Override
	public List<Integer> findCollectQnoByStu(Student stu) throws Exception {
		// TODO Auto-generated method stub
		List<Collect> collectList = findAllCollectByStu(stu);
		
		List<Integer> collectQno = new ArrayList<Integer>();
		
		//遍历出来
		for(Collect collect:collectList){
			collectQno.add(collect.getQuestion().getId());	
		}	
		return collectQno;
	}
}
