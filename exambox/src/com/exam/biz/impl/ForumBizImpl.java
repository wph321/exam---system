package com.exam.biz.impl;

import java.util.List;

import com.exam.biz.ForumBiz;
import com.exam.dao.ForumDao;
import com.exam.dao.ReplyDao;
import com.exam.entity.Forum;
import com.exam.entity.Student;



public class ForumBizImpl implements ForumBiz {
	private ForumDao forumdao;
	private ReplyDao replydao;
	@Override
	public int addForum(Forum f) throws Exception {
		// TODO Auto-generated method stub
		return forumdao.addForum(f);
	}

	@Override
	public Forum findForumByCid(Integer cid) throws Exception {
		// TODO Auto-generated method stub
		return forumdao.findForumByCid(cid);
	}

	@Override
	public Forum findForumByComid(Integer comid) throws Exception {
		// TODO Auto-generated method stub
		return forumdao.findForumByComid(comid);
	}

	@Override
	public List<Forum> findAllForum() throws Exception {
		// TODO Auto-generated method stub
		return forumdao.findAllForum();
	}

	@Override
	public int deleteForumByFid(Forum f) throws Exception {
		// TODO Auto-generated method stub
		replydao.deleteReplyBfid(f.getFid());
		forumdao.deleteForumByFid(f);
		return 1;
	}

	public ForumDao getForumdao() {
		return forumdao;
	}

	public void setForumdao(ForumDao forumdao) {
		this.forumdao = forumdao;
	}

	@Override
	public int findPage() throws Exception {
		// TODO Auto-generated method stub
		return forumdao.findPage();
	}

	@Override
	public List<Forum> findForumByPage(Integer nowpage) throws Exception {
		// TODO Auto-generated method stub
		return forumdao.findForumByPage(nowpage);
	}

	@Override
	public Forum findForumByFid(Integer fid) throws Exception {
		// TODO Auto-generated method stub
		return forumdao.findForumByFid(fid);
	}

	public ReplyDao getReplydao() {
		return replydao;
	}

	public void setReplydao(ReplyDao replydao) {
		this.replydao = replydao;
	}

	@Override
	public int findPageByTerm(Forum f) throws Exception {
		// TODO Auto-generated method stub
		return forumdao.findPageByTerm(f);
	}

	@Override
	public List<Forum> findForumByPageTerm(Forum f, Integer nowpage)
			throws Exception {
		// TODO Auto-generated method stub
		return forumdao.findForumByPageTerm(f, nowpage);
	}

	@Override
	public List<Forum> findForumByUserAndPage(Student stu, Integer nowpage)
			throws Exception {
		// TODO Auto-generated method stub
		return forumdao.findForumByUserAndPage(stu , nowpage);
	}

	@Override
	public int findPageByUser(Student stu) throws Exception {
		// TODO Auto-generated method stub
		return forumdao.findPageByUser(stu);
	}

}
