package com.holoyolo.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.holoyolo.dao.CommentDao;
import com.holoyolo.dto.BoardcommDto;

/**
 * Servlet implementation class InsertController
 */
@WebServlet("/InsertController")
public class InsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertController() {
        super();
        System.out.println("호출");
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("post");
		doProcess(request, response);
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
			System.out.println("됨3");
			int board_code = Integer.parseInt(request.getParameter("board_code"));
		String member_email = request.getParameter("member_email");
		String board_comm_content = request.getParameter("board_comm_content");
		

		try{
			CommentDao dao = new CommentDao();
			boolean rs = dao.boardInsert(board_code, member_email, board_comm_content);
			
			response.getWriter().print(rs);
			
			/*out.print("<script>");
			out.print("self.close();");
			out.print("opener.parent.location.reload();");
			out.print("</script>");*/

		}catch(Exception e) {e.printStackTrace();}
			
	}

}
