<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>用户注册</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resource_index/css/bootstrap.css">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resource_index/css/style.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource_index/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource_index/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource_index/js/simpleCart.min.js"></script>
	<style type="text/css">
		input{
			-moz-border-radius: 10px;
			-webkit-border-radius: 10px;
			border-radius: 10px;
			box-shadow: 2px 0px 15px #87cefa;
		}
	</style>
</head>
<body>

	<jsp:include page="header.jsp"/>
	
	<!--account-->
	<div class="account">
		<div class="container">
			<div class="register">
				<c:if test="${message!=null}"><div class="alert alert-danger">${message}</div></c:if>
				<form action="register" method="post"> 
					<div class="register-top-grid">
						<h3>注册新用户</h3>
						<div class="input">
							<span>用户名 <label style="color:red;">*</label></span>
							<input type="text" name="username" placeholder="请输入用户名" required="required"> 
						</div>
						<div class="input">
							<span>密码 <label style="color:red;">*</label></span>
							<input type="text" name="password" placeholder="请输入密码" required="required"> 
						</div>
						<div class="input">
							<span>收货人<label style="color:red;">*</label></span>
							<input type="text" name="realname" placeholder="请输入收货人">
						</div>
						<div class="input">
							<span>电话<label style="color:red;">*</label></span>
							<input type="text" name="phone" placeholder="请输入收货电话"> 
						</div>
						<div class="input">
							<span>收货地址<label style="color:red;">*</label></span>
							<input type="text" name="address" placeholder="请输入收货地址"> 
						</div>
						<div class="input">
							<span>输入验证码<label style="color:red;">*  <button>发送短信</button></label></span>
							<input type="text" name="address" placeholder="请输入短信验证码">

						</div>
						<div class="clearfix"> </div>
					</div>
					<div class="register-but text-center">
					   <input type="submit" value="提交">
					   <div class="clearfix"> </div>
					</div>
				</form>
				<div class="clearfix"> </div>
			</div>
	    </div>
	</div>
	<!--//account-->

	<jsp:include page="footer.jsp"/>
	
</body>
</html>