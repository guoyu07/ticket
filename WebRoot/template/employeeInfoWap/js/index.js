function myTextView(t){$.ajax({type:"POST",dataType:"text",async:!1,url:"/employeeInfoWap_myTextView.action",data:"id="+t,success:function(t){$(".notebook_info_dialog").html(t)}})}function myTextList(){$.ajax({type:"POST",dataType:"text",url:"/employeeInfoWap_myTextList.action",data:"",success:function(t){$("#notebook_list").html(t)}})}function myTextAdd(){var t=$("#title"),o=$("#content");return JM.isNullByJQuery(t)?(alert("请输入记事标题"),t.focus(),!1):JM.isNullByJQuery(o)?(alert("请输入记事内容"),t.focus(),!1):void(confirm("是否确认添加记事")?$.ajax({type:"POST",dataType:"text",url:"/employeeInfoWap_myTextAdd.action",data:$("#myTextForm").serialize(),success:function(t){JM.SUCCESS==t?(alert("添加成功"),window.location.reload()):(alert("添加失败"),window.location.reload())}}):window.location.reload())}$(function(){$("#addnotebook").on("tap",function(){$.do_dialog.open({msg:$(".notebook_dialog"),initBefore:function(){$("#title").val(""),$("#content").val("")}})}),$("body").on("tap","#notebook_list a",function(){var t=$(this).attr("attrValue");$.do_dialog.open({msg:$(".notebook_info_dialog"),initBefore:function(){myTextView(t)}})}),$("#myText_add_btn").on("tap",myTextAdd),myTextList()});