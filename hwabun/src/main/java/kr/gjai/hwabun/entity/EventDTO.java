package kr.gjai.hwabun.entity;

import lombok.Data;

@Data
public class EventDTO { 
	
	private long event_seq;
	private String event_time; // 이벤트 시간
	private int event_type; // 이벤트 유형
	private long cos_seq; // 화장품 순번
	private String user_id; // 사용자 아이디
	private String user_session; // 사용자 세션
	private String admin_id; // 관리자 아이디
	
}