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
	function getPoint(sid) {
		if (sid == "")
			return;
		$.ajax({
			type : "post",
			data : "sid=" + sid ,
			url : "ajax/findPointBySubjectAjax.action",
			datatype : "html",
			success : function(resu) {
				var points = eval("(" + resu + ")");
				var pointSele = document.getElementById("point-from-select");//获取下拉框对象
				pointSele.options.length = 0;
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
	
	function abcde(){
		var obj =  document.getElementById("point-to-select");
		var pointrel;
		for(var i =0;i<obj.options.length;i++){
			var t = obj.options[i];
			pointrel = pointrel + "+" + t.value  ;
		}
		document.getElementById("pointrelid").value=pointrel;
		return false;
		
		
		
		
		
		
		
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
							<i class="fa fa-pencil-square-o"></i> 添加试题
						</h1>
					</div>
					<div class="page-content row">
						<form id="question-add-form" style="margin-top:40px;"
							action="addQuestion.action" method="post">
							<input type=hidden name=pointrel id="pointrelid">
							<div class="form-line question-type" id="question-type">
								<span class="form-label"><span class="warning-label">*</span>试题类型：</span>
								<select id="question-type-select" class="df-input-narrow"
									name="question.quetype.id">

									<option value="1">单选题</option>

									<option value="2">多选题</option>

									<option value="3">判断题</option>

								</select><span class="form-message"></span>
							</div>
							<div class="form-line question-knowledge">
								<span class="form-label"><span class="warning-label">*</span>知识点：</span>
								<div>
									<div class="clearfix">
										<div id="aq-course1"
											style="padding:0px;float:left; width:48%;">
											<select id="field-select" class="df-input-narrow" size="4"
												style="width:100%;" onchange="getPoint(this.value)"
												name="question.subject.id">
												<c:forEach items="${subjectList }" var="item">
													<option value="${item.id }" >${item.sname }</option>
												</c:forEach>
											</select>
										</div>
										<div id="aq-course2"
											style="padding:0px; float:right;width:48%;">
											<select id="point-from-select" class="df-input-narrow"
												size="4" style="width:100%;">
											</select>
										</div>
									</div>

									<div style="text-align:center;margin:10px 0;">
										<button id="add-point-btn" class="btn btn-primary btn-xs">选择知识点</button>
										<button id="del-point-btn" class="btn btn-danger btn-xs">删除知识点</button>
										<button id="remove-all-point-btn"
											class="btn btn-warning btn-xs">清除列表</button>
									</div>
									<div id="kn-selected"
										style="padding-left:0px;text-align:center;margin-bottom:20px;">
										<select id="point-to-select"  class="df-input-narrow" size="4"
											style="width:80%;" name="pointsel">
										</select>
										<p style="font-size:12px;color:#AAA;">您可以从上面选择4个知识点</p>
									</div>
								</div>
								<span class="form-message"></span>
							</div>

							<div class="form-line question-content">
								<span class="form-label"><span class="warning-label">*</span>试题内容：</span>
								<textarea class="add-question-ta" name="question.qtitle"></textarea>
								<span class="add-img add-content-img" style="width:100px;"
									name="question.image">添加图片</span> <span class="form-message"></span>
							</div>
							<div class="form-line form-question-opt" style="display: block;">
								<span class="form-label"><span class="warning-label">*</span>选项：</span>
								<div class="add-opt-items">
									<span class="add-opt-item"><label class="que-opt-no">A</label>
										<input type="text"
										class="df-input-narrow form-question-opt-item"
										name="question.optiona"> </span> <span class="add-opt-item"><label
										class="que-opt-no">B</label> <input type="text"
										class="df-input-narrow form-question-opt-item"
										name="question.optionb"> </span> <span class="add-opt-item"><label
										class="que-opt-no">C</label> <input type="text"
										class="df-input-narrow form-question-opt-item"
										name="question.optionc"> <span><i
											class="small-icon ques-remove-opt fa fa-minus-square"
											title="删除此选项"></i></span> </span> <span class="add-opt-item"><label
										class="que-opt-no">D</label> <input type="text"
										class="df-input-narrow form-question-opt-item"
										name="question.optiond"> <span><i
											class="small-icon ques-remove-opt fa fa-minus-square"
											title="删除此选项"></i></span> </span>
								</div>
								<!--	<div class="small-icon" id="ques-add-opt" title=""></div>-->
								<span id="ques-add-opt"><i
									class="small-icon fa fa-plus-square" title="添加选项"></i></span> <br>
								<span class="form-message"></span>
							</div>
							<div class="form-line form-question-answer1 correct-answer"
								style="display: block;">
								<span class="form-label"><span class="warning-label">*</span>正确答案：</span>
								<select class="df-input-narrow" name="dxquestion">
									<option value="A">A</option>
									<option value="B">B</option>
									<option value="C">C</option>
									<option value="D">D</option>
								</select><span class="form-message"></span>
							</div>

							<div class="form-line form-question-answer-muti correct-answer"
								style="display: none;">
								<span class="form-label"><span class="warning-label">*</span>正确答案：</span>
								<span class="muti-opt-item"> <input type="checkbox"
									value="A" name="mutiquestion"> <label
									class="que-opt-no">A</label> <br>
								</span> <span class="muti-opt-item"> <input type="checkbox"
									value="B" name="mutiquestion"> <label
									class="que-opt-no">B</label> <br>
								</span> <span class="muti-opt-item"> <input type="checkbox"
									value="C" name="mutiquestion"> <label
									class="que-opt-no">C</label> <br>
								</span> <span class="muti-opt-item"> <input type="checkbox"
									value="D" name="mutiquestion"> <label
									class="que-opt-no">D</label> <br>
								</span> <span class="form-message"></span>
							</div>

							<div
								class="form-line form-question-answer-boolean correct-answer"
								style="display: none;">
								<span class="form-label"><span class="warning-label">*</span>正确答案：</span>
								<select class="df-input-narrow" name="pdquestion">
									<option value="T">正确</option>
									<option value="F">错误</option>
								</select><span class="form-message"></span>
							</div>

							<div class="form-line correct-answer form-question-answer-text"
								style="display: none;">
								<span class="form-label form-question-answer-more"><span
									class="warning-label">*</span>参考答案：</span>
								<textarea class="add-question-ta"></textarea>
								<span class="form-message"></span> <br>

							</div>
							<!-- 
							
							<div class="form-line form-question-reference"
								style="display: block;">
								<span class="form-label"><span class="warning-label"></span>来源：</span>
								<input type="text" class="df-input-narrow"><span
									class="form-message"></span> <br>
							</div> 
							
							-->
							<!-- 
							<div class="form-line form-question-examingpoint"
								style="display: block;">
								<span class="form-label"><span class="warning-label"></span>知识点：</span>
								<input type="text" class="df-input-narrow" name="questoin.knowpint"><span
									class="form-message"></span> <br>
							</div>
							 -->


							<div class="form-line form-question-keyword"
								style="display: block;">
								<span class="form-label"><span class="warning-label"></span>难度：</span>
								<select class="df-input-narrow" name="question.difficulty">
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
								</select>
							</div>
							<div class="form-line form-question-analysis"
								style="display: block;">
								<span class="form-label"><span class="warning-label"></span>题目解析：</span>
								<textarea class="add-question-ta" name="question.analysis"></textarea>
								<span class="form-message"></span> <br>

							</div>

							<div class="form-line">
								<input id="btn-save" value="保存" onclick="abcde()" type="submit" class="df-submit">
							</div>
						</form>

					</div>
					<div class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title">图片上传工具</h4>
								</div>
								<div class="modal-body">
									<div id="add-question-img-dialog" title="图片上传工具">
										<form>
											<div class="form-line img-destination">
												<span class="form-label">添加至：</span> <label></label> <input
													type="hidden" value="" />
											</div>
											<div class="form-line add-update-quetstionfile">
												<span class="form-label">上传图片：</span>
												<div id="div-file-list"></div>
												<div class="form-line" id="uploadify"></div>
												<span class="form-message">请上传png、jpg图片文件，且不能大于100KB。为了使得图片显示正常，请上传的图片长宽比例为2:1</span>
											</div>
										</form>
									</div>
								</div>

							</div>
							<!-- /.modal-content -->
						</div>
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