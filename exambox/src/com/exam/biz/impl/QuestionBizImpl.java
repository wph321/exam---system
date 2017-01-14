package com.exam.biz.impl;

import java.util.List;

import com.exam.biz.QuestionBiz;
import com.exam.dao.QuestionDao;
import com.exam.entity.Question;

public class QuestionBizImpl implements QuestionBiz {

	private QuestionDao questionDao;
	//模糊查询Question
	public List<Question> mhfindQuestion(String mohu) throws Exception{
		return questionDao.mhfindQuestion(mohu);
	}

	//模糊分页查询Question
	public int mufindPageBymu(String mohu) throws Exception{
		return questionDao.mufindPageBymu(mohu);
	}
	public List<Question> mufindQuestionByPage(String mohu , Integer mupage) throws Exception{
		return questionDao.mufindQuestionByPage(mohu, mupage);
	}
	
	@Override
	public int addQuestion(Question question) throws Exception {
		// TODO Auto-generated method stub
		return questionDao.addQuestion(question);
	}

	@Override
	public List<Question> findQuestionBySubjectandPage(Integer subid,
			Integer queSubPage) throws Exception {
		// TODO Auto-generated method stub
		return questionDao.findQuestionBySubjectandPage(subid, queSubPage);
	}

	@Override
	public List<Question> findQuestion(Integer pid,
			Integer quePoiPage,Integer subid) throws Exception {
		// TODO Auto-generated method stub
		return questionDao.findQuestion(pid, quePoiPage,subid);
	}

	@Override
	public Question findQuestionById(Integer qid) throws Exception {
		// TODO Auto-generated method stub
		return questionDao.findQuestionById(qid);
	}

	@Override
	public List<Question> findQuestionBySubject(Integer subid) throws Exception {
		// TODO Auto-generated method stub
		return questionDao.findQuestionBySubject(subid);
	}

	@Override
	public int findPageBySubject(Integer subid) throws Exception {
		// TODO Auto-generated method stub
		return questionDao.findPageBySubject(subid);
	}

	@Override
	public List<Question> findQuestionByPoint(Integer pid) throws Exception {
		// TODO Auto-generated method stub
		return questionDao.findQuestionByPoint(pid);
	}

	@Override
	public int findPage(Integer pid,Integer subid) throws Exception {
		// TODO Auto-generated method stub
		return questionDao.findPage(pid,subid);
	}

	@Override
	public List<Question> findQuestionBySubjectandQtype(Integer subid,
			Integer qtid) throws Exception {
		// TODO Auto-generated method stub
		return questionDao.findQuestionBySubjectandQtype(subid, qtid);
	}

	@Override
	public List<Question> findQuestionByPointandQtype(Integer pid, Integer qtid)
			throws Exception {
		// TODO Auto-generated method stub
		return questionDao.findQuestionByPointandQtype(pid, qtid);
	}

	@Override
	public int findPageBySubjectandQtype(Integer subid, Integer qtid)
			throws Exception {
		// TODO Auto-generated method stub
		return questionDao.findPageBySubjectandQtype(subid, qtid);
	}

	@Override
	public List<Question> findQuestionBySubjectandQtypeandPage(Integer subid,
			Integer qtid, Integer sqPage) throws Exception {
		// TODO Auto-generated method stub
		return questionDao.findQuestionBySubjectandQtypeandPage(subid, qtid, sqPage);
	}

	@Override
	public int findPageByPointandQtype(Integer pid, Integer qtid)
			throws Exception {
		// TODO Auto-generated method stub
		return questionDao.findPageByPointandQtype(pid, qtid);
	}

	@Override
	public List<Question> findQuestionByPointandQtypeandPage(Integer pid,
			Integer qtid, Integer pqPage) throws Exception {
		// TODO Auto-generated method stub
		return questionDao.findQuestionByPointandQtypeandPage(pid, qtid, pqPage);
	}
	
	//修改试题
	public void updateQuestion(Question question) throws Exception{
		questionDao.updateQuestion(question);
	}

	public QuestionDao getQuestionDao() {
		return questionDao;
	}

	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}

}
