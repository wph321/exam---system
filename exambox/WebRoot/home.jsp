<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
		<title>考试格子</title>
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

	</head>

	<body>
	<div  style="height:135px;">
	<iframe src="head.jsp"  width="100%" height="100%" scrolling="no"  frameborder="0" ></iframe>
	</div>
		<!-- Slider starts -->

		<div class="full-slider">
			<!-- Slider (Flex Slider) -->

			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="flexslider">
							<div class="flex-caption">
								<!-- Left column -->
								<div class="col-l">
								<p><h2>${subject.sname }</h2></p>

									<!-- Button -->
										<a class="btn btn-default btn-cta" href="findBySubVideo.action?sid=${subject.id }&type=1"  target="_parent"><i class="fa fa-arrow-circle-down"></i> 观看教学视频</a>
								
									</div>
								<!-- Right column -->
								<div class="col-r">

									<!-- Use the class "flex-back" to add background inside flex slider -->
									
									<p style="text-indent:2em;">${subject.sdetail }</p>
								
									<!-- <img alt="" src="resources/images/ad.png"> -->
									
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="content" style="margin-bottom: 100px;">
<c:if test="${not empty ts }">${ts }<%session.removeAttribute("ts"); %></c:if>
			<div class="container">
				<div>
					<h3>开始测验</h3>
					<p>
						选择您想要参加的测验，考核下自己吧
					</p>
					<div class="row">
						<div class="select-test col-xs-6">
							<div style="height: 100px;">
								<div class="select-test-icon">
									<i class="fa fa-cloud-upload"></i>
								</div>
								<div class="select-test-content">
									<h3 class="title">强化练习</h3>
									<p>
										自主选择具体考点，各个击破
									</p>
									<a class="btn btn-primary" data-toggle="modal" data-target=".levelup-practice-modal"><i class="fa fa-arrow-right"></i>参加练习</a>
									<div class="modal fade levelup-practice-modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
									  <div class="modal-dialog">
									    <div class="modal-content">
									    	<div class="modal-header">
										        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
										        <h6 class="modal-title" id="myModalLabel">选择想要练习的知识点</h6>
										     </div>
										     <div class="modal-body">
										     	
										     				
										     				<ul class="question-list-knowledge" >
										     					<c:forEach items="${subject.points }" var="p">
										     						<li>${p.pname } [共<span class="question-number">${fn:length(p.questions) } </span>题]
										     						<span style="position: absolute;left: 450px;">
										     							<a href="addByPointCard.action?pid=${p.id}" class="btn btn-success btn-sm join-practice-btn">参加练习</a>
										     						</span>
										     						</li>
										     					</c:forEach>
										     					<li>全部 [共<span class="question-number">${fn:length(subject.questions) } </span>题]
										     						<span style="position: absolute;left: 450px;">
										     							<a href="addBySubCard.action?subject.id=${subject.id}" class="btn btn-success btn-sm join-practice-btn">参加练习</a>
										     						</span>
										     						</li>
										     				</ul>
										     			
										     </div>
										     <div class="modal-footer">
        										<button type="button" class="btn btn-default" data-dismiss="modal">关闭窗口</button>
      										 </div>
									    </div>
									  </div>
									</div>
								</div>
								<!--//content-->

							</div>
						</div>
						<div class="select-test col-xs-6">
							<div style="height: 100px;">
								<div class="select-test-icon">
									<i class="fa fa-eraser"></i>
								</div>
								<div class="select-test-content">
									<h3 class="title">错题练习</h3>
									<p>
										收录做过的所有错题
									</p>
									<!-- <a class="btn btn-primary" href="student/practice-incorrect"><i class="fa fa-arrow-right"></i>参加练习</a> -->
									<a class="btn btn-primary" href="addByErrorAndSubCard.action?subject.id=${subject.id }"><i class="fa fa-arrow-right"></i>参加练习</a>
									
								</div>
								<!--//content-->

							</div>
						</div>
						<div class="select-test col-xs-6">
							<div style="height: 100px;">
								<div class="select-test-icon">
									<i class="fa fa-superscript"></i>
								</div>
								<div class="select-test-content">
									<h3 class="title">随机练习</h3>
									<p>

										根据你对考点的掌握程度智能出题，提升综合能力
									</p>
									<a class="btn btn-primary" href="addCard.action?subject.id=${subject.id }"><i class="fa fa-arrow-right"></i>参加练习</a>
								</div>
								<!--//content-->
								
							</div>
						</div>
						<div class="select-test col-xs-6">
							<div style="height: 100px;">
								<div class="select-test-icon">
									<i class="fa fa-file-text"></i>
								</div>
								<div class="select-test-content">
									<h3 class="title">未做过的题</h3>
									<p>
										做为做过的题
									</p>
									<a class="btn btn-primary" href="addByUndoAndSubCard.action?subject.id=${subject.id }"><i class="fa fa-arrow-right"></i>参加练习</a>
									
								</div>
								<!--//content-->
							</div>
						</div>
						<div class="select-test col-xs-6">
							<div style="height: 100px;">
								<div class="select-test-icon">
									<i class="fa fa-book"></i>
								</div>
								<div class="select-test-content">
									<h3 class="title">易错题练习</h3>
									<p>
										大家都容易做错的题，一起来看看吧！
									</p>
									<!-- <a class="btn btn-primary" href="student/practice-incorrect"><i class="fa fa-arrow-right"></i>参加练习</a> -->
									<a class="btn btn-primary" href="addyicuoCard.action?subject.id=${subject.id }"><i class="fa fa-arrow-right"></i>参加练习</a>
									
								</div>
								<!--//content-->

							</div>
						</div>
						<div class="select-test col-xs-6">
							<div style="height: 100px;">
								<div class="select-test-icon">
									<i class="fa fa-rocket"></i>
								</div>
								<div class="select-test-content">
									<h3 class="title">下载题库</h3>
									<p>
										下载本题库需要消耗100积分哦！
									</p>
									<!-- <a class="btn btn-primary" href="student/practice-incorrect"><i class="fa fa-arrow-right"></i>参加练习</a> -->
									<a class="btn btn-primary" href="addyicuoCard.action?subject.id=${subject.id }"><i class="fa fa-arrow-right"></i>我要下载</a>
									
								</div>
								<!--//content-->

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
		<script type="text/javascript"
		src="resources/js/jquery/jquery-1.9.0.min.js"></script>
		<!-- Bootstrap JS -->
		<script type="text/javascript"
		src="resources/bootstrap/js/bootstrap.min.js"></script>
		<script>
		$(function(){
			bindQuestionKnowledage();
			var result = checkBrowser();
			if (!result){
				alert("请至少更新浏览器版本至IE8或以上版本");
			}
		});
		
		function checkBrowser() {
			var browser = navigator.appName;
			var b_version = navigator.appVersion;
			var version = b_version.split(";");
			var trim_Version = version[1].replace(/[ ]/g, "");
			if (browser == "Microsoft Internet Explorer" && trim_Version == "MSIE7.0") {
				return false;
			} else if (browser == "Microsoft Internet Explorer"
					&& trim_Version == "MSIE6.0") {
				return false;
			} else
				return true;
		}
		
		function bindQuestionKnowledage(){
			$(".knowledge-title").click(function(){
				var ul = $(this).parent().find(".question-list-knowledge");
				
				if(ul.is(":visible")){
					$(this).find(".fa-chevron-down").hide();
					$(this).find(".fa-chevron-up").show();
					
					$(".question-list-knowledge").slideUp();
					
				}else{
					$(".fa-chevron-down").hide();
					$(".fa-chevron-up").show();
					
					$(this).find(".fa-chevron-up").hide();
					$(this).find(".fa-chevron-down").show();
					
					$(".question-list-knowledge").slideUp();
					ul.slideDown();
					
				}
				
				
			});
		}
		</script>
	</body>
</html>
