<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="common/head.jsp" %>
		<title>在线购票 - 云南省昆明市长水机场</title>
	</head>
	<body>
		<%@ include file="common/top.jsp" %>

		<%@ include file="common/nav.jsp" %>

		<%@ include file="common/search.jsp" %>
		<div class="banner_11"></div banner>
		<div class="place w980 mt30">
			当前位置:
			<a href="/airportPc.action">首页</a>
			<a href="#">在线购票</a>
		</div place w980 mt30>

		<div class="w980 mt30">
			<div class="online_article bhh">
				<table cellspacing="0" cellpadding="0" class="h2">
					<tr>
						<th width="100">
							行程
						</th>
						<th width="230">
							起降时间/机场
						</th>
						<th width="30">
							经停
						</th>
						<th width="170">
							航空公司
						</th>
						<th width="120">
							票价
						</th>
						<th width="100">
							机建/燃油
						</th>
					</tr>
					<tr align="center">
						<td rowspan="2">
							昆明-西安
						</td>
						<td>
							2015-09-11 22：40 长水机场
						</td>
						<td rowspan="2">
							无
						</td>
						<td>
							海南航空股份有限公司
						</td>
						<td>
							<b>￥424</b>/成人
						</td>
						<td rowspan="2">
							￥50/￥0
						</td>
					</tr>
					<tr align="center">
						<td>
							2015-09-12 00：40 咸阳机场
						</td>
						<td>
							HU7850
						</td>
						<td>
							经济舱
						</td>
					</tr>
					<tr align="center">
						<td colspan="6" rowspan="2">
							<!-- 婊中婊 -->
							<table cellspacing="0" cellpadding="0" class="h4" frame="border"
								rules="all">
								<tr>
									<td rowspan="2" align="center">
										<a href="javascript:;">退改签费用标准 >></a>
									</td>
									<td>
										类型
									</td>
									<td>
										退票扣费
									</td>
									<td>
										改期加收手续费
									</td>
									<td>
										签转
									</td>
								</tr>
								<tr>
									<td>
										成人票
									</td>
									<td>
										只退机建和燃油
									</td>
									<td>
										不可改期
									</td>
									<td>
										不可签转
									</td>
								</tr>
							</table>
							<!-- 婊中婊 -->
						</td>
					</tr>
				</table>

				<table cellspacing="0" cellpadding="0" class="mt30">
					<tr>
						<th>
							<h3>
								乘机人信息
							</h3>
						</th>
					</tr>
					<tr>
						<td>
							姓名
							<input name="" type="text" style="width: 100px;" value="中文/英文" />
							<select name="">
								<option value="成人">
									成人
								</option>
							</select>
							证件号码
							<select name="">
								<option value="身份证">
									身份证
								</option>
							</select>
							<input name="" type="text" style="width: 150px;"
								value="请准确填写以保证登机" />
						</td>
					</tr>
					<tr>
						<td align="right">
							<a href="javascript:void(0);">代金券/航意险</a>
							<select name="">
								<option value="￥70/份×1代金券+保险套餐">
									￥70/份×1代金券+保险套餐
								</option>
							</select>
							<br />
							<br />
							<a href="javascript:void(0);">延误险</a>
							<select name="">
								<option value="￥30/份×1保额最高300元">
									￥30/份×1保额最高300元
								</option>
							</select>
						</td>
					</tr>
				</table>


				<table cellspacing="0" cellpadding="0" class="mt30">
					<tr>
						<th>
							<h3>
								联系人信息
							</h3>
						</th>
					</tr>
					<tr>
						<td>
							姓名
							<input name="" type="text" style="width: 100px;" value="中文/英文" />
							手机
							<input name="" type="text" style="width: 150px;"
								value="通知出票状态和航班短信" />
							电子邮箱
							<input name="" type="text" style="width: 200px;" />
							<br />
							<font style="padding-left: 275px;"><a href="javascript:;">使用境外手机号</a>
							</font>
						</td>
					</tr>
				</table>

				<table cellspacing="0" cellpadding="0" class="mt30">
					<tr>
						<th>
							<h3>
								报销信息
							</h3>
						</th>
					</tr>
					<tr>
						<td>
							<input name="" type="checkbox" value="" />
							需要报销凭证（含：旅行产品发票、保险发票、快递费发票）
						</td>
					</tr>
				</table>

				<dt>
					<b>订单总额：</b><font style="font-size: 30px; color: #00AAFF;">￥574</font>
					<br />
					（含机票：￥424，机建：￥50，航意险：￥30，延误险：￥30，接送机代金券包：￥40）
					<br />
					<small style="margin-top: 10px;"><a href="javascript:;"><img
								src="images/dd.gif" />
					</a>
					</small>
				</dt>
			</div JPYZ3_article bhh>

			<%@include file="common/rightMenu.jsp" %>
		</div w980 mt30>
			<%@ include file="common/left.jsp" %>
		<%@ include file="common/bottom.jsp" %>
	</body>
</html>
