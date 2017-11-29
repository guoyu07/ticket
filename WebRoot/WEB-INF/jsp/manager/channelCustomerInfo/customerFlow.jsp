<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/employeeOutVisit.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理客户流向</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td><span>
					      	<input type="text" value="请输入公司名称或用户名关键词" 
					      		onFocus="if(value==defaultValue){value='';this.style.color='#000'}" 
					      		onBlur="if(!value){value=defaultValue;this.style.color='#999'}"
								class="my_input" id="keywordByFlow" name="keywordByFlow"  style="width:280px;height:23px">
							<input type="button" id="searchFlowBtn" value="搜索"/>
							<select name="customerFlow_id" id="customerFlow_id" class="my_input">
								<option></option>
							</select>
					      </span></td>
					    </tr>
					    <tr>
					      <td id="customerFlow">
					      	
					      </td>
					    </tr>
					  </table>
					  <%@ include file="../common/page.jsp"%>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>