<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ticket" uri="/WEB-INF/classes/tags.tld" %>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%>
<table class="my_table100 margin_top10">
	<tr class="text_align_left my_table_top">
		<td>
			导入错误会员
		</td>
	</tr>
	<tr class="text_align_left">
		<td>
			<span> </span>
		</td>
	</tr>
	<tr>
		<td>
			<table class="my_table100">
				<tr class="my_table_top1">
					<td width="80">
						编号
					</td>
					<td width="80">
						用户名
					</td>
					<td width="80">
						昵称
					</td>
					<td width="80">
						手机
					</td>
					<td width="80">
						邮箱
					</td>
				</tr>
				<s:if test="pageModule != null && pageModule.totalCount > 0">
					<s:iterator id="c" value="pageModule.pageList" status="st">
						<tr>
							<td>
								${c.id}
							</td>
							<td>
								${c.loginName}
							</td>
							<td>
								${c.nickName}
							</td>
							<td>
								${c.phone}
							</td>
							<td>
								${c.email}
							</td>
						</tr>
					</s:iterator>
				</s:if>
				<s:else>
					<tr>
						<td colspan="7">
							导入会员成功
						</td>
					</tr>
				</s:else>
			</table>
		</td>
	</tr>
</table>