﻿package com.holoyolo.service;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.holoyolo.action.Action;
import com.holoyolo.action.ActionForward;
import com.holoyolo.dao.HoloyoloDao;
import com.holoyolo.dto.BoardDto;

public class Mypage_BoardListService implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward foward = null;
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");

			HoloyoloDao dao = new HoloyoloDao();
			dao = new HoloyoloDao();
			ArrayList<BoardDto> freeboard = dao.getFreeboardList(1, 2);
			
			
			JSONObject data = new JSONObject();
			JSONArray jsonarray = new JSONArray();
			
			for (int i = 0; i < freeboard.size(); i++) {
				JSONObject subdata = new JSONObject();
				subdata.put("No", freeboard.get(i).getBoard_code());
				
				subdata.put("제목", freeboard.get(i).getBoard_title());

				SimpleDateFormat simplef = new SimpleDateFormat("yyyy-MM-dd");
				String resultf = simplef.format(freeboard.get(i).getBoard_date());
				subdata.put("등록일", resultf);
				jsonarray.add(subdata);
			}
			data.put("free", jsonarray);
			
			
			PrintWriter out = response.getWriter();
			out.print(data);

			// request.setAttribute("freeboard_indexpage", json);

			// foward = new ActionForward();
			// foward.setRedirect(false);
			// foward.setPath("index.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return foward;
	}
}
