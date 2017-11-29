<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/lostGoodsInfo.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>遗失物品信息详情</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									物品类型：
								</td>
								<td>
									<label>
										<ticket:systemCommon type="systemDictionaryObj" value="${lostGoodsInfo.type_id }"/>
								    	${systemDictionaryObj.name }
								    </label>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									物品名称：
								</td>
								<td>
									<label>${lostGoodsInfo.name}</label>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									物品ID：
								</td>
								<td>
									<label>${lostGoodsInfo.goodsId}</label>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									物品编号：
								</td>
								<td>
									<label>${lostGoodsInfo.goodsNumber}</label>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									物品颜色：
								</td>
								<td>
									<label><ticket:systemCommon type='goodsColor' value="${lostGoodsInfo.color }"/>
									    	${goodsColor.name}</label>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									拾取者姓名：
								</td>
								<td>
									<label>${lostGoodsInfo.pickerName}</label>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									拾取者电话：
								</td>
								<td>
								<label>${lostGoodsInfo.pickerPhone}</label>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									拾取时间：
								</td>
								<td>
									<s:date name="lostGoodsInfo.pickUpTime" format="yyyy-MM-dd HH:mm"/>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									拾取位置：
								</td>
								<td>
									<label>
										<ticket:systemCommon type="goodsLostPositionObj" value="${lostGoodsInfo.pickPosition_id}"/>
										${goodsLostPositionObj.name }
									</label>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									上交时间：
								</td>
								<td>
									<label>
										${lostGoodsInfo.commitTime}
									</label>      
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									库存位置：
								</td>
								<td>
									<label>
										${lostGoodsInfo.stockPosition}
									</label>      
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									物品描述：
								</td>
								<td>
									<label>
										${lostGoodsInfo.goodsDescript}
									</label>      
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									备注：
								</td>
								<td>
									<label>${lostGoodsInfo.remark}</label>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									接收人：
								</td>
								<td>
									<label>${lostGoodsInfo.operator_id}</label>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									其他信息一：
								</td>
								<td>
									<label>${lostGoodsInfo.otherInfoOne}</label>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									其他信息二：
								</td>
								<td>
									<label>${lostGoodsInfo.otherInfoTwo}</label>
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="returnBtn" type="button" value="返回" class="btn_remove margin_left20">
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