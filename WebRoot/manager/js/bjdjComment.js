/**
 * @descript 便捷登机评论表专用JS
 * @author   HiSay
 * @datetime 2015-10-23 15:24:09
 */
 /*************************************************/

/**
 * @description 初始化便捷登机评论表方法参数
 * @author HiSay
 * @datetime 2015-10-23 15:24:09
 */
jQuery(function(){
	editBjdjCommentInital();
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-10-23 15:24:09
 */
function editBjdjCommentInital() {
	//write code here.
}

function feedback(id){
	
	 easyDialog.open({
			container : {
				header : '填写信息',
				content : text ? text + '<textarea id="-feedback-" name="feedback"></textarea>'
						: '<textarea id="-feedback-" name="feedback"></textarea>',
				yesFn : function(){
					
			  		var feedback = $('#-feedback-').val();
			  		$.post(actionName + "_feedback.action" , 
			  			{
			  				feedback : feedback,
			  				id : id
			  			}, 
			  			function(json){
			  				
			  				if(json.status == 'y'){
			  					
			  					JM.alert(json.info);
			  					JM.reload();
			  				}else{
			  					
			  					JM.alert(json.info);
			  				}
			  			}
			  		);
				},
				noFn : true
			},
			overlay : true,
			fixed : false,
			drag: false
		});
}