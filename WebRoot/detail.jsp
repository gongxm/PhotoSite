<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>${sessionScope.image.filename}</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<c:url  value="/servlet/Download" var="url">
		<c:param name="id" value="${sessionScope.image.id}"></c:param>
	</c:url>
	<style type="text/css">
		#detail{
			border:1px solid #f3c3f3;
			margin-left:30%;
		}
		#down{
			margin-left:40%;
		}
		#copyright{
			font-size:20px;
			color:red;
			margin-left:30%;
		}
	</style>
  </head>
  <body>
  <jsp:include page="common/head.jsp"></jsp:include>
	<table id="detail">
		<caption>${sessionScope.image.filename}</caption>
		<tr>
			<td><img alt="" src="${url}"></td>
		</tr>
		<tr>
			<td><a href="${url}" id="down">下载</a></td>
		</tr>
	</table>
	
	<jsp:include page="common/foot.jsp"></jsp:include>
  </body>
</html>
