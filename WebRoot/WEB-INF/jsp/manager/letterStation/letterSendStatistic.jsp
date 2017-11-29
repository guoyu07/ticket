<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<script type="text/javascript" src="/manager/js/surveyQuestionnaire.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>站内信发送统计</span>
					</div>
					<form>
						<table class="my_table100 margin_top10">
							<tr  class="text_align_left">
						      <td><span>
						     	站内信标题:${letterStation.title } 
						     	总发送数量:<s:property value="stationLetterReadLogService.countAllCountByLetterId(letterStation.id)"/>
						     	已阅读数量:<s:property value="stationLetterReadLogService.countReadCountByLetterId(letterStation.id)"/>
						      </span></td>
						    </tr>
						    <tr class="text_align_left my_table_top">
								<td>发送给部门的站内信记录</td>
							</tr>
						    <tr>
						    	<td>
								  <table class="my_table100">
									  <tr class="my_table_top1">
									    <td width="180">发送时间</td>
									    <td width="80">发送数量</td>
									    <td>发送部门</td>
									  </tr>
									  <s:if test="deptSendRecords != null && deptSendRecords.size != 0">
									  	<s:iterator id="sendRecordByDept" value="deptSendRecords" status="st">
									  		<tr>
									  			<td><s:date name="#sendRecordByDept.status.createTime" format="yyyy-MM-dd hh:mm:ss"/></td>
											    <td>${sendRecordByDept.sendCount }</td>
											    <td>
											    	<ticket:systemCommon type="departmentInfoObj" value="${sendRecordByDept.object_id }"/>
									    			${departmentInfoObj}
											    </td>
											 </tr>
									  	</s:iterator>
									  </s:if>
									  <s:else>
									  	 <tr>
										    <td colspan="7">${noDataMessage }</td>
										  </tr>
									  </s:else>
									</table>
								</td>
					    	</tr>
					    	<tr class="text_align_left my_table_top">
								<td>发送给渠道客户的站内信记录</td>
							</tr>
					    	<tr><td>
					    		<table class="my_table100">
								  <tr class="my_table_top1">
								  	<td width="180">发送时间</td>
								    <td width="80">发送数量</td>
								    <td>渠道客户分类</td>
								  </tr>
								  <s:if test="channelTypeSendRecords != null && channelTypeSendRecords.size !=0">
								  	<s:iterator id="sendRecordByChannelType" value="channelTypeSendRecords" status="st">
								  		<tr>
								  			<td><s:date name="#sendRecordByChannelType.status.createTime" format="yyyy-MM-dd hh:mm:ss"/></td>
										    <td>${sendRecordByChannelType.sendCount }</td>
										    <td>
										    	<ticket:systemCommon type="channelTypeObj" value="${sendRecordByChannelType.object_id }"/>
										   		${channelTypeObj.name }
										    </td>
										 </tr>
								  	</s:iterator>
								  </s:if>
								  <s:else>
								  	 <tr>
									    <td colspan="7">${noDataMessage }</td>
									  </tr>
								  </s:else>
								</table>
					    	</td></tr>
						</table>
						<div class="my_table_list_r">
						    <input onclick="javascript:history.go(-1);" type="button" value="返回" class="btn_remove margin_left20">
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>