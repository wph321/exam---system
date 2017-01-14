<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>后台管理中心</title>  
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>   
</head>
<body style="background-color:#f2f9fd;">
<div class="header bg-main">
  <div class="logo margin-big-left fadein-top">
    <h1><img src="images/1.png" class="radius-circle rotate-hover" height="50" alt="" />后台管理中心</h1>
  </div>
  <div class="head-l"><a class="button button-little bg-green" href="" target="_blank"><span class="icon-home"></span> 前台首页</a> &nbsp;&nbsp;<a href="##" class="button button-little bg-blue"><span class="icon-wrench"></span> 清除缓存</a> &nbsp;&nbsp;<a class="button button-little bg-red" href="exitAdmin.action"><span class="icon-power-off"></span> 退出登录</a> </div>
</div>
<div class="leftnav">
  <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
  <h2><span class="icon-user"></span>用户管理</h2>
  <ul style="display:block">
    <li><a href="findByPageStudent.action?nowpage=1" target="right"><span class="icon-caret-right"></span>会员列表</a></li>
    <li><a href="admin/add-user2.jsp" target="right"><span class="icon-caret-right"></span>添加用户</a></li>
	<li><a href="findByPageAdmin.action?nowpage=1" target="right"><span class="icon-caret-right"></span>管理员列表</a></li>
	<li><a href="admin/add-admin2.jsp" target="right"><span class="icon-caret-right"></span>添加管理员</a></li>
  </ul>   
  <h2><span class="icon-pencil-square-o"></span>题库中心</h2>
  <ul>
    <li><a href="findQuestion.action" target="right"><span class="icon-caret-right"></span>题库列表</a></li>
    <li><a href="admin/add-question.jsp" target="right"><span class="icon-caret-right"></span>添加新题</a></li>
    <li><a href="admin/question-import.jsp" target="right"><span class="icon-caret-right"></span>批量添加</a></li>
  </ul>  
  <h2><span class="icon-pencil-square-o"></span>科目管理</h2>
  <ul>
    <li><a href="findByPageSubject.action?nowpage=1" target="right"><span class="icon-caret-right"></span>科目列表</a></li>
    <li><a href="admin/add-subject.jsp" target="right"><span class="icon-caret-right"></span>添加科目</a></li>    
  
  </ul> 
  <h2><span class="icon-pencil-square-o"></span>日期管理</h2>
  <ul>
    <li><a href="findByPageExamdate.action?nowpage=1" target="right"><span class="icon-caret-right"></span>日期列表</a></li>
    <li><a href="admin/add-examdate.jsp" target="right"><span class="icon-caret-right"></span>添加日期</a></li>         
  </ul> 
  <h2><span class="icon-pencil-square-o"></span>知识点管理</h2>
  <ul>
    <li><a href="findByPagePoint.action?nowpage=1" target="right"><span class="icon-caret-right"></span>知识点列表</a></li>
    <li><a href="admin/add-point.jsp" target="right"><span class="icon-caret-right"></span>添加知识点</a></li>          
  </ul> 
  <h2><span class="icon-pencil-square-o"></span>新闻管理</h2>
  <ul>
    <li><a href="findByPageNew?nowpage=1&type=admin" target="right"><span class="icon-caret-right"></span>新闻列表</a></li>
    <li><a href="admin/add-news.jsp" target="right"><span class="icon-caret-right"></span>添加新闻</a></li>          
  </ul> 
  <h2><span class="icon-pencil-square-o"></span>社区管理</h2>
  <ul>
    <li><a href="findByPageForum?nowpage=1&type=admin" target="right"><span class="icon-caret-right"></span>帖子列表</a></li>
    <li><a href="admin/add-forum.jsp" target="right"><span class="icon-caret-right"></span>添加知帖子</a></li>          
  </ul> 
  <h2><span class="icon-pencil-square-o"></span>视频管理</h2>
  <ul>
    <li><a href="findBySubVideo.action?type=admin" target="right"><span class="icon-caret-right"></span>视频列表</a></li>
    <li><a href="admin/add-video.jsp" target="right"><span class="icon-caret-right"></span>添加视频</a></li>          
  </ul>
</div>
<script type="text/javascript">
$(function(){
  $(".leftnav h2").click(function(){
	  $(this).next().slideToggle(200);	
	  $(this).toggleClass("on"); 
  })
  $(".leftnav ul li a").click(function(){
	    $("#a_leader_txt").text($(this).text());
  		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
  })
});
</script>
<ul class="bread">
  <li><a href="{:U('Index/info')}" target="right" class="icon-home"> 首页</a></li>
  <li><a href="##" id="a_leader_txt">网站信息</a></li>
  <li><b>当前语言：</b><span style="color:red;">中文</php></span>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;切换语言：<a href="##">中文</a> &nbsp;&nbsp;<a href="##">英文</a> </li>
</ul>
<div class="admin">
  <iframe scrolling="auto" frameborder="0" src="admin/info.jsp" name="right" width="100%" height="100%"></iframe>
</div>
<div style="text-align:center;">

</div>
</body>
</html>