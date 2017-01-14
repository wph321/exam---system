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
		<title>用户中心</title>
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="keywords" content="">
		<link rel="shortcut icon" href="<%=basePath%>resources/images/favicon.ico" />
		<link href="resources/bootstrap/css/bootstrap-huan.css" rel="stylesheet">
		<link href="resources/font-awesome/css/font-awesome.min.css" rel="stylesheet">
		<link href="resources/css/style.css" rel="stylesheet">
		
		<link href="resources/css/exam.css" rel="stylesheet">
		<link href="resources/chart/morris.css" rel="stylesheet">
		<!--[if lte IE 8]>
			<script type="text/javascript" src="resources/chartjs/excanvas.js"></script>
		<![endif]-->
		<style>
			.table-striped a{
				text-decoration: underline;
			}
			
			.span-success{
				color:#5cb85c;
				font-weight:bolder;
			}
			
			.span-danger{
				color:#d9534f;
				font-weight:bolder;
			}
			
			.span-info{
				color:#5bc0de;
				font-weight:bolder;
			}
			
			h6{
				font-weight:bold !important; 
			}
			
			.radar-legend li span {
				display: block;
				position: absolute;
				left: 0;
				top: 0;
				width: 20px;
				height: 100%;
				border-radius: 5px;
			}
			
			.radar-legend li {
				display: block;
				padding-left: 30px;
				position: relative;
				margin-bottom: 4px;
				border-radius: 5px;
				padding: 2px 8px 2px 28px;
				font-size: 14px;
				cursor: default;
				-webkit-transition: background-color 200ms ease-in-out;
				-moz-transition: background-color 200ms ease-in-out;
				-o-transition: background-color 200ms ease-in-out;
				transition: background-color 200ms ease-in-out;
			}				
		</style>
	</head>
	<body>
		<div  style="height:135px;">
		<iframe src="head.jsp"  width="100%" height="100%" scrolling="no"  frameborder="0" ></iframe>
		</div>
		

		<!-- Slider starts -->
		<c:if test="${not empty ts }">${ts }<%session.removeAttribute("ts"); %></c:if>

		<div>
			<!-- Slider (Flex Slider) -->

			<div class="container" style="min-height:500px;">

				<div class="row">
					<div class="col-xs-3">
						<ul class="nav default-sidenav">
							<li class="active">
								<a> <i class="fa fa-dashboard"></i> 用户中心 </a>
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
							<li>
								<a href="findCollect.action"> <i class="fa fa-dashboard"></i> 我的收藏</a>
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
							<h1><i class="fa fa-dashboard"></i> 用户中心</h1>
						</div>
						<div class="page-content row">
							<div class="col-xs-4">
								<h5>个人信息</h5>
								<br>
								<div>
									<span >头&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;像：&nbsp;&nbsp;&nbsp;&nbsp;</span>
									<span> <img style="border-radius: 50%;" alt="" src="images/${user.image}"  width="75" height="75"/></span>
								</div>
								<br>
								<div>
									<span >姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：&nbsp;&nbsp;&nbsp;&nbsp;</span>
									<span> ${user.uname}</span>
								</div>
								<br>
								<div>
									<span >邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：&nbsp;&nbsp;&nbsp;&nbsp;</span>
									<span> ${user.email} </span>
								</div>
								<br>
								<div>
									<span >电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：&nbsp;&nbsp;&nbsp;&nbsp;</span>
									<span> ${user.phone} </span>
								</div>
								<br>
									<span >上次登录：&nbsp;&nbsp;&nbsp;&nbsp;</span>
									<span> <fmt:formatDate value="${lastLoginTime}" pattern="yyyy-MM-dd"/> </span>
								</div>
								
							</div>
							
						</div>
						<div class="page-content row">
							<div class="col-xs-12" id="radar-div">
								<h6>知识掌握情况</h6>
								
								<div class="page-content row">
									<div class="col-xs-8">
										<canvas id="canvas" height="450" width="450"></canvas>
										<p>此统计数据不包括简答、论述、分析等主观题</p>
									</div>
									<div class="col-xs-4" id="radar-legend">
									</div>
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
		<script type="text/javascript" src="resources/chartjs/Chart.min.js"></script>
		
		<!-- Bootstrap JS -->
		<script type="text/javascript" src="resources/bootstrap/js/bootstrap.min.js"></script>
		
		<script type="text/javascript">
		var radarChartData = {
				labels: [${labels}],
				datasets: [
					{
						label: "完成率",
						fillColor: "rgba(220,220,220,0.2)",
						strokeColor: "rgba(220,220,220,1)",
						pointColor: "rgba(220,220,220,1)",
						pointStrokeColor: "#fff",
						pointHighlightFill: "#fff",
						pointHighlightStroke: "rgba(220,220,220,1)",
						data: [${finishrate}]
					},
					{
						label: "正确率",
						fillColor: "rgba(151,187,205,0.2)",
						strokeColor: "rgba(64, 170, 83,1)",
						pointColor: "rgba(151,187,205,1)",
						pointStrokeColor: "#fff",
						pointHighlightFill: "#fff",
						pointHighlightStroke: "rgba(151,187,205,1)",
						data: [${correctrate}]
					}
				]
			};

			window.onload = function(){
			
				
				var ctx = document.getElementById("canvas").getContext("2d");
				window.myBar = new Chart(ctx).Radar(radarChartData, {
					responsive : false,
					scaleShowLabels : true
				});
				
				var html = window.myBar.generateLegend();
				
				$("#radar-legend").append(html);
			};
		</script>()
	</body>
</html>