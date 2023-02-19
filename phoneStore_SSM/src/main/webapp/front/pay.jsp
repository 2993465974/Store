<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>支付</title>
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
	<div class="cart-items">
		<div class="container">
			<h2>确认收货信息</h2>
			<form class="form-horizontal" action="${pageContext.request.contextPath}/pay/orderPay" method="post" id="payform">
				<input type="hidden" name="oid" value="${orderPay.oid}">
				<input type="hidden" name="paytype" id="paytype">
				<div class="row">
					<label class="control-label col-md-1">收货人</label>
					<div class="col-md-6">
						<input type="text" class="form-control" name="name" value="${USERS.realname}" style="height:auto;padding:10px;" placeholder="输入收货人" required="required" id="fname"><br>
					</div>
				</div>
				<div class="row">
					<label class="control-label col-md-1">收货电话</label>
					<div class="col-md-6">
						<input type="text" class="form-control" name="phone" value="${USERS.phone}" style="height:auto;padding:10px;" placeholder="输入收货电话" required="required" id="fphone"><br>
					</div>
				</div>
				<div class="row">
					<label class="control-label col-md-1">收货地址</label>
					<div class="col-md-6">
						<input type="text" class="form-control" name="address" value="${USERS.address}" style="height:auto;padding:10px;" placeholder="输入收货地址" required="required" id="faddress"><br>
					</div>
				</div>
			</form>
			
			<br><hr><br>
			
			<h2>选择支付方式</h2>
			<h3>订单编号: ${order.oid}  支付金额: ${order.total}</h3><br><br>
			<div class="col-sm-6 col-md-4 col-lg-3 ">
				<div class="thumbnail">
					<a href="javascript:dopay(1);">
						未实现
						<img src="${pageContext.request.contextPath}/resource_index/images/wechat.jpg" alt="微信支付">
					</a>
				</div>
			</div>
			<div class="col-sm-6 col-md-4 col-lg-3 ">
				<div class="thumbnail">
					<a href="javascript:dopay(2);">
						<img src="${pageContext.request.contextPath}/resource_index/images/alipay.jpg" alt="支付宝支付">
					</a>
				</div>
			</div>
			<div class="col-sm-6 col-md-4 col-lg-3 ">
				<div class="thumbnail">
					<a href="javascript:dopay(3);">
						未实现
						<img src="${pageContext.request.contextPath}/resource_index/images/offline.jpg" alt="货到付款">
					</a>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp"/>
	
<script type="text/javascript">
	function dopay(paytype){
		// 信息校验
		var name = $("#fname").val();
		if(name==null || name==""){
			layer.msg("请正确填写收货人!");
			return;
		}
		var phone = $("#fphone").val();
		if(phone==null || phone=="" || !/^[0-9]*$/.test(phone)){
			layer.msg("请正确填写收货电话!");
			return;
		}
		var address = $("#faddress").val();
		if(address==null || address==""){
			layer.msg("请正确填写收货地址!");
			return;
		}
		
		$("#paytype").val(paytype);
		$("#payform").submit();
	}
</script>

</body>
</html>