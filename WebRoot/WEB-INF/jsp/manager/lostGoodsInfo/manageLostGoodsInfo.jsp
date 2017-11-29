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
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理遗失物品信息</td>
					    </tr>
					     <tr style="text-align: center"><td colspan="10">
							<form action="${actionName }_searchs.action?versionFlag=site" method="post">
								物品名称:<input type="text" name="name" class="my_input easyui-combobox"/>&nbsp;&nbsp;&nbsp;&nbsp;
								物品编号:<input type="text" name="goodsNumber" class="my_input easyui-combobox"/>&nbsp;&nbsp;&nbsp;&nbsp;
								物品颜色:<select id="color" name="color" class="my_select">
										<option value="">请选择颜色</option>
										<ticket:systemCommon type="goodsColorList"/>
										<s:if test="#request.goodsColorList != null">
											<s:iterator id="goodsColor" value="#request.goodsColorList">
												<option value="${goodsColor.id }">${goodsColor.name }</option>
											</s:iterator>
										</s:if>
									</select><br/>
							  	物品类别:
							  		<select id="type_id" name="type_id" class="my_select" datatype="*">
										<option value="">请选择类别</option>
										<ticket:systemCommon type="goodsTypeList"/>
										<s:if test="#request.goodsTypeList != null">
											<s:iterator id="goodsType" value="#request.goodsTypeList">
												<option value="${goodsType.id }">${goodsType.name }</option>
											</s:iterator>
										</s:if>
									</select>&nbsp;&nbsp;&nbsp;&nbsp;
							  	<%--<input name="type_id" class="my_input easyui-combobox"
							       data-options="url:'systemDictionary_querySubByParentValue.action?value=service_code_type',valueField:'id',textField:'name'"/>
							       &nbsp;&nbsp;&nbsp;&nbsp;
							  	遗失位置:--%>遗失位置<select id="pickPosition_id" name="pickPosition_id" class="my_select">
										<option value="">请选择位置</option>
										<ticket:systemCommon type="lostPositionList"/>
										<s:if test="#request.lostPositionList != null">
											<s:iterator id="lostPosition" value="#request.lostPositionList">
												<option value="${lostPosition.id }">${lostPosition.name }</option>
											</s:iterator>
										</s:if>
									</select>
							       <br/>
							  	创建时间:<input name="startTime" type="text" onclick="new WdatePicker();"/>
							  				&nbsp;&nbsp;~&nbsp;&nbsp;
							  			<input name="endTime" type="text" onclick="new WdatePicker();"/>
							  			<br/>
					  			<input type="submit" value="查询"/>
					  			<input type="reset" value="重置"/>
						  	</form>
						  	<script type="text/javascript">
						  		$(function(){
						  			
						  			$('input[name="start"]').val("${param.start }");
						  			$('input[name="end"]').val("${param.end }");
						  			$('input[name="type_id"]').val("${param.type.name }");
						  			$('input[name="state_id"]').val("${param.state.value }");
						  			$('input[name="startTime"]').val("${param.startTime }");
						  			$('input[name="endTime"]').val("${param.endTime }");
						  		});
						  	</script>
						  </td></tr>
					    <tr  class="text_align_left">
					      <td>
					      	<!-- <input type="text" value="请输入描述关键词检索物品信息" 
					      		onFocus="if(value==defaultValue){value='';this.style.color='#000'}" 
					      		onBlur="if(!value){value=defaultValue;this.style.color='#999'}"
								class="my_input" id="keyword" name="keyword"  style="width:320px;height:23px">
					      	<input type="button" id="searchBtn" value="检索" class="my_input" style="width:55px;height: 33px;text-align: left" /> -->
					      <span>
					      	<a href="#" class="batchAuditBtn" value="${actionName}CheckBox" entityName="${entityName}">批量审核</a>
					      	<a href="#" class="batchLogicDeleteBtn" value="${actionName}CheckBox" entityName="${entityName}">批量删除</a>
					      	<a><input id="isChecked" type="checkbox"/></a>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
							<table class="my_table100">
							  <tr class="my_table_top1">
							  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
							    <td width="80">创建时间</td>
							    <td width="80">拾取时间</td>
							    <td width="80">物品名称</td>
							    <td width="80">物品类型</td>
							    <td width="80">物品颜色</td>
							    <td width="80">物品编号</td>
							    <td width="80">拾取位置</td>
							    <td width="80">拾取者姓名</td>
							    <td width="80">拾取者电话</td>
							    <td width="150" class="text_align_center">操作</td>
							  </tr>
							  <s:if test="pageModule != null && pageModule.totalCount > 0">
							  	<s:iterator id="c" value="pageModule.pageList" status="st">
							  		<tr>
							  			<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
									    <td><s:date name="#c.status.createTime" format="yyyy-MM-dd"/></td>
									    <td><s:date name="#c.pickUpTime" format="yyyy-MM-dd"/></td>
									    <td>${c.name }</td>
									    <td>
									    	<ticket:systemCommon type="systemDictionaryObj" value="${c.type_id}"/>
									    	${systemDictionaryObj.name }
									    </td>
									    <td>
									    	<ticket:systemCommon type='goodsColor' value="${c.color }"/>
									    	${goodsColor.name}
									    </td>
									    <td>${c.goodsNumber }</td>
									    <td>
									    	<ticket:systemCommon type='lostPosition' value="${c.pickPosition_id}"/>
									    	${lostPosition.name }
									    </td>
									    <td>${c.pickerName }</td>
									    <td>${c.pickerPhone }</td>
									    <td><span>
									    	<a href="#" class="logicDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
									    	<a href="/${actionName}_detail.action?id=${c.id }&versionFlag=${versionFlag}">详细信息</a>
									    	<a href="/receiveRecord_add.action?lostGoods_id=${c.id }&versionFlag=${versionFlag}">发放</a>
									    	
									    </span></td>
									 </tr>
							  	</s:iterator>
							  </s:if>
							  <s:else>
							  	 <tr>
								    <td colspan="11">${noDataMessage }</td>
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