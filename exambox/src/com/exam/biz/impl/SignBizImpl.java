package com.exam.biz.impl;

import java.util.Date;

import com.exam.biz.SignBiz;
import com.exam.dao.SignDao;
import com.exam.entity.Sign;
import com.exam.entity.Student;

public class SignBizImpl implements SignBiz {
	private SignDao signDaoImpl;
	@Override
	public void addSign(Sign sign) throws Exception {
		// TODO Auto-generated method stub
		signDaoImpl.addSign(sign);
	}
	@Override
	public Sign findByDateAndStudent(Student stu, String datestr) throws Exception {
		// TODO Auto-generated method stub
		return signDaoImpl.findByDateAndStudent(stu, datestr);
	}
	public SignDao getSignDaoImpl() {
		return signDaoImpl;
	}
	public void setSignDaoImpl(SignDao signDaoImpl) {
		this.signDaoImpl = signDaoImpl;
	}
}
