/**
 * @descript 调查问题专用JS
 * @author   HiSay
 * @datetime 2015-11-12 14:53:43
 */
 /*************************************************/

/**
 * @description 初始化调查问题方法参数
 * @author HiSay
 * @datetime 2015-11-12 14:53:43
 */
jQuery(function(){
	editSurveyQuestionInital();
	
	JM.selectSelect("questionNo");
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-11-12 14:53:43
 */
function editSurveyQuestionInital() {
	
	 var type= $('#typeValue').val();
	 $("input:radio[name='type']").eq(type).attr("checked",true);
	 
	 
	 var questionTypeValue= $('#questionTypeValue').val();
	 
	 //alert(questionTypeValue);
	 $("input:radio[name='questionType']").eq(questionTypeValue).attr("checked",true);
	//write code here.
}