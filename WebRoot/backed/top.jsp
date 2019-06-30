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
	$(function() {
		//顶部导航切换
		$(".nav li a").click(function() {
			$(".nav li a.selected").removeClass("selected")
			$(this).addClass("selected");
		})
	})
	
	function resetP() {
		$("html", parent.document)
				.append( 
						'<div id="dialog" class="Popup" > <div class="Popup_top"> <h2 id="h2"></h2><a href="javascript:void(0);" class="Close" onclick="closeDialog();">关闭</a>'
								+ '</div>  <form name="edtForm" id="edtForm" action="" method="post"> <input type="hidden" name="id" id="id" value="" /> <input type="hidden" name="userid" id="userid" value="" />'
								+ '<ul class="forminfo" id="ul"> '
								+ '<li><label>原始密码</label><input name="password" id="password" type="password"  class="dfinput" /></li>'
								+ '	<li><label>修改密码</label><input name="pword" id="pword" type="password" class="dfinput" /></li>'
								+ '<li><label>确认密码</label><input name="rpword" id="rpword"  type="password" class="dfinput" /></li>'
								+ '<span id="erM" style="color:red"></span>'
								+ '<li><label>&nbsp;</label><input name="" onclick="submitForm();" type="button" class="btn" value="确认保存" /></li> </ul> </form></div>'

				);
	}
	function closeDialog() {
		$("#dialog").hide();
	}
</script>

<style type="text/css">
.Popup {
	width: 570px;
	height: 500px;
	position: absolute;
	left: 50%;
	top: 50%;
	margin-left: -400px;
	padding: 0 15px;
	margin-top: -250px;
	background-color: #F0F0F0;
	z-index: 9999999;
}

.Close {
	float: right;
	font-family: Arial, Helvetica, sans-serif;
	margin-right: 10px;
	margin-top: 10px;
}
a:hover {
	text-decoration: none;
}

.Popup_top {
	height: 40px;
	line-height: 40px;
	border-bottom: 1px solid #cccccc;
} 

.Popup_top h2 {
	float: left;
	font-size: 14px;
}
</style>
</head>

<body style="background:url(<%=path%>/images/topbg.gif) repeat-x;">

    <div class="topleft">
    <a href="main.jsp" target="_parent"><img src="<%=path%>/images/logo.png" title="系统首页" /></a>
    </div>
        
    <ul class="nav">

    </ul>
            
    <div class="topright">    
    <ul>
    <!-- <li><span><img src="images/help.png" title="帮助"  class="helpimg"/></span><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li> -->
    <li><a href="#" onclick="resetP();">修改密码</a></li>
    <li><a href="<%=path%>/backed/login.jsp" target="_parent">退出</a></li>
    </ul>
    
    <div class="user">
    <span>${sessionScope.admin.name}</span> 
    </div> 
    </div> 
     
</body>
</html>
