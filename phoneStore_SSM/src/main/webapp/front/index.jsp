<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>首页</title>
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
	
	<!--banner-->
	<c:forEach var="recommend" items="${recommendList}" end="0">
		<div class="banner">
			<div class="container">
				<h2 class="hdng"><a href="${pageContext.request.contextPath}/index/detail?goodsgid=${recommend.goods.gid}">${recommend.goods.gname}</a><span></span></h2>
				<p>今日精选推荐</p>
				<a class="banner_a" href="javascript:;" onclick="buy(${recommend.goods.gid})">加入购物车</a>
				<div class="banner-text">		
					<a href="${pageContext.request.contextPath}/index/detail?goodsgid=${recommend.goods.gid}">
						<img src="${recommend.goods.images1}" alt="${recommend.goods.gname}" width="350" height="350">
					</a>	
				</div>
			</div>
		</div>
	</c:forEach>			
	<!--//banner-->
	
<!-- 	<div class="subscribe2"></div> -->
	
	<!--gallery-->
	<div class="gallery">
		<div class="container">
			<div class="alert alert-danger">热销推荐</div>
			<div class="gallery-grids">
				<c:forEach var="recommend" items="${fashionRecommendList}" end="5">
					<div class="col-md-4 gallery-grid glry-two">
						<a href="${pageContext.request.contextPath}/index/detail?goodsgid=${recommend.goods.gid}">
							<img src="${recommend.goods.images1}" class="img-responsive" alt="${recommend.goods.gname}" width="350px" height="350px"/>
						</a>
						<div class="gallery-info galrr-info-two">
							<p>
								<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
								<a href="${pageContext.request.contextPath}/index/detail?goodsgid=${recommend.goods.gid}">查看详情</a>
							</p>
							<a class="shop" href="javascript:;" onclick="buy(${recommend.goods.gid})">加入购物车</a>
							<div class="clearfix"> </div>
						</div>
						<div class="galy-info">
							<p>${recommend.goods.category.cname} > ${recommend.goods.gname}</p>
							<div class="galry">
								<div class="prices">
									<h5 class="item_price">¥ ${recommend.goods.price}</h5>
								</div>
								<div class="clearfix"></div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>


						<div class="clearfix"></div>
						<div class="alert alert-warning">性价比推荐</div>
						<div class="gallery-grids">	
							<c:forEach var="recommend" items="${perfonmanRecommendList}" end="7">
								<div class="col-md-3 gallery-grid ">
									<a href="${pageContext.request.contextPath}/index/detail?goodsgid=${recommend.goods.gid}">
										<img src="${recommend.goods.images1}" class="img-responsive" alt="${recommend.goods.gname}"/>
									</a>
									<div class="gallery-info">
										<p>
											<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> 
											<a href="${pageContext.request.contextPath}/index/detail?goodsgid=${recommend.goods.gid}">查看详情</a>
										</p>
										<a class="shop" href="javascript:;" onclick="buy(${recommend.goods.gid})">加入购物车</a>
										<div class="clearfix"> </div>
									</div>
									<div class="galy-info">
										<p>${recommend.goods.category.cname} > ${recommend.goods.gname}</p>
										<div class="galry">
											<div class="prices">
												<h5 class="item_price">¥ ${recommend.goods.price}</h5>
											</div>
											<div class="clearfix"></div>
										</div>
									</div>
								</div>
							</c:forEach>
							
						</div>	
			
			
			
			<div class="clearfix"></div>
			<div class="alert alert-info">热销</div>
			<div class="gallery-grids">	
				<c:forEach var="goods" items="${goodsList.list}"><!--end="7"-->
					<div class="col-md-3 gallery-grid ">
						<a href="${pageContext.request.contextPath}/index/detail?goodsgid=${goods.gid}">
							<img src="${goods.images1}" class="img-responsive" alt="${goods.gname}"/>
						</a>
						<div class="gallery-info">
							<p>
								<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> 
								<a href="${pageContext.request.contextPath}/index/detail?goodsgid=${goods.gid}">查看详情</a>
							</p>
							<a class="shop" href="javascript:;" onclick="buy(${goods.gid})">加入购物车</a>
							<div class="clearfix"> </div>
						</div>
						<div class="galy-info">
							<p>${goods.category.cname} > ${goods.gname}</p>
							<div class="galry">
								<div class="prices">
									<h5 class="item_price">¥ ${goods.price}</h5>
								</div>
								<div class="clearfix"></div>
							</div>
						</div>
					</div>
				</c:forEach>
				
			</div>


		</div>
	</div>
	<!--//gallery-->
	
	<!--subscribe-->
<!-- 	<div class="subscribe"></div> -->
	<!--//subscribe-->

<%--	<a href="${pageContext.request.contextPath}/alipay/toAlipay"><h1>alipay</h1></a>--%>
	
	<jsp:include page="footer.jsp"/>

</body>
</html>
<script>
	function buy(goodsgid){
		// $.post("index/buy.action", {goodsgid:goodsgid}, function(data){
		$.post("${pageContext.request.contextPath}/index/operation.action", {goodsgid:goodsgid,type:1}, function(data){
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