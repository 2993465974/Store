<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>商品列表</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resource_index/css/bootstrap.css">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resource_index/css/style.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource_index/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource_index/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource_index/js/simpleCart.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource_index/layer/layer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource_index/js/cart.js"></script>
</head>
<body>
	
	<jsp:include page="header.jsp"/>
	
	<!--products-->
	<div class="products">	 
		<div class="container">
			<h2>${category.cname}</h2>
			<c:if test="${type==null}">
				<c:if test="${type==2}"><h2>热销推荐</h2></c:if>
				<c:if test="${type==3}"><h2>新品推荐</h2></c:if>
			</c:if>
			<div class="col-md-12 product-model-sec">
				<c:forEach var="goods" items="${goodsList}">
					<div class="product-grid">
						<a href="${pageContext.request.contextPath}/index/detail?goodsgid=${goods.gid}">
							<div class="more-product"><span> </span></div>						
							<div class="product-img b-link-stripe b-animate-go  thickbox">
								<img src="${goods.images1}" class="img-responsive" alt="${goods.gname}" width="240" height="240">
								<div class="b-wrapper">
									<h4 class="b-animate b-from-left  b-delay03">							
										<button>查看详情</button>
									</h4>
								</div>
							</div>
						</a>				
						<div class="product-info simpleCart_shelfItem">
							<div class="product-info-cust prt_name">
								<h4>${goods.gname}</h4>
								<span class="item_price">¥ ${goods.price}</span>
								<input type="button" class="item_add items" value="加入购物车" onclick="buy(${goods.gid})">
								<div class="clearfix"> </div>
							</div>												
						</div>
					</div>
				</c:forEach>
				<div class="clearfix"> </div>
			</div>
			<div>${pageTool}</div>
		</div>
	</div>
	<!--//products-->	
	
	<jsp:include page="footer.jsp"/>

</body>
</html>
<script>
	function buy(goodsgid){
		// $.post("index/buy.action", {goodsgid:goodsgid}, function(data){
		$.post("operation.action", {goodsgid:goodsgid,type:1}, function(data){
			if(data=="ok"){
				layer.msg("操作成功!", {time:800}, function(){
					location.reload();
				});
			}else if(data=="login"){
				alert("请登录后再加入购物车!");
				location.href="toLoginPage?flag=-1";
			}else if(data=="noStack"){
				alert("库存不足!");
				location.reload();
			}else if(data = "no"){
				alert("无法再减!");
			}else{
				alert("操作失败!");
			}
		});
	}
</script>