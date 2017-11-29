/**
 * @descript 预定义搜索结果专用JS
 * @author   HiSay
 * @datetime 2015-12-14 18:57:30
 */
 /*************************************************/

/**
 * @description 初始化预定义搜索结果方法参数
 * @author HiSay
 * @datetime 2015-12-14 18:57:30
 */
jQuery(function(){
	editPreResultDefinitionInital();
	$("#searchBtn").bind("click", searchPreResultDefinitionInital);
	initValidForm();
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-12-14 18:57:30
 */
function editPreResultDefinitionInital() {
	//write code here.
}
/**
 * @description 后台检索参数
 * @author HiSay
 * @datetime 2015-12-14 18:57:30
 * @return
 */
function searchPreResultDefinitionInital() {
	var keyword = $("#keyword");
	if(JM.isNullByJQuery(keyword)) {
		keyword.focus();
		return false;
	} else {
		window.location.href = '/preResultDefinition_search.action?keyword='+JM.encode(keyword)+'&random='+JM.randomNumber;
	}
}

/**
 * @description initial the searchForm to valid and submit.
 * @author HiSay
 * @datetime 2014-10-10 07:41:27
 */
function initValidForm() {
	var searchForm = jQuery("#searchValidForm");
	searchForm.Validform({
		tiptype:tipTypeFun,
		ajaxPost:true,
		datatype:{
			"double":/^$(\d{1,4})|^\d+(\.\d{1,5})?$/,
			"sf" : yzsfz,
			"np" : /^\d*$/ //非负数的验证
		},
		beforeSubmit:function(curform){
			
			var keyword = $('#keyword').val();
			var negativeKeyword = $('#negativeKeyword').val();
			var commonKey = inStr(keyword, negativeKeyword);
			if(!JM.isNull(commonKey)){
				
				JM.alert("关键词和否定词中，存在相同词：" + commonKey, 3000);
				return false;
			}
			
			var negativeKeyword2 = $('#negativeKeyword2').val();
			commonKey = inStr(keyword, negativeKeyword2);
			if(!JM.isNull(commonKey)){
				
				JM.alert("关键词和精确否定词中，存在相同词：" + commonKey, 3000);
				return false;
			}
			return true;
		},callback:function(data){
		   if(data.status == JM.YES) {
			   JM.alert(data.info);
			   var manageLink = jQuery("#manageLink");
			   if(!JM.isNull(manageLink) && manageLink.size() > 0){
				   window.location.href = manageLink.attr("href");
			   }
		   } else {
			   JM.alert(data.info, 3000);
			   JM.flush();
		   }
		   return false;
		}
	});
}

function inStr(str1, str2){
	
	if(JM.isNull(str1) || JM.isNull(str2)){
		
		return null;
	}
	
	var common = [];
	var arr1 = str1.split(",");
	var arr2 = str2.split(",");
	for(var i = 0; i < arr1.length; i++){
		
		for(var j = 0; j < arr2.length; j++){
			
			if(arr1[i] == arr2[j] && $.inArray(arr1[i], common) < 0){
				
				common.push(arr1[i]);
			}
		}
	}
	return common.join(",");
}