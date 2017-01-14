<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
		
		<link href="resources/css/style.css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/in_out.css" />
		<link type="text/css" rel="stylesheet" href="css/lyb.css" />

<!-- 返回顶部调用 begin -->

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/js.js"></script>
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
	</head>

	<body>
		<div  style="height:135px;">
		<iframe src="head.jsp"  width="100%" height="100%" scrolling="no"  frameborder="0" ></iframe>
	</div>
		<!-- header end -->
		
		<div>
 <div id="main" class="l" style="width:965px; margin: 0 auto; ">
		<div class="guestbook">
			<h2 style="color: black"><b>${findoneforum.ftitle }</b></h2>
			<br>
			<div>
						<div style="float: left;"><img style="border-radius: 50%;" src="styles/logo.jpg" alt="" width="75" height="75"></div>
						<div style="width:860px; float: left;margin-left: 15px">
				
						<dl>
							<dt>楼主：${findoneforum.admin.aname }${findoneforum.student.uname } </dt>
							<br>
							<dd class="author">
							<c:if test="${not empty findoneforum.image }">
							<img alt="" src="images/${findoneforum.image }" width="700px;" height="400px;">
							</c:if>
							<h6 style="font-weight:bold; color:#000000; ">${findoneforum.fcontent }</h6></dd>
							<br>
							<div class="timer" style="margin-left: 700px" >${findoneforum.fdate }</div>
						</dl>
				
						</div>
					</div>
				<div class="clear"></div>
					<hr>
			<ul>
					<s:iterator var="r" value="#attr.findoneforum.replies" status="num">
					<li>
					<div>
						<div style="float: left;"><img src="styles/logo.jpg" alt="" width="75" height="75"></div>
						<div style="width:860px;float: left;margin-left: 15px">
				
						<dl>
							<dt id="huise">${num.index+1 }楼 <c:if test="${(not empty r.admin.id && r.admin.id == user.id )||(not empty r.student.id && r.student.id == user.id)}">&nbsp&nbsp&nbsp<a href="deleteReply?reply.rid=${r.rid}&reply.forum.fid=${findoneforum.fid }">删除</a></c:if>
							</dt>
							<br>
							<dd class="author">
							<c:if test="${not empty r.image }">
							<img alt="" src="images/${r.image }" width="700px;" height="400px;">
							</c:if>
							<h3>${r.rcontent }</h3></dd>
							<br>
							<div class="timer" style="margin-left: 680px" >${findoneforum.admin.aname }${findoneforum.student.uname }&nbsp;&nbsp;&nbsp;${r.rdate }</div>
						</dl>
				
						</div>
					</div>
					</li>
					<div class="clear"></div>
					</s:iterator>
					
				</ul>
			<div class="clear"></div>
			<!-- 
			<div class="pager">
				<ul class="clearfix">
					<li><a href="#">上一页</a></li>
					<li class="current">1</li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li> 
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a href="#">下一页</a></li>
				</ul>
			</div>
			 -->
			<div id="reply-box">
				<form action="addReply.action" method="post" enctype="multipart/form-data">
				<input type="hidden" name="<c:if test="${user.type eq '1' }">reply.student.id</c:if><c:if test="${user.type eq '2' }">reply.admin.id</c:if>" value="${user.id }">
				<input type="hidden" name="reply.forum.fid" value="${findoneforum.fid }">
					<table>
						<tr>
							<td class="field">留言内容：</td>
							<td><textarea  name="reply.rcontent" rows="6" cols="260" ></textarea></td>
						</tr>
						<tr>
							<td class="field">回复图片：</td>
							<td><input type="file"style="width: 680px; height: 34px;" id="url1" name="image" class="input tips" style="width:25%; float:left;"  value=""  data-toggle="hover" data-place="right" data-image="" /></td>
						</tr>
						<tr>
							<td></td>
							<td><label class="ui-blue"><input class="btn btn-success"  type="submit"  value="提交留言" /></label></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	</div>
<!--mainbody end-->

</body>
</html>