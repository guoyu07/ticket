<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/businessInfo.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理商家信息</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td>
					      	根据关键词检索：
					       <input type="text" value="请输入商家名称关键词" 
					      		onFocus="if(value==defaultValue){value='';this.style.color='#000'}" 
					      		onBlur="if(!value){value=defaultValue;this.style.color='#999'}"
								class="my_input" id="keyword" name="keyword"  style="width:320px;height:23px">
					      	<input type="button" id="searchBtn" value="检索" class="my_input" style="width:55px;height: 33px;text-align: left" />
					      	<br />
					      	&nbsp;&nbsp;&nbsp;根据条件检索：
					      	<ticket:systemCommon type="businessTypeList"/>
					      	<select name="businessType" id="businessType" class="my_select" currentValue="${businessType_id }">
								<option value="">请选择商家类别</option>
								<s:if test="#request.businessTypeList != null">
									<s:iterator id="busType" value="#request.businessTypeList">
										<option value="${busType.id }">${busType.name }</option>
									</s:iterator>
								</s:if>
							</select>
							<ticket:systemCommon type="systemDictionayList" value="businessInfo_lc" />
							<select name="lc" id="lc" class="my_select" currentValue="${lc }">
								<option value="">请选择楼层</option>
								<s:iterator id="d" value="#request.systemDictionayList">
								<option value="${d.id}">${d.name}</option>
								</s:iterator>
							</select>
							<ticket:systemCommon type="systemDictionayList" value="businessInfo_wz"/>
							<select name="wz" id="wz" class="my_select" currentValue="${wz }">
								<option value="">请选择位置</option>
								<s:iterator id="w" value="#request.systemDictionayList">
								<option value="${w.id}">${w.name}</option>
								</s:iterator>
							</select>
							<input type="button" id="searchByConditions" value="检索" class="my_input" style="width:55px;height: 33px;text-align: left" />
					    	<br />
					      <span>
					      	<a href="#" class="batchAuditBtn" value="${actionName}CheckBox" entityName="${entityName}">批量审核</a>
					      	<a href="#" class="batchLogicDeleteBtn" value="${actionName}CheckBox" entityName="${entityName}">批量删除</a>
					      	<a href="#" class="batchCommendBtn" value="${actionName}CheckBox" entityName="${entityName}">批量推荐</a>
					      	<a href="#" class="batchHotBtn" value="${actionName}CheckBox" entityName="${entityName}">批量热门</a>
					      	<a><input id="isChecked" type="checkbox"/></a>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
							<table class="my_table100">
							  <tr class="my_table_top1">
							  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
							    <td width="80">创建时间</td>
							    <td width="100">商家名称</td>
							    <td width="80">商家类别</td>
							    <td width="80">主营</td>
							    <td>关联有赞商品</td>
							    <td>有赞商品销量</td>
							    <td width="120">营业时间</td>
							    <td width="280" class="text_align_center">操作</td>
							  </tr>
							  <s:if test="pageModule != null && pageModule.totalCount > 0">
							  	<s:iterator id="c" value="pageModule.pageList" status="st">
							  		<tr>
							  			<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
									    <td><s:date name="#c.status.createTime" format="yyyy-MM-dd"/></td>
									    <td>${c.name }</td>
									    <td>
									    	<ticket:systemCommon type="airportBusinessTypeObj" value="${c.businessType_id}"/>
									    	${airportBusinessTypeObj.name }
									    </td>
									    <td>${c.mainSale }</td>
									    <td>
									    	<ticket:common type="bindYouZanList" value="${c.id }"/>
									    	<s:if test="#request.bindYouZanList">
									    		<s:iterator value="#request.bindYouZanList" var="goods">
									    			${goods.goodsInfo.name }/
									    		</s:iterator>
									    	</s:if>
									    </td>
									    <td>${c.youZanSales }</td>
									    <td>${c.businessHours }</td>
									    <td><span>
									    	<a href="#" class="logicDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
									    	<a href="/${actionName}_edit.action?id=${c.id }&versionFlag=${versionFlag}">编辑</a>
									    	<a href="/businessEventBind_add.action?businessInfo_id=${c.id }&versionFlag=${versionFlag}">绑定活动</a>
									    	<a href="/businessEventBind_manage.action?id=${c.id }&versionFlag=${versionFlag}">查看活动</a>
									    	<a href="/friendlyLink_add.action?businessInfo_id=${c.id }&versionFlag=${versionFlag}">添加友情链接</a>
									    	<a href="/friendlyLink_manage.action?businessInfo_id=${c.id }&versionFlag=${versionFlag}">管理友情链接</a>
									    	<ticket:common type="bindYouZanList" value="${c.id }"/>
									    	<%-- <s:if test="#request.bindYouZanList != null"> --%>
									    		<a href="/bindYouZan_edit.action?id=${c.id}">绑定有赞商品</a>
									    	<%-- </s:if>
									    	<s:else>
									    		<a href="/bindYouZan_add.action?id=${c.id}">绑定有赞商品</a>
									    	</s:else> --%>
									    </span></td>
									 </tr>
							  	</s:iterator>
							  </s:if>
							  <s:else>
							  	 <tr>
								    <td colspan="10">${noDataMessage }</td>
								  </tr>
							  </s:else>
							</table>
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