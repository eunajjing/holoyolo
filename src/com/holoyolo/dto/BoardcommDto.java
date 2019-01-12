package com.holoyolo.dto;


public class BoardcommDto {
	int board_comm_code;
	int board_code;
	String member_email;
	String board_comm_content;
	String board_comm_date;
	
	public String getBoard_comm_date() {
		return board_comm_date;
	}
	public void setBoard_comm_date(String board_comm_date) {
		this.board_comm_date = board_comm_date;
	}
	public int getBoard_comm_code() {
		return board_comm_code;
	}
	public void setBoard_comm_code(int board_comm_code) {
		this.board_comm_code = board_comm_code;
	}
	public int getBoard_code() {
		return board_code;
	}
	public void setBoard_code(int board_code) {
		this.board_code = board_code;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getBoard_comm_content() {
		return board_comm_content;
	}
	public void setBoard_comm_content(String board_comm_content) {
		this.board_comm_content = board_comm_content;
	}


	
	
	
	
}
