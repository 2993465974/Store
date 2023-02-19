<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>客户列表</title>
<meta charset="utf-8"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/bootstrap.css"/>
</head>
<body>
<div class="container-fluid">

	<%@include file="header.jsp"%>
	
	<div class="text-right"><a class="btn btn-warning" href="toAddUserPage">添加客户</a></div>
		
	<br>
	
	<form class="form-inline" action="${pageContext.request.contextPath}/admin/userSearch" id="serchform" method="post">
		<div class="form-group">
			<input type="text" class="form-control" id="name" name="username" placeholder="待查找的用户名">
		</div>
		<a href="javascript:;" onclick="document.getElementById('serchform').submit();" type="submit" class="btn btn-primary">点击搜索</a>
	</form>
	
	<br>
	
	<table class="table table-bordered table-hover">

	<tr align="center">
		<th width="5%">ID</th>
		<th width="10%">用户名</th>
		<th width="10%">收货人</th>
		<th width="10%">电话</th>
		<th width="10%">地址</th>
		<th width="10%">操作</th>
	</tr>
	
	<c:forEach var="user" items="${userList}">
         <tr align="center">
         	<td><p>${user.uid}</p></td>
         	<td><p>${user.username}</p></td>
         	<td><p>${user.realname}</p></td>
         	<td><p>${user.phone}</p></td>
         	<td><p>${user.address}</p></td>
			<td>
				<a class="btn btn-info" href="resetPassword?uid=${user.uid}">重置密码</a>
				<a class="btn btn-primary" href="toEditUserPage?uid=${user.uid}">修改</a>
				<a class="btn btn-danger" href="deleteUser?uid=${user.uid}">删除</a>
			</td>
       	</tr>
     </c:forEach>
     
</table>

<%--<br>${pageTool}<br>--%>
<br id="tip">${message}<br>
</div>
</body>
</html>