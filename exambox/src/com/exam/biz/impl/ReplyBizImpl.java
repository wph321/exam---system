package com.exam.biz.impl;

import com.exam.biz.ReplyBiz;
import com.exam.dao.ReplyDao;
import com.exam.entity.Reply;


public class ReplyBizImpl implements ReplyBiz {
	private ReplyDao replydao;
	@Override
	public int addReply(Reply r) throws Exception {
		// TODO Auto-generated method stub
		return replydao.addReply(r);
	}

	@Override
	public Reply findReplyByCid(Integer cid) throws Exception {
		// TODO Auto-generated method stub
		return replydao.findReplyByCid(cid);
	}

	@Override
	public Reply findReplyByComid(Integer comid) throws Exception {
		// TODO Auto-generated method stub
		return replydao.findReplyByComid(comid);
	}

	@Override
	public int deleteReplyByrid(Reply r) throws Exception {
		// TODO Auto-generated method stub
		return replydao.deleteReplyByrid(r);
	}

	public ReplyDao getReplydao() {
		return replydao;
	}

	public void setReplydao(ReplyDao replydao) {
		this.replydao = replydao;
	}

}
