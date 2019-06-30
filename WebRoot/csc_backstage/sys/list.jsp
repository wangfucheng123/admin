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
<link rel="stylesheet" href="<%=path%>/css/demo.css" type="text/css">
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/common/common.js"></script>
<script type="text/javascript" src="<%=path%>/js/WdatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    CKEDITOR.replace('content');
	//	
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

a:hover {	text-decoration: none;
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
	<table class="tablelist">
		<thead>
			<tr> 
				<th>用户名</th> 
				<th>真实姓名</th>  
				<th>操作时间</th>       
				<th>操作</th>
			</tr>                 
		</thead>
		<tbody id="msg">                     
		</tbody>
	</table>                                  
	<div class="pagin" id="pagin"></div>
</body>
</html>  
<script type="text/javascript">
	var pageNum=0;                                  
	loadTable();
	function loadTable(){
		document.getElementById('msg').innerHTML="";                  
		var data=getList(); 
		var content=data.content;                 
		var count=data.count; 
		getPage(count,pageNum);                
		if(content!=null) { 
			var html='';                                                          
			for(var i=0;i<content.length;i++) {  
				html=html+'<tr><td>'+content[i].logname+'</td><td>'+content[i].username+'</td><td>'+(content[i].createtime).replace(".0","")+
			'</td><td>'+content[i].msg+
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
			url : "${pageContext.request.contextPath}/notice/logs.do?page="+pageNum,  
			success : function(data) { 
				jsons=$.parseJSON(data);   
			}
		}) 
		return jsons; 
	}   
</script>
