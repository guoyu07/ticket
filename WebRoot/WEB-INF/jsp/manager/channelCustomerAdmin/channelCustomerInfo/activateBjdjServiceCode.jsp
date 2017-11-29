<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="/WEB-INF/jsp/manager/common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/rechargeRecord.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="/WEB-INF/jsp/manager/common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="/WEB-INF/jsp/manager/common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>激活服务码</span>
					</div>
					<form action="/channelCustomerAdmin_activateBjdjServiceCode.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									服务码：
								</td>
								<td>
									${bjdjServiceCode.code}
									<input type="hidden" name="bjdjServiceCodeId" value="${bjdjServiceCode.id}"/>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									会员：
								</td>
								<td>
									<input id="searchKeyword" type="text" name="keyword" class="my_input"/>
									<input id="searchKeywordBtn" type="button" value="搜索"/>
									<select name="memberId" id="memberId">
										<s:iterator id="m" value="members">
											<option value="${m.id}">${m.phone}-${m.realName}</option>
										</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									航班号：
								</td>
								<td>
									<input id="flightNo" name="flightNo" class="my_input"/>
									<p class="Validform_checktip" style="display:inline;"> 请输入航班号</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									航班起飞时间：
								</td>
								<td>
									<input id="useTime" name="useTime" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'})"  class="my_input"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写使用期限</p>
								</td>
							</tr>
						</table>
						<div class="my_table_list_l">
						    <input id="submitBtn" type="submit" value="确认激活" class="btn_submit">
						    <input id="returnBtn" type="button" value="取消" class="btn_remove margin_left20">
						    <a id="addLink" href="/channelCustomerAdmin_activateBjdjServiceCode.action?bjdjServiceCodeId=${bjdjServiceCodeId}"></a>
						    <a id="manageLink" href="/channelCustomerAdmin_myBjdjServiceCode.action"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<script>
			$(function (){
				$("#searchKeywordBtn").on("click",searchMember);
				function searchMember(){
					var keyword = $("#searchKeyword");
					if(JM.isNullByJQuery(keyword)){
						$("#searchKeyword").focus();
						return false;
					}
					$.ajax({
						type:'POST',
						dataType:'text',
						url:'/channelCustomerAdmin_getMemberListByKeyword.action',
						data:'keyword='+JM.encodeByJQuery(keyword),
						success:function(data){
							if(!JM.isNull(data)){
								$("#memberId").empty();
								data = JSON.parse(data);
								for(var i=0;i<data.length;i++){
									$("#memberId").append($("<option>").val(data[i].id).text(data[i].phone+"-"+data[i].realName));
								}
							}
						}
					});
				}
			})
		</script>
		<%@ include file="/WEB-INF/jsp/manager/common/popUp.jsp"%>
	</body>
</html>