<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="../../echarts/echarts.min.js"></script>
<script src="../../echarts/dateUtil.js"></script>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../js/WdatePicker/WdatePicker.js"></script>
<title></title>
</head>
<body>
	<input type="radio" name="type" checked="true" value="r">日
	<input type="radio" name="type" value="y">月
	<input type="radio" name="type" value="n">年<br><br>
	<div id="main"  style="width: 990px; height: 500px;"></div>
</body>
<script type="text/javascript"> 
	$("input[type=radio]").change(function (){
		rendererBar();
	})
	rendererBar();
	function rendererBar() {
		var datas= getDatas(); 
		var xAxis = [];
		var webDatas = []; 
		var appDatas = [];
		var machineDatas = [];
		var ps = []; 
		var total=[];
		if(datas.weblist!=null){
			var weblist=datas.weblist;
			var machinelist=datas.machinelist; 
			var applist=datas.applist; 
			for (var i = 0; i < weblist.length; i++) {
				webDatas.push(weblist[i].title);
				xAxis.push(weblist[i].regisday);
				total.push(parseInt(weblist[i].title)+parseInt(applist[i].title)+parseInt(machinelist[i].title))
			} 
		} 
		var lmax=Array.max(total); 
		if(datas.applist!=null){
			var applist=datas.applist; 
			for(var i=0; i<applist.length; i++){
				appDatas.push(applist[i].title);
			} 
		}
		if(datas.machinelist!=null){
			var machinelist=datas.machinelist; 
			for(var i=0; i<machinelist.length; i++){
				machineDatas.push(machinelist[i].title);
			} 
		}	
		if(datas.percents!=null){
			var percents=datas.percents.replace("[","").replace("]","").split(",");  
			for(var i=0; i<percents.length; i++){
				ps.push(percents[i]);
			}
		}  
		var maxp=Array.max(ps);
		var minr; 
		var intervalr=((maxp-Math.min.apply(null,ps))/5); 
		if((Math.min.apply(null,ps)+"").indexOf("-")>=0){
			minr='dataMin';intervalr=((maxp-Math.min.apply(null,ps))/5);
		}else{
			minr=0;intervalr=maxp/5;
		}
		var myChart = echarts.init(document.getElementById('main'));
		// 指定图表的配置项和数据  
		option = {
			title : {
				text : '数金用户统计' 
			},
			tooltip : {
				trigger : 'axis',
				axisPointer : {
					type : 'cross',
					crossStyle : {
						color : '#999'
					}
				}
			},
			legend : { 
				top : 'auto',
				itemGap: 20,
				data : [ '数金终端注册数', '数享APP注册数', '后台创建用户数' ,'合计用户数环比增长']
			}, 
			xAxis : [ {
				data : xAxis,
				splitLine : {
					show : false
				}
			} ],
			yAxis : [ {
				type : 'value',
				name : '次',
				min : 0 ,max :lmax,interval:(lmax/5)
			}, {
				type : 'value',
				scale : true,
				name : '%',min : minr ,max :maxp,interval:intervalr,
				boundaryGap : [ 0.2, 0.2 ]
			} ],
			dataZoom : [ {
				show : true,
				start : 20,
				end : 100
			}, {
				type : 'inside',
				start : 50,
				end : 100
			}],
			series : [ {
				name : '数金终端注册数',
				type : 'bar',
				stack : '用户统计',
				data : machineDatas
			}, {
				name : '数享APP注册数',
				type : 'bar',
				stack : '用户统计',
				data : appDatas
			}, {
				name : '后台创建用户数',
				type : 'bar',
				stack : '用户统计',
				data : webDatas
			}, {
				name : '合计用户数环比增长',
				type : 'line',
				yAxisIndex : 1,
				data :ps
			} ]
		};
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
	}
	function getDatas(){
		var jsons;
		$.ajax({
			type : "POST",
			dataType : "json",
			async : false,
			url : "${pageContext.request.contextPath}/statistics/user_statist.do?type="+$("input[type=radio]:checked").val(),
			success : function(data) { 
				jsons = data;
			}
		})
		return jsons;
	}
</script>
</html>