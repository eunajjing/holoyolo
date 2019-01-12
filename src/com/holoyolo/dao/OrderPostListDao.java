package com.holoyolo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.holoyolo.dto.*;

/*
@class : OrderPost
 @Date : 2018-08-22
 @Author : 고은아
 @Desc : bbs_goods 엔티티 dao(검색, 테이블 뿌리기, 페이징 관련 로직 메서드 구현)
*/


public class OrderPostListDao {
	DataSource datasource = null;
	Connection conn = null;
	
	public OrderPostListDao() throws NamingException {
		Context context = new InitialContext();
		//JNDI 
		//context : container(was) 안에서 이름기반으로 검색 제공
		datasource = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
		
		//datasource.getConnection() > POOL 에 있는 연결객체 얻어오기
		//다쓰면 반환 : datasource.close()
	}
	
	
	public int getListCount() {
		int x= 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = datasource.getConnection();
			pstmt=conn.prepareStatement("select count(*) from BBS_GOODS");
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				x=rs.getInt(1);
			}
		}catch(Exception ex){
			ex.printStackTrace();	
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}
		return x;
	}
	
	public int getListSeachCount(String col, String word) {
		int x= 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = datasource.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("select count(*) from BBS_GOODS");
			
			if (col!=null) { 
				if (col.equals("all")) {
					sql.append(" where goods_name like ? or goods_content like ?");
					pstmt = conn.prepareStatement(sql.toString());
					pstmt.setString(1, "%"+word+"%");
					pstmt.setString(2, "%"+word+"%");
					System.out.println(word);
				} else if (col.equals("goods_name")) {
					sql.append(" where goods_name like ? ");
					pstmt = conn.prepareStatement(sql.toString());
					pstmt.setString(1, "%"+word+"%");
				} else if (col.equals("goods_content")) {
					sql.append(" where goods_content like ? ");
					pstmt = conn.prepareStatement(sql.toString());
					pstmt.setString(1, "%"+word+"%");
				} else {
					System.out.println("OrderPostListDao OrderPostListSeach 메서드 에러");
				}
				rs = pstmt.executeQuery();
	
				if(rs.next()){
					x=rs.getInt(1);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();	
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}
		return x;
	}
	
	public ArrayList<OrderPost> getOrderPostList(int page, int limit) throws SQLException{
		//select id,email,content from memo
		
		//Class.forName("oracle.jdbc.OracleDriver");
		//conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","kosta","1004");
		//위 코드 생략
		System.out.println("공구 게시판 게시물 리스트를 보기 위해 dao 도달");
		PreparedStatement pstmt = null;
		String sql = "select * from "
				+ "(select rownum rnum, goods_code,goods_name,goods_date, goods_price, goods_now_qty, goods_state"
				+ " from (select * from BBS_GOODS order by goods_code desc))"
				+ " where rnum>=? and rnum<=?";
		
		//연결 객체 얻기
		Connection conn= datasource.getConnection();
		//
		
		int startrow=(page-1)*10+1;
		int endrow=startrow+limit-1;
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, startrow);
		pstmt.setInt(2, endrow);
		
		ResultSet rs = pstmt.executeQuery();
		
		ArrayList<OrderPost>	orderboard = new ArrayList<OrderPost>();
		 
		while(rs.next()) {
			OrderPost m = new OrderPost();
			m.setGoods_code(rs.getInt("goods_code"));
			m.setGoods_name(rs.getString("goods_name"));
			m.setGoods_date(rs.getDate("goods_date"));
			m.setGoods_price(rs.getInt("goods_price"));
			m.setGoods_now_qty(rs.getInt("goods_now_qty"));
			m.setGoods_state(rs.getString("goods_state"));
			orderboard.add(m);
		}
		
		rs.close();
		pstmt.close();
		conn.close(); //반환하기
		return orderboard;
	}
	
	public ArrayList<OrderPost> OrderPostListSeach(String col, String word, int page, int limit) throws SQLException{
		
		
		System.out.println("공구 게시판 게시물 리스트 중 검색한 결과 보기 위해 dao 도달");
		Connection conn= datasource.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("select goods_code, goods_name, goods_date, "
				+ "goods_price, goods_now_qty, goods_state "
				+ "from BBS_GOODS");
		PreparedStatement pstmt = null;

		ArrayList<OrderPost>	orderboard = new ArrayList<OrderPost>();
		
		if (col!=null) { 
		if (col.equals("all")) {
			sql.append(" where goods_name like ? or goods_content like ?"
					+ " order by goods_code desc");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, "%"+word+"%");
			pstmt.setString(2, "%"+word+"%");
			System.out.println(word);
		} else if (col.equals("goods_name")) {
			sql.append(" where goods_name like ? "
					+ " order by goods_code desc");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, "%"+word+"%");
		} else if (col.equals("goods_content")) {
			sql.append(" where goods_content like ? "
					+ " order by goods_code desc");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, "%"+word+"%");
		} else {
			System.out.println("OrderPostListDao OrderPostListSeach 메서드 에러");
		}
		ResultSet rs = pstmt.executeQuery();
		
			while(rs.next()) {
				OrderPost m = new OrderPost();
				m.setGoods_code(rs.getInt("goods_code"));
				m.setGoods_name(rs.getString("goods_name"));
				m.setGoods_date(rs.getDate("goods_date"));
				m.setGoods_price(rs.getInt("goods_price"));
				m.setGoods_now_qty(rs.getInt("goods_now_qty"));
				m.setGoods_state(rs.getString("goods_state"));
				orderboard.add(m);
			}
		
		
		rs.close();
		pstmt.close();
		conn.close(); //반환하기
		
		
		}
		
		return orderboard;
	}
	
	public OrderPost getDetail(int goods_code) throws Exception{
		System.out.println(goods_code);
		OrderPost vo = null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		try{
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement("select * from BBS_GOODS where goods_code=?");
			pstmt.setInt(1, goods_code);
			rs= pstmt.executeQuery();
			
			if(rs.next()){
				vo = new OrderPost();
				vo.setGoods_code(rs.getInt("goods_code"));
				vo.setGoods_name(rs.getString("goods_name"));
				vo.setGoods_content(rs.getString("goods_content"));
				vo.setGoods_date(rs.getDate("goods_date"));
				vo.setGoods_price(rs.getInt("goods_price"));
				vo.setGoods_now_qty(rs.getInt("goods_now_qty"));
				vo.setGoods_state(rs.getString("goods_state"));
			}
			return vo;
		}catch(Exception ex){
			System.out.println("getDetail 에러 : " + ex);
			ex.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt !=null)try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}
		return null;
	}
}