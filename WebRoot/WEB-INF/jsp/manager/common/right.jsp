<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="site_right">
	<%@ include file="top.jsp" %>
	<div id="viewbox" class="site_right_box">
		<!--常用模块1-2-->
		<table class="my_table50 margin_top10">
			<tr class="text_align_left my_table_top">
				<td>
					系统信息
				</td>
			</tr>
			<tr>
				<td>
					当前版本：Version 1.0 Beta
				</td>
			</tr>
			<tr>
				<td>
					上次登录时间： 2015-10-01 09:29:35
				</td>
			</tr>
			<tr>
				<td>
					系统开发者：昆明长水国际机场信息中心
				</td>
			</tr>
		</table>
		<table class="my_table50 margin_top10">
			<tr class="text_align_left my_table_top">
				<td>
					订单处理
				</td>
			</tr>
			<tr>
				<td>
					当前信息总数：
					<font color="#CC0000">0</font>人
					<span><a href="/order_manage.action">详情</a> </span>
				</td>
			</tr>
			<tr>
				<td>
					今日新增会员总数：
					<font color="#CC0000">0</font>人
					<span><a href="/order_manage.action">详情</a> </span>
				</td>
			</tr>
			<tr>
				<td>
					今日便捷登机人数：
					<font color="#CC0000">100</font>人
					<span><a href="/member_manage.action">详情</a> </span>
				</td>
			</tr>
		</table>
		<table class="my_table100 margin_top10">
			<tr class="text_align_left my_table_top">
				<td>
					信息审核
				</td>
			</tr>
			<tr>
				<td>
					<textarea name="content" class="my_textarea"></textarea>
				</td>
			</tr>
		</table>
		<!--常用模块1-2结束-->
	</div>
</div>