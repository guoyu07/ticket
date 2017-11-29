<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/surveyQuestionnaire.js"></script>
	<script type="text/javascript">
		$(function(){
			d = new dTree('d');
			${deptHtml}
			$("#showTree").html(d + "");
		});
	</script>
  <body>
    <div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<input type="hidden" id="operationFlag" name="operationFlag" value="1"/>
					<input type="hidden" id="sendFlag" name="sendFlag" value="dept"/>
					<input type="hidden" id="id" name="id" value="${id }"/>
					<table class="my_table100_tree margin_top10">
						<tr class="text_align_left my_table_top">
							<td>选择发送问卷的对象</td>
							<td></td>
						</tr>
						<tr>
							<td>
								<span>
									 <input type="button" onclick="checkByDept();" value="根据员工部门选择"/>
									 <input type="button" onclick="checkByChannelType();" value="根据客户类型选择"/>
									 
									 <%--<a href="javascript: d.openAll();">展开所有</a> 
									 <a href="javascript: d.closeAll();">关闭所有</a>--%>
								</span>
							</td>
							<td></td>
						</tr>
						<tr id="selectByDept">
							<td>
								<a href="javascript: d.openAll();">展开所有</a> 
								<a href="javascript: d.closeAll();">关闭所有</a>
								<div id="showTree" style="padding-top:10px; padding-left: 0px; padding-right: 0px;"></div>
							</td>
							<td><input type="checkbox" id="selectAllDeptCheckBox" name="selectAllDeptCheckBox"/>全选/取消选中<br />
							</td>
						</tr>
						<tr id="selectByChannelType" style="display: none" >
							<ticket:systemCommon type="channelTypeList"/>
							<td>
								<s:if test="#request.channelTypeList != null">
								<s:iterator id="channelType" value="#request.channelTypeList">
									<input type="checkbox" value="${channelType.id }" name="selectType" /> <font style="font-size: 12">${channelType.name }</font><br />
								</s:iterator>
							</s:if>
							</td>
							<td><input type="checkbox" id="selectAllTypeCheckBox" name="selectAllTypeCheckBox"/>全选/取消选中</td>
						</tr>
					</table>
					<div class="my_table_list_r">
					    <input id="submitBtn" type="submit" onclick="sendQuestionnaire();" value="确认发送" class="btn_submit">
					    <input id="" onclick="javascript:history.go(-1)" type="submit" value="返回" class="btn_remove margin_left20">
					    <a id="addLink" href="/${actionName}_sendToObject.action?versionFlag=${versionFlag}&"></a>
					    <a id="manageLink" href="/${actionName}_manage.action?versionFlag=${versionFlag}"></a>
					</div>
				</div>
			</div>
		</div>
  </body>
</html>
