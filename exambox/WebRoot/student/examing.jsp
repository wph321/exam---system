<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<base href="<%=basePath%>">
		
		<!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame
		Remove this if you use the .htaccess -->
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="utf-8"><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title>Exam++</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<link rel="shortcut icon" href="<%=basePath%>resources/images/favicon.ico" />
		<link href="resources/bootstrap/css/bootstrap-huan.css" rel="stylesheet">
		<link href="resources/font-awesome/css/font-awesome.min.css" rel="stylesheet">
		<link href="resources/css/exam.css" rel="stylesheet" type="text/css">
		<link href="resources/css/style.css" rel="stylesheet">
		
		<script src="../js/jquery-1.9.0.min.js"></script>
		<script type="text/javascript">
			function shoucang(qid,qtid){
			
			 $.ajax({
		        type:"post",
		        url:"addCollect.action",
		        data:"question.id="+qid+"&quetype.id="+qtid,
		        dataType:"html",
		        success:function(resu){
		            alert(resu);
		            if(resu=='已取消收藏！'){
		            	 document.getElementById("shoucang").src="images/out.gif";
		            }else{
			            document.getElementById("shoucang").src="images/on.gif";
		            }
		            	
		        },
		        error:function(){
		        	alert("执行ajax的方法错误了");
		        }
		        });
				//location.href="addCollect.action?question.id="+qid+"&quetype.id="+qtid;				
			}
		</script>
	</head>
	<body>
		<div  style="height:135px;">
		<iframe src="head.jsp"  width="100%" height="100%" scrolling="no"  frameborder="0" ></iframe>
		</div>
		<!-- Navigation bar ends -->

		<div class="content" style="margin-bottom: 100px;">

			<div class="container">
				<div class="row">
					<div class="col-xs-3" style="padding-right: 0px;padding-bottom:15px;">
						<div class="def-bk" id="bk-exam-control">

							<div class="def-bk-content" id="exam-control">

								<div id="question-time" class="question-time-normal">
									<div style="height:140px;text-align: center;">
										<i id="time-icon" class="fa fa-clock-o"> </i>
									</div>

									<span style="margin-right:10px;color: #B8B8B8;">已用时</span>
									<span id="exam-clock">&nbsp;</span>
									<span id="exam-timestamp" style="display:none;">${time}</span>
									<div id="answer-save-info"></div>
	<input type="hidden" id="gradeid" name="card.grade.id" value="${card.grade.id }">
								</div>
								<div id="question-submit">
									<button class="btn-success btn" style="width:100%;" href="javascript:bindSubmit()">
										我要交卷
									</button>
								</div>
								<div id="exam-info" style="display:none;">
									<span id="answer-hashcode"></span>
								</div>
							</div>

						</div>

					</div>
					<div class="col-xs-9" style="padding-right: 0px;">
						<div class="def-bk" id="bk-exampaper">

							<div class="expand-bk-content" id="bk-conent-exampaper">
								<div id="exampaper-header">
									<div id="exampaper-title">
										<i class="fa fa-send"></i>
										<span id="exampaper-title-name"> 模拟试卷 </span>
										<span style="display:none;" id="exampaper-id">1</span>
									</div>
									<div id="exampaper-desc-container">
										<div id="exampaper-desc" class="exampaper-filter">
										<a class="btn btn-primary" href="javascript:sub(${card.qno-1})">上一题</a>
										<a class="btn btn-primary" href="javascript:sub(${card.qno+1})">下一题</a>
										
									<!-- 判断是否收藏 -->
									<c:set var="flag" value="0"></c:set>	
									
									<c:forEach var="collect" items="${collectList}">
										<c:if test="${collect.question.id == card.question.id}">
												<c:set var="flag" value="1"></c:set>	
										</c:if>
									</c:forEach>	
									
										<a class="btn btn-primary" href="javascript:shoucang('${card.question.id}','${card.question.quetype.id}')">
										<img id="shoucang" 
										<c:choose>
										<c:when test="${flag=='0'}">
										src="images/out.gif"  
										</c:when>
										<c:otherwise>src="images/on.gif"
										</c:otherwise>
										</c:choose>
										
										>收藏本题</a>
										<a class="btn btn-primary" href="noteQuestion.action?qid=${card.question.id}">本题分析</a>
										</div>
									</div>
									
								</div>
								<c:choose>
									<c:when test="${card.question.quetype.id eq 1 }">
										<form name="sinqtab" action="updateCard.action" method="post">
										<input type="hidden" name="card.id" value="${card.id }">
										<input type="hidden" name="card.grade.id" value="${card.grade.id }">
										<input type="hidden" name="nextqno" >
										<input type="hidden" name="time" >
										<ul style="margin-left: 20px" id="exampaper-body">
											<li style="font-size: 20px"><b>(单选题)${card.qno}.${card.question.qtitle }</b></li>
											<span class="muti-opt-item">
											<input type="radio" value="A" <c:if test="${card.uanswer eq 'A' }">checked="checked"</c:if> name="card.uanswer"> <label class="que-opt-no">A.${card.question.optiona}</label> <br>
											</span> <span class="muti-opt-item"> 
											<input type="radio" value="B" <c:if test="${card.uanswer eq 'B' }">checked="checked"</c:if> name="card.uanswer"> <label class="que-opt-no">B.${card.question.optionb}</label> <br>
											</span> <span class="muti-opt-item"> 
											<input type="radio" value="C" <c:if test="${card.uanswer eq 'C' }">checked="checked"</c:if> name="card.uanswer"> <label class="que-opt-no">C.${card.question.optionc}</label> <br>
											</span> <span class="muti-opt-item"> 
											<input type="radio" value="D" <c:if test="${card.uanswer eq 'D' }">checked="checked"</c:if> name="card.uanswer"> <label class="que-opt-no">D.${card.question.optiond}</label> <br>
											</span> <span class="form-message"></span>
										</ul>
										</form>
									</c:when>
									<c:when test="${card.question.quetype.id eq 2 }">
										<form name="mulqtab" action="updateCard.action" method="post">
										<input type="hidden" name="card.id" value="${card.id }">
										<input type="hidden" name="card.grade.id" value="${card.grade.id }">
										<input type="hidden" name="nextqno" >
										<input type="hidden" name="time" >
										<input type="hidden" id="uanswer" name="card.uanswer" value="">
										<ul style="margin-left: 20px" id="exampaper-body">
											<li style="font-size: 20px"><b>(多选题)${card.qno}.${card.question.qtitle }</b></li>
											<span class="muti-opt-item">
											
											<input name="mulq" type="checkbox" value="A" onclick="answer()" <c:if test="${fn:contains(card.uanswer,'A') }">checked="checked"</c:if>> <label class="que-opt-no">A.${card.question.optiona}</label> <br>
											</span> <span class="muti-opt-item"> 
											<input name="mulq" type="checkbox" value="B" onclick="answer()" <c:if test="${fn:contains(card.uanswer,'B') }">checked="checked"</c:if> > <label class="que-opt-no">B.${card.question.optionb}</label> <br>
											</span> <span class="muti-opt-item"> 
											<input name="mulq" type="checkbox" value="C" onclick="answer()" <c:if test="${fn:contains(card.uanswer,'C') }">checked="checked"</c:if>> <label class="que-opt-no">C.${card.question.optionc}</label> <br>
											</span> <span class="muti-opt-item"> 
											<input name="mulq" type="checkbox" value="D" onclick="answer()" <c:if test="${fn:contains(card.uanswer,'D') }">checked="checked"</c:if>> <label class="que-opt-no">D.${card.question.optiond}</label> <br>
											</span> <span class="form-message"></span>
											
										</ul>
										</form>
									</c:when>
									<c:when test="${card.question.quetype.id eq 3 }">
										<form name="tofqtab" action="updateCard.action" method="post">
										<input type="hidden" name="card.id" value="${card.id }">
										<input type="hidden" name="card.grade.id" value="${card.grade.id }">
										<input type="hidden" name="nextqno" >
										<input type="hidden" name="time" >
										<ul style="margin-left: 20px" id="exampaper-body">
											<li style="font-size: 20px"><b>(判断题)${card.qno}.${card.question.qtitle }</b></li>
											<span class="muti-opt-item">
											<input type="radio" value="T" <c:if test="${card.uanswer eq 'T' }">checked="checked"</c:if> name="card.uanswer"> <label class="que-opt-no">A.正确</label> <br>
											</span> <span class="muti-opt-item"> 
											<input type="radio" value="F" <c:if test="${card.uanswer eq 'F' }">checked="checked"</c:if> name="card.uanswer"> <label class="que-opt-no">B.错误</label> <br>
											</span> 
										</ul>
										</form>
									</c:when>
								</c:choose>
	<script type="text/javascript">
	//提交答案

	
	function answer(){
		var result="";
		var mulqs=document.getElementsByName("mulq")
		for(var i=0;i<mulqs.length;i++){
			if(mulqs[i].checked==true){
				result+=mulqs[i].value;
			}
		}
		document.getElementById("uanswer").value=result;
	}
	function sub(op){
		//判断下一题题号
		var etime = document.getElementById("exam-timestamp").innerHTML;
		if(op==0){
			op=1;
			alert("这已经是第一道题了！");		
		}
		if(op>${fn:length(cardList)}){
			op=${fn:length(cardList)};
			alert("这已经是最后一道题了！");		
		}
		//提交本题
		if(${card.question.quetype.id }==1){
			document.sinqtab.nextqno.value=op;
			document.sinqtab.time.value=etime;
			document.sinqtab.submit();
		}
		if(${card.question.quetype.id }==2){
			answer();
			document.mulqtab.nextqno.value=op;
			document.mulqtab.time.value=etime;
			document.mulqtab.submit();
		}
		if(${card.question.quetype.id }==3){
			document.tofqtab.nextqno.value=op;
			document.tofqtab.time.value=etime;
			document.tofqtab.submit();
		}
		
		//调下一个题
	}
	</script>						
								<div id="exampaper-footer">
									<div id="question-navi">
										<div id="question-navi-controller">
											<i class="fa fa-arrow-circle-down"></i>
											<span>答题卡</span>
										</div>
										<div id="question-navi-content">
										<c:forEach var="c" items="${cardList }" varStatus="num">
											<c:choose>
												<c:when test="${empty c.uanswer }">
													<a class="question-navi-item" href="javascript:sub(${c.qno})" >${c.qno }</a>
												</c:when>
												<c:otherwise>
													<a class="pressed question-navi-item"  href="javascript:sub(${c.qno})" >${c.qno }</a>
												</c:otherwise>
											</c:choose>
										
										</c:forEach>
										</div>
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
		<!-- Bootstrap JS -->
		<script type="text/javascript" src="resources/bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="resources/js/all.js?v=0712"></script>
		<script type="text/javascript" src="resources/js/paper-examing.js"></script>

		

	</body>
</html>
										