package com.holoyolo.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.holoyolo.dao.UserOrderdao;
import com.holoyolo.dto.UserOrder;


/*
@class : OrderPostInsert
 @Date : 2018-08-27
 @Author : 고은아
 @Desc : 유저가 공동구매를 신청했을 때 사용되는 서블릿
*/

/**
 * Servlet implementation class OrderPostInsert
 */
@WebServlet("/board_groupbuy/OrderPostInsert")
public class OrderPostInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderPostInsert() {
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
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		UserOrder vo = new UserOrder();
		
		PrintWriter out = response.getWriter();
		
		try {
			UserOrderdao dao = new UserOrderdao();
			System.out.println("주문서 등록 위해 action 도달");
			vo.setGoods_code(Integer.parseInt(request.getParameter("goods_code")));
			vo.setMember_email(request.getParameter("member_email"));
			vo.setOrderer_name(request.getParameter("orderer_name"));
			vo.setOrderer_phone(request.getParameter("orderer_phone"));
			vo.setOrderer_addr_code(request.getParameter("orderer_addr_code"));
			vo.setOrderer_addr_main(request.getParameter("orderer_addr_main"));
			vo.setOrderer_addr_detail(request.getParameter("orderer_addr_detail"));
			vo.setOrderer_count(request.getParameter("orderer_count"));
			vo.setOrderer_sum(Integer.parseInt(request.getParameter("orderer_sum")));
			
			boolean result = dao.orderInsert(vo);
			
			if(result==true){
				out.print("<script>");
				out.print("alert('신청되었습니다.');");
				out.print("self.close();");
				/*out.print("opener.parent.myModal1();");*/

				out.print("</script>");
			} else {
				out.print("<script>");
				out.print("alert('신청되지 않았습니다. 관리자에게 문의하세요.');");
				out.print("self.close();");
				/*out.print("opener.parent.myModal2();");*/

				out.print("</script>");
			}
		} catch(Exception e) {e.printStackTrace();}
		
	}

}
