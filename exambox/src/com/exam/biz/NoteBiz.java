package com.exam.biz;

import java.util.List;






import com.exam.entity.Forum;
import com.exam.entity.Note;
import com.exam.entity.Question;
import com.exam.entity.Student;
import com.exam.entity.Subject;



public interface NoteBiz {
	public int addNote(Note n)throws Exception;
	public Note findNoteById(Integer nid)throws Exception;
	
	public List<Note> findAllNoteByStu(Student stu)throws Exception;
	public int deleteNoteById(Note n)throws Exception;
	//分页查询(按题)
	public int findPageByQue(Question q)throws Exception;
	public List<Note> findNoteByPageAndQue(Integer nowpage,Question q)throws Exception;
	//分页查学生的
	public int findPageByStu(Student stu)throws Exception;
	public List<Note> findNoteByPageAndStu(Integer nowpage,Student stu)throws Exception;
	//分页查学生、科目的
	public int findPageByStuAndSub(Student stu,Subject sub)throws Exception;
	public List<Note> findNoteByPageAndStuAndSub(Integer nowpage,Student stu,Subject sub)throws Exception;

}
