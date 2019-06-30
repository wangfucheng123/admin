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
<script type="text/javascript" src="<%=path%>/js/WdatePicker/WdatePicker.js"></script>
</head>

<body>
    <div class="formbody">
    <div class="formtitle"><span>基本信息</span></div>
    <form name="edtForm" id="edtForm" action="" method="post">
    <input type="hidden" name="id" id="id" value="<%=id%>" />
    <ul class="forminfo">
    <li><label>用户名称</label><span id="name"></span></li>
    <li><label>当前余额</label><div class="vocation" id="balance1"></div></li>
    <li><label>增加金额</label><input name="balance" id="balance" type="text" class="dfinput" /></li>
    <li><label>&nbsp;</label><input name="" onclick="javascript:save('edtForm','user/saveAccount')" type="button" class="btn" value="确认保存"/></li>
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
	url:'<%=path%>/view/user/user?id=<%=id%>',
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
		document.getElementById("balance1").innerHTML=obj.balance;
		document.getElementById("name").innerHTML=obj.name;
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
