package kr.gjai.hwabun.entity;

import lombok.Data;

@Data
public class OrderDTO {

	

	private int od_seq;
	private int cos_seq;
	private int cos_cnt;

	
	private int order_seq;
	private String order_date;
	private int total_amount;
	private int discount_amount;
	private int pay_amount;
	private String pay_method;
	private int piad_amount;
	private String mb_id;
	private char order_status;
}
