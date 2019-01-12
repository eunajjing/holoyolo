package com.holoyolo.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.holoyolo.dao.HoloyoloDao;
import com.holoyolo.dto.BoardDto;
import com.holoyolo.dto.OrderPost;

public class GroupbyBoardListUseIndexAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward foward = null;
		
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			
			HoloyoloDao dao = new HoloyoloDao();
			   
			dao = new HoloyoloDao();
			ArrayList<OrderPost> orderboard = dao.getOrderPostList(1, 5);
			
			JSONObject data = new JSONObject();
			JSONArray jsonarray = new JSONArray();
			//for(int i=0; i<freeboard.size(); i++) {
			for(int i=0; i<orderboard.size(); i++) {
				JSONObject subdata = new JSONObject();
				subdata.put("번호", orderboard.get(i).getGoods_code());
				subdata.put("상품명", orderboard.get(i).getGoods_name());
				
				SimpleDateFormat simplef = new SimpleDateFormat("yyyy-MM-dd");
				String resultf = simplef.format(orderboard.get(i).getGoods_date());
				subdata.put("날짜", resultf);
				jsonarray.add(subdata);
			}
			data.put("d", jsonarray);
			//System.out.println(data);
			//String json = "{ \"num\" :  10 , \"msg\" : 20 }";
			PrintWriter out = response.getWriter();
			out.print(data);

			//request.setAttribute("freeboard_indexpage", json);
			
//			foward = new ActionForward();
//			foward.setRedirect(false);
//			foward.setPath("index.jsp");
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		return foward;
	}
}
