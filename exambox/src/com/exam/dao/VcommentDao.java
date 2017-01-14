package com.exam.dao;

import com.exam.entity.Vcomment;

public interface VcommentDao {
	// Ìí¼ÓÆÀÂÛ
	public int addVcomment(Vcomment comment) throws Exception;

	// É¾³ıÆÀÂÛ
	public int deleteVcomment(Vcomment comment) throws Exception;
}
