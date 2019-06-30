<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>

<html>
	
	

<% 
String path = request.getContextPath();
String name=request.getParameter("name");
String requestPath=request.getParameter("requestPath");
if(requestPath==null || requestPath.equals(""))
{
	requestPath="upload";
}
%>
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
<body  width="100%" height="100%"  valign="top" style="margin-left:0px;margin-top:0px;">
	<form method="post" enctype="multipart/form-data" name="f1" id="f1">
	<input type="hidden" name="requestPath" id="requestPath" value="<%=requestPath%>">
					<!-- <span>附件标题：<input type="text"></span> -->
					<span id="f">
						<input type="file" name="myFile" id="myFile" size="48">
					</span>
					<input type="button" value="上传" onclick="upload();" class="Button1">
					
			
	</form>
</body>
<script type="text/javascript">

		$('#f1').form
			({
				url: '<%=path%>/upload',
				onSubmit: function(){   
				},
					success: function(msg){ //回调方法
						if(msg.indexOf('fileName=')==-1)
						{
							alert("上传失败！");
						}
						else
						{
							parent.document.getElementById("<%=name%>").value=msg.substring(9,msg.length).replace('\\','\\\\');
							alert('上传成功');
						}
					}
				
				 });

		

		
		
		function upload()
		{
			$('#f1').submit();	
		}


		
		
		



</script>
</html>
