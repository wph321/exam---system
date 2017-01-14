<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="utf-8"><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title>Exam++ 登录系统</title>
		
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="keywords" content="">
		<link rel="shortcut icon" href="<%=basePath%>resources/images/favicon.ico" />
		<link href="resources/bootstrap/css/bootstrap-huan.css" rel="stylesheet">
		<link href="resources/font-awesome/css/font-awesome.min.css" rel="stylesheet">
		<link href="resources/css/style.css" rel="stylesheet">
		<style type="text/css">
			.form-group {
				margin-bottom: 5px;
				height: 59px;
			}

		</style>
		
		<script src="../js/jquery-1.9.0.min.js"></script>
<script type="text/javascript">
	function checkLoginname(){
				var tsobj=document.getElementById("errorzk");
				var getyhm=document.getElementById("loginname").value;
				if(getyhm==""){
					tsobj.innerHTML="用户名不能为空";
					return false;
				}
				if(getyhm.length>16||getyhm.length<4){
					tsobj.innerHTML="用户名为4-16字符";
					return false;
				}else{
					return true;
					}
				        
		}
		
	
	function checkpwd(){
	
				var tsobj=document.getElementById("errorzk");
				
				var pwdstr=document.getElementById("password").value;
				if(pwdstr==""){
				alert("进入到了密码为空")
					tsobj.innerHTML="密码不能为空";
					return false;
				}
				if(pwdstr.length>16||pwdstr.length<6){
					tsobj.innerHTML="密码长度为6-16位";
					return false;
				}else{
					
					return true;
				}
	}
		function checkall(){
			var yzm = document.getElementById("myyzm").value;
			
			if(checkLoginname()&&checkpwd()&&checkyzm(yzm)){
				return true;
			}else{
				return false;
			}
		}
		
		var i = 10;
		function changeImg(){
			document.getElementById("yzm").src="yzm.jsp?date="+i++;
		}
		
		function checkyzm(yzm){
		var tsobj=document.getElementById("errorzk");
		var tof=0;
		$.ajax({
				type : "post",
				data : "yzm=" + yzm,
				url : "yzmStudent.action",
				datatype : "html",
				success : function(resu) {
					if(resu=="输入错误"){
						tsobj.innerHTML="验证码输入错误";
						tof=1;
						return false;
					}else{
						tsobj.innerHTML="";
						tof=0;
						return true;
					}
				},
				error : function() {
					
				}
			});	
			if(tsobj.innerHTML=="验证码输入错误"){
				return false;
			}
			return true;
	}
		</script>
		
	</head>
	<body>
		<div  style="height:135px;">
		<iframe src="head.jsp"  width="100%" height="100%" scrolling="no"  frameborder="0" ></iframe>
		</div>

		<div class="content" style="margin-bottom: 100px;">

			<div class="container">
				<div class="row">

					<div class="col-md-12">
						<div class="lrform">
							<h5>登陆Exam++</h5>
							<div class="form">
								<!-- Login form (not working)-->
								<form class="form-horizontal" action="loginStudent.action" method="post"  onsubmit="return checkall()">
									<!-- Username -->
									<div class="form-group">
										<label class="control-label col-md-3" for="username">用户名</label>
										<div class="col-md-9">
											<input type="text" class="form-control" name="student.loginname" id="loginname">
										</div>
									</div>
									<!-- Password -->
									<div class="form-group">
										<label class="control-label col-md-3" for="password">密码</label>
										<div class="col-md-9">
											<input type="password" class="form-control" name="student.loginpass" id="password">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3" for="username">验证码</label>
										<div class="col-md-9" >
											<input type="text" class="form-control" name="uyzm" id="myyzm" style="width:250px" onblur="checkyzm(this.value)"/>
											<img id="yzm" src="yzm.jsp"  style="float: left;"/><a href="javascript:changeImg()">看不清</a>
										</div>
									</div>
									<!-- Buttons -->
									<div class="form-group">
										<!-- Buttons -->
										<div class="col-md-9 col-md-offset-3" >
										
										
										<span class="form-message" id="errorzk" style="font-size: 14px">
											<c:choose>
						    					<c:when test="${ not empty error}">${error}</c:when>
						    					<c:otherwise>${djerror}</c:otherwise>
							   				</c:choose>
											</span><br/><br/>
											<button type="submit" class="btn btn-default">
												登陆
											</button>
											<button type="reset" class="btn btn-default">
												取消
											</button>
											
										</div>
									</div>
								</form>
								没有账号? <a href="register.jsp">注册</a>
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
		<script type="text/javascript"
		src="resources/js/jquery/jquery-1.9.0.min.js"></script>
		<!-- Bootstrap JS -->
		<script type="text/javascript"
		src="resources/bootstrap/js/bootstrap.min.js"></script>

	</body>
</html>