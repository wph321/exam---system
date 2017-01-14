<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%-- <%@taglib uri="spring.tld" prefix="spring"%> --%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
	<head>
    	<base href="<%=basePath%>">
    
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="utf-8"><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title>用户管理</title>
		<meta name="keywords" content="">
		<link rel="shortcut icon" href="<%=basePath%>resources/images/favicon.ico" />
		<link href="resources/bootstrap/css/bootstrap-huan.css" rel="stylesheet">
		<link href="resources/font-awesome/css/font-awesome.min.css" rel="stylesheet">
		<link href="resources/css/style.css" rel="stylesheet">
		
		<link href="resources/css/exam.css" rel="stylesheet">
		<link href="resources/chart/morris.css" rel="stylesheet">
		<style type="text/css">
			.disable-btn, .enable-btn{
				text-decoration: underline;
			}
			.disable-btn, .enable-btn{
				cursor:pointer;
			}
		</style>
	</head>
	<body>
		<header>
			<div class="container">
				<div class="row">
					<div class="col-xs-5">
						<div class="logo">
							<h1><a href="#">网站管理系统</a></h1>
							<div class="hmeta">
								专注互联网在线考试解决方案
							</div>
						</div>
					</div>
					<div class="col-xs-7" id="login-info">
						<c:choose>
							<c:when test="${not empty sessionScope.user}">
								<div id="login-info-user">
									
									<a href="user-detail/${sessionScope.user}" id="system-info-account" target="_blank">${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}</a>
									<span>|</span>
									<a href="exitAdmin.action"><i class="fa fa-sign-out"></i> 退出</a>
								</div>
							</c:when>
							<c:otherwise>
								<a class="btn btn-primary" href="register.jsp">用户注册</a>
								<a class="btn btn-success" href="login.jsp">登录</a>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</header>
		<!-- Navigation bar starts -->

		<div class="navbar bs-docs-nav" role="banner">
			<div class="container">
				<nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">
					<ul class="nav navbar-nav">
						<li>
							<a href="#"><i class="fa fa-home"></i>网站首页</a>
						</li>
						<li>
							<a href="admin/question-list.jsp"><i class="fa fa-edit"></i>试题管理</a>
						</li>

						<li>
							<a href="admin/exampaper-list.jsp"><i class="fa fa-file-text-o"></i>试卷管理</a>
						</li>
						<li>
							<a href="admin/user-list.jsp"><i class="fa fa-user"></i>会员管理</a>
						</li>
						<li>
							<a href="admin/field-list-1.jsp"><i class="fa fa-cloud"></i>题库管理</a>
						</li>
						<li class="active">
							<a href="admin/sys-backup.jsp"><i class="fa fa-cogs"></i>网站设置</a>
						</li>
					</ul>
				</nav>
			</div>
		</div>

		<!-- Navigation bar ends -->

		<!-- Slider starts -->

		<div>
			<!-- Slider (Flex Slider) -->

			<div class="container" style="min-height:500px;">

				<div class="row">
					<div class="col-xs-3">
						<ul class="nav default-sidenav">
							<li>
								<a href="admin/sys-backup.jsp"> <i class="fa fa-bar-chart-o"></i> 数据备份 </a>
							</li>
							<li class="active">
								<a> <i class="fa fa-bar-chart-o"></i> 管理员列表 </a>
							</li>
							<li>
								<a href="admin/add-admin.jsp"> <i class="fa fa-list-ul"></i> 添加管理员 </a>
							</li>
							<li>
								<a href="admin/field-list-1.jsp"> <i class="fa fa-bar-chart-o"></i> 题库列表 </a>
							</li>
						</ul>

					</div>
					<div class="col-xs-9">
						<div class="page-header">
							<h1><i class="fa fa-bar-chart-o"></i> 管理员列表 </h1>
						</div>
						<div class="page-content row">

							
							<div id="question-list">
								<table class="table-striped table">
									<thead>
										<tr>
											<td></td><td>ID</td><td>姓名</td><td>邮箱</td><td></td><td>电话</td><td>注册时间</td>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${adminList}" var="admin">
											<tr>
												<td>
												<input type="checkbox" value="${admin.id}">
												</td><td>${admin.id}</td><td>${admin.aname }</td><td>${admin.email}</td><td>超级管理员</td><td>${admin.phone}</td><td><fmt:formatDate value="${item.create_date }" pattern="yyyy-MM-dd"/></td>

											</tr>
										</c:forEach>
										

									</tbody><tfoot></tfoot>
								</table>
							</div>
							<div id="page-link-content">
								<ul class="pagination pagination-sm">${pageStr}</ul>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>

		<footer>
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="copy">
							<p>
								Exam++ Copyright © <a href="http://www.examxx.net/" target="_blank">Exam++</a> - <a href="." target="_blank">主页</a> | <a href="http://www.examxx.net/" target="_blank">关于我们</a> | <a href="http://www.examxx.net/" target="_blank">FAQ</a> | <a href="http://www.examxx.net/" target="_blank">联系我们</a>
							</p>
						</div>
					</div>
				</div>

			</div>

		</footer>

		<!-- Slider Ends -->

		<!-- Javascript files -->
		<!-- jQuery -->
		<script type="text/javascript" src="resources/js/jquery/jquery-1.9.0.min.js"></script>
		<script type="text/javascript" src="resources/js/all.js"></script>
		<!-- Bootstrap JS -->
		<script type="text/javascript" src="resources/bootstrap/js/bootstrap.min.js"></script>
		<script>
			$(function(){
				$(".disable-btn").click(function(){
					var message = "确定要禁用该用户吗？";
					var answer = confirm(message);
					if(!answer){
						return false;
					}
					
					jQuery.ajax({
						headers : {
							'Accept' : 'application/json',
							'Content-Type' : 'application/json'
						},
						type : "GET",
						url : 'admin/disable-user/' + $(this).data("id"),
						success : function(message,tst,jqXHR) {
							if(!util.checkSessionOut(jqXHR))return false;
							if (message.result == "success") {
								util.success("操作成功！", function(){
									 window.location.reload();
								});
							} else {
								util.error(message.result);
							}
						},
						error : function(jqXHR, textStatus) {
							util.error("操作失败请稍后尝试");
						}
					});
					
				});
				
				$(".enable-btn").click(function(){
					var message = "确定要启用该用户吗？";
					var answer = confirm(message);
					if(!answer){
						return false;
					}
					jQuery.ajax({
						headers : {
							'Accept' : 'application/json',
							'Content-Type' : 'application/json'
						},
						type : "GET",
						url : 'admin/enable-user/' + $(this).data("id"),
						success : function(message,tst,jqXHR) {
							if(!util.checkSessionOut(jqXHR))return false;
							if (message.result == "success") {
								util.success("操作成功！", function(){
									 window.location.reload();
								});
							} else {
								util.error(message.result);
							}
						},
						error : function(jqXHR, textStatus) {
							util.error("操作失败请稍后尝试");
						}
					});
					
				});
			});
		
		</script>
	</body>
</html>