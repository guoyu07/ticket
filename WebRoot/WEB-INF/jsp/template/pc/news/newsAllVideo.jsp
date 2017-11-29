<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="../common/head.jsp"%>
		<title>${newsClass.name} - 云南省昆明市长水机场</title>
		<script type="text/javascript" src="/template/pc/js/door.js"></script>
	</head>
	<body>
		<%@ include file="../common/top.jsp"%>
		<%@ include file="../common/nav.jsp"%>
		<%@ include file="../common/search.jsp"%>
		<div class="banner_29"></div banner>
		<div class="place w980 mt30">
			当前位置:
			<a href="/airportPc.action">首页</a> >
			<a href="javascript:;">${newsClass.name}</a>
		</div place w980 mt30>
		<div class="video_1 w980 mt30">
			<h2>
				<a href="javascript:;"><img src="/template/pc/images/video_top_1.gif" />
				</a>
			</h2>
		</div video_1 w980 mt30>
		<div class="video_2 container">
			<div class="cbp-fwslider">
				<ul>
					<li>
						<s:iterator id="n" value="pageModule.pageList" status="st">
						<dl class="<s:if test="#st.index%3 eq 0">ml70</s:if>">
							
							<a href="/airport/newsView/${n.id}.html"><dd>
									<ticket:format value="${n.title}" size="12"/>
								</dd>
								<dt>
									<ticket:commonAnnex type="queryAnnexList"
											entityUuid="${n.id}" annexType="4" size="1" />
									<s:if test="#request.queryAnnexList != null">
										<img src="${queryAnnexList[0].annexPath}"/>
									</s:if>
									<s:else>
										<img src="/template/pc/images/001.jpg" />
									</s:else>
								</dt>
							</a>
						</dl>
						</s:iterator>
					</li>
				</ul>
			</div cbp-fwslider>
			<s:if test="pageModule != null && pageModule.totalCount > 0">
					<div class="page_list">
						<ul>
							<pg:pager items="${pageModule.totalCount}" url=""
								maxIndexPages="5" maxPageItems="${pageSize}"
								export="currentPageNumber=pageNumber">
								<pg:param name="method" />
								<pg:first>
									<a href="${pageUrl}"><li class="bhh">
											首页
										</li>
									</a>
								</pg:first>
								<pg:prev>
									<a href="${pageUrl}"><li class="bhh">
											上一页
										</li>
									</a>
								</pg:prev>
								<pg:pages>
									<c:choose>
										<c:when test="${currentPageNumber eq pageNumber }">
											<a class="hover"><li class="bhh">
													${pageNumber}
												</li>
											</a>
										</c:when>
										<c:otherwise>
											<a href="${pageUrl}"><li class="bhh">
													${pageNumber}
												</li>
											</a>
										</c:otherwise>
									</c:choose>
								</pg:pages>
								<pg:next>
									<a href="${pageUrl}"><li class="bhh">
											下一页
										</li>
									</a>
								</pg:next>
								<pg:last>
									<a href="${pageUrl}"><li class="bhh">
											末页
										</li>
									</a>
								</pg:last>
							</pg:pager>
						</ul>

					</div page_list>
				</s:if>
		</div video_2 container>
			<%@ include file="../common/left.jsp" %>
		<%@ include file="../common/bottom.jsp"%>
	</body>
</html>