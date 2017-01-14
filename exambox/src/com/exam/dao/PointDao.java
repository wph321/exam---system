package com.exam.dao;

import java.util.List;

import com.exam.entity.Point;
import com.exam.entity.Subject;

public interface PointDao {
  //添加知识点
    public int addPoint(Point point)throws Exception;
  //删除知识点
    public int deletePoint(Point point)throws Exception;
  //修改知识点
    public int updatePoint(Point point)throws Exception;
  //查询知识点
    public List<Point> findPoint()throws Exception;
  //查询单个知识点
    public Point findonePoint(Integer pid)throws Exception;
  //根据Subject查询知识点
    public List<Point> findPointBySid(Integer sid) throws Exception; 
  //查询页数
    public int findPages()throws Exception;
    public List<Point> findByPage(Integer nowpage)throws Exception;
   //多条件分页查询
    public int findPagesBySubject(Subject subject)throws Exception;
    public List<Point> findBypageAndSubject(Subject subject,Integer nowpage)throws Exception;
}
