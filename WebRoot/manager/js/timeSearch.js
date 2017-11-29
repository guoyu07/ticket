/**
 * @descript 搜索统计专用JS
 * @author   HiSay
 * @datetime 2016-08-12 11:26:22
 */
 /*************************************************/
jQuery(function(){
	editTimeSearchInital();
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2016-08-12 11:26:22
 */
function editTimeSearchInital() {
	//write code here.
	jQuery(".batchDownLoadBtn").bind("click", function(){
		batchDownLoadEntity(jQuery(this).attr("entityName"), jQuery(this).attr("value"),$(this).attr('methodName'));
	});
	jQuery(".batchDownLoadBtn1").bind("click", function(){
		batchDownLoadEntity1(jQuery(this).attr("entityName"), jQuery(this).attr("value"),$(this).attr('methodName'));
	});
}

/**
 * 根据时间端下载
 * @param entityName
 * @param selectElementId
 * @param methodName
 * @param resultSucTitle
 * @param resultFailedTitle
 * @returns {Boolean}
 */
function batchDownLoadEntity1(entityName, selectElementId, methodName,resultSucTitle, resultFailedTitle) {
	   /*var elementIds = JM.getValueByCheckedName(selectElementId);
	   //判断是否有选中要操作的信息
	   if(elementIds == null || elementIds == '') {
			JM.alert('请选择您要批量操作的信息！');
			return false;
	   }*/
		var date = $("#date");
		if(JM.isNullByJQuery(date)){
			JM.alert("请选择下载日期！", 2000);
		}else{
			easyDialog.open({
				container : {
					header : '系统提示',
					content : "是否下载所选时间段反馈资料？",
					yesFn : function(){
						
						var actionUrl = getActionUrlByEntity(entityName, methodName);
						window.location = actionUrl+"?date="+date.val();
						//+ '?idsValue='+elementIds+'&random='+JM.randomNumber;
					},
					noFn : true
				},
				overlay : true,
				fixed : false,
				drag: false
			});
			return false;
		}
	}

/**
 * 批量下载
 */
function batchDownLoadEntity(entityName, selectElementId, methodName,resultSucTitle, resultFailedTitle) {
	   /*var elementIds = JM.getValueByCheckedName(selectElementId);
	   //判断是否有选中要操作的信息
	   if(elementIds == null || elementIds == '') {
			JM.alert('请选择您要批量操作的信息！');
			return false;
	   }*/
	   easyDialog.open({
			container : {
				header : '系统提示',
				content : "是否下载全部反馈资料？",
				yesFn : function(){
					
					var actionUrl = getActionUrlByEntity(entityName, methodName);
					window.location = actionUrl;
					 //+ '?idsValue='+elementIds+'&random='+JM.randomNumber;
				},
				noFn : true
			},
			overlay : true,
			fixed : false,
			drag: false
		});
	   return false;
	}