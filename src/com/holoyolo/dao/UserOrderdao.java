package com.holoyolo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.holoyolo.dto.*;

/*
@class : UserOrderdao
 @Date : 2018-08-27
 @Author : 고은아
 @Desc : 공동구매 신청 엔티티 dao(insert만 구현, update 구현 안할 예정)
*/


public class UserOrderdao {
	
	DataSource datasource = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	public UserOrderdao() throws NamingException {
		Context context = new InitialContext();
		datasource = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}
	
	public boolean orderInsert(UserOrder userOrder) {
		int result=0;
		boolean resultretrun = false;
		System.out.println("주문서 등록하기 위해 dao 도달");
		
		try {
			conn = datasource.getConnection();
			String sql = "insert into GROUPBUY_AP (goods_code, member_email, orderer_name, "
					+ "orderer_phone, orderer_addr_code, orderer_addr_main, orderer_addr_detail, "
					+ "orderer_count, orderer_sum) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userOrder.getGoods_code());
			pstmt.setString(2, userOrder.getMember_email());
			pstmt.setString(3, userOrder.getOrderer_name());
			pstmt.setString(4, userOrder.getOrderer_phone());
			pstmt.setString(5, userOrder.getOrderer_addr_code());
			pstmt.setString(6, userOrder.getOrderer_addr_main());
			pstmt.setString(7, userOrder.getOrderer_addr_detail());
			pstmt.setString(8, userOrder.getOrderer_count());
			pstmt.setInt(9, userOrder.getOrderer_sum());
			
			result = pstmt.executeUpdate();
			
			if (result != 0)  resultretrun = true;

			
		} catch (Exception e) {e.printStackTrace();}
		finally {
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}
		return resultretrun;
	}
	
}
