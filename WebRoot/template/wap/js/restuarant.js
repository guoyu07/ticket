$(function(){
	businessInit();
	load();
});

function load(){
	var businessId = $("#businessTypeId").val();
	if(!JM.isNull(businessId)){
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/airport_businessListAjax.action',
			data:'var='+businessId,
			success:function(data){
				$("#sucai").html(data);
//				businessInit();
			}
		});
	}
}

function businessTopType(obj) {
	var typeId = obj.attr("typeId");
	$(".businessTopType").removeClass("bg-yello selected").addClass("bg-sub");
	obj.removeClass("bg-sub").addClass("bg-yello selected");
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'/airport_businessSecondTypeAjax.action',
		data:'id='+typeId,
		success:function(data){
			$(".secondType").html(data);
			businessTopTypeInit();
			businessInit();
			return;
		}
	});
}

function businessInit(){
	//根据商家分类查询商家列表
	$(".businessInfoListByType").bind('click',function(){
		//判断位置和排序是否有被选中的
		//排序
		var li = $(".div_px").find("li[class*='li_sel1']");
		var order="";
		if(!JM.isNullByJQuery(li)){
			order = li.attr("order");
		}
		
		//位置
		var li1 = $(".wz_r").find("li[class*='li_sel']");
		
		var wz="";
		if(!JM.isNullByJQuery(li1)){
			wz = li1.attr("wz");
		}
		
		var typeId = $(this).attr("typeId");
		var data='';
		if(!JM.isNull(order) && !JM.isNull(wz)){
			data = 'var='+typeId+"&order="+order+"&wz="+wz;
		}else if(!JM.isNull(order) && JM.isNull(wz)){
			data = 'var='+typeId+"&order="+order;
		}else if(JM.isNull(order) && !JM.isNull(wz)){
			data = 'var='+typeId+"&wz="+wz;
		}else{
			data = 'var='+typeId;
		}
		
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/airport_businessListAjax.action',
			data:data,
			success:function(data){
				$("#sucai").html(data);
				businessInit();
			}
		});
	});
	
	//根据位置查询商家列表
	$(".wz_kaifa").bind('click',function(){
		//判断分类和排序是否有被选中的
		//排序
		var li = $(".div_px").find("li[class*='li_sel1']");
		var order="";
		if(!JM.isNullByJQuery(li)){
			order = li.attr("order");
		}
		
		//分类
		var li1 = $(".type_r").find("li[class*='li_sel']");
		var typeId="";
		if(!JM.isNullByJQuery(li1)){
			typeId = li1.attr("typeId");
		}
		
		var wz = $(this).attr("wz");
		var data='';
		if(!JM.isNull(order) && !JM.isNull(typeId)){
			data = 'wz='+wz+"&order="+order+"&var="+typeId;
		}else if(!JM.isNull(order) && JM.isNull(typeId)){
			data = 'wz='+wz+"&order="+order;
		}else if(JM.isNull(order) && !JM.isNull(typeId)){
			data = 'wz='+wz+"&var="+typeId;
		}else{
			data = 'wz='+wz;
		}
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/airport_businessListAjax.action',
			data:data,
			success:function(data){
				$("#sucai").html(data);
				businessInit();
			}
		});
	});
	
	//根据活动查询
	$(".businessEvent").bind("click",function(){
		var id = $(this).attr("attrId");
		$.post("/airport_businessListAjaxByEvent.action",{id:id},function(data){
			$("#sucai").html(data);
			//businessInit();
		},"text");
	});
	
	//根据有赞数据排序
	$(".order").bind("click",function(){
		//分类
		var li1 = $(".type_r").find("li[class*='li_sel']");
		var typeId="";
		if(!JM.isNullByJQuery(li1)){
			typeId = li1.attr("typeId");
		}
		
		//位置
		var li2 = $(".wz_r").find("li[class*='li_sel']");
		
		var wz="";
		if(!JM.isNullByJQuery(li2)){
			wz = li2.attr("wz");
		}
		
		
		var order = $(this).attr("order");
		var data='';
		if(!JM.isNull(wz) && !JM.isNull(typeId)){
			data = 'order='+order+"&wz="+wz+"&var="+typeId;
		}else if(!JM.isNull(wz) && JM.isNull(typeId)){
			data = 'order='+order+"&wz="+wz;
		}else if(JM.isNull(wz) && !JM.isNull(typeId)){
			data = 'order='+order+"&var="+typeId;
		}else{
			data = 'order='+order;
		}
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/airport_businessListAjax.action',
			data:data,
			success:function(data){
				$("#sucai").html(data);
				businessInit();
			}
		});
	});
}