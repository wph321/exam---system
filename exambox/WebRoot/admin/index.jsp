<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  <script type="text/javascript" src="js/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<link href="css/login2.css" rel="stylesheet" type="text/css" />
</head>
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
		function ajax1(){
		var flag = 1;
			$.ajax({
			        type:"post",
			        url:"checkDJStudent.action",
			        data:"student.loginname="+getyhm,
			        dataType:"html",
			        success:function(resu){
			            if(resu=='该用户已被冻结'){
			            	alert("进入到了冻结")
							tsobj.innerHTML="由于违规操作，你已被管理员冻结，";
			            	flag = 0;
			            }else{
			            	alert("进入到了没有冻结")
			            }
			        	},
				        error:function(){
				        	alert("执行ajax的方法错误了");
				        }
				        });
				if(flag==0){
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
			
			if(checkLoginname()&&checkpwd()){
				
				return true;
			}else{
				
				return false;
			}
		}
	
		function checkNameCf(){
			$.ajax({
			        type:"post",
			        url:"addCollect.action",
			        data:"question.id="+qid+"&quetype.id="+qtid,
			        dataType:"html",
			        success:function(resu){
			            alert(resu);
			            if(resu=='已取消收藏！'){
			            	 document.getElementById("shoucang").src="images/out.gif";
			            }else{
				            document.getElementById("shoucang").src="images/on.gif";
			            }
			            	
			        },
			        error:function(){
			        	alert("执行ajax的方法错误了");
			        }
			        });
		  }
		</script>
<body>
<h1>管理员用户登录</h1>

<div class="login" style="margin-top:50px;">
    
    <div class="header" align="center">
        <span style="font-size: 22px;line-height: 50px;color: black; "><a class="switch_btn_focus" id="switch_qlogin" href="javascript:void(0);" tabindex="7">管理员登录</a>
        </span>
    </div>    
  
    
    <div class="web_qr_login" id="web_qr_login" style="display: block; height: 260px;">    
	
    <div class="web_login"><form action="loginAdmin.action" name="form2" id="regUser" accept-charset="utf-8"   method="post">
	      <input type="hidden" name="to" value="reg"/>
		      <div  style="color: red;"><span id="errorzk" style="font-size: 15px; text-align: center; margin-left: 80px">
 				<c:choose>
    				<c:when test="${ not empty error}">${error}</c:when>
    				<c:otherwise>${djerror}</c:otherwise>
	   			</c:choose>
 				</span></div>
        <ul class="reg_form" id="reg-ul">
        		<div id="userCue" class="cue"></div>
                <li>
                	
                    <label for="user"  class="input-tips2">用户名：</label>
                    <div class="inputOuter2">
                        <input type="text" id="user" name="admin.loginname" maxlength="16" class="inputstyle2"/>
                    </div>
                    
                </li>
                
                <li>
                <label for="passwd" class="input-tips2">密码：</label>
                    <div class="inputOuter2">
                        <input type="password" id="passwd"  name="admin.loginpass" maxlength="16" class="inputstyle2"/>
                    </div>
              
                
                
                <li>
                    <div class="inputArea">
                        <input type="submit" id="reg"  style="width:150px;margin-top:10px;margin-left:85px;" class="button_blue" value="登 录"/>
                    </div>
                    
                </li><div class="cl"></div>
            </ul></form>
           
    
    </div>
   
    
    </div>
    <!--注册end-->
</div>
</body></html>