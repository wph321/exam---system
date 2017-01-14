package com.exam.biz;

import java.util.List;

import com.exam.entity.Admin;

public interface AdminBiz {
	public void addAdmin(Admin admin) throws Exception;
	public void deleteAdmin(Admin admin) throws Exception;
	public void updateAdmin(Admin admin) throws Exception;
	public Admin login(String loginName, String loginPwd) throws Exception ;
	public List<Admin> findAdmins() throws Exception;
	
	public Admin findAminByloginName(String loginName) throws Exception;
	public Admin findAdminByAName(String AName) throws Exception;
	
	public int findPage()throws Exception;
	public List<Admin> findAdminByPage(Integer nowpage)throws Exception;
	//分页查学生的
	public int findPageByAdmin(Admin admin)throws Exception;
	public List<Admin> findAdminByPageAndAdmin(Integer nowpage,Admin admin)throws Exception;
}
