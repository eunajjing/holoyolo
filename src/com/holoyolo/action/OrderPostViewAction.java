package com.holoyolo.action;
/*
@class : OrderPostViewAction
 @Date : 2018-08-22
 @Author : 고은아
 @Desc : 공동구매 게시물 보기
*/

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.holoyolo.dao.*;
import com.holoyolo.dto.*;

public class OrderPostViewAction implements Action {
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
		request.setCharacterEncoding("utf-8");
		System.out.println("OrderPostViewAction 도달, 공동구매 게시물 보려함");
		OrderPost vo = new OrderPost();
		OrderPostListDao dao = new OrderPostListDao();
		
		int goods_code = Integer.parseInt(request.getParameter("goods_code"));
		vo= dao.getDetail(goods_code);
		
		if(vo == null) {
			System.out.println("공동구매 게시물 보기 실패");
		}
		
		request.setAttribute("vo", vo);
		ActionForward forward = new ActionForward();
	   	forward.setRedirect(false);
   		forward.setPath("./view_board_order.jsp");
   		return forward;
	}
}
