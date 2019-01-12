package com.holoyolo.dto;

import java.sql.Date;

public class ChartData {
	private Date board_date;
	private int count;
	
	public Date getBoard_date() {
		return board_date;
	}
	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
