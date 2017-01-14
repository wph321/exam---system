package com.exam.biz;

import java.util.List;

import com.exam.entity.Forum;
import com.exam.entity.Student;

public interface ForumBiz {
	public int addForum(Forum f) throws Exception;

	public Forum findForumByCid(Integer cid) throws Exception;

	public Forum findForumByComid(Integer comid) throws Exception;

	public List<Forum> findAllForum() throws Exception;

	public int deleteForumByFid(Forum f) throws Exception;

	public Forum findForumByFid(Integer fid) throws Exception;

	// ��ҳ��ѯ
	public int findPage() throws Exception;

	public List<Forum> findForumByPage(Integer nowpage) throws Exception;

	// tiaojian��ҳ��ѯ
	public int findPageByTerm(Forum f) throws Exception;

	public List<Forum> findForumByPageTerm(Forum f, Integer nowpage)
			throws Exception;

	// �����û���ҳ����ѯ����
	public List<Forum> findForumByUserAndPage(Student stu, Integer nowpage)
			throws Exception;

	public int findPageByUser(Student stu) throws Exception;
}
