<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String domain = request.getServerName();
	if(domain.startsWith("m.")){
		
		request.getRequestDispatcher("/airport.action").forward(request, response);
	}else if(domain.startsWith("www.")){
		
		request.getRequestDispatcher("/airportPc.action").forward(request, response);
	}
%>
