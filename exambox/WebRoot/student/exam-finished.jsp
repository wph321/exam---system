<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
		<title>Exam++</title>
		
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="keywords" content="">
		<link rel="shortcut icon" href="<%=basePath%>resources/images/favicon.ico" />
		<link href="resources/bootstrap/css/bootstrap-huan.css" rel="stylesheet">
		<link href="resources/font-awesome/css/font-awesome.min.css" rel="stylesheet">
		<link href="resources/css/style.css" rel="stylesheet">
		
		<link href="resources/css/exam.css" rel="stylesheet">
		<link href="resources/chart/morris.css" rel="stylesheet">
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
							<li class="active">
								<a href="student/finish-exam"> <i class="fa fa-bar-chart-o"></i> 分析报告 </a>

							</li>
							<!-- 
							<li>
								<a href="student/exam-report"> <i class="fa fa-file-text"></i> 详细解答 </a>
							</li>
							 -->
						</ul>

					</div>
					<div class="col-xs-9">
						<div class="page-header">
							<h1><i class="fa fa-bar-chart-o"></i> 分析报告 </h1>
						</div>
						<div class="page-content row">

							<div id="graph-base" class="col-xs-8" style="height:200px;width: 200px;">

							</div>
							<div  class="col-xs-7" style="margin-top: 24px;">
								
								<div class="form-line add-role">
									<span class="form-label">考试名称：</span>
									<span> ${grade.type } </span>
								</div>
								
								<div class="form-line add-role">
									<span class="form-label">总题目：</span>
									<span class="label label-info"> ${total } </span>
								</div>
								<div class="form-line exam-report-correct">
									<span class="form-label">正确题目：</span>
									<span class="label label-success"> ${right } </span>
								</div>
								<div class="form-line exam-report-error">
									<span class="form-label">错误题目：</span>
									<span class="label label-danger"> ${wrong } </span>
								</div>

							</div>

						</div>
						<div class="page-content row">
							<table class="table table-bordered" style="margin-top: 25px;">
								<thead>
									<tr>
										<th>科目</th>
										<th>答题情况</th>
										<th>正确率</th>
									</tr>
								</thead>
								<tbody>
									
										<tr>
											<td>${grade.subject.sname }</td>
											<td>${right } / ${total }</td>
											<td>
												<fmt:formatNumber value="${right/total}" type="number" pattern="0.00%"/>
											</td>
										</tr>
									
									
								</tbody>
							</table>
						</div>
						<div class="page-content row">
							<div id="question-navi" style="margin: 24px 0;width: 100%;">

								<div id="question-navi-content" style="padding: 15px 12px;">
								答对：<span style="background-color: #5cb85c;" class="question-navi-item">&nbsp;</span>
								答错：<a class="navi-item-error question-navi-item" >&nbsp;</a>
								未完成：<a class="pressed  question-navi-item">&nbsp;</a>
								<br>
								<c:forEach var="c" items="${cardList }" varStatus="num">
											<c:choose>
												<c:when test="${c.result eq 'F' }">
													<a class="question-navi-item navi-item-error" href="noteQuestion.action?qid=${c.question.id}" target="_blank">${c.qno }</a>
												</c:when>
												<c:when test="${empty c.uanswer }">
													<a class="pressed  question-navi-item" href="noteQuestion.action?qid=${c.question.id}" target="_blank">${c.qno }</a>
												</c:when>
												<c:otherwise>
													<a style="background-color: #5cb85c;" class="question-navi-item"  href="noteQuestion.action?qid=${c.question.id}" target="_blank">${c.qno }</a>
												</c:otherwise>
												
											</c:choose>
										
								</c:forEach>
								
								</div>
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
		<script type="text/javascript" src="resources/js/jquery/jquery-1.9.0.min.js"></script>
		<!-- Bootstrap JS -->
		<script type="text/javascript" src="resources/bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="resources/chart/raphael-min.js"></script>
		<script type="text/javascript" src="resources/chart/morris.js"></script>
		<script type="text/javascript" src="resources/js/exam-finished.js"></script>
	</body>
</html>
