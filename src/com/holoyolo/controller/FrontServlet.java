package com.holoyolo.controller;

/*
@class : BoardFrontController
 @Date : 2018-08-21
 @Author :
 @Desc :
*/

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.holoyolo.action.Action;
import com.holoyolo.action.ActionForward;
import com.holoyolo.action.BoardAddAction;
import com.holoyolo.action.BoardDeleteAction;
import com.holoyolo.action.BoardDetailAction;
import com.holoyolo.action.BoardListAction;
import com.holoyolo.action.BoardListUseIndexAction;
import com.holoyolo.action.BoardModifyAction;
import com.holoyolo.action.BoardModifyView;
import com.holoyolo.action.ChartDataAction;
import com.holoyolo.action.FreeBoardSearchAction;
import com.holoyolo.action.GroupbyBoardListUseIndexAction;
import com.holoyolo.action.OrderPostListAction;
import com.holoyolo.action.OrderPostViewAction;
import com.holoyolo.service.Admin_membermanagementService;
import com.holoyolo.service.LoginService;
import com.holoyolo.service.LogoutService;
import com.holoyolo.service.MemberAddService;
import com.holoyolo.service.MemberDeleteService;
import com.holoyolo.service.MemberUpdateService;
import com.holoyolo.service.MemberdeletecheckService;
import com.holoyolo.service.MemberpasscheckService;
import com.holoyolo.service.Mypage_BoardListService;
import com.holoyolo.service.Mypage_CommListService;

@WebServlet("*.bo")
public class FrontServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FrontServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String RequestURI = request.getRequestURI();
		String ContextPath = request.getContextPath();
		String url_command = RequestURI.substring(ContextPath.length());

		Action action = null;
		ActionForward forward = null;

		System.out.println("FrontServlet.java 에 진입");
		System.out.println(url_command);

		if (url_command.equals("/BoardWrite.bo")) {

			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/qna_board_write.jsp");
		} else if (url_command.equals("/board_free/free_search.bo")) {
			System.out.println("프론트 컨트롤러 도달, 리스트 조회하려 함");
			action = new FreeBoardSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (url_command.equals("/board_free/BoardDelete.bo")) {
			System.out.println();
			forward = new ActionForward();
			forward.setRedirect(false);

		} else if (url_command.equals("/board_free/BoardDeleteAction.bo")) {
			action = new BoardDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (url_command.equals("/board_free/BoardAddAction.bo")) {
			action = new BoardAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (url_command.equals("/board_free/BoardList.bo")) {

			action = new BoardListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (url_command.equals("/board_free/BoardDetailAction.bo")) {
			action = new BoardDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (url_command.equals("/MemberAdd.bo")) {
			try {
				action = new MemberAddService();
				forward = action.execute(request, response);
				System.out.println("forward : 가입완료");
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (url_command.equals("/board_free/BoardModify.bo")) {

			action = new BoardModifyView();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (url_command.equals("/board_free/BoardModifyAction.bo")) {
			System.out.println("내용 업데이트하러 왔어?!~");
			action = new BoardModifyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} /*
			 * else if(url_command.equals("/MemberID.bo")) { try { action = new MemberId();
			 * forward = action.execute(request, response); }catch (Exception e) {
			 * e.printStackTrace(); }
			 * 
			 * }
			 */else if (url_command.equals("/login.bo")) {
			try {
				action = new LoginService();
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (url_command.equals("/logout.bo")) {
			try {
				action = new LogoutService();
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (url_command.equals("/view.bo")) {
			// UI 제공
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/boardwrite.jsp");
		} else if (url_command.equals("/mypage/memberupdate.bo")) {
			try {
				action = new MemberUpdateService();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (url_command.equals("/memberdelete.bo")) {
			try {
				action = new MemberDeleteService();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (url_command.equals("/board_groupbuy/board_groupbuy.bo")) {
			System.out.println("프론트 컨트롤러 도달, 리스트 조회하려 함");
			action = new OrderPostListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (url_command.equals("/mypage/memberpasscheck.bo")) {
			try {
				action = new MemberpasscheckService();
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (url_command.equals("/board_groupbuy/board_groupbuy_View.bo")) {
			System.out.println("프론트 컨트롤러 도달, 게시물 보려 함");
			action = new OrderPostViewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (url_command.equals("/BoardListUseIndexAction.bo")) {
			try {
				action = new BoardListUseIndexAction();
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (url_command.equals("/GroupbyBoardListUseIndexAction.bo")) {
			try {
				action = new GroupbyBoardListUseIndexAction();
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (url_command.equals("/mypage/memberdeletecheck.bo")) {
			try {
				action = new MemberdeletecheckService();
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (url_command.equals("/chartdata.bo")) {
			try {
				action = new ChartDataAction();
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (url_command.equals("/mypage/MypageLoginMain.bo")) {
			action = new Mypage_BoardListService();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} 
		else if (url_command.equals("mypage/admin_membermanagement.bo")) {

			action = new Admin_membermanagementService();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (forward != null) {
			if (forward.isRedirect()) { // true
				response.sendRedirect(forward.getPath()); // location.href
			} else {
				// forward >> 주소변호 없고 내용만 변화 >>
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}

	}

}
