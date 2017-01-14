package com.exam.biz.impl;

import java.util.List;

import com.exam.biz.NewBiz;
import com.exam.biz.NoteBiz;
import com.exam.dao.NewDao;
import com.exam.dao.NoteDao;
import com.exam.entity.New;
import com.exam.entity.Note;
import com.exam.entity.Question;
import com.exam.entity.Student;
import com.exam.entity.Subject;


public class NoteBizImpl implements NoteBiz {
	private NoteDao notedao;
	@Override
	public int addNote(Note n) throws Exception {
		// TODO Auto-generated method stub
		return notedao.addNote(n);
	}

	@Override
	public Note findNoteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return notedao.findNoteById(id);
	}

	@Override
	public List<Note> findAllNoteByStu(Student stu) throws Exception {
		// TODO Auto-generated method stub
		return notedao.findAllNoteByStu(stu);
	}

	@Override
	public int findPageByStu(Student stu) throws Exception {
		// TODO Auto-generated method stub
		return notedao.findPageByStu(stu);
	}

	@Override
	public List<Note> findNoteByPageAndStu(Integer nowpage, Student stu)
			throws Exception {
		// TODO Auto-generated method stub
		return notedao.findNoteByPageAndStu(nowpage, stu);
	}

	public NoteDao getNotedao() {
		return notedao;
	}

	public void setNotedao(NoteDao notedao) {
		this.notedao = notedao;
	}

	@Override
	public int findPageByQue(Question q) throws Exception {
		// TODO Auto-generated method stub
		return notedao.findPageByQue(q);
	}

	@Override
	public List<Note> findNoteByPageAndQue(Integer nowpage, Question q)
			throws Exception {
		// TODO Auto-generated method stub
		return notedao.findNoteByPageAndQue(nowpage, q);
	}

	@Override
	public int deleteNoteById(Note n) throws Exception {
		// TODO Auto-generated method stub
		return notedao.deleteNoteById(n);
	}

	@Override
	public int findPageByStuAndSub(Student stu, Subject sub) throws Exception {
		// TODO Auto-generated method stub
		return notedao.findPageByStuAndSub(stu, sub);
	}

	@Override
	public List<Note> findNoteByPageAndStuAndSub(Integer nowpage, Student stu,
			Subject sub) throws Exception {
		// TODO Auto-generated method stub
		return notedao.findNoteByPageAndStuAndSub(nowpage, stu, sub);
	}
	
}
