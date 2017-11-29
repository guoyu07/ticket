$(function(){
	
	$('input[name="code"]').change(function(){
		
		$('#refundAmount').text($('input[name="code"]:checked').size() * price);
	});
});