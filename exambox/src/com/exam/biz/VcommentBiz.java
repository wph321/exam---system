package com.exam.biz;

import com.exam.entity.Vcomment;

public interface VcommentBiz {

// �������
public int addVcomment(Vcomment comment) throws Exception;

// ɾ������
public int deleteVcomment(Vcomment comment) throws Exception;
}