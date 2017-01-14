package com.exam.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.exam.dao.PointDao;
import com.exam.entity.Point;
import com.exam.entity.Subject;

public class PointDaoimpl extends HibernateDaoSupport implements PointDao {

	@Override
	public int addPoint(Point point) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(point);
		return 1;
	}

	@Override
	public int deletePoint(Point point) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(point);
		return 1;
	}

	@Override
	public int updatePoint(Point point) throws Exception {
		// TODO Auto-generated method stub
		Point poit=(Point)this.getHibernateTemplate().get(Point.class,point.getId());
		if(point.getPname()!=null&&!"".equals(point.getPname()))
			poit.setPname(point.getPname());
		if(point.getPex()!=null&&!"".equals(point.getPex()))
			poit.setPex(point.getPex());
		this.getHibernateTemplate().update(poit);
		return 1;
	}

	@Override
	public List<Point> findPoint() throws Exception {
		// TODO Auto-generated method stub
		String sql="from Point p left join fetch p.subject";
		List<Point> pointList=(List<Point>)this.getHibernateTemplate().find(sql);
		
		return pointList;
	}

	@Override
	public Point findonePoint(Integer pid) throws Exception {
		// TODO Auto-generated method stub
		String sql="from Point p where p.id="+pid;
		List<Point> pointList=(List<Point>)this.getHibernateTemplate().find(sql);
		if(pointList.size()>0){
			return pointList.get(0);
		}			
		    return null;
	}

	@Override
	public int findPages() throws Exception {
		// TODO Auto-generated method stub
		String sql="select count(*) from Point";
		Query query=this.getSession().createQuery(sql);
		Object rowsObj=query.uniqueResult();
		Integer rows=Integer.parseInt(rowsObj.toString());
		if(rows%6==0)
			 return rows/6;
		else
			 return rows/6+1;
		
	}
	@Override
	public List<Point> findByPage(Integer nowpage) throws Exception {
		// TODO Auto-generated method stub
		String sql="from Point p left join fetch p.subject order by p.id";
		Query query = this.getSession().createQuery(sql);
		query.setFirstResult((nowpage-1)*6);
		query.setMaxResults(6);
		List<Point> pointList = query.list();
		return pointList;
		
	}
	@Override
	public List<Point> findBypageAndSubject(Subject subject, Integer nowpage)
			throws Exception {
		// TODO Auto-generated method stub
		String sql="from Point p where 1=1";		
		if(subject!=null){
		   if(subject.getId()!=-1)	
		    sql+=" and p.subject.id="+subject.getId();
		}
		Query query = this.getSession().createQuery(sql);
		query.setFirstResult((nowpage-1)*6);
		query.setMaxResults(6);
		List<Point> pointList = query.list();
		return pointList;
	}

	@Override
	public int findPagesBySubject(Subject subject) throws Exception {
		// TODO Auto-generated method stub
		String sql="select count(*) from Point p where 1=1";
		if(subject!=null){
			   if(subject.getId()!=-1)	
			    sql+=" and p.subject.id="+subject.getId();
			}
		Query query=this.getSession().createQuery(sql);
		Object rowsObj=query.uniqueResult();
		Integer rows=Integer.parseInt(rowsObj.toString());
		if(rows%6==0)
			 return rows/6;
		else
			 return rows/6+1;
	}
	@Override
	public List<Point> findPointBySid(Integer sid) throws Exception {
		// TODO Auto-generated method stub
		String sql=" from Point p where p.subject.id="+sid;
		List<Point> pointList=(List<Point>)this.getHibernateTemplate().find(sql);
		return pointList;
	}

}
