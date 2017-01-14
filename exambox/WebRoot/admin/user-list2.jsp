<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
	<link rel="shortcut icon" href="<%=basePath%>resources/images/favicon.ico" />
		<link href="resources/bootstrap/css/bootstrap-huan.css" rel="stylesheet">
		<link href="resources/font-awesome/css/font-awesome.min.css" rel="stylesheet">
		<link href="resources/css/style.css" rel="stylesheet">
		
		<link href="resources/css/exam.css" rel="stylesheet">
		<link href="resources/chart/morris.css" rel="stylesheet">
		<link rel="stylesheet" href="css/pintuer.css">
		<link rel="stylesheet" href="css/admin.css">
		<script src="js/jquery.js"></script>
		<script src="js/pintuer.js"></script>

<style type="text/css">
			.disable-btn, .enable-btn{
				text-decoration: underline;
			}
			.disable-btn, .enable-btn{
				cursor:pointer;
			}
		</style>
</head>
<body>
<form name="newform" method="post" action="findByPageStudent" id="listform">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 内容列表</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
        <li> <a class="button border-main icon-plus-square-o" href="add.html"> 添加内容</a> </li>
        <li>
          <input type="text"  name="searchName" value="${backName}"  class="input" style="width:250px; line-height:17px;display:inline-block" />
         
 	 <input type="hidden" name="nowpage" id="nowpages" >
          <a href="javascript:void(0)" class="button border-main icon-search" onclick="changepage(1)" > 搜索</a></li>
	</form>
      </ul>
    </div>
    
    <table class="table table-hover text-center">
      <tr>
        <th width="100" style="text-align:left; padding-left:20px;">ID</th>
        
        <th>姓名</th>
        <th>邮箱</th>
        
        <th>电话</th>
        <th>头像</th>
        <th width="1px">状态</th>
        <th>操作</th>
       
      </tr>
     		<c:forEach items="${stuList}" var="stu" >
											<tr>
												
												<td>${stu.id}</td>
												<td>${stu.uname}</td>
												<td>${stu.email}</td>
												<td>${stu.phone}</td>
												<td>${stu.image}</td>

												<!-- <td></td> -->
												
												<td>
													<c:choose>
														<c:when test="${stu.status == 1 }">
															<span class="label label-success">启用</span>
														</c:when>
														<c:when test="${stu.status == 0 }">
															<span class="label label-danger">注销</span>
														</c:when>
														<c:otherwise>
															其他
														</c:otherwise>
													</c:choose>
												</td>
												<td>
													<c:choose>
														<c:when test="${stu.status == 1}">
															<a href="statusUdStudent.action?student.id=${stu.id}">禁用</a>
														</c:when>
														<c:when test="${stu.status == 0}">
															<a href="statusUdStudent.action?student.id=${stu.id}">启用</a>
														</c:when>
													</c:choose>
													
												</td>
											</tr>
										</c:forEach>
      <tr>
        <td colspan="8">
        <div class="pagelist"> 
        <span>第${nowpage }/${pages}页</span><a href="javascript:changepage(1)">首页</a><a href="javascript:changepage(${backpage})">上一页</a>
					<c:forEach var="i" step="1" begin="1" end="${pages }">
		    			<a href="javascript:changepage(${i })">${i }&nbsp&nbsp</a>
		  		 	 </c:forEach>
					<a href="javascript:changepage(${nextpage })">下一页</a><a href="javascript:changepage(${pages })">末页</a>
		</div></td>
      </tr>
    </table>
  </div>
  
<script type="text/javascript" src="resources/js/jquery/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="resources/js/all.js"></script>
<script type="text/javascript">
//换页
	function changepage(page){
				document.getElementById("nowpages").value=page;
				document.newform.submit();
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



$(function(){
				$(".disable-btn").click(function(){
					var message = "确定要禁用该用户吗？";
					var answer = confirm(message);
					if(!answer){
						return false;
					}
					
					jQuery.ajax({
						headers : {
							'Accept' : 'application/json',
							'Content-Type' : 'application/json'
						},
						type : "GET",
						url : 'admin/disable-user/' + $(this).data("id"),
						success : function(message,tst,jqXHR) {
							if(!util.checkSessionOut(jqXHR))return false;
							if (message.result == "success") {
								util.success("操作成功！", function(){
									 window.location.reload();
								});
							} else {
								util.error(message.result);
							}
						},
						error : function(jqXHR, textStatus) {
							util.error("操作失败请稍后尝试");
						}
					});
					
				});
				
				$(".enable-btn").click(function(){
					var message = "确定要启用该用户吗？";
					var answer = confirm(message);
					if(!answer){
						return false;
					}
					jQuery.ajax({
						headers : {
							'Accept' : 'application/json',
							'Content-Type' : 'application/json'
						},
						type : "GET",
						url : 'admin/enable-user/' + $(this).data("id"),
						success : function(message,tst,jqXHR) {
							if(!util.checkSessionOut(jqXHR))return false;
							if (message.result == "success") {
								util.success("操作成功！", function(){
									 window.location.reload();
								});
							} else {
								util.error(message.result);
							}
						},
						error : function(jqXHR, textStatus) {
							util.error("操作失败请稍后尝试");
						}
					});
					
				});
			});


</script>


	
		
		<!-- Bootstrap JS -->
	
		<script>
			
		
		</script>
</body>
</html>