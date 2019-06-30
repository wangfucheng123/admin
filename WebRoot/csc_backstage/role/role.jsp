<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/common/common.js"></script>

<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css" />




</head>

<body>
<div style="float:left;width:30%;height:100%;">
<div class="rightinfo">
    
    <div class="tools">
    
    	<ul class="toolbar">
        <li class="click" onclick="openDetail();" ><span><img src="<%=path%>/images/t01.png" /></span>添加</li>
        </ul>
    </div>

    <table id="table1" class="tablelist">
    	<thead>
    	<tr>
        <th>编号</th>
        <th>角色名称</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody id="msg">
        </tbody>
    </table>
    </div>
</div>

<div style="float:right;width:70%;height:500px;">
<iframe name="tree" id="tree" src="tree.jsp" frameborder="0" width="100%" height="100%"></iframe>
</div>
 
</body>



<script type="text/javascript">
function loadTable()
{
	$.ajax({
		type:'post',
		url:'<%=path%>/view/role/roleList',
		async:false,
		dataType:'json',
		beforeSend:function()
		{},
		error:function(e)
		{
			alert('网络错误！');
		},
		success:function(data)
		{
			data=JSON.parse(data);
			if(data!=null)
			{
				var content=data.content;
				var html='';
				for(var i=0;i<content.length;i++)
				{
					html=html+'<tr id="tr'+i+'" onclick="changeCSS('+i+','+content.length+','+content[i].id+')"><td>'+(i+1)+'</td><td><input type="text" name="name'+i+'" id="name'+i+'" value="'+content[i].name+'"></td><td><a href="#" onclick="save('+i+','+content[i].id+')">保存</a>&nbsp;<a onclick="comm(\'role/deleRole?id='+content[i].id+'\');" href="#">删除</a></td></tr>';
				}
				document.getElementById('msg').innerHTML=html;
			}
		}
	});
}
loadTable();
function changeCSS(k,length,roleId)
{
	for(var i=0;i<length;i++)
	{
		document.getElementById('tr'+i).style.backgroundColor='';
	}
	document.getElementById('tr'+k).style.backgroundColor='#e5ebee';
	var zTree =tree.$.fn.zTree.getZTreeObj("treeDemo");
	zTree.checkAllNodes(false);
	if(roleId=='' || roleId==null)
	{
		return;
	}
	$.ajax({
		type:'post',
		url:'<%=path%>/view/role/roleRightList?roleId='+roleId,
		async:false,
		dataType:'json',
		beforeSend:function()
		{},
		error:function(e)
		{
			alert('网络错误！');
		},
		success:function(data)
		{
			data=JSON.parse(data);
			var nodes = zTree.transformToArray(zTree.getNodes());
			for (var i=0, l=nodes.length; i < l; i++) {
				for(var k=0,q=data.length;k<q;k++)
				{
					if(nodes[i].id==data[k].rightId)
					{
						zTree.checkNode(nodes[i], true, true);
					}
				}
			}
		}
	});
	
}
function openDetail()
{
	var i = $("#table1 tr").length-1;
	var str='<tr id="tr'+i+'" onclick="changeCSS('+i+','+(i+1)+',\'\')"><td>'+(i+1)+'</td><td><input type="text" name="name'+i+'" id="name'+i+'"></td><td><a href="#" onclick="save('+i+',\'\');">保存</a>&nbsp;<a onclick="removeElement('+i+')" href="#">删除</a></td></tr>';
	$('#msg').append(str);
}


function save(count,id)
{
	var zTree =tree.$.fn.zTree.getZTreeObj("treeDemo");
	var rightId='';
	for(var i=0;i<zTree.getCheckedNodes(true).length;i++)
	{
		rightId=rightId+zTree.getCheckedNodes(true)[i].id+',';
	}
	if(rightId!='' && rightId!=null)
	{
		rightId=rightId.substring(0,rightId.length-1);
	}
	var name=document.getElementById('name'+count).value;
	if(document.getElementById('name'+count).value=='' || document.getElementById('name'+count).value==null)
	{
		alert('请输入角色名！');
	}
	else
	{
		comm('role/saveRole?id='+id+'&name='+encodeURI(encodeURI(name))+'&rightId='+rightId);
		loadTable();
	}
}


function removeElement(_element){
         var _parentElement = document.getElementById('tr'+_element).parentNode;
         if(_parentElement){
                _parentElement.removeChild(document.getElementById('tr'+_element));
         }
         return false;
}
</script>


</html>
