<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/youZanGoodsInfo.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>有赞商品绑定商家</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											商品ID：
										</td>
										<td>
											${youZanGoodsInfo.num_iid }
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											商品名称：
										</td>
										<td>
											${youZanGoodsInfo.name }
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											商家：
										</td>
										<td>
											<input type="checkbox" class="selectCheckBoxAllChks" objectChkName="arriveCitys"/>全选<br/>
												<ticket:common type="businessInfoLists"/>
												<s:if test="#request.businessInfoLists != null">
													<s:iterator value="#request.businessInfoLists" var="city">
														<input type="checkbox" name="arriveCitys" value="${city.id }"/>${city.name }
													</s:iterator>
												</s:if>
										</td>
									</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" value="取消" class="btn_remove margin_left20">
						    <a id="addLink" href="/${actionName}_add.action?versionFlag=${versionFlag}"></a>
						    <a id="manageLink" href="/${actionName}_manage.action?versionFlag=${versionFlag}"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>