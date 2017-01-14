package com.exam.dao;

import java.util.List;

import com.exam.entity.Question;
import com.exam.entity.Subject;

public interface QuestionDao {

	//ģ����ѯQuestion
	public List<Question> mhfindQuestion(String mohu) throws Exception;

	//ģ����ҳ��ѯQuestion
	public int mufindPageBymu(String mohu) throws Exception;
	public List<Question> mufindQuestionByPage(String mohu , Integer mupage) throws Exception;
		
	// ���Question
	public int addQuestion(Question question) throws Exception;

	// ͨ����Ŀ��ѯQuestion
	public List<Question> findQuestionBySubject(Integer subid) throws Exception;

	// ͨ����Ŀ��ҳ��ѯQuestion
	public int findPageBySubject(Integer subid) throws Exception;

	public List<Question> findQuestionBySubjectandPage(Integer subid,
			Integer queSubPage) throws Exception;

	// ͨ��֪ʶ���ѯQuestion
	public List<Question> findQuestionByPoint(Integer pid) throws Exception;

	// ͨ��֪ʶ���ҳ��ѯQuestion
	public int findPage(Integer pid ,Integer subid) throws Exception;

	public List<Question> findQuestion(Integer pid,
			Integer quePoiPage,Integer subid) throws Exception;

	// ͨ��ID��ѯQuestion
	public Question findQuestionById(Integer qid) throws Exception;

	// ͨ����Ŀ�����Ͳ�ѯQuestion
	public List<Question> findQuestionBySubjectandQtype(Integer subid,
			Integer qtid) throws Exception;

	// ͨ��֪ʶ������Ͳ�ѯQuestion
	public List<Question> findQuestionByPointandQtype(Integer pid, Integer qtid)
			throws Exception;

	// ͨ����Ŀ�����ͽ��з�ҳ��ѯQuestion
	public int findPageBySubjectandQtype(Integer subid, Integer qtid)
			throws Exception;

	public List<Question> findQuestionBySubjectandQtypeandPage(Integer subid,
			Integer qtid, Integer sqPage) throws Exception;

	// ͨ��֪ʶ������ͽ��з�ҳ��ѯQuestion
	public int findPageByPointandQtype(Integer pid, Integer qtid)
			throws Exception;

	public List<Question> findQuestionByPointandQtypeandPage(Integer pid,
			Integer qtid, Integer pqPage) throws Exception;
	
	//�޸�����
	public void updateQuestion(Question question) throws Exception;
}
