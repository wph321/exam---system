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
			var h=document.getElementById("field-input-select").value;
			if(h==-1){
			    alert("请重新选择！");
			    return false;
			    }
			if(f==""){
			alert("知识点名不能为空！");
			return false;
			}
			if(g==""){
			alert("知识点描述不能为空！")
			return false;
			}
			return true;
		}
   </script>
	</head>
	<body>
		
		<!-- Navigation bar starts -->

					<div class="col-xs-9">
						<div class="page-header">
							<h1><i class="fa fa-list-ul"></i> 添加知识点 </h1>
						</div>
						<div class="page-content row">

							<form  style="margin-top:40px;"  action="addPoint.action" method="post" onsubmit="return checkall()">
								<div class="form-line form-field" style="display: block;">
									<span class="form-label"><span class="warning-label"></span>题库：</span>
									<select id="field-input-select" class="df-input-narrow" name="point.subject.id">
										<option value="-1">--请选择--</option>
										<c:forEach items="${subjectList }" var="item">
											<option value="${item.id}" <c:if test="${item.id eq point.subject.id }" >selected='selected' </c:if>>${item.sname }</option>
										</c:forEach>
									</select>
									<span class="form-message"></span>
									<br>
								</div>
								<div class="form-line form-name" style="display: block;">
									<span class="form-label"><span class="warning-label"></span>知识点名：</span>
										<input type="text" class="df-input-narrow" id="name" name="point.pname"><span class="form-message"></span>
									<br>
								</div>
								<div class="form-line form-memo" style="display: block;">
									<span class="form-label"><span class="warning-label"></span>知识点描述：</span>
									<textarea id="cmn" name="point.pex" rows="10" cols="50"></textarea>
										<br>
								</div>
								
								
								<div class="form-line">
									<input  value="确认添加" type="submit" class="df-submit">
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
		<script type="text/javascript" src="resources/js/add-point.js"></script>
		
	</body>
</html>