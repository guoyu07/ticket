/**
 * @descript 站内信阅读日志专用JS
 * @author   HiSay
 * @datetime 2016-05-09 14:18:24
 */
 /*************************************************/

/**
 * @description 初始化站内信阅读日志方法参数
 * @author HiSay
 * @datetime 2016-05-09 14:18:24
 */
jQuery(function(){
	editStationLetterReadLogInital();
	
	setLetterRead();
	
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2016-05-09 14:18:24
 */
function editStationLetterReadLogInital() {
	//write code here.
}

function setLetterRead(){
	$(".setLetterIsRead").unbind().bind("click",function(){
		var id = $(this).attr("id");
		$.ajax({
			type:'POST',
			dataType:'json',
			url:'/stationLetterReadLog_setLetterRead.action',
			data:'id='+id,
			success:function(val){
				if(val.status == JM.YES){
					JM.alert("设置成功!", 500);
					window.location.reload();
					
				}
			}
		});
	});
}