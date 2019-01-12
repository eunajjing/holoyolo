package com.holoyolo.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.holoyolo.action.Action;
import com.holoyolo.action.ActionForward;
import com.holoyolo.dao.HoloyoloDao;

public class MemberIdCheckService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		String idCheck = null;
		String email = request.getParameter("email");
		try {
			HoloyoloDao dao = new HoloyoloDao();
			idCheck = dao.isIdCheckById(email);
			request.setAttribute("message", idCheck);

		} catch (Exception e) {
			System.out.println("email 검증 오류 :" + e.getMessage());
		}

		forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/uservalid.jsp");
		return forward;
	}

}
