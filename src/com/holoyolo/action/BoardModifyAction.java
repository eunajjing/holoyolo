package com.holoyolo.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.holoyolo.dao.HoloyoloDao;
import com.holoyolo.dto.BoardDto;

 public class BoardModifyAction implements Action {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	 	throws Exception{
		 request.setCharacterEncoding("utf-8");
		 ActionForward forward = new ActionForward();
		 boolean result = false;
		 System.out.println("수정하러 들어왔어?");
		 int num=Integer.parseInt(request.getParameter("BOARD_CODE"));
		 System.out.println("왜 null이 뜨지?");
		 HoloyoloDao boarddao=new HoloyoloDao();
		 BoardDto boarddata=new BoardDto();
		 
//		 boolean usercheck=boarddao.isBoardWriter(num, request.getParameter("BOARD_PASS"));
//		 if(usercheck==false){
//		   		response.setContentType("text/html;charset=utf-8");
//		   		PrintWriter out=response.getWriter();
//		   		out.println("<script>");
//		   		out.println("alert('수정할 권한이 없습니다.');");
//		   		out.println("location.href='./BoardList.bo';");
//		   		out.println("</script>");
//		   		out.close();
//		   		return null;
//		 }
//		 
		 try{
			 boarddata.setBoard_code(num);
			 boarddata.setBoard_title(request.getParameter("Board_title"));
			 boarddata.setBoard_content(request.getParameter("Board_content"));
			 boarddata.setBoard_file(request.getParameter("Board_file"));
			 boarddata.setBoard_notice(request.getParameter("Board_notice"));
			 result = boarddao.boardModify(boarddata);
			 if(result==false){
		   		System.out.println("게시판 수정 실패");
		   		return null;
		   	 }
		   	 System.out.println("게시판 수정 완료");
		   	 
		   	 forward.setRedirect(true);
		   	 forward.setPath("./BoardDetailAction.bo?num="+boarddata.getBoard_code());
		   	 return forward;
	   	 }catch(Exception ex){
	   			ex.printStackTrace();	 
		 }
		 
		 return null;
	 }
}