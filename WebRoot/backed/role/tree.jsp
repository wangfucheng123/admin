<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/common/common.js"></script>

<link rel="stylesheet" href="<%=path%>/css/demo.css" type="text/css">
	<link rel="stylesheet" href="<%=path%>/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="<%=path%>/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.ztree.excheck.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.ztree.exedit.js"></script>

<script type="text/javascript">
var setting = {
			async: {
				enable: true,
				url:"<%=path%>/view/rightTree/getCheckTree?rootId=1",
				autoParam:["id", "name=n", "level=lv"],
				otherParam:{"otherParam":"zTreeAsyncTest"},
				dataFilter: filter
			},
			check: {
				enable: true
			},
			callback: {
				onCheck: onCheck
			}
			
		};
		
		function onCheck(e, treeId, treeNode) {
			
		}	
		

		function filter(treeId, parentNode, childNodes) {
			if (!childNodes) return null;
			for (var i=0, l=childNodes.length; i<l; i++) {
				childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			}
			return childNodes;
		}

		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting);
		});
		

</script>


</head>

<body>

	
    
    
    <ul id="treeDemo" class="ztree"></ul>

</body>

</html>
