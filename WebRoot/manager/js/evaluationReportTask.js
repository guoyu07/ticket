/**
 * @descript 评论报表任务专用JS
 * @author   HiSay
 * @datetime 2016-02-04 21:40:28
 */
 /*************************************************/
jQuery(function(){
	
	var tail = $('.tail').clone();
	$('select[name="property"]').change(function(){
		
		var item = $(this);
		if(item.val() == 'normal'){
			
			$('.tail').remove();
		}else{
			
			$('.head').after(tail);
		}
	});
	
	$('select[name="property"]').trigger('change');
	
});