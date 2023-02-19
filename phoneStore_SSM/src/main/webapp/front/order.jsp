<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>我的订单</title>
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
		<c:if test="${ordersList!=null}">
			<h2>我的订单</h2>
			
				<table class="table table-bordered table-hover">

				<tr>
					<th width="10%">ID</th>
					<th width="10%">总价</th>
					<th width="20%">商品详情</th>
					<th width="20%">收货信息</th>
					<th width="10%">订单状态</th>
					<th width="10%">支付方式</th>
					<th width="10%">下单时间</th>
					<th width="10%">操作</th>
				</tr>
				<c:forEach var="order" items="${ordersList}">
			         <tr>
			         	<td><p>${order.oid}</p></td>
			         	<td><p>${order.total}</p></td>
			         	<td>
				         	<c:forEach var ="item" items="${order.orderItems}">
					         	<p><a href="${pageContext.request.contextPath}/index/detail?goodsgid=${item.goods.gid}" target="_blank">${item.goods.gname}</a>(${item.price}) x ${item.amount}</p>
				         	</c:forEach>
			         	</td>
			         	<td>
			         		<p>收货人：${USERS.realname}</p>
			         		<p>收货电话：${USERS.phone}</p>
			         		<p>收货地址：${USERS.address}</p>
			         	</td>
						<td>
							<p>
								<c:if test="${order.status==1}">未付款</c:if>
								<c:if test="${order.status==2}"><span style="color:red;">已付款</span></c:if>
								<c:if test="${order.status==3}">配送中</c:if>
								<c:if test="${order.status==4}">已完成</c:if>
							</p>
						</td>
						<td>
							<p>
								<c:if test="${order.paytype==0}">未进行支付操作</c:if>
								<c:if test="${order.paytype==1}">微信</c:if>
								<c:if test="${order.paytype==2}">支付宝</c:if>
								<c:if test="${order.paytype==3}">货到付款</c:if>
							</p>
						</td>
						<td><p><fmt:formatDate value="${order.systime}" pattern="yyyy-MM-dd HH:mm:ss" /></p></td>
						<td>
							<c:if test="${order.status==1}">
								<a class="btn btn-success" href="submitOrder?oid=${order.oid}">支付</a>
							</c:if>
							<c:if test="${order.status==3}">
								<a class="btn btn-warning" href="orderFinish?oid=${order.oid}&status=${order.status}">完成</a>
							</c:if>
						</td>
			       	</tr>
				</c:forEach>
			     
			</table>
			
			</c:if>
			<c:if test="${ordersList==null}"><div class="alert alert-info">空空如也</div></c:if>
			
		</div>
	</div>
	<!--//cart-items-->	
	
	<jsp:include page="footer.jsp"/>

</body>
</html>