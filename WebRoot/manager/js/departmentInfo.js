/**
 * @descript 部门信息专用JS
 * @author   HiSay
 * @datetime 2015-11-02 22:46:07
 */
 /*************************************************/

/**
 * @description 初始化部门信息方法参数
 * @author HiSay
 * @datetime 2015-11-02 22:46:07
 */
jQuery(function(){
	editDepartmentInfoInital();
	
	introduce = createEditor('introduce');
	$("body").on("click",".add",function (){
		var id = $(this).attr("attrValue");
		window.location.href="/departmentInfo_add.action?id="+id;
	});
	$("body").on("click",".update",function (){
		var id = $(this).attr("attrValue");
		window.location.href="/departmentInfo_edit.action?id="+id;
	});
	$("body").on("click",".remove",function (){
		var id = $(this).attr("attrValue");
		if(confirm("是否确认删除")){
			remove(id);
		}
	});
	$("body").on("change",".changeOrderValue",changeOrderValue);
	$('body').on("click", ".editInCharge", function(){
		
		var id = $(this).attr("attrValue");
		window.location="/departmentInfo_editInCharge.action?id="+id;
	});
});

/**
 * 删除部门
 * @param id
 * @return
 */
function remove(id) {
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'/departmentInfo_realDelete.action',
		data:'id='+id,
		success:function(data){
			if(JM.SUCCESS == data) {
				window.location.reload();
			}
		}
	});
}

/**
 * 修改部门排序值
 * @return
 */
function changeOrderValue() {
	var id = $(this).parent().parent().find("input[type='hidden']").val();
	var obj = $(this);
	var orderValue = $(this).val();
	jQuery.ajax({
		type:'post',
		dataType:'text',
		url:'/departmentInfo_changeOrderValue.action',
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
 * @datetime 2015-11-02 22:46:07
 */
function editDepartmentInfoInital() {
	//write code here.
}