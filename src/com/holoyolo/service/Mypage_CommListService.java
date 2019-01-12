package com.holoyolo.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.holoyolo.action.Action;
import com.holoyolo.action.ActionForward;
import com.holoyolo.dao.HoloyoloDao;
import com.holoyolo.dto.BoardcommDto;

public class Mypage_CommListService implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward foward = null;
		try {
			request.setCharacterEncoding("UTF-8");

			HoloyoloDao dao = new HoloyoloDao();
			ArrayList<BoardcommDto> membercommlist = dao.MemberCommList();
			request.setAttribute("membercommlist", membercommlist);

			foward = new ActionForward();
			foward.setRedirect(false);
			foward.setPath("/mypage/LoginMain.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return foward;
	}
}
