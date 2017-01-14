package com.exam.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.exam.dao.GradeDao;
import com.exam.entity.Grade;
import com.exam.entity.Student;




public class GradeDaoImpl extends HibernateDaoSupport implements GradeDao {

	@Override
	public int addGrade(Grade g) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(g);
		return 1;
	}

	@Override
	public Grade findGradeById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		String hql="from Grade g where g.id="+id;
		Query query = this.getSession().createQuery(hql);
		List<Grade> gradeList = query.list();
		return gradeList.get(0);
	}

	@Override
	public List<Grade> findAllGradeByStu(Student stu) throws Exception {
		// TODO Auto-generated method stub
		String hql="from Grade g where g.student.id="+stu.getId()+"order by time desc";
		Query query = this.getSession().createQuery(hql);
		List<Grade> gradeList = query.list();
		
		for(Grade grade:gradeList){
			grade.getSubject().getSname();
		}
		return gradeList;
	}

	@Override
	public int deleteGradeById(Grade grade) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(grade);
		return 1;
	}

	@Override
	public int findPage() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Grade> findGradeByPage(Integer nowpage) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findPageByStu(Student stu,Grade grade) throws Exception {
		// TODO Auto-generated method stub
		String sqlStr = "";
		if(grade.getSubject().getId().equals(-1)){
			sqlStr = "select count(*) from Grade e where e.student.id="+stu.getId()+"order by time asc";
		}else{
		 sqlStr="select count(*) from Grade e where e.student.id="+stu.getId()+" and e.subject.id="+grade.getSubject().getId()+"order by time asc";
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
	public List<Grade> findGradeByPageAndStu(Integer nowpage, Student stu,Grade grade)
			throws Exception {
		// TODO Auto-generated method stub
		String hql  = "";
		if(grade.getSubject().getId().equals(-1)){
			 hql="from Grade  where student.id="+stu.getId()+"order by time asc";
		}else{
			 hql="from Grade  where student.id="+stu.getId()+" and subject.id="+grade.getSubject().getId()+"order by time asc";
		}
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult((nowpage-1)*4);
		query.setMaxResults(4);
		List<Grade> gradeList = query.list();
		return gradeList;
	}
	@Override
	public int updateGrade(Grade g) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(g);
		return 1;
	}

	@Override
	public List<Grade> findGradeBySubId(Integer subid) throws Exception {
		// TODO Auto-generated method stub
		String hqlStr = "from Grade where subject.id = "+subid+"order by time asc";
		List<Grade> gradeList= (List<Grade>) this.getHibernateTemplate().find(hqlStr);
		return gradeList;
	}

	@Override
	public List<Grade> findGrades() throws Exception {
		// TODO Auto-generated method stub
		String hqlStr = "from Grade";
		List<Grade> gradeList= (List<Grade>)this.getHibernateTemplate().find(hqlStr);
		for(Grade grade :gradeList){
			grade.getSubject().getSname();
		}
		return gradeList;
	}

	@Override
	public List<Grade> findGradeBySubIdAndStudent(Integer subid, Student stu)
			throws Exception {
		// TODO Auto-generated method stub
		String hqlStr = "from Grade where subject.id="+subid+" and student.id="+stu.getId()+"order by time asc";
		List<Grade> gradeList= (List<Grade>)this.getHibernateTemplate().find(hqlStr);
		return gradeList;
	}
}
