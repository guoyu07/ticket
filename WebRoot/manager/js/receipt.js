/**
 * @descript 收据发票专用JS
 * @author   HiSay
 * @datetime 2015-11-17 17:10:15
 */
 /*************************************************/

/**
 * @description 初始化收据发票方法参数
 * @author HiSay
 * @datetime 2015-11-17 17:10:15
 */
jQuery(function(){
	editReceiptInital();
	moneyToRMB1();
	$(".submitApply").on("click",submitApply);
	$(".auditPass").on("click",auditPass);
	$(".auditNoPass").on("click",auditNoPass);
	$(".stateSuccess").on("click",stateSuccess);
	$("#searchapplyClass_id").on("change",searchReceipt);
	$("#amountOfMoney").on("keyup",moneyToRMB);
});

/**
 * 人民币转换
 * @return
 */
function moneyToRMB(){
	$("#money").val(atoc($(this).val()));
}

function moneyToRMB1(){
	if(!JM.isNull($("#amountOfMoney").val())){
		$("#money").val(atoc($("#amountOfMoney").val()));
	}else{
		$("#money").val(atoc(0));
	}
}
/**
 * 钱币转换
 * @param num
 * @return
 */
function atoc(numberValue){  
	if (!/^\d*(\.\d*)?$/.test(numberValue)) { alert("Number is wrong!"); return "Number is wrong!"; } 
	var numberValue=new String(Math.round(numberValue*100)); // 数字金额  
//	alert(numberValue);
	var chineseValue=""; // 转换后的汉字金额  
	var String1 = "零壹贰叁肆伍陆柒捌玖"; // 汉字数字  
	var String2 = "万仟佰拾亿仟佰拾万仟佰拾元角分"; // 对应单位  
	var len=numberValue.length; // numberValue 的字符串长度  
	var Ch1; // 数字的汉语读法  
	var Ch2; // 数字位的汉字读法  
	var nZero=0; // 用来计算连续的零值的个数  
	var String3; // 指定位置的数值  
	if(len>15){  
		alert("超出计算范围");  
	return "";  
	}  
	
	if (numberValue==0){  
		chineseValue = "零元整";  
	return chineseValue;  
	}  
	String2 = String2.substr(String2.length-len, len); // 取出对应位数的STRING2的值  
//	alert(String2);
	for(var i=0; i<len; i++){  
		String3 = parseInt(numberValue.substr(i, 1),10); // 取出需转换的某一位的值  
		if ( i != (len - 3) && i != (len - 7) && i != (len - 11) && i !=(len - 15) ){  
			if ( String3 == 0 ){  
				Ch1 = "";  
				Ch2 = "";  
				nZero = nZero + 1;  
			}  
			else if ( String3 != 0 && nZero != 0 ){  
				Ch1 = "零" + String1.substr(String3, 1);  
				Ch2 = String2.substr(i, 1);  
				nZero = 0;  
			}  
			else{  
				Ch1 = String1.substr(String3, 1);  
				Ch2 = String2.substr(i, 1);  
				nZero = 0;  
			}  
		}  
		else{ // 该位是万亿，亿，万，元位等关键位  
			if( String3 != 0 && nZero != 0 ){  
				Ch1 = "零" + String1.substr(String3, 1);  
				Ch2 = String2.substr(i, 1);  
				nZero = 0;  
			}  
			else if ( String3 != 0 && nZero == 0 ){  
				Ch1 = String1.substr(String3, 1);  
				Ch2 = String2.substr(i, 1);  
				nZero = 0;  
			}  
			else if( String3 == 0 && nZero >= 3 ){  
				Ch1 = "";  
				Ch2 = "";  
				nZero = nZero + 1;  
			}  
			else{  
				Ch1 = "";  
				Ch2 = String2.substr(i, 1);  
				nZero = nZero + 1;  
			}  
			if( i == (len - 11) || i == (len - 3)){ // 如果该位是亿位或元位，则必须写上  
				Ch2 = String2.substr(i, 1);  
			}  
		}  
		chineseValue = chineseValue + Ch1 + Ch2;  
		}  
		if ( String3 == 0 ){ // 最后一位（分）为0时，加上“整”  
			chineseValue = chineseValue + "整";  
		}  
		return chineseValue;  
	}  

/**
 *  搜索发票
 * @return
 */
function searchReceipt(){
	var applyClass_id = $(this).val();
	var leftIndex = $("#leftIndexInt").val();
	window.location.href='/receipt_manage.action?versionFlag=site&leftIndex='+leftIndex+'&applyClass_id='+applyClass_id;
}

/**
 * 提交申请
 * @return
 */
function submitApply(){
	var id = $(this).attr("attrValue");
	if(confirm("是否提交申请")){
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/receipt_submitApply.action',
			data:'id='+id,
			success:function(data){
				if(JM.SUCCESS == data){
					window.location.reload();
				}
			}
		});
	}
}

/**
 * 审核通过收据
 * @return
 */
function auditPass(){
	var id = $(this).attr("attrValue");
	if(confirm("是否审核通过")){
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/receipt_auditPass.action',
			data:'id='+id,
			success:function(data){
				if(JM.SUCCESS == data){
					window.location.reload();
				}
			}
		});
	}
}

/**
 * 驳回申请
 * @return
 */
function auditNoPass(){
	var id = $(this).attr("attrValue");
	if(confirm("是否驳回申请")){
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/receipt_auditNoPass.action',
			data:'id='+id,
			success:function(data){
				if(JM.SUCCESS == data){
					window.location.reload();
				}
			}
		});
	}
}

/**
 * 同意开票
 * @return
 */
function stateSuccess(){
	var id = $(this).attr("attrValue");
	if(confirm("是否同意开票")){
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/receipt_stateSuccess.action',
			data:'id='+id,
			success:function(data){
				if(JM.SUCCESS == data){
					window.location.reload();
				}
			}
		});
	}
}

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-11-17 17:10:15
 */
function editReceiptInital() {
	//write code here.
}