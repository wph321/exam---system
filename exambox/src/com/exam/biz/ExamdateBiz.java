package com.exam.biz;

import java.util.List;

import com.exam.entity.Examdate;
import com.exam.entity.Point;
import com.exam.entity.Subject;

public interface ExamdateBiz {
	   //��ӿ���ʱ��
		public int addDate(Examdate examdate)throws Exception;
	   //ɾ������ʱ��
		public int deleteDate(Integer did)throws Exception;
	   //�޸Ŀ���ʱ��
	   public int updateDate(Examdate examdate)throws Exception;
		//��ѯ����ʱ��
	   public List<Examdate> findDate()throws Exception;
		//��ѯ�����Ŀ���ʱ��
	   public Examdate findoneDate(Integer did)throws Exception;
	 //��ѯҳ��
	   public int findPages()throws Exception;
	   public List<Examdate> findByPage(Integer nowpage)throws Exception;
	 //��ѯҳ��
	   public int findPageBySubject(Subject subject)throws Exception;
	   public List<Examdate> findDateByPageAndSubject(Integer nowpage,Subject subject)throws Exception;
}
