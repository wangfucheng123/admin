<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%
String id=request.getParameter("id");
if(id==null)
{
	id="";
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
<script type="text/javascript" src="<%=path%>/js/WdatePicker/WdatePicker.js"></script>
</head>

<body>
    <div class="formbody">
    <div class="formtitle"><span>基本信息</span></div>
    <form name="edtForm" id="edtForm" action="" method="post">
    <input type="hidden" name="id" id="id" value="<%=id%>" />
    <ul class="forminfo">
    <li><label>用户名称</label><input name="name" id="name" type="text" class="dfinput" /></li>
    <%
	if(id.equals(""))
	{
	%>
    <%} %>
    <!-- <li><label>用户类型</label><div class="vocation"><select name="type" id="type" class="select1">
    <option value="1">免费用户</option><option value="2">付费用户</option><option value="3">内部员工</option></select></div></li> -->
    <li><label>真实姓名</label><input name="realname" id="realname" type="text" class="dfinput" /></li>
    <li><label>用户id</label><input name="userId" id="userId" type="text" class="dfinput" /></li>
    <li><label>性别</label><div class="vocation"><select name="sex" id="sex" class="select1">
    <option value="0">男</option><option value="1">女</option></select></div></li>
    <li><label>用户手机</label><input name="phone" id="phone" type="text" class="dfinput" /></li>
    <li><label>屏幕数</label><input name="screenNum" id="screenNum" type="text" class="dfinput" /></li>
    <li><label>公司名称</label><input name="company" id="company" type="text" class="dfinput" /></li>
    <li><label>职业类型</label><div class="vocation"><select name="profession" id="profession" class="select1">
    <option value="4">产品经理</option><option value="5">金融研究员</option><option value="6">经纪人</option>
    <option value="7">开发工程师</option><option value="8">职业炒股人</option><option value="9">自由职业者</option>
    </select></div></li>
    <li><label>出生日期</label><input onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="birthday" id="birthday" type="text" class="dfinput" /></li>
    <li><label>EMAIL</label><input name="email" id="email" type="text" class="dfinput" /></li>
    <li><label>&nbsp;</label><input name="" onclick="javascript:save('edtForm','cscUser/saveCscUser')" type="button" class="btn" value="确认保存"/></li>
    <!-- <li><label>&nbsp;</label><input name="" onclick="submitForm()" type="button" class="btn" value="确认保存"/></li> -->
    </ul>
    </form>
    </div>
</body>
</html>
<%
if(id!=null && !id.equals(""))
{
%>
<script type="text/javascript">
$.ajax({
	type:'post',
	url:'<%=path%>/view/cscUser/userById?id=<%=id%>',
	dataType:'json',
	async:false,
	beforeSend:function()
	{},
	error:function(e)
	{
		alert('网络错误！');
	},
	success:function(obj)
	{
		obj=JSON.parse(obj);
		document.getElementById("name").value=returnVal(obj.name);
		document.getElementById("userId").value=returnVal(obj.userId);
		document.getElementById("email").value=returnVal(obj.email);
		document.getElementById("sex").value=returnVal(obj.sex);
		document.getElementById("realname").value=returnVal(obj.realname);
		document.getElementById("phone").value=returnVal(obj.phone);
		document.getElementById("birthday").value=new function(){if(returnVal(obj.birthday)=='') return new String('');else return new String(new Date(returnVal(obj.birthday)).pattern("yyyy-MM-dd"));};
		document.getElementById("profession").value=returnVal(obj.profession);
		document.getElementById("company").value=returnVal(obj.company);
		document.getElementById("type").value=returnVal(obj.type);
		document.getElementById("screenNum").value=returnVal(obj.screenNum);
	}
});

function dateFormat(stringTypeDate){
        var dateType = "";
        var date = new Date(stringTypeDate);
        dateType = date.getFullYear()+"-"+getMonth(date)+"-"+getDay(date);
        return dateType;
    }

function submitForm() {
		if (validate()) {
			$.ajax({
				type : "POST",
				dataType : "html",
				url : "${pageContext.request.contextPath}/cscUser/saveCscUser",
				data : $('#edtForm').serialize(),
				success : function(data) {
					var r = $.parseJSON(data);
					if (r != null && r.flag == 'true') {
						$("#dialog").hide();
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
}%>
<div style=""></div>
<script type="text/javascript">
 $(".select1").uedSelect({
		width : 345			  
	});
</script>
