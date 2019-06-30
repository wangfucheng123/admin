<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
#wrap {
	width: 950px;
	height: 100%;
	margin: 0 auto;
}

.sidebar {
	margin-left: 20px;
	float: left;
	width: 220px; 
	height: 100%;
}

.primary {
	margin-left: 270px; 
	height: 500px;
}
.hover {
	background: #F0F0F0;
}
.hoverC {
	background: #E0E0E0;
}
</style>
<script src="../../echarts/echarts.min.js"></script>
<script src="../../echarts/dateUtil.js"></script>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../js/WdatePicker/WdatePicker.js"></script>
<title></title>
</head>
<body> <div id="wrap">
	<div class="sidebar"><input type="radio" name="type" checked="true" value="r">日
	<input type="radio" name="type" value="y">月
	<input type="radio" name="type" value="n">年<br><br>
	<input type="hidden" id="code"/>
	<table id="data"> 
			<thead>
				<tr>
					<th align="left">数据</th> 
				</tr> 
			</thead>
			<tbody id="adata"><tr onclick="reloadData(this);" id=""><td>研究报告下载量</td></tr>
				<tr id="ID_Web_SZ_News" onclick="reloadData(this);"><td>公告与解析下载量</td></tr>
				<tr id="JRSJ_DC" onclick="reloadData(this);"><td>金融大数据-数据导出量</td></tr>
				<tr id="HGHY_DC" onclick="reloadData(this);"><td>宏观经济与行业-数据导出量</td></tr>
				<tr id="GPLLQ_DC" onclick="reloadData(this);"><td>股票市场概览-数据导出量</td></tr>
				<tr id="JJLLQ_DC" onclick="reloadData(this);"><td>基金市场概览-数据导出量</td></tr>
				<tr id="ZQLLQ_DC" onclick="reloadData(this);"><td>债券市场概览-数据导出量</td></tr>
				<tr id="QHLLQ_DC" onclick="reloadData(this);"><td>期货市场概览-数据导出量</td></tr></tbody> 
		</table></div>
	<div id="center" class="primary" style="width: 990px; height: 500px;"></div> </div>
</body>
<script type="text/javascript"> 
	$("input[type=radio]").change(function() {
		rendererBar();
	})
	var title="";
	$("#adata tr").hover(function (){
		$(this).addClass("hoverC");
	},function (){
		$(this).removeClass("hoverC");
	});
	$("#code").val($("#adata first").id);
	$("#adata tr:first").addClass("hover").siblings().removeClass("hover");
	title=$("#adata tr:first").html().replace("<td>","").replace("</td>","");
	rendererBar();
	function reloadData(code){
		$(code).addClass("hover").siblings().removeClass("hover");
		$("#code").val(code.id);
		title=$(code).html().replace("<td>","").replace("</td>","");
		rendererBar();
	} 
	function rendererBar() {
		var datas = getDatas();
		var xAxis = []; 
		var pcsArr = [];   
		var pcArr = [];
		if (datas.pclist != null) {
			var pclist = datas.pclist;
			for (var i = 0; i < pclist.length; i++) { 
				xAxis.push(pclist[i].cDate);
				pcsArr.push(pclist[i].count);
			}
		} 
		if (datas.pcpercents != null) {
			var pcpercents = datas.pcpercents.replace("[", "").replace("]", "").split(",");
			for (var i = 0; i < pcpercents.length; i++) {
				pcArr.push(pcpercents[i]);
			}
		}
		var lmax=Array.max(pcsArr); 
		var maxp=Array.max(pcArr);
		var minr; 
		var intervalr=((maxp-Math.min.apply(null,pcArr))/5); 
		if((Math.min.apply(null,pcArr)+"").indexOf("-")>=0){
			minr='dataMin';intervalr=((maxp-Math.min.apply(null,pcArr))/5);
		}else{
			minr=0;intervalr=maxp/5;
		}
		var myChart = echarts.init(document.getElementById('center')); 
		// 指定图表的配置项和数据  
		option = {
			title : {
				text : title
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
				data : [ '下载次数', '环比增长']
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
				min : 0 ,max :lmax,interval:(lmax/5)
			}, {
				type : 'value',
				scale : true,
				name : '%',min : minr ,max :maxp,interval:intervalr,
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
				name : '下载次数',
				type : 'bar',
				data : pcsArr
			} , {
				name : '环比增长',
				type : 'line',
				yAxisIndex : 1,
				data : pcArr
			}  ]
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
			url : "${pageContext.request.contextPath}/statistics/data_downlaod_statist.do?type=" + $("input[type=radio]:checked").val()+"&pid="+$("#code").val(),
			success : function(data) {
				jsons = data;
			}
		})		
		return jsons;
	}
</script>
</html>