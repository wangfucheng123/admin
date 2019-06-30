<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="<%=path%>/js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	$.ajax({
		type : "POST",
		dataType : "json",
		async:false,
		url : "${pageContext.request.contextPath}/authority/authority_trees.do", 
		success : function(data) {  
			var menu="";	
			for(var k in data){ 
				menu=menu+'<dd><div class="title"> <span><img src="<%=path%>/images/leftico01.png" /></span>'+k+ ' </div> <ul class="menuson"> '; 
				for(var n=0;n<data[k].length;n++){   
					menu=menu+'<li class="active"><cite></cite><a href="'+data[k][n].url+'" target="rightFrame">'+data[k][n].name+'</a><i></i></li>' 
				}
				menu=menu+'</ul></dd>' ;
			} 
			
			$("#menu").prepend(menu);
		} 
	})
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
	
	
})	
</script>


</head>

<body style="background:#f0f9fd;">
	
      
    <dl class="leftmenu" id="menu">
    <!-- 添加风险管理模块  -->
    <dd>
    <div class="title">
    <span><img src="<%=path%>/images/leftico01.png" /></span>活动管理
    </div>
    	<ul class="menuson">
    	<li class="active"><cite></cite><a href="../backed/risk_mgr/paraSets.jsp" target="rightFrame">发布活动</a><i></i></li>
        <%-- <li><cite></cite><a href="../backed/risk_mgr/datashow.jsp" target="rightFrame">风控数据展示</a><i></i></li> --%>
        </ul>   
 	</dd>
 	
 	
 <dd>
    <div class="title">
    <span><img src="<%=path%>/images/leftico01.png" /></span>用户管理
    </div>
    	<ul class="menuson">
    	<li class="active"><cite></cite><a href="../csc_backstage/csc_user/user.jsp" target="rightFrame">用户管理</a><i></i></li>
       <%--  <li><cite></cite><a href="../csc_backstage/role/roleManage.jsp" target="rightFrame">角色管理</a><i></i></li>
        <li><cite></cite><a href="../csc_backstage/csc_function_menu/menu.jsp" target="rightFrame">菜单管理</a><i></i></li>
        <li><cite></cite><a href="../csc_backstage/csc_function_menu/function.jsp" target="rightFrame">功能管理</a><i></i></li>
         <li><cite></cite><a href="../csc_backstage/csc_function_menu/data.jsp" target="rightFrame">数据管理</a><i></i></li> --%>
        </ul>   
 </dd> 
 

   <%--   <c:if test="${sessionScope.admin.type==1}">   
    <dd>
    <div class="title">
    <span><img src="<%=path%>/images/leftico01.png" /></span>基础设置
    </div>
    	<ul class="menuson">
        <li class="active"><cite></cite><a href="admin/admin.jsp" target="rightFrame">管理员信息管理</a><i></i></li>
        <li><cite></cite><a href="rightTree/rightTree.jsp" target="rightFrame">权限树设置</a><i></i></li>
        <li><cite></cite><a href="role/role.jsp" target="rightFrame">用户角色设置</a><i></i></li>
        </ul>   
    </dd> 
    </c:if> 
    
    <c:if test="${sessionScope.admin.type==1 || sessionScope.admin.role==0}">     
    <dd>    
    <div class="title">
    <span><img src="<%=path%>/images/leftico01.png" /></span>用户
    </div>
    	<ul class="menuson">
        <li><cite></cite><a href="user/user.jsp" target="rightFrame">用户管理</a><i></i></li>
        <li><cite></cite><a href="order/order.jsp" target="rightFrame">购买订单管理</a><i></i></li>
        <li><cite></cite><a href="order/user_order.jsp" target="rightFrame">需求订单管理</a><i></i></li>
        </ul>  
    </dd>
<<<<<<< .mine </c:if>
    </c:if> --%>
 <!-- start new --> 
  
     
    </dl>
    
</body>
</html>
