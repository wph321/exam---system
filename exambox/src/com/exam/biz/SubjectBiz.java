package com.exam.biz;

import java.util.List;

import com.exam.entity.Subject;

public interface SubjectBiz {
	  //���ӿ��Կ�Ŀ
		public int addSubject(Subject suject)throws Exception;
	  //ɾ�����Կ�Ŀ
		public int deleteSubject(Integer sid)throws Exception;
	  //�޸ÿ��Կ�Ŀ
		public int updateSubject(Subject suject) throws Exception;
	  //��ѯ���еĿ��Կ�Ŀ
		public List<Subject> findSubject()throws Exception;
	  //��ѯ�������Կ�Ŀ
		public Subject findone(Integer sid)throws Exception;
	  //��ҳ��ѯ���еĿ�Ŀ
		public List<Subject> findByPage(Integer nowpage)throws Exception;
	  //��ѯ��ҳ��
		public int findPages()throws Exception;
}