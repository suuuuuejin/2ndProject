package kr.gjai.hwabun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
	
	private String mb_id; // 회원 아이디
	private String mb_pw; // 회원 비밀번호
	private String mb_post; // 회원 우편번호
	private String mb_addr1; // 회원 주소
	private String mb_addr2; // 회원 상세주소
	private String mb_grade; // 회원 등급
	private String mb_birthdate; // 회원 생년월일
	private int mb_point; // 회원 포인트
	private String mb_nick; // 회원 닉네임
	private char mb_gender; // 회원 성별
	private String mb_profile; // 회원 프로필사진
	private String mb_joindate; // 회원 가입일자
	private char mb_type; // 회원 유형
	private String mb_name; // 회원 이름
	
}
