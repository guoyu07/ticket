<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<form action="${actionName }_details.action" method="get" style="text-align: center; margin-top: 110px;">
					  	统计范围:
					  	<select name="region">
					  		<s:iterator var="region" value="@com.ticket.enumer.ReportRegion@values()">
					  		<option value="${region.value }">${region.text }</option>
					  		</s:iterator>
					  	</select>
					  	<input type="button" value="导出excel" id="exportExcel" onclick="location.href='${actionName }_details.action?operationFlag=excel'"/>
				  	</form>
				  	<iframe id="iframe" frameborder="0" onLoad="TuYou.iFrameAutoHeight('iframe')" src="${actionName }_details.action?operationFlag=html"></iframe>
				  	<script type="text/javascript">
				  		$('select[name="region"]').change(function(){
				  			
				  			$('#exportExcel').attr('onclick', "location.href=" + actionName + "_details.action?operationFlag=excel&region=" + $(this).val());
				  			$('#iframe').attr('src', actionName + "_details.action?operationFlag=html&region=" + $(this).val());
				  		});
				  	</script>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>