<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="common/head.jsp" %>
<style type="text/css">
#main {
	border: 1px solid #c3f3c3;
	margin: 100px;
	width: 80%;
}
#copyright{
	margin-left: 40%;
	color: #0000ff;
	font-size: 20px;
}
.div2{
	float:right;
	margin-right:200px;
	font-size:25px;
	color:blue;
}
</style>
	
	<h1>本站有好多美女：</h1>
	<div class="div2">
		<a href="${pageContext.request.contextPath}/servlet/ShowList">进入图片列表</a>
	</div>
	<table id="main">
		<tr>
			<c:forEach items="${page.records}" var="image" varStatus="vs">
				<td><c:url value="/servlet/Download" var="url">
						<c:param name="id" value="${image.id}"></c:param>
					</c:url> <a
					href="${pageContext.request.contextPath}/servlet/Detail?id=${image.id}">
						<img alt="" src="${url}" width="150px" height="200px" /> <br />
						${image.filename}
				</a></td>
				<c:if test="${vs.count%4==0}">
					<tr />
					<tr>
				</c:if>
			</c:forEach>
		</tr>
	</table>
	<div id="copyright">gonxm&trade; 版权所有 &reg; &copy;2014.6.29</div>
	<br />
	<br />
</body>
</html>
