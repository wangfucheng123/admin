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
</head>      
   
<body>   
	<div class="rightinfo">
		<div class="tools">
			<ul class="toolbar">
				<form method="post" id="user_searchForm">
					<li><input name="content" id="content" type="text"
						style="width: 290px; height: 28px;" placeholder="请输入内容关键词" /></li>
					<li class="click" onclick="loadTable();"><span><img
							src="" /></span>查询</li>
				</form>   
			</ul>     
		</div>    
		<table class="tablelist" >   
			<thead>   
				<tr>    
					<th>反馈内容</th>   
					<th>用户名</th>   
					<th>提交时间</th>   
					<th>来源</th>   
				</tr>
			</thead>    
			<tbody id="msg">   
			</tbody>      
		</table>
		<div class="pagin" id="pagin"></div>      
</body>        
</html>
<script type="text/javascript">    
	var pageNum = 0;
	function loadTable() {      
		document.getElementById('msg').innerHTML = "";    
		var data = getList();
		var content = data.content;
		var count = data.count;
		getPage(count, pageNum);
		if (content != null) {
			var html = '';
			for (var i = 0; i < content.length; i++) {  
				var source='';
				if(content[i].source=="1"){
					source="终端";
				}if(content[i].source=="2"){
					source="app";
				}
				var name='';      
				if(content[i].userId!=null){
					name=content[i].userId;
				}
				html = html + '<tr><td width="40%"><div style="width: 100%;">' + content[i].content + '</div> </td><td width="20%">'
						+ name + '</td><td width="20%">' +content[i].createDate.replace(".0","")+ '</td><td width="20%">' + source + '</td></tr>'; 
			 
			}
			document.getElementById('msg').innerHTML = html;
		} 
	}
	function getList() {
		var jsons;
		$.ajax({
					type : "POST",
					dataType : "html",
					async : false,
					url : "${pageContext.request.contextPath}/notice/getFeedBacks.do?page=" + pageNum,
					data : {
						content : $("#content").val()
					},
					success : function(data) {
						jsons = $.parseJSON(data);
					}
				})
		return jsons;
	}
	loadTable();
	
</script>
