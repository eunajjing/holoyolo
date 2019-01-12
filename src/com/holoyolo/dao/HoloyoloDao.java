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

import com.holoyolo.dto.*;

public class HoloyoloDao {
	DataSource datasource = null;

	public HoloyoloDao() throws NamingException {
		Context context = new InitialContext();
		// JNDI
		// context : container(was) 안에서 이름기반으로 검색 제공
		datasource = (DataSource) context.lookup("java:comp/env/jdbc/oracle");

		// datasource.getConnection() > POOL 에 있는 연결객체 얻어오기
		// 다쓰면 반환 : datasource.close()
	}

	public int getListCount() {
		int x = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			conn = datasource.getConnection();
			System.out.println("getConnection1");
			pstmt = conn.prepareStatement("select count(*) from board");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("getListCount 에러: " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return x;
	}

	public int getListSeachCount(String col, String word) {
		int x = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			conn = datasource.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("select count(*) from board");

			if (col != null) {
				if (col.equals("all")) {
					sql.append(" where board_title like ? or board_content like ?");
					pstmt = conn.prepareStatement(sql.toString());
					pstmt.setString(1, "%" + word + "%");
					pstmt.setString(2, "%" + word + "%");
					System.out.println(word);
				} else if (col.equals("board_title")) {
					sql.append(" where board_title like ? ");
					pstmt = conn.prepareStatement(sql.toString());
					pstmt.setString(1, "%" + word + "%");
				} else if (col.equals("board_content")) {
					sql.append(" where board_content like ? ");
					pstmt = conn.prepareStatement(sql.toString());
					pstmt.setString(1, "%" + word + "%");
				} else {
					System.out.println("HoloyoloDao getListSearchCount 메서드 에러");
				}
				rs = pstmt.executeQuery();

				if (rs.next()) {
					x = rs.getInt(1);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return x;
	}

	/*
	 * 공지사항 상단 고정 dao
	 */
	public ArrayList<BoardDto> getNoticeList() throws SQLException {
		// select id,email,content from memo

		// Class.forName("oracle.jdbc.OracleDriver");
		// conn =
		// DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","kosta","1004");
		// 위 코드 생략

		System.out.println("공지사항 목록만 가져올거야");
		PreparedStatement pstmt = null;
		String sql = "select * from (select member_email, board_code, board_title, board_date, board_notice from (select * from board order by board_code desc)) where board_notice='Y'";

		// 연결 객체 얻기
		Connection conn = datasource.getConnection();
		pstmt = conn.prepareStatement(sql);

		System.out.println("공지사항 목록 문제 없어?");
		ResultSet rs = pstmt.executeQuery();

		ArrayList<BoardDto> freeboard1 = new ArrayList<BoardDto>();

		while (rs.next()) {
			BoardDto m = new BoardDto();
			m.setMember_email(rs.getString("member_email"));
			m.setBoard_code(rs.getInt("board_code"));
			m.setBoard_title(rs.getString("board_title"));
			m.setBoard_date(rs.getDate("board_date"));
			m.setBoard_notice(rs.getString("board_notice"));
			freeboard1.add(m);
		}

		System.out.println("board_notice가지고 왔겠쥐~?");
		rs.close();
		pstmt.close();
		conn.close(); // 반환하기
		System.out.println("프리보드 반환할거야?");
		return freeboard1;
	}

	/*
	 * 자유게시판 목록
	 */

	public ArrayList<BoardDto> getFreeboardList(int page, int limit) throws SQLException {
		// select id,email,content from memo

		// Class.forName("oracle.jdbc.OracleDriver");
		// conn =
		// DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","kosta","1004");
		// 위 코드 생략

		System.out.println("여기서 에러나는건가?");
		PreparedStatement pstmt = null;
		String sql = "select * from (select rownum rnum, member_email, board_code, board_title, board_date, board_notice from (select * from board order by board_code desc)) where rnum>=? and rnum<=?";

		// 연결 객체 얻기
		Connection conn = datasource.getConnection();
		int startrow = (page - 1) * 10 + 1;
		int endrow = startrow + limit - 1;
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, startrow);
		pstmt.setInt(2, endrow);

		System.out.println("문제 없어?");
		ResultSet rs = pstmt.executeQuery();

		ArrayList<BoardDto> freeboard = new ArrayList<BoardDto>();

		while (rs.next()) {
			BoardDto m = new BoardDto();
			m.setMember_email(rs.getString("member_email"));
			m.setBoard_code(rs.getInt("board_code"));
			m.setBoard_title(rs.getString("board_title"));
			m.setBoard_date(rs.getDate("board_date"));
			m.setBoard_notice(rs.getString("board_notice"));
			freeboard.add(m);
		}

		System.out.println("board_notice가지고 왔겠쥐~?");
		rs.close();
		pstmt.close();
		conn.close(); // 반환하기
		System.out.println("프리보드 반환할거야?");
		return freeboard;
	}

	/*
	 * 자유게시판 검색기능
	 */

	public ArrayList<BoardDto> freeboardSeach(String col, String word, int page, int limit) throws SQLException {

		System.out.println("공구 게시판 게시물 리스트 중 검색한 결과 보기 위해 dao 도달");
		Connection conn = datasource.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from BOARD");
		PreparedStatement pstmt = null;

		ArrayList<BoardDto> freeboard = new ArrayList<BoardDto>();

		if (col != null) {
			if (col.equals("all")) {
				sql.append(" where board_title like ? or board_content like ?" + " order by board_code desc");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, "%" + word + "%");
				pstmt.setString(2, "%" + word + "%");
				System.out.println(word);
			} else if (col.equals("board_title")) {
				sql.append(" where board_title like ? " + " order by board_code desc");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, "%" + word + "%");
			} else if (col.equals("board_content")) {
				sql.append(" where board_content like ? " + " order by board_code desc");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, "%" + word + "%");
			} else {
				System.out.println("OrderPostListDao OrderPostListSeach 메서드 에러");
			}
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDto m = new BoardDto();
				m.setBoard_code(rs.getInt("Board_code"));
				m.setBoard_title(rs.getString("Board_title"));
				m.setBoard_date(rs.getDate("Board_date"));
				freeboard.add(m);
			}

			rs.close();
			pstmt.close();
			conn.close(); // 반환하기

		}

		return freeboard;
	}
	// 글 목록 보기
	/*
	 * public List getBoardList(int page, int limit){ String
	 * board_list_sql="select board_code, board_title, board_date from board"; List
	 * list = new ArrayList(); PreparedStatement pstmt = null; ResultSet rs = null;
	 * System.out.println("겟보드리스트왔니?"); int startrow=(page-1)*10+1; //읽기 시작할 row 번호
	 * int endrow=startrow+limit-1; //읽을 마지막 row 번호 try{
	 * 
	 * conn = datasource.getConnection(); pstmt =
	 * conn.prepareStatement(board_list_sql); pstmt.setInt(1, startrow);
	 * pstmt.setInt(2, endrow); rs = pstmt.executeQuery();
	 * System.out.println("겟보드리스트왔어?!"); while(rs.next()){ BoardDto board = new
	 * BoardDto(); board.setBoard_code(rs.getInt("Board_code"));
	 * 
	 * board.setBoard_title(rs.getString("Board_title"));
	 * board.setBoard_date(rs.getDate("Board_date")); list.add(board); } return
	 * list; }catch(Exception ex){ System.out.println("getBoardList 에러 : " + ex);
	 * }finally{ if(rs!=null) try{rs.close();}catch(SQLException ex){}
	 * if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} if(conn!=null)
	 * try{conn.close();}catch(SQLException ex){} } return null; }
	 */

	/*
	 * 글 내용 보기
	 */
	public BoardDto getDetail(int num) throws Exception {
		BoardDto board = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement("select * from board where Board_code=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				board = new BoardDto();
				board.setBoard_code(rs.getInt("Board_code"));
				board.setBoard_title(rs.getString("Board_title"));
				board.setMember_email(rs.getString("Member_email"));
				board.setBoard_content(rs.getString("Board_content"));
				board.setBoard_file(rs.getString("Board_file"));
				board.setBoard_date(rs.getDate("Board_date"));
				board.setBoard_notice(rs.getString("Board_notice"));
			}
			return board;
		} catch (Exception ex) {
			System.out.println("getDetail 에러 : " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return null;
	}

	/*
	 * 자유게시판 글 수정하기
	 */

	public boolean boardModify(BoardDto modifyboard) throws Exception {
		String sql = "update board set Board_title=?,Board_content=?,Board_file=?,Board_notice=? where Board_code=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("내용이 수정 되었대~~~~~~!!!!!!!!!!!박수환호!!!!!!!!");
		try {
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, modifyboard.getBoard_title());
			pstmt.setString(2, modifyboard.getBoard_content());
			pstmt.setString(3, modifyboard.getBoard_file());
			pstmt.setString(4, modifyboard.getBoard_notice());
			pstmt.setInt(5, modifyboard.getBoard_code());
			pstmt.executeUpdate();
			return true;

		} catch (Exception ex) {
			System.out.println("boardModify 에러 : " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return false;
	}

	public ArrayList<MemberDto> getMemberList() throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "select * from MEMBER";

		// 연결 객체 얻기
		Connection conn = datasource.getConnection();
		//
		pstmt = conn.prepareStatement(sql);

		ResultSet rs = pstmt.executeQuery();

		ArrayList<MemberDto> memberlist = new ArrayList<MemberDto>();

		while (rs.next()) {
			MemberDto m = new MemberDto();
			m.setMember_email(rs.getString("member_email"));
			m.setMember_password(rs.getString("member_password"));
			memberlist.add(m);
		}

		rs.close();
		pstmt.close();
		conn.close(); // 반환하기
		return memberlist;
	}

	/*
	 * 자유게시판 글삭제
	 */
	public boolean boardDelete(int num){
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;

		String board_delete_sql="delete from board where board_code=?";		
		int result=0;		
		try{
			conn = datasource.getConnection();
			pstmt=conn.prepareStatement(board_delete_sql);
			pstmt.setInt(1, num);
			result=pstmt.executeUpdate();
			if(result==0)return false;			
			return true;
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
	
	
	/*
	 * 자유게시판 글삭제할 때 이 댓글들도 같이 삭제 됨. 
	 */
public boolean boardDelete1(int num){
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String board_comm_delete="delete from BOARD_COMM where board_code=?";
		
		int result=0;		
		try{
			conn = datasource.getConnection();
			pstmt=conn.prepareStatement(board_comm_delete);
			pstmt.setInt(1, num);
			result=pstmt.executeUpdate();
			if(result==0)return false;			
			return true;
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

	/*
	 * 자유게시판 새글 쓰기
	 */
	public boolean boardInsert(BoardDto board) {
		int num = 0;
		String sql = "";
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		String title = board.getBoard_title();
		System.out.println("제목무엇이냐~?" + title);
		String notice = board.getBoard_notice();
		System.out.println("노티스 무엇이냐~?" + notice);
		try {
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement("select max(board_code) from board");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				num = rs.getInt(1) + 1;
			} else {
				num = 1;
			}

			pstmt.close();

			sql = "insert into board "
					+ "(board_code, member_email, board_title, board_content, board_file, board_date, board_notice)"
					+ "values(?,?,?,?,?,sysdate,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getMember_email());
			pstmt.setString(3, board.getBoard_title());
			pstmt.setString(4, board.getBoard_content());
			pstmt.setString(5, board.getBoard_file());
			pstmt.setString(6, board.getBoard_notice());
			result = pstmt.executeUpdate();
			if (result == 0) {
				return false;
			}
			return true;
		} catch (Exception ex) {
			System.out.println("boardInsert 에러 : " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return false;
	}

	public int insertMember(String email, String password) throws SQLException {
		int resultrow = 0;
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {

			String sql = "insert into member(member_email,member_password) values(?,?)";
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);

			resultrow = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("Insert :" + e.getMessage());
		} finally {
			pstmt.close();
			conn.close();
		}

		return resultrow;
	}

	public int memberModify(MemberDto dto) throws SQLException {
		PreparedStatement pstmt = null;
		Connection conn = null;
		int resultrow = 0;
		try {
			String sql = "update member set member_email, member_password=? where member_email=? ";
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMember_email());
			pstmt.setString(2, dto.getMember_password());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return resultrow;
	}

	// 추가함수 (비동기 통해서 ID 유무)
	public String isIdCheckById(String email) throws SQLException {
		String ismemoid = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {

			String sql = "select member_email from member where member_email=?";
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				ismemoid = "이미 사용중인 이메일 입니다.";

			} else {
				ismemoid = "사용 가능한 이메일 입니다.";
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return ismemoid;
	}

	public boolean LoginCheck(String email, String password) throws SQLException {
		boolean bcheck = false;
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;

		try {

			String sql = "select member_email, member_password from member where member_email=?";
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (password.equals(rs.getString("member_password"))) {
					bcheck = true;
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return bcheck;
	}

	public int memberUpdate(String email, String password) throws SQLException {
		int resultrow = 0;
		PreparedStatement pstmt = null;
		Connection conn = null;

		try {
			String sql = "update member set member_password=? where member_email=?";
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, password);
			pstmt.setString(2, email);

			resultrow = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return resultrow;
	}

	public int memberDelete(String email) throws SQLException {
		int resultRow = 0;
		PreparedStatement pstmt = null;
		Connection conn = null;

		try {
			String sql = "delete from member where member_email=?";
			conn = datasource.getConnection();

			conn.setAutoCommit(false);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			resultRow = pstmt.executeUpdate();

			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return resultRow;
	}

	public ArrayList<OrderPost> getOrderPostList(int page, int limit) throws SQLException {
		// select id,email,content from memo

		// Class.forName("oracle.jdbc.OracleDriver");
		// conn =
		// DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","kosta","1004");
		// 위 코드 생략
		System.out.println("공구 게시판 게시물 리스트를 보기 위해 dao 도달");
		PreparedStatement pstmt = null;
		String sql = "select * from "
				+ "(select rownum rnum, goods_code,goods_name,goods_date, goods_price, goods_now_qty, goods_state"
				+ " from (select * from BBS_GOODS order by goods_code desc))" + " where rnum>=? and rnum<=?";

		// 연결 객체 얻기
		Connection conn = datasource.getConnection();
		//

		int startrow = (page - 1) * 10 + 1;
		int endrow = startrow + limit - 1;
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, startrow);
		pstmt.setInt(2, endrow);

		ResultSet rs = pstmt.executeQuery();

		ArrayList<OrderPost> orderboard = new ArrayList<OrderPost>();

		while (rs.next()) {
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
		conn.close(); // 반환하기
		return orderboard;
	}

	public ArrayList<OrderPost> OrderPostListSeach(String col, String word, int page, int limit) throws SQLException {

		System.out.println("공구 게시판 게시물 리스트 중 검색한 결과 보기 위해 dao 도달");
		Connection conn = datasource.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("select goods_code, goods_name, goods_date, " + "goods_price, goods_now_qty, goods_state "
				+ "from BBS_GOODS");
		PreparedStatement pstmt = null;

		ArrayList<OrderPost> orderboard = new ArrayList<OrderPost>();

		if (col != null) {
			if (col.equals("all")) {
				sql.append(" where goods_name like ? or goods_content like ?" + " order by goods_code desc");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, "%" + word + "%");
				pstmt.setString(2, "%" + word + "%");
				System.out.println(word);
			} else if (col.equals("goods_name")) {
				sql.append(" where goods_name like ? " + " order by goods_code desc");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, "%" + word + "%");
			} else if (col.equals("goods_content")) {
				sql.append(" where goods_content like ? " + " order by goods_code desc");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, "%" + word + "%");
			} else {
				System.out.println("OrderPostListDao OrderPostListSeach 메서드 에러");
			}
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
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
			conn.close(); // 반환하기

		}

		return orderboard;
	}

	public String getMemberPassword(String email) throws SQLException {
		String getPassword = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;

		try {

			String sql = "select member_password from member where member_email=?";
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				getPassword = rs.getString("member_password");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return getPassword;
	}

	public ArrayList<BoardDto> MemberBoardList() throws SQLException {

		PreparedStatement pstmt = null;
		String sql = "select board_code, board_title, board_date from board order by board_date desc";

		Connection conn = datasource.getConnection();

		pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		ArrayList<BoardDto> MemberBoardList = new ArrayList<BoardDto>();

		while (rs.next()) {
			BoardDto m = new BoardDto();
			m.setBoard_code(rs.getInt("board_code"));
			m.setBoard_title(rs.getString("board_title"));
			m.setBoard_date(rs.getDate("board_date"));
			MemberBoardList.add(m);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return MemberBoardList;
	}

	public ArrayList<BoardcommDto> MemberCommList() throws SQLException {

		PreparedStatement pstmt = null;
		String sql = "select board_comm_code, board_comm_content, board_comm_date from board_comm order by board_comm_date desc";

		Connection conn = datasource.getConnection();

		pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		ArrayList<BoardcommDto> MemberCommList = new ArrayList<BoardcommDto>();

		while (rs.next()) {
			BoardcommDto mcomm = new BoardcommDto();
			mcomm.setBoard_code(rs.getInt("board_comm_code"));
			mcomm.setBoard_comm_content(rs.getString("board_comm_content"));
			mcomm.setBoard_comm_date(rs.getString("board_comm_date"));
			MemberCommList.add(mcomm);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return MemberCommList;
	}

	public ArrayList<ChartData> getChartData() throws SQLException {
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		ArrayList<ChartData> data = null;

		try {

			String sql = "select board_date, count(board_code) from board group by board_date order by board_date desc";
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			data = new ArrayList<ChartData>();
			int i = 0;
			while (rs.next()) {
				ChartData result = new ChartData();
				result.setBoard_date(rs.getDate("board_date"));
				result.setCount(rs.getInt(2));
				data.add(result);
				i++;
				if (i == 5)
					break;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return data;
	}
}