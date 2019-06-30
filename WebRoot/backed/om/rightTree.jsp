<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<style type="">

.Popup {
	width: 570px;
	height: 500px;
	position: absolute;
	left: 50%;
	top: 50%;
	margin-left: -400px;
	padding: 0 15px;
	margin-top: -250px;
	background-color: #F0F0F0;
	z-index: 999;
}
.N{
	display:none;
}
.B{
	display:block;
} 
.tools{clear:both; height:35px; margin-bottom:8px;}
.toolbar{float:left;}
.toolbar li{background:url(../images/toolbg.gif) repeat-x; line-height:33px; height:33px; border:solid 1px #d3dbde; float:left; padding-right:10px; margin-right:5px;border-radius: 3px; behavior:url(js/pie.htc); cursor:pointer;}
.toolbar li span{float:left; margin-left:10px; margin-right:5px; margin-top:5px;}
.tablelist{border:solid 1px #cbcbcb; width:100%; clear:both;}
.tablelist th{background:url(../images/th.gif) repeat-x; height:34px; line-height:34px; border-bottom:solid 1px #b6cad2; text-indent:11px; text-align:left;}
.tablelist td{line-height:35px; text-indent:11px; border-right: dotted 1px #c7c7c7;}
.tablelink{color:#056dae;}
.tablelist tbody tr.odd{background:#f5f8fa;}
.tablelist tbody tr:hover{background:#e5ebee;}
.Close {
	float: right;
	font-family: Arial, Helvetica, sans-serif;
	margin-right: 10px;
	margin-top: 10px;
}
.Popup_top {
	height: 40px;
	line-height: 40px;
	border-bottom: 1px solid #cccccc;
}

.Popup_top h2 {
	float: left;
	font-size: 14px;
}
 .ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
a:hover {
	text-decoration: none;background-color: #black;

</style>
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/common/common.js"></script>

<link rel="stylesheet" href="<%=path%>/css/demo.css" type="text/css">
<%-- <link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css" /> --%>
	<link rel="stylesheet" href="<%=path%>/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<link rel="stylesheet" href="<%=path%>/css/demo.css" type="text/css">
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
					autoParam : [ "id", "name=n", "level=lv" ],
					otherParam : {
						"otherParam" : "zTreeAsyncTest"
					},
					dataFilter : filter
				},
				view : {
					addHoverDom : addHoverDom,
					removeHoverDom : removeHoverDom,
					selectedMulti : false
				},
				edit : {
					enable : true,
					removeTitle : "删除",
					renameTitle : "编辑", 
					editNameSelectAll : true,
					showRemoveBtn : showRemoveBtn, 
					showRenameBtn : showRenameBtn
				},
				callback : {
					beforeRemove : beforeRemove,
					onRemove : onRemove,
					onRename : onRename,
					onClick : onClick
				}
			};

			function onRename(e, treeId, treeNode, isCancel) {
				comm('rightTree/saveTree?id=' + treeNode.id + '&name='
						+ encodeURI(encodeURI(treeNode.name)));
			}

			function onClick(event, treeId, treeNode, clickFlag) { 
				$("#treeId").val(treeNode.id); 
				if (treeNode.isParent == false) { 
					if (treeNode.type == 1) {
						 
					} else {
						 
					} $("#detail").show(); 
					loadDatas();
				}
				
			}

			var newCount = 1;
			function addHoverDom(treeId, treeNode) {
				var sObj = $("#" + treeNode.tId + "_span");
				if (treeNode.editNameFlag
						|| $("#addBtn_" + treeNode.tId).length > 0)
					return;
				if (treeNode.editNameFlag
						|| $("#dscBtn_" + treeNode.tId).length > 0)
					return;
				var addStr = "<span class='button add' id='addBtn_"
						+ treeNode.tId
						+ "' title='添加' onfocus='this.blur();'></span>";
						
				var dsc = "<span class='button add' id='dscBtn_"
							+ treeNode.tId
							+ "' title='描述' onfocus='this.blur();'></span>"
				sObj.after(addStr);
				sObj.append(dsc);
				var btn = $("#addBtn_" + treeNode.tId);
				if (btn)
					btn.bind("click", function() {
						var zTree = $.fn.zTree.getZTreeObj("treeDemo");
						comm('rightTree/saveTree?parentId=' + treeNode.id
								+ '&name=' + encodeURI(encodeURI('新节点')));
						zTree.addNodes(treeNode, {
							id : (100 + newCount),
							pId : treeNode.id,
							name : "新节点" + (newCount++)
						});

						var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
						treeObj.reAsyncChildNodes(null, "refresh");

						return false;
					});
				var btn2 = $("#dscBtn_" + treeNode.tId);
				if (btn2)
					btn2.bind("click", function() {
						var zTree = $.fn.zTree.getZTreeObj("treeDemo");
						openDetail(treeNode.id);
						$("#treeId").val(treeNode.id);
						return false;
					});
			}

			function removeHoverDom(treeId, treeNode) {
				$("#addBtn_" + treeNode.tId).unbind().remove();
				$("#dscBtn_"+treeNode.tId).unbind().remove(); 
			};
			 
			function beforeRemove(treeId, treeNode) {
				if (treeNode.id == 1) {
					alert('主节点不能删除！');
					return false;
				}
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				zTree.selectNode(treeNode);
				return confirm("确认删除 节点：" + treeNode.name + " 吗？");
			}
			function onRemove(e, treeId, treeNode) {
				comm('rightTree/deleTree?id=' + treeNode.id);
			}

			function showRemoveBtn(treeId, treeNode) {
				if (treeNode.id == 1) {
					return false;
				}
				return true;
			}
			function showRenameBtn(treeId, treeNode) {
				if (treeNode.id == 1) {
					return false;
				}
				return true;
			}

			function filter(treeId, parentNode, childNodes) {
				if (!childNodes)
					return null;
				for (var i = 0, l = childNodes.length; i < l; i++) {
					childNodes[i].name = childNodes[i].name
							.replace(/\.n/g, '.');
				}
				return childNodes;
			}

			$(document).ready(function() {
				$.fn.zTree.init($("#treeDemo"), setting);
			});
			function openDetail(treeId) {
				$.ajax({
					type : "POST",
					dataType : "html",
					url : "${pageContext.request.contextPath}/rightTree/getTreeById.do",
					data : {
						id : treeId
					},
					success : function(data) {
						var r = $.parseJSON(data);  
						$("#title").val(r.title)	
					 	$("#content").html(r.content)
					 	CKEDITOR.instances.content.setData(r.content); 
					}
				}) 
				$("#dialog").show();
			}
			function saveFreeLimit(){
				var isFree=""; 
				if($("#freeLimit").val()=="0"){
					isFree="0";
				}else{  
					isFree="1";
				} 
				$.ajax({
					type : "POST",
					dataType : "json",
					url : "${pageContext.request.contextPath}/rightTree/saveTree.do",
					data : {
						id : $("#treeId").val(),isFree:isFree ,freeLimit:$("#freeLimit").val()
					},
					success : function(data) { 
					}
				})
			}
		</script> 
</head>

<body> 
    <div style="float:left;width:42%;height:100%;">
    <ul id="treeDemo" class="ztree"></ul>
    </div>
    <br/>
	<div id="detail"  style="display: none; width: 200;" class="Popup" >
	<div class="Popup_top">
				<h2 id="h2">频道定价</h2>
				<a href="javascript:void(0);" class="Close" onclick="closeDialog();">关闭</a>
			</div>
		免费期限<select name="freeLimit" onchange="saveFreeLimit()" id="freeLimit">
		<option value="0">0</option>
		<option value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>
		<option value="6">6</option>
		<option value="7">7</option>
		<option value="8">8</option>
		<option value="9">9</option>
		<option value="10">10</option>
		<option value="11">11</option>
		<option value="12">12</option>
		</select>月
		<div class="tools">
			<ul class="toolbar">
				<li class="click" onclick="addTr();"><span><img
						src="<%=path%>/images/t01.png" /></span>添加</li> 
			</ul> 
		</div>
		<table class="tablelist">
			<thead>
				<tr>
					<th>时间</th>
					<th>价格</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="data">
			</tbody>
		</table>
		<input type="hidden" name="treeId" id="treeId" />
	</div>
	<div id="dialog" class="Popup" style="display: none; width: 200;">
	<div class="Popup_top">
				<h2 id="h2">频道</h2>
				<a href="javascript:void(0);" class="Close" onclick="closeE();">关闭</a>
			</div>
    <form name="edtForm" id="edtForm" action="" method="post">
    <div style="float:right;width:98%;height:100%;"> 
    	标题：<input type="text" name="title" id="title" value="" style="width:300px;" /><br/>
    	内容：<textarea name="content" id="content"></textarea><br/> 
    	<input onclick="saveMsg();" type="button" value="保存" />
    	<input type="hidden" name="id" id="id" />
    </div>	<!-- if(data.rightTree.freeLimit!=null){
						$("#freeLimit option[value='"+data.rightTree.freeLimit+"']").attr("selected","selected");
					} -->
    </form> </div>
	<script type="text/javascript">
		function addTr(){
			var i = $("#data tr").length+1;
			var tr='<tr id="tr'+i+'"><td><input name="limits'+i+'"  id="limits'+i+'" type="text"  />年</td><td><input name="price'+i+'"  id="price'+i+'" type="text"  /></td>'
			+'<td><a href="#" onclick="save(\'tr'+i+'\');">保存</a>&nbsp;<a href="#" >修改</a>&nbsp;<a href="#" onclick="removeData(\'tr'+i+'\');">删除</a></td></tr>';
			$('#data').append(tr);
		}
		function loadDatas(){
			$('#data').empty(); 
			$.ajax({
				type : "POST",
				dataType : "html",
				url : "${pageContext.request.contextPath}/channelPrice/getchannelPrices.do",
				data : {id : $("#treeId").val()},
				success : function(r) {  
					var r = $.parseJSON(r);  
					var i = $("#tauthority tr").length;
					if(r!=null){
						for(var num=0;num<r.length;num++){
							var tr='<tr id="'+r[num].id+'"><td><input name="limits'+r[num].id+'"  id="limits'+r[num].id+'" value="'+r[num].limits+'" disabled="disabled" type="text"  />年</td><td><input name="price'+r[num].id+'" value="'+r[num].price+'" disabled="disabled" id="price'+r[num].id+'" type="text"  /></td>'
							+'<td><a href="#" onclick="save(\''+r[num].id+'\');">保存</a>&nbsp;<a href="#" onclick="enable(\''+r[num].id+'\');">修改</a>&nbsp;<a href="#" onclick="removeData(\''+r[num].id+'\');">删除</a></td></tr>';
							$('#data').append(tr);
						}	
					}
				}
			});
			
			$.ajax({
				type : "POST",
				dataType : "html",
				url : "${pageContext.request.contextPath}/rightTree/getTreeById.do",
				data : {id : $("#treeId").val()},
				success : function(r) {  
					var r = $.parseJSON(r);
					var  myselect=document.getElementById("freeLimit");
					if(r.freeLimit ) {
                        myselect.value=r.freeLimit;                          
                    }
				}
			});
		}
		
		function removeData(key) {
			if (confirm("你确定要删除么？")) {
				if (!isNaN(key)) {
					$
							.ajax({
								type : "POST",
								dataType : "json",
								url : "${pageContext.request.contextPath}/channelPrice/removechannelPrices.do",
								data : {
									id : key
								},
								success : function(data) {

								}
							})
					$("#" + key).remove();
				}else{
					$("#" + key).remove();
				}
			}
		}
		function enable(key) {
			$("#limits" + key).attr("disabled", false);
			$("#price" + key).attr("disabled", false);
		}
		function saveMsg() {
			$("#id").val($("#treeId").val()) 
			$("#content").text(CKEDITOR.instances.content.getData());
			if (treeId == '' || treeId == null) {
				alert('请选择子节点！');
			} else {  
					$.ajax({
						type : "POST",
						dataType : "json",
						url : "${pageContext.request.contextPath}/rightTree/saveTree.do",
						data : $('#edtForm').serialize(),
						success : function(data) {
							var r = $.parseJSON(data); 
								$("#dialog").hide();
							 
						}
					}) 
			}
		}
		var msg = CKEDITOR.replace('content');
		function save(num) {
			var key = "";
			var price = "";
			var limits = "";
			if (isNaN(num)) { 
				num = num.replace("tr", "");
			} else {
				key = num;
			}				

			$
					.ajax({
						type : "POST",
						dataType : "json",
						url : "${pageContext.request.contextPath}/channelPrice/saveChannelPrice.do",
						data : {
							id : key,
							price : $("#price" + num).val(),
							limits : $("#limits" + num).val(),
							channelId : $("#treeId").val()
						},
						success : function(data) {
							var r = $.parseJSON(data);
							if (r.flag == 'true') {
								loadDatas();
								$("#limits" + key).attr("disabled", "disabled");
								$("#price" + key).attr("disabled", "disabled");
							}
						}
					});
		}
		function closeDialog() {
			$('#data').empty();
			$('#freeLimit').val("0");
			$("#detail").hide();
		}
		function closeE() { 
			$("#dialog").hide();
		}
	</script>
</body>

</html>
