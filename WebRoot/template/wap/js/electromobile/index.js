$(function(){
	
	$agree = $('#agree');
	$confirmForm = $('#confirmForm');
	$submitBtn = $('#submitBtn');
});

var $agree;
var $confirmForm;
var $submitBtn;
function submit(){
	
	if(!$agree.prop('checked')){
		
		JM.alert('请阅读并同意条款',2000);
	}else{
		
		$submitBtn.trigger('click');
	}
}