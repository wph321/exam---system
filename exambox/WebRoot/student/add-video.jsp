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
		
		
		
		<style type="text/css">
	.cg{
		color:green;
		font-size: 12px;
	}
	
	.cw{
		color:red;
		font-size: 12px;
	}
	.gx{border:#060 1px solid;
	
	background:url(images/li_ok.gif) no-repeat #AEFBD0;
	padding-left:18px;
	
	}
	.wgx{
	border:#900 1px solid;
	background: no-repeat url(images/li_err.gif) #FCC;
	padding-left:18px;
	}
	</style>
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
							<li >
								<a href="student/usercenter.jsp"> <i class="fa fa-dashboard"></i> 用户中心 </a>
							</li>
							<li >
								<a > <i class="fa fa-cogs"></i> 基本资料 </a>
							</li>
							<li class="active">
								<a > <i class="fa fa-wrench"></i> 修改密码 </a>
							</li>
							<li>
								<a href="findError.action?student.id=${user.id}"> <i class="fa fa-bar-chart-o"></i> 历史错题 </a>
							</li>
							<li>
								<a href="findByPageAndStudentNote.action?student.id=${user.id}&nowpage=1"> <i class="fa fa-history"></i> 我的笔记 </a>
							</li>
							<li >
								<a href="findCollect.action"><i class="fa fa-dashboard"></i>我的收藏</a>
							</li>
							<li>
								<a href="findGrade.action"> <i class="fa fa-history"></i> 分数管理</a>
							</li>

						</ul>

					</div>
					<div class="panel admin-panel">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>增加新闻</strong></div>
  <div class="body-content">
    <form name="form1" method="post" class="form-x" action="addVideoUpload.action" enctype="multipart/form-data">  
	
	 <div class="form-group">
        <div class="label">
          <label>科目：</label>
        </div>
        <div class="field">
            <select name="cid" class="input" style="width:200px; line-height:17px;" onchange="changesearch()">
				<c:forEach var="item" items="${subjectList }">
					<option value="${item.id}">${item.sname }</option>
				</c:forEach>
            </select>
          </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>标题：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="" name="title" data-validate="required:请输入标题" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>图片：</label>
        </div>
        <div class="field">
          <input type="file" id="url1" name="pic" class="input tips" style="width:25%; float:left;"  value=""  data-toggle="hover" data-place="right" data-image=""  data-validate="required:请选择图片"/>
          <div class="tipss">海报</div>
        </div>
      </div>     
       <div class="form-group">
        <div class="label">
          <label>视频：</label>
        </div>
        <div class="field">
          <input type="file" id="url2" name="shiping" class="input tips" style="width:25%; float:left;"  value=""  data-toggle="hover" data-place="right" data-image="" data-validate="required:请选择视频"/>
          <div class="tipss"></div>
        </div>
      </div>
      
       
      
      <div class="form-group">
        <div class="label">
          <label>内容：</label>
        </div>
        <div class="field">
          <textarea id="textarea" name="content" class="input" style="height:450px; border:1px solid #ddd;" data-validate="required:请输入影片简介"></textarea>
          <div class="tips"></div>
        </div>
      </div>
     
      <div class="clear"></div>
      
     
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
        <input class="button bg-main icon-check-square-o" type="submit" value="提交"/>
        </div>
      </div>
    </form>
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
		<script type="text/javascript" src="resources/js/all.js"></script>
		<!-- Bootstrap JS -->
		<script type="text/javascript" src="resources/bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="resources/chart/raphael-min.js"></script>
		<script type="text/javascript" src="resources/chart/morris.js"></script>
		<script type="text/javascript" src="resources/js/pwd-change.js"></script>
	</body>
</html>