<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>word</title>
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

#tip h1, #detail h1 {
	font-size: 14px;
	height: 25px;
	line-height: 25px;
	background-color: #eeeeee;
	color: #FFFFFF;
	padding: 0px 3px 0px 3px;
	filter: Alpha(Opacity = 100);
}

#tip h1 a, #detail h1 a {
	float: right;
	text-decoration: none;
	color: #FFFFFF;
}
.hover{
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
					<th>用户名称</th>
					<th>手机号码</th>
					<th>分  类</th>
					<th>真实姓名</th>
					<th>邮箱地址</th>
					<th>操  作</th>
				</tr>
			</thead>
			<tbody id="msg">
			</tbody>
		</table>
		<div class="pagin" id="pagin"></div>
		
		
		<div id="dialog" class="Popup" style="display: none; width: 200;">
			<div class="Popup_top">
				<h2 id="h2"></h2>
				<a href="javascript:void(0);" class="Close" onclick="closeDialog();">关闭</a>
			</div>

			<form name="edtForm" id="edtForm" action="" method="post">
				<input type="hidden" name="id" id="id" value="" />
				<input type="hidden" name="userid" id="userid" value="" />
				<ul class="forminfo" id="ul">
					<li id="next"><label>用户名称</label><input name="name" id="name"
						type="text" class="dfinput" /></li> 
					<li><label>手机号码</label><input name="phone" id="phone" type="text" onblur="checkPhone(this);" class="dfinput" /></li>
					<li><label>真实姓名</label><input name="realname" id="realname" type="text"  class="dfinput" /></li>
					<li><label>邮箱地址</label><input name="email" id="email" onblur="checkEmail(this);" type="text" class="dfinput" /></li>
					<li><label>&nbsp;</label><input name="" onclick="submitForm();" type="button" class="btn" value="确认保存" /></li>
				</ul>
			</form>
		</div>
		<p id=option style="display:none"></p>
		<input type="hidden" name="option" id="option" value="" />
		<div id="tip">
			<h1>
				<a href="javascript:void(0)" onclick="start()"></a>提示
			</h1>
			<p id="alt"></p>
		</div>
</body>
</html>
<script type="text/javascript">
var pageNum=0;
function checkPhone(v){
	if(v.value!=""&&(!/^1[34578]\d{9}$/.test(v.value))){
		alert("手机格式不对");
	}
}
function checkEmail(v){
	if(v.value!=""&&(!/^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/.test(v.value))){
		alert("邮箱格式不对");
	}
}
function loadTable()
{
	var data=JSON.parse(getData('admin/adminList.do?1=1',pageNum));
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
			var roleName='用户管理';
			if(content[i].role==1)
			{
				roleName='数据管理';
			}
			html=html+'<tr id="'+content[i].id+'" onclick="loadPrivileges('+content[i].id+'); "><td>'+content[i].name+'</td><td>'+content[i].phone+'</td><td>'+content[i].sort+'</td><td>'+content[i].realname+'</td><td>'+content[i].email
			+'</td><td><a href="#" onclick="openDetail('+content[i].id+');">修改</a>&nbsp;<a onclick="removeT('+content[i].id+');" href="#">删除</a>&nbsp;<a onclick="resetPW('+content[i].id+');" href="#">重置密码</a></td></tr>';
		}
		loadPrivileges(content[0].id); 
		document.getElementById('msg').innerHTML=html;
		getPage(count,pageNum);
	}
}
loadTable();
	function loadPrivileges(userid){ 
		$("#"+userid).addClass("hover").siblings().removeClass("hover");
		$("#userid").val(userid); 
		$('#authority').empty(); 
		$.ajax({
			type : "POST",
			dataType : "html",
			url : "${pageContext.request.contextPath}/authority/get_authoritys.do?userid="+userid,
			success : function(data) {   
				var data = $.parseJSON(data);  
				if (data!=null) { 
					for(var json=0;json<data.length;json++){ 
						var len = $("#tauthority tr").length-1;
						var select=$('#id'+data[json].authorityId);
						var tr="";   
						var tr='<tr id="tr'+data[json].authorityId+'"><td><select disabled="disabled" style="width:200" name="name'+len+'" id="id'+data[json].authorityId+'"></select><td><a href="#" onclick="save_authority('+data[json].authorityId+',\'\');">保存</a>&nbsp;<a href="#" onclick="enable('+data[json].authorityId+',\'\');">修改</a>&nbsp;<a onclick="removeElement('+data[json].authorityId+')" href="#">删除</a></td></tr>';
						var id=data[json].id;  
						var key='#id'+data[json].authorityId; 
						$.ajax({
							type : "POST",
							dataType : "html",
							async: false,
							url : "${pageContext.request.contextPath}/authority/get_authoritys.do", 
							success : function(r) {
								var option=''; 
								var r = $.parseJSON(r);    
								for(var num=0;num<r.length;num++){
									if(r[num].id==id){
										option=option+'<option value="'+r[num].id+'" selected="selected">'+r[num].name+'</option>';
									}else{
										option=option+'<option value="'+r[num].id+'">'+r[num].name+'</option>';
									}	
								}  	
								$('#option').text(option); 
							}
						})  
						$('#authority').append(tr);
						$(key).append($('#option').text());  
					} 
				} 
			}
		})
 	}
	function addTr(){
		if($("#userid").val()==""){
			alert("先选中要查看的用户！");
			return;
		}
		var i = $("#tauthority tr").length-1;
		var tr='<tr id="tr'+i+'"><td><select  name="name'+i+'" style="width:200" id="id'+i+'"></select><td><a href="#" onclick="save_authority(\''+"id"+i+'\');">保存</a>&nbsp;<a onclick="removeElement('+i+')" href="#">删除</a></td></tr>';
		var option='';
		$.ajax({
			type : "POST",
			dataType : "html",
			url : "${pageContext.request.contextPath}/authority/get_authoritys.do", 
			success : function(data) {
				var r = $.parseJSON(data); 
				for(var json=0;json<r.length;json++){
					option=option+'<option value="'+r[json].id+'">'+r[json].name+'</option>';
				} 
				$('#id'+i).append(option);
			} 
		}) 
		$('#authority').append(tr);
	} 
	function removeT(id){
 		if(confirm("你确定要删除么？")){
 	 		$.ajax({
 				type : "POST",
 				dataType : "json",
 				url : "${pageContext.request.contextPath}/admin/deleAdmin.do",
 				data : {id : id},
 				success : function(data) {
 					loadTable();
 				}
 			})
 			}
	}
	function enable(key){
		$("#id"+key).attr("disabled",false)
	}
 	function removeElement(key){
 		if(confirm("你确定要删除么？")){ 
	 		$.ajax({
				type : "POST",
				dataType : "json",
				url : "${pageContext.request.contextPath}/authority/remove_authority.do",
				data : {id : key},
				success : function(data) { 
					var r = $.parseJSON(data); 
					if (data.flag == 'true') {
						$("tr[id="+'tr'+key+"]").remove();
					}
				}
			})
		}
 	}
	function save_authority(key){
		var authorityId; 
		var k;
		if(!isNaN(key)){
			authorityId= $("#id"+key).val() 
			k="id"+key;
		}else{
			authorityId= $("#"+key).val() 
			k=key;
		}
		$.ajax({
			type : "POST",
			dataType : "html",
			url : "${pageContext.request.contextPath}/authority/save_authority.do",
			data : {id : key , userId : $("#userid").val(), authorityId :authorityId},
			success : function(data) {
				 var r = $.parseJSON(data);
				if (r.flag == 'true') { 
					$("#"+k).attr("disabled","disabled");
					loadPrivileges($("#userid").val());
					alert("保存成功！");
				}  
				if (r.flag == 're') {  
					alert("权限已存在！"); 
					$("tr[id="+'tr'+key.replace("id","")+"]").remove();
				}  
			}
		})
	} 
	function openDetail(id) {
		if(""==id){
			var li="<li id='v'><label>管理员密码*</label><input name=\"password\" id=\"password\" type=\"password\" class=\"dfinput\" /></li>";
			$("#next").after(li); 
			$("#h2").text("添加管理员用户");
		}else{
			$("#h2").text("修改管理员用户");
			$("#id").val(id); 
			$("#"+id).each(function (){
				var tdArr=$(this).children();
				$("#name").val(tdArr.eq(0).text());
				$("#phone").val(tdArr.eq(1).text());
				$("#sort option").each(function (){
					if($(this).text()==tdArr.eq(2).text()){
						$(this).attr("selected",true)
					}
				})
				$("#realname").val(tdArr.eq(3).text());
				$("#email").val(tdArr.eq(4).text());
			});
			$("#v").remove();
		}
		$("#dialog").show();
	}
	function closeDialog() {
		$(":text").val("");
		$(":password").val("");
		$("#v").remove();
		$("#dialog").hide();
	}
	function submitForm() {
		if (validate()) {
			$.ajax({
				type : "POST",
				dataType : "html",
				url : "${pageContext.request.contextPath}/admin/saveAdmin.do",
				data : $('#edtForm').serialize(),
				success : function(data) {
					var r = $.parseJSON(data);
					if (r.flag == 'true') {
						$("#dialog").hide();
						$(":text").val("");
						$(":password").val("");
						$("#v").remove();
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
		var reg = /^\w+$/;
		var preg = new RegExp(
				"^([A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()+=|{}\':;\',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“'。，、？]){6,16}$");
		if (!reg.test($("#name").val())) {
			$("#tip").show();
			$("#alt").text("用户名有误！");
			setTimeout("autoHide()", 2000);
			flag = false;
		}
		if (!preg.test($("#password").val())) {
			$("#tip").show();$("#alt").text("密码格式错误！");
			setTimeout("autoHide()", 2000);
			flag = false;
		}
		return flag;
	}
	function autoHide() {
		$("#tip").hide();
	}
	function resetPW(id){
		$.ajax({
			type : "POST",
			dataType : "html",
			url : "${pageContext.request.contextPath}/admin/saveAdmin.do",
			data : {id :id,password:'000000'},
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
