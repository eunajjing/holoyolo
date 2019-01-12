package com.holoyolo.dto;

import java.sql.Date;

public class GoodsDto {
	int goods_code;
	String goods_name;
	String goods_content;
	Date goods_date;
	int goods_price;
	int goods_now_qty;
	String goods_state;
	
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
	public Date getGoods_date() {
		return goods_date;
	}
	public void setGoods_date(Date goods_date) {
		this.goods_date = goods_date;
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
