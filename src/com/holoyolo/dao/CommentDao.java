package com.holoyolo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.holoyolo.dto.BoardDto;
import com.holoyolo.dto.BoardcommDto;

public class CommentDao {
	DataSource datasource = null;
	Connection conn = null;
	
	public CommentDao() throws NamingException {
		Context context = new InitialContext();
		//JNDI 
		//context : container(was) 안에서 이름기반으로 검색 제공
		datasource = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
		
		//datasource.getConnection() > POOL 에 있는 연결객체 얻어오기
		//다쓰면 반환 : datasource.close()
	}
	

	public ArrayList<BoardcommDto> getCommtList(int board_code) throws SQLException{
		System.out.println("댓글 리스트를 조회하기 위해 dao 도달");
		
		PreparedStatement pstmt = null;
		String sql = "select board_comm_code, member_email, board_comm_content, "
				+ "TO_CHAR(board_comm_date, 'YYYY-MM-DD HH24:MI:SS') AS board_comm_date"
				+ " from BOARD_COMM "
				+ "where board_code=? order by board_comm_code";
		
		//연결 객체 얻기
		Connection conn= datasource.getConnection();
		//

		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, board_code);

		
		ResultSet rs = pstmt.executeQuery();
		
		ArrayList<BoardcommDto> commboard = new ArrayList<BoardcommDto>();
		 
		while(rs.next()) {
			BoardcommDto m = new BoardcommDto();
			m.setBoard_comm_code(rs.getInt("board_comm_code"));
			m.setMember_email(rs.getString("member_email"));
			m.setBoard_comm_content(rs.getString("board_comm_content"));
			m.setBoard_comm_date(rs.getString("board_comm_date"));

			commboard.add(m);
		}
		
		rs.close();
		pstmt.close();
		conn.close(); //반환하기
		if (commboard.size()==0) return null;
		else return commboard;
	}
	
	public boolean commModify(BoardcommDto modifycommboard) throws Exception{
		String sql="update BOARD_COMM set board_comm_content=? where board_comm_code=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		System.out.println("댓글 수정을 위해 dao 도달");
		try{
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, modifycommboard.getBoard_comm_content());
			pstmt.setInt(2, modifycommboard.getBoard_comm_code());
			pstmt.executeUpdate();
			return true;
			
		}catch(Exception ex){
			System.out.println("boardModify 에러 : " + ex);
			ex.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
			}
		return false;
	}
	
	public boolean commDelete(int num){
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String board_delete_sql="delete from BOARD_COMM where board_comm_code=?";		
		int result=0;		
		try{
			System.out.println("댓글 삭제를 위해 dao 도달");
			conn = datasource.getConnection();
			pstmt=conn.prepareStatement(board_delete_sql);
			pstmt.setInt(1, num);
			result=pstmt.executeUpdate();
			if(result!=0) return true;
		}catch(Exception ex){
			System.out.println("boardDelete 에러 : "+ex);
		}finally{
			try{
				if(pstmt!=null)pstmt.close();
			}catch(Exception ex) {}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}
		
		return false;
	}
	
	public boolean boardInsert(int board_code, String member_email, String board_comm_content){
		int num =0;	
		int result=0;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		Connection conn = null;
		
		try{
			conn = datasource.getConnection();
			pstmt=conn.prepareStatement("select max(board_comm_code) from board_comm");
			rs = pstmt.executeQuery();			
			
			if(rs.next()) { 	
				num =rs.getInt(1)+1;
			}else{	
				num=1;
			}
			
			pstmt.close();
			
			String sql="insert into BOARD_COMM "+
					"(board_comm_code, board_code, member_email, board_comm_content, board_comm_date)"
					+ "values(?,?,?,?,sysdate)";			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, board_code);
			pstmt.setString(3, member_email);
			pstmt.setString(4, board_comm_content);			
			result=pstmt.executeUpdate();
		
			if(result==0){
				return false;
			}
			return true;
		}catch(Exception ex){
			System.out.println("boardInsert 에러 : "+ex);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}
		return false;
	}
	
	
	
}
