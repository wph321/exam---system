<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<form name="videoform" method="post" action="findBySubVideo.action" id="listform">
  <div class="panel admin-panel">
  <input type="hidden" name="type" value="admin">
 	 <input type="hidden" name="videoNowPage" id="nowpages" >
    <div class="panel-head"><strong class="icon-reorder"> 视频列表</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
        <li> <a class="button border-main icon-plus-square-o" href="admin/add-video.jsp"> 添加视频</a> </li>
         <li>
          <div style="float: left; width: 72%;border: 2xp;border-color: black;">
            <select name="sid" class="input" style="width:200px; line-height:17px;" >
				<option value="0" <c:if test="${sid == 0}" >selected='selected' </c:if>>--请选择--</option>
				<c:forEach var="item" items="${subjectList}">
					<option <c:if test="${item.id == sid }" >selected='selected' </c:if> value="${item.id}">${item.sname}</option>
				</c:forEach>
            </select>
          </div>
         <div style="float: left;" >
         &nbsp;<a class="button border-main icon-search" onclick="document.videoform.submit();" >搜索</a></li>
          </div></li>
	</form>
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
        <th width="100" style="text-align:left; padding-left:20px;">ID</th>
        
        <th>图片</th>
        <th>名称</th>
        <th>发布者</th>
        <th width="10%">发布时间</th>
        <th width="310px">操作</th>
      </tr>
      <c:forEach items="${videoListBySubject}" var="shiping">
		<tr>
          <td style="text-align:left; padding-left:20px;"><input type="checkbox" name="id[]" value="${shiping.id}" /></td>
          <td width="10%"><img src="${pageContext.request.contextPath}/video/images/${shiping.image}" alt="" width="70" height="50" /></td>
          <td>${shiping.vname}</td>
          <td><font color="#00CC99">${shiping.admin.aname}</font></td>
         
          <td>${shiping.time}</td>
          <td ><div class="button-group" > <a class="button border-main" href="findoneVideo?vid=${shiping.id}"><span class="icon-edit"></span> 修改</a><a class="button border-red" href="deleteVideo?vid=${shiping.id}" ><span class="icon-trash-o"></span> 删除</a> </div></td>
        </tr>
        
         </c:forEach>
      <tr>
        <td colspan="8">
        <div class="pagelist"> 
        <span>第${videoNowPage}/${allPageBySubject}页</span><a href="javascript:changepage(1)">首页</a><a href="javascript:changepage(${videoNowPage-1 })">上一页</a>
					
					<a href="javascript:changepage(${videoNowPage+1 })">下一页</a><a href="javascript:changepage(${allPageBySubject})">末页</a>
		</div></td>
      </tr>
    </table>
  </div>
 
<script type="text/javascript">
//换页
	function changepage(page){
				document.getElementById("nowpages").value=page;
				document.videoform.submit();
			}
//搜索
function changesearch(){	
		
}

//单个删除
function del(id,mid,iscid){
	if(confirm("您确定要删除吗?")){
		
	}
}

//全选
$("#checkall").click(function(){ 
  $("input[name='id[]']").each(function(){
	  if (this.checked) {
		  this.checked = false;
	  }
	  else {
		  this.checked = true;
	  }
  });
})

//批量删除
function DelSelect(){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){
		var t=confirm("您确认要删除选中的内容吗？");
		if (t==false) return false;		
		$("#listform").submit();		
	}
	else{
		alert("请选择您要删除的内容!");
		return false;
	}
}

//批量排序
function sorts(){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){	
		
		$("#listform").submit();		
	}
	else{
		alert("请选择要操作的内容!");
		return false;
	}
}


//批量首页显示
function changeishome(o){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){
		
		$("#listform").submit();	
	}
	else{
		alert("请选择要操作的内容!");		
	
		return false;
	}
}

//批量推荐
function changeisvouch(o){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){
		
		
		$("#listform").submit();	
	}
	else{
		alert("请选择要操作的内容!");	
		
		return false;
	}
}

//批量置顶
function changeistop(o){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){		
		
		$("#listform").submit();	
	}
	else{
		alert("请选择要操作的内容!");		
	
		return false;
	}
}


//批量移动
function changecate(o){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){		
		
		$("#listform").submit();		
	}
	else{
		alert("请选择要操作的内容!");
		
		return false;
	}
}

//批量复制
function changecopy(o){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){	
		var i = 0;
	    $("input[name='id[]']").each(function(){
	  		if (this.checked==true) {
				i++;
			}		
	    });
		if(i>1){ 
	    	alert("只能选择一条信息!");
			$(o).find("option:first").prop("selected","selected");
		}else{
		
			$("#listform").submit();		
		}	
	}
	else{
		alert("请选择要复制的内容!");
		$(o).find("option:first").prop("selected","selected");
		return false;
	}
}

</script>
</body>
</html>