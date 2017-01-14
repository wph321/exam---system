package com.exam.biz.impl;

import java.util.List;

import com.exam.biz.DoneBiz;
import com.exam.dao.DoneDao;
import com.exam.entity.Done;
import com.exam.entity.Point;
import com.exam.entity.Student;
import com.exam.entity.Subject;

public class DoneBizImpl implements DoneBiz {
private DoneDao donedao;
	@Override
	public void addDone(Done d) throws Exception {
		// TODO Auto-generated method stub
		donedao.addDone(d);
	}

	@Override
	public void deleteDone(Done d) throws Exception {
		// TODO Auto-generated method stub
		donedao.deleteDone(d);
	}

	@Override
	public List<Done> findDoneByStuAndSub(Student stu, Subject sub)
			throws Exception {
		// TODO Auto-generated method stub
		return donedao.findDoneByStuAndSub(stu, sub);
	}

	@Override
	public List<Done> findDoneByStuAndPoint(Student stu, Point p)
			throws Exception {
		// TODO Auto-generated method stub
		return donedao.findDoneByStuAndPoint(stu, p);
	}

	public DoneDao getDonedao() {
		return donedao;
	}

	public void setDonedao(DoneDao donedao) {
		this.donedao = donedao;
	}

	@Override
	public Done findDoneByQid(Integer qid) throws Exception {
		// TODO Auto-generated method stub
		return donedao.findDoneByQid(qid);
	}

}
