package com.holoyolo.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.holoyolo.dao.*;
import com.holoyolo.dto.*;

/*
@class : OrderPostListAction
 @Date : 2018-08-23
 @Author : 고은아
 @Desc : 공동구매 게시판 게시물 전체 조회 / 검색 조회 / 페이징 액션 수정
*/

 public class OrderPostListAction implements Action {
	 
	 
	 @Override
		public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
			ActionForward foward = null;
			
			int page=1;
			int limit=10;
			
			if(request.getParameter("page")!=null){
				page=Integer.parseInt(request.getParameter("page"));
			}
			
			OrderPostListDao dao;
			
			try {
				System.out.println("공구게시판 조회 액션 클래스 도달");
				String col = request.getParameter("col");
	            String word = request.getParameter("word");
	            
	            
	            if (col==null||word==null) {
	            	System.out.println("공구게시판 전체 게시물을 보고자 함");
	            	dao = new OrderPostListDao();
	            	int listcount=dao.getListCount();
	       	       ArrayList<OrderPost> orderboard = dao.getOrderPostList(page, limit);
	       	    int maxpage=(int)((double)listcount/limit+0.9);
	       	    if (maxpage==0) {
	       	    	maxpage = 1;
	       	    }
	       	 int startpage = (((int) ((double)page / 5 + 0.9)) - 1) * 5 + 1;
	       	int endpage = startpage + 5 -1;
	       	
	       	if (endpage>maxpage) endpage=maxpage;
	       	
	       	System.out.println(page+","+maxpage+","+startpage+","+endpage+","+listcount);
	       	
	       	request.setAttribute("page", page);		  //현재 페이지 수.
	   		request.setAttribute("maxpage", maxpage); //최대 페이지 수.
	   		request.setAttribute("startpage", startpage); // 현재 페이지에 표시할 첫 페이지 수
	   		request.setAttribute("endpage", endpage);     //현재 페이지에 표시할 끝 페이지 수
			request.setAttribute("listcount",listcount); //글 수
	       	       request.setAttribute("orderboard", orderboard);
	       	       foward = new ActionForward();
	       	       foward.setRedirect(false);
	       	       foward.setPath("./board_groupbuy.jsp");
	            	
	            	
	            	
	            }else {
	            	System.out.println("공구게시판 검색을 하고자 함");
	            	dao = new OrderPostListDao();
	            	int listcount=dao.getListSeachCount(col, word);
	            	

	            	
	            	ArrayList<OrderPost> orderboard = dao.OrderPostListSeach(col, word, page, limit);
	        	      
	        	       
		            	int maxpage=(int)((double)listcount/limit+0.9);
		            	int startpage = (((int) ((double)page / 5 + 0.9)) - 1) * 5 + 1;
		            	int endpage = startpage + 5 -1;
		   	       	
		            	if (endpage>maxpage) endpage=maxpage;
	        	       
		            	request.setAttribute("page", page);		  //현재 페이지 수.
		    	   		request.setAttribute("maxpage", maxpage); //최대 페이지 수.
		    	   		request.setAttribute("startpage", startpage); // 현재 페이지에 표시할 첫 페이지 수
		    	   		request.setAttribute("endpage", endpage);     //현재 페이지에 표시할 끝 페이지 수
		    			request.setAttribute("listcount",listcount); //글 수
		    			request.setAttribute("orderboard", orderboard);
		            	System.out.println(page+", "+maxpage+", "+startpage+", "+endpage+", "+listcount);
	        	       foward = new ActionForward();
	           	       foward.setRedirect(false);
	           	       foward.setPath("./board_groupbuy.jsp");
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