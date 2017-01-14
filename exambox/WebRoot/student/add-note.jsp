<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
		<title>Exam++</title>
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
		<link href="resources/css/exam.css" rel="stylesheet" type="text/css">
		<link  rel="stylesheet" href="css/note.css" />
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

	</head>

	<body>
		<div  style="height:135px;">
		<iframe src="head.jsp"  width="100%" height="100%" scrolling="no"  frameborder="0" ></iframe>
	</div>

		<!-- Slider starts -->

		
		<div class="content" style="margin-bottom: 100px;">

		<div class="expand-bk-content" id="bk-conent-exampaper" style="width: 960px; padding:20px; height: auto;margin-left:320px;margin-top:40px" >
								<c:choose>
								
									<c:when test="${question.quetype.id eq 1 }">
										<ul style="margin-left: 20px" id="exampaper-body">
											<li style="font-size: 20px"><b>(单选题)${question.id}.${question.qtitle }</b></li>
											<span class="muti-opt-item">
											<label class="que-opt-no">A.${question.optiona}</label> <br>
											</span> <span class="muti-opt-item"> 
											<label class="que-opt-no">B.${question.optionb}</label> <br>
											</span> <span class="muti-opt-item"> 
											<label class="que-opt-no">C.${question.optionc}</label> <br>
											</span> <span class="muti-opt-item"> 
											<label class="que-opt-no">D.${question.optiond}</label> <br>
											</span> <span class="form-message"></span>
											<li>答案：${question.result}</li>
											<li>解析：${question.analysis}</li>
										</ul>
									</c:when>
									
									<c:when test="${question.quetype.id eq 2}">
										<ul style="margin-left: 20px" id="exampaper-body">
											<li style="font-size: 20px"><b>(多选题)${question.id}.${question.qtitle }</b></li>
											<span class="muti-opt-item">
											
											<label class="que-opt-no">A.${question.optiona}</label> <br>
											</span> <span class="muti-opt-item"> 
											<label class="que-opt-no">B.${question.optionb}</label> <br>
											</span> <span class="muti-opt-item"> 
											<label class="que-opt-no">C.${question.optionc}</label> <br>
											</span> <span class="muti-opt-item"> 
											 <label class="que-opt-no">D.${question.optiond}</label> <br>
											</span> <span class="form-message"></span>
											<li>答案：${question.result}</li>
											<li>解析：${question.analysis}</li>
										</ul>
									</c:when>
									
									<c:when test="${question.quetype.id eq 3}">
										<ul style="margin-left: 20px" id="exampaper-body">
											<li style="font-size: 20px"><b>(判断题)${question.id}.${question.qtitle }</b></li>
											<br><br>
											<span class="muti-opt-item">
											<c:if test="${question.result eq 'T' }"><label class="que-opt-no">答案：正确</label></c:if>  <br>
											</span> <span class="muti-opt-item"> 
											<c:if test="${question.result eq 'F' }"><label class="que-opt-no">答案：错误</label></c:if>  <br>
											</span> 
											<li>解析：${question.analysis}</li>
										</ul>
									</c:when>
								</c:choose>
</div>
<div id="content" style="width: 960px; height: auto;margin-left:320px;margin-top:40px">
	<div class="wrap">
		<div class="comment">
			<div class="head-face">
				<img src="images/${user.image }" />
				<p>${user.uname }</p>
			</div>
			<div class="content">
			<form action="addNote.action" method="post">
			   <input type="hidden" name="note.quetype.id" value="${question.quetype.id}" />
			   <input type="hidden" name="note.question.id" value="${question.id}" />
			   <input type="hidden" name="note.student.id" value="${user.id}"/>
				<div class="cont-box">
					<textarea class="text" placeholder="请输入..." name="note.ncontent"></textarea>
				</div>
				
			    <div class="submit-btn"><input type="submit"  value="提交笔记" /></div>
				</form>
			</div>
		</div>
		<div id="info-show">
			<ul>
			<c:forEach var="no" items="${question.notes}">
			
				<li>
					<div class="head-face">
					<img src="images/${no.student.image }" />
					</div>
					<div class="reply-cont">
					<p class="username">${no.student.uname }&nbsp;&nbsp;&nbsp;&nbsp;<c:if test="${user.id eq no.student.id }"><a href="deleteNote.action?type=delete1&note.id=${no.id}&note.question.id=${question.id}">删除</a></c:if></p>
					<p class="comment-body">${no.ncontent }</p>
					<p class="comment-footer">${no.time }　</p>
					</div>
				</li>
				</c:forEach>
				
			</ul>
		</div>
	</div>
</div>

		</div>
		

		<!-- Slider Ends -->

		<!-- Javascript files -->
		</body>
</html>
