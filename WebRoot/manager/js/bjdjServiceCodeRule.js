/**
 * @description 初始化服务码表方法参数
 * @author tuyou
 */
$(function(){
	
	$('#type_id').change(function(){
			
			loadRule($(this).val());
	});
});

/**
 * 加载服务码生成规则
 * @param type_id
 */
function loadRule(type_id){
	
	$.post('bjdjServiceCode_loadRule.action', {type_id : type_id}, function(data){
		
		for(var i = 0; i < data.length; i++){
			
			var c = data.charAt(i);
			$('input[name="rule"]').eq(i).val(c);
		}
	});
}