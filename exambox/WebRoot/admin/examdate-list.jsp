<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'video-list.jsp' starting page</title>
    
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>
<script type="text/javascript">
	function deleteexamdate(id){
	   if(confirm("是否确认要删除？"))
	   location.href="deleteExamdate.action?did="+id;
	   }
	function showListByPage(npage){
	   document.sform.nowpage.value=npage;
	   document.sform.submit();
	}
	</script>
</head>
<body>
<form action="findByPageSubjectExamdate.action" method="post" name="sform" id="listform">
  <input type="hidden" name="nowpage" value="1"/>
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 内容列表</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
        <li> <a class="button border-main icon-plus-square-o" href="admin/add-examdate.jsp"> 添加内容</a> </li>
        <li>
          <select name="subject.id" class="input" style="width:200px; line-height:17px;" onchange="changesearch()">
					<option value="-1">--请选择科目--</option>
					<c:forEach items="${subjectList }" var="item">
					<option <c:if test="${item.id eq subject.id }" >selected='selected' </c:if> value="${item.id}">${item.sname }</option>
					</c:forEach>
			</select>
		</li>
		<li><input type="submit" value="搜索" class="button border-main icon-search" /></li>
		</ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
        
        <th>编号</th>
        <th>科目名称</th>
        <th>考试时间</th>
        <th width="10%">更新时间</th>
        <th width="310">操作</th>
      </tr>
		<c:forEach items="${examdateList }" var="item">
        <tr>
          <td>${item.id }</t>
		  <td>${item.subject.sname }</td>
		  <td>${item.edate }</td>	
          <td>2016-07-01</td>
          <td><div class="button-group"> 
          <a class="button border-main" href="findoneExamdate.action?did=${item.id }"><span class="icon-edit"></span> 修改</a>
          <a class="button border-red" href="javascript:deleteexamdate(${item.id })"><span class="icon-trash-o"></span> 删除</a> </div></td>
        </tr>
        </c:forEach>
   		
      <tr>
        <td colspan="8">
        <div class="pagelist"> 
        <center>
          <a href="javascript:showListByPage(${backpage })">上一页</a>
          <c:forEach var="i" step="1" begin="1" end="${pages}">
           <a href="javascript:showListByPage(${i})">${i}&nbsp;&nbsp;</a>
		    </c:forEach>
          <a href="javascript:showListByPage(${nextpage })">下一页</a>
          <a href="javascript:showListByPage(${pages })">尾页</a>
                               
    </center> 
        </div></td>
      </tr>
    </table>
  </div>
</form>
<script type="text/javascript">

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