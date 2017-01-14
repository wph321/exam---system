package com.exam.biz;

import java.util.List;

import com.exam.entity.Reply;


public interface ReplyBiz {
	public int addReply(Reply r)throws Exception;
	public Reply findReplyByCid(Integer cid)throws Exception;
	public Reply findReplyByComid(Integer comid)throws Exception;
	public int deleteReplyByrid(Reply r)throws Exception;
	
}
