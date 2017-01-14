package com.exam.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.exam.dao.ReplyDao;
import com.exam.entity.Reply;


public class ReplyDaoImpl extends HibernateDaoSupport implements ReplyDao {

	@Override
	public int addReply(Reply r) throws Exception {
		this.getHibernateTemplate().save(r);
		return 1;
	}

	@Override
	public Reply findReplyByCid(Integer cid) throws Exception {
		// TODO Auto-generated method stub
		String hql="from Reply r where r.cid="+cid;
		Query query = this.getSession().createQuery(hql);
		List<Reply> rlist = query.list();
		return rlist.get(0);
	}

	@Override
	public Reply findReplyByComid(Integer comid) throws Exception {
		// TODO Auto-generated method stub
		String hql="from Reply r where r.comid="+comid;
		Query query = this.getSession().createQuery(hql);
		List<Reply> rlist = query.list();
		return rlist.get(0);
	}

	@Override
	public int deleteReplyByrid(Reply r) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(r);
		return 1;
	}

	@Override
	public int deleteReplyBfid(Integer fid) throws Exception {
		// TODO Auto-generated method stub
		String hql="from Reply r where r.forum.fid="+fid;
		Query query = this.getSession().createQuery(hql);
		List<Reply> rlist = query.list();
		this.getHibernateTemplate().deleteAll(rlist);
		return 1;
	}

}
