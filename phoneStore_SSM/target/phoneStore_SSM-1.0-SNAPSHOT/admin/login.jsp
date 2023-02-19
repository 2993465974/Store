<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>登录</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/bootstrap.css">
</head>

<!-- 设置背景图片 -->
<body style="background: #0ea4d6">

	<div class="container">

		<c:if test="${message!=null}"><div class="alert alert-danger text-center">${message}</div></c:if>

		<form class="form-horizontal" action="login" method="post" style="margin-top:15%;">
			<h2 class="text-center">管理员登录后台</h2>
			<div class="form-group">
				<div class="col-md-4 col-md-offset-4">
					<input type="text" class="form-control" style="height:auto;padding:10px;" placeholder="输入用户名" name="username" value="菜稽">
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-4 col-md-offset-4">
					<input type="password" class="form-control" style="height:auto;padding:10px;" placeholder="输入密码" name="password" value="123456">
				</div>
			</div>
			<div class="col-md-4 col-md-offset-4">			
				<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
			</div>
		</form>

	</div>

</body>
</html>
