/**
 * 常用旅客的js
*/
$(function(){
	
	$("#memberForm2").Validform({
		btnSubmit : "#btncomm", 
		tiptype:tipTypeFun,
		ajaxPost:true,
		beforeSubmit : function(curform) {
			shangchuan();
			return false;
		},
		beforeCheck : function(){
			checkCEName();
			return true;
		},
	});
	
	$("#memberForm3").Validform({
		btnSubmit : "#editCommon", 
		tiptype:tipTypeFun,
		ajaxPost:true,
		beforeSubmit : function(curform) {
			edit();
			return false;
		},
		beforeCheck : function(){
			checkCEName();
			return true;
		},
	});
	
	$("#btncomm").click(leastChooseOneName,checkCEName);
	$("#eNameFirst").blur(checkCEName);
	$("#cName").blur(checkCEName);
	$("#idInfo").on("blur","input[name='身份证']",
		function(){
			var a = $("#memberForm2").Validform().check(false,".card");
			var a1 = $(".card").val();
			if(JM.isNull(a1)){
				a = false;
			}
			var b = $("#memberForm3").Validform().check(false,"#cardValues");
			var b1 = $("#cardValues").val();
			if(JM.isNull(b1)){
				b = false;
			}
			if(a){
				shengFZ();
			}else{
				$("#memberForm2").find("#gender").val("");
				$("#memberForm2").find("#birth").val("");
			}
			if(b){
				XGshengFZ();
			}else{
				$("#memberForm3").find("#gender").val("");
				$("#memberForm3").find("#birth").val("");
			}
		}	
	);
	
	//添加证件按钮
	$(function(){
        var str=$('<div class="lst clearfix"><span class="title"><i class="icon-minus-circle"></i><span>中文名</span><em class="icon-angle-right"></em></span><label><input type="input" placeholder="请输入证件号码"><p class="Validform_checktip" style="display:inline;"></p></label></div>');
        $('#idselect').change(function(e){
            if($(this).val()){
               var name=$('option[value="'+$(this).val()+'"]',this).html();//身份证
               if(name == '身份证'){
            	   $(str).find('input').attr("datatype","sf");
            	   $(str).find('input').attr("errormsg","请输入正确的15位或18位的证件号码");
            	   $(str).find('input').attr("class","card");
               }
               if(name == '护照'){
            	   $(str).find('input').attr("datatype","*");
               }
               //各种正则表达式?
               
               $(str).find('span span').html(name);
               
               $(str).find('input').attr('id',$(this).find("option:selected").attr("name"));//id值
               
               $(str).find('input').attr('name',name);
               
               $('#idInfo').append($(str).clone());//复制功能
               //已选择的证件，在添加按钮上就不存在了
               $('option[value="'+$(this).val()+'"]').hide();
               
               $("#errorPage").hide();
               
               $(this).find('option').eq(0).attr('selected','selected'); 
            }
        });
        //点击减号消除所选证件
        $('#idInfo').on('tap','.title i',function(){
            $(this).parent().parent().empty();
            var cardType = $(this).next().text();
            var a = $("#idInfo").next().find("option[value='"+ cardType +"']").val();
            $("#idInfo").next().find("option[value='"+ cardType +"']").show();
        });
        $('span[for="idselect"]').on('tap',function(){
            $('#idselect').trigger('onfocus');
        });
    });
});

function sftg(){
	var sfzq = $("#memberForm3").Validform().check(false,"#cardValues");
//	return sfzq;
	alert(sfzq);
	if(sfzq){
			return true;
		}
	return false;
	
}

function checkCName(){
	var cName = $("#cName").val();
	var regName = /^[\u4e00-\u9fa5]{2,5}$/;
	if (!regName.test(cName)) {
		$("#namePage").html("请输入正确信息");
		$("#namePage").show();
		return false;
	}else{
		$("#namePage").hide();
	}
	return true;
}

function checkENameL(){
	var eNameLast = $("#eNameLast").val();
	var regeName = /^[A-Za-z]+$/;
	if(!regeName.test(eNameLast)){
		$("#eNamePage1").html("请输入正确信息");
		$("#eNamePage1").show();
		return false;
	}else{
		$("#eNamePage1").hide();
	}
	return true;
}

function checkENameF(){
	var eNameFirst = $("#eNameFirst").val();
	var regeName = /^[A-Za-z]+$/;
	if(!regeName.test(eNameFirst)){
		$("#eNamePage2").html("请输入正确信息");
		$("#eNamePage2").show();
		return false;
	}else{
		$("#eNamePage2").hide();
	}
	return true;
}
function leastChooseOneName(){
	var cName = $("#cName").val();
	var eNameLast = $("#eNameLast").val();
	var eNameFirst = $("#eNameFirst").val();
	var eName = eNameFirst + " " + eNameLast;
	if((cName == "" || cName == null || cName == undefined) && (eName == " " || eName == null || eName == undefined)){
		$("#namePage").html("必须填写中文名或者英文名其中一个");
		$("#namePage").show();
		return false;
	}else{
		$("#namePage").hide();
		$("#eNamePage1").hide();
		$("#eNamePage2").hide();
	}
	return true;
}

function checkCEName(){
	var cName = $("#cName").val();
	var eNameLast = $("#eNameLast").val();
	var eNameFirst = $("#eNameFirst").val();
	var eName = eNameFirst + " " + eNameLast;
	var CName = cName == "" || cName == null || cName == undefined;
	var EName = eName == " " || eName == null || eName == undefined;
	if(!CName || !EName){
		if(!CName){
			checkCName();
		}else if(!EName){
			if(checkENameF() && checkENameL()){
				$("#namePage").hide();
			}
		}else{
			if(checkCName() && checkENameF() && checkENameL()){
				$("#namePage").hide();
			}
		}
		return true;
	}else{
		$("#namePage").html("必须填写中文名或者英文名其中一个");
		$("#namePage").show();
		return false;
	}
}
function shengFZ(){
	var certifications = $("#idInfo .lst input");
	var types = $("#idInfo .lst .title span");
	var typelength = certifications.length;
	for(var i=0;i<typelength;i++){
		var certification = $(certifications[i]);
		var type = $(types[i]).text();
		var value = certification.val();
		if(value.length == 18 && value != null){
			if(type == '身份证'){
				var sex = value.substring(16,17);
				var sex1 = parseInt(sex);
				var gender;
				if(sex1 % 2 == 0){
					gender = '女';
				}else{
					gender = '男';
				}
				var bir = value.substring(6,14);
				var year = bir.substring(0,4);
				var month = bir.substring(4,6);
				var day = bir.substring(6,8);
				var birth = year + "-" + month + "-" + day;
				$("#birth").val(birth);
				$("#gender").val(gender);
			}
		}
		
	}
}
function XGshengFZ(){
	var certifications = $("#idInfo").find("input[id='cardValues']");
	var types = $("#idInfo .lst .title span");
	var typelength = certifications.length;
	for(var i=0;i<typelength;i++){
		var certification = $(certifications[i]);
		var type = $(types[i]).text();
		var value = certification.val();
		if(value.length == 18 && value != null){
			if(type == '身份证'){
				var sex = value.substring(16,17);
				var sex1 = parseInt(sex);
				var gender;
				if(sex1 % 2 == 0){
					gender = '女';
				}else{
					gender = '男';
				}
				var bir = value.substring(6,14);
				var year = bir.substring(0,4);
				var month = bir.substring(4,6);
				var day = bir.substring(6,8);
				var birth = year + "-" + month + "-" + day;
				$("#birth").val(birth);
				$("#gender").val(gender);
			}
		}
		
	}
}
/**
 * 增加常用旅客
 */
function shangchuan(){
	var cName = $("#cName").val();
	var eNameLast = $("#eNameLast").val();
	var eNameFirst = $("#eNameFirst").val();
	var eName = eNameFirst + " " + eNameLast;
	var phone = $("#phone").val();
	var gender = $("#gender").val();
	var birth = $("#birth").val();
	
	var certifications = $("#idInfo .lst input");
	var types = $("#idInfo .lst .title span");
	var typelength = certifications.length;
	var typeArr = new Array();
	var valueArr = new Array();
	for(var i=0;i<typelength;i++){
		var certification = $(certifications[i]);
		var type = $(types[i]).text();
		var value = certification.val();
		typeArr[i] = type;
		valueArr[i] = value;
	}
	
	if(yesOrNo()){
		$.ajax({
			url : "commonTraveller_addTraveller.action",
			type : "post",
			data : {
				"chaName" : cName,
				"engName" : eName,
				"phone" : phone,
				"typeList" : typeArr.join(','),
				"valueList": valueArr.join(','),
				"gender" : gender,
				"birth" : birth,
			},
			dataType : "json",
			success : function(data) {
				if (data.status == 'y') {
					var parentId = JSON.parse(data.info).id;
					$.ajax({
						url:"commonTravellerCard_add.action",
						type:"post",
						dataType:"json",
						data:{"valueList":valueArr.join(','),"typeList":typeArr.join(','),"parentId":parentId},
						success:function(data){
							if(data.status == 'y'){
								JM.alert(data.info, 2000, function(){JM.goUrl($("#manageLink").attr("href"))});
							}
						}
					});
				} else {
					alert(data.info);
				}
			},
			error : function() {
				alert("页面出错了");
			}
		});
	}
}
/**
 * 修改常用旅客
 */
function edit(){
	var cName = $("#cName").val();
	var eNameLast = $("#eNameLast").val();
	var eNameFirst = $("#eNameFirst").val();
	var eName = eNameFirst + " " + eNameLast;
	var phone = $("#phone").val();
	var gender = $("#gender").val();
	var birth = $("#birth").val();
	
	var certifications = $("#idInfo .lst label input");
	var types = $("#idInfo .lst .title span");
	var typelength = certifications.length;
	var typeArr = new Array();
	var valueArr = new Array();
	for(var i=0;i<typelength;i++){
		var certification = $(certifications[i]);
		var type = $(types[i]).text();
		var value = certification.val();
		typeArr[i] = type;
		valueArr[i] = value;
	}
//	alert(valueArr.join(","));
	//身份id
	var cardIdcations = $("#idInfo").find("input[id='cardId']");
	var cardIdArr = new Array();
	for(var i=0;i<cardIdcations.length;i++){
		var cardId = $(cardIdcations[i]).val();
		cardIdArr[i] = cardId;
	}
	//身份id的父id
	var travellerId = $("#travellerId").val();
//	alert(travellerId);
	if(yesOrNo()){
		$.ajax({
			url:"commonTraveller_xiugai.action",
			type:"post",
			dataType:"json",
			data:{
				"chaName" : cName,
				"engName" : eName,
				"phone" : phone,
				"typeList" : typeArr.join(','),
				"valueList": valueArr.join(','),
				"gender" : gender,
				"birth" : birth,
				"id" : travellerId,
			},
			success:function(data){
				if(data.status == 'y'){
					$.ajax({
						url:"commonTravellerCard_edit.action",
						type:"post",
						dataType:"json",
						data:{
							"valueList":valueArr.join(','),
							"typeList":typeArr.join(','),
							"parentId":travellerId,
							"idList" : cardIdArr.join(','),
						},
						success:function(data){
							if(data.status == 'y'){
								JM.alert(data.info, 2000, function(){JM.goUrl($("#manageLink").attr("href"))});
							}else{
								alert(data.info);
							}
						}
					});
				}
			}
		});
	}
}
/**
 * 判断是否还有必须填写的信息
 * @returns {Boolean}
 */
function yesOrNo(){
	var card = true;
	var yz = 0;
	var idInfo = $("#idInfo").children();
	if(idInfo.children().length < 1){
		$("#errorPage").html("点击添加按钮填写证件信息");
		$("#errorPage").show();
		card = false;
	}
	var cName = $("#cName").val();
	var eNameLast = $("#eNameLast").val();
	var eNameFirst = $("#eNameFirst").val();
	var eName = eNameFirst + " " + eNameLast;
	var phone = $("#phone").val();
	var gender = $("#gender").val();
	var birth = $("#birth").val();
	var memberId = "";
	var CName = cName == "" || cName == null || cName == undefined;
	var EName = eName == " " || eName == null || eName == undefined;
	var Phone = phone == "" || phone == null || phone == undefined;
	if(CName && EName){
		yz = leastChooseOneName();
	}
	if(!CName || !EName){
		if(!CName){
			yz = checkCName() * card;
		}else if(!EName){
			yz = checkENameL() * checkENameF() * card;
		}else{
			yz = checkCName() * checkENameL() * checkENameF() * card;
		}
	}
	if(yz == 0){
		return false;
	}else{		
		return true;
	}
}
