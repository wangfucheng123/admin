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
<title>欢迎登录后台管理系统</title>
<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css" />           
<script language="JavaScript" src="<%=path%>/js/jquery.js"></script>
<script src="<%=path%>/js/cloud.js" type="text/javascript"></script>                

<script language="javascript">                 
	$(function(){                   
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});               
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});                                    
    })                 
});  
</script>               

</head>                                 

<body style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;"> 
    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>                          
      <div id="cloud2" class="cloud"></div>               
    </div>                               


<div class="logintop">    
    <span>欢迎登录后台管理界面平台</span>                                        
    <ul>
    </ul>                         
    </div>
    
    <div class="loginbody">                   
    
    <!-- <span class="systemlogo"></span> -->                  
       
    <div class="loginbox">                                                        
    
    <form action="<%=path%>/view/login/checkLogin" method="post" id="lform" name="login">
    <ul>
    <li><input name="name" id="name" type="text" class="loginuser" value="" /></li>
    <li><input name="password" id="password" type="password" class="loginpwd" value="" onkeypress="checkKey();"/></li>
    <li> <span id="error-message" style="color:red"> </span></li>
    <li><input name="" type="button" onclick="check();" class="loginbtn" value="登录" /></li> 
    </ul>            
    </form>                                          
    
    </div>
    
    </div>  
    
</body>
<script>
function checkKey(){
	if (event.keyCode == 13){                   
		check();                   
	}
}
function check(){                      
	var reg=/^\w+$/;                           
	var preg =new RegExp("^([A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()+=|{}\':;\',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“'。，、？]){5,16}$");
	if(document.getElementById('name').value=='' || document.getElementById('name').value==null ){
		$('#error-message').text( "用户名不能为空！"); return;
	} 	if(!(reg.test(document.getElementById('name').value))){                             
		$('#error-message').text( "用户名格式不对！"); return;
	} 
	if(document.getElementById('password').value=='' || document.getElementById('password').value==null){
		$('#error-message').text( "密码不能为空！"); return;                      
	} 	                     
	if(!preg.test(document.getElementById('password').value)){
		$('#error-message').text( "密码格式不对！"); return;                                                    
	}
	else
	{
		$.ajax({                                            
            type: "POST",
            dataType: "json",
            url:"<%=path%>/view/login/checkLogin",
				data : $('#lform').serialize(),
				success : function(data) {
					var r = $.parseJSON(data);
					if (data.flag == 'true') {
						window.location.href = '/admin/csc_backstage/main.jsp';
					} else {
						$('#error-message').text("用户名或密码错误！");
				}
			}
		})
	}
}
</script> 
</html>
