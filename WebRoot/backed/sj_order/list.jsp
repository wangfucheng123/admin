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
	getStatus();
	loadTable(); 
});
</script>

<style type="text/css">
.Popup {
	width: 680px;
	height: 620px;
	position: absolute;
	left: 50%;
	top: 50%;
	margin-left: -400px;
	padding: 0 15px;
	margin-top: -250px;
	background-color: #F0F0F0;
	z-index: 999;
	overflow-y:scroll;
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
.wh {width:200px; height:28px; line-height:32px; border-top:solid 1px #a7b5bc; border-left:solid 1px #a7b5bc; border-right:solid 1px #ced9df; border-bottom:solid 1px #ced9df; background:url(../images/inputbg.gif) repeat-x; text-indent:10px;}
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
.tableForm {
	border-collapse: collapse;
	font-family:helvetica,tahoma,verdana,sans-serif;
    padding:1px;
    font-size:13px;
}

.tableForm th {
	text-align: right;
	border-bottom: 1px dotted #ccc;
	padding: 5px;
}

.tableForm td {
	text-align: left;
	border-bottom: 1px dotted #ccc;
	padding: 5px;
}

.tableForm input {
	text-align: left;
	width:170px;
} 
.textarea {
 	width:80%;
}
</style>
</head>


<body>
	<div class="rightinfo"> 
		<div class="tools">
			<ul class="toolbar"> 
			<form method="post" id="searchForm">
				<li ><input name="name" type="text" style="width:290px; height:28px;" placeholder="请输入订单标号或手机号"/></li>
				<li ><label>订单状态</label><select style="width:200" name="status" id="status"  class="wh">
					<option value="">选择订单状态</option>
				</select></li>
				<li ><label>定制渠道</label><select  style="width:200" name="fromwhere" class="wh">
				<option value="">选择定制渠道</option>
				<option value="官网">官网</option>
				<option value="数金一体机">数金一体机</option>
				<option value="数享APP">数享APP</option> 
				</select></li>  
				<li ><label>下单时间</label><input name="startDate" id="search_start" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></li>
				<li ><label>下单时间</label><input name="endDate" id="search_end" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></li> 
			</form>
			</ul>
		</div>
		<div class="tools">
			<ul class="toolbar"> 
				<li class="click" onclick="loadTable();"><span><img src="" /></span>查询</li>
			</ul>
		</div>
		<table class="tablelist">
			<thead>
				<tr>
					<th>订单状态</th>
					<th>订单号</th>
					<th>需求</th>
					<th>姓名</th>
					<th>手机</th>
					<th>下单时间</th>
					<th>定制渠道</th>
					<th>邮箱</th> 
					<th>QQ</th> 
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="msg"> </tbody>
		</table>
		<div class="pagin" id="pagin"></div> 
		<div id="dialog" class="Popup" style="display: none; width: 200;">
			<div class="Popup_top">
				<h2 id="h2">在线订单处理</h2>
				<a href="javascript:void(0);" class="Close" onclick="closeDialog();">关闭</a>
			</div> 
			<form action="" name="submitForm" id="submitForm" method="post" >
				<table width="100%">
					<tr align="right"><td  width="50%">订单编号：<input type="text" class="wh" disabled="disabled" name="id" id="k"/></td><td  width="50%">下单时间：<input disabled="disabled" type="text" name = "cdate" class="wh" id = "cdate" /></td></tr>
					<tr align="right"><td  width="50%">姓名：<input type="text" class="wh" disabled="disabled" name="name" id="name"/></td><td  width="50%">手机：<input type="text" disabled="disabled" class="wh" name="phone" id="phone"></td></tr>
					<tr align="right"><td  width="50%">邮箱：<input name="email" id="email" class="wh" disabled="disabled" /></td><td  width="50%">qq：<input type="text"  disabled="disabled" name = "qq" class="wh" id = "qq" /></td></tr>
					<tr align="right"><td  colspan="2" >描述：<textarea rows="6" class="textarea" cols="77" disabled="disabled" name="content" id="content"></textarea></td></tr>
					<tr align="right"><td  width="50%">订单状态：<select name="status" onchange="cancelDis();" id="statu" class="wh" ></select></td><td  width="50%">订单价格（元）：<input name = "price" disabled="disabled" class="wh" id = "price" onblur="check();"/></td></tr>
					<tr align="right"><td colspan="2">备注：<textarea class="textarea" rows="4" cols="77" name = "remark" id = "remark"></textarea></td></tr> 
				</table>  
				<div class="tools">
			<ul class="toolbar">
				<li class="click" onclick="addData();"><span><img
						src="<%=path%>/images/t01.png" /></span>添加</li> 
			</ul> 
		</div>
				<table id="data">  
				</table>   	
				<input type="hidden" name="id" id="id" value="" />
				<input type="button" onclick="submitF();" value="保存"/> 
			</form>
		</div> 
		<input type="hidden" name="option" id="option" value="" />
</body>
</html>
<script type="text/javascript">
	var pageNum=0; 
	function loadTable(){
		var data=getList(); 
		if(data!=null) {
			var content=data.content;
			var count=data.count;
			var html='';
			for(var i=0;i<content.length;i++) { 
				var cdate=new Date(content[i].startDate).format("yyyy-MM-dd");  
				html=html+'<tr id="'+content[i].id+'"><td width="8%">'+content[i].status+'</td><td width="12%">'+content[i].id+'</td><td width="20%">'+content[i].content+'</td><td width="9%">'+content[i].name+'</td><td width="9%">'+content[i].phone
				+'</td><td width="9%">'+cdate+'</td><td width="9%">'+content[i].fromwhere+'</td><td width="10%">'+content[i].email+'</td><td width="8%">'+content[i].qq+'</td>'
				+'<td><a href="#" onclick="openDetail(\''+content[i].id+'\');">处理</a>&nbsp;<a onclick="removeT(\''+content[i].id+'\');" href="#">删除</a></tr>';
			} 
			document.getElementById('msg').innerHTML=html; 
		}
		getPage(count,pageNum);
	}
	function cancelDis(){
		if($("#statu").val()=="3"){ 
			$("#price").attr("disabled",false)
		}else if($("#statu").val()<=2){
			$("#price").attr("disabled",true)
			$("#price").val("");
		}else {
			$("#price").attr("disabled",true) 
		}
	}
	function check(){ 
		if(isNaN($("#price").val())){
			alert("不是数字！");
			return ;
		}
	}
	function addData(){
		var data;
		var i = $("#data tr").length;
		data=data+'<tr align="right" id="tr'+i+'"><td  width="29%">url<input type="text" class="wh" value="" name="url" id="url"></td><td  width="29%">描述<input type="text" class="wh" name="description" value=" " id="description"></td>'
		+'<td  width="29%">类别<select  class="wh" name="type" id="type"><option value="page">页面</option><option value="attc">附件</option><select/></td><td  ><input type="hidden" class="wh" name="cid" id="cid" value=" "/><a onclick="removeE(\''+"tr"+i+'\');" href="#">删除</a></tr>';
		$("#data").append(data);
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
	}
	function getList(){
		var jsons;
		$.ajax({
			type : "POST",
			dataType : "json",
			async: false,
			url : "${pageContext.request.contextPath}/userOrder/getUserOrders.do?page="+pageNum,
			data :serializeObject($('#searchForm')),
			success : function(data) { 
				jsons=data;  
			}
		})
		return jsons;  
	}    
	function submitF(){ 
		if($("#statu").val()=="3"&&$("#price").val()==""){
			alert("填写价格！");
			return;
			}
			$("#submitForm").attr("action","${pageContext.request.contextPath}/userOrder/updateStatus.do");
			$("#submitForm").submit();
			$("#price").attr("disabled",true)
	}
 	function removeT(id){ 
 		if(confirm("你确定要删除么？")){ 
 	 		$.ajax({
 				type : "POST",
 				dataType : "json",
 				url : "${pageContext.request.contextPath}/userOrder/deleUserOrder.do?id="+id,
 				success : function(data) {
 					loadTable(); 
 				}
 			})
 			}
	} 
 	function removeE(id){ 
 		if(confirm("你确定要删除么？")){
 			$("#"+id).remove();
 			if(!isNaN(id)) {
 				$.ajax({
 					type : "POST",
 					dataType : "json",
 					url : "${pageContext.request.contextPath}/userOrder/removeCData.do?id="+id,
 					success : function(data) { 
 					}
 				}) 
 			}}
	} 
	function getOrderDataById(id){
		$.ajax({
				type : "POST",
				dataType : "json",
				url : "${pageContext.request.contextPath}/userOrder/getOrderDataById.do?id="+id,
				success : function(data) { 
					$("#id").val(data.id);$("#k").val(data.id);
					$("#name").val(data.name);
					$("#cdate").val(new Date(data.startDate).format("yyyy-MM-dd"));
					$("#email").val(data.email);
					$("#phone").val(data.phone);
					$("#price").val(data.price);
					$("#qq").val(data.qq);
					$("#title").val(data.title);
					$("#content").val(data.content);$("#remark").val(data.remark);
					getStatus("statu");
					$("#statu option[value="+data.status+"]").attr("selected","selected");
					if($("#statu").val()=="3"){
						$("#price").attr("disabled",false)
					}
					if($("#statu").val()>=6){
						$("#statu").attr("disabled",true)
					}
				}
			})
	} 
	function getDatas(userid){
		$.ajax({
				type : "POST",
				dataType : "json",
				url : "${pageContext.request.contextPath}/userOrder/getCData.do?userId="+userid,
				success : function(data) { 
					var tr="";
					for(var i=0;i<data.length;i++){
						var description=" ";
						if(data[i].description){
							description=data[i].description;
						}
						tr='<tr id="'+ data[i].id+'"><td>url&nbsp;<input type="text" class="wh" name="url" id="url" value="'+data[i].url+'"></td><td>&nbsp;描述&nbsp;<input type="text" class="wh" name="description" id="description"  value="'+description+'"></td>'
						+'<td>&nbsp;类别&nbsp;<select  class="wh" name="type" id="type'+ data[i].id+'"><option value="page">页面</option><option value="attc">附件</option><select/></td><td><input type="hidden" class="wh" name="cid" id="cid" value="'+data[i].id+'"><a onclick="removeE(\''+data[i].id+'\');" href="#">删除</a></tr>';
						$("#data").append(tr);$("#type"+data[i].id+" option[value='"+data[i].type+"']").attr("selected","selected");
					} 
				}
			})
	}
	function openDetail(id) { 
		$("#data").empty();
		getOrderDataById(id);
		getDatas(id); 
		$("#dialog").show();
	}
	function closeDialog() {
		$("input[type=text]").val("");
		$("#data").empty();
		$("#dialog").hide();	$("#price").attr("disabled",true)
		$("#content").val("");$("#remark").val("");
	}  
	function getStatus(flag){
		$.ajax({
				type : "POST",
				dataType : "json",
				async :false,
				url : "${pageContext.request.contextPath}/userOrder/getStatus.do", 
				success : function(data) {
					var option=""; 
					for(var i=0;i<data.length;i++){
						option=option+'<option value="'+data[i].id+'">'+data[i].name+'</option>'
					}
					if(flag==null){
						$("#status").empty();
						$("#status").append(option);
					}else{
						$("#"+flag).empty(option);
						$("#"+flag).append(option);
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