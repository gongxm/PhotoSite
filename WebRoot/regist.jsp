<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户注册</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
#regist {
	border: 1px solid #f3c3f3;
	margin-top:100px;
	margin-left: 30%;
	width:300px;
}

td {
	border: 1px solid #c3f3c3;
}
</style>

</head>
<body>
	<div id="regist">
		<h1>用户注册</h1>
		<form
			action="${pageContext.request.contextPath}/servlet/RegistServlet"
			method="post">
			<table>
				<tr>
					<td>用户名：</td>
					<td><input type="text" name="username"
						value="${sessionScope.bean.username}" /></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="password" name="password"
						value="${sessionScope.bean.password}" /></td>
				</tr>
				<tr>
					<td>确认密码：</td>
					<td><input type="password" name="repassword" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="reset" value="清空" /> <input
						type="submit" value="注册" /></td>
				</tr>
			</table>
		</form>
		<a href="${pageContext.request.contextPath}">回到主页</a> <a
			href="${pageContext.request.contextPath}/login.jsp">已有账号，去登陆</a>
	</div>
</body>
</html>
