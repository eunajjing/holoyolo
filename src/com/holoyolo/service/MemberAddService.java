package com.holoyolo.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.holoyolo.action.Action;
import com.holoyolo.action.ActionForward;
import com.holoyolo.dao.HoloyoloDao;

public class MemberAddService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward foward = null;
		try {

			request.setCharacterEncoding("UTF-8");

			String email = request.getParameter("email");
			String password = request.getParameter("password");

			HoloyoloDao dao = new HoloyoloDao();
			int result = dao.insertMember(email, password);

			String msg = "";
			String url = "";

			if (result > 0) {
				msg = "정상적으로 가입처리 되었습니다.";
				url = "index.jsp";
			} else {
				msg = "가입에 실패 하였습니다. 다시 시도해주세요.";
				url = "index.jsp";
			}
			request.setAttribute("board_msg", msg);
			request.setAttribute("board_url", url);

			/*
			 * response.setContentType("text/html;charset=UTF-8"); PrintWriter out =
			 * response.getWriter(); if(n>0){ out.print("<script>");
			 * out.print("alert('등록성공..');"); out.print("location.href='MemoList.memo';");
			 * out.print("</script>"); return null; }else{ out.print("<script>");
			 * out.print("alert('등록실패..');"); out.print("location.href='memo.html';");
			 * out.print("</script>"); return null;
			 * 
			 * }
			 */

			/*
			 * if(result > 0){ msg = "edit success"; url ="board_list.jsp"; }else{ msg =
			 * "edit fail"; url ="board_edit.jsp?idx="+idx; }
			 * request.setAttribute("board_msg", msg); request.setAttribute("board_url",
			 * url);
			 */

			foward = new ActionForward();
			foward.setRedirect(false);
			foward.setPath("/WEB-INF/views/redirect.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return foward;
	}

}
