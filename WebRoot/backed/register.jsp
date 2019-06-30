<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%
String path = request.getContextPath();
session.removeAttribute("admin"); 
session.invalidate(); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>用户注册</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link type="text/css" rel="stylesheet" href="<%=path%>/css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="<%=path%>/css/style1.css">
</head>


<body>
<!--header-->
<div class="header">
    <div class="container">
        <nav class="navbar navbar-default" role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <h1 class="navbar-brand"><a href="index.action"></a></h1>
            </div>
            <!--navbar-header-->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <ul class="nav navbar-nav">
                    <li><a href="#" >报名</a></li>
                    <li><a href="<%=path%>/backed/register.jsp" >注册</a></li>
                    <li><a href="<%=path%>/backed/home_login.jsp" >登录</a></li>
                    <li><a href="<%=path%>/backed/login.jsp" target="_blank">后台管理</a></li>
                </ul>
                </ul>
                <!--/.navbar-collapse-->
            </div>
            <!--//navbar-header-->
        </nav>
        <div class="clearfix"> </div>
    </div>
</div>
<!--//header-->

</body>
</html>

<!--account-->
<div class="account">
    <div class="container">
        <div class="register">
        
        	<%-- <form action="<%=path%>/view/login/checkLogin" method="post" id="lform" name="login"> --%>
            <form action="<%=path%>/userRisg/risg.do" method="post" name="form">
            <!-- <form id ="form"  method="post" name="form"> -->
                <div class="register-top-grid">
                    <h3>注册新用户</h3>
                     <input type="hidden" name="id" value="" >
                    <div class="input" id="phone">
                        <span>手机号 <label style="color:red;">*</label></span>
                        <input type="text" name="phone" value="请输入手机号" required="required" onfocus="if(value == defaultValue){value='';this.style.color='#000'}"
                               onblur="if(!value){value = defaultValue;this.style.color='#999'}">
                    </div>
                    <div class="input" id="password">
                        <span>密码 <label style="color:red;">*</label></span>
                        <input type="text" name="password" value="请输入密码" required="required" onfocus="if(value == defaultValue){value='';this.style.color='#000'}"
                               onblur="if(!value){value = defaultValue;this.style.color='#999'}">
                    </div>
                    <div class="input" id="realname">
                        <span>真实姓名<label></label></span>
                        <input type="text" name="realname" value="请输入真实姓名" onfocus="if(value == defaultValue){value='';this.style.color='#000'}"
                               onblur="if(!value){value = defaultValue;this.style.color='#999'}">
                    </div>
                    <div class="input" id="email">
                        <span>电子邮箱<label></label></span>
                        <input type="text" name="email" value="请输入电子邮箱" onfocus="if(value == defaultValue){value='';this.style.color='#000'}"
                               onblur="if(!value){value = defaultValue;this.style.color='#999'}">
                    </div>
                    <div class="clearfix"> </div>
                </div>
                <div class="register-but text-center">
                    <input type="submit" value="提交" >
                    <!-- <input type="submit" value="提交"   onclick="javascript:form.submit();"> -->
                    <div class="clearfix"> </div>
                </div>
            </form>
            <div class="clearfix"> </div>
        </div>
    </div>
</div>
<!--//account-->
<script language=javascript> 


/* var params = {"name", $("#name").val()}
$.ajax({
     type: "POST",
     url: "/url.do",
     data: params,
     dataType : "json",
     success: function(respMsg){
    	 
     }
  }); */
</script>





<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
</head>
<body>

<!--footer-->
<div class="footer">
    <div class="container">
        <div class="text-center">
            <p>Compant Name © All rights Reseverd</p>
        </div>
    </div>
</div>
<!--//footer-->

</body>
</html>
</body>
</html>