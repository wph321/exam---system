package com.exam.biz.impl;

import java.util.List;

import com.exam.biz.ExamdateBiz;
import com.exam.dao.ExamdateDao;
import com.exam.entity.Examdate;
import com.exam.entity.Subject;

public class ExamdateBizimpl implements ExamdateBiz {
    private ExamdateDao examdateDao;
	@Override
	public int addDate(Examdate examdate) throws Exception {
		// TODO Auto-generated method stub
		return examdateDao.addDate(examdate);
	}

	@Override
	public int deleteDate(Integer did) throws Exception {
		// TODO Auto-generated method stub
		Examdate examdate=examdateDao.findoneDate(did);
		return examdateDao.deleteDate(examdate);
	}

	@Override
	public int updateDate(Examdate examdate) throws Exception {
		// TODO Auto-generated method stub
		return examdateDao.updateDate(examdate);
	}

	@Override
	public List<Examdate> findDate() throws Exception {
		// TODO Auto-generated method stub
		return examdateDao.findDate();
	}

	@Override
	public Examdate findoneDate(Integer did) throws Exception {
		// TODO Auto-generated method stub
		return examdateDao.findoneDate(did);
	}

	@Override
	public int findPageBySubject(Subject subject) throws Exception {
		// TODO Auto-generated method stub
		return examdateDao.findPageBySubject(subject);
	}

	@Override
	public List<Examdate> findDateByPageAndSubject(Integer nowpage,Subject subject) throws Exception {
		// TODO Auto-generated method stub
		return examdateDao.findDateByPageAndSubject(nowpage, subject);
	}
	public ExamdateDao getExamdateDao() {
		return examdateDao;
	}

	public void setExamdateDao(ExamdateDao examdateDao) {
		this.examdateDao = examdateDao;
	}

	@Override
	public int findPages() throws Exception {
		// TODO Auto-generated method stub
		return examdateDao.findPages();
	}

	@Override
	public List<Examdate> findByPage(Integer nowpage) throws Exception {
		// TODO Auto-generated method stub
		return examdateDao.findByPage(nowpage);
	}

}
