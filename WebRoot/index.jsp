<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="common/head.jsp"%>
<style type="text/css">
#main {
	border: 1px solid #f3c3f3;
	margin-left: 10%;
	margin-top: 5%;
	margin-bottom: 5%;
	width: 80%;
}

#copyright {
	color: #0000ff;
	font-size: 20px;
	margin-bottom: 5%;
}

.list {
	float: right;
	margin-right: 200px;
	font-size: 25px;
	color: blue;
}
a{
 	text-decoration:none;
}
</style>

<h1>本站有好多美女：</h1>
<div class="list">
	<a href="${pageContext.request.contextPath}/servlet/ShowList">进入图片列表</a>
</div>
<table id="main" align="center">
	<tr>
		<c:forEach items="${page.records}" var="image" varStatus="vs">
			<td align="center"><a
				href="${pageContext.request.contextPath}/servlet/Detail?id=${image.id}">
					<img alt="${image.filename}"
					src="${pageContext.request.contextPath}/getImage?id=${image.id}"
					width="150px" height="200px" /><br /> ${image.id}
			</a></td>
			<c:if test="${vs.count%4==0}">
				<tr />
				<tr>
			</c:if>
		</c:forEach>
	</tr>
</table>
<div id="copyright" align="center">gonxm&trade; 版权所有 &reg;
	&copy;2014.6.29</div>
<br />
<br />
</body>
</html>
