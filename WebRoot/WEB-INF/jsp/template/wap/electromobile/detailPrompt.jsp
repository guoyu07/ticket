<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<ticket:cache group="便捷登机">
	<body class="mobile">
		<div class="mobile-view">
		    <div class="mobile-page">
		        <jsp:include page="../common/title.jsp">
	            	<jsp:param value="消费提示" name="title"/>
	            </jsp:include>
		        <div class="mobile-main">
		            <div class="c_content">
		               <h2 class='text-center'>安检口-登机口距离</h2>
		               <br>
		               <table  class="table table-bordered">
		                    <tr>
		                        <td class="fz18" width="150">登机口</td>
		                        <td class="fz18">距离（M）</td>
		                        <td class="fz18">不行时间（m）</td>
		                    </tr>
		                    <tr>
		                        <td class="fz18">1号</td>
		                        <td class="fz18">30</td>
		                        <td class="fz18">1</td>
		                    </tr>
		                    <tr>
		                        <td class="fz18">1号</td>
		                        <td class="fz18">30</td>
		                        <td class="fz18">1</td>
		                    </tr>
		                    <tr>
		                        <td class="fz18">1号</td>
		                        <td class="fz18">30</td>
		                        <td class="fz18">1</td>
		                    </tr>
		                    <tr>
		                        <td class="fz18">1号</td>
		                        <td class="fz18">30</td>
		                        <td class="fz18">1</td>
		                    </tr>
		                    <tr>
		                        <td class="fz18">1号</td>
		                        <td class="fz18">30</td>
		                        <td class="fz18">1</td>
		                    </tr>
		                    <tr>
		                        <td class="fz18">1号</td>
		                        <td class="fz18">30</td>
		                        <td class="fz18">1</td>
		                    </tr>
		                    <tr>
		                        <td class="fz18">1号</td>
		                        <td class="fz18">30</td>
		                        <td class="fz18">1</td>
		                    </tr>
		                    <tr>
		                        <td class="fz18">1号</td>
		                        <td class="fz18">30</td>
		                        <td class="fz18">1</td>
		                    </tr>
		                    <tr>
		                        <td class="fz18">1号</td>
		                        <td class="fz18">30</td>
		                        <td class="fz18">1</td>
		                    </tr>
		                </table>
		            </div>
		        </div>
		        <%@ include file="../common/quickNav.jsp" %>
		    </div>
		</div>
		<div class="dialog" style="display:none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
	</ticket:cache>
</html>