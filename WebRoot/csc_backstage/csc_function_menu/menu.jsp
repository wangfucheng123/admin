<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=path%>/css/demo.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/common/common.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.ztree.all.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$(".click").click(function() {
			$(".tip").fadeIn(200);
		});

		$(".tiptop a").click(function() {
			$(".tip").fadeOut(200);
		});

		$(".sure").click(function() {
			$(".tip").fadeOut(100);
		});

		$(".cancel").click(function() {
			$(".tip").fadeOut(100);
		});

	});
</script>
</head>
<style>
	html,body,.rightinfo{
		height: 100%;
		flex-direction: column;
	}
</style>
<body>



	<div class="rightinfo flex">
		<div class="tools">
			<ul class="toolbar">
				<li class="click" onclick="openDetail('');"><span><img
						src="<%=path%>/images/t01.png" /></span>添加根目录</li>
			</ul>

		</div>
		<div id="treeDemo" class="ztree flex1">
			
		</div>
	</div>
	<div id="dialog" class="Popup" style="display: none; width: 200;">
			<div class="Popup_top">
				<h2 id="h2">新建菜单名称</h2>
				<a href="javascript:void(0);" class="Close" onclick="closeDialog();">关闭</a>
			</div>

			<form name="edtForm" id="edtForm" action="" method="post">
				<ul class="forminfo" id="ul">
					<li><label>菜单名称</label><input name="menuName" id="menuNameNew" type="text" class="dfinput" /></li>
					<li><label>&nbsp;</label><input name="" onclick="submitForm();" type="button" class="btn" value="确认保存" /></li>
				</ul>
			</form>
		</div>
</body>

</html>
<script type="text/javascript">
	/*菜单树*/
		var zTree;
		menuF();
		var submitForm =function(){}
		function closeDialog(){
			$('#edtForm input:not(.btn)').val('');
			$('.Popup').hide();
		}
		function openDetail(){/*添加根目录*/
			$('.Popup').show();
			submitForm=function(){
				if ($('#menuNameNew').val()) {
      				var _data=$('#edtForm').serialize()+'&parentId=0&menuLevel=1&menuOrder='+(zTree.getNodes().length+1)
      				
      				console.log(_data);
      				$.ajax({
						type : "POST",
						dataType : "json",
						url : "${pageContext.request.contextPath}/cscMenu/saveMenu.do?"+_data,
						success : function(data) {
							data=JSON.parse(data);
							if (data.ret=='OK') {
								//console.log(data.ret)
								closeDialog();
								menuF();
								alert('添加成功');
							}else{
								alert('添加失败');
							}
						}
	         		})
      			}else{
      				alert('菜单名称不能为空')
      			}
			}
		}
		function menuF(){
			var nodes;
			$.ajax({
				type : "POST",
				dataType : "json",
				async:false,
				url : "${pageContext.request.contextPath}/cscMenu/getMenuList.do",
				success : function(data) {
					nodes = $.parseJSON(data);
				}
			})

		    console.log(nodes)

			var setting = {       
		        data: {
		            simpleData:{
		                enable:true,
		                idKey: "menuId",
		                pIdKey: "parentId",
		            },
		            key: {
		              name: "menuName"
		            }
		        },
		        view: {
		            addHoverDom: addHoverDom, /*移入节点*/
		            removeHoverDom: removeHoverDom/*移出节点*/
		        },
		        edit: {
		          enable: true,
		          removeTitle:'删除',
		          renameTitle:'重命名',
		          drag: {
		            isCopy: false,
		            isMove: true
		          }
		        },
		        callback:{
		            beforeRemove:beforeRemove,//点击删除时触发，用来提示用户是否确定删除（可以根据返回值 true|false 确定是否可以删除）
		            beforeRename:beforeRename,//编辑结束时触发，用来验证输入的数据是否符合要求(也是根据返回值 true|false 确定是否可以编辑完成)
		            onRemove:onRemove,//(beforeRemove返回true之后可以进行onRemove)删除节点后触发，用户后台操作
		            /*拖拽*/
		            beforeDrag:beforeDrag,/*拖拽前*/
		            beforeDrop:beforeDrop,/*拖拽结束前*/
		            onDrop:onDrop/*拖拽后*/


		        }
		   };
		     zTree= $.fn.zTree.init($("#treeDemo"), setting,nodes);

		     /*拖拽*/
		     var orderF=function(_menuId,_menuOrder){/*更新Order*/
	     		$.ajax({
					type : "POST",
					dataType : "json",
					async:false,
					url : "${pageContext.request.contextPath}/cscMenu/updateMenu.do?menuId="+_menuId+"&menuOrder="+_menuOrder,
					success : function(data) {
						data=JSON.parse(data);
						if (data.ret=='OK') {
							/*console.log(data.ret)*/
							console.log('修改成功')
						}else{
							console.log('修改失败')
						}
					}
         		})
	     	}

		     function beforeDrag(treeId,treeNode){/*拖拽前元素更新*/
		     	
		     	var thisIndex=treeNode[0].getIndex();/*当前索引*/
		     	var treeNodeData=treeNode[0];/*节点信息*/
		     		if (treeNodeData.getParentNode()==null) {/*根节点*/
		     			//console.log(zTree.getNodes().length,thisIndex)
						if ((thisIndex+1)==zTree.getNodes().length) {
							return
						}else{
							for (var i = 0; i < zTree.getNodes().length-(thisIndex+1);i++) {
								treeNodeData=treeNodeData.getNextNode();
								orderF(treeNodeData.menuId,treeNodeData.menuOrder-1)
							}
						}
					} else {/*子节点*/
						//console.log(treeNodeData.getParentNode().children.length,thisIndex)
						if((thisIndex+1)==treeNodeData.getParentNode().children.length) {
							return
						}else{
							for (var i = 0; i < treeNodeData.getParentNode().children.length-(thisIndex+1);i++) {
								treeNodeData=treeNodeData.getNextNode();
								orderF(treeNodeData.menuId,treeNodeData.menuOrder-1)
							}
						}
					}
		     }

			function beforeDrop(treeId, treeNodes, targetNode, moveType, isCopy){/*拖拽结束前*/
				/*console.log(targetNode)
				if (targetNode.menuId) {
					return true
				}
				return false*/
			}
			/*递归*/
			function recurrence(name,num){
				num++;
				if (name.children!=undefined) {
					for (var i =0; i < name.children.length; i++) {
						//console.log(name.children[i].menuId)
						var _data='menuId='+name.children[i].menuId+'&menuLevel='+num;
	      				//console.log(_data);
	      				$.ajax({
							type : "POST",
							dataType : "json",
							url : "${pageContext.request.contextPath}/cscMenu/updateMenu.do?"+_data,
							success : function(data) {
								data=JSON.parse(data);
								if (data.ret=='OK') {
									//console.log(data.ret)
									console.log('menuLevel更新成功')
								}else{
									console.log('menuLevel更新失败')
								}
							}
		         		})
		         		recurrence(name.children[i],num)
					}
				}
			}
			 function onDrop(event, treeId, treeNodes, targetNode, moveType){/*拖拽后*/
			 		var thisIndex=treeNodes[0].getIndex();/*当前索引*/
		     		var treeNodeData=treeNodes[0];/*节点信息*/
					if (treeNodes[0].getParentNode()==null) {/*拖拽到根目录*/
						if ((thisIndex+1)==zTree.getNodes().length) {/*更新*/
							
						}else{
							var _thisIndex=thisIndex+2;
							for (var i = 0; i < zTree.getNodes().length-(treeNodes[0].getIndex()+1);i++) {
								treeNodeData=treeNodeData.getNextNode();
								//console.log(treeNodeData)
								orderF(treeNodeData.menuId,_thisIndex++)
							}
						}
						recurrence(treeNodes[0],1)
						//console.log(treeNodes[0].children)
						var _data='menuId='+treeNodes[0].menuId+'&parentId=0&menuLevel=1&menuOrder='+(treeNodes[0].getIndex()+1);
	      				//console.log(_data);
	      				$.ajax({
							type : "POST",
							dataType : "json",
							url : "${pageContext.request.contextPath}/cscMenu/updateMenu.do?"+_data,
							success : function(data) {
								data=JSON.parse(data);
								if (data.ret=='OK') {
									//console.log(data.ret)
									menuF();
									alert('拖拽成功');
								}else{
									alert('拖拽失败');
									menuF();
								}
							}
		         		})
		 		}else{
		 			if((thisIndex+1)==treeNodeData.getParentNode().children.length) {
							
					}else{
						var _thisIndex=thisIndex+2;
						for (var i = 0; i < treeNodeData.getParentNode().children.length-(treeNodes[0].getIndex()+1);i++) {
							treeNodeData=treeNodeData.getNextNode();
							//console.log(treeNodeData)
							orderF(treeNodeData.menuId,_thisIndex)
						}
					}
					recurrence(treeNodes[0],targetNode.menuLevel+1)
					var _data='menuId='+treeNodes[0].menuId+'&parentId='+targetNode.menuId+'&menuLevel='+(targetNode.menuLevel+1)+'&menuOrder='+(treeNodes[0].getIndex()+1);
	  				//console.log(_data);
	  				$.ajax({
						type : "POST",
						dataType : "json",
						url : "${pageContext.request.contextPath}/cscMenu/updateMenu.do?"+_data,
						success : function(data) {
							data=JSON.parse(data);
							if (data.ret=='OK') {
								//console.log(data.ret)
								menuF();
								alert('拖拽成功');
							}else{
								alert('拖拽失败');
								menuF();
							}
						}
	         		})
		 		}
			  }
	     	/*添加*/
	     	var onRemoveData;/*删除用的*/
	       function addHoverDom(treeId,treeNode){
	       		onRemoveData=treeNode.getNextNode();/*删除用的*/
	          var sObj = $("#" + treeNode.tId + "_span");
	          if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
	          var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
	              + "' title='添加子节点' onfocus='this.blur();'></span>";
	          sObj.after(addStr);
	          var btn = $("#addBtn_"+treeNode.tId);
	          if (btn) btn.bind("click", function(){
	          		closeDialog();
	          		$('.Popup').show();

	          		submitForm=function(){
	          			if ($('#menuNameNew').val()) {
	          				if(treeNode.parentId){
	          					var treeNodeP=treeNode.parentId;
	          				}else{
	          					var treeNodeP=0;
	          				}
	          				var _data=$('#edtForm').serialize()+'&parentId='+treeNode.menuId+'&menuLevel='+(treeNode.menuLevel+1)
	          				if(treeNode.isParent) {
						        var menuOrder = treeNode.children.length + 1 ;
						    } else {
						        /*如果不是父节点,说明没有子节点,设置为1*/
						        var menuOrder = 1;
						    }
						    _data+='&menuOrder='+menuOrder;
	          				console.log(_data);
	          				$.ajax({
								type : "POST",
								dataType : "json",
								url : "${pageContext.request.contextPath}/cscMenu/saveMenu.do?"+_data,
								success : function(data) {
									data=JSON.parse(data);
									if (data.ret=='OK') {
										//console.log(data.ret)
										closeDialog();
										menuF();
										alert('添加成功');
									}else{
										alert('添加失败');
									}
								}
			         		})
	          			}else{
	          				alert('菜单名称不能为空')
	          			}
	          			
	          		}
	              //zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.menuId, menuName:"新建节点" + (newCount++)});
	              return false;
	          });
	      }
		 	function removeHoverDom(treeId, treeNode) {
	            $("#addBtn_"+treeNode.tId).unbind().remove();
	        };

			function beforeRename(treeId, treeNode, newName, isCancel) {/*菜单修改编辑*/
	        	if (newName.length==0){
	        		alert('菜单名称不能为空?')
	        		return false;
	        	}else{

	        		$.ajax({
						type : "POST",
						dataType : "json",
						url : "${pageContext.request.contextPath}/cscMenu/updateMenu.do?menuId="+treeNode.menuId+"&menuName="+newName,
						success : function(data) {
							data=JSON.parse(data);
							if (data.ret=='OK') {
								/*console.log(data.ret)*/
								alert('修改成功');
							}else{
								alert('修改失败');
							}
							menuF();
						}
	         		})
	        	}
	        }
	       
	         function beforeRemove(e,treeId,treeNode){/*删除前*/
		        return confirm("你确定要删除吗？");
		    }
		   function onRemove(e,treeId,treeNode){/*删除中*/
		   			var thisIndex=treeNode.menuOrder;/*当前索引*/
		     		//console.log(thisIndex,onRemoveData)
		     		//console.log((zTree.getNodes().length+1),thisIndex,(zTree.getNodes().length+1-thisIndex))
		     		if (onRemoveData!=null) {
			     		if (onRemoveData.getParentNode()==null) {/*根节点*/
								for (var i = 0; i < (zTree.getNodes().length+1)-treeNode.menuOrder;i++) {
									orderF(onRemoveData.menuId,thisIndex++)
									onRemoveData=onRemoveData.getNextNode();
								}
						} else {/*子节点*/
							//console.log(onRemoveData.getParentNode().children.length+1,thisIndex)
							for (var i = 0; i < (onRemoveData.getParentNode().children.length+1)-treeNode.menuOrder;i++) {
								orderF(onRemoveData.menuId,thisIndex++)
								if ((onRemoveData.getParentNode().children.length+1)-thisIndex==0) {
									break;
								}
								onRemoveData=onRemoveData.getNextNode();
								
							}
						}
					}
					 $.ajax({
							type : "POST",
							dataType : "json",
							url : "${pageContext.request.contextPath}/cscMenu/deleMenu.do?menuId="+treeNode.menuId,
							success : function(data) {
								data=JSON.parse(data);
								if (data.ret=='OK') {
									//console.log(data.ret)
									//console.log(treeNode)
									alert('删除成功');
								}else{
									alert('删除失败');
								}
							}
		         		})
					
		       
		    }
		}
		
</script>