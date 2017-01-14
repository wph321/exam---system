package com.exam.dao;

import java.util.List;

import com.exam.entity.Point;
import com.exam.entity.Subject;

public interface PointDao {
  //���֪ʶ��
    public int addPoint(Point point)throws Exception;
  //ɾ��֪ʶ��
    public int deletePoint(Point point)throws Exception;
  //�޸�֪ʶ��
    public int updatePoint(Point point)throws Exception;
  //��ѯ֪ʶ��
    public List<Point> findPoint()throws Exception;
  //��ѯ����֪ʶ��
    public Point findonePoint(Integer pid)throws Exception;
  //����Subject��ѯ֪ʶ��
    public List<Point> findPointBySid(Integer sid) throws Exception; 
  //��ѯҳ��
    public int findPages()throws Exception;
    public List<Point> findByPage(Integer nowpage)throws Exception;
   //��������ҳ��ѯ
    public int findPagesBySubject(Subject subject)throws Exception;
    public List<Point> findBypageAndSubject(Subject subject,Integer nowpage)throws Exception;
}
