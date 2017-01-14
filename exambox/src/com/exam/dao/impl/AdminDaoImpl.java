package com.exam.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.exam.dao.AdminDao;
import com.exam.entity.Admin;
import com.exam.entity.Student;


public class AdminDaoImpl extends HibernateDaoSupport implements AdminDao{

	@Override
	public void addAdmin(Admin admin) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(admin);
	}

	@Override
	public void deleteAdmin(Admin admin) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(admin);
		
	}

	@Override
	public void updateAdmin(Admin admin) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(admin);
	}
	@Override
	public Admin login(String loginName, String loginPwd) throws Exception {
		System.out.println("进入到了底层Dao的方法"+loginName+" "+loginPwd);
		String hqlStr = "from Admin where loginname='"+loginName+"'"+" and loginpass='"+loginPwd+"'";		
		List<Admin> adminList = (List<Admin>) this.getHibernateTemplate().find(hqlStr);
		if(adminList.size()!=0){
			System.out.println("不为0");
			return adminList.get(0);
		}
		return null;
	}

	@Override
	public List<Admin> findAdmins() throws Exception {
		// TODO Auto-generated method stub
		String hqlStr  = "from Admin";
		List<Admin> adminList = (List<Admin>) this.getHibernateTemplate().find(hqlStr);
		return adminList;
	}
	
	
	@Override
	public int findPage() throws Exception {
		// TODO Auto-generated method stub
		String sqlStr="select count(*) from Admin";
		Query query = this.getSession().createQuery(sqlStr);
		List<Object> object =query.list();
		int ts =Integer.valueOf(object.get(0).toString());
		int num =0;
		if(ts%4==0)
			num = ts/4;
		else
			num=ts/4+1;
		return num;
	}
	@Override
	public List<Admin> findAdminByPage(Integer nowpage) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("进入到了Dao根据页数查询Student");
		String hql="from Admin";
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult((nowpage-1)*4);
		query.setMaxResults(4);
		List<Admin> adminList = query.list();
		return adminList;
	}
	@Override
	public int findPageByAdmin(Admin admin) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("进入到按条件查询页数");
		String sqlStr="select count(*) from Admin where 1=1";
		if(admin.getAname()!=null){
			sqlStr+=" and  aname like '%"+admin.getAname()+"%'";
		}
		Query query = this.getSession().createQuery(sqlStr);
		List<Object> object =query.list();
		int ts =Integer.valueOf(object.get(0).toString());
		int num =0;
		if(ts%4==0)
			num = ts/4;
		else
			num=ts/4+1;
		return num;
	}
	@Override
	public List<Admin> findAdminByPageAndAdmin(Integer nowpage, Admin admin)
			throws Exception {
		
		String hql="from Admin where 1=1";
		if(admin.getAname()!=null){
			hql+=" and  aname like '%"+admin.getAname()+"%'";
		}
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult((nowpage-1)*4);
		query.setMaxResults(4);
		List<Admin> adminList = query.list();
		return adminList;
	}

	@Override
	public Admin findAminByloginName(String loginName) throws Exception {
		// TODO Auto-generated method stub
		String hqlStr = "from Admin where loginname='"+loginName+"'";		
		List<Admin> adminList = (List<Admin>) this.getHibernateTemplate().find(hqlStr);
		if(adminList.size()!=0){
			
			return adminList.get(0);
		}
		return null;
	}

	@Override
	public Admin findAdminByAName(String AName) throws Exception {
		
		String hqlStr = "from Admin where aname='"+AName+"'";		
		List<Admin> adminList = (List<Admin>) this.getHibernateTemplate().find(hqlStr);
		if(adminList.size()!=0){
			
			return adminList.get(0);
		}
		return null;
	}
}
