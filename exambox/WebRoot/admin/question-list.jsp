<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'video-list.jsp' starting page</title>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>
<script type="text/javascript">
	function changepage(page) {
		document.getElementById("nowpage").value = page;
		document.sform.submit();
	}
	function getPoint(sid) {
		if (sid == "")
			return;
		$.ajax({
			type : "post",
			data : "sid=" + sid,
			url : "ajax/findPointBySubjectAjax.action",
			datatype : "html",
			success : function(resu) {
				var points = eval("(" + resu + ")");
				var pointSele = document.getElementById("point");//获取下拉框对象
				pointSele.options.length = 0;
				var op = new Option("全部", 0);
				pointSele.options.add(op);
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
</script>
</head>
<body>
	<!--
	<form action="findQuestion.action" name="questionListin" method="post">
		<input type="hidden" id="allPage" name="allPage" value="${allPage}" />
		<input type="hidden" id="pid" name="point.id" value="${point.id}" />
		<input type="hidden" id="nowpage" name="nowpage" value="${i}" />
	</form>
  -->
	<form action="findQuestion.action" method="post" name="sform"
		id="listform">
		<input type="hidden" id="allPage" name="allPage" value="${allPage}" />
		<input type="hidden" id="pid" name="point.id" value="${point.id}" />
		<input type="hidden" id="nowpage" name="nowpage" value="${i}" />
		<div class="panel admin-panel">
			<div class="panel-head">
				<strong class="icon-reorder"> 试题列表</strong> <a href=""
					style="float:right; display:none;">添加字段</a>
			</div>
			<div class="padding border-bottom">
				<ul class="search" style="padding-left:10px;">


					<li><select name="subid" class="input"
						style="width:200px; line-height:17px;"
						onchange="getPoint(this.value)">
							<option value="-1">--请选择科目--</option>
							<c:forEach items="${subjectList }" var="item">
								<option
									<c:if test="${item.id eq subid }" >selected='selected' </c:if>
									value="${item.id}">${item.sname}</option>
							</c:forEach>
					</select></li>
					<li><select id="point" class="input" name="pid">
							<option selected value="-1"
							<c:if test="${point.id eq pid }" >selected='selected' </c:if>>请选择</option>
					</select></li>
					<li><input type="submit"
						class="button border-main icon-plus-square-o" value="搜索"></li>
				</ul>
			</div>
			<table class="table table-hover text-center">
				<tr>
					<th width="100">编号</th>
					<th>试题题目</th>
					<th>解析</th>
					<th>答案</th>
					<th width="310">操作</th>
				</tr>

				<tr>

					<c:forEach items="${questionList }" var="question">
						<tr>
							<td>${question.id}</td>
							<td><c:if test="${question.qtitle.length() >= 30}">${question.qtitle.substring(0,30)}...</c:if>
								<c:if test="${question.qtitle.length() < 30}">${question.qtitle}</c:if></td>
							<td>${question.analysis}</td>
							<td>${question.result}</td>

							<td>
								<div class="button-group">
									<a class="button border-main"
										href="tiaoupdateQuestion.action?qid=${question.id }"><span
										class="icon-edit"></span> 修改</a>
								</div>
							</td>
						</tr>
					</c:forEach>
				<tr>
					<td colspan="8"><div class="pagelist">
							<a href="javascript:changepage(${1 })">首页</a> <a
								href="javascript:changepage(${nowpage-1 })">上一页</a>
							<c:forEach var="i" step="1" begin="1" end="${allPage}">
								<a href="javascript:changepage(${i})">${i}&nbsp;&nbsp;</a>
							</c:forEach>
							<a href="javascript:changepage(${nowpage+1})">下一页</a> <a
								href="javascript:changepage(${allPage })">尾页</a>

						</div></td>
				</tr>

			</table>
		</div>
	</form>
	<script type="text/javascript">
		//搜索
		function changesearch() {

		}

		//单个删除
		function del(id, mid, iscid) {
			if (confirm("您确定要删除吗?")) {

			}
		}

		//全选
		$("#checkall").click(function() {
			$("input[name='id[]']").each(function() {
				if (this.checked) {
					this.checked = false;
				} else {
					this.checked = true;
				}
			});
		})

		//批量删除
		function DelSelect() {
			var Checkbox = false;
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
				}
			});
			if (Checkbox) {
				var t = confirm("您确认要删除选中的内容吗？");
				if (t == false)
					return false;
				$("#listform").submit();
			} else {
				alert("请选择您要删除的内容!");
				return false;
			}
		}

		//批量排序
		function sorts() {
			var Checkbox = false;
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
				}
			});
			if (Checkbox) {

				$("#listform").submit();
			} else {
				alert("请选择要操作的内容!");
				return false;
			}
		}

		//批量首页显示
		function changeishome(o) {
			var Checkbox = false;
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
				}
			});
			if (Checkbox) {

				$("#listform").submit();
			} else {
				alert("请选择要操作的内容!");

				return false;
			}
		}

		//批量推荐
		function changeisvouch(o) {
			var Checkbox = false;
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
				}
			});
			if (Checkbox) {

				$("#listform").submit();
			} else {
				alert("请选择要操作的内容!");

				return false;
			}
		}

		//批量置顶
		function changeistop(o) {
			var Checkbox = false;
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
				}
			});
			if (Checkbox) {

				$("#listform").submit();
			} else {
				alert("请选择要操作的内容!");

				return false;
			}
		}

		//批量移动
		function changecate(o) {
			var Checkbox = false;
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
				}
			});
			if (Checkbox) {

				$("#listform").submit();
			} else {
				alert("请选择要操作的内容!");

				return false;
			}
		}

		//批量复制
		function changecopy(o) {
			var Checkbox = false;
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
				}
			});
			if (Checkbox) {
				var i = 0;
				$("input[name='id[]']").each(function() {
					if (this.checked == true) {
						i++;
					}
				});
				if (i > 1) {
					alert("只能选择一条信息!");
					$(o).find("option:first").prop("selected", "selected");
				} else {

					$("#listform").submit();
				}
			} else {
				alert("请选择要复制的内容!");
				$(o).find("option:first").prop("selected", "selected");
				return false;
			}
		}
	</script>
</body>
</html>