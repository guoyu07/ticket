function iWillActivate(){var e=$("#channelCustomerInfoLoginId"),t=$("#idCard"),a=$("#flightNo"),i=$("#useTime");return JM.isNullByJQuery(e)?(e.focus(),alert("请输入渠道客户账号"),!1):JM.isNullByJQuery(t)?(alert("请输入身份证号码"),t.focus(),!1):JM.isNullByJQuery(i)?(alert("请输入使用时间"),i.focus(),!1):JM.isNullByJQuery(a)?(alert("请输入航班号"),a.focus(),!1):void(confirm("是否确认激活")&&$.ajax({type:"POST",dataType:"text",url:"/employeeInfoWap_iWillActivate.action",data:$("#iWillActivateForm").serialize(),success:function(e){JM.SUCCESS==e?(alert("激活成功"),window.location.reload()):$("#activateResult").html(e)}}))}function searchUnusedCode(){var e=$("#channelCustomerInfoLoginId");return JM.isNullByJQuery(e)?(e.focus(),!1):void $.ajax({type:"POST",dataType:"text",url:"/employeeInfoWap_getUnusedCodeByChannelCutomerInfo.action",data:"channelCustomerInfoLoginId="+e.val(),success:function(e){$("#serviceCodeList").html(e)}})}$(function(){$("#searchUnusedCodeBtn").on("tap",searchUnusedCode),$("#iWillActivateBtn").on("tap",iWillActivate)});