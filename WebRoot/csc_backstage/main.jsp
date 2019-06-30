<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%
	String path = request.getContextPath();
	if (session.getAttribute("admin") == null) {
		response.sendRedirect("/admin/csc_backstage/login.jsp");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css" />
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
	z-index: 9999999;
}

.Close {
	float: right;
	font-family: Arial, Helvetica, sans-serif;
	margin-right: 10px;
	margin-top: 10px;
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
</style>
<script type="text/javascript"> 
function submitForm() {   
	var preg =new RegExp("^([A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()+=|{}\':;\',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“'。，、？]){6,16}$");
	
	if($("#password").val()!=""){ 
		if(!preg.test(document.getElementById('pword').value)){
			$('#erM').text("密码错误（6-16个字符，字母或数字、特殊字符均可）！");
			return;
		}
		if($("#pword").val()!=$("#rpword").val()){
			$("#erM").text("两次填写的密码不同");
			return;
		}
		$.ajax({
            type: "POST",
            dataType: "json",
            url:"<%=path%>/admin/editPass.do",
				data : $('#edtForm').serialize(),
				success : function(data) {
					var r = $.parseJSON(data);
					if (data.flag == 'true') {
						$("input[type==password]").val('');
						$("#dialog").remove();
					}else if (data.flag == 'false') {
						$('#erM').text("原始密码错误");
					} else  {
						$('#erM').text("未知错误");
					}
				}
			})
	
}else{
	$("#erM").text("原始密码不能为空！");
}
}
function closeDialog() { 
	$("#dialog").remove();
}
</script>
<script language="JavaScript" src="<%=path%>/js/jquery.js"></script>
<title>信息管理系统界面</title>
</head>
<frameset rows="88,*" cols="*" frameborder="no" border="0"
	framespacing="0">
	<frame src="top.jsp" name="topFrame" scrolling="no" noresize="noresize"
		id="topFrame" title="topFrame" />
	<frameset cols="187,*" frameborder="no" border="0" framespacing="0">
		<frame src="left.jsp" name="leftFrame" scrolling="no"
			noresize="noresize" id="leftFrame" title="leftFrame" />
		<frame src="" name="rightFrame" id="rightFrame" title="rightFrame" />
	</frameset>
</frameset>
<body>
</html>
