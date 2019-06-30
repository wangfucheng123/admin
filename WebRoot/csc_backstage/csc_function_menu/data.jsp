<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
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

<script type="text/javascript">
	$(document).ready(function() {
		$(".click").click(function() {
			$(".tip").fadeIn(200);
		});

		$(".tiptop a").click(function() {
			$(".tip").fadeOut(200);
		});

		$(".sure").click(function() {
			$(".tip").fadeOut(100);
		});

		$(".cancel").click(function() {
			$(".tip").fadeOut(100);
		});

	});
</script>


</head>

<style>
	html,body,.rightinfo{
		height: 100%;
	}
	.rightinfo{
		flex-direction: column;
	}
	.flex1{
		overflow: auto;
	}
	input[readonly]{
		background: #ccc;
	}
</style>
<body>



	<div class="rightinfo flex">

		<div class="tools">
			<ul class="toolbar">
				<li class="click" onclick="openDetail('');"><span><img
						src="<%=path%>/images/t01.png" /></span>添加</li>
			</ul>

		</div>
		<div class="">
			<table class="tablelist">
				<thead>
					<tr>
						<th>数据ID</th>
						<th>数据名称</th>
						<th>数据源</th>
						<th>数据编码</th>
						<th>功能状态</th>
						<th>创建时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="msg">
				</tbody>
			</table>
		</div>
		<div class="pagin" id="pagin"></div>
		<div id="dialog" class="Popup" style="display: none; width: 200;">
			<div class="Popup_top">
				<h2 id="h2"></h2>
				<a href="javascript:void(0);" class="Close" onclick="closeDialog();">关闭</a>
			</div>

			<form name="edtForm" id="edtForm" action="" method="post">
				<ul class="forminfo" id="ul">
					<!-- <li><label>数据名称</label><input name="dataName" id="dataName" type="text" class="dfinput" /></li>
					<li><label>数据源</label><input name="dataSource" id="dataSource" type="text" class="dfinput" /></li>
					<li><label>数据编码</label><input name="dataCode" id="dataCode" type="text" class="dfinput" /></li>
					<li><label>&nbsp;</label><input name="" onclick="submitForm();" type="button" class="btn" value="确认保存" /></li> -->
				</ul>
			</form>
		</div>
</body>

</html>
<script type="text/javascript">
	var pageNum = 0;
	function loadTable() {
		var data = JSON.parse(getData('cscData/dataList.do?', pageNum));
		if (data != null) {
			var count = data.count;/*页数*/
			data=data.content;
			var html = '';
			for (var i = 0; i < data.length; i++) {
				html += '<tr id='+data[i].dataId+'><td>'
						+ returnVal(data[i].dataId)
						+ '</td><td>'
						+ returnVal(data[i].dataName)
						+ '</td><td>'
						+ returnVal(data[i].dataSource)
						+ '</td><td>'
						+ returnVal(data[i].dataCode)
						+ '</td><td>'
						+ openF(data[i].dataStatus)
						+ '</td><td>'
						+ FormatDate(data[i].ctime)
						+ '</td><td><a onclick="delF('+data[i].dataId+');" href="#">删除</a>&nbsp;<a href="#" onclick="openDetail('
						+ data[i].dataId
						+ ');">修改</a></td></tr>';
			}
			document.getElementById('msg').innerHTML = html;
			getPage(count,pageNum);
		}
	}
	loadTable();
	function openDetail(_id) {/*打开*/
		console.log(_id)
		$('.Popup').show();
		if (_id){
			$('#h2').text('修改数据');
			let _html='<li><label>数据ID</label><input name="dataId" readonly="readonly" id="dataId" type="text" class="dfinput" value='+_id+'   /></li><li><label>数据名称</label><input name="dataName" id="dataName" type="text" class="dfinput" /></li><li><label>数据源</label><input name="dataSource" id="dataSource" type="text" class="dfinput" /></li><li><label>数据编码</label><input name="dataCode" id="dataCode" type="text" class="dfinput" /></li><li><label>&nbsp;</label><input name="" onclick="submitForm(1);" type="button" class="btn" value="确认保存" /></li>'
			$('.forminfo#ul').html(_html);
		}else{
			$('#h2').text('添加数据');
			let _html='<li><label>数据名称</label><input name="dataName" id="dataName" type="text" class="dfinput" /></li><li><label>数据源</label><input name="dataSource" id="dataSource" type="text" class="dfinput" /></li><li><label>数据编码</label><input name="dataCode" id="dataCode" type="text" class="dfinput" /></li><li><label>&nbsp;</label><input name="" onclick="submitForm();" type="button" class="btn" value="确认保存" /></li>'
			$('.forminfo#ul').html(_html);
		}
	}
	function closeDialog() {/*关闭*/
		$('.Popup').hide();
		$('#edtForm input:not(.btn)').val('');
	}

	function delF(_Id){/*删除*/
		if (confirm("你确定要删除么？")) {
			$.ajax({
				type : "POST",
				dataType : "json",
				url : "${pageContext.request.contextPath}/cscData/deleData.do?dataId="+_Id,
				success : function(data) {
					data=JSON.parse(data);
					if (data.ret=='OK') {
						//console.log(data.ret)
						$('#'+_Id).remove();
					}
				}
			})
		}
	}
	function submitForm(num) {/*提交*/
		if (num){/*判断添加修改*/
			var _url='cscData/updateData.do';
		}else{
			var _url='cscData/saveData.do';
		}
		console.log($('#edtForm').serialize())
		$.ajax({
			type : "POST",
			dataType : "json",
			url : "${pageContext.request.contextPath}/"+_url,
			data : $('#edtForm').serialize(),
			success : function(data) {
				//var data=JSON.parse(JSON.stringify(data));
				data=JSON.parse(data);
				if (data.ret=='OK') {
					$('.Popup').hide();
					$('#edtForm input:not(.btn)').val('');
					loadTable();
				} else {
					alert('系统出错！');
				}
			}
		})
	}
</script>