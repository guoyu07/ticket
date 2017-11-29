$(function(){
        $('.modify_btn').click(xiugai);
        
        $("#delCommon").click(del);
        
        $("#eNameFirst").blur(checkCEName);
    	$("#cName").blur(checkCEName);
    	
    	$(".idInfo").on("blur","input[name='身份证']",
    			function(){
    				var a = $("#memberForm2").Validform().check(false,".card");
    				var a1 = $(".card").val();
    				if(JM.isNull(a1)){
    					a = false;
    				}
    				var b = $("#memberForm3").Validform().check(false,"#cardValues");
    				var b1 = $("#cardValues").val();
    				//var b2 = $(".card").val();
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
        
        $(function(){
            var str=$('<div class="lst clearfix"><label><span><i class="push_icon"></i> 中文名</span><label><input type="input" placeholder="请输入证件号码"><p class="Validform_checktip" style="display:inline;"></p></div>');
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
                   
                   $(str).find('span').html('<i class="push_icon"></i>'+name);
                   
                   $(str).find('input').attr('id',$(this).find("option:selected").attr("name"));//id值
                   
                   $(str).find('input').attr('name',name);
                   
                   $('.idInfo').append($(str).clone());//复制功能
                   //已选择的证件，在添加按钮上就不存在了
                   $('option[value="'+$(this).val()+'"]').hide();
                   
                   $("#errorPage").hide();
                   
                   $(this).find('option').eq(0).attr('selected','selected'); 
                }
            });
            //点击减号消除所选证件
            $('.idInfo').on('click','label i',function(){
                $(this).parent().parent().parent().empty();
                var cardType = $(this).parent().text();
                /*var a = $("#add").next().find("option[value='"+ cardType +"']").val();*/
                $("option[value='"+ cardType +"']").show();
            });
            $('span[for="idselect"]').on('tap',function(){
                $('#idselect').trigger('onfocus');
            });
        });
    });
	function add(){
		$("#errorPage").val("");
		$("#eNamePage2").val("");
		$("#eNamePage1").val("");
		$("#namePage").val("");
		$(".Validform_checktip").text("");
		$("#memberForm3").attr("id","memberForm2");
		$("#cName").val("");
		$("#eNameLast").val("");
		$("#eNameFirst").val("");
		$("#idselect").empty();
		$(".idInfo").empty();
		$("#phone").val("");
		$("#gender").val("");
		$("#birth").val("");
		$(".btn").find("button").eq(0).hide();
		$(".btn").find("button").eq(1).hide();
		$(".btn").find("button").eq(2).show();
		
		$("#memberForm2").Validform({
    		btnSubmit : "#addCommon", 
    		datatype:{"sf" : yzsfz},
    		tiptype:function(msg, o, cssctl){
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
    			shangchuan();
    			return false;
    		},
    		beforeCheck : function(){
    			checkCEName();
    			return true;
    		},
    	});
		
		$.getJSON("pcCommonTraller_add.action",function(data){
			if(data.status == 'y'){
				var json = JSON.parse(data.info);
				var cardType = json.type;
				var addCardType = '<option value="">添加证件</option>';
				for(var i=0;i<cardType.length;i++){
					addCardType += '<option value="'+cardType[i].name+'" name="'+cardType[i].id+'">'+ cardType[i].name +'</option>';
				}
				$("#idselect").html(addCardType);
				
				$.slide({'id':'modify_dialog','content':$('.modify_dialog').get(0),lock:true});
			}
		});
	}
    function xiugai(){
    	$("#errorPage").val("");
		$("#eNamePage2").val("");
		$("#eNamePage1").val("");
		$("#namePage").val("");
		$(".Validform_checktip").text("");
    	$("#memberForm2").attr("id","memberForm3");
		var id = $(this).parent().find("input").val();
		$("#idselect").empty();
		$(".idInfo").empty();
		$(".btn").find("button").eq(0).show();
		$(".btn").find("button").eq(1).show();
		$(".btn").find("button").eq(2).hide();
		
		$("#memberForm3").Validform({
    		btnSubmit : "#editCommon", 
    		datatype:{"sf" : yzsfz},
    		tiptype:function(msg, o, cssctl){
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
    		beforeCheck : function(){
    			checkCEName();
    			return true;
    		},
    	});
		
		$.ajax({
			url:"pcCommonTraller_editCommon.action",
			type:"post",
			dataType:"json",
			data:{"id":id},
			success:function(data){
				if(data.status == "y"){
					var json = JSON.parse(data.info);
					if(!JM.isNull(json.traveller.chaName)){
						$("#cName").val(json.traveller.chaName);
					}
					if(!JM.isNull(json.traveller.engName)){
						var index = json.traveller.engName.indexOf(" ");
						var eNameF = json.traveller.engName.substr(0,index);
						var eNameL = json.traveller.engName.substr(index+1);
						$("#eNameLast").val(eNameL);
						$("#eNameFirst").val(eNameF);
					}
					$("#travellerId").val(json.traveller.id);
					$("#phone").val(json.traveller.phone);
					$("#gender").val(json.traveller.gender);
					$("#birth").val(json.traveller.birth);
					var cardList = json.list;
					//填充已有的证件信息
					var optionType = '';
					for(var i=0;i<cardList.length;i++){
						if(cardList[i].cardType == "身份证"){
							optionType += '<div class="lst clearfix"><input type="hidden"  value="'+cardList[i].id+'" id="cardId"/><label>'+
							'<span>'+
							'<i class="push_icon"></i>'+
							cardList[i].cardType +
							'</span>'+
							'<input type="text" name="'+cardList[i].cardType+'" value="'+ cardList[i].cardValue +'" id="cardValues" datatype="sf" errormsg="请输入正确的15位或18位的证件号码" placeholder="请输入证件号码"/>'+
							'<p class="Validform_checktip" style="display:inline;"></p>'+
							'</label><div>';
						}else{
							optionType += '<div class="lst clearfix"><input type="hidden" value="'+cardList[i].id+'" id="cardId"/><label>'+
							'<span>'+
							'<i class="push_icon"></i>'+
							cardList[i].cardType +
							'</span>'+
							'<input type="text" name="" value="'+ cardList[i].cardValue +'" id="cardValues" datatype="*" errormsg="请输入正确的证件号码" placeholder="请输入证件号码"/>'+
							'<p class="Validform_checktip" style="display:inline;"></p>'+
							'</label><div>';
						}
					}
					$(".idInfo").html(optionType);
					
					//填充添加按钮证件类型
					var cardType = json.type;
					var addCardType = '<option value="">添加证件</option>';
					for(var i=0;i<cardType.length;i++){
						addCardType += '<option value="'+cardType[i].name+'" name="'+cardType[i].id+'">'+ cardType[i].name +'</option>';
					}
					$("#idselect").html(addCardType);
					
					for(var i=0;i<cardType.length;i++){
						for(var j=0;j<cardList.length;j++){
							var name =cardType[i].name;
							var type = cardList[j].cardType;
							if(cardType[i].name == cardList[j].cardType){
								$("#idselect").find("option[name="+cardType[i].id+"]").hide();
							}
						}
					}
					
					$.slide({'id':'modify_dialog','content':$('.modify_dialog').get(0),lock:true});
				}
			}
		});
	}
    function sftg(){
    	var sfzq = $("#memberForm3").Validform().check(false,"#cardValues");
//    	return sfzq;
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
    	var certifications = $(".idInfo .lst label input");
    	var types = $(".idInfo .lst span");
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
    	var certifications = $(".idInfo").find("input[id='cardValues']");
    	var types = $(".idInfo .lst span");
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
    	
    	var certifications = $(".idInfo .lst label input");
    	var types = $(".idInfo .lst span");
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
    			url : "pcCommonTraller_addTraveller.action",
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
    						url:"pcCommonTrallerCard_add.action",
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
    	
    	var certifications = $(".idInfo .lst label input");
    	var types = $(".idInfo .lst span");
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
//    	alert(valueArr.join(","));
    	//身份id
    	var cardIdcations = $(".idInfo").find("input[id='cardId']");
    	var cardIdArr = new Array();
    	for(var i=0;i<cardIdcations.length;i++){
    		var cardId = $(cardIdcations[i]).val();
    		cardIdArr[i] = cardId;
    	}
    	//身份id的父id
    	var travellerId = $("#travellerId").val();
//    	alert(travellerId);
    	if(yesOrNo()){
    		$.ajax({
    			url:"pcCommonTraller_xiugai.action",
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
    						url:"pcCommonTrallerCard_edit.action",
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
    	var idInfo = $(".idInfo").children();
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
    
    function yzsfz(gets,obj,curform,regxp){
        //参数gets是获取到的表单元素值，obj为当前表单元素，curform为当前验证的表单，regxp为内置的一些正则表达式的引用;
        var rgp1 = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
        var rgp2 = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
        if(rgp1.test(gets) || rgp2.test(gets)){
            if(gets.length==18){
                var idCardWi=new Array( 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ); //将前17位加权因子保存在数组里
                var idCardY=new Array( 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ); //这是除以11后，可能产生的11位余数、验证码，也保存成数组
                var idCardWiSum=0; //用来保存前17位各自乖以加权因子后的总和
                for(var i=0;i<17;i++){
                    idCardWiSum+=gets.substring(i,i+1)*idCardWi[i];
                }

                var idCardMod=idCardWiSum%11;//计算出校验码所在数组的位置
                var idCardLast=gets.substring(17);//得到最后一位身份证号码

                //如果等于2，则说明校验码是10，身份证号码最后一位应该是X
                if(idCardMod==2){
                    if(idCardLast=="X"||idCardLast=="x"){
                        return true;
                    }else{
                        return false;
                    }
                }else{
                    //用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码
                    if(idCardLast==idCardY[idCardMod]){
                       return true;
                    }else{
                        return false;
                    }
                }
            }
            if(gets.length==15){
                var year =  gets.substring(6,8);
                var month = gets.substring(8,10);
                var day = gets.substring(10,12);
                var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));
                // 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法
                if(temp_date.getYear()!=parseFloat(year)||temp_date.getMonth()!=parseFloat(month)-1||temp_date.getDate()!=parseFloat(day)){
                    return false;
                }else{
                    return true;
                }
            }
        }else{
            return false;
        }

    }
    
    function del(){
    	var id = $("#travellerId").val();
    	var url = $("#manageLink").attr("href");
    	$.ajax({
    		url:"pcCommonTraller_del.action",
    		type:"post",
    		dataType:"json",
    		data:{"id":id},
    		success:function(data){
    			if(data.status == 'y'){
    				$.ajax({
    					url:"pcCommonTrallerCard_del.action",
    					type:"post",
    					dataType:"json",
    					data:{"parentId":id},
    					success:function(data){
    						if(data.status == 'y'){
    							JM.alert(data.info, 2000, function(){JM.goUrl(url)});
    						}else{
    							JM.alert(data.info, 2000);
    						}
    					}
    				});
    			}else{
    				JM.alert(data.info, 2000);
    			}
    		}
    	});
    }
