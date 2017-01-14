<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		.pagelist {padding:10px 0; text-align:center;}
		.pagelist span,.pagelist a{ background-color: white;border-radius:3px; border:1px solid #dfdfdf;display:inline-block; padding:5px 12px;}
		.pagelist a{ margin:0 3px;}
		.pagelist span.current{ background:#09F; color:#FFF; border-color:#09F; margin:0 2px;}
		.pagelist a:hover{background:#09F; color:#FFF; border-color:#09F; }
		.pagelist label{ padding-left:15px; color:#999;}
		.pagelist label b{color:red; font-weight:normal; margin:0 3px;}
		</style>
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.0.min.js"></script>
		<script type="text/javascript">
		function findVideoBySubject(){
		var subjectid = $("#field-input-select").value;
			$.ajax({
				data:"sid="+subjectid,
				type:"post",
				url:"findVideoBySubjectSubject.action",
				success:function(){
					alert(${allPageBySubject}+${videoListBySubject});
				},
				})
			
		}
		</script>


	</head>

	<body>
		<div  style="height:135px;">
		<iframe src="head.jsp"  width="100%" height="100%" scrolling="no"  frameborder="0" ></iframe>
	</div>
		<!-- Slider starts -->

		<div class="full-slider">
			<!-- Slider (Flex Slider) -->

			<div class="container" style="height: 2400px;">
				<div class="row">
					<div class="col-md-12">
						<div class="flexslider">
							<div class="flex-caption">
								<!-- 新闻列表 -->
								
								     <ul style="">
<h1>教学视频</h1>
<!-- form -->
<form action="findBySubVideo.action" method="post">
<span class="form-label" style="font-size:18px; font-weight:bold"><span class="warning-label"></span>科目：</span>
									<select id="field-input-select" class="df-input-narrow" name="sid" onchange="findVideoBySubject(this.value)">
										<option value="0" style="color: black;">--请选择--</option>
										<c:forEach items="${subjectList}" var="item">
											<option name="sid" value="${item.id}" style="color: black; height: 32px;width: 120px"<c:if test="${item.id == sid }" >selected='selected' </c:if> >${item.sname }</option>
										</c:forEach>
									</select>
	
	<input style="color: black" type="submit" value="查询">
</form>	

<br/><br/>
  <c:forEach items="${videoListBySubject}" var="shiping" varStatus="num">
<li style="font-size: 25px;margin-bottom: 10px; list-style: none; width: 800px; border: 2px;border-color: red;"><span><img src="${pageContext.request.contextPath}/video/images/${shiping.image}" alt="" width="150px" height="120px"/>&nbsp;&nbsp;&nbsp;<a href="findVideoByIdplay.action?vid=${shiping.id}" style="color: white" >${shiping.vname}</a></span>
	<br/><br/>
	<c:if test="${num.index  lt 8 }">
		<c:if test="${shiping.vex.length() >= 90 }">
	<p>${shiping.vex.substring(0,90)}...</p>
	</c:if>
		<c:if test="${shiping.vex.length() < 90 }">
		<p>${shiping.vex}</p>
		</c:if>
	</c:if>
	<br/>
	<hr/>
</li>

  </c:forEach>
 </ul>
  <div class="pagelist" style="width:365px; margin-left: 300px;" align="center">
  
  <!-- 页码 -->
  <a href="findBySubVideo.action?videoNowPage=1"  style="color: black;">首页</a>
  <a href="findBySubVideo.action?videoNowPage=${videoNowPage-1 }" style="color: black;">上一页</a>
  <a href="findBySubVideo.action?videoNowPage=${videoNowPage+1 }" style="color: black;">下一页</a>
  <a href="findBySubVideo.action?videoNowPage=${allPageBySubject}" style="color: black;">末页</a>
  <span style="color: black;">第${videoNowPage}/${allPageBySubject}页</span>
  </div>
  </div>
								
							</div>
						</div> 
					</div>
				</div>
			</div>
		
	</body>
</html>
