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
	function checkall(){
			var f=document.getElementById("name").value;
			var g=document.getElementById("cmn").value;
		
			if(f==""){
			alert("科目名不能为空！");
			return false;
			}
			if(g==""){
			alert("科目内容不能为空！")
			return false;
			}
			return true;
		}
   </script>
	</head>
	<body>
		
		
					<div class="col-xs-9">
						<div class="page-header">
							<h1><i class="fa fa-list-ul"></i> 添加科目</h1>
						</div>
						<div class="page-content row">

							<form id="field-add-form" style="margin-top:40px;"  action="addSubject.action" method="post" onsubmit="return checkall()">
								
								<div class="form-line form-name" style="display: block;">
									<span class="form-label"><span class="warning-label"></span>科目名：</span>
										<input type="text" class="df-input-narrow" id="name" name="subject.sname" size="100px"><span class="form-message"></span>
									<br>
								</div>
								<div class="form-line form-name" style="display: block;">
									<span class="form-label"><span class="warning-label"></span>简介：</span>
										<textarea id="cmn" name="subject.sdetail" rows="10" cols="100"></textarea>
									<br>
								</div>
								
								
								<div class="form-line">
									<input id="btn-save" value="确认添加" type="submit" class="df-submit">
								</div>
							</form>
							

						</div>
					</div>
				</div>
			</div>
		</div>

		
		<!-- Slider Ends -->

		<!-- Javascript files -->
		<!-- jQuery -->
		<script type="text/javascript" src="resources/js/jquery/jquery-1.9.0.min.js"></script>
		<!-- Bootstrap JS -->
		<script type="text/javascript" src="resources/bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="resources/chart/raphael-min.js"></script>
		<script type="text/javascript" src="resources/chart/morris.js"></script>
		<script type="text/javascript" src="resources/js/add-field.js"></script>
		
	</body>
</html>