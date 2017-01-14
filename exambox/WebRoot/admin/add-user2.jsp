<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title></title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>


<script type="text/javascript">
		
		function checkyhnc(name){
			var getyhxm=document.getElementById("name").value;
			var tsobj = document.getElementById("errorname");
			var reg=/^[\u4e00-\u9fa5]+$/;
			if(!reg.test(getyhxm)){
				tsobj.className="cw";
				tsobj.innerHTML="请输入汉字";
				return false;
			}
			else{
				$.ajax({
				type : "post",
				data : "student.uname=" +name,
				url : "adminaddAJAXStudent.action",
				datatype : "html",
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
		else{
			$.ajax({
				type : "post",
				data : "student.loginname=" +loginname,
				url : "adminaddAJAXStudent.action",
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
				tsobj.innerHTML="文件已选择";
				return true;
			}else{
				tsobj.className="cw";	
				tsobj.innerHTML="文件未选择";
				return false;
			}
		}
		function checkAll(){
			zk();
			var name=document.getElementById("name").value;
			var loginname = document.getElementById("loginname").value;
			if(checkpicture()&&checkpwd()&&checkemail()&&checkphone()&&checkyhnc(name)&&checkyhm(loginname)){
				return true;
			}
			else {
			
			return false;}
		}
		function zk(){
		var name=document.getElementById("name").value;
		var loginname = document.getElementById("loginname").value;
		checkpicture();checkpwd();checkemail();checkphone();checkyhnc(name);checkyhm(loginname);
		}
		
		</script>
		
	<style type="text/css">
	.cg{
		color:#55DD88;
		font-size: 15px;
		margin-left: 10px;
		margin-top: 5px;
	}
	
	.cw{
		color:red;
		font-size: 15px;
		margin-left: 10px;
		margin-top: 5px;
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
<div class="panel admin-panel">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>添加用户</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="adminaddStudent.action" enctype="multipart/form-data" method="post" onsubmit="return checkAll()">  
      <div class="form-group">
        <div class="label">
          <label>真实姓名</label>
        </div>
        <div class="field">
          <input type="text"  class="input w50"  name="student.uname" id="name"  onblur="checkyhnc(this.value)"/>
          <div class="tips" id="errorname"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>用户名</label>
        </div>
        <div class="field">
          <input type="text"  class="input w50" name="student.loginname" id="loginname" onblur="checkyhm(this.value)"/>
          <div class="tips" id="errorloginname"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>密码</label>
        </div>
        <div class="field">
          <input type="password"  class="input w50" name="student.loginpass" onblur="checkpwd()" id="password"/>
          <div class="tips" id="errorpass"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>头像：</label>
        </div>
        <div class="field">
          <input type="file"  name="image" class="input tips" style="width:25%; float:left;"   data-toggle="hover" data-place="right" data-image="" onchange="checkpicture()" id="picture"/>   
          <div class="tipss" id="errorpicture"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>邮箱：</label>
        </div>
        <div class="field">
          <input type="text"  name="student.email" class="input w50" onblur="checkemail()" id="email"/>         
          <div class="tipss" id="erroremail"></div>
        </div>
      </div>
      
       <div class="form-group">
        <div class="label">
          <label>电话</label>
        </div>
        <div class="field">
          <input type="text"  class="input w50"  name="student.phone" id="phone" onblur="checkphone()"/>
          <div class="tips" id="errorphone"></div>
        </div>
      </div>
      <div class="clear"></div>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
        </div>
      </div>
    </form>
  </div>
</div>

</body></html>