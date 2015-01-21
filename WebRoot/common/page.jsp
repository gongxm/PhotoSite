<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<a href="${page.url}?index=1">首页</a>
		<a href="${page.url}?index=${page.previousPage}">上一页</a>
		<c:forEach begin="${page.startIndex}" end="${page.endIndex}" var="num">
		<a href="${page.url}?index=${num}">${num}</a>
		</c:forEach>
		<a href="${page.url}?index=${page.nextPage}">下一页</a>
		<a href="${page.url}?index=${page.totalPageNum}">尾页</a>
		跳到：<input type="text" id="num" size="3" value="${page.currentPage}" onchange="jump()"/>页
