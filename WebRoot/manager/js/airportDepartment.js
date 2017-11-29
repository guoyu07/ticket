/**
 * @description 初始化系统组织机构方法参数
 * @author 涂有
 * @datetime 2015-10-24 15:27:39
 */
jQuery(function(){
	//渲染组织机构的树，在管理页面中
	if($("#tg").length > 0){
		
		renderTreeGrid();
	}
	
	//自动填充值
	if($("#commonForm").length > 0){
		
		addOrEditSys();
	}
	
	description = createEditor("description");
});

var SYSTEMORG_PATH = "SYSTEMORG_PATH";


/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-10-24 15:27:39
 */
function addOrEditSys() {
	
	//下拉列表自动填充
	var parent_id = $('#parent_id');
	parent_id.combotree('setValue', parent_id.val());
	
	//清空表单
	$('#resetBtn').click(function(){
		
		parent_id.combotree('setValue', '');
	});
}

/**
 * 渲染组织机构的展示的表格树
 */
function renderTreeGrid(){
	
	$('#tg').treegrid(
		{
			fitColumns : true,
			nowrap : false,
			rownumbers : true,
			animate : true,
			width: $("#tg").parent().width(),
			height: "auto",
			collapsible : true,
			loadMsg : '数据加载中请稍后……',
			url : actionName+'_loadTree.action',
			idField : 'id',
			treeField : 'name',
			frozenColumns : [[{
				title : '组织机构名称',
				field : 'name',
				width : '50%'
			}]],
			columns : [[{
				title : '部门编号',
				field : 'departmentId',
				width : '22%'
			},{
				title : '备注',
				field : 'description',
				width : '22%'
			}]],
			onBeforeLoad : function(row, param) {

				// 此处就是异步加载地所在
//				if(row){
//					
//					$(this).treegrid('options').url = actionName+'_loadTree.action?id='+ row.id;
//				}
			},
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
//					if(TuYou.Cookie.get(SYSTEMORG_PATH).indexOf(rows[i].id) >= 0){
//						
//						$(this).treegrid('expand', rows[i].id);
//					}
//				}
			},
			onClickRow : function(row){
				
				saveExpandPath(this, row);
			}
		});
}

function saveExpandPath(tree_, row){
	
	var path = [];
	path.push(row.id);
	while((row = $(tree_).treegrid('getParent', row.id))){
		
		path.push(row.id);
	};
	path.reverse();
	TuYou.Cookie.set(SYSTEMORG_PATH, path.join(','));
	return path.join(',');
}

function toManage(){
	window.location = actionName+'_manage.action?versionFlag=site';
}

function toAdd(){
	var row = $('#tg').treegrid('getSelected');
	if(row){
		
		window.location = actionName+'_add.action?versionFlag=site&id=' + row.id;
	}else{
		
		window.location = actionName+'_add.action?versionFlag=site';
	}
	saveExpandPath(row);
}

function toEdit(){
	var row = $('#tg').treegrid('getSelected');
	if(row){
		
		window.location = actionName+'_edit.action?versionFlag=site&id=' + row.id;
	}else{
		
		JM.alert("请先选择一条数据", 1500);
	}
}
function toDelete(){
	
	var row = $('#tg').treegrid('getSelected');
	
	if(!row){
		
		JM.alert("请先选择一条数据", 1500);
		return;
	}
	
	if(confirm('你确定要删除吗？')){
		
		var url = actionName+'_realDelete.action?versionFlag=site&id=' + row.id;
		$.getJSON(url, function(json){
			
			JM.alert(json.info, 2000);
			if(json.status == 'y'){
				
				$("#tg").treegrid("reload", row._parentId);
			}
		});
	}
}