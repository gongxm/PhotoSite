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
.table2 {
	border: 1px solid #f3f3f3;
	width: 100%;
	height: 80%;
	border: 0;
	cellspacing: 0;
	cellpadding: 0;
}

.table {
	border: 1px solid #f3c3c3;
	width: 300px;
}

td {
	border: 1px solid #c3f3c3;
}
</style>

</head>
<body>

	<h1 align="center">用户注册</h1>

	<table class="table2">
		<tr>
			<td align="center">
				<form
					action="${pageContext.request.contextPath}/servlet/RegistServlet"
					method="post">
					<table class="table">
						<tr>
							<td>用户名:</td>
							<td><input type="text" name="username"
								value="${sessionScope.bean.username}" /></td>
						</tr>
						<tr>
							<td>密码:</td>
							<td><input type="password" name="password"
								value="${sessionScope.bean.password}" /></td>
						</tr>
						<tr>
							<td>确认密码:</td>
							<td><input type="password" name="repassword" /></td>
						</tr>
						<tr>
							<td align="center"><input type="reset" value="清空" /></td>
							<td align="center"><input type="submit" value="注册" /></td>
						</tr>
						<tr>
							<td align="center"><a
								href="${pageContext.request.contextPath}">回到主页</a></td>
							<td align="center"><a
								href="${pageContext.request.contextPath}/login.jsp">已有账号，去登陆</a></td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table>
</body>
</html>
