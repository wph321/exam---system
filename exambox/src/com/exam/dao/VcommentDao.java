package com.exam.dao;

import com.exam.entity.Vcomment;

public interface VcommentDao {
	// �������
	public int addVcomment(Vcomment comment) throws Exception;

	// ɾ������
	public int deleteVcomment(Vcomment comment) throws Exception;
}
