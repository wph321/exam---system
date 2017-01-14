package com.exam.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.exam.dao.QuestionDao;
import com.exam.entity.Question;
import com.exam.entity.Subject;

public class QuestionDaoImpl extends HibernateDaoSupport implements QuestionDao {

	private Session session1;

	private Question question;

	// 分页查询Question

	// 模糊查询Question
	public List<Question> mhfindQuestion(String mohu) throws Exception {
		String strHql = "from Question q where q.qtitle like '%" + mohu + "%'";
		List<Question> questionList = (List<Question>) this
				.getHibernateTemplate().find(strHql);
		return questionList;
	}

	// 模糊分页查询Question
	public int mufindPageBymu(String mohu) throws Exception {
		String[] mohusum = mohu.split(" ");
		String sqlStr = "select count(*) from Question q where 1=1";
		for(int i = 0 ; i<mohusum.length ; i++){
			
			sqlStr += " and( q.qtitle like '%"
				+ mohusum[i] + "%' or q.analysis like '%" + mohusum[i] + "%'"
						+ " or q.subject.sname like '%" + mohusum[i] + "%')";
		}
		Query query = this.getSession().createQuery(sqlStr);
		List<Object> object = query.list();
		int ts = Integer.valueOf(object.get(0).toString());
		int num = 0;
		if (ts % 8 == 0)
			num = ts / 8;
		else
			num = ts / 8 + 1;
		return num;
	}

	public List<Question> mufindQuestionByPage(String mohu, Integer mupage)
			throws Exception {
		String[] mohusum = mohu.split(" ");
		String sqlStr = "from Question q where 1=1";
		for(int i = 0 ; i<mohusum.length ; i++){
			sqlStr += " and( q.qtitle like '%"
				+ mohusum[i] + "%' or q.analysis like '%" + mohusum[i] + "%'"
						+ " or q.subject.sname like '%" + mohusum[i] + "%')";
		}
		Query query = this.getSession().createQuery(sqlStr);
		query.setFirstResult((mupage - 1) * 8);
		query.setMaxResults(8);
		List<Question> questionList = query.list();
		return questionList;
	}

	// // 模糊分页查询Question
	// public int mufindPageBymu(String mohu) throws Exception {
	// String sqlStr = "select count(*) from Question q where q.qtitle like '%"
	// + mohu + "%'";
	// Query query = this.getSession().createQuery(sqlStr);
	// List<Object> object = query.list();
	// int ts = Integer.valueOf(object.get(0).toString());
	// int num = 0;
	// if (ts % 8 == 0)
	// num = ts / 8;
	// else
	// num = ts / 8 + 1;
	// return num;
	// }
	//
	// public List<Question> mufindQuestionByPage(String mohu, Integer mupage)
	// throws Exception {
	// String hqlStr = "from Question q where q.qtitle like '%" + mohu + "%'";
	// Query query = this.getSession().createQuery(hqlStr);
	// query.setFirstResult((mupage - 1) * 8);
	// query.setMaxResults(8);
	// List<Question> questionList = query.list();
	// return questionList;
	// }

	// 添加Question
	public int addQuestion(Question question) throws Exception {
		this.getHibernateTemplate().save(question);
		return 1;
	}

	// 通过科目查询Question
	public List<Question> findQuestionBySubject(Integer subid) throws Exception {
		String hqlStr = "from Question q where q.subject.id = " + subid;
		List<Question> questionList = (List<Question>) this
				.getHibernateTemplate().find(hqlStr);

		return questionList;
	}

	// 通过科目分页查询Question
	public int findPageBySubject(Integer subid) throws Exception {
		String sqlStr = "select count(*) from Question q where q.subject.id="
				+ subid;
		Query query = this.getSession().createQuery(sqlStr);
		List<Object> object = query.list();
		int ts = Integer.valueOf(object.get(0).toString());
		int num = 0;
		if (ts % 8 == 0)
			num = ts / 8;
		else
			num = ts / 8 + 1;
		return num;
	}

	public List<Question> findQuestionBySubjectandPage(Integer subid,
			Integer queSubPage) throws Exception {
		String hqlStr = "from Question q where q.subject.id=" + subid;
		Query query = this.getSession().createQuery(hqlStr);
		query.setFirstResult((queSubPage - 1) * 8);
		query.setMaxResults(8);
		List<Question> questionList = query.list();
		return questionList;
	}

	// 通过知识点查询Question
	public List<Question> findQuestionByPoint(Integer pid) throws Exception {
		String hqlStr = "from Question q left join fetch q.subject left join fetch q.points p where p.id= "
				+ pid;
		List<Question> questionList = (List<Question>) this
				.getHibernateTemplate().find(hqlStr);
		System.out.println("Impl+....." + questionList);
		return questionList;
	}

	// 分页查询Question(!!!现在只用这个)
	public int findPage(Integer pid, Integer subid) throws Exception {
		String sqlStr = "from Question q left join fetch q.points p where 1=1";
		if (pid != null && pid != -1) {
			sqlStr += " and p.id=" + pid;
		}
		if (subid != null && subid != -1) {
			sqlStr += " and q.subject.id=" + subid;
		}

		List<Question> questionList = (List<Question>) this
				.getHibernateTemplate().find(sqlStr);
		Set<Question> qset = new HashSet<Question>();
		qset.addAll(questionList);
		List<Question> qlist = new ArrayList<Question>();
		qlist.addAll(qset);
		int ts = qlist.size();
		System.out.println("ts:" + ts);
		// List<Object> object= (List<Object>)
		// this.getHibernateTemplate().find(sqlStr);
		// int ts = Integer.valueOf(object.get(0).toString());
		int num = 0;
		if (ts % 8 == 0)
			num = ts / 8;
		else
			num = ts / 8 + 1;
		return num;
	}

	public List<Question> findQuestion(Integer pid, Integer quePoiPage,
			Integer subid) throws Exception {
		String hqlStr = "from Question q left join fetch q.points p  where 1=1";
		if (pid != null && pid != -1) {
			hqlStr += " and p.id= " + pid;
		}
		if (subid != null && subid != -1) {
			hqlStr += " and q.subject.id=" + subid;
		}

		Query query = this.getSession().createQuery(hqlStr);
		query.setFirstResult((quePoiPage - 1) * 8);
		query.setMaxResults(8);
		List<Question> questionList = query.list();
		return questionList;
	}

	// 通过ID查询Question
	public Question findQuestionById(Integer qid) throws Exception {
		String hqlStr = "from Question q where q.id = " + qid;
		List<Question> questionList = (List<Question>) this
				.getHibernateTemplate().find(hqlStr);
		question = questionList.get(0);
		return question;
	}

	// 通过科目和题型查询Question
	public List<Question> findQuestionBySubjectandQtype(Integer subid,
			Integer qtid) throws Exception {
		String hqlStr = "from Question q where q.subject.id = '" + subid
				+ "' and q.quetype.id = '" + qtid + "' ";
		List<Question> questionList = (List<Question>) this
				.getHibernateTemplate().find(hqlStr);
		return questionList;
	}

	// 通过科目和题型进行分页查询Question
	public int findPageBySubjectandQtype(Integer subid, Integer qtid)
			throws Exception {
		String sqlStr = "select count(*) from Question q where q.subject.id = '"
				+ subid + "' and q.quetype.id ='" + qtid + "'";
		Query query = this.getSession().createQuery(sqlStr);
		List<Object> object = query.list();
		int ts = Integer.valueOf(object.get(0).toString());
		int num = 0;
		if (ts % 8 == 0)
			num = ts / 8;
		else
			num = ts / 8 + 1;
		return num;
	}

	public List<Question> findQuestionBySubjectandQtypeandPage(Integer subid,
			Integer qtid, Integer sqPage) throws Exception {
		String hqlStr = "from Question q where q.subject.id = '" + subid
				+ "' and q.quetype.id = '" + qtid + "'";
		Query query = this.getSession().createQuery(hqlStr);
		query.setFirstResult((sqPage - 1) * 8);
		query.setMaxResults(8);
		List<Question> questionList = query.list();
		return questionList;
	}

	// 通过知识点和题型查询Question(未完成)
	public List<Question> findQuestionByPointandQtype(Integer pid, Integer qtid)
			throws Exception {
		String hqlStr = "from Question q where q.";
		return null;
	}

	// 通过知识点和题型进行分页查询Question(未完成)
	public int findPageByPointandQtype(Integer pid, Integer qtid)
			throws Exception {
		return qtid;

	}

	// 修改试题
	public void updateQuestion(Question question) throws Exception {
		Session session = this.getSession();
		System.out.println("updateDao....");
		Question question_update = (Question) session.get(Question.class,
				question.getId());
		if (question.getQtitle() != null && !"".equals(question.getQtitle()))
			question_update.setQtitle(question.getQtitle());
		if (question.getResult() != null && !"".equals(question.getResult()))
			question_update.setResult(question.getResult());
		if (question.getDifficulty() != null
				&& !"".equals(question.getDifficulty()))
			question_update.setDifficulty(question.getDifficulty());
		if (question.getKnowpint() != null
				&& !"".equals(question.getKnowpint()))
			question_update.setKnowpint(question.getKnowpint());
		if (question.getOptiona() != null && !"".equals(question.getOptiona()))
			question_update.setOptiona(question.getOptiona());
		if (question.getOptionb() != null && !"".equals(question.getOptionb()))
			question_update.setOptionb(question.getOptionb());
		if (question.getOptionc() != null && !"".equals(question.getOptionc()))
			question_update.setOptionc(question.getOptionc());
		if (question.getOptiond() != null && !"".equals(question.getOptiond()))
			question_update.setOptiond(question.getOptiond());
		if (question.getAnalysis() != null && !"".equals(question.getAnalysis()))
			question_update.setAnalysis(question.getAnalysis());
		session.update(question_update);
	}

	public List<Question> findQuestionByPointandQtypeandPage(Integer pid,
			Integer qtid, Integer pqPage) throws Exception {
		return null;

	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Session getSession1() {
		return session1;
	}

	public void setSession1(Session session1) {
		this.session1 = session1;
	}

}
