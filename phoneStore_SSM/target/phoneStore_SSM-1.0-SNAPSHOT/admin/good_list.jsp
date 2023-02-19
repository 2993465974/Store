<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>商品列表</title>
<meta charset="utf-8"/>
<meta name="referrer" content="no-referrer" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/bootstrap.css"/>
</head>
<body>
<div class="container-fluid">

	<%@include file="header.jsp" %>
	
	<br>
	
	<div  class="text-right"><a class="btn btn-warning" href="toAddGoodsPage">添加商品</a></div>
	
	<br>
	
	<form class="form-inline" action="goodsList" id="serchform" method="post">
		<div class="form-group">
			<input type="text" class="form-control" id="name" name="gname" placeholder="商品名称">
		</div>
		<a href="javascript:;" onclick="document.getElementById('serchform').submit();" type="submit" class="btn btn-primary">点击搜索</a>
	</form>
	
	<br>
	
		
	<ul role="tablist" class="nav nav-tabs">
        <li <c:if test='${status==0}'>class="active"</c:if> role="presentation"><a href="goodsList">全部商品</a></li>
        <li <c:if test='${status==1}'>class="active"</c:if> role="presentation"><a href="goodsList?status=1">条幅推荐</a></li>
        <li <c:if test='${status==2}'>class="active"</c:if> role="presentation"><a href="goodsList?status=2">热销推荐</a></li>
        <li <c:if test='${status==3}'>class="active"</c:if> role="presentation"><a href="goodsList?status=3">性价比推荐</a></li>
    </ul>
    
    <c:if test="${status == 1}"><br><p>首页默认只显示前[1]条记录</p></c:if>
    <c:if test="${status == 2}"><br><p>首页默认只显示前[6]条记录</p></c:if>
    <c:if test="${status == 3}"><br><p>首页默认只显示前[8]条记录</p></c:if>
	
	<br>

	<table class="table table-bordered table-hover">

	<tr>
		<th width="5%">商品ID</th>
		<th width="10%">图片</th>
		<th width="10%">名称</th>
		<th width="20%">介绍</th>
		<th width="10%">价格</th>
		<th width="10%">库存</th>
		<th width="10%">类目</th>
		<th width="25%">操作</th>
	</tr>
	<c:forEach var="goods" items="${goodsList.list}">
         <tr>
         	<td><p>${goods.gid}</p></td>
         	<td><p><a href="../index/detail?goodsgid=${goods.gid}" target="_blank"><img src="${goods.images1}" width="100px" height="100px"></a></p></td>
         	<td><p><a href="../index/detail?goodsgid=${goods.gid}" target="_blank">${goods.gname}</a></p></td>
         	<td><p>${goods.intro}</p></td>
         	<td><p>${goods.price}</p></td>
         	<td><p>${goods.stock}</p></td>
         	<td><p>${goods.category.cname}</p></td>
			<td>
				<p>
				<c:if test="${goods.topScroll}"><a class="btn btn-info operateRecommend" href="javascript:;" type="1" goodsId="${goods.gid}" text="加入条幅">移出条幅</a></c:if>
				<c:if test="${!goods.topScroll}"><a class="btn btn-primary operateRecommend" href="javascript:;" type="1" goodsId="${goods.gid}" text="移出条幅">加入条幅</a></c:if>
				<c:if test="${goods.topLarge}"><a class="btn btn-info operateRecommend" href="javascript:;" type="2" goodsId="${goods.gid}" text="加入热销">移出热销</a></c:if>
				<c:if test="${!goods.topLarge}"><a class="btn btn-primary operateRecommend" href="javascript:;" type="2" goodsId="${goods.gid}" text="移出热销">加入热销</a></c:if>
				<c:if test="${goods.topSmall}"><a class="btn btn-info operateRecommend" href="javascript:;" type="3" goodsId="${goods.gid}" text="加入性价比">移出性价比</a></c:if>
				<c:if test="${!goods.topSmall}"><a class="btn btn-primary operateRecommend" href="javascript:;" type="3" goodsId="${goods.gid}" text="移出性价比">加入性价比</a></c:if>
				</p>
				<a class="btn btn-success" href="showGoodsEdit?gid=${goods.gid}">修改</a>
				<a class="btn btn-danger" href="goodsDelete?gid=${goods.gid}">删除</a>
			</td>
       	</tr>
     </c:forEach>
     
</table>
	<br>
	<div style="text-align: center;">
		<a class="btn btn-info"  href="goodsList?pageNum=${goodsList.navigateFirstPage}&status=${status}&gname=${gnames}">首页</a>
		<a class="btn btn-info"  href="goodsList?pageNum=${goodsList.prePage}&status=${status}&gname=${gnames}">上一页</a>
		<a class="btn btn-info"  href="goodsList?pageNum=${goodsList.nextPage}&status=${status}&gname=${gnames}">下一页</a>
		<a class="btn btn-info" href="goodsList?pageNum=${goodsList.navigateLastPage}&status=${status}&gname=${gnames}">尾页</a>
	</div>
<br>${pageTool}<br>
</div>


<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery.js"></script>
<script type="text/javascript">
$(function(){
	//增加或删除让后台自己进行判断
	$(".operateRecommend").on("click",function(event){
		event.preventDefault(); // 使a自带的方法失效，即无法向addStudent.action发出请求
		var type = $(this).attr("type");
		var goodsId = $(this).attr("goodsId");
		var text = $(this).attr("text");
		var old = $(this).text();
		var obj = this;
		// alert("type:" + type +"\r\ngoodsId:" + goodsId + "\r\ntext:" + text + "\r\nold:" +old + "\r\nobj:" + obj );
		$.ajax({
			type: "POST", // 使用post方式
			url: "operateRecommend.action" + "?" +"goodsId=" + goodsId  + "&" + "type=" + type,
			contentType:"application/json",
			// data: JSON.stringify({goodsId:goodsId, type:type}), // 参数列表，stringify()方法用于将JS对象序列化为json字符串
			dataType:"json",
			success: function(result){
				if(old.indexOf("加入") == -1){
					$(obj).text(text).attr("class", "btn btn-primary operateRecommend").attr("text", old);
				}else{
					$(obj).text(text).attr("class", "btn btn-info operateRecommend").attr("text", old);
				}
			},
			error: function(result){
				alert("修改失败")
			}
		});
	});
});
</script>
</body>
</html>