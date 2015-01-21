<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户登陆</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<style type="text/css">
#login {
	border: 1px solid #c3f3c3;
	margin-left: 30%;
	margin-top:100px;
	width: 300px;
}

td {
	border: 1px solid #c3f3c3;
}
</style>
</head>
<body>
	<div id="login">
		<h1>用户登陆</h1>
		<form action="${pageContext.request.contextPath}/servlet/LoginServlet" method="post">
			<table>
				<tr>
					<td>用户名：</td>
					<td><input type="text" name="username" /></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="remember" />记住我</td>
				</tr>
				<tr>
					<td><input type="reset" value="清空" /></td>
					<td><input type="submit" value="登陆" /></td>
				</tr>
			</table>
		</form>
		<a href="${pageContext.request.contextPath}">回到主页</a> <a
			href="${pageContext.request.contextPath}/regist.jsp">还没有账号？去注册</a>
	</div>
</body>
</html>
