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
<br/>
<ul class="seachform">
    
    <li><label>&nbsp;&nbsp;&nbsp;处理状态：</label>  
    
    <select id="status" name="status" class="scinput" style="opacity:1;width:100px;" onChange="loadTable();">
    	<option value="">全部</option>
    	<option value="0">未处理</option>
    	<option value="1">已处理</option>
    </select> 
    </li> 
    </ul> 
    <div class="rightinfo"> 
    
    <table class="tablelist">
    	<thead>
    	<tr>
    	<th>机型</th>
    	<th>用户名称</th>
    	<th>用户电话</th>
        <th>标题</th>
        <th>类容</th>
        <th>联系姓名</th>
        <th>电话号码</th>
        <th>Email</th>
        <th>QQ</th>
        <th>日期</th>
        <th>状态</th>
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
	var status=document.getElementById('status').value;
	var data=JSON.parse(getData('admin/userOrderList?status='+status,pageNum));
	if(data!=null)
	{
		var content=data.content;
		var count=data.count;
		var html='';
		for(var i=0;i<content.length;i++)
		{
			var sta='未处理';
			var opt='<a href="">处理</a>';
			if(content[i].status==1)
			{
				sta='已处理';
				opt='';
			}
			opt=opt+'<a href="#" comm(\'admin/deleUserOrder?id='+content[i].id+'\');>删除</a>';
			html=html+'<tr><td>'+returnVal(content[i].mtype)+'</td><td>'+returnVal(content[i].uName)+
			'</td><td>'+returnVal(content[i].phone)+'</td><td>'+returnVal(content[i].title)+
			'</td><td>'+returnVal(content[i].content)+'</td><td>'+returnVal(content[i].name)+
			'</td><td>'+returnVal(content[i].phone)+'</td><td>'+returnVal(content[i].email)+
			'</td><td>'+returnVal(content[i].qq)+'</td><td>'+returnVal(content[i].cdate)+
			'</td><td>'+sta+'</td><td>'+opt+'</td></tr>';
		}
		document.getElementById('msg').innerHTML=html;
		getPage(count,pageNum);
	}
}
loadTable();
</script>