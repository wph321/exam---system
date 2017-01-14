<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
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
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/in_out.css" />
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
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.0.min.js"></script>


	</head>

	<body>
		<div  style="height:135px;">
		<iframe src="head.jsp"  width="100%" height="100%" scrolling="no"  frameborder="0" ></iframe>
		</div>

		<!-- Slider starts -->

		<div class="full-slider">
			<!-- Slider (Flex Slider) -->

			<div class="container" style="height: 650px;">
				<div class="row">
					<div class="col-md-12">
						<div class="flexslider">
							<div class="flex-caption">
								<!-- 新闻列表 -->
								
								     <ul>
<h1>最新公告</h1>
<c:forEach var="news" items="${newList}">
<li style="font-size: 18px;margin-bottom: 10px;"><span>${news.ndate}</span><a href="findoneNew.action?n.nid=${news.nid}" style="color: white" >&nbsp;&nbsp;&nbsp;${news.ntitle}</a></li>
</c:forEach>
 </ul>
  <div  style="width:365px; position: absolute;top: 500px;left: 300px; ">
  <!-- 页码 -->
  <a href="findByPageNew.action?nowpage=1"><button id="firstnewspage" value="1">首页</button></a>&nbsp;&nbsp;&nbsp;
   <a id="jianpagea" href="findByPageNew.action?nowpage=${nowpage-1}" ><button id="lastnewspage">上一页</button></a>
    <a id="jiapagea" href="findByPageNew.action?nowpage=${nowpage+1}" ><button id="nextpage" >下一页</button></a>&nbsp;&nbsp;&nbsp;
     <a href="show_news_action.action?nowpage=${allPage}"><button id="lastpage" value="${allPage}">末页</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     <span id="nowpage"><s:property value="nowpage" /></span><span>/${allPage}</span>
  </div>
  </div>
								
							</div>
						</div> 
					</div>
				</div>
			</div>
		
	</body>
</html>
