package com.holoyolo.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.holoyolo.action.Action;
import com.holoyolo.action.ActionForward;
import com.holoyolo.dao.HoloyoloDao;

public class MemberUpdateService implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;

		try {

			request.setCharacterEncoding("UTF-8");

			HttpSession session = request.getSession();

			String email = (String) session.getAttribute("email");
			String updatepassword = request.getParameter("updatepassword");

			HoloyoloDao dao = new HoloyoloDao();
			int result = dao.memberUpdate(email, updatepassword);
			PrintWriter out = response.getWriter();
			
			if (result > 0) {
				out.print("success");
			} else {
				out.print("false");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
}
