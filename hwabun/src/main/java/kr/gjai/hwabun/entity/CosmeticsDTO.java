package kr.gjai.hwabun.entity;

import lombok.Data;

@Data
public class CosmeticsDTO { // 화장품 정보 엔티티
	
	private long cos_seq; // 화장품 순번
	private String cos_name; // 화장품 명
	private int cos_price; // 화장품 가격
	private String cos_ingredient; // 성분
	private String cos_photo1; // 화장품 사진1
	private String cos_photo2; // 화장품 사진2
	private String cos_type; // 화장품 제품 타입
	private int cos_sales_cnt; // 판매수량
	private String gubun_name; //브랜드 명
	private int review_cnt; // 리뷰수
	private float review_rating; // 평점
	private int cos_likes; //좋아요 수
	private String gubun_value; // 카테고리 구분값
	private float percent; //그래프에 뿌릴 화장품 타입별 백분률 
}


