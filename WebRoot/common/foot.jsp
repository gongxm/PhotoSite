<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
.div1 {
	margin-left: 45%;
	font-size: 28px;
	color: #0000ff;
}

#copyright {
	margin-left: 40%;
	color: #0000ff;
	font-size: 20px;
}

a{
	text-decoration: none;
}

td{
	padding: 5px;
}

</style>
<h1>好图推荐：</h1>
<table align="center">

	<tr>
		<c:forEach items="${recomm.records}" var="item">
			<td align="center"><a href="${pageContext.request.contextPath}/servlet/Detail?id=${item.id}">
					<img alt="" src="${pageContext.request.contextPath}/getImage?id=${item.id}" width="150px" height="200px" /> <br/>
					${item.id}
			</a></td>
		</c:forEach>
	</tr>

</table>


<br />
<br />
<div align="center">
	<a href="${pageContext.request.contextPath}/">回到主页</a>
</div>
<hr />

<div id="copyright">gonxm&trade; 版权所有 &reg; &copy;2014.6.29</div>
<br />
<br />
<br />
