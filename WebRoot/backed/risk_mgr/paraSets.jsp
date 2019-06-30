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
	/* getStatus(); */
	loadTable();  
	/* getList(); */
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
	        	<li class="click" onclick="openDetail('');" ><span><img src="<%=path%>/images/t01.png" /></span>添加活动</li>
	        </ul>
		</div>
		
		
		<table class="tablelist">
			<thead>
				<tr>
					<th>活动主题</th>
					<th>活动时间</th>
					<th>活动地点</th>
					<th>活动简介</th>
					<!-- <th>操作</th> -->
				</tr>
			</thead>
			<tbody id="msg"> </tbody>
		</table>
		<div class="pagin" id="pagin"></div> 
		
		
		
		<!-- <div id="dialog" class="Popup" style="display: none; width: 200;">
			<div class="Popup_top">
				<h2 id="h2">添加活动信息</h2>
				<a href="javascript:void(0);" class="Close" onclick="closeDialog();">关闭</a>
			</div> 
			
			设置表格内容  
			<form action="" name="submitForm" id="submitForm" method="post" >
				<table width="100%">
					<tr align="right"><td  width="50%">活动名称：<input type="text" class="wh"  name="acti_name" id="acti_name"/></td><td  width="50%">活动简介<input type="text" name = "acti_disc" class="wh" id = "acti_disc" /></td></tr>
					<tr align="right"><td  width="50%">活动时间：<input type="text" class="wh" name="acti_time" id="acti_time"/></td><td  width="50%">活动地址<input type="text" class="wh" name="acti_addr" id="acti_addr"></td></tr>
				</table>  
				<div class="tools"></div>
				<table id="data"></table>   	
				<input type="hidden" name="id" id="id" value="" />
				<input type="button" onclick="submitF();" value="保存"/> 
				
			</form>
		</div>  -->
		
		<div id="dialog" class="Popup" style="display: none; width: 200;">
	      <div class="Popup_top">
	        <h2 id="h2">添加活动信息</h2>
	        <a href="javascript:void(0);" class="Close" onclick="closeDialog();">关闭</a>
	      </div> 
	      <form name="edtForm" id="edtForm" action="" method="post">
	        <input type="hidden" name="id" id="id" value="" />
	        <ul class="forminfo" id="ul">
	          <li id="next"><label>活动名称：</label><input name="acti_name" id="acti_name" type="text" class="dfinput" /></li> 
	          <li ><label>活动简介</label><input name="acti_disc" id="acti_disc" type="text" class="dfinput" /></li> 
	          <li ><label>活动时间：</label><input name="acti_time" id="acti_time" type="text" class="dfinput" /></li> 
	          <li ><label>活动地址</label><input name="acti_addr" id="acti_addr" type="text" class="dfinput" /></li>  
	          <li><label>&nbsp;</label><input name="" onclick="submitForm();" type="button" class="btn" value="确认保存" /></li>
	        </ul>
	      </form>
    	</div>
		
		
		<input type="hidden" name="option" id="option" value="" />
</body>
</html>
<script type="text/javascript">

function submitForm() {
      $.ajax({
        type : "POST",
        dataType : "html",
        url : "${pageContext.request.contextPath}/userRisg/addActi.do",
        data : {"actiName" : $("#acti_name").val(),"actiDisc" : $("#acti_disc").val(),"actiTime" : $("#acti_time").val(),"actiAddr" : $("#acti_addr").val()},
        success : function(data) {
          /* var r = $.parseJSON(data); 
          if (r.flag == 'true') { 
            cleanForm();
          } else {
            alert(r.msg);
          } */
        	/* $("#dialog").hide(); */
        	loadTable();  
        }
      })
  }

	var pageNum=0; 
	/* 初始加载内容  */
	function loadTable(){
		var data=getList(); 
		if(data!=null) {
			/* var content=data.; */
			/* var count=data.count */;
			var html='';
			for(var i=0;i<data.length;i++) { 
				 
				
				
				/* html=html+'<tr id="'+data[i].id+'"><td width="12%">'+data[i].actiName+'</td><td width="9%">'+data[i].actiName+'</td><td width="9%">'+data[i].actiAddr+'</td><td width="10%">'+data[i].actiDisc+'</td><td><a href="#" onclick="openDetail(\''+data[i].id+'\');">设置</a></tr>'; */
				html=html+'<tr id="'+data[i].id+'"><td width="12%">'+data[i].actiName+'</td><td width="9%">'+data[i].actiTime+'</td><td width="9%">'+data[i].actiAddr+'</td><td width="10%">'+data[i].actiDisc+'</td></tr>';
			} 
			document.getElementById('msg').innerHTML=html; 
		}
		/* getPage(count,pageNum); */
		getPage(10,pageNum);
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
			type : "GET",
			dataType : "json",
			async: false,
			/* url : "${pageContext.request.contextPath}/userOrder/getUserOrders.do?page="+pageNum, */
			url : "${pageContext.request.contextPath}/userRisg/getAllActi.do",
			/* data :serializeObject($('#searchForm')), */
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
			$("#submitForm").attr("action","${pageContext.request.contextPath}/userRisg/addActi.do");
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
	/* function openDetail(id) {  */
	function openDetail() { 
		$("#data").empty();
		/* getOrderDataById(id); */
		/* getDatas(id);  */
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