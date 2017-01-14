package com.exam.biz.impl;

import java.util.List;

import com.exam.biz.CardBiz;
import com.exam.biz.NewBiz;
import com.exam.dao.CardDao;
import com.exam.dao.NewDao;
import com.exam.entity.Card;
import com.exam.entity.New;


public class CardBizImpl implements CardBiz {
	private CardDao carddao;
	@Override
	public int addCard(Card c) throws Exception {
		// TODO Auto-generated method stub
		return carddao.addCard(c);
	}

	@Override
	public List<Card> findCardByGid(Integer gid) throws Exception {
		// TODO Auto-generated method stub
		return carddao.findCardByGid(gid);
	}

	@Override
	public Card findCardById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return carddao.findCardById(id);
	}

	@Override
	public int updateCard(Card c) throws Exception {
		// TODO Auto-generated method stub
		return carddao.updateCard(c);
	}

	public CardDao getCarddao() {
		return carddao;
	}

	public void setCarddao(CardDao carddao) {
		this.carddao = carddao;
	}

	@Override
	public Card findCardByGidAndQno(Integer gid, Integer qno) throws Exception {
		// TODO Auto-generated method stub
		return carddao.findCardByGidAndQno(gid, qno);
	}


	

}
