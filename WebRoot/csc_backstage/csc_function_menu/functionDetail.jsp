<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%
	String id = request.getParameter("id");
	String functionId = request.getParameter("functionId");
	String functionName = request.getParameter("functionName");
	if (id == null) {
		id = "";
	}
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
<link href="<%=path%>/css/select.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=path%>/js/select-ui.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/WdatePicker/WdatePicker.js"></script>
</head>

<body>
	<div class="formbody">
		<div class="formtitle">
			<span>基本信息</span>
		</div>
		
		<%-- 
		<form name="edtForm" id="edtForm" action="" method="post">
			<input type="hidden" name="id" id="id" value="<%=id%>" />
			<ul class="forminfo">
				<li><label>功能名称</label><input name="functionName" id="functionName" type="text"class="dfinput" /></li>
				<li><label>功能id</label><input name="functionId" id="functionId" type="text" class="dfinput" /></li>
				<li><label>&nbsp;</label><input name=""
					onclick="javascript:save('edtForm','cscFunction/saveFunction')"
					type="button" class="btn" value="确认保存" /></li>

			</ul>
		</form> --%>
		
		
		
		
		
		<form name="edtForm" id="edtForm" action="" method="post">
			<input type="hidden" name="id" id="id" value="<%=id%>" />
			<ul class="forminfo">
				<li><label>功能名称</label><input name="functionName" id="functionName" type="text"class="dfinput" /></li>
				<%
					if (id.equals("")) {
				%>

				<%
					}
				%>

				<li><label>功能id</label><input name="functionId" id="functionId" type="text" class="dfinput" /></li>
				<li><label>&nbsp;</label><input name=""
					onclick="javascript:save('edtForm','cscFunction/saveFunction')"
					type="button" class="btn" value="确认保存" /></li>

			</ul>
		</form>
	</div>
</body>
</html>
<%
	if (id != null && !id.equals("")) {
%>
<script type="text/javascript">
<%-- $.ajax({
	type:'post',
	url:'<%=path%>/view/cscFunction/user?id=<%=id%>',
				dataType : 'json',
				async : false,
				beforeSend : function() {
				},
				error : function(e) {
					alert('网络错误！');
				},
				success : function(obj) {
					obj = JSON.parse(obj);
					document.getElementById("name").value = returnVal(obj.name);
					document.getElementById("email").value = returnVal(obj.email);
					document.getElementById("sex").value = returnVal(obj.sex);
					document.getElementById("realname").value = returnVal(obj.realname);
					document.getElementById("phone").value = returnVal(obj.phone);
					document.getElementById("birthday").value = new function() {
						if (returnVal(obj.birthday) == '')
							return new String('');
						else
							return new String(new Date(returnVal(obj.birthday))
									.pattern("yyyy-MM-dd"));
					};
					document.getElementById("profession").value = returnVal(obj.profession);
					document.getElementById("company").value = returnVal(obj.company);
					document.getElementById("type").value = returnVal(obj.type);
					document.getElementById("screenNum").value = returnVal(obj.screenNum);
				}
			}); --%>
function init(){


if()
}
init();
	function dateFormat(stringTypeDate) {
		var dateType = "";
		var date = new Date(stringTypeDate);
		dateType = date.getFullYear() + "-" + getMonth(date) + "-"
				+ getDay(date);
		return dateType;
	}

	function submitForm() {
		if (validate()) {
			$.ajax({
				type : "POST",
				dataType : "html",
				url : "${pageContext.request.contextPath}/cscFunction/saveCscUser",
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
</script>

<%
	}
%>
<div style=""></div>
<script type="text/javascript">
	$(".select1").uedSelect({
		width : 345
	});
</script>
