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
	<input type="radio" name="type" value="n">年
	<br/><br/>
	<div id="top" style="width: 990px; height: 500px;"></div><br/><br/>
	<div id="bottom" style="width: 990px; height: 500px;"></div>
</body>
<script type="text/javascript">
	$("input[type=radio]").change(function() {
		rendererBar();
	})
	rendererBar();
	function rendererBar() {
		var datas = getDatas();
		var xAxis = [];
		var avgsArr = [];
		var totalsArr = [];
		var machineDatas = [];
		var avgArr = [];
		var totalArr = [];
		if (datas.totalList != null) {
			var totalList = datas.totalList;
			for (var i = 0; i < totalList.length; i++) { 
				xAxis.push(totalList[i].cDate);
				totalsArr.push(totalList[i].count);
			}
		}
		if (datas.avgList != null) {
			var avgList = datas.avgList;
			for (var i = 0; i < avgList.length; i++) {
				avgsArr.push(avgList[i].count);
			}
		}
		if (datas.totals != null) {
			var totals = datas.totals.replace("[", "").replace("]", "").split(
					",");
			for (var i = 0; i < totals.length; i++) {
				totalArr.push(totals[i]);
			}
		}
		if (datas.avgs != null) {
			var avgs = datas.avgs.replace("[", "").replace("]", "").split(",");
			for (var i = 0; i < avgs.length; i++) {
				avgArr.push(avgs[i]);
			}
		}
		max=Array.max(totalsArr); 
		maxp=Array.max(totalArr); 
		var intervall=((max)/5);
		var intervalr=((maxp-Math.min.apply(null,totalArr))/5); 
		var minr;
		if((Math.min.apply(null,totalArr)+"").indexOf("-")>=0){
			minr='dataMin';intervalr=((maxp-Math.min.apply(null,totalArr))/5);
		}else{
			minr=0;intervalr=maxp/5;
		}
		var myChart = echarts.init(document.getElementById('top'));
		var myChart2 = echarts.init(document.getElementById('bottom'));
		// 指定图表的配置项和数据  
		option = { 
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
				itemGap: 40,
				data : [ '自定义页面累计数量', '环比增长' ]
			},

			xAxis : [ {
				data : xAxis,
				splitLine : {
					show : false
				}
			} ],
			yAxis : [ {
				type : 'value',
				name : '个',
				max: max,interval : intervall,min:0
			}, {
				type : 'value',
				scale : true,min:minr,max: maxp,interval : intervalr,
				name : '%',
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
			series : [ 
				{
				name : '自定义页面累计数量',
				type : 'bar',
				data : totalsArr
			}, {
				name : '环比增长',
				type : 'line',
				yAxisIndex : 1,
				data : totalArr
			} ]
		};
		option2 = {
				 
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
					data : [ '人均自定义页面数量' ]
				}, 
				xAxis : [ {
					data : xAxis,
					splitLine : {
						show : false
					}
				} ],
				yAxis : [ {
					type : 'value',
					name : '个',
					min : 0 
				}],
				dataZoom : [ {
					show : true,
					start : 20,
					end : 100
				}, {
					type : 'inside',
					start : 50,
					end : 100
				} ],
				series : [ {
					name : '人均自定义页面数量',
					type : 'bar',
					data : avgsArr
				}]
			};
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
		myChart2.setOption(option2);
	}
	function getDatas() {
		var jsons;
		$
				.ajax({
					type : "POST",
					dataType : "json",
					async : false,
					url : "${pageContext.request.contextPath}/statistics/page_statist.do?type="
							+ $("input[type=radio]:checked").val(),
					success : function(data) {
						jsons = data;
					}
				})
		return jsons;
	}
</script>
</html>