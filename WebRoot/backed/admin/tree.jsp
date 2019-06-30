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
	
<style type="">.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
.ztree li span.button.desc {margin-left:2px; margin-right: -1px; background-position:-125px 0; vertical-align:top; *vertical-align:middle}
</style>
<link rel="stylesheet" href="<%=path%>/css/demo.css" type="text/css">
	<link rel="stylesheet" href="<%=path%>/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="<%=path%>/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.ztree.excheck.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.ztree.exedit.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.ztree.exhide.js"></script>
	<script type="text/javascript" src="<%=path%>/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
var setting = {
		   async:{
               enable:true,
               dataType: "text",
               autoParam:["id","pId"],
               type:"post",
               url:"<%=path%>/authority/getMenus.do?tid=0"
           },
           data: {
               simpleData: {
                   enable: true,
                   idKey: "id",
                   pIdKey: "pId",
                   rootPId: 0,
               }
           },
           view: {
               showLine: true,
               selectedMulti: false,
               dblClickExpand: true
           },
		edit: {
			enable: true,
			editNameSelectAll: true,
			removeTitle : "删除",
			renameTitle : "编辑", 
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
			 $.post('<%=path%>/authority/rename.do?id='+treeNode.id+'&name='+encodeURI(encodeURI(treeNode.name)));  
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
			/* if(treeNode.level == 0){
				return false;
			}else{ */
				var sObj = $("#" + treeNode.tId + "_span"); //获取节点信息  
			    if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;  
			    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId + "' title='添加'  onfocus='this.blur();'></span>"; //定义添加按钮  
			    sObj.after(addStr); //加载添加按钮  
			    var btn = $("#addBtn_"+treeNode.tId);   
			    //绑定添加事件，并定义添加操作  
			    if (btn) btn.bind("click", function(){   
			        var zTree = $.fn.zTree.getZTreeObj("tree");  
			        //将新节点添加到数据库中  
			        var name='NewNode';  
			        $.post('<%=path%>/authority/save_menu.do?pid='+treeNode.id+'&name='+name,function (data) {  
			            var newID = data; //获取新添加的节点Id  
			            zTree.addNodes(treeNode, {id:newID, pId:treeNode.id, name:name}); //页面上添加节点  
			            var node = zTree.getNodeByParam("id", newID, null); //根据新的id找到新添加的节点  
			            zTree.selectNode(node); //让新添加的节点处于选中状态  
			        });  
			    });  
			}

		function removeHoverDom(treeId, treeNode) {
			$("#addBtn_"+treeNode.tId).unbind().remove(); 
		};
		
		function beforeRemove(treeId, treeNode) { 
			var zTree = $.fn.zTree.getZTreeObj("tree");  
		    zTree.selectNode(treeNode);  
		    return confirm("确认删除 节点  " + treeNode.name + " 吗？");  
		}
		function onRemove(e, treeId, treeNode) {
			 $.post('<%=path%>/authority/remove_menu.do?id='+treeNode.id+'&name='+treeNode.name); 
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
			$.fn.zTree.init($("#tree"), setting);
		});
</script>
</head>
<body>  
    <div style="float:left;width:42%;height:100%;">
    <ul id="tree" class="ztree"></ul>
    </div> 
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
