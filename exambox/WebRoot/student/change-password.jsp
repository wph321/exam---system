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
		<title>试题管理</title>
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="keywords" content="">
		<link rel="shortcut icon" href="<%=basePath%>resources/images/favicon.ico" />
		<link href="resources/bootstrap/css/bootstrap-huan.css" rel="stylesheet">
		<link href="resources/font-awesome/css/font-awesome.min.css" rel="stylesheet">
		<link href="resources/css/style.css" rel="stylesheet">
		
		<link href="resources/css/exam.css" rel="stylesheet">
		<link href="resources/chart/morris.css" rel="stylesheet">
		
		<script type="text/javascript">
		
		
		function checkOldPass(oldpass){
			var tsobj=document.getElementById("error_oldpass");
				$.ajax({
				type : "post",
				data : "student.loginpass="+oldpass,
				url : "checkPassStudent.action",
				datatype : "text",
				success : function(resu) {
				
					if(resu=="正确"){
						tsobj.innerHTML="原密码正确";
						tsobj.className="cg";
					}
					else{
						tsobj.innerHTML="原密码错误";
						tsobj.className="cw";
					}
				},
				error : function() {
					alert("出错了");
				}
				});	
				if(tsobj.innerHTML=="原密码错误"){
					return false;
				}else{
					return true;
				}
		}  
		
		
		function checkpwd(){
			var tsobj=document.getElementById("errorpass");
			var pwdstr=document.getElementById("password").value;
			if(pwdstr.length>16||pwdstr.length<6){
				tsobj.className="cw";
				tsobj.innerHTML="密码长度为6-16位";
				return false;
			}else{
				tsobj.innerHTML="输入正确";
				tsobj.className="cg";
				return true;
			}
		}
		
		function checkrepass(newpass){
			
			var tsobj=document.getElementById("error_repass");
			
			var pwdstr=newpass;
			var pwdstr2=document.getElementById("password").value;
			
			if(pwdstr!=pwdstr2){
				tsobj.innerHTML="两次密码不一致";
				return false;
			}else{
				var aa = pwdstr.toString();
				if(aa.length!=0){
				tsobj.innerHTML="输入正确";
				tsobj.className="cg";
					return true;
				}else{
					tsobj.innerHTML="确认密码不能为空";
					return false;	
				}
			}
				
		}
		function checkAll(){
		var newpass = document.getElementById("repass").value;
		var oldpass = document.getElementById("oldpass").value;
		checkrepass(newpass);checkpwd();checkOldPass(oldpass);
		
			if(checkrepass(newpass)&&checkpwd()&&checkOldPass(oldpass)){
					return true;
			}else{
				return false;
			}
		
		}
		</script>
		
		<style type="text/css">
	.cg{
		color:green;
		font-size: 12px;
	}
	
	.cw{
		color:red;
		font-size: 12px;
	}
	.gx{border:#060 1px solid;
	
	background:url(images/li_ok.gif) no-repeat #AEFBD0;
	padding-left:18px;
	
	}
	.wgx{
	border:#900 1px solid;
	background: no-repeat url(images/li_err.gif) #FCC;
	padding-left:18px;
	}
	</style>
	</head>
	<body>
		<div  style="height:135px;">
		<iframe src="head.jsp"  width="100%" height="100%" scrolling="no"  frameborder="0" ></iframe>
	</div>

		<!-- Slider starts -->

		<div>
			<!-- Slider (Flex Slider) -->

			<div class="container" style="min-height:500px;">

				<div class="row">
					<div class="col-xs-3">
						<ul class="nav default-sidenav">
							<li >
								<a href="student/usercenter.jsp"> <i class="fa fa-dashboard"></i> 用户中心 </a>
							</li>
							<li >
								<a > <i class="fa fa-cogs"></i> 基本资料 </a>
							</li>
							<li class="active">
								<a > <i class="fa fa-wrench"></i> 修改密码 </a>
							</li>
							<li>
								<a href="findError.action?student.id=${user.id}"> <i class="fa fa-bar-chart-o"></i> 历史错题 </a>
							</li>
							<li>
								<a href="findByPageAndStudentNote.action?student.id=${user.id}&nowpage=1"> <i class="fa fa-history"></i> 我的笔记 </a>
							</li>
							<li >
								<a href="findCollect.action"><i class="fa fa-dashboard"></i>我的收藏</a>
							</li>
							<li>
								<a href="findByUserAndPageForum.action?student.id=${user.id}&nowpage=1"> <i class="fa fa-dashboard"></i> 我的帖子</a>
							</li>
							<li>
								<a href="findGrade.action"> <i class="fa fa-history"></i> 分数管理</a>
							</li>

						</ul>

					</div>
					<div class="col-xs-9">
						<div class="page-header">
							<h1><i class="fa fa-wrench"></i> 修改密码 </h1>
						</div>
						<div class="page-content row">
							<form class="form-horizontal"  action="changepwdStudent.action" style="margin-top:40px;" method="post" onsubmit="return checkAll()">
									<input type="hidden" name="student.id" value="${user.id}"/>
									<!-- 原先的密码 -->
									<div class="form-group ">
									
										<label class="control-label col-md-2" >原密码</label>
										<div class="col-md-5">
											<input type="password"  name="beforepwd" class="form-control" onblur="checkOldPass(this.value)" id="oldpass">
											<span class="form-message" id="error_oldpass"></span>
										</div>
									</div>
									<!-- password -->
							<c:if test="${not empty ts }">${ts }<%session.removeAttribute("ts"); %></c:if>		
									<div class="form-group form-password">
									
										<label class="control-label col-md-2" >新密码</label>
										<div class="col-md-5">
											<input type="password" class="form-control" name="student.loginpass" id="password" onblur="checkpwd(this.value)">
											<span class="form-message" id="errorpass"></span>
										</div>
									</div>
									
									<!-- password-confirm -->
									<div class="form-group form-password-confirm">
										<label class="control-label col-md-2" for="password">确认新密码</label>
										<div class="col-md-5">
											<input type="password" class="form-control" id="password-confirm" id="repass" onblur="checkrepass(this.value)" >
											<span class="form-message" id="error_repass"></span>
										</div>
									</div>

									<!-- Buttons -->
									<div class="form-group">
										<!-- Buttons -->
										<div class="col-md-5 col-md-offset-2">
										<input type="submit" value="确认提交" class="btn"/>
											
										
										</div>
									</div>
								</form>
							
							
							

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
		<script type="text/javascript" src="resources/chart/raphael-min.js"></script>
		<script type="text/javascript" src="resources/chart/morris.js"></script>
		<script type="text/javascript" src="resources/js/pwd-change.js"></script>
	</body>
</html>