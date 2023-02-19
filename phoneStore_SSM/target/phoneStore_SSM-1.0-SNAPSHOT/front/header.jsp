<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
</head>
<body>

	<!--header-->
	<div class="header">
		<div class="container" style="width: 1200px">
			<nav class="navbar navbar-default" role="navigation">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<h1 class="navbar-brand"><a href="${pageContext.request.contextPath}/index/">XX手机商城</a></h1>
				</div>
				<!--navbar-header-->
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav" style="position: absolute;left:225px;">
						<li><a href="${pageContext.request.contextPath}/index/" <c:if test="${flag==1}">class="active"</c:if>>首页</a></li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle <c:if test="${flag==2}">active</c:if>" data-toggle="dropdown">商品分类<b class="caret"></b></a>
							<ul class="dropdown-menu multi-column columns-2">
								<li>
									<div class="row">
										<div class="col-sm-12">
											<h4>商品类型</h4>
											<ul class="multi-column-dropdown">
												<c:forEach var="category" items="${categoryLists}">
													<li><a class="list" href="${pageContext.request.contextPath}/index/toCategoGoodsPage?flag=2&cid=${category.cid}">${category.cname}</a></li>
												</c:forEach>
											</ul>
										</div>	
									</div>
								</li>
							</ul>
						</li>
						<li><a href="${pageContext.request.contextPath}/index/toTypeGoodsPage?flag=7&type=2" <c:if test="${flag==7}">class="active"</c:if>>热门</a></li>
						<li><a href="${pageContext.request.contextPath}/index/toTypeGoodsPage?flag=8&type=3" <c:if test="${flag==8}">class="active"</c:if>>性价比</a></li>
						<c:if test="${sessionScope.USERS==null}">
							<li><a href="${pageContext.request.contextPath}/index/toRegisterPage?flag=5" <c:if test="${flag==5}">class="active"</c:if>>注册</a></li>
							<li><a href="${pageContext.request.contextPath}/index/toLoginPage?flag=6" <c:if test="${flag==6}">class="active"</c:if>>登录</a></li>
						</c:if>
						<c:if test="${sessionScope.USERS!=null}">
							<li><a href="${pageContext.request.contextPath}/index/toOrderPage?flag=3" <c:if test="${flag==3}">class="active"</c:if>>我的订单</a></li>
							<li><a href="${pageContext.request.contextPath}/index/toMyPage?flag=4" <c:if test="${flag==4}">class="active"</c:if>>个人中心</a></li>
							<li><a href="${pageContext.request.contextPath}/index/logout">退出</a></li>
						</c:if>
						<li><a href="${pageContext.request.contextPath}/admin/toLogin" target="_blank">后台登陆</a></li>
						
					</ul> 
					<!--/.navbar-collapse-->
				</div>
				<!--//navbar-header-->
			</nav>
			<div class="header-info">
				<div class="header-right search-box">
					<a href="javascript:;"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></a>				
					<div class="search">
						<form class="navbar-form" action="${pageContext.request.contextPath}/index/searchGoodsByLikeName" method="get">
							<input type="text" class="form-control" name="gname">
							<button type="submit" class="btn btn-default" aria-label="Left Align">搜索</button>
						</form>
					</div>	
				</div>
				<div class="header-right cart">
					<a href="${pageContext.request.contextPath}/index/toCart">
						<span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"><span class="card_num">${sessionScope.order.amount==null ? '' :sessionScope.order.amount}</span></span>
					</a>
				</div>
				<div class="header-right login">
					<a href="${pageContext.request.contextPath}/index/toMyPage"><span class="glyphicon glyphicon-user" aria-hidden="true"><!--${sessionScope.USERS.username}--></span></a>
				</div>
				<div class="clearfix"> </div>
			</div>
			<div class="clearfix"> </div>
		</div>
	</div>
	<!--//header-->

</body>
</html>