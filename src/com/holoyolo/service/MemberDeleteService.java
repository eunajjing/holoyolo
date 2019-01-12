package com.holoyolo.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.holoyolo.action.Action;
import com.holoyolo.action.ActionForward;
import com.holoyolo.dao.HoloyoloDao;

public class MemberDeleteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		try {

			request.setCharacterEncoding("UTF-8");

			HttpSession session = request.getSession();

			String email = (String) session.getAttribute("email");

			System.out.println("세션값테스트 = " + email);

			HoloyoloDao dao = new HoloyoloDao();
			int result = dao.memberDelete(email);

			String msg = "";
			String url = "";

			if (result > 0) {
				msg = "정상적으로 탈퇴 되었습니다.";
				url = "index.jsp";
			} else {
				msg = "탈퇴에 실패하였습니다. 올바르게 입력해주세요.";
				url = "LoginMain.jsp";
			}

			request.setAttribute("board_msg", msg);
			request.setAttribute("board_url", url);
			
			session.invalidate();

			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/redirect.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
}
