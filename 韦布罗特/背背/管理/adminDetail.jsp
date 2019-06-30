<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%
String id=request.getParameter("id");
if(id==null)
{
         id="";
}                                                                                   
 String path = request.getContextPath();
 %>                                                                                                                   
 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 <html xmlns="http://www.w3.org/1999/xhtml">
 <head>                                                                              
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>                                                               
<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/common/common.js"></script>
<link href="<%=path%>/css/select.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=path%>/js/select-ui.min.js"></script>
</head>

<body>                                                                                        
    <div class="formbody">
    <div class="formtitle"><span>基本信息</span></div>
    <form name="edtForm" id="edtForm" action="" method="post">
    <input type="hidden" name="id" id="id" value="<%=id%>" />
    <ul class="forminfo">
    <li><label>管理员名称</label><input name="name" id="name" type="text" class="dfinput" /></li>
    <%
	if(id.equals(""))
	{
	%>                                                                          
    <li><label>管理员密码</label><input name="password" id="password" type="password" class="dfinput" /></li>
    <%} %>                                                                           
    <li><label>真实姓名</label><input name="realname" id="realname" type="text" class="dfinput" /></li>
	                                                            
    <li><label>性别</label><div class="vocation"><select name="sex" id="sex" class="select1">
	    
    <option value="0">男</option><option value="1">女</option></select></div></li>
	    
    <li><label>管理员角色</label><div class="vocation"><select name="role" id="role" class="select1">
	    
    <option value="0">用户管理</option><option value="1">数据管理</option></select></div></li>
	    
    <li><label>管理员手机</label><input name="phone" id="phone" type="text" class="dfinput" /></li>
	    
    <li><label>EMAIL</label><input name="email" id="email" type="text" class="dfinput" /></li>
	    
    <li><label>&nbsp;</label><input name="" onclick="javascript:save('edtForm','admin/saveAdmin')" type="button" class="btn" value="确认保存"/></li>
    </ul>                                          
    </form>                                                                      
    </div>
</body>                                                                                    
</html>
<%
if(id!=null && !id.equals(""))
{
%>
<script type="text/javascript">
$.ajax({                                                                                  
	type:'post',
	url:'<%=path%>/view/admin/admin?id=<%=id%>',
	dataType:'json',
	async:false,
	beforeSend:function()
	{},
	error:function(e)                                                                         
	{
		alert('网络错误！');
	},
	success:function(obj)
	{
		obj=JSON.parse(obj);
		document.getElementById("name").value=obj.name;
		document.getElementById("email").value=obj.email;
		document.getElementById("sex").value=obj.sex;                                                        
		document.getElementById("role").value=obj.role;
		document.getElementById("realname").value=obj.realname;                                                          
		document.getElementById("phone").value=obj.phone;
	}
});                                                                          
</script>



<%
}%>
<div style=""></div>
<script type="text/javascript">
 $(".select1").uedSelect({
		width : 345			  
	});
</script>                                                                                             

                  
