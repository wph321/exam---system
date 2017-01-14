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
		<link href="css/forum.css" rel="stylesheet">

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
		<div id="mainbody">
 
  <div class="blank"></div>
  <div class="blogs">
    <ul class="bloglist">
   
      
      
       <c:forEach var="f" items="${forumListByPage}">
      <li>
        <div class="arrow_box">
          <div class="ti"></div>
          <!--三角形-->
          <div class="ci"></div>
          <!--圆形-->
          <h2 class="title"><a href="findoneForum?forum.fid=${f.fid }" target="_blank">${f.ftitle }</a></h2>
          <ul class="textinfo">
            <<img src="images/${f.student.image }" style="border-radius:50%">
            <p> ${f.fcontent}</p>
          </ul>
          <ul class="details">
            
            <li class="comments">${fn:length(f.replies) }</li>
            <li class="icon-time">${f.fdate }</li>
          </ul>
        </div>
        <!--arrow_box end--> 
      </li>
      </c:forEach>
      <div class="pagelist">
      				<a href="javascript:changepage(1)">首页</a><a href="javascript:changepage(${nowpage-1 })">上一页</a>
					<c:forEach var="i" step="1" begin="1" end="${allPage }">
		    			<a href="javascript:changepage(${i })">${i }&nbsp&nbsp</a>
		  		 	 </c:forEach>
					<a href="javascript:changepage(${nowpage+1 })">下一页</a><a href="javascript:changepage(${allPage })">末页</a> 
		</div>
    </ul>
    <!--bloglist end-->
    <aside>
      
       <div class="search" >
        <form name="forumform" class="searchform" method="post" action="findByPageForum">
          <input id="ss" type="text" name="forum.ftitle" value="${forum.ftitle }"  style="height: 30px">
          <input type="hidden" name="nowpage" id="nowpages" >
        	<input type="submit" class="btn btn-primary" value="搜索">
        </form>
        <a class="btn btn-success" href="addforum.jsp" style="width: 280px;">发布帖子</a>
      </div>
     <script type="text/javascript">
     //换页
	function changepage(page){
				document.getElementById("nowpages").value=page;
				document.forumform.submit();
			}
     </script>  
      
      <div class="clicks">
        <h2>热门点击</h2>
        <ol>
       			<s:iterator var="m" value="#attr.allnew" status="num">
					<c:if test="${num.index  lt 8 }">
						<c:if test="${m.ntitle.length() >= 15 }">
						<li><span>${n.admin.aname }</span><a href="findoneNew?n.nid=${m.nid}" style="cursor:default;text-decoration:none;" title="${m.ntitle }">*${m.ntitle.substring(0,18) }...</a></li>
						</c:if>
						<c:if test="${m.ntitle.length() < 15 }">
						<li><span>${n.admin.aname }</span><a href="findoneNew?n.nid=${m.nid}">*${m.ntitle }</a></li>
						</c:if>
					</c:if>
				</s:iterator>
			
        </ol>
      </div>
      <div class="toppic">
        <h2>教学视频</h2>
        <ul>
        	<c:forEach items="${vlist }" var="v" >
          <li><a href="/"><img src="/images/${v.image}" width="80px;" height="80px">${v.vname }
            <p>${v.subject }</p>
            </a></li>
            </c:forEach>
         
        </ul>
      </div>
    </aside>
  </div>
  <!--blogs end--> 
<!--mainbody end-->
</div>
  

</body>
</html>