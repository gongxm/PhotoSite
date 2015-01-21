<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>网站后台</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	function update() {
		//if (confirm("确认要更新文件夹？"))
		//location.href = "${pageContext.request.contextPath}/servlet/UpdataImages";
	}
	function kit() {
		location.href = "${pageContext.request.contextPath}/servlet/KitUser";
	}
	function httpdownload() {
		var url = document.getElementById("url").value;
		if (url.length == 0) {
			alert("请输入文件地址!");
		} else {
			location.href = "${pageContext.request.contextPath}/servlet/UrlDownload?uri="
					+ url;
		}
	}
	function check() {
		var str = document.getElementById("upload").value;
		if (str.length == 0) {
			alert("请选择上传的文件!");
		} else {
			document.forms[0].submit();
		}
	}
	//解压已下载的zip文件
	function unzip() {
		location.href = "${pageContext.request.contextPath}/servlet/Unzip";
	}

	//重置远程下载状态
	function resetStatus() {
		location.href = "${pageContext.request.contextPath}/servlet/Reset";
	}
	//进入菜单管理页面
	function menu() {
		location.href = "${pageContext.request.contextPath}/servlet/EditMenus";
	}
	//清空图片数据库
	function clearDB() {
		if (confirm("确认要清空数据库？"))
			location.href = "${pageContext.request.contextPath}/servlet/ClearDB";
	}
	//开始采集网站图片
	function getImages() {
		if (confirm("确认要开始采集网站图片？")){
		//获取当前选中的类型
			var selectNode=document.getElementById("category");
			var index=selectNode.selectedIndex;
			var category=selectNode.options[index].value;
			//获取开始位置和结束位置
			var startIndex=document.getElementById("start").value;
			var endIndex=document.getElementById("end").value;
			location.href = "${pageContext.request.contextPath}/servlet/GetImages?category="+category+"&startIndex="+startIndex+"&endIndex="+endIndex;
		}
	}
</script>


<style type="text/css">
table {
	margin-left: 100px;
	border: 1px solid #f3c3f3;
	width: 300px;
}

.div1 {
	margin-top: 10px;
	margin-left: 200px;
}
</style>

</head>
<body>
	<h1>网站后台</h1>
	<form action="${pageContext.request.contextPath}/servlet/UploadZip"
		method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>更新图片文件夹</td>
				<td><input type="button" value="更新" onclick="update()" /></td>
			</tr>
			<tr>
				<td>查看在线用户</td>
				<td><input type="button" value="查看在线用户" onclick="kit()" /></td>
			</tr>
			<tr>
				<td>上传图片zip包<input type="file" name="zip" id="upload" />
				</td>
				<td><input type="button" value="上传" onclick="check()" /></td>
			</tr>
			<tr>
				<td>远程下载<input type="text" id="url" name="url" /></td>
				<td><input type="button" value="远程下载" onclick="httpdownload()" /></td>
				<td><input type="button" value="下载重置" onclick="resetStatus()" /></td>
			</tr>
			<tr>
				<td>解压下载</td>
				<td><input type="button" value="解压缩" onclick="unzip()" /></td>
			</tr>
			<tr>
				<td>清空数据库</td>
				<td><input type="button" value="清空数据库" onclick="clearDB()" /></td>
			</tr>
			<tr>
				<td>类型：<select id="category">
						<option value="glamour">glamour</option>
						<option value="beauty">beauty</option>
						<option value="photo">photo</option>
						<option value="korea">korea</option>
						<option value="beautyleg">beautyleg</option>
						<option value="cosplay">cosplay</option>
						<option value="jiepaimeinv">jiepaimeinv</option>
				</select></td>
				<td colspan="2">开始位置：<input type="text" id="start" value="1" style="width:20px">结束位置：<input type="text" id="end" value="20" style="width:20px"></td>
			</tr>
			<tr>
				<td>开始采集</td>
				<td><input type="button" value="采集" onclick="getImages()" /></td>
			</tr>
			<tr>
			<td>菜单管理</td>
			<td><input type="button" value="进入" onclick="menu()" /></td>
			</tr>
		</table>
	</form>
	<div class="div1">
		<a href="${pageContext.request.contextPath}/">回到主页</a>
	</div>
</body>
</html>
