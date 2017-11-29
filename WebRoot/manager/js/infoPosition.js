/**
 * @descript 信息定位专用JS
 * @author   HiSay
 * @datetime 2015-10-20 18:13:14
 */
 /*************************************************/

/**
 * @description 初始化信息定位方法参数
 * @author HiSay
 * @datetime 2015-10-20 18:13:14
 */
jQuery(function(){
//	var memberForm2 = $("#memberForm2").Validform();
	$("#memberForm2").Validform({
		btnSubmit : "#add", 
		tiptype:function(msg, o, cssctl) {
			//msg：提示信息;
			//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
			//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
			if(!o.obj.is("form")) {//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
				var objtip = o.obj.siblings(".Validform_checktip");
				cssctl(objtip, o.type);
				objtip.text(msg);
			} else {
				var objtip = o.obj.find("#msgdemo");
				cssctl(objtip, o.type);
				objtip.text(msg);
			}
		},
		ajaxPost:true,
		beforeSubmit : function(curform) {
			add();
			return false;
		},
	});
//	var memberForm3 = $("#memberForm3").Validform();
	$("#memberForm3").Validform({
		btnSubmit : "#edit", 
		tiptype:function(msg, o, cssctl) {
			//msg：提示信息;
			//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
			//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
			if(!o.obj.is("form")) {//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
				var objtip = o.obj.siblings(".Validform_checktip");
				cssctl(objtip, o.type);
				objtip.text(msg);
			} else {
				var objtip = o.obj.find("#msgdemo");
				cssctl(objtip, o.type);
				objtip.text(msg);
			}
		},
		ajaxPost:true,
		beforeSubmit : function(curform) {
			edit();
			return false;
		},
	});
	/*memberForm2.ignore("#mobile,#url");
	memberForm3.ignore("#mobile,#url");*/
	editInfoPositionInital();
	initPositionMap();
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-10-20 18:13:14
 */
function editInfoPositionInital() {
	var latitudes = $(".hidelatitude");
	var floorNumbers = $(".hidefloorNumber");
	var classNames = $(".hideclassName");
	var latitudes1 = $(".latitude");
	var floorNumbers1 = $(".floorNumber");
	var classNames1 = $(".className");
	//var latitudesArr = new Array();
	//var floorNumbersArr = new Array();
	if(latitudes.length > 1){
		for(var i=0;i<latitudes.length;i++){
			var a = $(latitudes[i]).val();
			var b = $(floorNumbers[i]).val();
			 $(latitudes1[i]).val(a);
			 $(floorNumbers1[i]).val(b);
		}
	}
	if(classNames.length > 0){
		for(var i=0;i<classNames.length;i++){
			var c = $(classNames[i]).val();
			$(classNames1[i]).val(c);
		}
	}
}
function add(){
	var longitudes = $("#jwdu").find(".longitude");
	var latitudes = $("#jwdu").find(".latitude");
	var floorNumbers = $("#jwdu").find(".floorNumber");
	var classNames = $("#jwdu").find(".className");
	var positionAlias = $("#positionAlias").val();
	var mobile = $("#mobile").val();
	var url1 = $("#url").val();
	var name = $("#name").val();
	var url = $("#manageLink").attr("href");
	var arrlongitudes = new Array();
	var arrlatitudes = new Array();
	var arrfloorNumbers = new Array();
	var arrclassNames = new Array();
	for(var i=0;i<longitudes.length;i++){
		arrlongitudes[i] = $(longitudes[i]).val();
		arrlatitudes[i] = $(latitudes[i]).val();
		arrfloorNumbers[i] = $(floorNumbers[i]).val();
		arrclassNames[i] = $(classNames[i]).val();
	}
	$.ajax({
		url:"infoPosition_add.action?operationFlag=1&versionFlag=site",
		type:"post",
		dataType:"json",
		data:{"classNames":arrclassNames.join(","),"mobile":mobile,"url":url1,"name":name,"positionAlias":positionAlias,"longitudes":arrlongitudes.join(","),"latitudes":arrlatitudes.join(","),"floorNumbers":arrfloorNumbers.join(",")},
		success:function(data){
			if(data.status == 'y'){
				JM.alert(data.info, 2000, function(){
					JM.goUrl(url);
				});
			}else{
				JM.alert(data.info, 2000);
			}
		}
	});
}

function edit(){
	var longitudes = $("#jwdu").find(".longitude");
	var latitudes = $("#jwdu").find(".latitude");
	var floorNumbers = $("#jwdu").find(".floorNumber");
	var classNames = $("#jwdu").find(".className");
	var positionAlias = $("#positionAlias").val();
	var mobile = $("#mobile").val();
	var url1 = $("#url").val();
	var id = $("#id").val();
	var name = $("#name").val();
	var url = $("#manageLink").attr("href");
	var arrlongitudes = new Array();
	var arrlatitudes = new Array();
	var arrfloorNumbers = new Array();
	var arrclassNames = new Array();
	for(var i=0;i<longitudes.length;i++){
		arrlongitudes[i] = $(longitudes[i]).val();
		arrlatitudes[i] = $(latitudes[i]).val();
		arrfloorNumbers[i] = $(floorNumbers[i]).val();
		arrclassNames[i] = $(classNames[i]).val();
	}
	$.ajax({
		url:"infoPosition_edit.action?operationFlag=1&versionFlag=site",
		type:"post",
		dataType:"json",
		data:{"classNames":arrclassNames.join(","),"mobile":mobile,"url":url1,"id":id,"name":name,"positionAlias":positionAlias,"longitudes":arrlongitudes.join(","),"latitudes":arrlatitudes.join(","),"floorNumbers":arrfloorNumbers.join(",")},
		success:function(data){
			if(data.status == 'y'){
				JM.alert(data.info, 2000, function(){
					JM.goUrl(url);
				});
			}else{
				JM.alert(data.info, 2000);
			}
		},
		error:function(){
			alert("出错了");
		}
	});
}
function addInfoPosition(){
	var $jwdu = $("#jwdu");
	var $add = '<p>经度：<input class="longitude" name="longitude"/> '+
	'纬度：<input class="latitude"  name="latitude" /> 楼层号：<input class="floorNumber"  name="floorNumber"/>单/多点位名称:<input class="className"  name="className" datatype="*"/><a href="javascript:;" onclick="removeP(this);">移除<a/><p/>';
	$jwdu.append($add);
}
function removeP(dom){
	var a = $(dom);
	var parent = a.parent();
	parent.remove();
}
//初始化地图
function initPositionMap(){
	var position = $("#positionMap");
	if(position&&position.size()>0){
		var map = new BMap.Map("positionMap");
		map.centerAndZoom("昆明", 11);
		map.addEventListener("click",function(e){
			//alert(e.point.lng + "," + e.point.lat);
			$("#longitude").val(e.point.lng);
			$("#latitude").val(e.point.lat);
		});
	}
}