<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>商品详情</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resource_index/css/bootstrap.css">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resource_index/css/style.css">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resource_index/css/flexslider.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource_index/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource_index/js/jquery.flexslider.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource_index/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource_index/layer/layer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource_index/js/cart.js"></script>
	<script>
		$(function() {
		  $('.flexslider').flexslider({
			animation: "slide",
			controlNav: "thumbnails"
		  });
		});
	</script>
</head>
<body>
	 
	<jsp:include page="header.jsp"/>
	<!--//single-page-->
	<div class="single">
		<div class="container">
			<div class="single-grids">				
				<div class="col-md-4 single-grid">		
					<div class="flexslider">
						<%-- <div class="thumb-image"> <img src="../${good.cover}" data-imagezoom="true" class="img-responsive"> </div> --%>
						<ul class="slides">
							<li data-thumb="${goods.images1}">
								<div class="thumb-image"> <img src="${goods.images1}" data-imagezoom="true" class="img-responsive"> </div>
							</li>
							<li data-thumb="${goods.images1}">
								 <div class="thumb-image"> <img src="${goods.images1}" data-imagezoom="true" class="img-responsive"> </div>
							</li>
							<li data-thumb="${goods.images2}">
							   <div class="thumb-image"> <img src="${goods.images2}" data-imagezoom="true" class="img-responsive"> </div>
							</li> 
						</ul>
					</div>
				</div>	
				<div class="col-md-4 single-grid simpleCart_shelfItem">		
					<h3>${good.gname}</h3>
					<div class="tag">
						<p>分类 : <a href="goods?typeid=${good.category.cid}">${goods.category.cname}</a></p>
					</div>
					<p>介绍: ${goods.intro}</p>
					<div class="galry">
						<h4>库存: ${goods.stock}</h4>
					</div>
					<div class="galry">
						<div class="prices">
							<h5 class="item_price">¥ ${goods.price}</h5>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="btn_form">
						<a href="javascript:;" class="add-cart item_add" onclick="buy(${goods.gid})">加入购物车</a>
					</div>
				</div>
<%--				<div class="col-md-4 single-grid1">--%>
<%--					<!-- <h2>商品分类</h2> -->--%>
<%--					<ul>--%>
<%--						<c:forEach var="category" items="${categoryList}">--%>
<%--							<li><a href="goods?cid=${category.cid}">${category.cname}</a></li>--%>
<%--						</c:forEach>--%>
<%--					</ul>--%>
<%--				</div>--%>
				<div class="clearfix"> </div>
			</div>
		</div>
	</div>
	
	<!--related-products--><!-- 
	<div class="related-products">
		<div class="container">
			<h3>猜你喜欢</h3>
			<div class="product-model-sec single-product-grids">
				<div class="product-grid single-product">
					<a href="single.html">
					<div class="more-product"><span> </span></div>						
					<div class="product-img b-link-stripe b-animate-go  thickbox">
						<img src="images/m1.png" class="img-responsive" alt="">
						<div class="b-wrapper">
						<h4 class="b-animate b-from-left  b-delay03">							
						<button>View</button>
						</h4>
						</div>
					</div>
					</a>					
					<div class="product-info simpleCart_shelfItem">
						<div class="product-info-cust prt_name">
							<h4>Product #1</h4>								
							<span class="item_price">$2000</span>
							<div class="ofr">
							  <p class="pric1"><del>$2300</del></p>
							  <p class="disc">[15% Off]</p>
							</div>
							<div class="clearfix"> </div>
						</div>												
					</div>
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
	</div>
 -->	<!--related-products-->	
	
	<jsp:include page="footer.jsp"/>

</body>
</html>
<script>
	function buy(goodsgid){
		// $.post("buy.action", {goodsgid:goodsgid}, function(data){
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
</script>