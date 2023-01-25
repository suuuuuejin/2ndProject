package kr.gjai.hwabun.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class UserSkinDTO {
	
	private int skin_seq; // 스킨 순번
	private String mb_id; // 회원 아이디
	private float oil; // 유분
	private float water; // 수분
	private float color; // 색소
	private float wrinkle; // 주름
	private float trouble; // 트러블
	private float sensi; // 민감성
	private String ingredient; // 성분
	private String reg_date; // 등록일자
	private String mbti; // 피부 mbti
	
}
