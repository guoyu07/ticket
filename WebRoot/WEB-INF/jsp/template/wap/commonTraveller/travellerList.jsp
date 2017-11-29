<%@page import="opensource.jpinyin.PinyinHelper"%>
<%@page import="com.ticket.util.ChineseUtil"%>
<%@page import="com.ticket.pojo.CommonTraveller"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
		    <div class="mobile-page">
		        <jsp:include page="../common/title.jsp">
                	<jsp:param value="常用旅客" name="title"/>
                </jsp:include>
		        <div class="mobile-main">
		            <dl class="">
		                <!-- <div class="line-big padding-big-top border-bottom padding-bottom">
		                    <div class="x10" style="position:relative">
		                        <input name='' id='keywords' type="text" class="d_input block fz20" style="border-radius:0px;border:0px;" placeholder='可输入姓名' />
		                        <div class="tagMatches" style='margin-left:10px;'>
		                            <ul>
		                                <li>asdasd <span>KMJ</span></li>
		                                <li>asdasd <span>KMJ</span></li>
		                                <li>asdasd <span>KMJ</span></li>
		                                <li>asdasd <span>KMJ</span></li>
		                                <li>asdasd <span>KMJ</span></li>
		                                <li>asdasd <span>KMJ</span></li>
		                                <li>asdasd <span>KMJ</span></li>
		                                <li>asdasd <span>KMJ</span></li>
		                            </ul>
		                        </div>
		                    </div>
		                    <div class="x2">
		                        <button type="button" class='button fz20' style="border-radius:0px;border:0px;"><i class="icon-bell" style='font-size:40px;'></i></button>
		                    </div>
		                </div> -->
		                <%
		                	List<Character> alphas = new ArrayList<Character>();
		                	for(int i = 'A'; i <= 'Z'; i++){
		                		List<Object> list = (List<Object>)request.getAttribute("list");
	                			boolean added = false;
		                		for(int j = 0; j < list.size(); j++){
		                			CommonTraveller item = (CommonTraveller)list.get(j);
		                			if(i == (int)PinyinHelper.getShortPinyin(item.getName().substring(0,1)).toUpperCase().charAt(0)){
		                				if(!added){
	                	%>
	                	<dt class='fz22 b_l_grey padding-big margin-large-top' id='<%=(char)i%>'>
		                	<%=(char)i %>
		                </dt>
		                <% 
		                					alphas.add((char)i);
		                					added = true;
		                				}
		                %>
	                	<dd class='fz22 margin-big border-bottom padding-big-bottom'>
	                		<label>
	                			<%-- <input type="checkbox" class="d_checkbox" name="<%=item.getId()%>"> --%>
	                			<a href="bjdjServiceCodeActivate_page.action?orderId=${orderId }&idcard=<%=item.getCards().get(0).getCardValue()%>"><%=item.getName() %></a>
	                		</label>
	                	</dd>
		              	<%
	                				}
	                			}
		                	}
		                 %>
		            </dl>
		        </div>
		        <div class="mobile-foot">
		            <div class="c_ft clearfix">
		                <a href="#" class="ft_more"></a>
		            </div>
		            <div class="en_tag">
		                <ul class='text-center c_blue'>
		                <%
		                	for(int i = 0; i < alphas.size(); i++){
		                 %>
	                	 <li><a href="#<%=alphas.get(i)%>"><%=alphas.get(i)%></a></li>
		                <% 
		                	}
		                 %>
		                </ul>
		            </div>
		        </div>
		    </div>
		    <%@ include file="../common/quickNav.jsp"%>
		</div>
		<div class="dialog" style="display:none;">
			<%@ include file="../common/left.jsp"%>
		</div>
		<script type="text/javascript">
		    $('.tit_tab a').on('tap',function(){
		        $(this).addClass('selected').siblings().removeClass('selected');
		    });
		</script>
	</body>
</html>