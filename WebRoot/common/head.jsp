<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>图片主页</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<style>
#back{
	float:right;
	font-size:20px;
	color:blue;
}
body{
	background-color:#ff80ff;
}
</style>


</head>
<body>
<h1>XXX图片站</h1>

<c:if test="${sessionScope.user!=null}">
欢迎您：${user.username}!<a href="${pageContext.request.contextPath}/servlet/Logout">注销</a>
</c:if>
<c:if test="${sessionScope.user==null}">
	<a href="${pageContext.request.contextPath}/regist.jsp">注册</a>
	<a href="${pageContext.request.contextPath}/login.jsp">登陆</a>
</c:if>
<div id="back"><a href="${pageContext.request.contextPath}/admin">后台管理</a></div>
<hr />