<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户登陆</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<style type="text/css">
td {
	border: 1px solid #c3f3c3;
}

.table {
	border: 1px solid #f3c3c3;
	width: 300px;
}

.table2 {
	width: 100%;
	height: 80%;
	border: 0;
	cellspacing: 0;
	cellpadding: 0;
	align: center;
	text-align: center;
}


</style>
</head>
<body>
	<h1 align="center">用户登陆</h1>
	<table class="table2">
		<tr>
			<td align="center">
				<form
					action="${pageContext.request.contextPath}/servlet/LoginServlet"
					method="post">
					<table class="table">
						<tr>
							<td>用户名:</td>
							<td><input type="text" name="username" /></td>
						</tr>
						<tr>
							<td>密码:</td>
							<td><input type="password" name="password" /></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input type="checkbox" name="remember" />记住我</td>
						</tr>
						<tr>
							<td align="center"><input type="reset" value="清空" /></td>
							<td align="center"><input type="submit" value="登陆" /></td>
						</tr>

						<tr>
							<td><a href="${pageContext.request.contextPath}">回到主页</a></td>
							<td><a href="${pageContext.request.contextPath}/regist.jsp">还没有账号？去注册</a></td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table>

</body>
</html>
