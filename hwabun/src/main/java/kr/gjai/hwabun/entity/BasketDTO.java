package kr.gjai.hwabun.entity;

import lombok.Data;

@Data
public class BasketDTO {

	
	private int basket_seq;
	private int cos_seq;
	private int cnt;
	private String mb_id;
	private String reg_date;
	private String basket_status;
}
