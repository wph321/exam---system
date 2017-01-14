package com.exam.dao;

import java.util.List;

import com.exam.entity.Question;
import com.exam.entity.Subject;

public interface QuestionDao {

	//模糊查询Question
	public List<Question> mhfindQuestion(String mohu) throws Exception;

	//模糊分页查询Question
	public int mufindPageBymu(String mohu) throws Exception;
	public List<Question> mufindQuestionByPage(String mohu , Integer mupage) throws Exception;
		
	// 添加Question
	public int addQuestion(Question question) throws Exception;

	// 通过科目查询Question
	public List<Question> findQuestionBySubject(Integer subid) throws Exception;

	// 通过科目分页查询Question
	public int findPageBySubject(Integer subid) throws Exception;

	public List<Question> findQuestionBySubjectandPage(Integer subid,
			Integer queSubPage) throws Exception;

	// 通过知识点查询Question
	public List<Question> findQuestionByPoint(Integer pid) throws Exception;

	// 通过知识点分页查询Question
	public int findPage(Integer pid ,Integer subid) throws Exception;

	public List<Question> findQuestion(Integer pid,
			Integer quePoiPage,Integer subid) throws Exception;

	// 通过ID查询Question
	public Question findQuestionById(Integer qid) throws Exception;

	// 通过科目和题型查询Question
	public List<Question> findQuestionBySubjectandQtype(Integer subid,
			Integer qtid) throws Exception;

	// 通过知识点和题型查询Question
	public List<Question> findQuestionByPointandQtype(Integer pid, Integer qtid)
			throws Exception;

	// 通过科目和题型进行分页查询Question
	public int findPageBySubjectandQtype(Integer subid, Integer qtid)
			throws Exception;

	public List<Question> findQuestionBySubjectandQtypeandPage(Integer subid,
			Integer qtid, Integer sqPage) throws Exception;

	// 通过知识点和题型进行分页查询Question
	public int findPageByPointandQtype(Integer pid, Integer qtid)
			throws Exception;

	public List<Question> findQuestionByPointandQtypeandPage(Integer pid,
			Integer qtid, Integer pqPage) throws Exception;
	
	//修改试题
	public void updateQuestion(Question question) throws Exception;
}
