package kr.gjai.hwabun.entity;

import lombok.Data;

@Data
public class RecReviewDTO{
	private int reco_seq;
	private int cos_seq;
	private String cos_photo1;
	private String mb_id;
	private String rv_content;
	private String cos_name;
	private String reg_date;
	
}
