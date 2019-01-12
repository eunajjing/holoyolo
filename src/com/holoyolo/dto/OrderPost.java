package com.holoyolo.dto;
/*
@class : OrderPost
 @Date : 2018-08-21
 @Author : 고은아
 @Desc : bbs_goods(공동구매 게시판) 엔티티 vo
*/
import java.sql.Date;

public class OrderPost {
	private int goods_code;
	private String goods_name;
	private String goods_content;
	private Date goods_date;
	private int goods_price;
	private int goods_now_qty;
	private String goods_state;
	
	
	
	public Date getGoods_date() {
		return goods_date;
	}
	public void setGoods_date(Date goods_date) {
		this.goods_date = goods_date;
	}
	public int getGoods_code() {
		return goods_code;
	}
	public void setGoods_code(int goods_code) {
		this.goods_code = goods_code;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getGoods_content() {
		return goods_content;
	}
	public void setGoods_content(String goods_content) {
		this.goods_content = goods_content;
	}

	public int getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(int goods_price) {
		this.goods_price = goods_price;
	}
	public int getGoods_now_qty() {
		return goods_now_qty;
	}
	public void setGoods_now_qty(int goods_now_qty) {
		this.goods_now_qty = goods_now_qty;
	}
	public String getGoods_state() {
		return goods_state;
	}
	public void setGoods_state(String goods_state) {
		this.goods_state = goods_state;
	}
	
	
}
