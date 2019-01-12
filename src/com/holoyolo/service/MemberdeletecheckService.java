package com.holoyolo.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.holoyolo.action.Action;
import com.holoyolo.action.ActionForward;
import com.holoyolo.dao.HoloyoloDao;

public class MemberdeletecheckService implements Action {
   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

      ActionForward forward = null;

      HttpSession session = request.getSession();
      String email = (String) session.getAttribute("email");

      String password;
      String inputpassword;
      try {
         HoloyoloDao dao = new HoloyoloDao();
         password = dao.getMemberPassword(email);
         inputpassword = request.getParameter("deletememberpassword");
         PrintWriter out = response.getWriter();

         if (password.equals(inputpassword)) {
            dao.memberDelete(email);
            session.invalidate();
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