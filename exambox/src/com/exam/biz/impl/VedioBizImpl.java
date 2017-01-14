package com.exam.biz.impl;

import java.util.List;

import com.exam.biz.VedioBiz;
import com.exam.dao.VideoDao;
import com.exam.entity.Vedio;

public class VedioBizImpl implements VedioBiz {

	private VideoDao vedioDao;
	
	public VideoDao getVedioDao() {
		return vedioDao;
	}

	public void setVedioDao(VideoDao vedioDao) {
		this.vedioDao = vedioDao;
	}

	@Override
	public List<Vedio> findVideoByTypeId(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return vedioDao.findVideoByTypeId(id);
	}

	@Override
	public Vedio findVideoByVideoId(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return vedioDao.findVideoByVideoId(id);
	}

	@Override
	public void addVideo(Vedio vedio) throws Exception {
		// TODO Auto-generated method stub
		vedioDao.addVideo(vedio);
	}

	@Override
	public void deleteVedioById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		vedioDao.deleteVedioById(id);
	}

	@Override
	public void updatevedio(Vedio vedio) throws Exception {
		// TODO Auto-generated method stub
		vedioDao.updatevedio(vedio);
	}

	@Override
	public List<Vedio> findVedioByPage(Integer page) throws Exception {
		// TODO Auto-generated method stub
		return vedioDao.findVedioByPage(page);
	}

	@Override
	public List<Vedio> findVedioByPageAndType(Integer page, Integer id)
			throws Exception {
		// TODO Auto-generated method stub
		return vedioDao.findVedioByPageAndType(page, id);
	}

	@Override
	public Integer findAllPage() throws Exception {
		// TODO Auto-generated method stub
		return vedioDao.findAllPage();
	}

	@Override
	public Integer findAllpageByType(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return vedioDao.findAllpageByType(id);
	}

}
