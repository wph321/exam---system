package com.exam.biz.impl;

import java.util.List;

import com.exam.biz.ErrorBiz;
import com.exam.dao.ErrorDao;
import com.exam.entity.Student;
import com.exam.entity.Error;
import com.exam.entity.Subject;

public class ErrorBizImpl implements ErrorBiz {
	private ErrorDao errordao;

	@Override
	public int addError(Error e) throws Exception {
		// TODO Auto-generated method stub
		return errordao.addError(e);
	}

	@Override
	public Error findErrorById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return errordao.findErrorById(id);
	}

	@Override
	public List<Error> findAllErrorByStu(Student stu) throws Exception {
		// TODO Auto-generated method stub
		return errordao.findAllErrorByStu(stu);
		
	}

	@Override
	public int deleteErrorById(Error e) throws Exception {
		// TODO Auto-generated method stub
		return errordao.deleteErrorById(e);
	}

	@Override
	public int findPage() throws Exception {
		// TODO Auto-generated method stub
		return errordao.findPage();
	}

	@Override
	public List<Error> findErrorByPage(Integer nowpage) throws Exception {
		// TODO Auto-generated method stub
		return errordao.findErrorByPage(nowpage);
	}

	@Override
	public int findPageByStu(Student stu,Error error) throws Exception {
		// TODO Auto-generated method stub
		return errordao.findPageByStu(stu,error);
	}

	@Override
	public List<Error> findErrorByPageAndStu(Integer nowpage, Student stu,Error error)
			throws Exception {
		// TODO Auto-generated method stub
		return errordao.findErrorByPageAndStu(nowpage, stu,error);
	}
	@Override
	public List<Error> findErrorBySubidAndUser(Integer id, Student stu)
			throws Exception {
		// TODO Auto-generated method stub
		return errordao.findErrorBySubidAndUser(id, stu);
	}
	public ErrorDao getErrordao() {
		return errordao;
	}

	public void setErrordao(ErrorDao errordao) {
		this.errordao = errordao;
	}

	@Override
	public Error findErrorByQidAndStu(Integer qid, Student stu) throws Exception {
		// TODO Auto-generated method stub
		return errordao.findErrorByQidAndStu(qid, stu);
	}

	@Override
	public List<Integer> findErrorBySub(Integer sid) throws Exception {
		// TODO Auto-generated method stub
		return errordao.findErrorBySub(sid);
	}

	
	
}
