<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'news-list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>
</head>
<body>
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

</body></html>