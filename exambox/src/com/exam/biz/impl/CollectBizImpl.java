package com.exam.biz.impl;

import java.util.List;

import com.exam.biz.CollectBiz;
import com.exam.biz.NewBiz;
import com.exam.dao.CollectDao;
import com.exam.dao.NewDao;
import com.exam.entity.Collect;
import com.exam.entity.New;
import com.exam.entity.Student;


public class CollectBizImpl implements CollectBiz {
	private CollectDao collectdao;
	@Override
	public int addCollect(Collect c) throws Exception {
		// TODO Auto-generated method stub
		return collectdao.addCollect(c);
	}

	@Override
	public Collect findCollectById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return collectdao.findCollectById(id);
	}

	@Override
	public List<Collect> findAllCollectByStu(Student stu) throws Exception {
		// TODO Auto-generated method stub
		return collectdao.findAllCollectByStu(stu);
	}

	@Override
	public int deleteCollectById(Collect c) throws Exception {
		// TODO Auto-generated method stub
		return collectdao.deleteCollectById(c);
	}

	@Override
	public int findPage() throws Exception {
		// TODO Auto-generated method stub
		return collectdao.findPage();
	}

	@Override
	public List<Collect> findCollectByPage(Integer nowpage) throws Exception {
		// TODO Auto-generated method stub
		return collectdao.findCollectByPage(nowpage);
	}

	@Override
	public int findPageByStu(Student stu,Collect collect) throws Exception {
		// TODO Auto-generated method stub
		return collectdao.findPageByStu(stu,collect);
	}

	@Override
	public List<Collect> findCollectByPageAndStu(Integer nowpage, Student stu,Collect collect)
			throws Exception {
		// TODO Auto-generated method stub
		return collectdao.findCollectByPageAndStu(nowpage, stu,collect);
	}

	public CollectDao getCollectdao() {
		return collectdao;
	}

	public void setCollectdao(CollectDao collectdao) {
		this.collectdao = collectdao;
	}
}
