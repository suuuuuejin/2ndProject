package kr.gjai.hwabun.entity;

import lombok.Data;

@Data
public class RecoDTO {

	private int reco_seq;
	private int cos_seq;

	private int skin_seq; // 스킨 순번
	private String mb_id; // 회원 아이디
	private int oil; // 유분
	private int water; // 수분
	private int color; // 색소
	private int wrinkle; // 주름
	private int sagging; // 피부쳐짐
	private int trouble; // 트러블
	private int pore; // 모공
	private int sensi; // 민감성
	private String ingredient; // 성분
	private String reg_date; // 등록일자
	private int skintest_cnt; // 테스트 진행 횟수
	
}
