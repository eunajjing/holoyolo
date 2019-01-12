package com.holoyolo.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.holoyolo.dao.HoloyoloDao;
import com.holoyolo.dto.*;



 public class FreeBoardSearchAction implements Action {
	 
	 
	 @Override
		public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
			ActionForward foward = null;
			HoloyoloDao dao;
			try {
				System.out.println("자유게시판 조회 액션 클래스 도달");
				String col = request.getParameter("col");
	            String word = request.getParameter("word");
	            if (col==null||word==null) {
	            	System.out.println("자유게시판 전체 게시물을 보고자 함");
	            	dao = new HoloyoloDao();
	       	       /*ArrayList<BoardDto> freeboard = dao.getFreeboardList();*/
	       	      /* request.setAttribute("orderboard", freeboard);*/
	       	       
	       	       foward = new ActionForward();
	       	       foward.setRedirect(false);
	       	       foward.setPath("./board_free.jsp");
	            	
	            	
	            	
	            }else {
	            	System.out.println("공구게시판 검색을 하고자 함");
	            	dao = new HoloyoloDao();
	            	/*ArrayList<BoardDto> freeboard = dao.freeboardSeach(col, word);*/
	        	  /*     request.setAttribute("freeboard", freeboard);*/
	        	       foward = new ActionForward();
	           	       foward.setRedirect(false);
	           	       foward.setPath("./board_free.jsp");
	            }
	       	       
			}catch(Exception e) {
				e.printStackTrace();
			}

			return foward;
		}
	 
	 /*public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 HoloyoloDao boarddao=new HoloyoloDao();
		List boardlist=new ArrayList();
		System.out.println("왔능가?");
	  	int page=1;
		int limit=10; // 한 페이지당 게시글 갯수
		
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("왔느냐꼬!");
		int listcount=boarddao.getListCount(); //총 리스트 수.
		boardlist = boarddao.getBoardList(page,limit); //리스트를 받아옴.
		//총 페이지 수
   		int maxpage=(int)((double)listcount/limit+0.95);
   		//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
   		int startpage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
   		//현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등...)
   		int endpage = startpage + 10 -1;
   		
   		if (endpage>maxpage) endpage=maxpage;
   		
   		request.setAttribute("page", page);		  //현재 페이지 수.
   		request.setAttribute("maxpage", maxpage); //최대 페이지 수.
   		request.setAttribute("startpage", startpage); // 현재 페이지에 표시할 첫 페이지 수
   		request.setAttribute("endpage", endpage);     //현재 페이지에 표시할 끝 페이지 수
		request.setAttribute("listcount",listcount); //글 수
		request.setAttribute("boardlist", boardlist);
		
		ActionForward forward= new ActionForward();
	   	forward.setRedirect(false);
   		forward.setPath("./board_free.jsp");
   		return forward;
	 }*/
 }