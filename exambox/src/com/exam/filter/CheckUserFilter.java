package com.exam.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

public class CheckUserFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request =(HttpServletRequest)arg0;
		HttpServletResponse response=(HttpServletResponse)arg1;
		HttpSession session=request.getSession();
		
		String uri=request.getRequestURI();
		String[] us=uri.split("/");
		if(us.length>2){
			if(!"index.jsp".equals((us[us.length-1]))){
				if("admin".equals((us[us.length-2]))){
					if(session.getAttribute("admin")==null){
						response.sendRedirect("index.jsp");
						return;
					}
				}
			}
			
		
		}
			
		uri=uri.substring(uri.lastIndexOf('/')+1);
		if("usercenter.jsp".equals(uri)||"setting.jsp".equals(uri)||"change-password.jsp".equals(uri)||"addNote.action".equals(uri)||"findExamdate.action".equals(uri)||"addForum.action".equals(uri)||"addReply.action".equals(uri)){
			if(session.getAttribute("user")==null){
				String path = request.getContextPath();
				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/login.jsp";
				response.sendRedirect(basePath);
				return;
			}
		}


		arg2.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
