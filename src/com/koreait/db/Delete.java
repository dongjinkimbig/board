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
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		PrintWriter writer = response.getWriter();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String b_idx = request.getParameter("b_idx");
		String userid = (String)session.getAttribute("userid");
		try{
			conn=Dbconn.getConnection();    
			if(conn != null){

				String sql = "delete from tb_board where b_idx=? and b_userid=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,b_idx);
				pstmt.setString(2,userid);
				pstmt.executeUpdate();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	
	writer.println("<script>alert('해당 글이 삭제되었습니당');	location.href='board/list.jsp'</script>");
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
