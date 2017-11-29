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
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >商家[${businessInfo.name }]绑定有赞商品</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td>
					      根据商品名称检索：
					       <input type="text" value="请输入商品名称关键词" 
					      		onFocus="if(value==defaultValue){value='';this.style.color='#000'}" 
					      		onBlur="if(!value){value=defaultValue;this.style.color='#999'}"
								class="my_input" id="keyword" name="keyword"  style="width:320px;height:23px">
					      	<input type="button" id="searchBtn" value="检索" class="my_input" style="width:55px;height: 33px;text-align: left" />
					      	<input type="hidden" value="${businessInfo.id }" id="business"/>
					      <span>
					      <a href="#" class="batchAuditBtn" value="${actionName}CheckBox" entityName="${entityName}">批量绑定</a>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
								    <td width="80">编号</td>
								    <td width="80">商品ID</td>
								    <td width="80">商品名称</td>
								    <td width="80">商品别名</td>
								    <td width="80">商品详情url</td>
								    <td width="80">商品价格</td>
								    <td width="80">商品销量</td>
								    <!-- <td>上架状态</td> -->
								    <td width="150" class="text_align_center">操作</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<tr>
								  			<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
										    <td>${st.count }</td>
										    <td>${c.num_iid }</td>
										    <td>${c.name }</td>
										    <td>${c.alias }</td>
										    <td>${c.detail_url }</td>
										    <td>${c.price }</td>
										    <td>${c.sold_num }</td>
										    <%-- <td>${c.listing }</td> --%>
										    <td>
										    	<a href="javascript:;" class="bind" objValue="${c.id }">绑定</a>
										    </td>
										 </tr>
								  	</s:iterator>
								  </s:if>
								  <s:else>
								  	 <tr>
									    <td colspan="100">${noDataMessage }</td>
									  </tr>
								  </s:else>
								</table>
					        </td>
					    </tr>
					  </table>
					  <%@ include file="../common/page.jsp"%>
					<%-- <div class="my_table_list_nav">
						<span>绑定有赞商品</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" value="${businessInfo.id }"/>
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											商家：
										</td>
										<td>
											${businessInfo.name }
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											有赞商品：
										</td>
										<td>
											<input type="checkbox" class="selectCheckBoxAllChks" objectChkName="ids"/>全选<br/>
												<ticket:common type="youZangGoodsList"/>
												<s:if test="#request.youZangGoodsList != null">
													<s:iterator value="#request.youZangGoodsList" var="goods">
														<input type="checkbox" name="ids" value="${goods.id }"/>${goods.name }
													</s:iterator>
												</s:if>
										</td>
									</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" value="取消" class="btn_remove margin_left20">
						    <a id="addLink" href="/${actionName}_add.action?versionFlag=${versionFlag}"></a>
						    <a id="manageLink" href="/businessInfo_manage.action?versionFlag=${versionFlag}"></a>
						</div>
					</form> --%>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>