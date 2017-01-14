package com.exam.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.exam.dao.ErrorDao;
import com.exam.entity.Student;
import com.exam.entity.Error;
import com.exam.entity.Subject;

public class ErrorDaoImpl extends HibernateDaoSupport implements ErrorDao {

	@Override
	public int addError(Error e) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(e);
		return 1;
	}

	@Override
	public Error findErrorById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		String hql="from Error e where e.id="+id;
		Query query = this.getSession().createQuery(hql);
		List<Error> errorList = query.list();
		return errorList.get(0);
	}

	@Override
	public List<Error> findAllErrorByStu(Student stu) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("进入到了按照student查错误记录的方法");
		String hql="from Error e where e.student.id="+stu.getId();
		Query query = this.getSession().createQuery(hql);
		List<Error> errorList = query.list();
		
//		for(Error error:errorList){
//			//访问
//			error.getQuestion().getKnowpint();
//			error.getQuetype().getTname();
//			error.getStudent().getId();
//			error.getQuestion().getSubject().getSname();
//		}
		return errorList;
	}

	@Override
	public int deleteErrorById(Error e) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(e);
		return 1;
	}

	@Override
	public int findPage() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("进入到了查询所有页数");
		String sqlStr="select count(*) from Error";
		
		Query query = this.getSession().createQuery(sqlStr);
		List<Object> object =query.list();
		int ts =Integer.valueOf(object.get(0).toString());
		int num =0;
		if(ts%6==0)
			num = ts/4;
		else
			num=ts/4+1;
		return num;
	}

	@Override
	public int findPageByStu(Student stu,Error error) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("进入到按条件查询页数");
		String sqlStr="select count(*) from Error e where e.student.id="+stu.getId();
		if(error.getQuestion().getSubject()!=null){
			sqlStr+=" and e.question.subject.id="+error.getQuestion().getSubject().getId();
		}
		Query query = this.getSession().createQuery(sqlStr);
		List<Object> object =query.list();
		int ts =Integer.valueOf(object.get(0).toString());
		int num =0;
		if(ts%4==0)
			num = ts/4;
		else
			num=ts/4+1;
		return num;
	}
	
	@Override
	public List<Error> findErrorByPage(Integer nowpage) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("进入到了Dao根据页数查询error");
		String hql="from Error";
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult((nowpage-1)*4);
		query.setMaxResults(4);
		List<Error> errorList = query.list();
		return errorList;
	}
	@Override
	public List<Error> findErrorByPageAndStu(Integer nowpage, Student stu,Error error)
			throws Exception {
		
		String hql="from Error e where e.student.id="+stu.getId();
		if(error.getQuestion().getSubject()!=null){
			hql+=" and e.question.subject.id="+error.getQuestion().getSubject().getId();
		}
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult((nowpage-1)*4);
		query.setMaxResults(4);
		List<Error> errorList = query.list();
		System.out.println(errorList.size());
		return errorList;
	}

	@Override
	public List<Error> findErrorBySubidAndUser(Integer id, Student stu)
			throws Exception {
		// TODO Auto-generated method stub
		String hqlStr = "from Error e where e.student.id="+stu.getId()+" and e.question.subject.id="+id;
		List<Error> errorList =(List<Error>) this.getHibernateTemplate().find(hqlStr);
		
//		for(Error error:errorList){
//			//访问
//			error.getQuestion().getKnowpint();
//			error.getQuetype().getTname();
//			error.getStudent().getId();
//			error.getQuestion().getSubject().getSname();
//		}
		return errorList;
	}

	@Override
	public Error findErrorByQidAndStu(Integer qid, Student stu) throws Exception {
		// TODO Auto-generated method stub
		String hql="from Error e where e.question.id="+qid+" and e.student.id="+stu.getId();
		Query query = this.getSession().createQuery(hql);
		List<Error> errorList = query.list();
		if(errorList.size()>0)
			return errorList.get(0);
		return null;
	}	
	public List<Integer> findErrorBySub(Integer sid)throws Exception {
		String hql="select  e.question.id  from Error e where e.question.subject.id="+sid+" group by e.question order by count(e) desc";
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(10);
		List<Integer> errorList = query.list();
		System.out.println(errorList.size());
		
		return errorList;
	}
}
