package kr.gjai.hwabun.entity;

import lombok.Data;

//@Alias("ReviewDTO")
@Data
public class ReviewDTO { // 화장품 정보 엔티티
	
	private long review_seq; // 리뷰 순번
	private long cos_seq; // 화장품 순번
	private String cos_name; //화장품 이름
	private String review_content; // 리뷰내용
	private String mb_id; // 작성자 아이디
	private float review_rating; // 리뷰평점
	private int review_likes; // 리뷰 좋아요수
	private String review_photo; // 리뷰 사진
	private String review_date; // 댓글 작성 시간
	private String mb_nick; // 작성자 닉네임
	private String mb_name; //작성자 이름 
	private String filepath; //저장 파일 경로
	private String mb_profile; //작성자 프로필
	
}
