<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<script type="text/javascript" src="<%=path%>/js/WdatePicker/WdatePicker.js"></script>
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
	height: 680px;
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

.wh {
	width: 200px;
	height: 28px;
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
    
.hover {
	background-color: #E0E0E0;
}               

.demo {
	width: 570px;             
	height: 480px;
	position: absolute;          
	left: 50%;                                                  
	top: 50%;
	margin-left: -400px;            
	padding: 0 15px;               
	margin-top: -250px;
	background-color: #F0F0F0;            
	z-index: 999;  
}              

.select_side {           
	float: left;
	width: 200px
}                
 
           
.select_opt {
	float: left;             
	width: 40px;
	height: 100%;                                                     
	margin-top: 36px
}

.select_opt button {
	width:60px;
	height:30px;        
	text-align:center;
	line-height:30px;
	border:1px solid #666666;       
	margin-top: 6px; 
	cursor: pointer;
}
                
.select_opt p#toright {                   
	background-position: 2px 0
}

.select_opt p#toleft {                
	background-position: 2px -22px
}
         
.sub_btn {
	clear: both;
	height: 42px;          
	line-height: 42px;
	padding-top: 10px;
	text-align: center
}            
.clearfix {
  zoom: 1; 
} 
.clearfix:before,                
.clearfix:after {
  display: table; 
  line-height: 0; 
  content: ""; 
}
.clearfix:after {            
  clear: both;                                                 
} 
</style>
<script type="text/javascript">
	function autoS(val) {
		$.ajax({
			type : "POST",                 
			dataType : "json",
			url : "${pageContext.request.contextPath}/user/phoneList.do",
			data : {                                                    
				phone : val
			},
			success : function(data) {
				var options = "";
				var r = $.parseJSON(data);
				for (var i = 0; i < r.length; i++) {
					options += '<option ondblclick="toRight(this)">' + r[i].phone + '</option>';            
				}
				$("#selectL").empty();                                  
				$("#selectL").append(options);
			}
		})
	}
	function toRight(e){                           
		var option;                
		if("undefined"==typeof(e)){                              
			if(""!=$("#selectL option:selected").text()){
				appendEle( $("#selectL option:selected").text());
			}else{                   
				alert("手机号不能为空！")
			}              
		}else {
			option=e;           
			appendEle(option.text);
		}
		var roptions=$("#selectR option"); 	 
	}
	function appendEle(text){             
		var flag=true;
		$("#selectR option").each(function (){                                
			if(text==$(this).text()){
				flag=false;
			}                       
		})
		if(flag){
			$("#selectR").append('<option ondblclick="toLeft(this)">' + text + '</option>');
		}                  
	}
	function toLeft(){
		var option;                
		if("undefined"==typeof(e)){
			 option=$("#selectR option:selected");
		}else option=e;              
		$(option).remove()
	}
	function anota(){                              
		if($("#autoA").attr("checked")){
			$.ajax({
				type : "POST",
				dataType : "json",                               
				url : "${pageContext.request.contextPath}/user/phoneList.do",
				data : {
					phone : "1"
				},
				success : function(data) {
					var options = "";             
					var r = $.parseJSON(data);
					for (var i = 0; i < r.length; i++) {
						options += '<option ondblclick="toLeft(this)">' + r[i].phone + '</option>';
					}
					$("#selectR").empty();
					$("#selectR").append(options);
				}                      
			})               

		}else $("#selectR").empty();
	}
	function fs(){
		var roptions=$("#selectR option"); 
		var phones="";                 
			if(roptions!=null&&roptions.length>0){ 
				for (var i = 0; i < roptions.length; i++){ 
					if(!(/^1(3|4|5|7|8)\d{9}$/.test(roptions[i].text))){           
						
					}else{
						phones+=roptions[i].text+","                           
					}
				} 
			}if($("#autoC").val()==""){
				alert("内容不能为空!")
				return;                 
			}
			if(phones==""){
				alert("手机号不能为空！")
				return;
			} 
		$.ajax({
			type : "POST",
			dataType : "json",
			url : "${pageContext.request.contextPath}/notice/fsdx.do",
			data : {
				phones : phones,content:""+$("#autoC").val()+""
			},                  
			success : function(data) {
				closeAuto();
			}
		})                                                                  
	}
</script>
</head>

<body>
<div id="windialog" class="demo" style="display: none; width: 600px;">
			<p class="clearfix" style="width:100%;height:40px;line-height:40px;border-bottom:1px solid #cccccc;margin:20px 0">
				<span style="float:left">短信编辑</span>
				<a  href="javascript:void(0);" style="float:right" onclick="closeAuto();">取消</a>
			</p>
			<p class="clearfix">                               
				<span style="height:71px;width:70px;line-height:71px;text-align:right;float:left">短信内容：</span>
				<textarea style="float:right;width:calc(100% - 70px)"  id="autoC" rows="4"></textarea>	
			</p>
			<p class="clearfix" style="margin:20px 0">                     
				<span style="width:70px;float:left;text-align:right">发送到：</span>
				<input style="float:left;width:calc(50% - 70px);height:20px" id="autoS" onkeyup="autoS(this.value);" type="text">
				<span style="width:70px;float:left;text-align:right">全部&nbsp;&nbsp;</span>
				<input style="float:left;width:20px;height:20px" id="autoA" onchange="anota();" type="checkbox">
				
			</p>                                 
			
			<div class="select_side" style="width:45%;text-align:center">
				<p style="line-height:24px">待选区</p>
				<select id="selectL" name="selectL"  multiple="multiple" style="	width: 180px;
	height: 120px;overflow:auto">
					 				</select>
			</div>                                   
			<div class="select_opt" style="width:10%;text-align:center"> 
				<p id="toright" title="添加"><button onclick="toRight();">添加</button></p>
				<p id="toleft" title="移除"><button  onclick="toLeft();">移除</button></p>
			</div>
			<div class="select_side" style="width:45%;text-align:center">
				<p style="line-height:24px">已选区</p>
				<select id="selectR" name="selectR" multiple="multiple" style="overflow:auto;	width: 180px;
	height: 120px">
				
				</select>                    
			</div>
				<p class="clearfix" style="margin:20px 0">
				<span style="width:90px;float:right;height:40px;text-align:right"><input style="width:60px;
	height:30px;
	text-align:center;
	line-height:30px;                          
	border:1px solid #666666;                                                           
	margin-top: 6px; 
	cursor: pointer;" type="button" id="fs" onclick="fs();" value="发送" /></span>
				</p> 
</div>
	<div class="rightinfo"> 
		<div class="tools">
			<ul class="toolbar"> 
			<form method="post" id="user_searchForm">
				<li ><input name="name" type="text" style="width:290px; height:28px;" placeholder="可搜索用户名、姓名、手机号、工作单位、邮箱"/></li>
				<li ><label>所在省</label><select  onchange="getS_Cities();" style="width:200" name="province" id="s_province"  class="wh">
					<option value="0">选择省</option>
				</select></li>
				<li ><label>所在市</label><select  style="width:200" name="city" id="s_city" class="wh"></select></li> 
				<li ><label>职业</label><select name="profession"   class="wh">
				<option value="0">选择职业</option>               
					<option value="4">产品经理</option>
					<option value="5">金融研究员</option>
					<option value="6">经纪人</option>
					<option value="7">开发工程师</option>
					<option value="8">职业炒股人</option>                                                             
					<option value="9">自由职业者</option>
					</select></li>
					<li ><label>注册时间</label><input name="startDate" id="search_start" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></li>
					<li ><label>注册时间</label><input name="endDate" id="search_end" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></li>
					<li><label>来源</label> 
							<select name="source" id="source"  class="wh"> 
							<option value="">选择来源</option>
								<option value="0">后台创建</option>
								<option value="1">PC</option>
								<option value="2">APP</option>
							</select>
					</li> 
					</form>
			</ul>                     
		</div>
		<div class="tools">                               
			<ul class="toolbar">
				<li class="click" onclick="openDetail('');"><span><img src="<%=path%>/images/t01.png" /></span>添加</li>
				<li class="click" onclick="loadTable();"><span><img src="" /></span>查询</li>
				<li class="click" onclick="showWinPhone();"><span><img src="" /></span>发送短信</li>
			</ul>
		</div>
		<table class="tablelist">
			<thead>
				<tr>
					<th>用户名</th>
					<th>真实姓名</th>
					<th>性别</th>                   
					<th>出生日期</th>
					<th>手机号码</th>               
					<th>所在地</th>
					<th>职业</th>
					<th>工作单位</th>
					<th>邮箱</th>
					<th>注册时间</th>
					<th>来源</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="msg"> </tbody>
		</table>
		<div class="pagin" id="pagin"></div>                      
		<div class="tools">
			<ul class="toolbar">
				<li class="click" onclick="addTr();"><span><img
						src="<%=path%>/images/t01.png" /></span>添加频道</li> 
			</ul>
			 
		</div>
		<table id="tauthority" class="tablelist">
			<thead>
				<tr>
					<th>菜单名称</th>                                                    
					<th>开始时间</th>
					<th>截止时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="authority">
			</tbody>
		</table>
		<div id="dialog" class="Popup" style="display: none; width: 200;">
			<div class="Popup_top">
				<h2 id="h2"></h2>                              
				<a href="javascript:void(0);" class="Close" onclick="closeDialog();">关闭</a>
			</div> 
			<form name="edtForm" id="edtForm" action="" method="post">                                 
				<input type="hidden" name="id" id="id" value="" />
				<input type="hidden" name="userid" id="userid" value="" />
				<ul class="forminfo" id="ul">
					<li id="next"><label>用户名*</label><input name="name" id="name" type="text" class="dfinput" /></li> 
					<li ><label>真实姓名</label><input name="realname" id="realname" type="text" class="dfinput" /></li> 
					<li><label>性别</label>
						<div class="vocation">
								<select name="sex" id="sex" class="dfinput"> 
								<option value="">选择性别</option>
									<option value="0">男</option>
									<option value="1">女</option>
								</select>
						</div>
					</li>	                                 
					<li ><label>手机号码</label><input name="phone" id="phone" type="text" class="dfinput" /></li> 
					<li ><label>出生日期</label><input name="birthday" id="birthday" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,maxDate:'%y-%M-%d'})" /></li> 
					<li ><label>所在省</label><div class="vocation"><select  onchange="getCities();" style="width:200" name="province" id="province" class="dfinput"></select></div></li>
					<li ><label>所在市</label><div class="vocation"><select  style="width:200" name="city" id="city" class="dfinput"></select></div></li>
					<li ><label>职业</label><select name="profession" id="profession" class="dfinput">
					<option value="0">选择职业</option>
					<option value="4">产品经理</option>                                      
					<option value="5">金融研究员</option>
					<option value="6">经纪人</option>
					<option value="7">开发工程师</option>
					<option value="8">职业炒股人</option>
					<option value="9">自由职业者</option>
					</select></li> 
					<li ><label>工作单位</label><input name="company" id="company" type="text" class="dfinput" /></li>
					<li ><label>邮箱</label><input name="email" id="email" type="text" class="dfinput" /></li>	
					<li><label>分类</label>
					<div class="vocation">                              
							<select name="type" id="type" class="dfinput">
								<option value="0">后台创建</option>
								<option value="1">PC</option>
								<option value="2">APP</option>
							</select>
					</div></li> 
					<li><label>&nbsp;</label><input name="" onclick="submitForm();" type="button" class="btn" value="确认保存" /></li>
				</ul>
			</form>
		</div>
		<p id=option style="display:none"></p>
		<input type="hidden" name="option" id="option" value="" />
		<input type="hidden" name="trid" id="trid" value="" />
		<div id="tip">
			<h1>
				<a href="javascript:void(0)" onclick="start()"></a>提示
			</h1>                       
			<p id="alt"></p>
		</div>
		</body>
</html>
<script type="text/javascript">
function showWinPhone(){
	$("#windialog").show();
}
function closeAuto(){
	$("#windialog").hide();
	$("#autoC").val("");
	$("#autoS").val("");
	$("#selectL").empty();
	$("#selectR").empty();
	$("#autoA").attr("checked",false);
}                      
var pageNum=0; 
function loadTable(){
	document.getElementById('msg').innerHTML="";
	var data=getList();
	var content=data.content;
	var count=data.count; 
	getPage(count,pageNum);
	if(content!=null) { 
		var html='';
		for(var i=0;i<content.length;i++) { 
			var address="";                   
			var profession="";
			var company="";
			var realname="";
			var name="";
			var birthday="";
			var createDate="";
			var email="";                    
			var sex="";
			var source="PC"; 
			if(content[i].province!=null&&content[i].province!=""){
				address=content[i].province;
				if(content[i].city!=null&&content[i].city!=""){
					address=address+"-"+content[i].city;
				}  
			}
			if(content[i].profession!=null&&content[i].profession!=""){
				profession=content[i].profession;
			}
			if(content[i].company!=null&&content[i].company!=""){
				company=content[i].company;
			}
			if(content[i].email!=null&&content[i].email!=""){
				email=content[i].email;                     
			}
			if(content[i].regisday!=null&&content[i].regisday!=""){
				createDate=content[i].regisday
			}
			if(content[i].birthday!=null&&content[i].birthday!=""){
				birthday=content[i].birthday.split(" ")[0];
			}
			if(content[i].name!=null&&content[i].name!=""){
				name=content[i].name;
			}
			if(content[i].realname!=null&&content[i].realname!=""){
				realname=content[i].realname;
			}
			if(content[i].sex=="0"){
				sex="男";
			} if(content[i].sex=="1"){
				sex="女";
			}                       
			if(content[i].source=="0"){
				source="后台创建";
			} 
			if(content[i].source=="1"){
				source="PC";
			}  
			if(content[i].source=="2"){
				source="APP";
			} 

			html=html+'<tr id="'+content[i].id+'" onclick="loadPrivileges('+content[i].id+'); "><td>'+name+'</td><td>'+realname+'</td><td>'+sex+'</td><td>'+birthday.replace(".0","")+'</td><td>'+content[i].phone
			+'</td><td>'+address+'</td><td>'+profession+'</td><td>'+company+'</td><td>'+email+'</td><td>'+createDate.replace(".0","")+'</td><td>'+source
			+'</td><td><a href="#" onclick="openDetail('+content[i].id+');">修改</a>&nbsp;<a onclick="removeT('+content[i].id+');" href="#">删除</a>&nbsp;<a onclick="resetPW('+content[i].id+');" href="#">重置密码</a></td></tr>';
		}$('#authority').empty(); 
			loadPrivileges(content[0].id); 
		  	document.getElementById('msg').innerHTML=html; 
		  	$("#"+content[0].id).addClass("hover").siblings().removeClass("hover");
		}                          
}
function getList(){
	var jsons;          
	$.ajax({
		type : "POST",
		dataType : "json",                        
		async: false,
		url : "${pageContext.request.contextPath}/user/getList.do?page="+pageNum,
		data :serializeObject($('#user_searchForm')),
		success : function(data) {       
			jsons=$.parseJSON(data);  
		}
	}) 
	return jsons;     
} 
serializeObject = function(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {   
		if (o[this['name']]) {                               
			if(this['value'] == '' || this['value'] == null){
				
			}else{
			   o[this['name']] = o[this['name']] + "," + this['value'];
			}
		} else {
			if(this['value'] == '' || this['value'] == null){
				
			}else{
			   o[this['name']] = this['value'];
			}
		}           
	});
	return o;
};                              
loadTable();
	function loadPrivileges(userid){  
		$("#"+userid).addClass("hover").siblings().removeClass("hover");
		$("#userid").val(userid); 
		$('#authority').empty(); 
		$.ajax({
			type : "POST",
			dataType : "html",
			url : "${pageContext.request.contextPath}/rightTree/getTreeListByUserId.do?userid="+userid,
			success : function(data) {   
				var data = $.parseJSON(data);
				var ops;
				$.ajax({
					type : "POST",         
					dataType : "html",
					async: false,
					url : "${pageContext.request.contextPath}/rightTree/getTreeList.do", 
					success : function(r) {       
						var option=''; 
						var r = $.parseJSON(r);    
						ops=r;                                  
					}
				})
					for(var json=0;json<data.length;json++){ 
						var len = $("#tauthority tr").length-1;
						var select=$('#id'+data[json].id);
						var tr="";   
						var tr='<tr id="tr'+data[json].id+'"><td><select disabled="disabled" style="width:200" name="name'+len+'" id="id'+data[json].id+'"></select></td><td><input name="startDate'+data[json].id+'" id="startDate'+data[json].id+'" type="text" disabled="disabled" class="Wdate" onfocus="WdatePicker({dateFmt:\'yyyy-MM-dd HH:mm:ss\'})" /></td>'
						+'<td><input name="endDate'+data[json].id+'"  disabled="disabled" id="endDate'+data[json].id+'" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:\'yyyy-MM-dd HH:mm:ss\'})" /></td>'+
						'<td><a href="#" onclick="save_authority('+data[json].id+',\'\');">保存</a>&nbsp;<a href="#" onclick="enable('+data[json].id+',\'\');">修改</a>&nbsp;<a onclick="removeElement('+data[json].id+')" href="#">删除</a></td></tr>';
						var id=data[json].rightId;  
						var key='#id'+data[json].id; 
						for(var num=0;num<ops.length;num++){                                  
							if(ops[num].id==id){
								option=option+'<option value="'+ops[num].id+'" selected="selected">'+ops[num].name+'</option>';
							}else{
								option=option+'<option value="'+ops[num].id+'">'+ops[num].name+'</option>';
							}	
						}  	
						$('#option').text(option);   
						var end=new Date(data[json].endDate).format("yyyy-MM-dd hh:mm:ss");
						var start=new Date(data[json].startDate).format("yyyy-MM-dd hh:mm:ss"); 
						$('#authority').append(tr);
						$(key).append($('#option').text());  
						$('#endDate'+data[json].id).val(end);
						$('#startDate'+data[json].id).val(start);
					}  
			}
		})
 	}                                            
	function addTr(){ 
		var i = $("#tauthority tr").length-1;
		var tr='<tr id="tr'+i+'"><td><select  name="name'+i+'" style="width:200" id="id'+i+'"></select></td><td><input name="startDate'+i+'"  id="startDate'+i+'" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:\'yyyy-MM-dd HH:mm:ss\'})" /></td>'
		+'<td><input name="endDate'+i+'" id="endDate'+i+'" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:\'yyyy-MM-dd HH:mm:ss\'})" /></td><td><a href="#" onclick="save_authority(\''+"id"+i+'\');">保存</a>&nbsp;<a href="#" onclick="save_authority('+''+',\'\');">修改</a>&nbsp;<a onclick="removeElement('+i+')" href="#">删除</a></td></tr>';
		var option='';
		$.ajax({
			type : "POST",
			dataType : "html",
			url : "${pageContext.request.contextPath}/rightTree/getTreeList.do", 
			success : function(data) {
				var r = $.parseJSON(data); 
				for(var json=0;json<r.length;json++){
					option=option+'<option value="'+r[json].id+'">'+r[json].name+'</option>';
				} 
				$('#id'+i).append(option);
			}                                    
		}) 
		$('#authority').append(tr);
		$('#endDate'+i).val(new Date().format("yyyy-MM-dd hh:mm:ss"));
		$('#startDate'+i).val(new Date().format("yyyy-MM-dd hh:mm:ss"));
	}
	function enable(key){
		$("#id"+key).attr("disabled",false);
		$('#endDate'+key).attr("disabled",false);
		$('#startDate'+key).attr("disabled",false);
	}
 	function removeElement(key){
 		if(confirm("你确定要删除么？")){ 
 		$.ajax({
			type : "POST",                           
			dataType : "json",
			url : "${pageContext.request.contextPath}/rightTree/remove_authority.do",
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
 	function removeT(id){
 		if(confirm("你确定要删除么？")){                         
 	 		$.ajax({
 				type : "POST",
 				dataType : "json",
 				url : "${pageContext.request.contextPath}/user/deleUser.do",
 				data : {id : id},
 				success : function(data) {
 					loadTable();
 				}
 			})
 			}
	}
	function save_authority(key){
		var authorityId;
		var  startDate ;var  endDate ;                     
		if(!isNaN(key)){
			authorityId= $("#id"+key).val() 
			startDate=$("#startDate"+key).val();
			endDate=$("#endDate"+key).val();
		}else{ 
			authorityId= $("#"+key).val() 
			startDate=$("#startDate"+key.replace("id","")).val();
			endDate=$("#endDate"+key.replace("id","")).val();
		} 
		$.ajax({
			type : "POST",
			dataType : "json",
			url : "${pageContext.request.contextPath}/rightTree/saveUserRightRole.do",
			data : {id : key , userId : $("#userid").val(), rightId :authorityId,startDate: startDate,endDate: endDate},
			success : function(data) { 
				var r = $.parseJSON(data);  
				if (r.flag == 'true') {                             
					alert("保存成功！");
					loadPrivileges($("#userid").val()); 
				}
			if (r.flag == 're') {   
				alert("权限已存在！"); 
				$("tr[id="+'tr'+key.replace("id","")+"]").remove();
			}}                      
		})                                          
	} 
	function getProvinces(){
		$.ajax({
			type : "POST",
			dataType : "html",
			url : "${pageContext.request.contextPath}/user/getprovinceList.do", 
			success : function(data) {
				var r = $.parseJSON(data); 
				var option='<option value="0">选择所在地</option>';
				for(var json=0;json<r.length;json++){
					option=option+'<option value="'+r[json].proid+'"  >'+r[json].proname+'</option>';
				} 
				$('#province').append(option); 
			}                                                                                                   
		}) 
	}
	getS_Provinces();
	function getS_Provinces(){
		$.ajax({
			type : "POST",
			dataType : "html",
			url : "${pageContext.request.contextPath}/user/getprovinceList.do", 
			success : function(data) {
				var r = $.parseJSON(data); 
				var option="";
				for(var json=0;json<r.length;json++){
					option=option+'<option value="'+r[json].proid+'"  >'+r[json].proname+'</option>';
				}  
				$('#s_province').append(option); 
			} 
		}) 
	}                          
	function getCities(){
		$.ajax({
			type : "POST",
			dataType : "html",
			url : "${pageContext.request.contextPath}/user/getcityList.do", 
			data:{proid : $('#province').val()},
			success : function(data) {
				var option="";
				var r = $.parseJSON(data); 
				for(var json=0;json<r.length;json++){
					option=option+'<option value="'+r[json].cityid+'">'+r[json].cityname+'</option>';
				} 
				$('#city').empty();
				$('#city').append(option); 
			} 
		}) 
	}
	function getS_Cities(){
		$.ajax({                                        
			type : "POST",
			dataType : "html",
			url : "${pageContext.request.contextPath}/user/getcityList.do", 
			data:{proid : $('#s_province').val()},
			success : function(data) {
				var option="";
				$('#s_city').empty();
				var r = $.parseJSON(data); 
				for(var json=0;json<r.length;json++){
					option=option+'<option value="'+r[json].cityid+'">'+r[json].cityname+'</option>';
				}    
				$('#s_city').append(option);
			} 
		}) 
	}
	function openDetail(id) {  
		getProvinces(); 
		$("#li").remove();
		if(""==id){                                                                     
			$("#next").after('<li id="li"><label>用户密码*</label><input name="password" id="password" type="password" class="dfinput" /></li>')
			$("#h2").text("添加用户"); 
		}else{
			$("#h2").text("修改数金用户");
			$("#id").val(id); 
			$("#"+id).each(function (){
				var tdArr=$(this).children();
				$("#name").val(tdArr.eq(0).text()); 
				$("#realname").val(tdArr.eq(1).text());
				$("#sex option").each(function (){
					if($(this).text()==tdArr.eq(2).text()){
						$(this).attr("selected",true)
					}
				})
				$("#birthday").val(tdArr.eq(3).text());
				$("#phone").val(tdArr.eq(4).text());
				$("#profession option").each(function (){
					if($(this).text()==tdArr.eq(5).text()){
						$(this).attr("selected",true)
					}
				})
				$("#province option").each(function (){
					if($(this).text()==tdArr.eq(6).text().split("-")[0]){
						$(this).attr("selected",true)
					}
				})
				$("#city option").each(function (){
					if($(this).text()==tdArr.eq(6).text().split("-")[1]){
						$(this).attr("selected",true)
					}
				}) 
				$("#company").val(tdArr.eq(7).text());
				$("#email").val(tdArr.eq(8).text());
				$("#source option").each(function (){
					if($(this).text()==tdArr.eq(10).text()){
						$(this).attr("selected",true)
					}
				})
			});  
		}
		$("#dialog").show();
	}
	function closeDialog() { 
		cleanForm();
		$("#li").remove();
	}
	function submitForm() {
		if (validate()) {
			$.ajax({
				type : "POST",
				dataType : "html",
				url : "${pageContext.request.contextPath}/user/saveUser_back.do",
				data : $('#edtForm').serialize(),
				success : function(data) {
					var r = $.parseJSON(data); 
					if (r.flag == 'true') { 
						cleanForm();
					} else {
						alert(r.msg);
					}
				}
			})
		}
	}
	function cleanForm(){
		$("#li").remove();
		$("#dialog").hide();
		$(":text").val("");
		$(":password").val("");
		$('#province').empty(); 
		$('#city').empty();
		loadTable();
	}
	function validate() {
		var flag = true;
		var reg = /^\w+|[\u4e00-\u9fa5]$/;
		var preg = new RegExp(
				"^([A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()+=|{}\':;\',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“'。，、？]){6,16}$");
		if ($("#name").val()==""&&!reg.test($("#name").val())) {
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
			dataType : "json",
			url : "${pageContext.request.contextPath}/user/saveUser_back.do",
			data : {id :id,password:'000000'},
			success : function(r) { 
				if (r.flag == 'true') { 
					alert('密码重置成功！');
				} else {
					alert('系统出错！');
				}
			}
		})
	}
	Date.prototype.format = function(fmt) { 
	     var o = { 
	        "M+" : this.getMonth()+1,                 //月份 
	        "d+" : this.getDate(),                    //日 
	        "h+" : this.getHours(),                   //小时 
	        "m+" : this.getMinutes(),                 //分 
	        "s+" : this.getSeconds(),                 //秒  
	        "S"  : this.getMilliseconds()             //毫秒 
	    }; 
	    if(/(y+)/.test(fmt)) {
	            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	    }
	     for(var k in o) {
	        if(new RegExp("("+ k +")").test(fmt)){
	             fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
	         }
	     }
	    return fmt; 
	} 
</script>
