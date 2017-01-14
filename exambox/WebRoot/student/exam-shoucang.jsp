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
		<title>练习历史</title>
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="keywords" content="">
		<link rel="shortcut icon" href="<%=basePath%>resources/images/favicon.ico" />
		<link href="resources/bootstrap/css/bootstrap-huan.css" rel="stylesheet">
		<link href="resources/font-awesome/css/font-awesome.min.css" rel="stylesheet">
		<link href="resources/css/style.css" rel="stylesheet">
		
		<link href="resources/css/exam.css" rel="stylesheet">
		<link href="resources/chart/morris.css" rel="stylesheet">
		<style type="text/css">
		.pagelist {padding:10px 0; text-align:center;}
		.pagelist span,.pagelist a{ border-radius:3px; border:1px solid #dfdfdf;display:inline-block; padding:5px 12px;}
		.pagelist a{ margin:0 3px;}
		.pagelist span.current{ background:#09F; color:#FFF; border-color:#09F; margin:0 2px;}
		.pagelist a:hover{background:#09F; color:#FFF; border-color:#09F; }
		.pagelist label{ padding-left:15px; color:#999;}
		.pagelist label b{color:red; font-weight:normal; margin:0 3px;}
		</style>
		<script type="text/javascript">
			function findCollectType(id){
				
				location.href="findByPageCollect.action?subject.id="+id+"&nowpage="+'1';
			}
			function pageform(page){
			
				var subid = document.getElementById("zk").value;
				location.href="findByPageCollect.action?subject.id="+subid+"&nowpage="+page;
			}
		</script>
		
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
		
	
							<li>
								<a href="student/usercenter.jsp"> <i class="fa fa-dashboard"></i> 用户中心 </a>
							</li>
							<li >
								<a href="student/setting.jsp"> <i class="fa fa-cogs"></i> 基本资料 </a>
							</li>
							<li>
								<a href="student/change-password.jsp"> <i class="fa fa-wrench"></i> 修改密码 </a>
							</li>
							<li>
								<a href="findError.action?student.id=${user.id}"> <i class="fa fa-bar-chart-o"></i> 历史错题 </a>
							</li>
							<li>
								<a href="findByPageAndStudentNote.action?student.id=${user.id}&nowpage=1"> <i class="fa fa-history"></i> 我的笔记 </a>
							</li>
							<li class="active">
								<a ><i class="fa fa-dashboard"></i>我的收藏</a>
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
							<h1><i class="fa fa-history"></i>收藏</h1>  
							<h10>
							<select onchange="javascript:findCollectType(this.value)" id="zk">
								<option value="-1" >所有</option>
								<c:forEach var="sub" items="${subjectList}">
									<option value="${sub.id}" 
									<c:if test="${collectsubid!=null and sub.id==collectsubid}">
									 selected="selected"
									</c:if>
									>${sub.sname}</option>
								</c:forEach>
							</select>
						</h10>
						</div>
						<div class="page-content row">
							<div id="question-list">
								<table class="table-striped table">
									<thead>
										<tr>
											<td width="80px">试题类型</td><td width="120px">科目名称</td><td align="center" width="150px">题目</td><td align="center" width="66px">操作</td><td width="80px"></td>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${collectList}" var="collect">
											<tr>
												<td>
													${collect.quetype.tname}
												</td>
												<td>
													${collect.question.subject.sname}
												</td>
												<td align="center">
													<c:if test="${collect.question.qtitle.length() >= 18 }">
													${collect.question.qtitle.substring(0,18)}...
													</c:if>
													<c:if test="${collect.question.qtitle.length() <= 18 }">
													${collect.question.qtitle}
													</c:if>
												</td>
												<td align="center"><a href="deleteCollect.action?collect.id=${collect.id}">删除</a></td>
												<td align="center"><a href="noteQuestion.action?qid=${collect.question.id}">转到此题</a></td>
												
												
											</tr>
										</c:forEach>
											
									</tbody><tfoot></tfoot>
								
								</table>
									<div class="pagelist"> 
									<span style="color: #0099FF">第${nowpage }/${pages }页</span>
									<a href="javascript:pageform('${backpage}')">上一页</a> 
									<c:forEach  varStatus="i" begin="1" end="${pages}">
									<a href="javascript:pageform('${i.index}')"> ${i.index}</a>
									</c:forEach>
									<a href="javascript:pageform('${nextpage}')">下一页</a><a href="javascript:pageform('${pages}')">尾页</a> 									
								
									</div>
							</div>
							<div id="page-link-content">
								<ul class="pagination pagination-sm"></ul>
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