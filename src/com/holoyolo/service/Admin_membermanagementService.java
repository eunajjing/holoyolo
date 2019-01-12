package com.holoyolo.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.holoyolo.action.Action;
import com.holoyolo.action.ActionForward;
import com.holoyolo.dao.HoloyoloDao;
import com.holoyolo.dto.MemberDto;

public class Admin_membermanagementService implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward foward = null;
		try {
			request.setCharacterEncoding("UTF-8");
			
			HoloyoloDao dao = new HoloyoloDao();
			ArrayList<MemberDto> memberlist = dao.getMemberList();
			request.setAttribute("memberlist", memberlist);

			foward = new ActionForward();
			foward.setRedirect(false);
			foward.setPath("/mypage/admin_membermanagement.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return foward;
	}
}