/**
 * @descript 角色信息专用JS
 * @author   HiSay
 * @datetime 2015-10-16 11:18:46
 */
/*************************************************/

/**
 * @description 初始化角色信息方法参数
 * @author HiSay
 * @datetime 2015-10-16 11:18:46
 */
jQuery(function(){
	editRoleInfoInital();
	if($("#tg").length > 0){
		
		renderTreeGrid();
	}
	
	
	descript = createSimpleEditor('descript');
	var position_id = $("#position_id").val();
	$("#selectAllCheckBox").bind("click",function(){
		if($(this).prop("checked")){
			popDiv();
			$("input[type='checkbox']").each(function(){
				$(this).prop("checked",true);
			});
			jQuery.ajax({
				type:'POST',
				dataType:'text',
				url:'/positionDataAythority_selectAll.action',
				data:'position_id='+position_id+'&random='+JM.randomNumber,
				success:function(data){
				hideDiv();
				//alert('授权成功');
			}
			});
		}else{
			$("input[type='checkbox']").each(function(){
				$(this).prop("checked",false);
			});
			popDiv();
			jQuery.ajax({
				type:'POST',
				dataType:'text',
				url:'/positionDataAythority_selectNone.action',
				data:'position_id='+position_id+'&random='+JM.randomNumber,
				success:function(data){
				hideDiv();
			}
			});
		}
	});
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-10-16 11:18:46
 */
function editRoleInfoInital() {
	//write code here.
}

function popDiv(){
	$("#popLayer").slideDown();
	$("#popbgLayer").fadeIn(200);
	$("#popIframeLayer").fadeIn(100);
}

function hideDiv(){
	$("#popLayer").slideUp();
	$("#popbgLayer").fadeOut(200);
	$("#popIframeLayer").fadeOut(100);
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
			width: $(".site_right").width(),
			height: "auto",
			collapsible : true,
			loadMsg : '数据加载中请稍后……',
			url : actionName+'_loadTree.action',
			idField : 'id',
			treeField : 'name',
			frozenColumns : [[{
				title : '菜单名称',
				field : 'name',
				width : '50%'
			}]],
			columns : [[{
				field : 'privilege',
				title : '权限',
				width : '45%',
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
	while((row = $(tree_).treegrid('getParent_id', row.id))){
		
		path.push(row.id);
	};
	path.reverse();
	TuYou.Cookie.set(SYSTEMDICTIONARY_PATH, path.join(','));
	return path.join(',');
}
