<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
+ request.getServerName() + ":" + request.getServerPort()
+ path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>">
		<!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame
		Remove this if you use the .htaccess -->
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="utf-8"><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title>考试格子</title>
		<meta name="viewport"
		content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="keywords" content="">
		<link rel="shortcut icon" href="<%=basePath%>resources/images/favicon.ico" />
		<link href="resources/bootstrap/css/bootstrap-huan.css"
		rel="stylesheet">
		<link href="resources/font-awesome/css/font-awesome.min.css"
		rel="stylesheet">
		<link href="resources/css/style.css" rel="stylesheet">
		
		<style>
			.question-number{
				color: #5cb85c;
				font-weight: bolder;
				display: inline-block;
				width: 30px;
				text-align: center;
			}
			
			.question-number-2{
				color: #5bc0de;
				font-weight: bolder;
				display: inline-block;
				width: 30px;
				text-align: center;
			}
			.question-number-3{
				color: #d9534f;
				font-weight: bolder;
				display: inline-block;
				width: 30px;
				text-align: center;
			}
			
			a.join-practice-btn{
				margin:0;
				margin-left:20px;
			}
			
			.content ul.question-list-knowledge{
				padding:8px 20px;
			}
			
			.knowledge-title{
				background-color:#EEE;
				padding:2px 10px;
				margin-bottom:20px;
				cursor:pointer;
				border:1px solid #FFF;
				border-radius:4px;
			}
			
			.knowledge-title-name{
				margin-left:8px;
			}
			
			.point-name{
				width:200px;
				display:inline-block;
			}
		</style>
	
	<script src="js/jquery.js"></script>
	<script type="text/javascript">
		function ajaxSign(){
			
			$.ajax({
				type : "post",
				url : "addSign.action",
				datatype :"text",
				success : function(resu) {
					if(resu=="已经签到"){
						alert("您已经签到，一天一次");
					}
					else{
						alert("签到成功");
					}
				},
				error : function() {
					
				}
				})
		}
	</script>
	</head>

	<body >
		<header>
			<div class="container">
				<div class="row">
					<div class="col-xs-5">
						<div class="logo">
							<h1><a href="index.jsp"  target="_parent"><img alt="" src="resources/images/logo.png"></a></h1>
						</div>
					</div>
					<div class="col-xs-7" id="login-info">
						<c:choose>
							<c:when test="${not empty user && user.type == '1'}">
								<div id="login-info-user">
									&nbsp;
									<a href="student/usercenter.jsp"  id="system-info-account" target="_blank">${user.uname}</a>
									<span>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;</span>
									<a href="javascript:ajaxSign()">签到</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="exitStudent.action"  target="_parent"><i class="fa fa-sign-out"></i> 退出</a>&nbsp;&nbsp;
								</div>
							</c:when>
							<c:otherwise>
								<a class="btn btn-primary" href="register.jsp"  target="_parent">用户注册</a>
								<a class="btn btn-success" href="login.jsp"  target="_parent">登录</a>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</header>
		<!-- Navigation bar starts -->

		<div class="navbar bs-docs-nav" role="banner" >
			<div class="container">
				<nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation"  >
					<ul class="nav navbar-nav">
						<li>
							<a href="index.jsp"  target="_parent"><i class="fa fa-edit"></i>主页</a>
						</li>
						<li>
							<a href="findExamdate.action"  target="_parent"><i class="fa fa-home"></i>近期考试</a>
						</li>
						
						
						<li>
							<a href="student/usercenter.jsp"  target="_parent"><i class="fa fa-dashboard"></i>会员中心</a>
						</li>
						<li>
							<a href="findBySubVideo.action"  target="_parent"><i class="fa fa-cogs"></i>教学视频</a>
						</li>
						<li>
							<a href="findByPageNew.action?nowpage=1"  target="_parent"><i class="fa fa-dashboard"></i>新闻公告</a>
						</li>
						<li>
							<a href="findByPageForum?nowpage=1"  target="_parent"><i class="fa fa-dashboard"></i>社区论坛</a>
						</li>
					</ul>
				</nav>
			</div>
		</div>
		<!-- Navigation bar ends -->
		<!-- jQuery -->
		<script type="text/javascript"
		src="resources/js/jquery/jquery-1.9.0.min.js"></script>
		<!-- Bootstrap JS -->
		<script type="text/javascript"
		src="resources/bootstrap/js/bootstrap.min.js"></script>
		<script>
		$(function(){
			bindQuestionKnowledage();
			var result = checkBrowser();
			if (!result){
				alert("请至少更新浏览器版本至IE8或以上版本");
			}
		});
		function checkBrowser() {
			var browser = navigator.appName;
			var b_version = navigator.appVersion;
			var version = b_version.split(";");
			var trim_Version = version[1].replace(/[ ]/g, "");
			if (browser == "Microsoft Internet Explorer" && trim_Version == "MSIE7.0") {
				return false;
			} else if (browser == "Microsoft Internet Explorer"
					&& trim_Version == "MSIE6.0") {
				return false;
			} else
				return true;
		}
		function bindQuestionKnowledage(){
			$(".knowledge-title").click(function(){
				var ul = $(this).parent().find(".question-list-knowledge");
				
				if(ul.is(":visible")){
					$(this).find(".fa-chevron-down").hide();
					$(this).find(".fa-chevron-up").show();
					
					$(".question-list-knowledge").slideUp();
					
				}else{
					$(".fa-chevron-down").hide();
					$(".fa-chevron-up").show();
					
					$(this).find(".fa-chevron-up").hide();
					$(this).find(".fa-chevron-down").show();
					
					$(".question-list-knowledge").slideUp();
					ul.slideDown();
					
				}
				
				
			});
		}
		</script>
	</body>
</html>
