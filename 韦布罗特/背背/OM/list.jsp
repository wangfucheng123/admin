<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% 
	String path = request.getContextPath();
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=path%>/css/demo.css" type="text/css">
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/common/common.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/WdatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<%=path%>/editor/config.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	CKEDITOR.replace('content');
	//	
});
</script>

<style type="text/css">
.Popup {
	width: 570px;
	height: 580px;
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
</style>
</head>


<body>
	<div class="rightinfo">
		<div class="tools">
			<ul class="toolbar">
				<form method="post" id="user_searchForm">
					<li><input name="stitle" id="stitle" type="text"
						style="width: 290px; height: 28px;" placeholder="请输入公告标题" /></li>
					<li><label>分类</label><select style="width: 200" name="sortC"
						id="sortC" class="wh">
							<option value="">选择公告类别</option>
							<option value="terminal">终端</option>
							<option value="app">app</option>
							<option value="message">短信</option>
							<option value="email">邮件</option>
					</select></li>
					<li class="click" onclick="loadTable();"><span><img
							src="" /></span>查询</li>
				</form>
			</ul>
		</div>
		<div class="tools">
			<ul class="toolbar">
				<li class="click" onclick="openDetail('a');"><span></span>添加</li>
				<li class="click" onclick="removet();"><span></span>删除</li>
				<li class="click" onclick="cancel();"><span></span>撤销</li>
			</ul>
		</div>
		<table class="tablelist">
			<thead>
				<tr>
					<th></th>
					<th>标题</th>
					<th>内容</th>
					<th>分类</th>
					<th>已发送</th>
				</tr>
			</thead>
			<tbody id="msg">
			</tbody>
		</table>
		<div class="pagin" id="pagin"></div>
		<div id="dialog" class="Popup" style="display: none; width: 200;">
			<div class="Popup_top">
				<h2 id="h2">公告</h2>
				<a href="javascript:void(0);" class="Close" onclick="closeDialog();">关闭</a>
			</div>
			<form name="edtForm" id="edtForm" action="" method="post">
				<div style="float: right; width: 98%; height: 100%;">
					标题：
					<textarea rows="2" name="title" id="title" value=""
						style="width: 400px; height: 29px"></textarea>
					<br /> 内容：
					<textarea name="content" id="content"></textarea>
					<br /> <input type="radio" name="sort" value="terminal">终端
					<input type="radio" name="sort" checked="checked" value="app">app <input
						type="radio" name="sort" value="message">短信 <input
						type="radio" name="sort" value="email">邮件 <input
						onclick="saveNotice();" type="button" value="发送" /> <input
						type="hidden" name="id" id="id" />
				</div>
			</form>
		</div>
</body>
</html>
<script type="text/javascript">
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
				var sort=""; 
				var status="";
				if(content[i].sort!=null){  
					if(content[i].sort=='terminal'){
						sort="终端";
					}
					if(content[i].sort=='email'){
						sort="邮件";
					}
					if(content[i].sort=='message'){
						sort="短信";
					}
					if(content[i].sort=='app'){
						sort="app";
					}
				}
				if(content[i].status!=null){
					if(content[i].status=='0'){
						status="已发送";
					}else{
						status="已撤销";
					}
				}
				html=html+'<tr id="tr'+content[i].id+'"><td><input type="checkbox" name="id" id="'+content[i].id+'"></td><td width="20%">'+content[i].title+'</td><td width="50%">'+content[i].content+'</td><td width="20%">'+sort+
			'</td><td>'+status+
			'</td></tr>';
			} 
			  	document.getElementById('msg').innerHTML=html; 
			}
	}
	function getList(){
		var jsons;
		$.ajax({
			type : "POST",
			dataType : "html",
			async: false,
			url : "${pageContext.request.contextPath}/notice/getNotices.do?page="+pageNum, 
			data : {title:$("#stitle").val(),sort:$("#sortC").val()},
			success : function(data) {
				jsons=$.parseJSON(data);  
			}
		}) 
		return jsons; 
	}  
	loadTable();  
 	function removet(){   
 		if($(":checked").length<2){
 			alert("选择要删除的公告！");
 			return;
 		}
 		var arras=$(":checked");
 		var id="";
 		for(var i=0;i<arras.length;i++){ 
 			id=id+arras[i].id+",";
 		}
 		if(confirm("你确定要删除么？")){ 
 	 		$.ajax({
 				type : "POST",
 				dataType : "json",
 				url : "${pageContext.request.contextPath}/notice/delNotice.do",
 				data : {id : id},
 				success : function(data) {
 					loadTable();
 				}
 			})
 			}
	}
	function cancel(){
		if($(":checked").length<2){
 			alert("选择要撤销的公告！");
 			return;
 		}
 		var arras=$(":checked");
 		var id="";
 		for(var i=1;i<arras.length;i++){ 
 			id=id+arras[i].id+",";
 			var tdArr=$("#tr"+arras[i].id).children('td').eq(3).text();  
 					if(tdArr=="短信"){
 						alert("短信不能撤销");
 						return;
 					}
 					if(tdArr=="邮件"){
 						alert("邮件暂时不能撤销");return;
 					} 
 		}
 		if(confirm("你确定要撤销么？")){ 
 	 		$.ajax({
 				type : "POST",
 				dataType : "json",
 				url : "${pageContext.request.contextPath}/notice/delNotice.do",
 				data : {id : id,flag:'c'},
 				success : function(data) {
 					loadTable();
 				}
 			})
 			}
	} 
	function saveNotice(){   
		
		$("#content").text(CKEDITOR.instances.content.getData()); 
			
		if(checkForm()){  
			$.ajax({
				type : "POST",
				dataType : "html",
				url : "${pageContext.request.contextPath}/notice/saveNotice.do", 
				data : $('#edtForm').serialize(),
				success : function(data) {
					 var r = $.parseJSON(data); 
					 cleanForm(); 
					}
			})
		} 
	}  
	function checkForm(){
		var flag=true;
		if($("#title").val()==""){
			flag=false;
			alert("标题不能为空！");return
		}
		if($("#title").val().length>30){
			flag=false;
			alert("标题最多可输入30个字符！");return
		}
		if($("#content").text()==""){
			flag=false;
			alert("内容不能为空！");return
		}
		var regex = /<[^>]+>/g;
		var html = CKEDITOR.instances.content.getData();
		var len = html.replace(regex,"").length;
		if(len>200){
			flag=false;
			alert("内容最多可输入200个字符！");return
		}
		var checked=$("[type=radio]:checked").val(); 
		if(checked=="message"&&len>70){
			flag=false;
			alert("短信内容最多可输入70个字符！");return
		}
		return flag;
	}
	function openDetail(flag) {
		$("#id").val(""); 
		$("#dialog").show();
	}
	function closeDialog() { 
		cleanForm(); 
	}
	
	function cleanForm(){ 
		$("#dialog").hide();
		$("#title").val(""); 
		$("#id").val("");
		CKEDITOR.instances.content.setData(""); 
		loadTable();
	} 
</script>
