package com.exam.biz.impl;

import java.util.List;

import com.exam.biz.PointBiz;
import com.exam.dao.PointDao;
import com.exam.entity.Point;
import com.exam.entity.Subject;

public class PointBizimpl implements PointBiz {
   private PointDao pointDao;
	@Override
	public int addPoint(Point point) throws Exception {
		// TODO Auto-generated method stub
		return pointDao.addPoint(point);
	}

	@Override
	public int deletePoint(Integer pid) throws Exception {
		// TODO Auto-generated method stub
		Point point=pointDao.findonePoint(pid);
		return pointDao.deletePoint(point);
	}

	@Override
	public int updatePoint(Point point) throws Exception {
		// TODO Auto-generated method stub
		return pointDao.updatePoint(point);
	}

	@Override
	public List<Point> findPoint() throws Exception {
		// TODO Auto-generated method stub
		return pointDao.findPoint();
	}

	@Override
	public Point findonePoint(Integer pid) throws Exception {
		// TODO Auto-generated method stub
		return pointDao.findonePoint(pid);
	}

	@Override
	public int findPages() throws Exception {
		// TODO Auto-generated method stub
		return pointDao.findPages();
	}

	@Override
	public List<Point> findByPage(Integer nowpage) throws Exception {
		// TODO Auto-generated method stub
		return pointDao.findByPage(nowpage);
	}

	@Override
	public int findPagesBySubject(Subject subject) throws Exception {
		// TODO Auto-generated method stub
		return pointDao.findPagesBySubject(subject);
	}

	@Override
	public List<Point> findBypageAndSubject(Subject subject, Integer nowpage)
			throws Exception {
		// TODO Auto-generated method stub
		return pointDao.findBypageAndSubject(subject, nowpage);
	}
	public PointDao getPointDao() {
		return pointDao;
	}

	public void setPointDao(PointDao pointDao) {
		this.pointDao = pointDao;
	}
	@Override
	public List<Point> findPointBySid(Integer sid) throws Exception {
		// TODO Auto-generated method stub
		return pointDao.findPointBySid(sid);
	}
}
