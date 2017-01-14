package com.exam.biz;

import java.util.List;

import com.exam.entity.Vedio;

public interface VedioBiz {

	public List<Vedio> findVideoByTypeId(Integer id)throws Exception;
	
	public Vedio findVideoByVideoId(Integer id)throws Exception;
	
	public void addVideo(Vedio vedio)throws Exception;
	
	public void deleteVedioById(Integer id)throws Exception;
	
	public void updatevedio(Vedio vedio)throws Exception;
	
	public List<Vedio> findVedioByPage(Integer page)throws Exception;
	
	public List<Vedio> findVedioByPageAndType(Integer page,Integer id)throws Exception;
	
	public Integer findAllPage()throws Exception;
	
	public Integer findAllpageByType(Integer id)throws Exception;
}
