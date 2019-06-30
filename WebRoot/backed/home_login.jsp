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
    <script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
    
    <script type="text/javascript">
	$(document).ready(function(){
	loadTable();  
});
</script>
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
                <h1 class="navbar-brand"><a href="#">校友聚会报名</a></h1>
            </div>
            <!--navbar-header-->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a href="#" >报名</a></li>
                    <li><a href="<%=path%>/csc_backstage/csc_user/risg.jsp" >注册</a></li>
                    <li><a href="<%=path%>/backed/home_login.jsp" >登录</a></li>
                    <li><a href="<%=path%>/backed/login.jsp" target="_blank">后台管理</a></li>
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

            <form action="login.jsp" method="post">
                <div class="register-top-grid" >
                    <h3 >近期聚会信息</h3>
                    <div id = "msg"></div>
                     <!-- <h2>聚会信息1：</h2>
                     <h1>miaoshimiaoshimiaoshimiaoshimiaoshimiaoshimiaoshimiaoshi</h1> -->
                    <!-- <div class="input" id="username">
                        <span>用户名</span>
                        <input type="text" name="user.username" value="请输入用户名" required="required" onfocus="if(value == defaultValue){value='';this.style.color='#000'}"
                               onblur="if(!value){value = defaultValue;this.style.color='#999'}">
                    </div>
                    <div class="input" id="password">
                        <span>密码 <label style="color:red;">*</label></span>
                        <input type="text" name="user.password" value="请输入密码" required="required" onfocus="if(value == defaultValue){value='';this.style.color='#000'}"
                               onblur="if(!value){value = defaultValue;this.style.color='#999'}">
                    </div>
                    <div class="clearfix"> </div>
                </div>-->
                
                <div class="register-but text-center">
                    <input type="submit" value="报名">
                    <div class="clearfix"> </div>
                </div> 
            </form>
            <div class="clearfix"> </div>
        </div>
    </div>
</div>
<!--//account-->





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
<script type="text/javascript">
 function loadTable(){
	 	var html='';
		var data=getList(); 
		if(data!=null) {
			var html='';
			for(var i=0;i<data.length;i++) { 
				html=html+'<tr id="'+data[i].id+'"><h3>'+data[i].actiName+'</td><td width="9%">'+data[i].actiTime+'</td><td width="9%">'+data[i].actiAddr+'</td><td width="10%">'+data[i].actiDisc+'</td></tr>';
			} 
			document.getElementById('msg').innerHTML=html; 
		}
		$("#msg").html(html);
	}
function getList(){
		var jsons;
		$.ajax({
			type : "GET",
			dataType : "json",
			async: false,
			url : "${pageContext.request.contextPath}/userRisg/getAllActi.do",
			success : function(data) { 
				jsons=data;  
			}
		})
		return jsons;  
	}   
</script>

</body>
</html>
</body>
</html>