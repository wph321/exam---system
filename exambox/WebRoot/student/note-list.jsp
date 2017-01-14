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
		<script type="text/javascript">
	function deletenote(id){
	   if(confirm("是否确认要删除？"))
	   location.href="deleteNote.action?note.id="+id;
	   }
	function showListByPage(npage){
	   document.sform.nowpage.value=npage;
	   document.sform.submit();
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
							<li  class="active">
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
							<h1><i class="fa fa-history"></i>我的笔记</h1>
						</div>
						<div class="page-content row">
						  <form action="findByPageAndSubjectAndStudentNote.action" name="sform" mehtod="post" >
						    <input type="hidden" name="nowpage" value="1"/>
						    <input type="hidden" name="student.id" value="${user.id}"/>
						  分类搜索笔记:&nbsp;&nbsp;<select name="subject.id" class="input" >
					       <option value="-1">--请选择科目--</option>
					       <c:forEach items="${subjectList }" var="item">
					       <option <c:if test="${item.id eq subject.id }">selected='selected'</c:if> value="${item.id}">${item.sname }</option>
					       </c:forEach>
				         </select> &nbsp;&nbsp;&nbsp;&nbsp;
				         <input type="submit" value="搜索" />
						  </form>
							<div id="question-list">
								<table class="table-striped table">
									<tr>
										<td width="190px">笔记内容</td><td width="120px">记录时间</td><td width="130px">相关题型</td><td width="30px">操作</td>
									</tr>
									
									<tbody>
										<c:forEach items="${noteList}" var="note">
											<tr>
											   <td>
													<c:if test="${note.ncontent.length() >= 18 }">
													${note.ncontent.substring(0,18)}...
													</c:if>
													<c:if test="${note.ncontent.length() <18 }">
													${note.ncontent}
													</c:if>
												</td>
												<td>
													${note.time}
												</td>
												<td>
												<a href="noteQuestion.action?qid=${question.id}"><c:if test="${note.question.qtitle.length() >= 10 }">
													${note.question.qtitle.substring(0,10)}...
													</c:if>
													<c:if test="${note.question.qtitle.length() <10 }">
													${note.question.qtitle}
													</c:if>
													</a>
												</td>
												<td>
												<a href="javascript:deletenote(${note.id})">删除</a>
												</td>
											</tr>
										</c:forEach>
									</tbody><tfoot></tfoot>
								</table>
							</div>
							<div id="page-link-content">
								<center>
                                <a href="javascript:showListByPage(${backpage})">上一页</a>
                                <c:forEach var="i" step="1" begin="1" end="${pages}">
                              <c:if test="nowpage = i">style="background:#FFF;"</c:if>
		    			        <a href="javascript:showListByPage(${i})">${i}&nbsp;&nbsp;</a>
		  		 	           </c:forEach>
                                <a href="javascript:showListByPage(${nextpage })">下一页</a>
                                <a href="javascript:showListByPage(${pages })">尾页</a>
                                
                                </center> 
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