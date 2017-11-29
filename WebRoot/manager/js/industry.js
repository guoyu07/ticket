/**
 * @descript 企业行业专用JS
 * @author   HiSay
 * @datetime 2016-01-11 09:44:02
 */
 /*************************************************/

/**
 * @description 初始化企业行业方法参数
 * @author HiSay
 * @datetime 2016-01-11 09:44:02
 */
jQuery(function(){
	editIndustryInital();
	$("body").on("click",".add",function (){
		var id = $(this).attr("attrValue");
		window.location.href="/industry_add.action?id="+id;
	});
	$("body").on("click",".update",function (){
		var id = $(this).attr("attrValue");
		window.location.href="/industry_edit.action?id="+id;
	});
	$("body").on("click",".remove",function (){
		var id = $(this).attr("attrValue");
		if(confirm("是否确认删除")){
			remove(id);
		}
	});
	$("body").on("change",".changeOrderValue",changeOrderValue);
});


/**
 * 删除行业
 * @param id
 * @return
 */
function remove(id) {
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'/industry_realDelete.action',
		data:'id='+id,
		success:function(data){
			if(JM.SUCCESS == data) {
				window.location.reload();
			}
		}
	});
}

/**
 * 修改行业排序值
 * @return
 */
function changeOrderValue() {
	var id = $(this).parent().parent().find("input[type='hidden']").val();
	var obj = $(this);
	var orderValue = $(this).val();
	jQuery.ajax({
		type:'post',
		dataType:'text',
		url:'/industry_changeOrderValue.action',
		data:'id='+id+"&orderValue=" + orderValue,
		success:function(data){
			if(JM.SUCCESS == data) {
				obj.css({"color": "#00aa00", "font-weight": "bold"});
			}
		}
	});
}

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2016-01-11 09:44:02
 */
function editIndustryInital() {
	//write code here.
}