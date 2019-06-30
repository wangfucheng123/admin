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

<style type="text/css">
html,body,.rightinfo{
	height: 100%;
}
.rightinfo{
	flex-direction: column;
}
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

.Close {
	float: right;
	font-family: Arial, Helvetica, sans-serif;
	margin-right: 10px;
	margin-top: 10px;
}

a {
	text-decoration: none;
}

a:hover {
	text-decoration: none;
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

#tip {
	position: absolute;
	right: 0px;
	bottom: 0px;
	height: 200px;
	width: 200px;
	border: 1px solid #CCCCCC;
	background-color: #FFFFFF;
	padding: 1px;
	overflow: hidden;
	display: none;
	font-size: 12px;
	z-index: 10;
}

#tip p {
	padding: 6px;
}

#tip h1,#detail h1 {
	font-size: 14px;
	height: 25px;
	line-height: 25px;
	background-color: #eeeeee;
	color: #FFFFFF;
	padding: 0px 3px 0px 3px;
	filter: Alpha(Opacity = 100);
}

#tip h1 a,#detail h1 a {
	float: right;
	text-decoration: none;
	color: #FFFFFF;
}
a:hover{
	background: rgba(0,0,0,0);
}
.hover {
	background-color: #F0F0F0;
}
.tablelist.RoleName td.cur{
	background: #e5ebee;
}
.rightinfoBottom{
	flex-direction: column;	
	margin-top:15px;
}
.rightinfoBottom .nav li{
    line-height: 33px;
    height: 33px;
    border: solid 1px #d3dbde;
    float: left;
    margin-right: 5px;
    border-radius: 3px;
    cursor: pointer;
}
.rightinfoBottom .nav li.cur{
	background: #e5ebee;
}
.rightinfoBottom .content{
	overflow: auto;
}
.rightinfoBottom section{
	display:none;
}
.nav{
	margin-bottom: 10px;
}
/* .rightinfoBottom section.rightinfoBottom table,.rightinfoBottom .content{
	width: 100%;
	height: 100%;
} */
.ztree{
	border: 1px solid #617775;
    background: #f0f6e4;
    width: 100%;
    height: 100%;
    overflow-y: scroll;
    overflow-x: auto;
}
.ztree span{
	display: inline;
}
.ztree li span.button.add{background-position:-144px -1px}
</style>
</head>


<body>
	<div class="rightinfo flex">
		<div class="tools">
			<ul class="toolbar">
				<li class="click" onclick="openDetail('',1);"><span><img
						src="<%=path%>/images/t01.png" /></span>添加</li>
			</ul>
		</div>
		<table class="tablelist RoleName">
			<thead>
				<tr>
					<th>角色名称</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="msg">
			</tbody>
		</table>
		<div class="rightinfoBottom flex1 flex">
			<ul class="flex nav">
				<li class="cur">数据</li>
				<li>功能</li>
				<li>菜单</li>
			</ul>
			<div class="tools">
				<ul class="toolbar">
					<li class="click" onclick="openDetail('',5);"><span><img
							src="<%=path%>/images/t01.png" /></span>添加</li>
				</ul>
			</div>
			<div class="content flex1">
				<section style="display: block">
					<table>
						<thead>
							<tr>
								<th>数据ID</th>
								<th>数据名称</th>
								<th>数据源</th>
								<th>数据编码</th>
								<th>功能状态</th>
								<th>创建时间</th>
								<th>操作</th>	
							</tr>
						</thead>
						<tbody id="data_id">

						</tbody>
					</table>
				</section>
				<section>
					<table>
						<thead>
							<tr>
								<th>功能ID</th>
								<th>功能名称</th>
								<th>功能状态</th>
								<th>创建时间</th>
								<th>操作</th>	
							</tr>
						</thead>
						<tbody id="function_id">
							
						</tbody>
					</table>
				</section>
				<section  id="treeDemo" class="ztree">
					
				</section>
			</div>
		</div>
		<!-- <div class="tools">
			<ul class="toolbar">
				<li class="click" onclick="addTr();"><span><img
						src="<%=path%>/images/t01.png" /></span>添加</li>
			</ul>
		</div>
		<div style="width:100%">
			<div style="float:left;width:50%">
				<table id="function_table" class="tablelist">
					<thead>
						<tr>
							<th>权限功能名称</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="funciton_id">
					</tbody>
				</table>
			</div>
			<div style="float:left;width:50%">
		
				<table id="menu_table" class="tablelist">
					<thead>
						<tr>
							<th>权限菜单名称</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="menu_id">
					</tbody>
				</table>
			</div>
		
		</div> -->

		<div id="dialog" class="Popup" style="display: none; width: 200;">
			<div class="Popup_top">
				<h2 id="h2"></h2>
				<a href="javascript:void(0);" class="Close" onclick="closeDialog();">关闭</a>
			</div>

			<form name="edtForm" id="edtForm" action="" method="post">
				<ul class="forminfo" id="ul">

					
				</ul>
			</form>
		</div>



		<p id=option style="display:none"></p>
		<input type="hidden" name="option" id="option" value="" />
		<p id=option_menu style="display:none"></p>
		<input type="hidden" name="option_menu" id="option_menu" value="" />	
		<div id="tip">
			<h1>
				<a href="javascript:void(0)" onclick="start()"></a>提示
			</h1>
			<p id="alt"></p>
		</div>
</body>
</html>
<script type="text/javascript">
 	var roleidAll=1;
	/*视图*/
		/*底部切换*/
		$('.rightinfoBottom .nav li').click(function(){
			$('.rightinfoBottom .nav li').removeClass('cur');
			$(this).addClass('cur');
			$('.rightinfoBottom section').hide().eq($(this).index()).show();
		})

	/*菜单 ztree*/
	var pageNum = 0;

	function loadRole() {/*角色名称载入*/
		var html = '';
		$.ajax({
			type : "POST",
			dataType : "json",
			url : "${pageContext.request.contextPath}/cscRole/getRoleList.do",
			success : function(data) {
				var content = $.parseJSON(data);
				//console.log(content)
				for (var i = 0; i < content.length; i++) {
					html +='<tr id="js_' + content[i].roleId
							+ '"><td onclick="loadrolePermisson('+content[i].roleId+')">'
							+ content[i].roleName
							+ '</td><td><a href="#" onclick="openDetail('
							+ content[i].roleId
							+ ',2);">修改</a>&nbsp;<a onclick="delF(\'js_'+content[i].roleId+'\',1)" href="#">删除</a>';
				}
				document.getElementById('msg').innerHTML = html;

				loadrolePermisson(roleidAll);
			}
		})

	}
	loadRole();
	function loadrolePermisson(roleid) {/*角色名称点击，打印三表*/
		$('.tablelist.RoleName td').removeClass('cur');
		$('#js_'+roleid+' td:eq(0)').addClass('cur');

		$('#funciton_id').empty();
		$('#menu_id').empty();

		roleidAll=roleid;
		$.ajax({
					type : "POST",
					dataType : "json",
					url : "${pageContext.request.contextPath}/cscRolePermission/selectRolePermissions.do?roleId="+ roleid,
					success : function(data) {
						//console.log(data)
						var data = $.parseJSON(data);
						dataInfo(data.datas);
						functionInfo(data.functions);
						menuF();
					}
				})
	}
	function dataInfo(data){/*数据展示*/
		let html='';
		for (var i = 0; i < data.length; i++) {
			html += '<tr id=dataTable_'+data[i].dataId+'><td>'
						+ data[i].dataId
						+ '</td><td>'
						+ data[i].dataName
						+ '</td><td>'
						+ data[i].dataSource
						+ '</td><td>'
						+ data[i].dataCode
						+ '</td><td>'
						+ openF(data[i].dataStatus)
						+ '</td><td>'
						+ FormatDate(data[i].ctime)
						+ '</td><td><a href="#" onclick="openDetail('
						+ data[i].dataId
						+ ',3,'+data[i].rolePermissionId+');">修改</a>&nbsp;<a href="#" onclick="delF(\'dataTable_'+data[i].rolePermissionId+'\',2)">删除</a></td></tr>';
		}
		document.getElementById('data_id').innerHTML = html;
	}

	function functionInfo(data){/*方法展示*/
		let html='';
		for (var i = 0; i < data.length; i++) {
			html += '<tr id=function_'+data[i].functionId+'><td>'
						+ data[i].functionId
						+ '</td><td>'
						+ data[i].functionName
						+ '</td><td>'
						+ openF(data[i].functionStatus)
						+ '</td><td>'
						+ FormatDate(data[i].ctime)
						+ '</td><td><a href="#" onclick="openDetail('
						+ data[i].functionId
						+ ',4,'+data[i].rolePermissionId+');">修改</a>&nbsp;<a href="#" onclick="delF(\'function_'+data[i].rolePermissionId+'\',2)">删除</a></td></tr>';
		}
		document.getElementById('function_id').innerHTML = html;
	}
	/*菜单 ztree*/
	function menuF(){
			var dataCheck;
			$.ajax({
					type : "POST",
					dataType : "json",
					async:false,
					url : "${pageContext.request.contextPath}/cscRolePermission/selectRolePermissions.do?roleId="+ roleidAll,
					success : function(data) {
						//console.log(data)
						var data = $.parseJSON(data);
						dataCheck=data.menus;
					}
			})
			console.log(dataCheck)/*权限数据*/

			var dataAll;/*所有数据*/
			$.ajax({
				type : "POST",
				dataType : "json",
				async:false,
				url : "${pageContext.request.contextPath}/cscMenu/getMenuList.do",
				success : function(data) {
					dataAll = $.parseJSON(data);
				}
			})
			console.log(dataAll)
		    nodes=dataAll;
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
		        check : {
		            enable : true,
		            chkStyle : "checkbox",    //复选框
		        },
		        callback:{
		            onCheck:onCheck/*勾选*/
		        }

		   };
			var zTree= $.fn.zTree.init($("#treeDemo"),setting,nodes);
			
			(function () {/*初始化默认勾选*/
				zTree=$.fn.zTree.getZTreeObj("treeDemo");
				
				var node='';
				for (var i = 0; i < dataCheck.length; i++) {
					var node = zTree.getNodeByParam("menuId",dataCheck[i].menuId,null);
					node.rolePermissionId=dataCheck[i].rolePermissionId;//添加PermissionId
					zTree.cancelSelectedNode();//先取消所有的选中状态
					zTree.checkNode(node,true);//将指定ID的节点选中
					zTree.expandNode(node, true, false);//将指定ID节点展开*/
				}
	})();

	        var newCount = 1;
	        function onCheck(e,treeId,treeNode){
	        	

	           	zTree=$.fn.zTree.getZTreeObj("treeDemo");
	         
	         	console.log(treeNode)
	         	if (treeNode.checked) {/*选择成功*/
	         		var addData='roleId='+roleidAll+'&menuId='+treeNode.menuId+'&temp=1'
	         		console.log(addData)
	         		$.ajax({
						type : "POST",
						dataType : "json",
						url : "${pageContext.request.contextPath}/cscRolePermission/save_rolePermission.do?"+addData,
						success : function(data) {
							data=JSON.parse(data);
							if (data.ret=='OK') {
								/*console.log(data.ret)*/
								alert('添加成功');
							}else{
								alert('添加失败');
							}
							menuF()
						}
	         		})
	         	}else{
	         		$.ajax({
						type : "POST",
						dataType : "json",
						url : "${pageContext.request.contextPath}/cscRolePermission/deleRolePermission.do?rolePermissionId="+treeNode.rolePermissionId,
						success : function(data) {
							data=JSON.parse(data);
							if (data.ret=='OK') {
								/*console.log(data.ret)*/
								alert('删除成功');
							}else{
								alert('删除失败');
							}
							menuF()
						}
	         		})
	         	}
				/*var changedNodes = zTree.getChangeCheckedNodes(); //获取改变的全部结点
				for ( var i=0 ; i < changedNodes.length ; i++ ){
					var treeNode = changedNodes[i];
					console.log((treeNode?treeNode.menuName:"root") + "checked " +(treeNode.checked?"true":"false"));

				}*/
	            /*for(var i=0;i<nodes.length;i++){
	              v+=nodes[i].menuName + ",";
	              //console.log("节点id:"+nodes[i].id+"节点名称"+v); //获取选中节点的值
	            }
	            console.log(v)*/
	        }

		}
	/*添加修改判断函数*/
	function openDetail(_id,num,pmId){
		let _html='';
		switch(num){
			case 1:/*角色添加*/
				$('.Popup').show();
			  $('#h2').text('角色添加');
			  _html='<li><label>角色名称</label><input name="roleName" id="roleName" type="text" class="dfinput" /></li><li><label>&nbsp;</label><input name="" onclick="submitForm(1);" type="button" class="btn" value="确认保存" /></li>'
			$('.forminfo#ul').html(_html);
			  break;
			case 2:
				$('.Popup').show();
				 $('#h2').text('角色修改');
			 _html='<li><label>角色Id</label><input name="roleId" readonly="readonly" id="roleId" type="text" class="dfinput" value="'+_id+'" /></li><li><label>角色名称</label><input name="roleName" id="roleName" type="text" class="dfinput" /></li><li><label>角色描述</label><input name="description" id="description" type="text" class="dfinput" /></li><li><label>&nbsp;</label><input name="" onclick="submitForm(2);" type="button" class="btn" value="确认保存" /></li>'
				 $('.forminfo#ul').html(_html);
			  break;
			case 3:
			  $('.Popup').show();
				 $('#h2').text('修改数据ID');
			 _html=' <input type="hidden" name="temp" id="temp" value="3" /><input type="hidden" name="roleId" id="roleId" value="'+roleidAll+'" /><input type="hidden" name="rolePermissionId" id="rolePermissionId" value="'+pmId+'" /><li><label>数据名称</label><select name="" id="reviseSelect" class="dfinput"></select></li><li><label>&nbsp;</label><input name="" onclick="submitForm(3);" type="button" class="btn" value="确认保存" /></li>'
				 $('.forminfo#ul').html(_html);
				 var option='';
				 $.ajax({
						type : "POST",
						dataType : "json",
						async:false,
						url : "${pageContext.request.contextPath}/cscData/getDataList.do", 
						success : function(data) {
							//console.log(data)
							var data = JSON.parse(data); 
							for(var i=0;i<data.length;i++){
								option+='<option value='+data[i].dataId+'>'+data[i].dataName+'</option>';
							} 
							$('#reviseSelect').append(option);
						} 
					})
			  break;
			case 4:
			  $('.Popup').show();
				 $('#h2').text('修改方法ID');
			 _html=' <input type="hidden" name="temp" id="temp" value="2" /><input type="hidden" name="roleId" id="roleId" value="'+roleidAll+'" /><input type="hidden" name="rolePermissionId" id="rolePermissionId" value="'+pmId+'" /><li><label>功能名称</label><select name="" id="reviseSelect" class="dfinput"></select><//li><li><label>&nbsp;</label><input name="" onclick="submitForm(4);" type="button" class="btn" value="确认保存" /></li>'
				 $('.forminfo#ul').html(_html);
				 var option='';
				 $.ajax({
						type : "POST",
						dataType : "json",
						async:false,
						url : "${pageContext.request.contextPath}/cscFunction/getFunctionList.do", 
						success : function(data) {
							//console.log(data)
							var data = JSON.parse(data); 
							for(var i=0;i<data.length;i++){
								option+='<option value='+data[i].functionId+'>'+data[i].functionName+'</option>';
							} 
							$('#reviseSelect').append(option);
						} 
					})
			  break;
			case 5:
			  if ($('.rightinfoBottom .nav li:eq(0)').hasClass('cur')) {/*数据权限添加*/
			  		addTr('data');
			  }else if($('.rightinfoBottom .nav li:eq(1)').hasClass('cur')){/*功能权限添加*/
			  		addTr('function');
			  }else{
			  		alert('无法添加')
			  }
		}
	}
	function delF(_id,num){
		event.stopPropagation();
		if (confirm("你确定要删除么？")) {
			var _url,arr=_id.split('_');
			if (num==1) {
				_url='cscRole/deleRole.do?roleId'
			}else if (num==2) {
				_url='cscRolePermission/deleRolePermission.do?rolePermissionId'	
			}
			//console.log(_id,arr[1])
			$.ajax({
				type : "POST",
				dataType : "json",
				url : "${pageContext.request.contextPath}/"+_url+"="+arr[1],
				success : function(data) {
					data=JSON.parse(data);
					if (data.ret=='OK') {
						/*console.log(data.ret)*/
						loadRole();
					}
				}
			})
		}
	}
	/*添加最后一行*/
	function addTr(type){ 
		if (type=='data') {/*数据权限*/
			var lengthNum = $("#data_id tr").length-1;
			var tr='<tr id="addData'+lengthNum+'"roleId='+roleidAll+' temp="3"><td colspan="6"><select style="width:180px;" id="selectData'+lengthNum+'"></select></td><td><a href="#" onclick="saveTr(\'addData'+lengthNum+'\');">保存</a>&nbsp;</tr>';
			var option='';
			$.ajax({
				type : "POST",
				dataType : "json",
				url : "${pageContext.request.contextPath}/cscData/getDataList.do", 
				success : function(data) {
					var data = JSON.parse(data); 
					for(var i=0;i<data.length;i++){
						//console.log(data[i])
						option+='<option value='+data[i].dataId+'>'+data[i].dataName+'</option>';
					} 
					$('#selectData'+lengthNum).append(option);
				} 
			}) 
			$('#data_id').append(tr);
		}else{
			var lengthNum = $("#function_id tr").length-1;
			var tr='<tr id="addFunction'+lengthNum+'" roleId='+roleidAll+' temp="2"><td colspan="4"><select style="width:180px;" id="selectFunction'+lengthNum+'"></select></td><td><a href="#" onclick="saveTr(\'addFunction'+lengthNum+'\');">保存</a>&nbsp;</tr>';
			var option='';
			$.ajax({
				type : "POST",
				dataType : "json",
				url : "${pageContext.request.contextPath}/cscFunction/getFunctionList.do", 
				success : function(data) {
					//console.log(data)
					var data = JSON.parse(data); 
					for(var i=0;i<data.length;i++){
						option+='<option value='+data[i].functionId+'>'+data[i].functionName+'</option>';
					} 
					$('#selectFunction'+lengthNum).append(option);
				} 
			}) 
			$('#function_id').append(tr);
		}
	}

	/*角色权限保存*/
	function saveTr(name){
		var temp=$('#'+name).attr('temp');
		if (temp==3) {
			var _ID='dataId';
		}else{
			var _ID='functionId';
		}

		var _data='roleId='+roleidAll+'&'+_ID+'='+$('#'+name+' option:selected').val()+'&temp='+temp;
		console.log(_data)
		$.ajax({
			type : "POST",
			dataType : "json",
			url : "${pageContext.request.contextPath}/cscRolePermission/save_rolePermission.do",
			data : _data,
			success : function(data) {
				data=JSON.parse(data);
				if (data.ret=='OK') {
					alert(data.ret)
					//console.log(data.ret)
					loadrolePermisson(roleidAll);
				} else {
					alert('系统出错！');
				}
			},
			error:function(data){
					alert('保存的权限已存在！');
			}
		})
	}

	/*function addTr() {
		if ($("#roleid").val() == "") {
			alert("先选中要查看的用户！");
			return;
		}

		var len_function = $("#function_id tr").length - 1;
		var len_menu = $("#menu_id tr").length - 1;
		loadFunctionInfo(len_function);
		loadMenuInfo(len_menu);
		enable(len_function);
		enable(len_menu);
	}*/





	function save_rolePermissionFunction(key, temp) {

		var id = "tr" + key;

		var functionId = $("#" + id).find("option:selected").val();//选中的值

		var roleId = $("#roleid").val();
		$.ajax({
					type : "POST",
					dataType : "html",
					url : "${pageContext.request.contextPath}/cscRolePermission/save_rolePermission.do",
					data : {
						//id : 0,
						roleId : roleId,
						functionId : functionId,
						//menuId : null,
						temp : 1
					},
					success : function(data) {
						var r = $.parseJSON(data);
						if (r.flag == 'true') {
							$("#" + id).attr("disabled", "disabled");
							$("#funciton_id").html("")
							loadrolePermisson(roleId);
							alert("保存成功！");
							//$('#id').attr('id', "tr" + functionId);
						}
						if (r.flag == 're') {
							alert("权限已存在！");
							$("tr[id=" + 'tr' + key.replace("id", "") + "]")
									.remove();
						}
					}
				})
	}
	function removeFunction(key) {
		//var functionId = key.substring(2);
		//var functionId = $("#" + id).find("option:selected").val();//选中的值
		var roleId = $("#roleid").val();
		if (confirm("你确定要删除么？")) {
			$.ajax({
						type : "POST",
						dataType : "json",
						url : "${pageContext.request.contextPath}/cscRolePermission/deleRolePermission.do",
						data : {
							//id : 0,
							roleId : roleId,
							functionId : key,
							//menuId : null,
							temp : 1
						},
						success : function(data) {
							var r = $.parseJSON(data);
							if (data.flag == 'true') {
								$("tr[id=" + 'tr' + key + "]").remove();
							}
						}
					})
		}
	}

	function save_rolePermissionMenu(key, temp) {

		var id = "tr2" + key;

		var menuId = $("#" + id).find("option:selected").val();//选中的值

		var roleId = $("#roleid").val();
	
		$.ajax({
					type : "POST",
					dataType : "html",
					url : "${pageContext.request.contextPath}/cscRolePermission/save_rolePermission.do",
					data : {
						//id : 0,
						roleId : roleId,
						//functionId : null,
						menuId : menuId,
						temp : 0
					},
					success : function(data) {
						var r = $.parseJSON(data);
						if (r.flag == 'true') {
							$("#" + id).attr("disabled", "disabled");
							$("#menu_id").html("")
							loadrolePermisson(roleId);
							alert("保存成功！");
							//$('#id').attr('id', "tr" + functionId);
						}
						if (r.flag == 're') {
							alert("权限已存在！");
							$("tr[id=" + 'tr' + key.replace("id", "") + "]")
									.remove();
						}
					}
				})
	}
	function removeMenu(key) {
		//var functionId = key.substring(2);
		var roleId = $("#roleid").val();
		if (confirm("你确定要删除么？")) {
			$.ajax({
						type : "POST",
						dataType : "json",
						url : "${pageContext.request.contextPath}/cscRolePermission/deleRolePermission.do",
						data : {
							//id : 0,
							roleId : roleId,
							//functionId : null,
							menuId : key,
							temp : 0
						},
						success : function(data) {
							var r = $.parseJSON(data);
							if (data.flag == 'true') {
								$("tr[id=" + 'tr' + key + "]").remove();
							}
						}
					})
		}
	}

	function enable(key) {
		$("#id" + key).attr("disabled", false)
		$("#id2" + key).attr("disabled", false)
	}
	function removeElement(key) {

		var functionId = key.substring(2);
		//var functionId = $("#" + id).find("option:selected").val();//选中的值
		var roleId = $("#roleid").val();
		if (confirm("你确定要删除么？")) {
			$.ajax({
						type : "POST",
						dataType : "json",
						url : "${pageContext.request.contextPath}/cscRolePermission/deleRolePermission.do",
						data : {
							id : 0,
							roleId : roleId,
							functionId : functionId,
							menuId : null,
							temp : 1
						},
						success : function(data) {
							var r = $.parseJSON(data);
							if (data.flag == 'true') {
								$("tr[id=" + 'tr' + key + "]").remove();
							}
						}
					})
		}
	}
	function closeDialog() {
		$('.Popup').hide();
		$('#edtForm input:not(.btn)').val('');
	}
	function submitForm(num) {
			var _url,_data;
			switch(num){
				case 1:/*角色添加*/
					_url='cscRole/saveRole.do';	 
					_data=$('#edtForm').serialize();
				break;
				case 2:/*角色添加*/
					_url='cscRole/updateRole.do';
					_data=$('#edtForm').serialize();	 
				break;
				case 3:/*角色添加*/
					_url='cscRolePermission/updateRolePermission.do'; 
					_data='dataId='+$('#edtForm option:selected').val()+'&'+$('#edtForm').serialize();
					//console.log(_data)
				break;
				case 4:/*角色添加*/
					_url='cscRolePermission/updateRolePermission.do'; 
					_data='functionId='+$('#edtForm option:selected').val()+'&'+$('#edtForm').serialize();
				break;
			}
			$.ajax({
				type : "POST",
				dataType : "json",
				url : "${pageContext.request.contextPath}/"+_url,
				data : _data,
				success : function(data) {
					data=JSON.parse(data);
					if (data.ret=='OK') {
						$("#dialog").hide();
						$('#edtForm input:not(.btn)').val('');
						loadRole();
					} else {
						alert('系统出错！');
					}
				},
				error:function(data){
					alert('操作失败！');
				}
			})
	}
	function validate() {
		var flag = true;
		//匹配   以数字开头的一个或多个数字且以数字结尾的字符串
		var reg = /^\w+$/;
		var preg = new RegExp(
				"^([A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()+=|{}\':;\',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“'。，、？]){6,16}$");

		if ($("#name").val().replace(/(^s*)|(s*$)/g, "").length !== 0
				&& $("#roleidinput_id").val().replace(/(^s*)|(s*$)/g, "").length !== 0) {
			return true;
		}

		if (!reg.test($("#name").val())) {
			$("#tip").show();
			$("#alt").text("用户名有误！");
			setTimeout("autoHide()", 2000);
			flag = false;
		}
		if (!preg.test($("#password").val())) {
			$("#tip").show();
			$("#alt").text("密码格式错误！");
			setTimeout("autoHide()", 2000);
			flag = false;
		}
		return flag;
	}
	function autoHide() {
		$("#tip").hide();
	}
	function resetPW(id) {
		$.ajax({
			type : "POST",
			dataType : "html",
			url : "${pageContext.request.contextPath}/admin/saveAdmin.do",
			data : {
				id : id,
				password : '000000'
			},
			success : function(data) {
				var r = $.parseJSON(data);
				if (r.flag == 'true') {
					alert('密码重置成功！');
				} else {
					alert('系统出错！');
				}
			}
		})
	}
</script>