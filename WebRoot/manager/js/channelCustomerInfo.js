/**
 * @descript 渠道客户信息专用JS
 * @author   HiSay
 * @datetime 2015-11-04 10:39:40
 */
 /*************************************************/

/**
 * @description 初始化渠道客户信息方法参数
 * @author HiSay
 * @datetime 2015-11-04 10:39:40
 */
jQuery(function(){
	editChannelCustomerInfoInital();
	youhuizhengce();
	$("#searchBtn").bind("click", search);
	$("#channelTypeId").on("click",youhuizhengce);
	$(".applyPickUp").bind("click", function(){
		applyPickUp($(this).attr("entityName"), $(this).attr("value"),$(this).attr("employee_id"));
	});
	$("#loginId").on("keyup",yzyonghuming);
	$(".batchGiveUpCustomer").on("click",batchGiveUpCustomer);
	$(".singleGiveUpCustomer").on("click",singleGiveUpCustomer);
	$("#name").on("blur",isExist);
	$("#name").on("change",isExist);
	initSelectCustomer();
	transferCustomerInit();
	
	statisticInit();
});

function isExist(){
	var name1 = $("#name");
	var name = $("#name").val();
	if(!JM.isNull(name)){
		$.ajax({
			type:"post",
			dataType:"json",
			url:"/channelCustomerInfo_nameIsExsit.action",
			data:"name="+name,
			success:function(data){
					if(!JM.isNull(data.info)){
						if(data.info == "该客户已是渠道客户，请确认客户名称！"){
							easyDialog.open({
								container : {
									header:"系统提示",
									content:data.info,
//									yesFn:function (){
//										JM.goUrl("channelCustomerInfo_manageBase.action?name="+name);
//									},
									noFn:true
								}
							});
					}else{
						easyDialog.open({
							container : {
								header:"系统提示",
								content:data.info,
								yesFn:function (){
									window.location.href = "/channelCustomerInfo_manageBase.action?name="+JM.encode(name1);
								},
								noFn:true
							}
						});
					}
				}
			}
		});
	}
}

function yzyonghuming(){
	var loginId = $("#loginId").val();
	if(JM.isNull(loginId)){
		$("#loginmsg").attr("style","color:red");
		$("#loginmsg").text("请填写用户名！");
	}else{
		$.ajax({
			type:"post",
			dataType:"json",
			data:"loginId="+loginId,
			url:"/channelCustomerInfo_queryByLoginIdExist.action",
			success:function(data){
				if(data.status == 'y'){
					$("#loginmsg").attr("style","color:green");
					$("#loginmsg").text(data.info);
				}else{
					$("#loginmsg").attr("style","color:red");
					$("#loginmsg").text(data.info);
				}
			}
		});
	}
}

function youhuizhengce(){
	var channelTypeId = $("#channelTypeId").val();
//	JM.goUrl("favouredPolicy_findByCustomerId.action?id=" + channelTypeId);
	$.ajax({
		type:'POST',
		dataType:'json',
		url:'/favouredPolicy_findByCustomerId.action',
		data:"id="+channelTypeId,
		success:function(jdata){
			if(jdata.status == "y"){
				var ids = $.parseJSON(jdata.info);
				var idss = ids.favouredPolicys;
				var html = "";
				for(var i=0;i<idss.length;i++){
					html += "<option value="+idss[i].id+">"+idss[i].name+"</option>";
				}
				$("#favouredPolicyId").html(html);
			}
		}
	});
}

function transferCustomerInit(){
	$("#transferCustomer").unbind().bind('click',function(){
		var bf = $("#beforeEmployee_id").val();
		var af = $("#afterEmployee_id").val();
		if(JM.isNull(bf)){
			JM.alert("请选择员工");
			return;
		}
		var selectedCustomer;
		var selectIntention = $("#getIntentionCustomer option").map(function(){return $(this).val();}).get().join(',');
		var selectChannel = $("#getChannelCustomer option").map(function(){return $(this).val();}).get().join(',');
		if(JM.isNull(selectIntention)){
			if(JM.isNull(selectChannel)){
				JM.alert("转让的客户不能为空！",1200);
				return;
			}else{
				selectedCustomer = selectChannel;
			}
		}else{
			if(JM.isNull(selectChannel)){
				selectedCustomer = selectIntention;
			}else{
				selectedCustomer = selectIntention+','+selectChannel;
			}
			
		}
		if(JM.isNull(af)){
			JM.alert("请选择接手客户的员工");
			return;
		}
		if(bf == af){
			JM.alert("员工不能是同一个！");
			return;
		}
		JM.alert("正在执行，请稍等",-1);
		$.ajax({
			type:'POST',
			dataType:'json',
			url:'/channelCustomerInfo_transfer.action',
			data:"operationFlag=1"+"&beforeEmployee_id="+bf+"&afterEmployee_id="+af+"&customer_id="+selectedCustomer,
			success:function(data){
				if(data.status=='y') {
					JM.alert(data.info);
					window.location.reload(); 
				}else{
					JM.alert(data.info);
					window.location.reload();
				}
			}
		});
	});
}

/**
 * 批量放弃客户
 * @return
 */
function batchGiveUpCustomer(){
	var idsValue = JM.getValueByCheckedName("channelCustomerInfoCheckBox");
	if(idsValue.length > 0){
		if(confirm("是否确认放弃客户")){
			$.ajax({
				type:'POST',
				dataType:'text',
				url:'/channelCustomerInfo_batchGiveUpCustomer.action',
				data:'idsValue='+idsValue,
				success:function(data){
					if(JM.SUCCESS == data) {
						window.location.reload(); 
					}
				}
			});
		}
	}else{
		alert("请选中需要放弃的客户");
	}
}

/**
 * 单个放弃客户
 * @return
 */
function singleGiveUpCustomer(){
	var idsValue = $(this).attr("value");
	if(confirm("是否确认放弃客户")){
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/channelCustomerInfo_batchGiveUpCustomer.action',
			data:'idsValue='+idsValue,
			success:function(data){
				if(JM.SUCCESS == data) {
					window.location.reload();
				}
			}
		});
	}
}

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-11-04 10:39:40
 */
function editChannelCustomerInfoInital() {
	//write code here.
}




function changeBalance(){
	
	var m = parseFloat($("#favouredPolicyId").find("option:selected").attr("m"));
	var d =parseFloat($("#favouredPolicyId").find("option:selected").attr("d"));
	var balance;
	var openAccountMoney =parseFloat( $("#openAccountMoney").val());
	if(openAccountMoney>=m){
		balance = openAccountMoney+d;
		$("#balanceValue").html(balance);
	}else{
		balance=openAccountMoney;
		$("#balanceValue").html(balance);
		
	}
}

/**
 * @description 搜索渠道客户(根据客户名称/联系电话关键词)
 * @author HiSay
 * @datetime 2015-04-02 15:31:32
 */
function search() {
	var methodName = $(this).attr("methodName");
	var keyword = $("#keyword");
	if(JM.isNullByJQuery(keyword)||keyword.val()=="请输入客户名称/联系电话关键词"){
		alert('请输入检索关键词~');
		keyword.focus();
		return false;
	} else {
		window.location.href = '/channelCustomerInfo_'+methodName+'.action?keyword='+JM.encode(keyword)+'&random='+JM.randomNumber;
	}
}

function applyPickUp(entityName, entityId,employee_id) {
	   easyDialog.open({
			container : {
				header : '系统提示',
				content : '是否申请捡单？',
				yesFn : function(){
				   var actionUrl = getActionUrlByEntity(entityName, 'applyPickUp');
			  		jQuery.ajax({
			  			type:'POST',
			  			dataType:'json',
			  			url:actionUrl,
			  			data:'id='+entityId+'&random='+JM.randomNumber+'&employee_id='+employee_id,
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

function initSelectCustomer(){
	$("#beforeEmployee_id").val("");
	$("#afterEmployee_id").val("");
	$("#beforeEmployee_id").unbind().bind("change",function(){
		var employeeId = $(this).val();
		JM.alert("正在查询该员工的客户", -1);
		$.ajax({
			type:'POST',
			dataType:'json',
			url:'channelCustomerInfo_queryCustomerByEmployee.action',
			data:'beforeEmployee_id='+employeeId,
			success:function(data){
				$("#selectChannelCustomer").empty();
				$("#selectIntentionCustomer").empty();
				$("#getIntentionCustomer").empty();
				$("#getChannelCustomer").empty();
				var intentionList = data.intentionList;
				var channelList = data.channelList;
				$.each(intentionList,function(i,item){
					$("#selectIntentionCustomer").append("<option value='"+item.id+"'>"+item.name+"</option>");
				})
				$.each(channelList,function(i,item){
					$("#selectChannelCustomer").append("<option value='"+item.id+"'>"+item.name+"</option>");
				})
				JM.alert("", 100);
			}
		});
	});
	
	$("#selectIntentionCustomer").dblclick(function(){
		var $option = $("#selectIntentionCustomer option:selected");
		$option.appendTo("#getIntentionCustomer");
	});
	$("#getIntentionCustomer").dblclick(function(){
		var $option = $("#getIntentionCustomer option:selected");
		$option.appendTo("#selectIntentionCustomer");
	});
	$("#singleSelectIntention").unbind().bind('click',function(){
		var $option = $("#selectIntentionCustomer option:selected");
		$option.appendTo("#getIntentionCustomer");
	});
	$("#singleDisSelectIntention").unbind().bind('click',function(){
		var $option = $("#getIntentionCustomer option:selected");
		$option.appendTo("#selectIntentionCustomer");
	});
	
	$("#multiSelectIntention").click(function(){
		$("#selectIntentionCustomer option").appendTo("#getIntentionCustomer");
	});
	$("#multiDisSelectIntention").click(function(){
		$("#getIntentionCustomer option").appendTo("#selectIntentionCustomer");
	});
	
	
	$("#selectChannelCustomer").dblclick(function(){
		var $option = $("#selectChannelCustomer option:selected");
		$option.appendTo("#getChannelCustomer");
	});
	$("#getChannelCustomer").dblclick(function(){
		var $option = $("#getChannelCustomer option:selected");
		$option.appendTo("#selectChannelCustomer");
	});
	$("#singleSelectChannel").unbind().bind('click',function(){
		var $option = $("#selectChannelCustomer option:selected");
		$option.appendTo("#getChannelCustomer");
	});
	$("#singleDisSelectChannel").unbind().bind('click',function(){
		var $option = $("#getChannelCustomer option:selected");
		$option.appendTo("#selectChannelCustomer");
	});
	
	$("#multiSelectChannel").click(function(){
		$("#selectChannelCustomer option").appendTo("#getChannelCustomer");
	});
	$("#multiDisSelectChannel").click(function(){
		$("#getChannelCustomer option").appendTo("#selectChannelCustomer");
	});
}


function statisticInit(){
	$("#statisticBtn").bind("click",function(){
		var startTime = $("#startTime").val();
		var deadline = $("#deadline").val();
		var department_id = $("#department_id").val();
		$.ajax({
			type:'POST',
			dataType:'json',
			url:'/channelCustomerInfo_statisticByDate.action',
			data:'startDate='+startTime+'&endDate='+deadline+"&department_id="+department_id,
			success:function(val){
		        $("#statisticDetail").html("");//清空info内容
		        $.each(val, function(i, item) {
		            $("#statisticDetail").append(
                    "<tr><td>" + item.dept + "</td>" + 
                    "<td>" + item.customerCount    + "</td>" +
                    "<td>" + item.protectCount + "</td></tr>");
		        });
			}
		});
	});
}

