<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Exam++</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="keywords" content="">
<link rel="shortcut icon"
	href="<%=basePath%>resources/images/favicon.ico" />
<link href="resources/bootstrap/css/bootstrap-huan.css" rel="stylesheet">
<link href="resources/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<link href="resources/css/style.css" rel="stylesheet">
<link href="resources/css/exam.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="css/findq.css">
<script type="text/javascript">
	//换页
	function changepage(page) {
		document.getElementById("nowpage").value = page;
		document.questionListform.submit();
	}
</script>
<style>
.question-number {
	color: #5cb85c;
	font-weight: bolder;
	display: inline-block;
	width: 30px;
	text-align: center;
}

.question-number-2 {
	color: #5bc0de;
	font-weight: bolder;
	display: inline-block;
	width: 30px;
	text-align: center;
}

.question-number-3 {
	color: #d9534f;
	font-weight: bolder;
	display: inline-block;
	width: 30px;
	text-align: center;
}

a.join-practice-btn {
	margin: 0;
	margin-left: 20px;
}

.content ul.question-list-knowledge {
	padding: 8px 20px;
}

.knowledge-title {
	background-color: #EEE;
	padding: 2px 10px;
	margin-bottom: 20px;
	cursor: pointer;
	border: 1px solid #FFF;
	border-radius: 4px;
}

.knowledge-title-name {
	margin-left: 8px;
}

.point-name {
	width: 200px;
	display: inline-block;
}
</style>
<style type="text/css">
.pagelist {
	padding: 10px 0;
	text-align: center;
}

.pagelist span, .pagelist a {
	border-radius: 3px;
	border: 1px solid #dfdfdf;
	display: inline-block;
	padding: 5px 12px;
}

.pagelist a {
	margin: 0 3px;
}

.pagelist span.current {
	background: #09F;
	color: #FFF;
	border-color: #09F;
	margin: 0 2px;
}

.pagelist a:hover {
	background: #09F;
	color: #FFF;
	border-color: #09F;
}

.pagelist label {
	padding-left: 15px;
	color: #999;
}

.pagelist label b {
	color: red;
	font-weight: normal;
	margin: 0 3px;
}
</style>
</head>

<body>
	<header>
		<div  style="height:135px;">
		<iframe src="head.jsp"  width="100%" height="100%" scrolling="no"  frameborder="0" ></iframe>
		</div>

	<!-- Slider starts -->


	<div class="content" style="margin-bottom: 100px;">
		<div class="expand-bk-content" id="bk-conent-exampaper"
			style="width: 960px; padding:20px; height: auto;margin-left:320px;margin-top:40px">
			<h3  style="color: black;">>>为您找到关于<b style="color: red">"${mohu }"</b>的问题共有<b style="color: red">${fn:length(questionList) }</b>条,如下:</h3><br>
			<c:forEach var="question" items="${questionList}" varStatus="num">
				<div
					style="padding:6px 10px;<c:if test="${num.index%2 eq 0}">background:  #eceaea;</c:if>">
					<div style="font-size:18px;">
					
					<c:if test="${question.qtitle.length() >= 90 }">
						<a href="noteQuestion.action?qid=${question.id}">${question.qtitle.substring(0,90)}...</a>
					</c:if>
					<c:if test="${question.qtitle.length() < 90 }">
						<a href="noteQuestion.action?qid=${question.id}">${question.qtitle}</a>
					</c:if>
						
					</div>
					<div style="color:black;font-size:15px;">${question.optiona}${question.optionb}${question.optionc}${question.optiond}&nbsp;&nbsp;&nbsp;&nbsp;${question.knowpint}&nbsp;&nbsp;&nbsp;&nbsp;${question.result}</div>
					<div style="color:green;font-size:14px;">题目详情：${question.subject.sname}&nbsp;&nbsp;&nbsp;&nbsp;${question.quetype.tname}</div>
					<hr>
				</div>

			</c:forEach>
		</div>

		<form name="questionListform" action="searchfindByPageQuestion.action" method="post">
			
		<input type="hidden" id="allPage" name="allPage" value="${allPage}" />
		<input type="hidden" id="mohu" name="mohu" value="${mohu}" /> <input
			type="hidden" id="nowpage" name="nowpage" value="${i}" />
			
		</form>

		<div class="pagelist">
			<a href="javascript:changepage(${1})">首页</a> <a
				href="javascript:changepage(${nowpage-1})">上一页</a>
			<c:forEach var="i" begin="1" end="${allPage}">
				<a href="javascript:changepage(${i})">${i}</a>
			</c:forEach>
			<a href="javascript:changepage(${nowpage+1})">下一页</a><a
				href="javascript:changepage(${allPage})">尾页</a>
		</div>
	</div>

	<!-- Slider Ends -->

	<!-- Javascript files -->
</body>
</html>
