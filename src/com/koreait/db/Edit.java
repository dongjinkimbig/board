package com.koreait.db;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
import com.koreait.db.Dbconn;

/**
 * Servlet implementation class Edit
 */
@WebServlet("/Edit")
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Edit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
	     
	    String b_idx = request.getParameter("b_idx");
	 	String b_title = request.getParameter("b_title");
	 	String b_content = request.getParameter("b_content");
	 	
	 	String userid = (String)session.getAttribute("userid");	/* 세션에서 가져오는거 몰랐다 */
	 	String name = (String)session.getAttribute("name");
	 	
	 	
	 	
	 	PreparedStatement pstmt = null;
	 	Connection conn = null;

	 	try{
			conn=Dbconn.getConnection();    
			if(conn != null){

				String sql = "update tb_board set b_title=?, b_content=?, b_userid=?, b_name=? where b_idx=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,b_title);
				pstmt.setString(2,b_content);
				pstmt.setString(3,userid);
				pstmt.setString(4,name);
				pstmt.setString(5,b_idx);
				pstmt.executeUpdate();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	 writer.println("<script>alert('수정되었습니당'); location.href='board/view.jsp?b_idx="+b_idx+"' </script>");
	 
	}

}
