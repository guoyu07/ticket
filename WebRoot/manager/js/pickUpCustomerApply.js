/**
 * @descript 捡单客户申请专用JS
 * @author   HiSay
 * @datetime 2015-11-24 15:45:34
 */
 /*************************************************/

/**
 * @description 初始化捡单客户申请方法参数
 * @author HiSay
 * @datetime 2015-11-24 15:45:34
 */
jQuery(function(){
	editPickUpCustomerApplyInital();
	
	$(".audit").bind("click", function(){
		audit($(this).attr("entityName"), $(this).attr("value"),$(this).attr("customer_id"),$(this).attr("employee_id"));
	});
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-11-24 15:45:34
 */
function editPickUpCustomerApplyInital() {
	//write code here.
}


function audit(entityName, entityId,customerId,employeeId) {
	
	   easyDialog.open({
			container : {
				header : '系统提示',
				content : '是否同意将此客户所有相关数据转至此员工？',
				yesFn : function(){
				   var actionUrl = getActionUrlByEntity(entityName, 'auditPickUp');
			  		jQuery.ajax({
			  			type:'POST',
			  			dataType:'json',
			  			url:actionUrl,
			  			data:'id='+entityId+'&random='+JM.randomNumber+'&employee_id='+employeeId+'&customer_id='+customerId,
			  			success:function(data){
			  				if(data.status == JM.YES){
			  					easyDialog.open({
			  						container : {
			  							header : '系统提示',
			  							content : data.info,
			  							yesFn : function(){
			  								window.location.reload();
			  							}
			  						},
			  						overlay : true,
			  						fixed : false,
			  						drag: false
			  					});
			  				} else if(data.status == JM.NO) {
			  					JM.alert(data.info);
			  				}
			  			}
			  		});
				},
				noFn : true
			},
			overlay : true,
			fixed : false,
			drag: false
		});
	   return false;
	}