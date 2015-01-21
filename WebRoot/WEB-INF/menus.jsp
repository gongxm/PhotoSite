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
	function deleteMenu(id) {
		if (confirm("是否删除该菜单？")) {
			location.href = "${pageContext.request.contextPath}/servlet/EditMenus?id="
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
		}
	}

	//添加菜单
	function addMenu() {
		var menu = document.getElementById("menu").value;
		var name = document.getElementById("name").value;

		if (menu == "" || menu == "请输入菜单英文") {
			alert("请输入菜单英文！");
			return;
		} else if (name == "" || name == "请输入菜单名") {
			alert("请输入菜单名！");
			return;
		}
		if (confirm("是否添加该菜单？")) {
			location.href = "${pageContext.request.contextPath}/servlet/EditMenus?menu="+menu+"&name="+name;
		}
	}
</script>
</head>

<body>
	<h1>菜单列表</h1>
	<table id="menus">
		<c:forEach items="${sessionScope.menus}" var="menus">
			<tr>
				<td>${menus.menu}</td>
				<td>${menus.name}</td>
				<td><input type="button" value="删除"
					onclick="deleteMenu('${menus.id}')"></td>
			</tr>
		</c:forEach>
		<tr>
			<td><input type="text" id="menu" value="请输入菜单英文"
				onclick="check('menu')" onblur="check2('menu')"></td>
			<td><input type="text" id="name" value="请输入菜单名"
				onclick="check('name')" onblur="check2('name')"></td>
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
