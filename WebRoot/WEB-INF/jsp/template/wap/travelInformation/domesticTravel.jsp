<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="国内旅游" name="title"/>
				</jsp:include>
				<div class="mobile-main">
					<div class="tit_tab">
						<a href="#" class="selected"><i class="icon-caret-down"></i>国内旅游</a>
						<a href="#"><i class="icon-caret-down"></i>国外旅游</a>
					</div>
					<div class="searchForm mr40">
						<label class='button'>
							<i class='icon-search'></i>
							<input type="text" name="" value="" placeholder='(目的地)'>
						</label>
						<button class="button bg-sub">
							搜索
						</button>
					</div>
					<div class="c_content text-center clearfix">
						<a href="#" class='x3 fz20'>大理<span class='float-right'>|</span>
						</a>
						<a href="#" class='x3 fz20'>丽江<span class='float-right'>|</span>
						</a>
						<a href="#" class='x3 fz20'>石林<span class='float-right'>|</span>
						</a>
						<a href="#" class='x3 fz20'>普者黑</a>
					</div>
					<ul class='mr40 viewport_ls'>
						<li>
							<img src="/template/wap/images/pic/82.jpg">
							<p>
								丽江古城
								<span class='icon-heart-o'></span>
							</p>
						</li>
						<li>
							<img src="/template/wap/images/pic/82.jpg">
							<p>
								丽江古城
								<span class='icon-heart-o'></span>
							</p>
						</li>
						<li>
							<img src="/template/wap/images/pic/82.jpg">
							<p>
								丽江古城
								<span class='icon-heart-o'></span>
							</p>
						</li>
					</ul>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
</html>