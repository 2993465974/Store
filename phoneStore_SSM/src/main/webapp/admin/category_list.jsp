<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>类目列表</title>
<meta charset="utf-8"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/bootstrap.css"/>
</head>
<body>
<div class="container-fluid">

	<%@include file="header.jsp"%>
	
	<br>
	
	<form class="form-inline" action="categoryList" id="serchform" method="get">
		<div class="form-group">
			<input type="text" class="form-control" id="name" name="cname" placeholder="类目名称">
		</div>
		<a href="javascript:;" onclick="document.getElementById('serchform').submit();" type="submit" class="btn btn-primary">点击搜索</a>
	</form>
	
	<br>

	<div>
		<form class="form-inline" method="post" action="addCategory">
			<input type="text" class="form-control" id="input_name" name="cname" placeholder="输入类目名称" required="required" style="width: 500px">
			<input type="submit" class="btn btn-warning" value="添加类目"/>
		</form>
	</div>
	
	<br>

	<table class="table table-bordered table-hover">

	<tr>
		<th width="5%">ID</th>
		<th width="10%">名称</th>
		<th width="10%">操作</th>
	</tr>
	
	<c:forEach var="category" items="${categoryList}">
         <tr>
         	<td><p>${category.cid}</p></td>
         	<td><p>${category.cname}</p></td>
			<td>
				<a class="btn btn-primary" href="typeEdit?id=${category.cid}">修改</a>
				<a class="btn btn-danger" href="typeDelete?id=${category.cid}">删除</a>
			</td>
       	</tr>
     </c:forEach>
     
</table>

</div>
</body>
</html>