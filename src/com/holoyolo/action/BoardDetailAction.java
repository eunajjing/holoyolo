package com.holoyolo.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.holoyolo.dao.HoloyoloDao;
import com.holoyolo.dto.BoardDto;
import com.holoyolo.dto.BoardcommDto;

 public class BoardDetailAction implements Action {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
		request.setCharacterEncoding("utf-8");
   		
		System.out.println("정보 보러 왔슈~? 들어왔시유~?");
		HoloyoloDao boarddao=new HoloyoloDao();
	   	BoardDto boarddata=new BoardDto();
	   	
	   	
	   	System.out.println(request.getParameter("num"));
	   	int num1=Integer.parseInt(request.getParameter("num"));
	   	
	   	System.out.println(num1);
	   	boarddata=boarddao.getDetail(num1);
	   
		/*boarddao.setReadCountUpdate(num);
	   	boarddata=boarddao.getDetail(num);
	   	*/
	
	   	
	   	/*
	   	 * 2018-08-22 이주원 수정
	   	 */
	   		//
	 /*  	CommentDao commentDAO = CommentDao.getInstance();
        ArrayList<BoardcommDto> commentList = commentDAO.getCommentList(num1);*/
        
        // 댓글이 1개라도 있다면 request에 commentList를 세팅한다.
        /*if(commentList.size() > 0)    request.setAttribute("commentList", commentList);*/
        	//


	   	
	   	if(boarddata==null){
	   		System.out.println("상세보기 실패");
	   		return null;
	   	}
	   	System.out.println("상세보기 성공");
	   	
	   	request.setAttribute("boarddata", boarddata);
	   	ActionForward forward = new ActionForward();
	   	forward.setRedirect(false);
   		forward.setPath("./view_board_free.jsp");
   		return forward;

	 }
}