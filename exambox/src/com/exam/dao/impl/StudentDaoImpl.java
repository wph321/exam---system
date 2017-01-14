package com.exam.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.exam.dao.StudentDao;
import com.exam.entity.Error;
import com.exam.entity.Student;

public class StudentDaoImpl extends HibernateDaoSupport implements StudentDao{
	
	@Override
	public void addStudent(Student student) throws Exception {
		System.out.println("进入到了添加学生的Dao方法");
		this.getHibernateTemplate().save(student);
	}
	@Override
	public Student login(String loginName, String loginPwd) throws Exception {
		
		String hqlStr = "from Student where loginname='"+loginName+"'"+" and loginpass='"+loginPwd+"'";		
		List<Student> stuList = (List<Student>) this.getHibernateTemplate().find(hqlStr);
		if(stuList.size()!=0){
			System.out.println("不为0");
			return stuList.get(0);
		}
		return null;
	}
	@Override
	public Student findStudentBySid(Integer sid) throws Exception {
		// TODO Auto-generated method stub
		String hqlStr = "from Student where id="+sid;
		List<Student> studentList = (List<Student>) this.getHibernateTemplate().find(hqlStr);
		if(studentList.size()!=0){
			return studentList.get(0);
		}
		return null;
	}
	@Override
	public List<Student> findStudents() throws Exception {
		// TODO Auto-generated method stub
		String hqlStr ="from Student ";
		return (List<Student>) this.getHibernateTemplate().find(hqlStr);
	}
	@Override
	public void updateStudent(Student stu) throws Exception {
		this.getHibernateTemplate().update(stu);
	}
	@Override
	public void deleteStudent(Student student) throws Exception {
		System.out.println(student.getId());
		this.getHibernateTemplate().delete(student);
	}
	
	
	
	@Override
	public int findPage() throws Exception {
		// TODO Auto-generated method stub
		String sqlStr="select count(*) from Student";
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
	public List<Student> findStudentByPage(Integer nowpage) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("进入到了Dao根据页数查询Student");
		String hql="from Student";
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult((nowpage-1)*4);
		query.setMaxResults(4);
		List<Student> studentList = query.list();
		return studentList;
	}
	@Override
	public int findPageByStu(Student stu) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("进入到按条件查询页数");
		String sqlStr="select count(*) from Student where 1=1";
		if(stu.getUname()!=null){
			sqlStr+=" and  uname like '%"+stu.getUname()+"%'";
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
	public List<Student> findStudentByPageAndStu(Integer nowpage, Student stu)
			throws Exception {
		// TODO Auto-generated method stub
		
		String hql="from Student where 1=1";
		if(stu.getUname()!=null){
			hql+=" and  uname like '%"+stu.getUname()+"%'";
		}
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult((nowpage-1)*4);
		query.setMaxResults(4);
		List<Student> studentList = query.list();
		return studentList;
	}
	@Override
	public Student findStudentByUName(String UName) throws Exception {
		// TODO Auto-generated method stub
		String hqlStr = "from Student where uname='"+UName+"'";
		List<Student> stuList = (List<Student>) this.getHibernateTemplate().find(hqlStr);
		if(stuList.size()!=0){
			return stuList.get(0);
		}
		return null;
	}
	@Override
	public Student findStudentByLoginName(String loginName) throws Exception {
		// TODO Auto-generated method stub
		String hqlStr = "from Student where loginname='"+loginName+"'";
		List<Student> stuList = (List<Student>) this.getHibernateTemplate().find(hqlStr);
		if(stuList.size()!=0){
			return stuList.get(0);
		}
		return null;
	}
}
