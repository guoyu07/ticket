SystemDictionary = {};

SystemDictionary.setValueByName = function(id, name){
	
	$.post(
		'systemDictionaryTemplate_getValueByName.action', 
		{name : name}, 
		function(data){
	
			value = data;
			flag = true;
			var font = $('#'+id).text(data);
			$('#'+id).text(data);
		}
	);
};