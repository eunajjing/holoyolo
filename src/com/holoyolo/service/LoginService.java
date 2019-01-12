package com.holoyolo.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.holoyolo.action.Action;
import com.holoyolo.action.ActionForward;
import com.holoyolo.dao.HoloyoloDao;

public class LoginService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		try {
			request.setCharacterEncoding("UTF-8");
			String stremail = request.getParameter("login_email");
			String strpassword = request.getParameter("login_password");
			
			HoloyoloDao dao = new HoloyoloDao();
			boolean bcheck = dao.LoginCheck(stremail, strpassword);
			System.out.println(bcheck);
			PrintWriter out = response.getWriter();
			
			if(bcheck == true){
				out.print(stremail);
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("email", stremail);
			} else{
				out.print("");
			}	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
}
