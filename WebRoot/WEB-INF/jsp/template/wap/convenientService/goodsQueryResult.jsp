<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param name="title" value="服务查询结果"/>
				</jsp:include>
				<div class="mobile-main">
					<div class="mr40 fz22">
						<s:if test="goodsCount != null">
							您查询的结果为：有
							<span class='c_red'>${goodsCount }</span> 件疑似物品
						</s:if>
						<s:else>
							您查询的结果为：${noDataMessage }
						</s:else>
					</div>
					<div class="c_content">
						<h2 class='c_grey'>
							认领细则
							<button class='button bg-sub float-right' id='rl_btn'>
								认领须知
							</button>
						</h2>
						<div class="padding-big-top fz16">
							<ticket:common type="systemDicObjByValue" value="lostGoodsQuerygGetTips"/>
							${systemDicObjByValue.description }
						</div>
					</div>
					<div class="tit" style="height: 120px">
						<div class='fz16 text-left mrlr20'>
							<ticket:common type="systemDicObjByValue" value="lostGoodsQueryTips"/>
							${systemDicObjByValue.description }
						</div>
					</div>
					<br>
					<br>
					<div class='fz16 text-left mrlr20'>
						<div class='padding-bottom fz22 text-left mrlr20'>
							H区问询台
							<a href="<ticket:common type="positionUrl" value="yishiwupinchaxun"/>" class='c_blue float-right'><em class='quick_map'></em>导航到这</a>
						</div>
					</div>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<div class="default_dialog">
				<div class="default_box c_grey fz16 text-left">
					<dl class='llist fz16 height-big'>
						<ticket:common type="receiveNoticeObj"/>
						${receiveNoticeObj.description }
						<ticket:common type="lostGoodsQueryPhoneList"/>
						<s:if test="#request.lostGoodsQueryPhoneList != null">
							<s:iterator id="queryPhone" value="#request.lostGoodsQueryPhoneList">
								<dd class='padding-bottom border-small border-bottom height-large'>
									${queryPhone.name }遗失物品查询电话：${queryPhone.value }
									<a href="tel:${queryPhone.value }" class='c_blue float-right'><em
										class='quick_mobile'></em>一键拨号</a>
								</dd>
							</s:iterator>
						</s:if>
						</dl>
					<!-- <img src="../images/pic/60.jpg" height="653" width="507"> -->
				</div>
			</div>
		</div>
		<script type="text/javascript">
			$(function() {
				$('#rl_btn').on('tap', function() {
					$.do_dialog.open( {
						'msg' : $('.default_dialog')
					});
				})
			});
		</script>
		<script type="text/javascript" src="../js/script.js"></script>
	</body>
</html>