package com.exam.biz.impl;

import java.util.List;

import com.exam.biz.SubjectBiz;
import com.exam.dao.SubjectDao;
import com.exam.entity.Subject;

public class SubjectBizimpl implements SubjectBiz {
   private SubjectDao subjectDao;
	@Override
	public int addSubject(Subject suject) throws Exception {
		// TODO Auto-generated method stub
		return subjectDao.addSubject(suject);
	}

	@Override
	public int deleteSubject(Integer sid) throws Exception {
		System.out.println("进入Biz方法");
		// TODO Auto-generated method stub
		Subject subject=subjectDao.findone(sid);
		return subjectDao.deleteSubject(subject);
	}

	@Override
	public int updateSubject(Subject subject) throws Exception {
		// TODO Auto-generated method stub
		return subjectDao.updateSubject(subject);
	}

	@Override
	public List<Subject> findSubject() throws Exception {
		// TODO Auto-generated method stub
		return subjectDao.findSubject();
	}
	@Override
	public Subject findone(Integer sid) throws Exception {
		// TODO Auto-generated method stub
		return subjectDao.findone(sid);
	}

	@Override
	public List<Subject> findByPage(Integer nowpage) throws Exception {
		// TODO Auto-generated method stub
		return subjectDao.findByPage(nowpage);
	}

	@Override
	public int findPages() throws Exception {
		// TODO Auto-generated method stub
		return subjectDao.findPages();
	}
	public SubjectDao getSubjectDao() {
		return subjectDao;
	}

	public void setSubjectDao(SubjectDao subjectDao) {
		this.subjectDao = subjectDao;
	}

	

	
}
