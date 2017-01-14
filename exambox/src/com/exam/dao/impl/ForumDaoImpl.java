package com.exam.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.exam.dao.ForumDao;
import com.exam.entity.Forum;
import com.exam.entity.Student;


public class ForumDaoImpl extends HibernateDaoSupport implements ForumDao {

	@Override
	public int addForum(Forum f) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(f);
		return 1;
	}

	@Override
	public Forum findForumByCid(Integer cid) throws Exception {
		// TODO Auto-generated method stub
		String hql="from Forum f where f.cid="+cid;
		Query query = this.getSession().createQuery(hql);
		List<Forum> flist = query.list();
		return flist.get(0);
	}

	@Override
	public Forum findForumByComid(Integer comid) throws Exception {
		// TODO Auto-generated method stub
		String hql="from Forum f where f.comid="+comid;
		Query query = this.getSession().createQuery(hql);
		List<Forum> flist = query.list();
		return flist.get(0);
	}

	@Override
	public List<Forum> findAllForum() throws Exception {
		// TODO Auto-generated method stub
		String hql="from Forum f";
		Query query = this.getSession().createQuery(hql);
		List<Forum> flist = query.list();
		return flist;
	}

	@Override
	public int deleteForumByFid(Forum f) throws Exception {
		this.getHibernateTemplate().delete(f);
		return 1;
	}

	@Override
	public Forum findForumByFid(Integer fid) throws Exception {
		// TODO Auto-generated method stub
		String hql="from Forum f left join fetch f.replies r where f.fid="+fid;
		Query query = this.getSession().createQuery(hql);
		List<Forum> flist = query.list();
		return flist.get(0);
	}

	@Override
	public int findPage() throws Exception {
		// TODO Auto-generated method stub
		String sqlStr="select count(*) from Forum";
		
		Query query = this.getSession().createQuery(sqlStr);
		List<Object> object =query.list();
		int ts =Integer.valueOf(object.get(0).toString());
		int num =0;
		if(ts%6==0)
			num = ts/6;
		else
			num=ts/6+1;
		return num;
	}

	@Override
	public List<Forum> findForumByPage(Integer nowpage) throws Exception {
		// TODO Auto-generated method stub
		String hql="from Forum f order by f.fdate desc";
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult((nowpage-1)*6);
		query.setMaxResults(6);
		List<Forum> flist = query.list();
		return flist;
	}

	@Override
	public int findPageByTerm(Forum f) throws Exception {
		// TODO Auto-generated method stub
		String sqlStr="select count(*) from Forum f  where 1=1";
		if(f!=null){
			if(f.getFtitle()!=null&&!"".equals(f.getFtitle()))
				sqlStr+=" and (f.ftitle like '%"+f.getFtitle()+"%' or f.fcontent like '%"+f.getFtitle()+"%')";
		}
		Query query = this.getSession().createQuery(sqlStr);
		List<Object> object =query.list();
		int ts =Integer.valueOf(object.get(0).toString());
		int num =0;
		if(ts%6==0)
			num = ts/6;
		else
			num=ts/6+1;
		return num;
	}

	@Override
	public List<Forum> findForumByPageTerm(Forum f, Integer nowpage)
			throws Exception {
		// TODO Auto-generated method stub
		String hql="from Forum f where 1=1";
		if(f!=null){
			if(f.getFtitle()!=null&&!"".equals(f.getFtitle()))
				hql+=" and (f.ftitle like '%"+f.getFtitle()+"%' or f.fcontent like '%"+f.getFtitle()+"%')";
		}
		hql+=" order by f.fdate desc";
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult((nowpage-1)*6);
		query.setMaxResults(6);
		List<Forum> flist = query.list();
		return flist;
	}

	@Override
	public List<Forum> findForumByUserAndPage(Student stu, Integer nowpage)
			throws Exception {
		// TODO Auto-generated method stub
		String sqlStr=" from Forum f where f.student.id="+stu.getId();
		Query query = this.getSession().createQuery(sqlStr);
		query.setFirstResult((nowpage-1)*6);
		query.setMaxResults(6);
		List<Forum> flist = query.list();
		return flist;
	}

	@Override
	public int findPageByUser(Student stu) throws Exception {
		// TODO Auto-generated method stub
		String sqlStr="select count(*) from Forum f where f.student.id="+stu.getId();
			Query query = this.getSession().createQuery(sqlStr);
			List<Object> object =query.list();
			int ts =Integer.valueOf(object.get(0).toString());
			int num =0;
			if(ts%6==0)
				num = ts/6;
			else
				num=ts/6+1;
			return num;
			
	}

}
