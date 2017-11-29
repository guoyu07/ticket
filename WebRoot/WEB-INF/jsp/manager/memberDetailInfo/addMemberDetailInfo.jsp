<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/memberDetailInfo.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>新增会员详细信息</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									会员id：
								</td>
								<td>
									<input id="member_id" name="member_id" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写会员id</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									性别：
								</td>
								<td>
									<input id="sex" name="sex" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写性别</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									护照信息：
								</td>
								<td>
									<input id="passport" name="passport" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写护照信息</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									国籍：
								</td>
								<td>
									<input id="nationality" name="nationality" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写国籍</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									台胞证：
								</td>
								<td>
									<input id="MTP" name="MTP" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写台胞证</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									企业性质：
								</td>
								<td>
									<input id="enterpriseProperty" name="enterpriseProperty" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写企业性质</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									公司名称：
								</td>
								<td>
									<input id="companyNAme" name="companyNAme" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写公司名称</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									政治面貌：
								</td>
								<td>
									<input id="politicalOutLook" name="politicalOutLook" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写政治面貌</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									头衔：
								</td>
								<td>
									<input id="honor" name="honor" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写头衔</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									职级：
								</td>
								<td>
									<input id="rank" name="rank" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写职级</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									职位：
								</td>
								<td>
									<input id="occupation" name="occupation" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写职位</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									收入：
								</td>
								<td>
									<input id="income" name="income" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写收入</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									籍贯：
								</td>
								<td>
									<input id="nativePlace" name="nativePlace" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写籍贯</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									家庭住址：
								</td>
								<td>
									<input id="homeAddress" name="homeAddress" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写家庭住址</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									年龄：
								</td>
								<td>
									<input id="age" name="age" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写年龄</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									生日：
								</td>
								<td>
									<input id="birthday" name="birthday" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写生日</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									学历：
								</td>
								<td>
									<input id="education" name="education" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写学历</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									学校：
								</td>
								<td>
									<input id="school" name="school" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写学校</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									专业：
								</td>
								<td>
									<input id="major" name="major" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写专业</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									星座：
								</td>
								<td>
									<input id="constellation" name="constellation" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写星座</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									属相：
								</td>
								<td>
									<input id="zodiac" name="zodiac" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写属相</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									婚配：
								</td>
								<td>
									<input id="marriage" name="marriage" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写婚配</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									血型：
								</td>
								<td>
									<input id="bloodType" name="bloodType" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写血型</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									身高：
								</td>
								<td>
									<input id="height" name="height" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写身高</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									体重：
								</td>
								<td>
									<input id="weight" name="weight" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写体重</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									邮政编码：
								</td>
								<td>
									<input id="zipcode" name="zipcode" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写邮政编码</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									疾病史：
								</td>
								<td>
									<input id="diseaseHistory" name="diseaseHistory" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写疾病史</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									过敏原：
								</td>
								<td>
									<input id="anaphylactogen" name="anaphylactogen" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写过敏原</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									健康状况：
								</td>
								<td>
									<input id="healthStatus" name="healthStatus" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写健康状况</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									兴趣点：
								</td>
								<td>
									<input id="hobby" name="hobby" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写兴趣点</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									车牌号：
								</td>
								<td>
									<input id="plateNumber" name="plateNumber" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写车牌号</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									微信号：
								</td>
								<td>
									<input id="weChatId" name="weChatId" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写微信号</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									微博号：
								</td>
								<td>
									<input id="weiboNumber" name="weiboNumber" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写微博号</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									人人网账号：
								</td>
								<td>
									<input id="renrenAccount" name="renrenAccount" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写人人网账号</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									百度账号：
								</td>
								<td>
									<input id="baiduAccount" name="baiduAccount" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写百度账号</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									搜狗账号：
								</td>
								<td>
									<input id="sogouAccount" name="sogouAccount" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写搜狗账号</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									个性签名：
								</td>
								<td>
									<input id="personalitySignature" name="personalitySignature" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写个性签名</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									紧急联系人姓名：
								</td>
								<td>
									<input id="emergencyContactName" name="emergencyContactName" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写紧急联系人姓名</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									紧急联系人性别：
								</td>
								<td>
									<input id="emergencyContactSex" name="emergencyContactSex" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写紧急联系人性别</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									紧急联系人电话：
								</td>
								<td>
									<input id="emergencyContactPhone" name="emergencyContactPhone" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写紧急联系人电话</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									紧急联系人年龄：
								</td>
								<td>
									<input id="emergencyContactAge" name="emergencyContactAge" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写紧急联系人年龄</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									紧急联系人身份证：
								</td>
								<td>
									<input id="emergencyContactIDCard" name="emergencyContactIDCard" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写紧急联系人身份证</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									紧急联系人头衔：
								</td>
								<td>
									<input id="emergencyContacthonor" name="emergencyContacthonor" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写紧急联系人头衔</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									紧急联系人邮箱：
								</td>
								<td>
									<input id="emergencyContactEmail" name="emergencyContactEmail" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写紧急联系人邮箱</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									与紧急联系人关系：
								</td>
								<td>
									<input id="emergencyContactRelation" name="emergencyContactRelation" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写与紧急联系人关系</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									常旅客卡数据：
								</td>
								<td>
									<input id="visitorCardData" name="visitorCardData" class="my_input" ignore="ignore"/>
									
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