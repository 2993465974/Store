<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>商品编辑</title>
<meta charset="utf-8" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/bootstrap.css" />
</head>
<body>
<div class="container-fluid">
	<%@include file="header.jsp"%>

	<br><br>
	<form class="form-horizontal" action="goodsUpdate" method="post" enctype="multipart/form-data">
		<input type="hidden" name="gid" value="${goods.gid}"/>
		<input type="hidden" name="cover" value="${goods.images1}"/>
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">名称</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="input_name" name="gname" value="${goods.gname}" required="required">
			</div>
		</div>
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">价格</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="input_name" name="price" value="${goods.price}">
			</div>
		</div>
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">介绍</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="input_name" name="intro" value="${goods.intro}">
			</div>
		</div>
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">库存</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="input_name" name="stock" value="${goods.stock}">
			</div>
		</div>
<%--		<div class="form-group">--%>
<%--			<label for="input_file" class="col-sm-1 control-label">封面图片</label> --%>
<%--			<div class="col-sm-6"><img src="${goods.images1}" width="100" height="100"/>--%>
<%--				<input type="file" name="cover"  id="input_file">推荐尺寸: 500 * 500--%>
<%--			</div>--%>
<%--		</div>--%>
		<div class="form-group">
			<label for="input_file" class="col-sm-1 control-label">详情图片1</label> 
			<div class="col-sm-6"><img src="${goods.images1}" width="100" height="100"/>
				<input type="file" name="images1"  id="input_file">推荐尺寸: 500 * 500
			</div>
		</div>
		<div class="form-group">
			<label for="input_file" class="col-sm-1 control-label">详情图片2</label> 
			<div class="col-sm-6"><img src="${goods.images2}" width="100" height="100"/>
				<input type="file" name="images2"  id="input_file">推荐尺寸: 500 * 500
			</div>
		</div>
		<div class="form-group">
			<label for="select_topic" class="col-sm-1 control-label">类目</label>
			<div class="col-sm-6">
				<select class="form-control" id="select_topic" name="categoryId">
					<c:forEach var="category" items="${categoryList}">
						<c:if test="${category.cid==goods.category.cid}"><option selected="selected" value="${category.cid}">${category.cname}</option></c:if>
						<c:if test="${category.cid!=goods.category.cid}"><option value="${category.cid}">${category.cname}</option></c:if>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-1 col-sm-10">
				<button type="submit" class="btn btn-success">提交修改</button>
			</div>
		</div>
	</form>
</div>
</body>
</html>