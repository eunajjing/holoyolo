package com.holoyolo.dto;

/*
@class : UserOrder
 @Date : 2018-08-27
 @Author : 고은아
 @Desc : 유저가 공동구매를 신청했을 때 사용되는 vo
*/

public class UserOrder {
	
	private int goods_code;
	private String member_email;
	private String orderer_name;	
	private String orderer_phone;	
	private String orderer_addr_code;
	private String orderer_addr_main;
	private String orderer_addr_detail;
	private String orderer_count;
	private int orderer_sum;
	
	public int getGoods_code() {
		return goods_code;
	}
	public void setGoods_code(int goods_code) {
		this.goods_code = goods_code;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getOrderer_name() {
		return orderer_name;
	}
	public void setOrderer_name(String orderer_name) {
		this.orderer_name = orderer_name;
	}
	public String getOrderer_phone() {
		return orderer_phone;
	}
	public void setOrderer_phone(String orderer_phone) {
		this.orderer_phone = orderer_phone;
	}
	public String getOrderer_addr_code() {
		return orderer_addr_code;
	}
	public void setOrderer_addr_code(String orderer_addr_code) {
		this.orderer_addr_code = orderer_addr_code;
	}
	public String getOrderer_addr_main() {
		return orderer_addr_main;
	}
	public void setOrderer_addr_main(String orderer_addr_main) {
		this.orderer_addr_main = orderer_addr_main;
	}
	public String getOrderer_addr_detail() {
		return orderer_addr_detail;
	}
	public void setOrderer_addr_detail(String orderer_addr_detail) {
		this.orderer_addr_detail = orderer_addr_detail;
	}
	public String getOrderer_count() {
		return orderer_count;
	}
	public int getOrderer_sum() {
		return orderer_sum;
	}
	public void setOrderer_sum(int orderer_sum) {
		this.orderer_sum = orderer_sum;
	}
	public void setOrderer_count(String orderer_count) {
		this.orderer_count = orderer_count;
	}



	

	

}
