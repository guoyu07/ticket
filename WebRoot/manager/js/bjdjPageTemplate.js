/**
 * @descript jdj支付激活页面模板专用JS
 * @author   HiSay
 * @datetime 2016-08-18 15:26:40
 */
 /*************************************************/
jQuery(function(){
	editBjdjPageTemplateInital();
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
	var memberForm3 = jQuery("#memberForm3");
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
			var alertConfirm = memberForm3.attr("alertConfirm");
			if(!JM.isNull(alertConfirm)) {
				return true;
			} else {
				if(confirm('是否确认提交？')){
					memberForm3.find("textarea").each(function(){
						try {
							var obj=$(this);
							if(!JM.isNull(obj)){
								try {
									var objKE = eval(obj.attr("name"));
									objKE.sync();//sync textarea editor content to commom's textarea
								} catch(ee){}
							}
						} catch(e) {}
					});
					edit();
					return true;	
				} else {
					return false;
				}
			}
			return false;
		},
	});
});
/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2016-08-18 15:26:40
 */
function editBjdjPageTemplateInital() {
	//write code here.
	createEditor("content");
	var buttonUrls = $(".hidelatitude");
	var buttonTypes = $(".hidefloorNumber");
	var buttonClasses = $(".hideclassName");
	var latitudes1 = $(".buttonUrl");
	var floorNumbers1 = $(".buttonType");
	var classNames1 = $(".buttonClass");
	//var latitudesArr = new Array();
	//var floorNumbersArr = new Array();
	if(buttonUrls.length > 1){
		for(var i=0;i<buttonUrls.length;i++){
			var a = $(buttonUrls[i]).val();
			var b = $(buttonTypes[i]).val();
			 $(latitudes1[i]).val(a);
			 $(floorNumbers1[i]).val(b);
		}
	}
	if(buttonClasses.length > 0){
		for(var i=0;i<buttonClasses.length;i++){
			var c = $(buttonClasses[i]).val();
			$(classNames1[i]).val(c);
		}
	}
}

function addInfoPosition(){
	var $jwdu = $("#jwdu");
	var $add = '<p>名称：<input class="buttonName" name="buttonName" dataType="*"/> '+
	'链接：<input class="buttonUrl"  name="buttonUrl" dataType="*"/> 类型：<select class="buttonType"  name="buttonType" dataType="*"><option value="1">a链接</option><option value="2">button按钮</option></select>样式:<input class="buttonClass"  name="buttonClass" dataType="*"/><a href="javascript:;" onclick="removeP(this);">移除<a/><p/>';
	$jwdu.append($add);
}

function removeP(dom){
	var a = $(dom);
	var parent = a.parent();
	parent.remove();
}
function add(){
	var longitudes = $("#jwdu").find(".buttonName");
	var latitudes = $("#jwdu").find(".buttonUrl");
	var floorNumbers = $("#jwdu").find(".buttonType");
	var classNames = $("#jwdu").find(".buttonClass");
//	var positionAlias = $("#positionAlias").val();
//	var mobile = $("#mobile").val();
//	var url1 = $("#url").val();
	var content = $("#content").val();
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
		url:"bjdjPageTemplate_add.action?operationFlag=1&versionFlag=site",
		type:"post",
		dataType:"json",
		data:{"buttonClasses":arrclassNames.join(","),"name":name,"content":content,"buttonNames":arrlongitudes.join(","),"buttonUrls":arrlatitudes.join(","),"buttonTypes":arrfloorNumbers.join(",")},
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
	var longitudes = $("#jwdu").find(".buttonName");
	var latitudes = $("#jwdu").find(".buttonUrl");
	var floorNumbers = $("#jwdu").find(".buttonType");
	var classNames = $("#jwdu").find(".buttonClass");
//	var positionAlias = $("#positionAlias").val();
//	var mobile = $("#mobile").val();
//	var url1 = $("#url").val();
	var id = $("#id").val();
	var name = $("#name").val();
	var content = $("#content").val();
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
		url:"bjdjPageTemplate_edit.action?operationFlag=1&versionFlag=site",
		type:"post",
		dataType:"json",
		data:{"id":id,"buttonClasses":arrclassNames.join(","),"name":name,"content":content,"buttonNames":arrlongitudes.join(","),"buttonUrls":arrlatitudes.join(","),"buttonTypes":arrfloorNumbers.join(",")},
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