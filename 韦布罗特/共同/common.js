var ip='/admin/';
/*var ip='192.168.245.29';*/
function FormatDate (strTime) {
    var d2 = new Date(strTime);
    return d2.getFullYear()+'-'+((d2.getMonth()<9?'0':'')+(d2.getMonth()+1))+'-'+(d2.getDate()<10?'0':'')+d2.getDate();
}
function getData(url,pageNum)
{
	var ret;
	//console.log(ip+url+'&page='+pageNum)
	$.ajax({
		type:'post',
		url:ip+url+'&page='+pageNum,
		async:false,
		dataType:'json',
		beforeSend:function()
		{
		},
		error:function(e)
		{
			alert('网络错误！！');
		},
		success:function(data)
		{
			ret=data;
		}
	});
	return ret;
}

function getPage(count,pageNum)
{
	var totle=Math.ceil(count/10);
	var html='<div class="message">共<i class="blue">'+totle+'</i>当前显示&nbsp;<i class="blue">'+(pageNum+1)+'&nbsp;</i>页</div>';
	if(totle>1){
		html=html+'<ul class="paginList">';
		html=html+'<li class="paginItem"><a href="#" onclick="javascript:pageNum='+0+';loadTable();">首页</a></li>';
		if(pageNum>0){
			html=html+'<li class="paginItem"><a href="#" style="width:50px" onclick="javascript:pageNum='+(pageNum-1)+';loadTable();">上一页</a></li>';
		} 
		if(pageNum<(totle-1)){
			html=html+'<li class="paginItem"><a href="#" style="width:50px" onclick="javascript:pageNum='+(pageNum+1)+';loadTable();">下一页</a></li>';
		}
		html=html+'<li class="paginItem"><a href="#" onclick="javascript:pageNum='+(totle-1)+';loadTable();">尾页</a></li>';
		 
		html=html+'</ul>';
	} 
	document.getElementById("pagin").innerHTML=html;
}

function comm(url)
{
	$.ajax({
		type:'post',
		url:ip+url,
		dataType:'json',
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		beforeSend:function()
		{},
		error:function(e)
		{
			alert('网络错误！');
		},
		success:function(data)
		{
			alert('OK');
			try
			{
				loadTable();
			}
			catch(e)
			{}
		}
	});
}

function save1(id,url)
{
	$.ajax({
		type:'post',
		url:ip+url,
		data:$('#'+id).serialize(),
		dataType:'json',
		beforeSend:function()
		{},
		error:function(e)
		{
			alert('网络错误！');
		},
		success:function(data)
		{
			alert(JSON.parse(data).ret);
		}
	});
}


function save(id,url)
{
	$.ajax({
		type:'post',
		url:ip+url,
		data:$('#'+id).serialize(),
		dataType:'json',
		beforeSend:function()
		{},
		error:function(e)
		{
			alert('网络错误！！');
		},
		success:function(data)
		{
			alert(JSON.parse(data).ret);
			window.opener.loadTable();
			window.close();
		}
	});
}

String.prototype.replaceAll = function(s1,s2) { 
    return this.replace(new RegExp(s1,"gm"),s2); 
}


Date.prototype.pattern=function(fmt) {         
    var o = {         
    "M+" : this.getMonth()+1, //月份         
    "d+" : this.getDate(), //日         
    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时         
    "H+" : this.getHours(), //小时         
    "m+" : this.getMinutes(), //分         
    "s+" : this.getSeconds(), //秒         
    "q+" : Math.floor((this.getMonth()+3)/3), //季度         
    "S" : this.getMilliseconds() //毫秒         
    };         
    var week = {         
    "0" : "/u65e5",         
    "1" : "/u4e00",         
    "2" : "/u4e8c",         
    "3" : "/u4e09",         
    "4" : "/u56db",         
    "5" : "/u4e94",         
    "6" : "/u516d"        
    };         
    if(/(y+)/.test(fmt)){         
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));         
    }         
    if(/(E+)/.test(fmt)){         
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);         
    }         
    for(var k in o){         
        if(new RegExp("("+ k +")").test(fmt)){         
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));         
        }         
    }         
    return fmt;         
}

function returnVal(obj)
{
	if(typeof(obj)!="undefined")
	{
		return obj;
	}
	else
	{
		return '';
	}
}

/*功能状态*/
function openF(name){
	return name?'打开':'关闭'
}

	function checkPhone(v) {
		if (v.value != "" && (!/^1[34578]\d{9}$/.test(v.value))) {
			alert("手机格式不对");
		}
	}
	function checkEmail(v) {
		if (v.value != ""
				&& (!/^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/
						.test(v.value))) {
			alert("邮箱格式不对");
		}
	}
