<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<ticket:cache group="便捷登机">
	<script type="text/javascript" src="/common/jquery-form/jquery-form.js"></script>
	<script type="text/javascript" src="/common/FileUpload.js"></script>
	<script type="text/javascript" src="/template/wap/js/convientBording/evaluate.js"></script>
	<body class="mobile quick">
		<div class="mobile-view">
		    <div class="mobile-page">
                <jsp:include page="../common/title.jsp">
                	<jsp:param value="评价" name="title"/>
                </jsp:include>
		        <div class="mobile-main">
					<form action="bjdjCommentTemplate_add.action" method="post" id="memberForm" return="false">
						<input id="serviceCode_id" type="hidden" name="serviceCode_id" value="${serviceCode_id }"></input>
		            	<div class="panel mr40 border10">
							<div class="panel-head fz22 padding-big-top padding-big-bottom text-center">评价星级</div>
							<input type="hidden" name="star" value="5"></input>
							<div id="totalStar" class="panel-body fz16 padding-big-top padding-big-bottom text-center star_lavel">
								<i class='icon-star text-dot fz30'></i> 
								<i class='icon-star text-dot fz30'></i> 
								<i class='icon-star text-dot fz30'></i> 
								<i class='icon-star text-dot fz30'></i> 
								<i class='icon-star text-dot fz30'></i>
							</div>
						</div>
						<div class="c_content text-left no-border" style="padding:0px 0px;">
			                <label>
			                	<input type="checkbox" value="true" id="showName" name="showName" style="width: 40px;height: 25px;background: #ddd;border-radius: 100%;position: relative;">
			                	&nbsp;&nbsp;
			                	<label for="showName" style="padding: 0; border: 0;">显示用户名</label>
			                </label>
			                <s:fielderror cssStyle="float:left;">
								<s:param>showName</s:param>
							</s:fielderror>
			            </div>
						<s:fielderror>
							<s:param>addFailed</s:param>
						</s:fielderror>
						<div class="panel mr40 border10">
							<div class="panel-head fz22 padding-big-top padding-big-bottom text-center">文字评价</div>
							<div class="panel-body fz16 padding-big-top padding-big-bottom">
								<textarea name="content" style="width:100%;height:100px;border:0px;" datatype="*1-120" placeholder="这里填写评价内容"></textarea>
								<p class="Validform_checktip" style="display:inline;"></p>
								<s:fielderror cssStyle="float:left;">
									<s:param>content</s:param>
								</s:fielderror>
							</div>
						</div>
						<div class="mr40">
							<s:iterator value="evaluate_rule" var="var" status="st">
			                <div class="line">
			                    <div class="x4 fz22 height-large">${var.value }</div>
			                    <input type="hidden" name="ruleId" value="${var.id }"></input>
			                    <input type="hidden" name="reason" id='id<s:property value="#st.count"></s:property>'></input>
			                    <input type="hidden" name="rule" value="5"></input>
			                    <div class="x8 star_lavel">
			                        <i class='icon-star text-dot fz30'></i>
			                        <i class='icon-star text-dot fz30'></i>
			                        <i class='icon-star text-dot fz30'></i>
			                        <i class='icon-star text-dot fz30'></i>
			                        <i class='icon-star text-dot fz30'></i>
			                    </div>
			                    <div class="dialog" style="display:none;">
								    <div class="search_dialog" style="text-align: center; padding-top: 60px;">
								    	<s:iterator value="dictionaryService.querySubByParent(#var.id)" var="var2">
							            <label class="checkbox_label">
							            	<i class='checkbox icon-square-o' parentId='id<s:property value="#st.count"></s:property>'></i>
						            		<input type="checkbox" value="${var2.value }"/>${var2.value }
							            </label>
							            </s:iterator>
							            <!-- <button class="button bg-yello radius-big confirmReason">提交</button> -->
								    </div>
								</div>
			                </div>
			                </s:iterator>
			            </div>
						<div class='mr40'>
							<div class="upload_ls clearfix padding-big-bottom x10">
			                    <a href="javascript:void()" id="upload"><img src="/template/wap/images/camera.jpg" height="135" width="135"></a>
			                </div>
			                <div class="x2">
								<s:fielderror cssStyle="float:left;">
									<s:param>showName</s:param>
								</s:fielderror>
							</div>
							<button class="button b_yello d_button block">提交评价</button>
							<a href="bjdjCommentTemplate_listPage.action?bjdjPage_id=${bjdjPage_id }" id="manageLink" style="display: none;"></a>
						</div>
					</form>
		        </div>
		        <div class="mobile-foot">
		            <p class='text-center fz12 padding-big-top'>COPYRIGHT YUNNAN AIRPORT GROUP CO.,LTD. ALL RIGHTS RESERVED</p>
		            <%@ include file="../common/quickNav.jsp"%>
		        </div>
		    </div>
		</div>
	</body>
	</ticket:cache>
</html>