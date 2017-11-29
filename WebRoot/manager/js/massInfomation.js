/**
 * @descript 群发信息专用JS
 * @author   HiSay
 * @datetime 2016-02-03 20:46:58
 */
 /*************************************************/

/**
 * @description 初始化群发信息方法参数
 * @author HiSay
 * @datetime 2016-02-03 20:46:58
 */
jQuery(function(){
	editMassInfomationInital();
	sendInfo();
	content = createEditor("content");
	initUploadify("picture", 1, "fileQueue", true, false, "preImage", globalVersionFlag);
	
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2016-02-03 20:46:58
 */
function editMassInfomationInital() {
	//write code here.
}

function sendInfo(){
	$(".sendInfo").unbind().bind("click",function(){
		var id = $(this).attr("value");
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/massInfomation_sendInfo.action',
			data:'id='+id,
			success:function(data){
				JM.alert(data.info);
			}
		});
	});
}