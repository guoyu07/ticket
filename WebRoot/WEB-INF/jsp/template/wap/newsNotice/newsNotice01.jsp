<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp"></jsp:include>
				<div class="mobile-main">
					<div class="notice_ls">
						<h3>
							公告栏
							<a href="#" class="float-right">查看更多</a>
						</h3>
						<ul>
							<li>
								<a href="#"><span>[公告栏]</span>云南机场集团责任有限公司</a>
							</li>
							<li>
								<a href="#"><span>[公告栏]</span>云南机场集团责任有限公司</a>
							</li>
							<li>
								<a href="#"><span>[公告栏]</span>云南机场集团责任有限公司</a>
							</li>
						</ul>
					</div>
					<div class="news_ind clearfix">
						<div class="xl">
							<h4>
								图片新闻
							</h4>
							<ul>
								<li>
									<a href="#"><span class="tag bg-red">置顶</span>云南机场集团责任</a>
								</li>
								<li>
									<a href="#">云南机场集团责任有限公司</a>
								</li>
								<li>
									<a href="#">云南机场集团责任有限公司</a>
								</li>
							</ul>
							<a href=""
								class="button float-right yello c_l_grey margin-big-right">查看更多</a>
						</div>
						<div class="xr">
							<h4>
								机场要闻
							</h4>
							<ul>
								<li>
									<a href="#"><span class="tag bg-red">置顶</span>云南机场集团责任</a>
								</li>
								<li>
									<a href="#">云南机场集团责任有限公司</a>
								</li>
								<li>
									<a href="#">云南机场集团责任有限公司</a>
								</li>
							</ul>
							<a href=""
								class="button float-right yello c_l_grey margin-big-right">查看更多</a>
						</div>
					</div>
					<div class="c_ft clearfix">
						<a href="#" class="ft_more"></a>
					</div>
				</div>
			</div>
		</div>
		<div class="dialog" style="display: none;"><%@ include file="../common/left.jsp" %></div>
		<script type="text/javascript" src="../js/script.js"></script>
	</body>
</html>