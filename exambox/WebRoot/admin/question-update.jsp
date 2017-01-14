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

<link href="resources/css/question-add.css" rel="stylesheet">
<link href="resources/chart/morris.css" rel="stylesheet">
<link href="resources/css/jquery-ui-1.9.2.custom.min.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function typeid() {
		document.getElementById("quetypeid").value = ${question.quetype.id};
		document.getElementById("queid").value = ${question.id};
	}
</script>
<style type="text/css">
.uploadify-button-text {
	text-decoration: underline;
}

span.add-img {
	text-decoration: underline;
	cursor: pointer;
}

span.add-img:hover {
	text-decoration: underline;
}

.swfupload {
	z-index: 10000 !important;
}

.add-content-img {
	display: block;
}

.diaplay-img {
	margin-right: 5px;
}

.diaplay-img:hover {
	text-decoration: underline;
}
</style>
</head>
<body>

	<div>

		<div class="container" style="min-height:500px;">

			<div class="row">
				<div class="col-xs-9">
					<div class="page-header">
						<h1>
							<i class="fa fa-pencil-square-o"></i> 修改试题
						</h1>
					</div>
					<div class="page-content row">
						<form id="question-add-form" style="margin-top:40px;"
							action="updateQuestion.action" method="post">
							<div>
								<input type="hidden" id="quetypeid" name="question.quetype.id">
								<input type="hidden" id="queid" name="question.id">
							</div>
							<div class="form-line question-content">
								<span class="form-label"><span class="warning-label">*</span>试题内容：</span>
								<textarea class="add-question-ta" name="question.qtitle">${question.qtitle}</textarea>
								<span class="add-img add-content-img" style="width:100px;"
									name="question.image">添加图片</span> <span class="form-message"></span>
							</div>
							<c:choose>

								<c:when test="${question.quetype.id eq 1 }">
									<div class="add-opt-items">
										<span class="add-opt-item"> <label class="que-opt-no">A</label>
											<input type="text"
											class="df-input-narrow form-question-opt-item"
											name="question.optiona" value="${question.optiona}">
										</span> <span class="add-opt-item"> <label class="que-opt-no">B</label>
											<input type="text"
											class="df-input-narrow form-question-opt-item"
											name="question.optionb" value="${question.optionb}">
										</span> <span class="add-opt-item"> <label class="que-opt-no">C</label>
											<input type="text"
											class="df-input-narrow form-question-opt-item"
											name="question.optionc" value="${question.optionc}">
										</span> <span class="add-opt-item"> <label class="que-opt-no">D</label>
											<input type="text"
											class="df-input-narrow form-question-opt-item"
											name="question.optiond" value="${question.optiond}">
										</span>
									</div>
									<span class="form-label" style="margin-left: 0px;"><span class="warning-label">*</span>正确答案：</span>
									<select class="df-input-narrow" name="dxquestion">
										<option value="A"
											<c:if test="${question.result eq 'A' }">checked="checked"</c:if>>A</option>
										<option value="B"
											<c:if test="${question.result eq 'B' }">checked="checked"</c:if>>B</option>
										<option value="C"
											<c:if test="${question.result eq 'C' }">checked="checked"</c:if>>C</option>
										<option value="D"
											<c:if test="${question.result eq 'D' }">checked="checked"</c:if>>D</option>
									</select>
									<span class="form-message"></span>
								</c:when>

								<c:when test="${question.quetype.id eq 2 }">
									<div class="add-opt-items">
										<span class="add-opt-item"> <label class="que-opt-no">A</label>
											<input type="text"
											class="df-input-narrow form-question-opt-item"
											name="question.optiona" value="${question.optiona}">
										</span> <span class="add-opt-item"> <label class="que-opt-no">B</label>
											<input type="text"
											class="df-input-narrow form-question-opt-item"
											name="question.optionb" value="${question.optionb}">
										</span> <span class="add-opt-item"> <label class="que-opt-no">C</label>
											<input type="text"
											class="df-input-narrow form-question-opt-item"
											name="question.optionc" value="${question.optionc}">
										</span> <span class="add-opt-item"> <label class="que-opt-no">D</label>
											<input type="text"
											class="df-input-narrow form-question-opt-item"
											name="question.optiond" value="${question.optiond}">
										</span>
									</div>
									<span class="form-label" style="margin-left: 0px;"><span class="warning-label">*</span>正确答案：</span>
									<span class="muti-opt-item"> <input type="checkbox"
										value="A" name="mutiquestion"
										<c:if test="${fn:contains(question.result,'A') }">checked="checked"</c:if>>
										<label class="que-opt-no">A</label> <br>
									</span>
									<span class="muti-opt-item"> <input type="checkbox"
										value="B" name="mutiquestion"
										<c:if test="${fn:contains(question.result,'B') }">checked="checked"</c:if>>
										<label class="que-opt-no">B</label> <br>
									</span>
									<span class="muti-opt-item"> <input type="checkbox"
										value="C" name="mutiquestion"
										<c:if test="${fn:contains(question.result,'C') }">checked="checked"</c:if>>
										<label class="que-opt-no">C</label> <br>
									</span>
									<span class="muti-opt-item"> <input type="checkbox"
										value="D" name="mutiquestion"
										<c:if test="${fn:contains(question.result,'D') }">checked="checked"</c:if>>
										<label class="que-opt-no">D</label> <br>
									</span>
									<span class="form-message"></span>
								</c:when>

								<c:when test="${question.quetype.id eq 3 }">
									<span class="form-label"  style="margin-left: 0px;"><span class="warning-label">*</span>正确答案：</span>
									<select class="df-input-narrow" name="pdquestion">
										<option value="T"
											<c:if test="${question.result eq 'T' }">checked="checked"</c:if>>正确</option>
										<option value="F"
											<c:if test="${question.result eq 'F' }">checked="checked"</c:if>>错误</option>
									</select>
									<span class="form-message"></span>
								</c:when>

							</c:choose>

							<div class="form-line form-question-keyword"
								style="display: block;">
								<span class="form-label"><span class="warning-label"></span>难度：</span>
								<select class="df-input-narrow" name="question.difficulty">
									<option value="1"
										<c:if test="${question.difficulty eq 1 }"> checked="checked"</c:if>>1</option>
									<option value="2"
										<c:if test="${question.difficulty eq 2 }"> checked="checked"</c:if>>2</option>
									<option value="3"
										<c:if test="${question.difficulty eq 3 }"> checked="checked"</c:if>>3</option>
									<option value="4"
										<c:if test="${question.difficulty eq 4 }"> checked="checked"</c:if>>4</option>
								</select>
							</div>

							<div class="form-line form-question-analysis"
								style="display: block;">
								<span class="form-label"><span class="warning-label"></span>题目解析：</span>
								<textarea class="add-question-ta" name="question.analysis">${question.analysis }</textarea>
								<span class="form-message"></span> <br>

							</div>

							<div class="form-line">
								<input id="btn-save" value="保存" onclick="javascript:typeid()"
									type="submit" class="df-submit">
							</div>
						</form>

					</div>
					<div class="modal fade">

						<!-- /.modal-dialog -->
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
	<script type="text/javascript" src="resources/js/all.js"></script>
	<script type="text/javascript"
		src="resources/js/jquery-ui-1.9.2.custom.min.js"></script>

	<script type="text/javascript"
		src="resources/js/uploadify/jquery.uploadify3.1Fixed.js"></script>
	<script type="text/javascript"
		src="resources/js/question-upload-img.js"></script>
	<script type="text/javascript" src="resources/js/field-2-point.js"></script>
	<script type="text/javascript" src="resources/js/question-add.js"></script>

	<!-- Bootstrap JS -->
	<script type="text/javascript"
		src="resources/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>