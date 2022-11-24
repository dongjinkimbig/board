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
 * Servlet implementation class Write
 */
@WebServlet("/Write")
public class Write extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Write() {
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
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=UTF-8");
	 	request.setCharacterEncoding("UTF-8");
	 	PrintWriter writer = response.getWriter();
	 	
	 	String b_title = request.getParameter("b_title");
	 	String b_content = request.getParameter("b_content");
	 	String userid = (String)session.getAttribute("userid");	/* 세션에서 가져오는거 몰랐다 */
	 	String name = (String)session.getAttribute("name");
	 	
	 	PreparedStatement pstmt = null;
	 	Connection conn = null;
	 	
	 	try{
			conn=Dbconn.getConnection();    
			if(conn != null){
//	 			System.out.println("DB연결 성공");
				String sql = "insert into tb_board (b_userid, b_name, b_title, b_content) values (?, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,userid);
				pstmt.setString(2,name);
				pstmt.setString(3,b_title);
				pstmt.setString(4,b_content);
				pstmt.executeUpdate();
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	 writer.println("<script>alert('등록되었습니다');	location.href='board/list.jsp'</script>");
	}

}
