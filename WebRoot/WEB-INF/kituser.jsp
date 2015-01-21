<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>在线用户列表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
#user {
	border: 1px solid #f3c3f3;
	margin-left: 100px;
	width: 200px;
}


td {
	border: 1px solid #c3f3c3;
}
</style>
<script type="text/javascript">
	function kit(name) {
		if (confirm("真的要踢下线吗？")) {
			location.href = "${pageContext.request.contextPath}/servlet/KitUser?username="
					+ name;
		}
	}
</script>

</head>
<body>
	<h1>在线用户列表</h1>
	<table id="user">
		<c:forEach items="${sessionScope.online}" var="map">
			<tr>
				<td>${map.key}</td>
				<td><input type="button" value="踢下线"
					onclick="kit('${map.key}')" /></td>
			</tr>
		</c:forEach>
		<tr>
			<td><a href="${pageContext.request.contextPath}/">回到主页</a></td>
			<td><a href="${pageContext.request.contextPath}/admin">后台管理</a>
			</td>
		</tr>
	</table>

</body>
</html>
