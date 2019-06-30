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
     <dd>
    <div class="title">
    <span><img src="<%=path%>/images/leftico01.png" /></span>基础设置
    </div>
    	<ul class="menuson">
    	<li class="active"><cite></cite><a href="csc_user/user.jsp" target="rightFrame">用户管理</a><i></i></li>
        <li><cite></cite><a href="role/roleManage.jsp" target="rightFrame">角色管理</a><i></i></li>
        <li><cite></cite><a href="csc_function_menu/menu.jsp" target="rightFrame">菜单管理</a><i></i></li>
        <li><cite></cite><a href="csc_function_menu/function.jsp" target="rightFrame">功能管理</a><i></i></li>
        </ul>   
    </dd> 
     
    </dl>
    
</body>
</html>
