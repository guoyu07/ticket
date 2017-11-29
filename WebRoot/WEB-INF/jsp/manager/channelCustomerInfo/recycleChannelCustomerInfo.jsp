<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/channelCustomerInfo.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理渠道客户信息</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td><span><a href="#">批量还原</a><a href="#">批量删除</a></span></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								  
								    <td width="20"><input name="" type="checkbox" value=""></td>
								    <td width="80">创建时间</td>
								     <td width="80">客户名称</td>
								    <td width="80">用户名</td>
								    <td width="80">渠道分类</td>
								    <td width="100">开户日期</td>
								    <td width="80">联系人</td>
								    <td width="80">联系电话</td>
								    <td width="80">开户金额</td>
								    <td width="80">可用余额</td>
								    <td width="80">优惠政策</td>
								    <td width="80">支付方式</td>
								    <td width="150" class="text_align_center">操作</td>
								  </tr>
								 <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<tr>
								  			<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
										    <td><s:date name="#c.status.createTime" format="yyyy-MM-dd"/></td>
										    <td>${c.name }</td>
										    <td>${c.loginId }</td>
										    
										    <td>
										    <ticket:systemCommon type="channelTypeObj" value="${c.channelTypeId}"/>
									    	${channelTypeObj.name }
										    </td>
										    
										    <td><s:date name="#c.openAccountDate" format="yyyy-MM-dd"/></td>
										    <td>${c.contact }</td>
										    <td>${c.contactPhone }</td>
										    <td>${c.openAccountMoney }</td>
										    <td>${c.balance }</td>
										    <td>
										     <ticket:systemCommon type="favouredPolicyObj" value="${c.favouredPolicyId}"/>
									    	${favouredPolicyObj.name }
										    </td>
										    <td>${c.payWay }</td>
										    <td><span>
										    	<a href="#" class="realDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
												<a href="#" class="restoreBtn" entityName="${entityName}" value="${c.id }">还原</a>
												</span>
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
					  </table>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>