<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*" %>
    <%@ page import="com.koreait.db.Dbconn" %>
    <%@ include file = "../include/sessionCheck.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");
    String b_idx = request.getParameter("b_idx");
    String re_idx = request.getParameter("re_idx");
    String userid = (String)session.getAttribute("userid");	
 	String name = (String)session.getAttribute("name");
 	PreparedStatement pstmt = null;
 	Connection conn = null;
 	try{
		conn=Dbconn.getConnection();
		if(conn != null){
			String sql = "delete from tb_reply where re_idx=? and re_userid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,re_idx);
			pstmt.setString(2,userid);
			pstmt.executeUpdate();
		}
	}catch(Exception e){
		e.printStackTrace();
	}
%>
 <script>
 	alert('삭제되었습니당');
 	location.href='view.jsp?b_idx=<%=b_idx%>';
 </script>