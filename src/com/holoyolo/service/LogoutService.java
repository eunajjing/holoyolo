package com.holoyolo.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.holoyolo.action.Action;
import com.holoyolo.action.ActionForward;

public class LogoutService implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		try {
			request.setCharacterEncoding("UTF-8");

			HttpSession httpSession = request.getSession();
			httpSession.invalidate();
			
			String msg = "로그아웃 성공";
			String url ="index.jsp";
			//request.setAttribute("board_msg", msg);
			request.setAttribute("board_url", url);
			   
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/redirect.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
}