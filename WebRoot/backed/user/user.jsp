<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%
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

<script type="text/javascript">
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});
</script>


</head>


<body>

	
    
    <div class="rightinfo">
    
    <div class="tools">
    
    	<ul class="toolbar">
        <li class="click" onclick="openDetail('');" ><span><img src="<%=path%>/images/t01.png" /></span>添加</li>
        </ul>
        
        
        
    
    </div>
    
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th>用户名称</th>
        <th>用户类型</th>
        <th>用户角色</th>
        <th>账户余额</th>
        <th>手机号码</th>
        <th>真实姓名</th>
        <th>性别</th>
        <th>公司名称</th>
        <th>职业类型</th>
        <th>出生日期</th>
        <th>Email</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody id="msg">
        </tbody>
    </table>
    <div class="pagin" id="pagin">
    </div>
    

</body>

</html>
<script type="text/javascript">
var pageNum=0;
function loadTable()
{
	var data=JSON.parse(getData('user/userList?1=1',pageNum));
	if(data!=null)
	{
		var content=data.content;
		var count=data.count;
		var html='';
		for(var i=0;i<content.length;i++)
		{
			var sex='男';
			if(content[i].sex==1)
			{
				sex='女';
			}
			html=html+'<tr><td>'+returnVal(content[i].name)+'</td><td>'+returnVal(content[i].typeName)+'</td><td>'+
			returnVal(content[i].roleName)+'</td><td>'+returnVal(content[i].balance)+'</td><td>'+returnVal(content[i].phone)+'</td><td>'+
			returnVal(content[i].realname)+'</td><td>'+sex+'</td><td>'+returnVal(content[i].company)+'</td><td>'+
			returnVal(content[i].professionName)+'</td><td>'+new function(){if(returnVal(content[i].birthday)=='') return new String('');else return new String(new Date(returnVal(content[i].birthday)).pattern("yyyy-MM-dd"));}+'</td><td>'+returnVal(content[i].email)+
			'</td><td><a href="#" onclick="openDetail('+content[i].id+
			');">修改</a>&nbsp;<a onclick="comm(\'user/deleUser?id='+content[i].id+
			'\');" href="#">删除</a>&nbsp;<a onclick="openRole('+content[i].id+
			');" href="#">权限管理</a>&nbsp;<a onclick="openAccount('+content[i].id+
			');" href="#">账户管理</a>&nbsp;<a onclick="comm(\'user/userStatus?password='+content[i].phone+'&id='+content[i].id+
			'\');" href="#">密码重置</a></td></tr>';
		}
		document.getElementById('msg').innerHTML=html;
		getPage(count,pageNum);
	}
}
loadTable();
function openDetail(id)
{
	window.open('userDetail.jsp?id='+id);
}
function openAccount(id)
{
	window.open('userAccount.jsp?id='+id);
}
function openRole(id)
{
	window.open('userRole.jsp?id='+id);
}
</script>