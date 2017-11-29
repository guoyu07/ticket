<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="ticket" uri="/WEB-INF/classes/tags.tld" %>
<%@page import="com.ticket.mq.FlightStatus"%>
<%@page import="com.ticket.pojo.DepartFromPort"%>
<%@page import="com.ticket.pojo.ArrivalAtPort"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<table class="table">
    <tbody>
    <s:if test="departList != null">
    	<thead>
	        <tr>
	            <th>预定时间</th>
	            <th>实际时间</th>
	            <th>到达</th>
	            <th>航班号</th>
	            <th>登机门</th>
	            <th>状态</th>
	        </tr>
	    </thead>
	    <s:if test="departList.size==0"><tr><td>没有数据</td></tr></s:if>
	    <s:else>
    	<s:iterator id="departFromPort" value="#request.departList">
    		 <tr>
	            <td><s:date name="#departFromPort.std" format="HH:mm"/></td>
	            <td><s:date name="#departFromPort.abt" format="HH:mm"/></td>
	            <td><ticket:common type="airportInfoByThreeCode" value="${departFromPort.des }"/>${airportInfoByThreeCode.name }</td>
	            <td>${departFromPort.flt }</td>
	            <td>${departFromPort.gat }</td>
	            <td><s:if test="#departFromPort.frs != null && #departFromPort.frs !=''">  
                  	 	<%=Enum.valueOf(FlightStatus.class, ((DepartFromPort)request.getAttribute("departFromPort")).getFrs().toUpperCase()).getText() %>
                    </s:if>
	            </td>
	        </tr>
    	</s:iterator>
    	</s:else>
    </s:if>
    <s:if test="arrivalList != null">
    	<thead>
	        <tr>
	            <th>预定时间</th>
	            <th>实际时间</th>
	            <th>出发</th>
	            <th>航班号</th>
	            <th>登机门</th>
	            <th>状态</th>
	        </tr>
	    </thead>
	    <s:if test="arrivalList.size==0"><tr><td>没有数据</td></tr></s:if>
	    <s:else>
	    	<s:iterator id="arrivalAtPort" value="#request.arrivalList">
	    		 <tr>
		            <td><s:date name="#arrivalAtPort.stp" format="HH:mm"/></td>
		            <td><s:date name="#arrivalAtPort.abp" format="HH:mm"/></td>
		            <td><ticket:common type="airportInfoByThreeCode" value="${arrivalAtPort.org }"/>${airportInfoByThreeCode.name }</td>
		            <td>${arrivalAtPort.flt }</td>
		            <td>--</td>
		            <td><s:if test="#arrivalAtPort.frs != null && #arrivalAtPort.frs !=''">  
                  	 	<%=Enum.valueOf(FlightStatus.class, ((ArrivalAtPort)request.getAttribute("arrivalAtPort")).getFrs().toUpperCase()).getText() %>
                    </s:if></td>
		        </tr>
	    	</s:iterator>
    	</s:else>
     </s:if>
    </tbody>
</table>
           
