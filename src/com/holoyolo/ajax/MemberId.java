package com.holoyolo.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.holoyolo.dao.HoloyoloDao;

@WebServlet("/MemberId")
public class MemberId extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public MemberId() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String email = request.getParameter("email");
		System.out.println("입력한 email : " + email);
		HoloyoloDao dao;
		try {
			dao = new HoloyoloDao();
			String emailcheck = dao.isIdCheckById(email);
			out.print(emailcheck); // true ,false 문자
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
