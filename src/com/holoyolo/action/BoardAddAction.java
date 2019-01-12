package com.holoyolo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import com.holoyolo.dao.HoloyoloDao;
import com.holoyolo.dto.BoardDto;

public class BoardAddAction implements Action {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 HoloyoloDao boarddao=new HoloyoloDao();
		 BoardDto boarddata=new BoardDto();
	   	ActionForward forward=new ActionForward();
	   	
		String realFolder="";
   		String saveFolder="/boardupload";
   		
   		int fileSize=5*1024*1024;
   		
   		realFolder=request.getRealPath(saveFolder);
   		boolean result=false;
   		
   		try{   		
   			MultipartRequest multi=null;   			
   			multi=new MultipartRequest(request, realFolder, fileSize, "utf-8", new DefaultFileRenamePolicy());
   			System.out.println("왔느냐고");
   			boarddata.setMember_email(multi.getParameter("Member_email"));
	   		boarddata.setBoard_title(multi.getParameter("Board_title"));
	   		boarddata.setBoard_content(multi.getParameter("Board_content"));
	   		boarddata.setBoard_notice(multi.getParameter("Board_notice"));
	   		boarddata.setBoard_file(multi.getFilesystemName((String)multi.getFileNames().nextElement()));
	   		// dto수정후 set구문 추가
	
	   		
	   		result=boarddao.boardInsert(boarddata);
	   		System.out.println(result);
	   		System.out.println("됐다고");
	   		
	   		if(result==false){
	   			System.out.println("게시판 등록 실패");
	   			return null;
	   		}
	   		System.out.println("게시판 등록 완료");
	   		System.out.println("등록완료가 됐대!");
	   		forward.setRedirect(true);
	   		forward.setPath("BoardList.bo");
	   		System.out.println("보드리스트로 갔어?");
	   		return forward;
	   		
  		}catch(Exception ex){
   			ex.printStackTrace();
   		}
  		return null;
	}  	
}