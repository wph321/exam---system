package com.exam.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.exam.dao.NewDao;
import com.exam.entity.New;



public class NewDaoImpl extends HibernateDaoSupport implements NewDao {

	@Override
	public int addNew(New n) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(n);
		return 1;
	}

	@Override
	public New findNewByNid(New n) throws Exception {
		// TODO Auto-generated method stub
		String hql="from New n where n.nid="+n.getNid();
		Query query = this.getSession().createQuery(hql);
		List<New> nlist = query.list();
		return nlist.get(0);
	}

	@Override
	public List<New> findAllNew() throws Exception {
		// TODO Auto-generated method stub
		String hql="from New";
		Query query = this.getSession().createQuery(hql);
		List<New> nlist = query.list();
		return nlist;
	}

	@Override
	public void updateNew(New n) throws Exception {
		// TODO Auto-generated method stub
		Session session=this.getSession();
		New nn=	(New)session.get(New.class, n.getNid());
		if(n.getNimage()!=null&&!"".equals(n.getNimage()))
			nn.setNimage(n.getNimage());
		if(n.getNcontent()!=null&&!"".equals(n.getNcontent()))
			nn.setNcontent(n.getNcontent());
		if(n.getNdate()!=null&&!"".equals(n.getNdate()))
			nn.setNdate(n.getNdate());
		if(n.getNtitle()!=null&&!"".equals(n.getNtitle()))
			nn.setNtitle(n.getNtitle());
		session.update(nn);
//		this.getHibernateTemplate().update(n);
	}

	@Override
	public int deleteNewByNid(New n) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(n);
		return 1;
	}

	@Override
	public int findPage(New n) throws Exception {
		// TODO Auto-generated method stub
		String sqlStr="select count(*) from New n  where 1=1";
		if(n!=null){
			if(n.getNcontent()!=null&&!"".equals(n.getNcontent()))
				sqlStr+=" and (n.ncontent like '%"+n.getNcontent()+"%' or n.ntitle like '%"+n.getNcontent()+"%')";
		}
		Query query = this.getSession().createQuery(sqlStr);
		List<Object> object =query.list();
		int ts =Integer.valueOf(object.get(0).toString());
		int num =0;
		if(ts%8==0)
			num = ts/8;
		else
			num=ts/8+1;
		return num;
	}

	@Override
	public List<New> findNewByTermAndPage(New n, Integer page) {
		// TODO Auto-generated method stub
		String hql="from New n  where 1=1";
		if(n!=null){
			if(n.getNcontent()!=null&&!"".equals(n.getNcontent()))
				hql+=" and (n.ncontent like '%"+n.getNcontent()+"%' or n.ntitle like '%"+n.getNcontent()+"%')";
		}
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult((page-1)*8);
		query.setMaxResults(8);
		List<New> nlist = query.list();
		return nlist;
	}

	@Override
	public New findNewById(Integer n) throws Exception {
		// TODO Auto-generated method stub
		New news = this.getHibernateTemplate().load(New.class, n);
		return news;
	}

}
