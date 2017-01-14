package com.exam.biz;

import java.util.List;

import com.exam.entity.Error;
import com.exam.entity.Student;
import com.exam.entity.Subject;





public interface ErrorBiz {
	public int addError(Error e)throws Exception;
	public Error findErrorById(Integer id)throws Exception;
	//�ж�ѧ���Ƿ��й�
	public Error findErrorByQidAndStu(Integer qid,Student stu)throws Exception;
	public List<Error> findAllErrorByStu(Student stu)throws Exception;
	public int deleteErrorById(Error e)throws Exception;
	//��ҳ��ѯ
	public int findPage()throws Exception;
	public List<Error> findErrorByPage(Integer nowpage)throws Exception;
	//��ҳ��ѧ����
	public int findPageByStu(Student stu,Error error)throws Exception;
	public List<Error> findErrorByPageAndStu(Integer nowpage,Student stu,Error error)throws Exception;

	public List<Error> findErrorBySubidAndUser(Integer id, Student stu)throws Exception ;
	public List<Integer> findErrorBySub(Integer sid)throws Exception;
}
