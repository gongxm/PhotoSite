<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
	function jump() {
		var num = document.getElementById("num").value;
		window.location.href = "${pageContext.request.contextPath}/servlet/ShowList?index="
				+ num;
	}
</script>
<div align="center">
	<a href="${page.url}1">首页</a> <a href="${page.url}${page.previousPage}">上一页</a>
	<c:forEach begin="${page.startIndex}" end="${page.endIndex}" var="num">
		<a href="${page.url}${num}">${num}</a>
	</c:forEach>
	<a href="${page.url}${page.nextPage}">下一页</a> <a
		href="${page.url}${page.totalPageNum}">尾页</a> 跳到：<input type="text"
		id="num" size="3" value="${page.currentPage}" onchange="jump()" />页
</div>