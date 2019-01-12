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
import com.holoyolo.dto.*;
import com.holoyolo.dao.*;


/**
 * Servlet implementation class CommentController
 */
@WebServlet("/CommentController")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentController() {
        super();
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
		doProcess(request, response);
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		System.out.println("코멘트 서블릿 도달");

      
		int board_code = Integer.parseInt(request.getParameter("BOARD_CODE"));
		System.out.println("보드코드1 : " + board_code);
		
		try {
			CommentDao dao = new CommentDao();
			System.out.println("보드코드2 : " + board_code);
			ArrayList<BoardcommDto> commlist = dao.getCommtList(board_code);

			
       	 if (commlist != null) {
       		System.out.println("commlist : null은 아니지?!");
        		net.sf.json.JSONArray jsonarray = net.sf.json.JSONArray.fromObject(commlist);
        		System.out.println(jsonarray);
        		System.out.println("commlist : 여기까지는 옵니까?~~?~!?!?!");
        		response.getWriter().print(jsonarray);
        		System.out.println("댓글이 있음");
        	 } else {
        		boolean re = false;
        		System.out.println("댓글이 없음");
        		response.getWriter().print(re);
        		
        	 }
	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
