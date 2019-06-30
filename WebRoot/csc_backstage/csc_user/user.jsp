<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
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
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});
</script>
<style>
  .Popup {
    width: 570px;
    height: 680px;
    position: absolute;
    left: 50%;
    top: 50%;
    margin-left: -400px;
    padding: 0 15px;
    margin-top: -300px;
    background-color: #F0F0F0;
    z-index: 999;
}
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
input[readonly]{
  background: #ccc;
}
</style>

</head>


<body>

	
    
    <div class="rightinfo">
    
    <%-- <div class="tools">
    	<ul class="toolbar">
        <li class="click" onclick="openDetail('');" ><span><img src="<%=path%>/images/t01.png" /></span>添加</li>
        </ul>
    </div> --%>
    
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th>真实姓名</th>
        <th>用户密码</th>
        <th>手机号码</th>
        <th>Email</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody id="msg">
        </tbody>
    </table>
    <div class="pagin" id="pagin"></div>
    <div id="dialog" class="Popup" style="display: none; width: 200;">
      <div class="Popup_top">
        <h2 id="h2"></h2>
        <a href="javascript:void(0);" class="Close" onclick="closeDialog();">关闭</a>
      </div> 
      <form name="edtForm" id="edtForm" action="" method="post">
        <input type="hidden" name="id" id="id" value="" />
        <input type="hidden" name="userid" id="userid" value="" />
        <ul class="forminfo" id="ul">
          <li id="next"><label>用户名*</label><input name="name" id="name" type="text" class="dfinput" /></li> 
          <li ><label>真实姓名</label><input name="realname" id="realname" type="text" class="dfinput" /></li> 
          <li ><label>手机号码</label><input name="phone" id="phone" type="text" class="dfinput" /></li> 
          <li ><label>邮箱</label><input name="email" id="email" type="text" class="dfinput" /></li>  
          <li><label>&nbsp;</label><input name="" onclick="submitForm();" type="button" class="btn" value="确认保存" /></li>
        </ul>
      </form>
    </div>
    <div id="dialog1" class="Popup" style="display: none; width: 200;">
      <div class="Popup_top">
        <h2 id="h2"></h2>
        <a href="javascript:void(0);" class="Close" onclick="closeDialog();">关闭</a>
      </div> 
      <form name="edtForm1" id="edtForm1" action="" method="post">
           <ul class="forminfo" id="ul1"></ul>
      </form>
    </div>
    <div id="tip">
      <h1>
        <a href="javascript:void(0)" onclick="start()"></a>提示
      </h1>
      <p id="alt"></p>
    </div>
</body>

</html>
<script type="text/javascript">
var pageNum=0;
function loadTable()
{
	var data=JSON.parse(getData('user/cscUserList.do?',pageNum));
	if(data!=null)
	{
    console.log(data)
		var content=data.content;
		var count=data.count;
		var html='';
		for(var i=0;i<content.length;i++)
		{
			var sex='男';
			if(content[i].sex==1)
			{
				sex='女';
			}
			html=html+'<tr><td>'+returnVal(content[i].realname)+'</td><td>******</td><td>'+returnVal(content[i].phone)+'</td><td>'+returnVal(content[i].email)+
			'</td><td><a href="#" onclick="openDetail('+content[i].id+
			');">修改</a>&nbsp;<a onclick="removeT('+content[i].id+
			');" href="#">删除</a>&nbsp;</tr>';
		}
		document.getElementById('msg').innerHTML=html;
		getPage(count,pageNum);
	}
}
loadTable();

function openDetail(id) {  
    getProvinces(); 
    $("#li").remove();
    if(""==id){ 
      $("#next").after('<li id="li"><label>用户密码*</label><input name="password" id="password" type="password" class="dfinput" /></li>')
      $("#h2").text("添加用户"); 
    }else{
      $("#h2").text("修改用户信息");
      $("#id").val(id); 
      $("#"+id).each(function (){
        var tdArr=$(this).children();
        $("#name").val(tdArr.eq(0).text()); 
        $("#realname").val(tdArr.eq(1).text());
        $("#sex option").each(function (){
          if($(this).text()==tdArr.eq(2).text()){
            $(this).attr("selected",true)
          }
        })
        $("#birthday").val(tdArr.eq(3).text());
        $("#phone").val(tdArr.eq(4).text());
        $("#profession option").each(function (){
          if($(this).text()==tdArr.eq(5).text()){
            $(this).attr("selected",true)
          }
        })
        $("#province option").each(function (){
          if($(this).text()==tdArr.eq(6).text().split("-")[0]){
            $(this).attr("selected",true)
          }
        })
        $("#city option").each(function (){
          if($(this).text()==tdArr.eq(6).text().split("-")[1]){
            $(this).attr("selected",true)
          }
        }) 
        $("#company").val(tdArr.eq(7).text());
        $("#email").val(tdArr.eq(8).text());
        $("#source option").each(function (){
          if($(this).text()==tdArr.eq(10).text()){
            $(this).attr("selected",true)
          }
        })
      });  
    }
    $("#dialog").show();
  }

function getProvinces(){
    $.ajax({
      type : "POST",
      dataType : "html",
      url : "${pageContext.request.contextPath}/user/getprovinceList.do", 
      success : function(data) {
        var r = $.parseJSON(data); 
        var option='<option value="0">选择所在地</option>';
        for(var json=0;json<r.length;json++){
          option=option+'<option value="'+r[json].proid+'" >'+r[json].proname+'</option>';
        } 
        $('#province').append(option); 
      } 
    }) 
  }
  function submitForm() {
    if (validate()) {
      $.ajax({
        type : "POST",
        dataType : "html",
        url : "${pageContext.request.contextPath}/user/saveUser_back.do",
        data : $('#edtForm').serialize(),
        success : function(data) {
          var r = $.parseJSON(data); 
          if (r.flag == 'true') { 
            cleanForm();
          } else {
            alert(r.msg);
          }
        }
      })
    }
  }
  function closeDialog() { 
    cleanForm();
    $("#li").remove();
    $('#dialog1').hide();
  }
  function cleanForm(){
    $("#li").remove();
    $("#dialog").hide();
    $(":text").val("");
    $(":password").val("");
    $('#province').empty(); 
    $('#city').empty();
    loadTable();
  }
  function validate() {
    var flag = true;
    var reg = /^\w+|[\u4e00-\u9fa5]$/;
    var preg = new RegExp(
        "^([A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()+=|{}\':;\',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“'。，、？]){6,16}$");
    if ($("#name").val()==""&&!reg.test($("#name").val())) {
      $("#tip").show();
      $("#alt").text("用户名有误！");
      setTimeout("autoHide()", 2000);
      flag = false;
    }
    if (!preg.test($("#password").val())) {
      $("#tip").show();$("#alt").text("密码格式错误！");
      setTimeout("autoHide()", 2000);
      flag = false;
    } 
    return flag;
  }
  function autoHide() {
    $("#tip").hide();
  }
  function getCities(){
    $.ajax({
      type : "POST",
      dataType : "html",
      url : "${pageContext.request.contextPath}/user/getcityList.do", 
      data:{proid : $('#province').val()},
      success : function(data) {
        var option="";
        var r = $.parseJSON(data); 
        for(var json=0;json<r.length;json++){
          option=option+'<option value="'+r[json].cityid+'">'+r[json].cityname+'</option>';
        } 
        $('#city').empty();
        $('#city').append(option); 
      } 
    }) 
  }
  function removeT(id){
    if(confirm("你确定要删除么？")){
      $.ajax({
        type : "POST",
        dataType : "json",
        url : "${pageContext.request.contextPath}/user/deleUser.do",
        data : {id : id},
        success : function(data) {
          loadTable();
        }
      })
      }
  }
  function openRole(_id,num){/*权限管理*/
    var _html='';var option='';
    $.ajax({
          type : "POST",
          dataType : "json",
          async:false,
          url : "${pageContext.request.contextPath}/cscRole/getRoleList.do", 
          success : function(data) {
            var data = JSON.parse(data); 
            for(var i=0;i<data.length;i++){
              //console.log(data[i])
              option+='<option roleId='+data[i].roleId+'>'+data[i].roleName+'</option>';
            } 
          } 
        })
    if (num==1) {
       $("#dialog1").show().find('h2').text('角色添加');
      _html='<li><label>用户ID</label><input name="userId" id="userId" type="text" class="dfinput" readonly="readonly" value='+_id+' /></li><li><label>角色添加</label><select style="width:200"  id="qxcxSelect" class="dfinput"></select></div></li><li><label>&nbsp;</label><input name="" onclick="submitForm1('+_id+',1);" type="button" class="btn" value="确认保存" /></li>'
      $('#ul1').html(_html);
    }else{
      $("#dialog1").show().find('h2').text('角色修改');
      
      _html='<li><label>用户ID</label><input name="userId" id="userId" type="text" class="dfinput" readonly="readonly" value='+_id+' /></li><li><label>角色分配</label><select style="width:200"  id="qxcxSelect" class="dfinput"></select></div></li><li><label>&nbsp;</label><input name="" onclick="submitForm1('+_id+',2);" type="button" class="btn" value="确认保存" /></li>'
      $('#ul1').html(_html);
      } 
       $('#qxcxSelect').append(option);
  }
  function submitForm1(_id,num){/*权限提交*/
    var _url;
    if (num==1) {
      _url='cscUserRole/saveUserRole.do';
    }else{
      _url='cscUserRole/updateByUserId.do';
    }
    var _data='userId='+_id+'&roleId='+$('#qxcxSelect option:selected').attr('roleId');
    console.log(_data)
    $.ajax({
      type : "POST",
      dataType : "json",
      url : "${pageContext.request.contextPath}/"+_url,
      data : _data,
      success : function(data) {
        console.log(data);
        data=JSON.parse(data);
        if (data.ret=='OK') {
          //alert(data.ret)
          loadTable();
           $('#dialog1').hide();
        } else {
          alert('系统出错！');
        }
      },
      error:function(data){
          alert('');
      }
    })
  }
/*function openDetail(id)
{
	window.open('userDetail.jsp?id='+id);
}
function openAccount(id)
{
	window.open('userAccount.jsp?id='+id);
}
function openRole(id)
{
	window.open('userRole.jsp?id='+id);
}*/
</script>