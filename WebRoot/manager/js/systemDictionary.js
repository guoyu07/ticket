/**
 * @description 初始化系统字典表方法参数
 * @author 涂有
 * @datetime 2015-10-24 15:27:39
 */
jQuery(function(){
	hotEntity();
	searchCity();
	//渲染字典的树，在管理页面中
	if($("#tg").length > 0){
		
		renderTreeGrid();
	}
	
	//自动填充值
	if($("#commonForm").length > 0){
		
		addOrEditSysDictionary();
	}
	
	description = createEditor("description");
});

var SYSTEMDICTIONARY_PATH = "SYSTEMDICTIONARY_PATH";


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
				title : '字典名称',
				field : 'name',
				width : '50%'
			}]],
			columns : [[{
				field : 'value',
				title : '字典值',
				width : '45%'
			}]],
			onBeforeLoad : function(row, param) {

				// 此处就是异步加载地所在
				if(row){
					
					$(this).treegrid('options').url = actionName+'_loadTree.action?id='+ row.id;
				}
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
//					if(TuYou.Cookie.get(SYSTEMDICTIONARY_PATH).indexOf(rows[i].id) >= 0){
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
	TuYou.Cookie.set(SYSTEMDICTIONARY_PATH, path.join(','));
	return path.join(',');
}

function toManageDictionary(){
	window.location = actionName+'_manage.action?versionFlag=site';
}

function toAddDictionary(){
	var row = $('#tg').treegrid('getSelected');
	if(row){
		
		window.location = actionName+'_add.action?versionFlag=site&id=' + row.id;
	}else{
		
		window.location = actionName+'_add.action?versionFlag=site';
	}
	saveExpandPath(row);
}

function toEditDictionary(){
	var row = $('#tg').treegrid('getSelected');
	if(row){
		
		window.location = actionName+'_edit.action?versionFlag=site&id=' + row.id;
	}else{
		
		JM.alert("请先选择一条数据", 1500);
	}
}
function toDeleteDictionary(){
	
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

function hotEntity(){
	$(".hotDomEntity").bind("click",function(){
		var id = $(this).attr("value");
		var statusValue = $(this).attr("statusValue");
		var content = $(this).text().trim();
		if(content=='设为热门'){
			$(this).html("取消热门");
		}else{
			$(this).html("设为热门");
		}
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/systemDictionary_setDomHot.action',
			data:'id='+id+"&statusValue="+statusValue,
			success:function(){
				JM.alert("设置成功！");
			}
		});
	});
	$(".hotIntEntity").bind("click",function(){
		var id = $(this).attr("value");
		var statusValue = $(this).attr("statusValue");
		var content = $(this).text().trim();
		if(content=='设为热门'){
			$(this).html("取消热门");
		}else{
			$(this).html("设为热门");
		}
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/systemDictionary_setIntHot.action',
			data:'id='+id+"&statusValue="+statusValue,
			success:function(){
			JM.alert("设置成功！");
		}
		});
	});
}

function searchCity(){
	$("#searchDomBtn").bind("click",function(){
		var keyword = $("#keyword");
		if(JM.isNullByJQuery(keyword)||keyword.val()=="请输入城市关键词"){
			JM.alert('请输入城市关键词~');
			keyword.focus();
			return false;
		} else {
			window.location.href = '/systemDictionary_searchDom.action?keyword='+JM.encode(keyword)+'&random='+JM.randomNumber;
		}
	});
	$("#searchIntBtn").bind("click",function(){
		var keyword = $("#keyword");
		if(JM.isNullByJQuery(keyword)||keyword.val()=="请输入城市关键词"){
			JM.alert('请输入城市关键词~');
			keyword.focus();
			return false;
		} else {
			window.location.href = '/systemDictionary_searchInt.action?keyword='+JM.encode(keyword)+'&random='+JM.randomNumber;
		}
	});
}