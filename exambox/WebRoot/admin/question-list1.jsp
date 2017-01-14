<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%-- <%@taglib uri="spring.tld" prefix="spring"%> --%>
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

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>试题管理</title>
<meta name="keywords" content="">
<link rel="shortcut icon"
	href="<%=basePath%>resources/images/favicon.ico" />
<link href="resources/bootstrap/css/bootstrap-huan.css" rel="stylesheet">
<link href="resources/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<link href="resources/css/style.css" rel="stylesheet">

<link href="resources/css/exam.css" rel="stylesheet">
<link href="resources/chart/morris.css" rel="stylesheet">
<style>
.examing-point {
	display: block;
	font-size: 10px;
	margin-top: 5px;
}

.question-name-td {
	width: 300px;
}

.change-property {
	cursor: pointer;
}

.add-tag-btn {
	cursor: pointer;
}
</style>
<script type="text/javascript">
	function getPoint(sid) {
		if (sid == "")
			return;
		$.ajax({
			type : "post",
			data : "sid=" + sid,
			url : "ajax/findPointBySubjectAjax.action",
			datatype : "html",
			success : function(resu) {
				var points = eval("(" + resu + ")");
				var pointSele = document.getElementById("point");//获取下拉框对象
				pointSele.options.length = 0;
				var op = new Option("全部", 0);
				pointSele.options.add(op);
				for (var i = 0; i < points.length; i++) {
					var point = points[i];
					var op = new Option(point.pname, point.id);
					pointSele.options.add(op);
				}
			},
			error : function() {
				alert("出错了");
			}
		});

	}
</script>
</head>
<body>
	<header>
		
	</header>
	<!-- Navigation bar starts -->

	<div class="navbar bs-docs-nav" role="banner">
		<div class="container">
			
		</div>
	</div>

	<!-- Navigation bar ends -->

	<!-- Slider starts -->

	<div>
		<!-- Slider (Flex Slider) -->

		<div class="container" style="min-height:500px;">

			<div class="row">
				<div class="col-xs-9">
					<div class="page-header">
						<h1>
							<i class="fa fa-list-ul"></i> 试题管理
						</h1>
					</div>
					<div class="page-content row">
						<form action="findQuestion.action" method="post">
						<div id="question-filter">
							<dl id="question-filter-field">
								<dt>科&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;目：</dt>
								<dd>
									<select id="type-select" class="df-input-narrow"
										name="subject.id" onchange="getPoint(this.value)">
										<option selected value="-1">请选择</option>
										<c:forEach items="${subjectList }" var="item">
											<option value="${item.id}">${item.sname}</option>
										</c:forEach>
									</select>
								</dd>
							</dl>
							<dl id="question-filter-knowledge">
								<dt>知&nbsp;&nbsp;识&nbsp;&nbsp;点：</dt>
								<dd>
									<select id="point" class="df-input-narrow" name="point.id">
										<option selected value="-1">请选择</option>
								</dd>
							</dl>
						</div>
							<input type="submit" value="查询" />
						</form>
					

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
							Exam++ Copyright © <a href="http://www.examxx.net/"
								target="_blank">Exam++</a> - <a href="." target="_blank">主页</a>
							| <a href="http://www.examxx.net/" target="_blank">关于我们</a> | <a
								href="http://www.examxx.net/" target="_blank">FAQ</a> | <a
								href="http://www.examxx.net/" target="_blank">联系我们</a>
						</p>
					</div>
				</div>
			</div>

		</div>

	</footer>

	<!-- Slider Ends -->

	<!-- Javascript files -->
	<!-- jQuery -->
	<script type="text/javascript"
		src="resources/js/jquery/jquery-1.9.0.min.js"></script>
	<!-- Bootstrap JS -->
	<script type="text/javascript"
		src="resources/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="resources/js/question-list.js"></script>
	<script type="text/javascript" src="resources/js/all.js"></script>
	<script type="text/javascript" src="resources/js/field-2-point.js"></script>

	<script>
		$(function() {
			$(".change-property")
					.click(
							function() {
								$("#change-property-modal").modal({
									backdrop : true,
									keyboard : true
								});
								var paper_id = $(this).parent().parent().find(
										":checkbox").val();
								$("#add-update-questionid").text(paper_id);
								$
										.ajax({
											headers : {
												'Accept' : 'application/json',
												'Content-Type' : 'application/json'
											},
											type : "GET",
											url : "teacher/question-tag/"
													+ paper_id,
											success : function(message, tst,
													jqXHR) {
												if (!util
														.checkSessionOut(jqXHR))
													return false;
												if (message.result == "success") {
													//将message.object里面的内容写到 div（class=q-label-list）里面
													var innerHtml = "";
													$
															.each(
																	message.object,
																	function(
																			index,
																			element) {
																		innerHtml += "<span class=\"label label-info q-label-item\" data-privatee=" 
										+ element.privatee + " data-creator=" + element.creator
										+" data-memo="+ element.memo
										+" data-id="+ element.tagId
										+ ">"
																				+ element.tagName
																				+ "  <i class=\"fa fa-times\"></i>	</span>";
																	});
													$(".q-label-list").html(
															innerHtml);
												} else {
													util.error("操作失败请稍后尝试:"
															+ message.result);
												}

											},
											error : function(jqXHR, textStatus) {
												util.error("操作失败请稍后尝试");
											}
										});
							});

			$(".add-tag-btn")
					.click(
							function() {
								var label_ids = $(".q-label-item");
								var flag = 0;
								label_ids.each(function() {
									if ($(this).data("id") == $(
											"#tag-from-select").val())
										flag = 1;
								});
								if (flag == 0) {
									var selected = $("#tag-from-select").find(
											"option:selected");

									$(".q-label-list")
											.append(
													"<span class=\"label label-info q-label-item\" data-privatee="
															+ selected
																	.data("privatee")
															+ " data-creator="
															+ selected
																	.data("creator")
															+ " data-memo="
															+ selected
																	.data("memo")
															+ " data-id="
															+ $(
																	"#tag-from-select")
																	.val()
															+ " data-createTime="
															+ selected
																	.data("createTime")
															+ ">"
															+ $(
																	"#tag-from-select :selected")
																	.text()
															+ "  <i class=\"fa fa-times\"></i>	</span>");
								} else {
									util.error("不能重复添加");
								}
							});

			$("#update-exampaper-btn").click(
					function() {

						if ($("#point-from-select").val() == null
								|| $("#point-from-select").val() == "") {
							util.error("请选择知识类");
						}
						$("#point-from-select").val();
						var data = new Array();

						$(".q-label-item")
								.each(
										function() {
											var tag = new Object();
											tag.tagId = $(this).data("id");
											tag.questionId = $(
													"#add-update-questionid")
													.text();
											data.push(tag);
										});
						$.ajax({
							headers : {
								'Accept' : 'application/json',
								'Content-Type' : 'application/json'
							},
							type : "POST",
							url : "admin/question-update/"
									+ $("#add-update-questionid").text() + "/"
									+ $("#point-from-select").val(),
							data : JSON.stringify(data),
							success : function(message, tst, jqXHR) {
								if (!util.checkSessionOut(jqXHR))
									return false;
								if (message.result == "success") {
									util.success("修改成功", function() {
										window.location.reload();
									});
								} else {
									util.error("操作失败请稍后尝试:" + message.result);
								}

							},
							error : function(jqXHR, textStatus) {
								util.error("操作失败请稍后尝试");
							}
						});

						return false;
					});

			$(".q-label-list").on("click", ".fa", function() {
				$(this).parent().remove();
			});

		});
	</script>
</body>
</html>
