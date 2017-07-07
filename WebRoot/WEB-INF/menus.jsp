<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>菜单管理页面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
#menus {
	border: 1px solid #f3c3f3;
	margin-left: 100px;
	width: 200px;
}

td {
	border: 1px solid #c3f3c3;
}
</style>
<script type="text/javascript">
	//删除菜单
	function deleteMenu(id) {
		if (confirm("是否删除该菜单？")) {
			location.href = "${pageContext.request.contextPath}/servlet/EditMenus?operate=delete&id="
					+ id;
		}
	}
	function check(id) {
		var node = document.getElementById(id);
		var value = node.value;
		if (id == "menu") {
			if (value == "请输入菜单英文")
				node.value = "";
		} else if (id == "name") {
			if (value == "请输入菜单名")
				node.value = "";
		} else if (id == "start") {
			if (value == "请输入开始位置")
				node.value = "";
		} else if (id == "end") {
			if (value == "请输入结束位置")
				node.value = "";
		}
	}
	function check2(id) {
		var node = document.getElementById(id);
		var value = node.value;
		if (id == "menu") {
			if (value == "") {
				node.value = "请输入菜单英文";
			}
		} else if (id == "name") {
			if (value == "") {
				node.value = "请输入菜单名";
			}
		} else if (id == "start") {
			if (value == "") {
				node.value = "请输入开始位置";
			}
		} else if (id == "end") {
			if (value == "") {
				node.value = "请输入结束位置";
			}
		}
	}

	//添加菜单
	function addMenu() {
		var menu = document.getElementById("menu").value;
		var name = document.getElementById("name").value;
		var start = document.getElementById("start").value;
		var end = document.getElementById("end").value;

		if (menu == "" || menu == "请输入菜单英文") {
			alert("请输入菜单英文！");
			return;
		} else if (name == "" || name == "请输入菜单名") {
			alert("请输入菜单名！");
			return;
		} else if (start == "" || start == "请输入开始位置") {
			alert("请输入开始位置！");
			return;
		} else if (end == "" || end == "请输入结束位置") {
			alert("请输入结束位置！");
			return;
		}
		if (confirm("是否添加该菜单？")) {
			location.href = "${pageContext.request.contextPath}/servlet/EditMenus?operate=add&menu="
					+ menu
					+ "&name="
					+ name
					+ "&startIndex="
					+ start
					+ "&endIndex=" + end;
		}
	}

	//编辑菜单
	function editMenu(id, node) {
		var value = node.value;
		var trNode = node.parentElement.parentElement;
		var tdArr = trNode.children;
		if (value == "编辑") {
			node.value = "完成";
			var menu = tdArr[0].innerText;
			var name = tdArr[1].innerText;
			var start = tdArr[2].innerText;
			var end = tdArr[3].innerText;
			tdArr[0].innerHTML = "<input type='text' id='newmenu' value='"+menu+"'>";
			tdArr[1].innerHTML = "<input type='text' id='newname' value='"+name+"'>";
			tdArr[2].innerHTML = "<input type='text' id='newstart' value='"+start+"'>";
			tdArr[3].innerHTML = "<input type='text' id='newend' value='"+end+"'>";
		} else {
			node.value = "编辑";
			var mvalue = document.getElementById("newmenu").value;
			var nvalue = document.getElementById("newname").value;
			var svalue = document.getElementById("newstart").value;
			var evalue = document.getElementById("newend").value;
			if (mvalue == "" || nvalue == ""||svalue==""||evalue=="") {
				alert("数据不能为空！");
				return;
			}
			if (confirm("是否修改该菜单？")) {
				location.href = "${pageContext.request.contextPath}/servlet/EditMenus?operate=modify&menu="
						+ mvalue + "&name=" + nvalue + "&id=" + id+ "&startIndex="
					+ svalue
					+ "&endIndex=" + evalue;
			} else {
				location.href = "${pageContext.request.contextPath}/servlet/EditMenus";
			}
		}
	}
</script>
</head>

<body>
	<h1>菜单列表</h1>
	<table id="menus">
		<c:forEach items="${sessionScope.menus}" var="menu">
			<tr>
				<td>${menu.menu}</td>
				<td>${menu.name}</td>
				<td>${menu.startIndex}</td>
				<td>${menu.endIndex}</td>
				<td><input type="button" value="删除"
					onclick="deleteMenu('${menu.id}')"></td>
				<td><input type="button" value="编辑"
					onclick="editMenu('${menu.id}',this)"></td>
			</tr>
		</c:forEach>
		<tr>
			<td><input type="text" id="menu" value="请输入菜单英文"
				onclick="check('menu')" onblur="check2('menu')"></td>
			<td><input type="text" id="name" value="请输入菜单名"
				onclick="check('name')" onblur="check2('name')"></td>
			<td><input type="text" id="start" value="请输入开始位置"
				onclick="check('start')" onblur="check2('start')"></td>
			<td><input type="text" id="end" value="请输入结束位置"
				onclick="check('end')" onblur="check2('end')"></td>
			<td><input type="button" value="添加" onclick="addMenu()"></td>
		</tr>
		<tr>
			<td><a href="${pageContext.request.contextPath}/">回到主页</a></td>
			<td><a href="${pageContext.request.contextPath}/admin">后台管理</a>
			</td>
		</tr>
	</table>
</body>
</html>
