
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
		}else if(data=="empty"){
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