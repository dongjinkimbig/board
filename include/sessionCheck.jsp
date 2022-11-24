<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	if(session.getAttribute("userid")==null){

		response.sendRedirect("/Day4/login.jsp");	//어디서든 팅겨내게 /Day4/ 붙임
		
		return;

	}
%>