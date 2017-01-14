package com.exam.biz.impl;

import java.util.List;

import com.exam.biz.AdminBiz;
import com.exam.dao.AdminDao;
import com.exam.entity.Admin;

public class AdminBizImpl implements AdminBiz{
	private AdminDao adminDao; 
	@Override
	public void addAdmin(Admin admin) throws Exception {
		// TODO Auto-generated method stub
		adminDao.addAdmin(admin);
	}

	@Override
	public void deleteAdmin(Admin admin) throws Exception {
		// TODO Auto-generated method stub
		adminDao.deleteAdmin(admin);
	}

	@Override
	public void updateAdmin(Admin admin) throws Exception {
		// TODO Auto-generated method stub
		adminDao.updateAdmin(admin);
	}
	
	@Override
	public Admin login(String loginName, String loginPwd) throws Exception {
		// TODO Auto-generated method stub
		return adminDao.login(loginName, loginPwd);
	}
	
	@Override
	public List<Admin> findAdmins() throws Exception {
		// TODO Auto-generated method stub
		return adminDao.findAdmins();
	}
	public AdminDao getAdminDao() {
		return adminDao;
	}
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public int findPage() throws Exception {
		// TODO Auto-generated method stub
		return adminDao.findPage();
	}

	@Override
	public List<Admin> findAdminByPage(Integer nowpage) throws Exception {
		// TODO Auto-generated method stub
		return adminDao.findAdminByPage(nowpage);
	}

	@Override
	public int findPageByAdmin(Admin admin) throws Exception {
		// TODO Auto-generated method stub
		return adminDao.findPageByAdmin(admin);
	}

	@Override
	public List<Admin> findAdminByPageAndAdmin(Integer nowpage, Admin admin)
			throws Exception {
		// TODO Auto-generated method stub
		return adminDao.findAdminByPageAndAdmin(nowpage, admin);
	}

	@Override
	public Admin findAminByloginName(String loginName) throws Exception {
		// TODO Auto-generated method stub
		return adminDao.findAminByloginName(loginName);
	}

	@Override
	public Admin findAdminByAName(String AName) throws Exception {
		// TODO Auto-generated method stub
		return adminDao.findAdminByAName(AName);
	}
}
