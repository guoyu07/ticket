<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/massInfomation.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>新增群发信息</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									信息标题：
								</td>
								<td>
									<input id="title" name="title" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写信息标题</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									短信内容：
								</td>
								<td>
									<textarea id="content" name="content" class="my_input" datatype="*" style="height: 280px"></textarea>
									<p class="Validform_checktip" style="display:inline;"> 请填写短信内容</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									图片：
								</td>
								<td>
									<div id="fileQueue"></div>
									<div style="clear: both;">
									<input type="file" id="picture" name="picture" style="width: 200px;" tabindex="1" />
									</div>
									<span id="pictureTip" style="height: 22px;"></span>
									<div style="margin: 0 auto; float: left;" id="preImage" title="点击删除"></div>
							    </td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									是否发送短信：
								</td>
								<td>
									<select name="sendSms">
										<option value="false">不发送</option>
										<option value="true">发送</option>
									</select>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									短信链接：
								</td>
								<td>
									<input id="url" name="url" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写短信链接</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									提醒时间：
								</td>
								<td>
									<input onclick="new WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'});" name="reminderTime" class="my_input"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写提醒时间</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									航班号：
								</td>
								<td>
									<input name="flightNumber" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写航班号（关注了航班才填写）</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									航班日期：
								</td>
								<td>
									<input onclick="new WdatePicker({dateFmt:'yyyy-MM-dd'});" name="flightDate" class="my_input"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写航班日期（航班号和航班日期必须同时写）</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									群发对象：
								</td>
								<td>
									头&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;像<select name="photo" class="my_select">
											<option value="" selected="selected">所有人</option>
											<option value="1">已上传</option>
											<option value="0">未上传</option>
										</select>
									昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称<select name="nickName" class="my_select" >
											<option value="" selected="selected">所有人</option>
											<option value="1">已填写</option>
											<option value="0">未填写</option>
										</select><br/>
									姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名<select name="name" class="my_select" >
											<option value="" selected="selected">所有人</option>
											<option value="1">已填写</option>
											<option value="0">未填写</option>
										</select>
									身&nbsp;&nbsp;份&nbsp;&nbsp;证<select name="idCard" class="my_select" >
											<option value="" selected="selected">所有人</option>
											<option value="1">已填写</option>
											<option value="0">未填写</option>
										</select><br/>
									护&nbsp;&nbsp;照&nbsp;&nbsp;号<select name="passport" class="my_select" >
											<option value="" selected="selected">所有人</option>
											<option value="1">已填写</option>
											<option value="0">未填写</option>
										</select>
									家庭地址<select name="homeAddress" class="my_select" >
											<option value="" selected="selected">所有人</option>
											<option value="1">已填写</option>
											<option value="0">未填写</option>
										</select><br/>
									常用地址<select name="address" class="my_select" >
											<option value="" selected="selected">所有人</option>
											<option value="1">已填写</option>
											<option value="0">未填写</option>
										</select><br/>
									生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日<select name="birthday" class="my_select" >
											<option value="" selected="selected">所有人</option>
											<option value="1">已填写</option>
											<option value="0">未填写</option>
										</select>
									个性签名<select name="underWrite" class="my_select" >
											<option value="" selected="selected">所有人</option>
											<option value="1">已填写</option>
											<option value="0">未填写</option>
										</select><br/>
									车&nbsp;&nbsp;牌&nbsp;&nbsp;号<select name="plateNumber" class="my_select" >
											<option value="" selected="selected">所有人</option>
											<option value="1">已填写</option>
											<option value="0">未填写</option>
										</select>
									公司名称<select name="companyName" class="my_select" >
											<option value="" selected="selected">所有人</option>
											<option value="1">已填写</option>
											<option value="0">未填写</option>
										</select><br/>
									学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;校<select name="school" class="my_select" >
											<option value="" selected="selected">所有人</option>
											<option value="1">已填写</option>
											<option value="0">未填写</option>
										</select>
									专&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业<select name="major" class="my_select" >
											<option value="" selected="selected">所有人</option>
											<option value="1">已填写</option>
											<option value="0">未填写</option>
										</select><br/>
									邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱<select name="email" class="my_select" >
											<option value="" selected="selected">所有人</option>
											<option value="1">已填写</option>
											<option value="0">未填写</option>
										</select>
									企业名称<select name="enterpriseName" class="my_select" >
											<option value="" selected="selected">所有人</option>
											<option value="1">已填写</option>
											<option value="0">未填写</option>
										</select><br/>
									<br /><br/>
									性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别<select name="sex" class="my_select" >
											<option value="" selected="selected">所有人</option>
											<option value="1">男</option>
											<option value="0">女</option>
										</select>
									国&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;籍<select name="nation" class="my_select" >
											<option value="">所有国籍</option>
											<ticket:common type="systemDictionaryListByParentValueOrder" value="nationality"/>
						                	<s:if test="#request.systemDictionaryListByParentValueOrder != null">
						                		<s:iterator id="systemDictionary" value="#request.systemDictionaryListByParentValueOrder">
								                   <option value="${systemDictionary.value }">${systemDictionary.value }</option>
						                		</s:iterator>
						                	</s:if>
										</select><br />
									籍&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;贯<select name="nativePlace" class="my_select" >
											<option value="">所有籍贯</option>
											<ticket:common type="systemDictionaryListByParentValueOrder" value="nationality"/>
						                	<s:if test="#request.systemDictionaryListByParentValueOrder != null">
						                		<s:iterator id="systemDictionary" value="#request.systemDictionaryListByParentValueOrder">
								                   <option value="${systemDictionary.value }">${systemDictionary.value }</option>
						                		</s:iterator>
						                	</s:if>
										</select>
									血&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型<select name="bloodType" class="my_select" >
											<option value="">所有血型</option>
											<option value="A">A</option>
											<option value="B">B</option>
											<option value="AB">AB</option>
											<option value="O">O</option>
										</select><br/>
									星&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;座<select name="constellation" class="my_select" >
											<option value="">所有星座</option>
											<option value="白羊座">白羊座</option>
											<option value="金牛座">金牛座</option>
											<option value="双子座">双子座</option>
											<option value="巨蟹座">巨蟹座</option>
											<option value="狮子座">狮子座</option>
											<option value="处女座">处女座</option>
											<option value="天秤座">天秤座</option>
											<option value="天蝎座">天蝎座</option>
											<option value="射手座">射手座</option>
											<option value="摩羯座">摩羯座</option>
											<option value="水瓶座">水瓶座</option>
											<option value="双鱼座">双鱼座</option>
										</select>
									属&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;相<select name="zodiac" class="my_select" >
											<option value="">所有属相</option>
											<option value="鼠">鼠</option>
											<option value="牛">牛</option>
											<option value="虎">虎</option>
											<option value="兔">兔</option>
											<option value="龙">龙</option>
											<option value="蛇">蛇</option>
											<option value="马">马</option>
											<option value="羊">羊</option>
											<option value="猴">猴</option>
											<option value="鸡">鸡</option>
											<option value="狗">狗</option>
											<option value="猪">猪</option>
										</select><br/>
									婚&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;配<select name="marriage" class="my_select" >
											<option value="">所有人</option>
											<option value="1">已婚</option>
											<option value="0">未婚</option>
										</select>
									兴&nbsp;&nbsp;趣&nbsp;&nbsp;点<select name="hobby" class="my_select" >
											<option value="">所有人</option>
											<option value="阅读">阅读</option>
											<option value="运动">运动</option>
											<option value="饮食">饮食</option>
											<option value="旅游">旅游</option>
											<option value="音乐">音乐</option>
											<option value="其他">其他</option>
										</select><br/>
									企业性质<select name="enterpriseProperty" class="my_select" >
											<option value="">所有人</option>
											<option value="私企">私企</option>
											<option value="国企">国企</option>
											<option value="集体所有制企业">集体所有制企业</option>
											<option value="联营企业">联营企业</option>
											<option value="三资企业">三资企业</option>
											<option value="其他企业">其他企业</option>
										</select>
									职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业<select name="occupation" class="my_select" >
											<option value="">所有人</option>
											<option value="农林牧渔业">农林牧渔业</option>
											<option value="采矿业">采矿业</option>
											<option value="制造业">制造业</option>
											<option value="电力燃气及水的生产和供应业">电力燃气及水的生产和供应业</option>
											<option value="建筑业">建筑业</option>
											<option value="交通运输仓储和邮政业">交通运输仓储和邮政业</option>
											<option value="信息传输计算机服务和软件业">信息传输计算机服务和软件业</option>
											<option value="批发和零售业">批发和零售业</option>
											<option value="住宿和餐饮业">住宿和餐饮业</option>
											<option value="金融业">金融业</option>
											<option value="房地产业">房地产业</option>
											<option value="租赁和商务服务业">租赁和商务服务业</option>
											<option value="科学研究技术服务和地质勘查业">科学研究技术服务和地质勘查业</option>
											<option value="水利环境和公共设施管理业">水利环境和公共设施管理业</option>
											<option value="居民服务和其他服务业">居民服务和其他服务业</option>
											<option value="教育">教育</option>
											<option value="卫生社会保障和社会福利业">卫生社会保障和社会福利业</option>
											<option value="文化体育和娱乐业">文化体育和娱乐业</option>
											<option value="公共管理和社会组织">公共管理和社会组织</option>
											<option value="国际组织">国际组织</option>
										</select><br/>
									月&nbsp;&nbsp;收&nbsp;&nbsp;入<select name="income" class="my_select" >
											<option value="">所有人</option>
											<option value="1000元以下">1000元以下</option>
											<option value="1001-2000元">1001-2000元</option>
											<option value="2001-3000元">2001-3000元</option>
											<option value="3001-5000元">3001-5000元</option>
											<option value="5001-8000元">5001-8000元</option>
											<option value="8001-10000元">8001-10000元</option>
											<option value="10001-20000元">10001-20000元</option>
											<option value="20001-50000元">20001-50000元</option>
											<option value="50000元以上">50000元以上</option>
										</select>
									学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;历<select name="education" class="my_select" >
											<option value="">所有人</option>
											<option value="院士">院士</option>
											<option value="博士">博士</option>
											<option value="硕士">硕士</option>
											<option value="本科">本科</option>
											<option value="专科">专科</option>
											<option value="高中">高中</option>
											<option value="初中">初中</option>
										</select><br/>
									头&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;衔<select name ="honor" class="my_select" >
											<option value="">所有人</option>
											<option value="董事长">董事长</option>
											<option value="总经理">总经理</option>
											<option value="经理">经理</option>
											<option value="储备干部">储备干部</option>
											<option value="底层员工">底层员工</option>
										</select>
									职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级<select name="rank" class="my_select" >
											<option value="">所有人</option>
											<option value="高层管理">高层管理</option>
											<option value="中层管理">中层管理</option>
											<option value="底层管理">底层管理</option>
											<option value="一线职员">一线职员</option>
										</select><br/>
									体&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;重<select name="weight" class="my_select" >
											<option value="">所有人</option>
											<option value="40kg以下">40kg以下</option>
											<option value="40kg-49kg">40kg-49kg</option>
											<option value="50kg-59kg">50kg-59kg</option>
											<option value="60kg-69kg">60kg-69kg</option>
											<option value="70kg-79kg">70kg-79kg</option>
											<option value="80kg-100kg">80kg-100kg</option>
											<option value="100kg以上">100kg以上</option>
										</select>
									身&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;高<select name="height" class="my_select" >
											<option value="">所有人</option>
											<option value="140cm以下">140cm以下</option>
											<option value="140cm-159cm">140cm-159cm</option>
											<option value="160cm-169cm">160cm-169cm</option>
											<option value="170cm-179cm">170cm-179cm</option>
											<option value="180cm-189cm">180cm-189cm</option>
											<option value="190cm以上">190cm以上</option>
										</select><br/>
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄<select name="age" class="my_select" >
											<option value="">所有人</option>
											<option value="19岁以下">19岁以下</option>
											<option value="20-29岁">20-29岁</option>
											<option value="30-39岁">30-39岁</option>
											<option value="40-49岁">40-49岁</option>
											<option value="50岁以上">50岁以上</option>
										</select>
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