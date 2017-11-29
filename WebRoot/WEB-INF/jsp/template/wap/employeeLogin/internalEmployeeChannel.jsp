<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<script type="text/javascript">
		$(function(){
			$(".back").click(function(){
				JM.goUrl("/airport_loginSuccessIndex.action");
			});
		});
	</script>
	<ticket:cache group="资讯中心">
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<s:if test="#session.fromApp == null && #parameters.fromApp == null">
				<div class="mobile-top">
					<div class="header">
						<a href="" class="FL"><i class="icon-angle-left"></i>
							</a> 内部员工登陆
							</a>
					</div>
					<div class="header_search">
						<div class="searchForm">
							<label class='button'>
								<i class='icon-search c_l_grey'></i>
								<input type="text" placeholder='你要搜索的内容' datatype="*" id="keyword" name="keyword">
							</label>
							<button class="button bg-sub" id="commonSearchBtn">
								搜索
							</button>
						</div>
					</div>
				</div>
				</s:if>
				<s:else>
				<div class="mobile-top" style="display: none;">
					<div class="header">
						<a href="" class="FL"><i class="icon-angle-left"></i>
							</a> 内部员工登陆
							</a>
					</div>
					<div class="header_search">
						<div class="searchForm">
							<label class='button'>
								<i class='icon-search c_l_grey'></i>
								<input type="text" placeholder='你要搜索的内容' datatype="*" id="keyword" name="keyword">
							</label>
							<button class="button bg-sub" id="commonSearchBtn">
								搜索
							</button>
						</div>
					</div>
				</div>
				</s:else>
				<div class="mobile-main">
					<div class="mr40 tab_ls">
						<ticket:common type="regulationTypeList"/>
						<s:if test="#request.regulationTypeList != null">
							<s:iterator id="regulationType" value="#request.regulationTypeList">
							<dl>
								<dt class='fz30 line  b_blue c_white'>
			                        <div class='x11 text-left'>${regulationType.name }</div>
			                        <div class="x1 text-right"><span class='icon-plus'></span></div>
			                    </dt>
								<dd class='fz18 no-border'>
									<ticket:common type="regulationListByType" value="${regulationType.id }"/>
									<s:if test="#request.regulationListByType != null">
										<s:iterator id="regulation" value="#request.regulationListByType">
											<a href="/airport_regulationDetail.action?id=${regulation.id }" class="block text-center fz24 border-bottom padding-bottom margin-bottom">${regulation.title }</a>
										</s:iterator>
									</s:if>
								 </dd>
								</dl>
							</s:iterator>
						</s:if>
						<div class="line clearfix mr40">
						<button type="" class="button d_button bg-yello block back" style="margin: 20px auto;">返回首页</button>
						</div>
					</div>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
	</ticket:cache>
</html>