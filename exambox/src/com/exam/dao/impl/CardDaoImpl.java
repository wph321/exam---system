package com.exam.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.exam.dao.CardDao;
import com.exam.entity.Card;
import com.exam.entity.Forum;





public class CardDaoImpl extends HibernateDaoSupport implements CardDao {

	@Override
	public int addCard(Card c) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(c);
		return 1;
	}

	@Override
	public List<Card> findCardByGid(Integer gid) throws Exception {
		// TODO Auto-generated method stub
		String hql="from Card c left join fetch c.grade where c.grade.id="+gid+"order by qno";
		Query query = this.getSession().createQuery(hql);
		List<Card> cardList = query.list();
		return cardList;
	}

	@Override
	public Card findCardById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		String hql="from Card c left join fetch c.question q left join fetch q.quetype left join fetch c.grade where c.id="+id;
		Query query = this.getSession().createQuery(hql);
		List<Card> cardList = query.list();
		return cardList.get(0);
	}

	@Override
	public int updateCard(Card c) throws Exception {
		// TODO Auto-generated method stub
//		Session session=this.getSession();
//		Card card=	(Card)session.get(Card.class, c.getId());
//		if(c.getUanswer()!=null&&!"".equals(c.getUanswer()))
//			card.setUanswer(c.getUanswer());
//		if(c.getScore()!=null&&!"".equals(c.getScore()))
//			card.setScore(c.getScore());
//		session.update(card);
		this.getHibernateTemplate().update(c);
		return 1;
	}

	@Override
	public Card findCardByGidAndQno(Integer gid, Integer qno) throws Exception {
		// TODO Auto-generated method stub
		String hql="from Card c  left join fetch c.question q left join fetch q.quetype left join fetch c.grade g where g.id="+gid+" and c.qno="+qno;
		Query query = this.getSession().createQuery(hql);
		List<Card> cardList = query.list();
		return cardList.get(0);
	}

}
