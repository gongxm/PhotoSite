<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>${sessionScope.image.id}</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<style type="text/css">
#detail {
	border: 1px solid #f3c3f3;
}


#image{
	max-width:auto;
}
</style>

<script type="text/javascript">
	function next() {
		var index=${page.currentPage}+1;
		if(index>${page.totalPageNum}){
			var id=${sessionScope.image.id}+1;
			location.href = "${pageContext.request.contextPath}/servlet/Detail?id="+id;
			return;
		}
		location.href = "${pageContext.request.contextPath}/servlet/Detail?id=${sessionScope.image.id}&index="+index;
	}
	function download(){
		location.href = "${pageContext.request.contextPath}/servlet/Download?id=${sessionScope.image.id}&index="+${page.currentPage};
	}
</script>
</head>
<body>
	<jsp:include page="common/head.jsp"></jsp:include>
	<table id="detail" align="center">
		<caption>${sessionScope.image.id}(${page.currentPage}/${page.totalPageNum})</caption>
		<tr>
			<td><img alt="${sessionScope.image.filename}" src="${pageContext.request.contextPath}/getImage?id=${sessionScope.image.id}"
				id="image" onclick="next()"></td>
		</tr>
		<tr>
			<td align="center"><input type="button" value="下载" onclick="download()"/></td>
		</tr>
		<tr>
			<td><jsp:include page="common/page.jsp" /></td>
		</tr>
	</table>

	<jsp:include page="common/foot.jsp"></jsp:include>
</body>
</html>
