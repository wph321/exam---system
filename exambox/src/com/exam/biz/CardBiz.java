package com.exam.biz;

import java.util.List;

import com.exam.entity.Card;
import com.exam.entity.Forum;
import com.exam.entity.Grade;
import com.exam.entity.Student;



public interface CardBiz {
	public int addCard(Card c)throws Exception;
	public List<Card> findCardByGid(Integer gid)throws Exception;
	public Card findCardById(Integer id)throws Exception;
	public int updateCard(Card c)throws Exception;
	public Card findCardByGidAndQno(Integer gid,Integer qno)throws Exception;
}
