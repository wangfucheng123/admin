/**
 * 
 */
$(function(){
	forceGuide();
});
function forceGuide(){
var forceMap = echarts.init(document.getElementById('forceGuide'));
forceMap.showLoading();
$.ajax({
    type: "post",
    async: false,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
    url: "http://localhost:8080/radi/v1/kmdocs/entityRelation",
    data: EmotionLineData,
    dataType: "json",        //返回数据形式为json
    contentType: "application/json",
    success: function(result) {
    	var webkitDep = {  
    		    "type": "force",  
    		    "categories": [//关系网类别，可以写多组  
    		    	{
    		    		"name": "实体关系",
    		    	},
    		        {  
    		            "name": "人名",//关系网名称  
    		            /*"keyword": {},  
    		            "base": "网格关系"*/  
    		        },
    		        {  
    		            "name": "地名",//关系网名称  
    		            /*"keyword": {},  
    		            "base": "网格关系"*/  
    		        },
    		        {  
    		            "name": "机构",//关系网名称  
    		            /*"keyword": {},  
    		            "base": "网格关系"*/  
    		        }
    		    ],  
    		    "nodes": [//展示的节点
    		        {  
    		            "name": "关于简版救赎者无畏的注意事项",//节点名称  
    		            "value": 3,  
    		            "category": 0//与关系网类别索引对应，此处只有一个关系网所以这里写0  
    		        },  
    		        {  
    		            "name": "【考前突击】6月份精简时政50题",  
    		            "value": 8,  
    		            "category": 0  
    		        },  
    		        {  
    		            "name": "二级父节点2",  
    		            "value": 10,  
    		            "category": 1  
    		        },  
    		        {  
    		            "name": "子节点1",  
    		            "value": 10,  
    		            "category": 1  
    		        },  
    		        {  
    		            "name": "子节点2",  
    		            "value": 110,  
    		            "category": 1  
    		        },  
    		        {  
    		            "name": "子节点3",  
    		            "value": 100,  
    		            "category": 1  
    		        },  
    		        {  
    		            "name": "子节点4",  
    		            "value": 100000,  
    		            "category": 1 
    		        },
    		        {  
    		            "name": "子节点5",  
    		            "value": 100000,  
    		            "category": 2  
    		        },
    		        {  
    		            "name": "子节点6",  
    		            "value": 1000,  
    		            "category": 3  
    		        }
    		    ],  
    		    "links": [//节点之间连接  
    		        {  
    		            "source": 0,//起始节点，0表示第一个节点  
    		            "target": 0 //目标节点，1表示与索引为1的节点进行连接  
    		        },  
    		        {  
    		            "source": 0,  
    		            "target": 2  
    		        },  
    		        {  
    		            "source": 1,  
    		            "target": 3  
    		        },  
    		        {  
    		            "source": 1,  
    		            "target": 4  
    		        },  
    		        {  
    		            "source": 2,  
    		            "target": 5  
    		        },  
    		        {  
    		            "source": 2,  
    		            "target": 6  
    		        },
    		        {  
    		            "source": 2,  
    		            "target": 7  
    		        },
    		        {  
    		            "source": 3,  
    		            "target": 8  
    		        }
    		    ]  
    		};  
    	forceMap.hideLoading();  
    		    option = {  
    		        legend: {  
    		            data: ['人名','地名','机构']//此处的数据必须和关系网类别中name相对应  
    		        },  
    		        series: [{  
    		            type: 'graph',  
    		            layout: 'force',  
    		            animation: false,  
    		            label: {  
    		                normal: {  
    		                    show:true,  
    		                    position: 'right'  
    		                }  
    		            },  
    		            draggable: true,  
    		            data: webkitDep.nodes.map(function (node, idx) {  
    		                node.id = idx;  
    		                return node;  
    		            }),  
    		            categories: webkitDep.categories,  
    		            force: {  
    		                edgeLength: 105,//连线的长度  
    		                repulsion: 100  //子节点之间的间距  
    		            },  
    		            edges: webkitDep.links  
    		        }]  
    		    };  
    		    forceMap.setOption(option);
    	},
	/*error:function(errorMsg){
		 console.log("图表请求数据失败!");
	}*/
	});
}
	

	