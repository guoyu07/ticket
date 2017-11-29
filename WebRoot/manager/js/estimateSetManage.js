/**
 * @descript 评价设置专用JS
 * @author   HiSay
 * @datetime 2016-01-26 10:56:19
 */
 /*************************************************/

/**
 * @description 初始化评价设置方法参数
 * @author HiSay
 * @datetime 2016-01-26 10:56:19
 */
jQuery(function(){
//	hotEntity();
//	searchCity();
	//渲染字典的树，在管理页面中
	if($("#es").length > 0){
		
		renderTreeGrid();
	}
	
	//自动填充值
	if($("#commonForm").length > 0){
		
		addOrEditSysDictionary();
	}
	
	description = createEditor("description");
});

//var SYSTEMDICTIONARY_PATH = "SYSTEMDICTIONARY_PATH";

function ifEnd(){
	var row = $('#es').treegrid('getSelected');
	$.ajax({
		url:actionName + "_isEnd.action",
		type:"post",
		dataType:"json",
		data:{"id":row.id},
		success:function(data){
			if(data.status == 'y'){
				$("#adds").attr("href","javascript:void(0);");
			}
		}
	});
}
/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-10-24 15:27:39
 */
function addOrEditSysDictionary() {
	
	//下拉列表自动填充
	var parent_id = $('#parent_id');
	parent_id.combotree('setValue', parent_id.val());
	
	//清空表单
	$('#resetBtn').click(function(){
		
		parent_id.combotree('setValue', '');
	});
}



/**
 * 渲染字典的展示的表格树
 */
function renderTreeGrid(){
	
	$('#es').treegrid(
		{
			fitColumns : true,
			nowrap : false,
			rownumbers : true,
			animate : true,
			width: $("#es").parent().width(),
			height: "auto",
			collapsible : true,
			loadMsg : '数据加载中请稍后……',
			url : actionName + '_loadTree.action',
			idField : 'id',
			treeField : 'name',
			frozenColumns : [[{
				title : '评价类别/项目/指标',
				field : 'name',
				width : '50%'
			}]],
			columns : [[{
				field : 'enabled',
				title : '禁用状态',
				width : '45%'
			}]],
			onLoadSuccess : function(row, data){
				
				//自动打开先前的路径
//				var rows;
//				if(data instanceof Array){
//					
//					rows = data;
//				}else{
//					
//					rows = data.rows;
//				}
//				for(var i = 0; i < rows.length; i++){
//					
//					if(TuYou.Cookie.get(SYSTEMDICTIONARY_PATH).indexOf(rows[i].id) >= 0){
//						
//						$(this).treegrid('expand', rows[i].id);
//					}
//				}
			},
			onClickRow : function(row){
				
//				saveExpandPath(this, row);
			},
			onClickRow : function(){
				var row = $('#es').treegrid('getSelected');
				$.ajax({
					url:actionName + "_isEnd.action",
					type:"post",
					dataType:"json",
					data:{"id":row.id},
					success:function(data){
						if(data.status == 'y'){
							$("#adds").attr("href","javascript:void(0);");
						}else{
							$("#adds").attr("href","javascript:toAddDictionary()");
						}
					}
				});
			},
		});
}

//function saveExpandPath(tree_, row){
//	
//	var path = [];
//	path.push(row.id);
//	while((row = $(tree_).treegrid('getParent', row.id))){
//		
//		path.push(row.id);
//	};
//	path.reverse();
//	TuYou.Cookie.set(SYSTEMDICTIONARY_PATH, path.join(','));
//	return path.join(',');
//}

function toManageDictionary(){
	window.location = actionName + '_manage.action?versionFlag=site';
}

function toAddDictionary(){
	var row = $('#es').treegrid('getSelected');
	if(row){
		
		window.location = actionName + '_add.action?versionFlag=site&id=' + row.id;
	}else{
		
		window.location = actionName + '_add.action?versionFlag=site';
	}
//	saveExpandPath(row);
}

function toEditDictionary(){
	var row = $('#es').treegrid('getSelected');
	if(row){
		
		window.location = actionName + '_edit.action?versionFlag=site&id=' + row.id;
	}else{
		
		JM.alert("请先选择一条数据", 1500);
	}
}
function toDeleteDictionary(){
	
	var row = $('#es').treegrid('getSelected');
	
	if(!row){
		
		JM.alert("请先选择一条数据", 1500);
		return;
	}
	
	if(confirm('你确定要删除吗？')){
		
		var url = actionName + '_realDelete.action?versionFlag=site&id=' + row.id;
		$.getJSON(url, function(json){
			
			JM.alert(json.info, 2000);
			if(json.status == 'y'){
				
				$("#es").treegrid("reload", row._parentId);
			}
		});
	}
}
