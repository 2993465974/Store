<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>购物车</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resource_index/css/bootstrap.css">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resource_index/css/style.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource_index/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource_index/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource_index/layer/layer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource_index/js/cart.js"></script>
</head>
<body>
	
	<jsp:include page="header.jsp"/>
	
	<!--cart-items-->
	<div class="cart-items">
		<div class="container">
		
		<c:if test="${message!=null}"><div class="alert alert-success">${message}</div></c:if>
		<c:if test="${order!=null}">
			<h2>我的购物车</h2>
			
			<c:forEach var="item" items="${order.orderItems}">
				<div class="cart-header col-md-6">
					<div class="cart-sec simpleCart_shelfItem">
						<div class="cart-item cyc">
							<a href="detail?goodsgid=${item.goods.gid}">
								<img src="${item.goods.images1}" class="img-responsive">
							</a>
						</div>
						<div class="cart-item-info">
							<h3><a href="detail?goodsgid=${item.goods.gid}">${item.goods.gname}</a></h3>
							<h3><span>单价: ¥ ${item.goods.price}</span></h3>
							<h3><span>数量: ${item.amount}</span></h3>
							<a class="btn btn-info" href="javascript:buy(${item.goods.gid});">增加</a>
							<c:if test="${item.amount != 0}">
								<a class="btn btn-warning" href="javascript:lessen(${item.goods.gid});">减少</a>
							</c:if>
							<a class="btn btn-danger" href="javascript:deletes(${item.goods.gid});">删除</a>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
			</c:forEach>
			
			<div class="cart-header col-md-12">
				<hr>
				<h3>订单总金额: ¥ ${order.total}</h3>
				<a class="btn btn-success btn-lg" style="margin-left:74%" href="submitOrder?oid=${order.oid}">提交订单</a>
			</div>
			</c:if>
			<c:if test="${order==null}"><div class="alert alert-info">空空如也</div></c:if>
			
		</div>
	</div>
	<!--//cart-items-->	
	
	<jsp:include page="footer.jsp"/>

</body>
</html>
<script>

	/**
	 * 加入购物车
	 */
	function buy(goodsgid){
		$.post("operation.action", {goodsgid:goodsgid,type:1}, function(data){
			if(data=="ok"){
				layer.msg("操作成功!", {time:800}, function(){
					location.reload();
				});
			}else if(data=="login"){
				alert("请登录后再加入购物车!");
				location.href="${pageContext.request.contextPath}/index/toLoginPage?flag=-1";
			}else if(data=="noStack"){
				alert("库存不足!");
				location.reload();
			}else{
				alert("请求失败!");
			}
		});
	}
	/**
	 * 购物车减去
	 */
	function lessen(goodsgid){
		$.post("operation.action", {goodsgid:goodsgid,type:2}, function(data){
			if(data=="ok"){
				layer.msg("操作成功!", {time:800}, function(){
					location.reload();
				});
			}else if(data=="login"){
				alert("请登录后再操作!");
				location.href="${pageContext.request.contextPath}/index/toLoginPage?flag=-1";
			}else if(data=="empty"){
				alert("库存不足!");
				location.reload();
			}else{
				alert("请求失败!");
			}
		});
	}
	/**
	 * 购物车删除
	 */
	function deletes(goodsgid){
		$.post("operation.action", {goodsgid:goodsgid,type:3}, function(data){
			if(data=="ok"){
				layer.msg("操作成功!", {time:800}, function(){
					location.reload();
				});
			}else if(data=="login"){
				alert("请登录后再操作!");
				location.href="${pageContext.request.contextPath}/index/toLoginPage?flag=-1";
			}else if(data=="empty"){
				alert("库存不足!");
				location.reload();
			}else{
				alert("请求失败!");
			}
		});
	}
</script>