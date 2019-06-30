<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%
String userId=request.getParameter("userId");
if(userId==null)
{
	userId="";
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
<script type="text/javascript"
	src="<%=path%>/js/WdatePicker/WdatePicker.js"></script>
</head>

<body>
	<div class="formbody">
		<div class="formtitle">
			<span>基本信息</span>
		</div>
		<form name="edtForm" id="edtForm" action="" method="post">
			<input type="hidden" name="id" id="id" value="<%=userId%>" />
			<input type="hidden" name="roleId" id="roleId" value="" />
			<ul class="forminfo">
				<li><label>用户昵称：</label><span id="name"></span></li>
				<li><label>用户名称：</label><span id="realname"></span></li>
				<li><label>用户电话：</label><span id="phone"></span></li>
				<!-- <li><label>用户账户：</label><span id="balance"></span></li> -->
				<li><label>角色：</label>
					<div class="vocation">
						<select  onchange="getRole(this);" name="roleIds" id="roleIds" class="select1"></select> <br />
						</table>
						<br />
					</div></li>

				<li><label>&nbsp;</label><input name="" onclick="save();"
					type="button" class="btn" value="确认保存" /></li>
			</ul>
		</form>
	</div>
</body>
</html>
<%
if(userId!=null && !userId.equals(""))
{
%>
<script type="text/javascript">


$.ajax({
	type:'post',
	url:'<%=path%>/view/cscRole/getRoleList',
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
		r=JSON.parse(obj);
	    var option = '';	
		for(var i=0;i<r.length;i++)
		{						
														
								option = option
										+ '<option value="'+r[i].roleId+'">'
										+ r[i].roleName + '</option>';							
		
			//document.getElementById("roleId_id").options.add(new Option(ret[i].roleName,ret[i].roleId));
		}
		
		
		$("#roleIds").append(option)
	}
});

function getRole(obj){
  var roleId=obj.value; 
  alert(roleId);
  $('#roleId').val(roleId);
	
}
$.ajax({
	type:'post',
	url:'<%=path%>/view/cscUser/userListByUserId?userId=<%=userId%>',
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
		document.getElementById("name").innerHTML=obj.name;
		document.getElementById("realname").innerHTML=obj.realname;
		document.getElementById("phone").innerHTML=obj.phone;
	
	}
});


var role='';


var count=0;

function save()
{

	var roleId=document.getElementById('roleId').value;						
	var url='<%=path%>/view/cscUserRole/saveUserRole?userId=<%=userId%>&roleId='+roleId
	$.ajax({
		type:'post',
		url:url,
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
			alert(JSON.parse(obj).ret);
			if(JSON.parse(obj).ret=='OK')
			{
				window.close();
			}
		}
	});
}

</script>

<%
	}
%>
<div style=""></div>
<script type="text/javascript">
 $(".select1").uedSelect({
		width : 200			  
	});
</script>
