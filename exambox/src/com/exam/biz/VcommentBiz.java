package com.exam.biz;

import com.exam.entity.Vcomment;

public interface VcommentBiz {

// Ìí¼ÓÆÀÂÛ
public int addVcomment(Vcomment comment) throws Exception;

// É¾³ıÆÀÂÛ
public int deleteVcomment(Vcomment comment) throws Exception;
}