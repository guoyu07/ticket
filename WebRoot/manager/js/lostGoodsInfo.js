/**
 * @descript 遗失物品信息专用JS
 * @author   HiSay
 * @datetime 2015-11-23 16:17:30
 */
 /*************************************************/

/**
 * @description 初始化遗失物品信息方法参数
 * @author HiSay
 * @datetime 2015-11-23 16:17:30
 */
jQuery(function(){
	editLostGoodsInfoInital();
	
	goodsDescript = createEditor("goodsDescript");
	remark = createEditor("remark");
	
	$("#searchBtn").bind("click",search);
	
	initUploadify("picture", 1, "fileQueue", true, false, "preImage", globalVersionFlag);
	$("#commonFormSumit").on("click",commonFormSumit);
});

function commonFormSumit(){
	$.ajax({
		type:'POST',
		dataType:'json',
		url:'/lostGoodsInfo_importLostGoods.action',
		data:$("#commonForm").serialize(),
		success:function(data){
			JM.alert(data.info, 2000, function(){
				JM.goPage("lostGoodsInfo_manage.action?versionFlag=site");
			});
		}
	});
}

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-11-23 16:17:30
 */
function editLostGoodsInfoInital() {
	JM.selectSelect("pickPosition_id");
	JM.selectSelect("type_id");
	JM.selectSelect("color");
	
//	$(".hotEntity").bind("click", function(){
//		var obj = $(this);
//		var id = obj.attr("id");
//		var stateValue = obj.attr("statusValue");
//		var isUpload = window.confirm("确认要上传么？");
//		if(isUpload){
//			JM.alert("正在上传……", -1);
//			$.ajax({
//				type:'POST',
//				dataType:'json',
//				url:'lostGoodsInfo_hot.action',
//				data:'id='+id+'&statusValue='+stateValue,
//				success:function(data){
//					if(data.status=='y'){
//						window.location.reload();
//						JM.alert("上传完成！", 0);
//						return;
//					}else{
//						JM.alert("上传失败！", 0);
//						return;
//					}
//				}
//			});
//		}else{
//			return false;
//		}
//	});
	//write code here.
	
	//绑定批量热门按钮的事件
	jQuery(".batchUploadBtn").bind("click", function(){
		batchOperationEntity(jQuery(this).attr("entityName"), jQuery(this).attr("value"), 
							'hot', '是否确认批量上传选中信息？', '批量上传信息成功！', '批量上传信息失败！');
	});
}

function search(){
	var keyword = $("#keyword");
	if(JM.isNullByJQuery(keyword)||keyword.val()=="请输入描述关键词检索物品信息"){
		alert('请输入检索关键词~');
		keyword.focus();
		return false;
	} else {
		window.location.href = '/lostGoodsInfo_search.action?keyword='+JM.encode(keyword)+'&random='+JM.randomNumber;
	}
}

