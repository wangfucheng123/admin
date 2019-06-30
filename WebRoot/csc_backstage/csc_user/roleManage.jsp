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
<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/common/common.js"></script>

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

.hover {
	background-color: #F0F0F0;
}
</style>
</head>


<body>
	<div class="rightinfo">
		<div class="tools">
			<ul class="toolbar">
				<li class="click" onclick="openDetail('');"><span><img
						src="<%=path%>/images/t01.png" /></span>添加</li>
			</ul>
		</div>
		<table class="tablelist">
			<thead>
				<tr>
					<th>角色名称</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="msg">
			</tbody>
		</table>
		<div class="pagin" id="pagin"></div>
		<div class="tools">
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

		</div>

		<div id="dialog" class="Popup" style="display: none; width: 200;">
			<div class="Popup_top">
				<h2 id="h2"></h2>
				<a href="javascript:void(0);" class="Close" onclick="closeDialog();">关闭</a>
			</div>

			<form name="edtForm" id="edtForm" action="" method="post">
				<input type="hidden" name="id" id="id" value="" /> <input
					type="hidden" name="roleid" id="roleid" value="" />
				<ul class="forminfo" id="ul">
					<li id="rolename_id"><label>角色名称*</label><input
						name="roleName" id="name" type="text" class="dfinput" /></li>
					<li id="roleid_id"><label>角色id*</label><input name="roleId"
						id="roleidinput_id" type="text" class="dfinput" /></li>
					<li><label>&nbsp;</label><input name=""
						onclick="submitForm();" type="button" class="btn" value="确认保存" /></li>
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
	var pageNum = 0;
	function checkPhone(v) {
		if (v.value != "" && (!/^1[34578]\d{9}$/.test(v.value))) {
			alert("手机格式不对");
		}
	}
	function checkEmail(v) {
		if (v.value != ""
				&& (!/^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/
						.test(v.value))) {
			alert("邮箱格式不对");
		}
	}
	function loadRole() {
		var html = '';
		$.ajax({
			type : "POST",
			dataType : "json",
			url : "${pageContext.request.contextPath}/cscRole/getRoleList.do",
			success : function(data) {
				var content = $.parseJSON(data);
				for (var i = 0; i < content.length; i++) {
					html = html + '<tr id="' + content[i].roleId
							+ '"onclick="loadrolePermisson(\''
							+ content[i].roleId + '\' ); "><td>'
							+ content[i].roleName
							+ '</td><td><a href="#" onclick="openDetail(\''
							+ content[i].roleId
							+ '\');">修改</a>&nbsp;<a onclick="removeT(\''
							+ content[i].roleId + '\');" href="#">删除</a>';

				}
				document.getElementById('msg').innerHTML = html;
			}
		})

	}
	loadRole();

	function loadFunctionInfo(function_id) {
		var len = $("#function_id tr").length - 1;
		var select = $('#id' + function_id);
		var tr = "";
		var tr = '<tr id="tr'+function_id+'"><td><select disabled="disabled" style="width:200" name="name'+len+'" id="id'+function_id+'"></select><td><a href="#" onclick="save_rolePermission('
				+ function_id
				+ ',1);">保存</a>&nbsp;<a href="#" onclick="enable(\''
				+ function_id
				+ '\');">修改</a>&nbsp;<a onclick="removeElement('
				+ function_id + ')" href="#">删除</a></td></tr>';

		var id = function_id;
		var key = '#id' + function_id;
		$
				.ajax({
					type : "POST",
					dataType : "json",
					async : false,
					url : "${pageContext.request.contextPath}/cscFunction/getFunctionList.do",
					success : function(r) {
						var option = '';
						var r = $.parseJSON(r);
						for (var num = 0; num < r.length; num++) {
							if (r[num].functionId == id) {
								option = option
										+ '<option value="'+r[num].functionId+'" selected="selected">'
										+ r[num].functionName + '</option>';
							} else {
								option = option
										+ '<option value="'+r[num].functionId+'">'
										+ r[num].functionName + '</option>';
							}
						}
						$('#option').text(option);
					}
				})

		$('#funciton_id').append(tr);
		$(key).append($('#option').text());

	}

	function loadMenuInfo(menu_id) {

		var len = $("#menu_id tr").length - 1;
		var select_menu = $('#id' + menu_id);
		var tr_menu = "";
		var tr_menu = '<tr id="tr'+menu_id+'"><td><select disabled="disabled" style="width:200" name="name'+len+'" id="id'+menu_id+'"></select><td><a href="#" onclick="save_rolePermission('
				+ menu_id
				+ '\'+ 1);">保存</a>&nbsp;<a href="#" onclick="enable(\''
				+ menu_id
				+ '\');">修改</a>&nbsp;<a onclick="removeElement('
				+ menu_id + ')" href="#">删除</a></td></tr>';

		var id_menu = menu_id;
		var key_menu = '#id' + menu_id;
		$
				.ajax({
					type : "POST",
					dataType : "json",
					async : false,
					url : "${pageContext.request.contextPath}/cscMenu/getMenuList.do",
					success : function(menuData) {
						var option_menu = '';
						var menus = $.parseJSON(menuData);
						for (var num = 0; num < menus.length; num++) {
							if (menus[num].menuId == id_menu) {
								option_menu = option_menu
										+ '<option value="'+menus[num].menuId+'" selected="selected">'
										+ menus[num].menuName + '</option>';
							} else {
								option_menu = option_menu
										+ '<option value="'+menus[num].menuId+'">'
										+ menus[num].menuName + '</option>';
							}
						}
						$('#option_menu').text(option_menu);
					}
				})

		$('#menu_id').append(tr_menu);
		$(key_menu).append($('#option_menu').text());

	}

	function loadrolePermisson(roleid) {
		$("#" + roleid).addClass("hover").siblings().removeClass("hover");
		$('#roleid').val(roleid);
		$('#funciton_id').empty();
		$('#menu_id').empty();
		$
				.ajax({
					type : "POST",
					dataType : "json",
					url : "${pageContext.request.contextPath}/cscRole/getPermisson.do?roleid="
							+ roleid,
					success : function(data) {
						var data = $.parseJSON(data);
						var functionData = data.function_list;
						var menuData = data.menu_list;
						if (functionData.length > 0) {
							for (var i = 0; i < functionData.length; i++) {
								loadFunctionInfo(functionData[i].function_id);
							}
						}
						if (menuData.length > 0) {
							for (var i = 0; i < menuData.length; i++) {

								loadMenuInfo(menuData[i].menu_id);
							}
						}
					}
				})
	}
	function addTr() {
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
	}
	function removeT(id) {
		if (confirm("你确定要删除么？")) {
			$.ajax({
				type : "POST",
				dataType : "json",
				url : "${pageContext.request.contextPath}/cscRole/deleRole.do",
				data : {
					roleId : id
				},
				success : function(data) {
					loadTable();
				}
			})
		}
	}
	function enable(key) {
		$("#id" + key).attr("disabled", false)
	}
	function removeElement(key) {
		if (confirm("你确定要删除么？")) {
			$
					.ajax({
						type : "POST",
						dataType : "json",
						url : "${pageContext.request.contextPath}/authority/remove_authority.do",
						data : {
							id : key
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
	function save_rolePermission(key, temp) {
	
	var id="tr"+key;
	
	var  functionId=$("#"+id ).find("option:selected").val();//选中的值

		var roleId = $("#roleid").val();
		var rolePerId;
		$
				.ajax({
					type : "POST",
					dataType : "html",
					url : "${pageContext.request.contextPath}/cscRolePermission/save_rolePermission.do",
					data : {
						id : 0,
						roleId : roleId,
						functionId : functionId,
						menuId : null,
						temp : 1
					},
					success : function(data) {
						var r = $.parseJSON(data);
						if (r.flag == 'true') {
							$("#" + k).attr("disabled", "disabled");
							loadPrivileges($("#userid").val());
							alert("保存成功！");
						}
						if (r.flag == 're') {
							alert("权限已存在！");
							$("tr[id=" + 'tr' + key.replace("id", "") + "]")
									.remove();
						}
					}
				})
	}
	function openDetail(id) {
		if ("" == id) {
			/* var li="<li id='roleid_id'><label>角色id*</label><input name=\"roleId\" id=\"roleidinput_id\" type=\"text\" class=\"dfinput\" /></li>";
			$("#rolename_id").after(li); */
			$("#h2").text("添加新角色");

		} else {
			$("#h2").text("修改角色信息");
			$("#id").val(0);
			$("#roleidinput_id").val(id);
			$("#roleidinput_id").attr("readonly", true);
			$("#" + id).each(function() {
				var tdArr = $(this).children();
				$("#name").val(tdArr.eq(0).text());
			});
			//$("#roleid_id").remove();
		}

		$("#dialog").show();

	}
	function closeDialog() {
		$(":text").val("");
		$(":password").val("");
		$("#v").remove();
		$("#dialog").hide();

		$("#roleid_id").attr("readonly", false);
	}
	function submitForm() {
		if (validate()) {
			$.ajax({
				type : "POST",
				dataType : "html",
				url : "${pageContext.request.contextPath}/cscRole/saveRole.do",
				data : $('#edtForm').serialize(),
				success : function(data) {
					var r = $.parseJSON(data);
					if (r != null && r.flag == 'true') {
						$("#dialog").hide();
						/* $(":text").val("");
						$(":password").val(""); */
						//						$("#roleid_id").remove();
						loadTable();
					} else {
						alert('系统出错！');
					}
				}
			})
		}
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