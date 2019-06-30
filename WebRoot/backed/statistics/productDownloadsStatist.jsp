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
	<div id="center" style="width: 990px; height: 500px;"></div> 
</body>
<script type="text/javascript">
	$("input[type=radio]").change(function() {
		rendererBar();
	})
	rendererBar();
	function rendererBar() {
		var datas = getDatas();
		var xAxis = [];
		var appsArr = [];
		var pcsArr = [];  
		var appArr = [];
		var pcArr = [];
		
		if (datas.pclist != null) {
			var pclist = datas.pclist;
			for (var i = 0; i < pclist.length; i++) { 
				xAxis.push(pclist[i].cDate);
				pcsArr.push(pclist[i].count);
			}
		}
		if (datas.applist != null) {
			var applist = datas.applist;
			for (var i = 0; i < applist.length; i++) {
				appsArr.push(applist[i].count);
			}
		}var max=Array.max(appsArr);
		if(Array.max(pcsArr)>=Array.max(appsArr)){
			max=Array.max(pcsArr);
		}
		if (datas.pcpercents != null) {
			var pcpercents = datas.pcpercents.replace("[", "").replace("]", "").split(",");
			for (var i = 0; i < pcpercents.length; i++) {
				pcArr.push(pcpercents[i]);
			}
		}
		if (datas.apppercents != null) {
			var apppercents = datas.apppercents.replace("[", "").replace("]", "").split(",");
			for (var i = 0; i < apppercents.length; i++) {
				appArr.push(apppercents[i]);
			}
		}
		var rmax=Array.max(apppercents);
		if(Array.max(pcpercents)>=Array.max(apppercents)){
			rmax=Array.max(pcpercents);
		}
		var minr=Math.min.apply(null,apppercents); 
		if(Math.min.apply(null,apppercents)>Math.min.apply(null,pcpercents)){
			minr=Math.min.apply(null,pcpercents);
		}
		if(!minr<0){
			minr=0;
		}
		var intervalr=((rmax-minr)/5); 
	 
		var myChart = echarts.init(document.getElementById('center')); 
		// 指定图表的配置项和数据  
		option = {
			title : {
				text : '产品下载量统计' 
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
				data : [ '数金终端下载量', '数金终端下载量环比增长','数享APP下载量','数享APP下载量环比增长' ]
			},

			xAxis : [ {
				type: 'category', 
				data : xAxis,
				splitLine : {
					show : false
				}
			} ],
			yAxis : [ {
				type : 'value',
				name : '次',
				min : 0 ,max :max,interval:(max/5)
			}, {
				type : 'value',
				scale : true,
				name : '%',min : minr ,max :rmax,interval:intervalr,
				boundaryGap : [ 0.2, 0.2 ] 
			} ],
			dataZoom : [ {
				show : true,
				start : 80,
				end : 100
			}, {
				type : 'inside',
				start : 50,
				end : 100
			}  ],
			series : [ {
				name : '数金终端下载量',
				type : 'bar',
				data : pcsArr
			},{
				name : '数享APP下载量',
				type : 'bar',
				data : appsArr
			}, {
				name : '数金终端下载量环比增长',
				type : 'line',
				yAxisIndex : 1,
				data : pcArr
			}, {
				name : '数享APP下载量环比增长',
				type : 'line',
				yAxisIndex : 1,
				data : appArr
			} ]
		}; 
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option); 
	}
	function getDatas() {
		var jsons;
		$.ajax({
					type : "POST",
					dataType : "json",
					async : false,
					url : "${pageContext.request.contextPath}/statistics/downlaod_statist.do?type=" + $("input[type=radio]:checked").val(),
					success : function(data) {
						jsons = data;
					}
				})
		return jsons;
	}
</script>
</html>