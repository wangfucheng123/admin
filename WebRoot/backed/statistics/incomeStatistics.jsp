<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">  
<style type="text/css">
.hover{
	background-color: #F0F0F0;
}
.hoverC {
	background: #E0E0E0;
}
</style>
<script src="../../echarts/echarts.min.js"></script>
<script src="../../echarts/dateUtil.js"></script>
<script type="text/javascript" src="../../js/jquery.js"></script> 
<script type="text/javascript" src="../../js/WdatePicker/WdatePicker.js"></script>
<body>
	<div id="channel"> 
		<input type="radio" name="type" checked="true" value="r" onchange="reloadData();">日<input type="radio" name="type" value="y" onchange="reloadData();">月<input type="radio"  onchange="reloadData();" name="type" value="n" >年
		<input name="time"  id="time" type="text" class="Wdate" onfocus="setDate();" onchange="changeValue();" />
		<br><br>
		<table id="data"> 
			<thead>
				<tr>
					<th align="left">频道名</th>
					<th align="left">金额(元)</th> 
				</tr>
			</thead>
			<tbody id="adata"></tbody> 
		</table>
	</div> <br><br>
		<div id="main"  style="width: 990px; height: 500px;"></div><br><br>
		<div id="pie"  style="width: 990px; height: 500px;"></div> 
		<input name="treeId"  id="treeId" type="hidden"/>
</body>
<script type="text/javascript"> 
	$('#time').val((new Date).format('yyyy-MM-dd'));
	$('#treeId').val("all"); 
    changeValue();
    var title="";
	function changeValue(){ 
	    var pieDatas=getPieData();   
		var data="";
		$('#adata').empty();
		var sum=0;
		if(pieDatas!=null){
			for (var i = 0; i < pieDatas.length; i++) {
				data=data+"<tr id=\""+pieDatas[i].id+"\" onclick=\"setValue("+pieDatas[i].id+")\"><td width='200px'>"+pieDatas[i].name+"</td>&nbsp;&nbsp;&nbsp;&nbsp;<td>"+pieDatas[i].value+"</td></tr>"; 
				sum=sum+parseInt(pieDatas[i].value);
			}
			data=data+"<tr id=\"all\" onclick=\"setValue('all')\"><td>"+"总计"+"</td><td>"+sum+"</td></tr>";
			$('#treeId').val(pieDatas[0].id); 
			title=pieDatas[0].name;
			$('#adata').append(data);$("#"+pieDatas[0].id).addClass("hover").siblings().removeClass("hover");
		}  
		rendererPie();
	    rendererBar();
	} 
	$("#adata tr").hover(function (){
		$(this).addClass("hoverC");
	},function (){
		$(this).removeClass("hoverC");
	});
	function formatPat(type){
		var format="";
		if(type=='n'){
			format='yyyy'
		}else if(type=='y'){
			format='yyyy-MM'
		}else if(type=='r'){
			format='yyyy-MM-dd'
		}
		return format;
	}
	function reloadData(){
		var type=$("input[type=radio]:checked").val(); 
		if(type=='n'){
			$('#time').val((new Date).format('yyyy-MM-dd'));
		}
		$("#time").val((new Date()).format(formatPat(type)));
		changeValue();
	}
	function setDate(){
		var type=$("input[type=radio]:checked").val(); 
		var format=formatPat(type); 
		WdatePicker({ dateFmt: format, isShowToday: false, isShowClear: false });   
	}
	function setValue(val){ 
		$("#"+val).addClass("hover").siblings().removeClass("hover"); 
		$('#treeId').val(val);
		title=$("#"+val).find("td:first").html();
		rendererBar();
	} 
	function rendererBar(){
		var myChart = echarts.init(document.getElementById('main'));
		var datas = getDatas();
		var prices = datas.prices; 
		prices = $.parseJSON(prices);
		var percents = datas.percents;
		percents = $.parseJSON(percents);
		var dates = datas.dates;
		if(dates!=null){
			var max;
			var ds = dates.split(",");
			var xAxisData = [];
			var xAxisDate = [];
			for (var i = 0; i < prices.length; i++) {
				xAxisData.push(prices[i]);
				xAxisDate.push(ds[i]);
			} 
			max=Array.max(xAxisData); 
			maxp=Array.max(percents); 
			var intervall=((max)/5);
			var intervalr=((maxp-Math.min.apply(null,percents))/5); 
			var minr;
			if((Math.min.apply(null,percents)+"").indexOf("-")>=0){
				minr='dataMin';intervalr=((maxp-Math.min.apply(null,percents))/5);
			}else{
				minr=0;intervalr=maxp/5;
			}
			// 指定图表的配置项和数据  
			option = {
					title : {
						text : title+'收益趋势图'
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
				legend : { 	top : 'auto',
					itemGap: 20,
					data : [ '收益金额', '环比增长' ]
				},
				xAxis : [ {
					data : xAxisDate,
					splitLine : {
						show : false
					}
				} ], 
				yAxis : [ {
					type : 'value',
					name : '元',scale: true,min:0,interval : intervall,
					max: max
				},
		        {
		            type: 'value',
		            scale: true,
		            name: '%',
		            boundaryGap: [0.2, 0.2] ,min:minr,max: maxp,interval : intervalr
		        } ],
				dataZoom : [ {
					show : true,
					start : 20,
					end : 100
				}, {
					type : 'inside',
					start : 94,
					end : 100
				}],
				series : [{
							name : '收益金额',
							type : 'bar',
							data : xAxisData
						},
						{
					        yAxisIndex: 1,
							name : '环比增长',
							type : 'line', 
							data : percents
						}
			 ]
			}; 
			// 使用刚指定的配置项和数据显示图表。
			myChart.setOption(option);
		}
	}
	function getDatas() {
		var jsons;
		$.ajax({
			type : "POST",
			dataType : "json",
			async : false,
			url : "${pageContext.request.contextPath}/statistics/getDatas.do?treeId="+$("#treeId").val()+"&type="+$("input[type=radio]:checked").val(),
			success : function(data) { 
				jsons = data;
			}
		})
		return jsons;
	}  
	function rendererPie(){
		var pieChart = echarts.init(document.getElementById('pie'));
		var pieDatas=getPieData();  
		if(pieDatas!=null){
		var legends = [];
		for (var i = 0; i < pieDatas.length; i++) {
			legends.push(pieDatas[i].name); 
		}
		pieoption = {
				title : {
					text : '数金频道收益饼图', 
					x : 'center'
				},
				tooltip : {
					trigger : 'item',
					formatter : "{a} <br/>{b} : {c} ({d}%)"
				},
				legend : {
					orient : 'vertical',
					left : 'left',
					data :legends
				},
				series : [ {
					name : '频道名称',
					type : 'pie',
					radius : '55%',
					center : [ '50%', '60%' ],
					data :pieDatas,itemStyle : {
						emphasis : {
							shadowBlur : 10,
							shadowOffsetX : 0,
							shadowColor : 'rgba(0, 0, 0, 0.5)'
						}
					}
				} ]
			};
		pieChart.setOption(pieoption);}
	}
	function getPieData() {
		var jsons;
		$.ajax({
			type : "POST",
			dataType : "json", async : false,
			url : "${pageContext.request.contextPath}/statistics/getPieData.do?type="+$("#time").val(),
			success : function(data) {   
				jsons =$.parseJSON(data); 
			}
		})
		return jsons;
	} 
</script>
</html>