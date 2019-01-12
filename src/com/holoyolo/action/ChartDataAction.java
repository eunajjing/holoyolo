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
import com.holoyolo.dto.ChartData;

public class ChartDataAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward foward = null;
		
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			
			HoloyoloDao dao = new HoloyoloDao();
			
			ArrayList<ChartData> chartdata = dao.getChartData(); 
			
			JSONObject data = new JSONObject();
			JSONArray jsonarray = new JSONArray();
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("type", "string");
			jsonarray.add(jsonobj);
			
			jsonobj = new JSONObject();
			jsonobj.put("type", "number");
			jsonarray.add(jsonobj);			
			data.put("cols", jsonarray);
			/*
// Create the data table.
  var jsonData = {
      "cols":[
          {"type":"string"},{"type":"number"}
          ],
      "rows":[
          {"c":[{"v":"2014-08-23 16:13:17"},{"v":294}]},
          {"c":[{"v":"2014-08-24 16:24:15"},{"v":131}]},
          {"c":[{"v":"2014-08-25 16:28:57"},{"v":182}]},
          {"c":[{"v":"2014-08-26 16:28:57"},{"v":200}]},
          {"c":[{"v":"2014-08-27 16:28:57"},{"v":150}]}
          ]
      };
			 */
			
			jsonarray = new JSONArray();
			for(int i=0; i<chartdata.size(); i++) {
				JSONArray jsonarray2 = new JSONArray();
				
				SimpleDateFormat simplef = new SimpleDateFormat("MM-dd");
				String resultf = simplef.format(chartdata.get(i).getBoard_date());
				jsonobj = new JSONObject();
				jsonobj.put("v", resultf);
				jsonarray2.add(jsonobj);
				jsonobj = new JSONObject();
				jsonobj.put("v", chartdata.get(i).getCount());
				jsonarray2.add(jsonobj);
				
				jsonobj = new JSONObject();
				jsonobj.put("c", jsonarray2);
				
				jsonarray.add(jsonobj);
			}
			data.put("rows", jsonarray);
			System.out.println(data);
			PrintWriter out = response.getWriter();
			out.print(data);
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		return foward;
	}
}
