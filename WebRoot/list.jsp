<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>图片列表页</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<style type="text/css">
table {
	border: 1px solid #f3c3f3;
	margin: 100px;
}
</style>

<script type="text/javascript">
	function jump() {
		var num = document.getElementById("num").value;
		window.location.href = "${pageContext.request.contextPath}/servlet/ShowList?index="
				+ num;
	}
</script>
</head>
<body>
	<jsp:include page="common/head.jsp"></jsp:include>
	<h1>图片列表页</h1>
	<table>
		<tr>
			<c:forEach items="${page.records}" var="image" varStatus="vs">
				<td><c:url value="/servlet/Download" var="url">
						<c:param name="id" value="${image.id}"></c:param>
					</c:url> <a	href="${pageContext.request.contextPath}/servlet/Detail?id=${image.id}">
						<img alt="" src="${url}" width="150px" height="200px" /> <br />
						${image.filename}
				</a></td>
				<c:if test="${vs.count%4==0}">
					</tr><tr>
				</c:if>
			</c:forEach>
		</tr>
		<tr>
			<td colspan="4"><jsp:include page="common/page.jsp"></jsp:include></td>
		</tr>
	</table>
	<jsp:include page="common/foot.jsp"></jsp:include>
	<br />
	<br />
	<br />
</body>
</html>
