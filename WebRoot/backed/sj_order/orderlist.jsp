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
	width: 570px;
	height: 570px;
	position: absolute;
	left: 50%;
	top: 50%;
	margin-left: -400px;
	padding: 0 15px;
	margin-top: -250px;
	background-color: #F0F0F0;
	z-index: 999;
}
.wh {width:200px; height:28px; line-height:32px; border-top:solid 1px #a7b5bc; border-left:solid 1px #a7b5bc; border-right:solid 1px #ced9df; border-bottom:solid 1px #ced9df; background:url(../images/inputbg.gif) repeat-x; text-indent:10px;}

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
	width:200px; height:28px;
} 
</style>
</head>


<body>
	<div class="rightinfo"> 
		<div class="tools">
			<ul class="toolbar"> 
			<form method="post" id="searchForm">
				<li ><input name="name" type="text" style="width:290px; height:28px;" placeholder="请输入订单标号或手机号"/></li>
				<li ><label>订单状态</label><select  onchange="" style="width:200" name="status" id="status"  class="wh">
					<option value="">选择订单状态</option>
				</select></li>
				<li ><label>选择机型</label><select  style="width:200" name="mtype" class="wh">
				<option value="">选择机型</option>
				<option value="二屏">二屏</option>
				<option value="四屏">四屏</option>
				<option value="六屏">六屏</option> 
				<option value="定制">定制</option> 
				</select></li>  
				<li ><label>下单开始时间</label><input name="startDate" id="search_start" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></li>
				<li ><label>下单终止时间</label><input name="endDate" id="search_end" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></li> 
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
					<th>选择机型</th>
					<th>姓名</th>
					<th>手机</th>
					<th>下单时间</th>
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
					<table >
						<tr align="left"><td  width="50%"><label>&nbsp;&nbsp;订单编号：<input type="text" class="wh" disabled="disabled" name="id" id="k"/></td><td  width="50%"><label>&nbsp;&nbsp;下单时间：<input disabled="disabled" type="text" name = "cdate" class="wh" id = "cdate" /></td></tr>
						<tr align="left"><td><label>&nbsp;&nbsp;姓名：</label><input type="text" class="wh" disabled="disabled" name="name" id="name"></td><td>手机：<input type="text" disabled="disabled" class="wh" name="phone" id="phone"></td></tr>
						<tr align="left"><td  width="50%"><label>&nbsp;&nbsp;邮箱：<input name="email" id="email" class="wh" disabled="disabled" /></td><td  width="50%"><label>&nbsp;&nbsp;qq：<input type="text"  disabled="disabled" name = "qq" class="wh" id = "qq" /></td></tr>
						<tr align="left"><td colspan="2" ><label>描述：</label><textarea rows="6" cols="70" disabled="disabled" name="content" id="content"></textarea></td></tr>
						<tr align="left"><td  width="50%"><label>&nbsp;&nbsp;机型：<input name="mtype" id="mtype" class="wh" disabled="disabled" /></td><td  width="50%"><label>订单状态：<select name="status" id="statu" class="wh" ></select></td></tr>
						<tr align="left"><td colspan="2"><label>备注：<textarea rows="4" cols="70" name = "remark" id = "remark"></textarea></td></tr> 
					</table>  
					<div class="tools"> 
			</div> 
					<input type="button" onclick="submitF();" value="保存"/>
					<input type="hidden" name="id" id="id" value="" />
					<input type="hidden" name="flag" id="flag" value="order" />
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
			var mtype=""; 
			for(var i=0;i<content.length;i++) {
				if(content[i].mtype!=null){
					mtype=content[i].mtype;
				}else{
					mtype="";
				} 
				var cdate=new Date(content[i].startDate).format("yyyy-MM-dd");  
				html=html+'<tr id="'+content[i].id+'"><td>'+content[i].status+'</td><td>'+content[i].id+'</td><td>'+mtype+'</td><td>'+content[i].name+'</td><td>'+content[i].phone
				+'</td><td>'+cdate+'</td><td>'+content[i].email+'</td><td>'+content[i].qq+'</td>'
				+'<td><a href="#" onclick="openDetail(\''+content[i].id+'\');">处理</a>&nbsp;<a onclick="remove(\''+content[i].id+'\');" href="#">删除</a></tr>';
			} 
			document.getElementById('msg').innerHTML=html;
			getPage(count,pageNum);
		}
	}
	function submitF(){ 
		$("#submitForm").attr("action","${pageContext.request.contextPath}/userOrder/updateStatus.do");
		$("#submitForm").submit();
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
			url : "${pageContext.request.contextPath}/userOrder/getOrders.do?page="+pageNum,
			data :serializeObject($('#searchForm')),
			success : function(data) { 
				jsons=data;  
			}
		})  
		return jsons; 
	}    
	function submitForm(){
		$.ajax({
				type : "POST",
				dataType : "json",
				url : "${pageContext.request.contextPath}/userOrder/updateStatus.do?id="+id+"&flag=order",
				success : function(data) {
					loadTable();
				}
			})
	}
 	function remove(id){ 
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
	function getOrderDataById(id){
		$.ajax({
				type : "POST",
				dataType : "json",
				url : "${pageContext.request.contextPath}/userOrder/getOrderDataById.do?id="+id,
				success : function(data) { 
					$("#id").val(data.id);$("#k").val(data.id);
					$("#name").val(data.name);	$("#mtype").val(data.mtype);
					$("#cdate").val(new Date(data.startDate).format("yyyy-MM-dd"));
					$("#email").val(data.email);
					$("#phone").val(data.phone); 
					$("#title").val(data.title);
					$("#content").val(data.content);
					$("#remark").val(data.remark);
					getStatus("statu");
					$("#statu option[value="+data.status+"]").attr("selected","selected");
				}
			})
	} 
 	function openDetail(id) { 
		$("#data").empty();
		getOrderDataById(id); 
		$("#dialog").show();
	}
	function closeDialog() { $("input[type=text]").val("");
		 $("#dialog").hide();
	}  
	function getStatus(flag){
		$.ajax({
				type : "POST",
				dataType : "json",
				async :false,
				url : "${pageContext.request.contextPath}/userOrder/getOrderStatus.do", 
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