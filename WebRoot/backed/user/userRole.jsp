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
    <li><label>用户名称：</label><span id="name"></span></li>
	<li><label>用户账户：</label><span id="balance"></span></li>
    <li><label>角色：</label><div class="vocation"><select onchange="getRight(this);" name="roleId" id="roleId" class="select1"></select>
    <br/>
    <table class="tablelist">
    <thead>
    <tr><th>功能</th><th>标准价格(元/月)</th><th>生效时间</th><th>时长</th><th>折扣</th></tr>
    </thead>
    <tbody id="msg">
    
    </tbody>
    </table>
    <br/>
    </div></li>
    
    <li><label>&nbsp;</label><input name="" onclick="save();" type="button" class="btn" value="确认保存"/></li>
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
	url:'<%=path%>/view/role/roleList',
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
		var ret=obj.content;
		for(var i=0;i<ret.length;i++)
		{
			document.getElementById("roleId").options.add(new Option(ret[i].name,ret[i].id));
		}
	}
});

var role='';
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
		document.getElementById("name").innerHTML=obj.name;
		document.getElementById("roleId").value=obj.roleId;
		role=obj.roleId;
		document.getElementById("balance").innerHTML=obj.balance+'元';
	}
});

var count=0;
function getRight(obj)
{
	var roleId='';
	if(obj!=null)
	{
		roleId=obj.value;
	}
	var flag=false;
	if(role==roleId || roleId=='')
	{
		flag=true;
	}
	$.ajax({
		type:'post',
		url:'<%=path%>/view/user/userRight?userId=<%=id%>&roleId='+roleId+'&flag='+flag,
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
			count=obj.length;
			var html='';
			if(flag==true)
			{
				for(var i=0;i<obj.length;i++)
				{
					html=html+'<tr><td><input type="hidden" id="rightId'+i+'" value="'+obj[i].right_id+
					'"><input type="hidden" id="price'+i+'" value="'+obj[i].price+'">'+obj[i].name+
					'</td><td>'+obj[i].price+
					'</td><td><input id="startDate'+i+'" value="" onFocus="WdatePicker({dateFmt:\'yyyy-MM-dd\'})" type="text"/>（结束日期：'+obj[i].end_date+'）</td><td><input id="num'+i+'" type="text" style="width:30px" value="0"/><select id="sel'+i+'" style="opacity:1;border: solid 1px;" width="50px" height="20px"><option value="0">月</option><option value="1">年</option></select></td><td><input id="dis'+i+'" type="text" style="width:30px" value="1"/></td></tr>';
				}
			}
			else
			{
				for(var i=0;i<obj.length;i++)
				{
					html=html+'<tr><td><input type="hidden" id="rightId'+i+'" value="'+obj[i].right_id+
					'"><input type="hidden" id="price'+i+'" value="'+obj[i].price+'">'+obj[i].name+
					'</td><td>'+obj[i].price+
					'</td><td><input id="startDate'+i+'" value="" onFocus="WdatePicker({dateFmt:\'yyyy-MM-dd\'})" type="text"/></td><td><input id="num'+i+'" type="text" style="width:30px" value="0"/><select id="sel'+i+'" style="opacity:1;border: solid 1px;" width="50px" height="20px"><option value="0">月</option><option value="1">年</option></select></td><td><input id="dis'+i+'" type="text" style="width:30px" value="1"/></td></tr>';
				}
			}
			document.getElementById('msg').innerHTML=html;
		}
	});
}

getRight(null);

function save()
{
	var rightId='';
	var startDate='';
	var num='';
	var dis='';
	var sel='';
	var roleId=document.getElementById('roleId').value;
	for(var i=0;i<count;i++)
	{
		rightId=rightId+document.getElementById('rightId'+i).value+',';
	 if(document.getElementById('startDate'+i).value==""){
		startDate=startDate+'-,';  
	 }else{ 
		startDate=startDate+document.getElementById('startDate'+i).value+','; 
	 }
		sel=sel+document.getElementById('sel'+i).value+',';
		num=num+document.getElementById('num'+i).value+',';
		dis=dis+document.getElementById('dis'+i).value+',';
		 
	}
	
	if(count>0)
	{
		rightId=rightId.substring(0,rightId.length-1);
		startDate=startDate.substring(0,startDate.length-1);
		sel=sel.substring(0,sel.length-1);
		num=num.substring(0,num.length-1);
		dis=dis.substring(0,dis.length-1);
	}
	var url='<%=path%>/view/user/saveUserRight?userId=<%=id%>&roleId='+roleId+'&rightId='+rightId+'&startDate='+startDate+'&sel='+sel+'&num='+num+'&dis='+dis;
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
}%>
<div style=""></div>
<script type="text/javascript">
 $(".select1").uedSelect({
		width : 200			  
	});
</script>
