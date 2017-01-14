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
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<meta charset="utf-8"><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<!--
		<link rel="stylesheet" type="text/css" href="styles.css">
		-->
		<title>Exam++</title>
		<meta name="viewport"
		content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="keywords" content="">
		<link rel="shortcut icon" href="<%=basePath%>resources/images/favicon.ico" />
		<link href="resources/bootstrap/css/bootstrap-huan.css" rel="stylesheet">
		<link href="resources/font-awesome/css/font-awesome.min.css"
		rel="stylesheet">
		<link href="resources/css/style.css" rel="stylesheet">
		<!-- Javascript files -->
		<style type="text/css">
			.form-group {
				margin-bottom: 5px;
				height: 59px;
			}
		</style>
		<script src="js/jquery.js"></script>
		<script src="js/pintuer.js"></script>
		<script type="text/javascript">
		
		function checkyhnc(name){
			var getyhxm=document.getElementById("yhnc").value;
			var tsobj = document.getElementById("errornc");
			var reg=/^[\u4e00-\u9fa5]+$/;
			if(!reg.test(getyhxm)){
				tsobj.className="cw";
				tsobj.innerHTML="请输入汉字";
				return false;
			}
			else{
				$.ajax({
				type : "post",
				data : "student.uname="+name+"&student.loginname="+'1',
				url : "addAJAXStudent.action",
				datatype : "text",
				success : function(resu) {
				
					if(resu=="该姓名已经存在，请重新填写"||resu=="都错"){
						tsobj.innerHTML="该姓名已经存在，请重新填写";
						tsobj.className="cw";
					}
					else{
						tsobj.innerHTML="输入正确";
						tsobj.className="cg";
					}
				},
				error : function() {
					
				}
				});	
				if(tsobj.innerHTML=="该姓名已经存在，请重新填写"){
					return false;
				}else{
					return true;
				}
			}  
		}
		
		function checkyhm(loginname){
		
			var tsobj=document.getElementById("errorloginname");
			var getyhm=document.getElementById("loginname").value;
			var firstchar=getyhm.charAt(0);
			var firstreg=/^\d?$/;
		if(getyhm==""){
			tsobj.className="cw";
			tsobj.innerHTML="不能为空";
			return false;
			}
		if(firstreg.test(firstchar)){
			tsobj.className="cw";
			tsobj.innerHTML="首字母不能为数字";
			return false;
			}
		var reg=/^\w+$/;
		if(!reg.test(getyhm)){
			tsobj.className="cw";
			tsobj.innerHTML="只能包含数字、字母、下划线";
			return false;
			}
		if(getyhm.length>16||getyhm.length<4){
				tsobj.className="cw";
				tsobj.innerHTML="用户名为4-16字符";
				return false;
		}else{
			$.ajax({
				type : "post",
				data : "student.loginname="+loginname+"&student.uname="+'1',
				url : "addAJAXStudent.action",
				datatype : "html",
				success : function(resu) {
				
					if(resu=="用户名重复，请重新填写"||resu=="都错"){
						tsobj.innerHTML="用户名重复，请重新填写";
						tsobj.className="cw";
					}
					else{
						tsobj.innerHTML="输入正确";
						tsobj.className="cg";
					}
				},
				error : function() {
				
				}
				});	
				if(tsobj.innerHTML=="用户名重复，请重新填写"){
					return false;
				}else{
					return true;
				}
			}
		}
	
		function checkemail(){
			var tsobj = document.getElementById("erroremail");
			var emailstr = document.getElementById("email").value;
			var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
 			if (filter.test(emailstr)) {
 				tsobj.innerHTML = "格式正确";
 				tsobj.className = "cg";
 				return true;
 			}
 			else {
 				tsobj.className="cw";
 				tsobj.innerHTML = "输入的邮箱格式错误";
 				return false;
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
		
		function checkrepass(){
			
			var tsobj=document.getElementById("error_repass");
			
			var pwdstr=document.getElementById("repass").value;
		
			var pwdstr2=document.getElementById("password").value;
			
			if(pwdstr!=pwdstr2){
				tsobj.innerHTML="密码不一致";
				return false;
			}else{
				var aa = pwdstr.toString();
				if(aa.length!=0){
				tsobj.innerHTML="输入正确";
				tsobj.className="cg";
					return true;
				}else{
					tsobj.className="cw";	
					tsobj.innerHTML="确认密码不能为空";
					return false;	
				}
			}
				
		}
		
		function checkphone(){
			var tsobj=document.getElementById("errorphone");
			var reg=/^1\d{10}$/;
			var phonestr=document.getElementById("phone").value;
			if(!reg.test(phonestr)){
				tsobj.className="cw";	
				tsobj.innerHTML="格式不对";
				return false;
			}
			else{
				tsobj.innerHTML="输入正确";
				tsobj.className="cg";
				return true;
			}
		}
		
		function checkpicture(){	
			var tsobj = document.getElementById("errorpicture");
			var picturestr = document.getElementById("picture").value;
			if(picturestr!=""){
				tsobj.className="cg";	
				tsobj.innerHTML="";
				
				return true;
			}else{
				tsobj.className="cw";	
				tsobj.innerHTML="文件未选择";
				return false;
			}
		}
		function checktiaokuan(){
			var tsobj = document.getElementById("tiaokuan");
			if(document.getElementById("inlineCheckbox1").checked){
				tsobj.className="gx";
				tsobj.innerHTML = "已勾选";
				return "true";
			}else{
				
				tsobj.className="wgx";
				tsobj.innerHTML = "未勾选，需要勾选";
				return "false";
			}
		}
		function checkall(){
			zk();
			var name=document.getElementById("yhnc").value;
			var loginname = document.getElementById("loginname").value;
			var yzm = document.getElementById("inputyzm").value;
			if(checkyzm(yzm)&&checktiaokuan()&&checkpicture()&&checkpwd()&&checkemail()&&checkphone()&&checkyhnc(name)&&checkyhm(loginname)&&checkrepass()){
				return true;
			}
			else {
				alert("输入格式错误");
			return false;}
		}
		function zk(){
		var name=document.getElementById("yhnc").value;
		var loginname = document.getElementById("loginname").value;
		var yzm = document.getElementById("inputyzm").value;
		checkyzm(yzm);checkpicture();checktiaokuan();checkpwd();checkemail();checkphone();checkyhnc(name);checkyhm(loginname);checkrepass();
		}
		
		
		function checkyzm(yzm){
		var tsobj=document.getElementById("erroryzm");
		var tof=0;
		$.ajax({
				type :"post",
				data :"yzm=" + yzm,
				url :"yzmRegisterStudent.action",
				datatype: "html",
				success: function(resu){
					if(resu=="输入错误"){
						tsobj.innerHTML="验证码输入错误";
						tof=1;
						return false;
					}else{
						tsobj.className="cg";	
						tsobj.innerHTML="输入正确";
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
	
		var i = 10;
		function changeImg(){
			document.getElementById("yzm").src="yzm.jsp?date="+i++;
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

		<div class="content" style="margin-bottom: 100px;">

			<div class="container">
				<div class="row">

					<div class="col-md-12">
						<div class="lrform">
							<h5>注册账号</h5>
							<span class="form-message"></span>
							<div class="form">
								<!-- Register form (not working)-->
								<form class="form-horizontal" action="addStudent.action" enctype="multipart/form-data" method="post"   onsubmit="return checkall()">
									<!-- 用户昵称 -->
									<div class="form-group ">
										<label class="control-label col-md-3" for="name">姓名</label>
										<div class="col-md-9">
											<input type="text" class="form-control" name="student.uname"  onblur="checkyhnc(this.value)" id="yhnc">
											<span class="form-message" id="errornc"></span>
										</div>

									</div>
									
									<!-- Name -->
									<div class="form-group ">
										<label class="control-label col-md-3" for="name">账号</label>
										<div class="col-md-9">
											<input type="text" class="form-control" name="student.loginname" id="loginname" onblur="checkyhm(this.value)" >
											<span class="form-message" id="errorloginname"></span>
										</div>

									</div>
									<!-- Email -->
									<div class="form-group ">
										<label class="control-label col-md-3" for="email">email</label>
										<div class="col-md-9">
											<input type="text" class="form-control" name="student.email" onblur="checkemail()" id="email">
											<span class="form-message" id="erroremail"></span>
										</div>

									</div>
									<!-- password -->
									<div class="form-group ">
										<label class="control-label col-md-3" for="password">密码</label>
										<div class="col-md-9">
											<input type="password" class="form-control" name="student.loginpass" onblur="checkpwd()" id="password">
											<span class="form-message" id="errorpass"></span>
										</div>

									</div>
									<!-- password-confirm -->
									<div class="form-group ">
										<label class="control-label col-md-3" for="password-confirm">确认密码</label>
										<div class="col-md-9">
											<input type="password" class="form-control"  onblur="checkrepass()" id="repass">
											<span class="form-message" id="error_repass"></span>
										</div>

									</div>
									
									<div class="form-group ">
										<label class="control-label col-md-3">电话</label>
									
										<div class="col-md-9">
											<input type="text" class="form-control" name="student.phone" id="phone" onblur="checkphone()">
											<span class="form-message" id="errorphone"></span>
										</div>
									</div>
									
									<div class="form-group ">
										<label class="control-label col-md-3" >头像</label>
										<div class="col-md-9">
											<input type="file" class="form-control" name="image" id="picture" onblur="checkpicture()">
											<span class="form-message" id="errorpicture"></span>
										</div>
									</div>
									<!-- 验证码 -->
									<div class="form-group ">
										<label class="control-label col-md-3" >验证码</label>
										<div class="col-md-9">
											<input type="text" class="form-control" id="inputyzm" onblur="checkyzm(this.value)" >
											<img id="yzm" src="yzm.jsp"  style="float: left;"/><a href="javascript:changeImg()">看不清</a>
											<span class="form-message" id="erroryzm"></span>
										</div>
									</div>
									<!-- 验证码结束 -->
									<!-- Checkbox -->
									<div class="form-group form-confirm">
										<div class="col-md-9 col-md-offset-3">
											<label class="checkbox-inline">
												<input type="checkbox"
												id="inlineCheckbox1" value="agree" onchange="checktiaokuan()">
												<a> 同意Exam++条款</a> </label>
											<span id="tiaokuan" style="margin-top: 5px"> </span>
										</div>
									</div>

									<!-- Buttons -->
									<div class="form-group">
										<!-- Buttons -->
										<div class="col-md-9 col-md-offset-3">
											<button type="submit" class="btn" id="btn-reg" >
												注册账号
											</button>
											<button type="reset" class="btn">
												重置
											</button>
										</div>
									</div>
								</form>
								已有账号? <a href="login2.jsp">直接登录</a>
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
		<!-- jQuery -->
		<script type="text/javascript" src="resources/js/jquery/jquery-1.9.0.min.js"></script>
		<!-- Bootstrap JS -->
		<script type="text/javascript" src="resources/bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="resources/js/register.js"></script>

	</body>
</html>
