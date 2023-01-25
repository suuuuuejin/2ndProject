package kr.gjai.hwabun.entity;


import lombok.Data;

@Data
public class StarDTO { // 화장품 정보 엔티티
	
	private int review_range; // 별 범위
	private int review_cnt; // 별점당 숫자
	private int percentage; // 별점 백분률

}
