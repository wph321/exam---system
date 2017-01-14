package com.exam.biz.impl;

import java.util.List;

import com.exam.biz.GradeBiz;
import com.exam.biz.NewBiz;
import com.exam.dao.GradeDao;
import com.exam.dao.NewDao;
import com.exam.entity.Grade;
import com.exam.entity.New;
import com.exam.entity.Student;


public class GradeBizImpl implements GradeBiz {
	private GradeDao gradedao;
	@Override
	public int addGrade(Grade g) throws Exception {
		// TODO Auto-generated method stub
		return gradedao.addGrade(g);
	}

	@Override
	public Grade findGradeById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return gradedao.findGradeById(id);
	}

	@Override
	public List<Grade> findAllGradeByStu(Student stu) throws Exception {
		// TODO Auto-generated method stub
		return gradedao.findAllGradeByStu(stu);
	}

	@Override
	public int deleteGradeById(Grade grade) throws Exception {
		// TODO Auto-generated method stub
		return gradedao.deleteGradeById(grade);
	}

	@Override
	public int findPage() throws Exception {
		// TODO Auto-generated method stub
		return gradedao.findPage();
	}

	@Override
	public List<Grade> findGradeByPage(Integer nowpage) throws Exception {
		// TODO Auto-generated method stub
		return gradedao.findGradeByPage(nowpage);
	}

	@Override
	public int findPageByStu(Student stu,Grade grade) throws Exception {
		// TODO Auto-generated method stub
		return gradedao.findPageByStu(stu,grade);
	}

	@Override
	public List<Grade> findGradeByPageAndStu(Integer nowpage, Student stu,Grade grade)
			throws Exception {
		// TODO Auto-generated method stub
		return gradedao.findGradeByPageAndStu(nowpage, stu,grade);
	}

	public GradeDao getGradedao() {
		return gradedao;
	}

	public void setGradedao(GradeDao gradedao) {
		this.gradedao = gradedao;
	}

	@Override
	public int updateGrade(Grade g) throws Exception {
		// TODO Auto-generated method stub
		return gradedao.updateGrade(g);
	}

	@Override
	public List<Grade> findGradeBySubId(Integer subid) throws Exception {
		// TODO Auto-generated method stub
		return gradedao.findGradeBySubId(subid);
	}

	@Override
	public List<Grade> findGrades() throws Exception {
		// TODO Auto-generated method stub
		return gradedao.findGrades();
	}

	@Override
	public List<Grade> findGradeBySubIdAndStudent(Integer subid, Student stu)
			throws Exception {
		// TODO Auto-generated method stub
		return gradedao.findGradeBySubIdAndStudent(subid, stu);
	}
	
}
