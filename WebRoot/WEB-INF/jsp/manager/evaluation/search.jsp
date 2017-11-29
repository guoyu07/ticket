<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
							<form action="${actionName }_${currentMethod }.action?versionFlag=${versionFlag }&operationFlag=${operationFlag }" method="get">
								<input type="hidden" value="${operationFlag }" name="operationFlag"/>
							  	起始时间:<input name="startTime" type="text" 
								  			value="${param.startTime }"
								  			onclick="new WdatePicker();"/>
							  				&nbsp;&nbsp;&nbsp;&nbsp;
							  	结束时间:<input name="endTime" type="text" 
								  			value="${param.endTime }"
								  			onclick="new WdatePicker();"/>
							  				<br/>
							  			<input type="submit" value="查询"/>
						  	</form>