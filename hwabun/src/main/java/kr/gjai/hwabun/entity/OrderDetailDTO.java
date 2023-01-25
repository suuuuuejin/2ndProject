package kr.gjai.hwabun.entity;

import lombok.Data;

@Data
public class OrderDetailDTO {

	private int od_seq;
	private int order_seq;
	private int cos_seq;
	private int cos_cnt;
	private String cos_name;
	private String cos_photo1;
	private int cos_price;
	private int price;
}
