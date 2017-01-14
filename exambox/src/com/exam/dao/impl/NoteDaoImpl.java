package com.exam.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.exam.dao.NoteDao;
import com.exam.entity.Grade;
import com.exam.entity.Note;
import com.exam.entity.Question;
import com.exam.entity.Student;
import com.exam.entity.Subject;




public class NoteDaoImpl extends HibernateDaoSupport implements NoteDao {

	@Override
	public int addNote(Note n) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(n);
		return 1;
	}

	@Override
	public Note findNoteById(Integer nid) throws Exception {
		// TODO Auto-generated method stub
		String hql="from Note g where g.id="+nid;
		Query query = this.getSession().createQuery(hql);
		List<Note> noteList = query.list();
		return noteList.get(0);
	}

	@Override
	public List<Note> findAllNoteByStu(Student stu) throws Exception {
		// TODO Auto-generated method stub
		String hql="from Note g where g.student.id="+stu.getId();
		Query query = this.getSession().createQuery(hql);
		List<Note> noteList = query.list();
		return noteList;
	}

	@Override
	public int deleteNoteById(Note n) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(n);
		return 1;
	}



	@Override
	public int findPageByStu(Student stu) throws Exception {
		// TODO Auto-generated method stub
		String sqlStr="select count(*) from Note e where e.student.id="+stu.getId();
		
		Query query = this.getSession().createQuery(sqlStr);
		List<Object> object =query.list();
		int ts =Integer.valueOf(object.get(0).toString());
		int num =0;
		if(ts%6==0)
			num = ts/6;
		else
			num=ts/6+1;
		return num;
	}

	@Override
	public List<Note> findNoteByPageAndStu(Integer nowpage, Student stu)
			throws Exception {
		// TODO Auto-generated method stub
		String hql="from Note e where e.student.id="+stu.getId();
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult((nowpage-1)*6);
		query.setMaxResults(6);
		List<Note> noteList = query.list();
		return noteList;
	}

	@Override
	public int findPageByQue(Question q) throws Exception {
		// TODO Auto-generated method stub
		String sqlStr="select count(*) from Note e where e.question.id="+q.getId();
		
		Query query = this.getSession().createQuery(sqlStr);
		List<Object> object =query.list();
		int ts =Integer.valueOf(object.get(0).toString());
		int num =0;
		if(ts%6==0)
			num = ts/6;
		else
			num=ts/6+1;
		return num;
	}

	@Override
	public List<Note> findNoteByPageAndQue(Integer nowpage, Question q)
			throws Exception {
		// TODO Auto-generated method stub
		String hql="from Note e where e.question.id="+q.getId();
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult((nowpage-1)*6);
		query.setMaxResults(6);
		List<Note> noteList = query.list();
		return noteList;
	}

	@Override
	public int findPageByStuAndSub(Student stu, Subject sub) throws Exception {
		// TODO Auto-generated method stub
     String sqlStr="select count(*) from Note e where e.student.id="+stu.getId();
      if(sub!=null){
		if(sub.getId()!=-1)
			sqlStr+=" and e.question.subject.id="+sub.getId(); 
		}
		Query query = this.getSession().createQuery(sqlStr);
		List<Object> object =query.list();
		int ts =Integer.valueOf(object.get(0).toString());
		int num =0;
		if(ts%6==0)
			num = ts/6;
		else
			num=ts/6+1;
		return num;
		
	}

	@Override
	public List<Note> findNoteByPageAndStuAndSub(Integer nowpage, Student stu,
			Subject sub) throws Exception {
		// TODO Auto-generated method stub
		String hql="from Note e where  e.student.id="+stu.getId();
		if(sub!=null){
		if(sub.getId()!=-1)
				 hql+=" and e.question.subject.id="+sub.getId(); 
		}
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult((nowpage-1)*6);
		query.setMaxResults(6);
		List<Note> noteList = query.list();
		return noteList;
		
	}

	
}
