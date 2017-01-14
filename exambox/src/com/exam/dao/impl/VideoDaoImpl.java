package com.exam.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.exam.dao.VideoDao;
import com.exam.entity.Vedio;

public class VideoDaoImpl extends HibernateDaoSupport implements VideoDao {

	@Override
	public List<Vedio> findVideoByTypeId(Integer id) throws Exception {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		String hql="from Vedio as v where v.subject.id="+id;
		Query query = session.createQuery(hql);
		List<Vedio> vedioList = query.list();
		return vedioList;
	}

	@Override
	public Vedio findVideoByVideoId(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().load(Vedio.class, id);
	}

	@Override
	public void addVideo(Vedio vedio) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(vedio);
	}

	@Override
	public void deleteVedioById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		Vedio vedio = (Vedio) session.get(Vedio.class, id);
		this.getHibernateTemplate().delete(vedio);
//		session.delete(vedio);
	}

	@Override
	public void updatevedio(Vedio vedio) throws Exception {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		session.update(vedio);
		session.beginTransaction().commit();
		//		this.getHibernateTemplate().update(vedio);
	}

	@Override
	public List<Vedio> findVedioByPage(Integer page) throws Exception {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();
		String hql = "from Vedio as v";
		Query query = this.getSession().createQuery(hql);
		List<Object> object = query.list();
		query.setFirstResult((page-1)*8);
		query.setMaxResults(8);
		List<Vedio> vedioList = query.list();
		return vedioList;
	}

	@Override
	public List<Vedio> findVedioByPageAndType(Integer page, Integer id)
			throws Exception {
		// TODO Auto-generated method stub
		String hql = "from Vedio as v left join fetch v.admin where 1=1";
		if(id!=null&&id!=0){
			hql += " and v.subject.id="+id;
		}
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult((page-1)*8);
		query.setMaxResults(8);
		List<Vedio> vedioList = query.list();
		return vedioList;
	}

	@Override
	public Integer findAllPage() throws Exception {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Vedio as v";
		Query query = this.getSession().createQuery(hql);
		List<Object> object = query.list();
		int num = Integer.valueOf(object.get(0).toString());
		if(num % 8==0){
		int allPage = num/8;
		return allPage;
		}else{
		int	allPage = num/8+1;
		return allPage;
		}
	}

	@Override
	public Integer findAllpageByType(Integer id) throws Exception {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Vedio as v where 1=1";
		if(id!=null&&id!=0){
			hql += " and v.subject.id="+id;
		}
		Query query = this.getSession().createQuery(hql);
		List<Object> object = query.list();
		int num = Integer.parseInt(object.get(0).toString());
		if(num % 8==0){
		int allPage = num/8;
		return allPage;
		}else{
		int	allPage = num/8+1;
		return allPage;
		}
	}

}
