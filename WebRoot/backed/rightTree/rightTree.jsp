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

<link rel="stylesheet" href="<%=path%>/css/demo.css" type="text/css">
	<link rel="stylesheet" href="<%=path%>/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="<%=path%>/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.ztree.excheck.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.ztree.exedit.js"></script>
	<script type="text/javascript" src="<%=path%>/js/ckeditor/ckeditor.js"></script>

<script type="text/javascript">
var setting = {
			async: {
				enable: true,
				url:"<%=path%>/view/rightTree/getTree?rootId=1",
				autoParam:["id", "name=n", "level=lv"],
				otherParam:{"otherParam":"zTreeAsyncTest"},
				dataFilter: filter
			},
			view: {
				addHoverDom: addHoverDom,
				removeHoverDom: removeHoverDom,
				selectedMulti: false
			},
			edit: {
				enable: true,
				editNameSelectAll: true,
				showRemoveBtn: showRemoveBtn,
				showRenameBtn: showRenameBtn
			},
			callback: {
				beforeRemove: beforeRemove,
				onRemove: onRemove,
				onRename: onRename,
				onClick: onClick
			}
		};
		
		
		function onRename(e, treeId, treeNode, isCancel) {
			comm('rightTree/saveTree?id='+treeNode.id+'&name='+encodeURI(encodeURI(treeNode.name)));
		}
		
		function onClick(event, treeId, treeNode, clickFlag) {
			
			document.getElementById('id').value='';
			if(treeNode.isParent==false)
			{
				document.getElementById('id').value=treeNode.id;
				document.getElementById('price').value=treeNode.price;
				if(treeNode.type==1)
				{
					document.getElementById('type').checked=true;
				}
				else
				{
					document.getElementById('type').checked=false;
				}
			}
		}	
		
		var newCount = 1;
		function addHoverDom(treeId, treeNode) {
			var sObj = $("#" + treeNode.tId + "_span");
			if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
			var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
				+ "' title='add node' onfocus='this.blur();'></span>";
			sObj.after(addStr);
			var btn = $("#addBtn_"+treeNode.tId);
			if (btn) btn.bind("click", function(){
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				comm('rightTree/saveTree?parentId='+treeNode.id+'&name='+encodeURI(encodeURI('新节点')));
				zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"新节点" + (newCount++)});
				
				var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
				treeObj.reAsyncChildNodes(null, "refresh");
				
				return false;
			});
		}

		function removeHoverDom(treeId, treeNode) {
			$("#addBtn_"+treeNode.tId).unbind().remove();
		};
		
		function beforeRemove(treeId, treeNode) {
			if(treeNode.id==1)
			{
				alert('主节点不能删除！');
				return false;
			}
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.selectNode(treeNode);
			return confirm("确认删除 节点：" + treeNode.name + " 吗？");
		}
		function onRemove(e, treeId, treeNode) {
			comm('rightTree/deleTree?id='+treeNode.id);
		}
		
		function showRemoveBtn(treeId, treeNode) {
			if(treeNode.id==1){
				return false;
			}
			return true;
		}
		function showRenameBtn(treeId, treeNode) {
			if(treeNode.id==1){
				return false;
			}
			
			return true;
		}
	

		function filter(treeId, parentNode, childNodes) {
			if (!childNodes) return null;
			for (var i=0, l=childNodes.length; i<l; i++) {
				childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			}
			return childNodes;
		}

	
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting);
		});
		

	
</script>


	
</head>

	
<body>

	
    
    <div style="float:left;width:42%;height:100%;">
	    
    <ul id="treeDemo" class="ztree"></ul>
	    
    </div>
	
    <br/>
	
    <form name="edtForm" id="edtForm" action="" method="post">
    <div style="float:right;width:58%;height:100%;">
    	价格：<input type="text" name="price" id="price" value="0" />元/月<br/>
    	是否验证试用：<input type="checkbox" value="1" name="type" id="type" /><br/>
    	标题：<input type="text" name="title" id="title" value="" style="width:300px;" /><br/>
    	内容：<textarea name="content" id="content"></textarea><br/>
    	<input type="hidden" name="img" id="img" value="">
		<iframe name="f1" id="f1" src='<%=path%>/common/upload.jsp?name=img' frameborder="0" width="450" height="38"></iframe><br/>
    	<input onclick="saveMsg();" type="button" value="保存" />
    	<input type="hidden" name="id" id="id" />
    </div>
    </form>

<script type="text/javascript">
	
function saveMsg()
{
	var id=document.getElementById('id').value;
	var price=document.getElementById('price').value;
	var type=document.getElementById('type').value;
	if(id=='' || id==null)
	{
		alert('请选择子节点！');
	}
	else
	{
		if(price=='' || price==null)
		{
			alert('请输入价格！');
		}
		else
		{
			document.getElementById('content').value=msg.document.getBody().getHtml();
			save1('edtForm','rightTree/saveTree');
		}
	}
}
var msg=CKEDITOR.replace('content');
</script>
</body>
</html>
